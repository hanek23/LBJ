package generator;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import constants.XmlParts;

public class GeneratorTest {

	private static final String AUTHOR = "hanek23";

	@Test
	public void testGetResources() {
		String fromString = XmlParts.CHANGELOG;
		String fromFile = XmlParts.changelogStart();
		assertTrue(StringUtils.deleteWhitespace(fromString).equals(StringUtils.deleteWhitespace(fromFile)));
	}

	@Test
	public void testGenerateTestTable1() {
		assertChangeLogEquals(new TestTable1());
	}

	@Test
	public void testGenerateTestTable2() {
		assertChangeLogEquals(new TestTable2());
	}

	@Test
	public void testGenerateTestTable3() {
		assertChangeLogEquals(new TestTable3());
	}

	@Test
	public void testGenerateTestTable4() {
		assertChangeLogEquals(new TestTable4());
	}

	private void assertChangeLogEquals(TestTableSupplier testTableSupplier) {
		String expected = StringUtils.deleteWhitespace(testTableSupplier.getExpectedTable());
		String actual = StringUtils.deleteWhitespace(
				Generator.generate(testTableSupplier.getTable(), testTableSupplier.getOperation(), AUTHOR));
		for (int i = 0; i < expected.length(); i++) {
			if (!String.valueOf(expected.charAt(i)).equals(String.valueOf(actual.charAt(i)))) {
				fail("Wrong character at position " + i + ", lines before are expected:  "
						+ String.valueOf(expected.subSequence(i - 10, i + 10)) + " but were: "
						+ String.valueOf(actual.subSequence(i - 10, i + 10)));
			}
		}
	}

}
