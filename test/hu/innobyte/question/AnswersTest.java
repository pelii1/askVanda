package hu.innobyte.question;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

public class AnswersTest {

	@Test
	public void test() throws AnswerException {
		String filePath = new File(".").getAbsolutePath() + "\\files\\answers.xml";
		
		File file = new File(filePath);
		if (file.isFile()) {
			file.delete();
		}
		
		Answers testObj = new Answers();
		
		testObj.newDialog(Answer.builder()
				.id("SZAMLAEGYENLEG")
				.answer("A sz�mal tartoz�sod 15000ft")
				.build());
		
		testObj.newDialog(Answer.builder()
				.id("SZAMLEKERDEZES")
				.answer("A telefonsz�mod: 06-30-7777-777")
				.build());
		
		testObj.newDialog(Answer.builder()
				.id("SZOLGALTATASLEKERDEZES")
				.answer("Telefon �s internet el�fizet�ssel rendelkezel")
				.build());
		
		testObj.saveIntoXML(filePath);
		
		assertEquals(testObj.answers.size(), 3);
		
	}

}
