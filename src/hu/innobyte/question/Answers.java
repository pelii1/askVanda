package hu.innobyte.question;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import hu.innobye.InterpreterData;
import hu.innobye.dummy.DummyInterpreter;

@XmlRootElement(name = "answers")
public class Answers {
	@XmlElement(name="answer")
    public List<Answer> answers = new ArrayList<Answer>();

    public String getAnswer(String question,String path) throws AnswerException {
    	DummyInterpreter dummyInterpreter = new DummyInterpreter(path);
    	
    	InterpreterData interpreterData =  dummyInterpreter.interpreterSentence(question);
    	int maxConfidence = 0;
    	String result = "";
    	
		for (Answer answer : answers) {
		    if (answer.getId().equalsIgnoreCase(interpreterData.getId()) && interpreterData.getConfidence() > maxConfidence) {
		    	maxConfidence = interpreterData.getConfidence();
		    	result = answer.getAnswer();
		    }
		}
		
		if (maxConfidence > 0) {
			return result;
		}

		throw new AnswerException();
    }

    public void newDialog(Answer newDialog) throws AnswerException {
		for (Answer dialog : answers) {
		    if (dialog.getId().equalsIgnoreCase(newDialog.getId()) && dialog.getConfidence() == dialog.getConfidence()) {
		    	throw new AnswerException();
		    }
		}
	
		answers.add(newDialog);
    }
    
    public static Answers loadFromFile(String path) {
    	Answers dialogs = null;
    	
    	try {
    		JAXBContext jaxbContext = JAXBContext.newInstance(Answers.class);
    		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
    		
    		dialogs = (Answers) jaxbUnmarshaller.unmarshal(new File(path));
    		
    	} catch (Exception e) {
    		System.err.println(String.format("Error in XML load, exception: %s, message: %s, path: %s",e.getClass(),e.getMessage(),path));
    	}
    	
    	return dialogs;
    }
    
    public void saveIntoXML(String path) {
    	try {
	    	JAXBContext context = JAXBContext.newInstance(Answers.class);
	    	Marshaller marshaller = context.createMarshaller();
	    	marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,  Boolean.TRUE);
	    	marshaller.marshal(this, new File(path));
    	} catch (Exception e) {
    		System.err.println(String.format("Error in save XML, exception: %s, message: %s, path: %s", e.getClass(),e.getMessage(),path));
    	}
    	
    }
}