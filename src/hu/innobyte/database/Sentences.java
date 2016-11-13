package hu.innobyte.database;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import hu.innobyte.question.Answers;

@XmlRootElement(name = "sentences")
public class Sentences {
	@XmlElement(name="sentence")
    public List<Sentence> sentences = new ArrayList<Sentence>();
	
	public void newSentence(Sentence newSentence) throws SentenceException {
		for(Sentence sentence : sentences) {
			if (sentence.getId().equals(newSentence.getId()) && sentence.getConfidence() == newSentence.getConfidence()) {
				throw new SentenceException();
			}
		}
		sentences.add(newSentence);
	}
	
	public void saveToXml(String path) {
    	try {
	    	JAXBContext context = JAXBContext.newInstance(Sentences.class);
	    	Marshaller marshaller = context.createMarshaller();
	    	marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,  Boolean.TRUE);
	    	marshaller.marshal(this, new File(path));
    	} catch (Exception e) {
    		System.err.println(String.format("Error in save XML, exception: %s, message: %s, path: %s", e.getClass(),e.getMessage(),path));
    	}
	}
	
	public static Sentences loadFromXml(String path) {
    	Sentences sentences = null;
    	
    	try {
    		JAXBContext jaxbContext = JAXBContext.newInstance(Sentence.class);
    		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
    		
    		sentences = (Sentences) jaxbUnmarshaller.unmarshal(new File(path));
    		
    	} catch (Exception e) {
    		System.err.println(String.format("Error in XML load, exception: %s, message: %s",e.getClass(),e.getMessage()));
    	}
    	
    	return sentences;
	}
}
