package hu.innobyte.question;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

public class DialogsTest {

	@Test
	public void test() throws DialogException {
		String filePath = new File(".").getAbsolutePath() + "\\files\\dialogs.xml";
		
		File file = new File(filePath);
		if (file.isFile()) {
			file.delete();
		}
		
		Dialogs testObj = new Dialogs();
		System.out.println(System.getProperty("user.dir"));
		
		testObj.newDialog(Dialog.builder()
				.question("Mennyi az egyenlegem?")
				.answer("5000ft")
				.build());
		
		testObj.newDialog(Dialog.builder()
				.question("Milyen szolgáltatásaim vannak?")
				.answer("Televízió, és telefon.")
				.build());
		
		testObj.saveIntoXML(filePath);
		
		assertEquals(testObj.dialogs.size(), 2);
		
	}

}
