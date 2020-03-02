package generator;

import static org.junit.Assert.fail;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

public class GeneratorTest {

	private static final String AUTHOR = "hanek23";

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

	@Test
	public void testGenerateTestTable5() {
		assertChangeLogEquals(new TestTable5());
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
