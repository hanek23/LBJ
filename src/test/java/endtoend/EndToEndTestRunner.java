package endtoend;

import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;

import org.junit.jupiter.api.Test;

import testutils.LBJFormTest;
import testutils.LBJTestUtils;

public class EndToEndTestRunner extends LBJFormTest {

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

	@Test
	public void test4() throws Exception {
		runEndToEndTest(new EndToEndTest4());
	}

	@Test
	public void test5() throws Exception {
		runEndToEndTest(new EndToEndTest5());
	}

	@Test
	public void test6() throws Exception {
		runEndToEndTest(new EndToEndTest6());
	}

	@Test
	public void test7() throws Exception {
		runEndToEndTest(new EndToEndTest7());
	}

	@Test
	public void test8() throws Exception {
		runEndToEndTest(new EndToEndTest8());
	}

	@Test
	public void test9() throws Exception {
		runEndToEndTest(new EndToEndTest9());
	}

	private void runEndToEndTest(EndToEndTest testCase) throws Exception {
		testCase.test();
		String expected = testCase.getExpectedXml();
		String actual = getStringFromClipboard();
		LBJTestUtils.assertLiquibaseXmlEquals(expected, actual, testCase.checkXsd());
	}

	private static String getStringFromClipboard() throws Exception {
		return (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
	}

}
