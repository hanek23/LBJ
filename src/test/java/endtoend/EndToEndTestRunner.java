package endtoend;

import static org.assertj.core.api.Assertions.assertThat;

import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;

import org.junit.jupiter.api.Test;

import constants.XmlPartsSupplier;
import testutils.LBJFormTestCase;
import testutils.LBJTestUtils;

class EndToEndTestRunner extends LBJFormTestCase {

	@Test
	void test1() throws Exception {
		runEndToEndTest(new EndToEndTestCase1());
	}

	@Test
	void test2() throws Exception {
		runEndToEndTest(new EndToEndTestCase2());
	}

	@Test
	void test3() throws Exception {
		runEndToEndTest(new EndToEndTestCase3());
	}

	@Test
	void test4() throws Exception {
		runEndToEndTest(new EndToEndTestCase4());
	}

	@Test
	void test5() throws Exception {
		runEndToEndTest(new EndToEndTestCase5());
	}

	@Test
	void test6() throws Exception {
		runEndToEndTest(new EndToEndTestCase6());
	}

	@Test
	void test7() throws Exception {
		runEndToEndTest(new EndToEndTestCase7());
	}

	@Test
	void test8() throws Exception {
		runEndToEndTest(new EndToEndTestCase8());
	}

	@Test
	void test9() throws Exception {
		runEndToEndTest(new EndToEndTestCase9());
	}

	@Test
	void test10() throws Exception {
		runEndToEndTest(new EndToEndTestCase10());
	}

	@Test
	void test11() throws Exception {
		runEndToEndTest(new EndToEndTestCase11());
	}

	@Test
	void test12() throws Exception {
		runEndToEndTest(new EndToEndTestCase12());
	}
	
	@Test
	void test13() throws Exception {
		runEndToEndTest(new EndToEndTestCase13());
	}
	
	@Test
	void test14() throws Exception {
		runEndToEndTest(new EndToEndTestCase14());
	}
	
	@Test
	void test15() throws Exception {
		runEndToEndTest(new EndToEndTestCase15());
	}
	
	@Test
	void test16() throws Exception {
		runEndToEndTest(new EndToEndTestCase16());
	}
	
	@Test
	void test17() throws Exception {
		runEndToEndTest(new EndToEndTestCase17());
	}

	private void runEndToEndTest(EndToEndTestCase testCase) throws Exception {
		testCase.test();
		String expected = testCase.getExpectedXml();
		expected = XmlPartsSupplier.replaceVersion(expected);
		String actual = getStringFromClipboard();
		// used for replacement of names
		assertThat(actual).doesNotContain("$");
		LBJTestUtils.assertLiquibaseXmlEquals(expected, actual, testCase.checkXsd());
	}

	private static String getStringFromClipboard() throws Exception {
		return (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
	}

}
