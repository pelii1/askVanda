package hu.innobyte.database;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

public class SentencesTest {

	@Test
	public void test() throws SentenceException {
		Sentences testObj = new Sentences();
		String filePath = new File(".").getAbsolutePath() + "\\files\\sentencesTest.xml";
		
		File file = new File(filePath);
		if (file.isFile()) {
			file.delete();
		}
		
		
		
		testObj.newSentence(Sentence.builder()
				.id("A01")
				.confidence(55)
				.sentence("He?")
				.build());
		
		testObj.newSentence(Sentence.builder()
				.id("A02")
				.confidence(85)
				.sentence("HeHee")
				.build());
		
		testObj.saveToXml(filePath);
		
		assertEquals(testObj.sentences.size(),2);
	}
}
