package endtoend;

import static org.assertj.core.api.Assertions.assertThat;

import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;

import org.junit.jupiter.api.Test;

import testutils.LBJFormTest;
import testutils.LBJTestUtils;

public class EndToEndTestRunner extends LBJFormTest {

	@Test
	public void test1() throws Exception {
		runEndToEndTest(new EndToEndTestCase1());
	}

	@Test
	public void test2() throws Exception {
		runEndToEndTest(new EndToEndTestCase2());
	}

	@Test
	public void test3() throws Exception {
		runEndToEndTest(new EndToEndTestCase3());
	}

	@Test
	public void test4() throws Exception {
		runEndToEndTest(new EndToEndTestCase4());
	}

	@Test
	public void test5() throws Exception {
		runEndToEndTest(new EndToEndTestCase5());
	}

	@Test
	public void test6() throws Exception {
		runEndToEndTest(new EndToEndTestCase6());
	}

	@Test
	public void test7() throws Exception {
		runEndToEndTest(new EndToEndTestCase7());
	}

	@Test
	public void test8() throws Exception {
		runEndToEndTest(new EndToEndTestCase8());
	}

	@Test
	public void test9() throws Exception {
		runEndToEndTest(new EndToEndTestCase9());
	}

	@Test
	public void test10() throws Exception {
		runEndToEndTest(new EndToEndTestCase10());
	}

	@Test
	public void test11() throws Exception {
		runEndToEndTest(new EndToEndTestCase11());
	}

	private void runEndToEndTest(EndToEndTestCase testCase) throws Exception {
		testCase.test();
		String expected = testCase.getExpectedXml();
		String actual = getStringFromClipboard();
		// used for replacement of names
		assertThat(actual).doesNotContain("$");
		LBJTestUtils.assertLiquibaseXmlEquals(expected, actual, testCase.checkXsd());
	}

	private static String getStringFromClipboard() throws Exception {
		return (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
	}

}
