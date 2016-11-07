package hu.innobyte.question;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import hu.innobyte.util.SentenceOperations;

@XmlRootElement(name = "dialogs")
public class Dialogs {
	@XmlElement(name="dialog")
    public List<Dialog> dialogs = new ArrayList<Dialog>();

    public String getAnswer(String question) throws DialogException {
		for (Dialog dialog : dialogs) {
		    if (SentenceOperations.deleteSpace(dialog.getQuestion()).equalsIgnoreCase(SentenceOperations.deleteSpace(question))) {
		    	return dialog.getAnswer();
		    }
		}

		throw new DialogException();
    }

    public void newDialog(Dialog newDialog) throws DialogException {
		for (Dialog dialog : dialogs) {
		    if (dialog.getQuestion().equalsIgnoreCase(newDialog.getQuestion())) {
		    	throw new DialogException();
		    }
		}
	
		dialogs.add(newDialog);
    }
    
    public static Dialogs loadFromFile(String path) {
    	Dialogs dialogs = null;
    	
    	try {
    		JAXBContext jaxbContext = JAXBContext.newInstance(Dialogs.class);
    		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
    		
    		dialogs = (Dialogs) jaxbUnmarshaller.unmarshal(new File(path));
    		
    	} catch (Exception e) {
    		System.err.println(String.format("Error in XML load, exception: %s, message: %s",e.getClass(),e.getMessage()));
    	}
    	
    	return dialogs;
    }
    
    public void saveIntoXML(String path) {
    	try {
	    	JAXBContext context = JAXBContext.newInstance(Dialogs.class);
	    	Marshaller marshaller = context.createMarshaller();
	    	marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,  Boolean.TRUE);
	    	marshaller.marshal(this, new File(path));
    	} catch (Exception e) {
    		System.err.println(String.format("Error in save XML, exception: %s, message: %s, path: %s", e.getClass(),e.getMessage(),path));
    	}
    	
    }
}