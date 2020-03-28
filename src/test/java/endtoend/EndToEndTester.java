package endtoend;

import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;

import org.junit.jupiter.api.Test;

import testutils.LBJTestUtils;
import testutils.MainMenuTest;

public class EndToEndTester extends MainMenuTest {

	@Test
	public void test1() throws Exception {
		runEndToEndTest(new EndToEndTest1());
	}

	@Test
	public void test2() throws Exception {
		runEndToEndTest(new EndToEndTest2());
	}

	@Test
	public void test3() throws Exception {
		runEndToEndTest(new EndToEndTest3());
	}

	private void runEndToEndTest(EndToEndTest testCase) throws Exception {
		testCase.test();
		String expected = testCase.getExpectedXml();
		String actual = getStringFromClipboard();
		LBJTestUtils.assertLiquibaseXmlEquals(expected, actual, testCase.checkXsd());
	}

	public static String getStringFromClipboard() throws Exception {
		return (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
	}

}
