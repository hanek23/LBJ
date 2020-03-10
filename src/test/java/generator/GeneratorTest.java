package generator;

import javax.xml.transform.stream.StreamSource;

import org.junit.jupiter.api.Test;
import org.xmlunit.assertj.XmlAssert;

public class GeneratorTest {

	private static final String AUTHOR = "hanek23";
	private static final String LIQUIBASE_XSD = "/generator/liquibase-3.8.xsd";

	@Test
	public void testGenerateTestTable1() throws Exception {
		assertChangeLogEquals(new TestTable1());
	}

	@Test
	public void testGenerateTestTable2() throws Exception {
		assertChangeLogEquals(new TestTable2());
	}

	@Test
	public void testGenerateTestTable3() throws Exception {
		assertChangeLogEquals(new TestTable3());
	}

	@Test
	public void testGenerateTestTable4() throws Exception {
		assertChangeLogEquals(new TestTable4());
	}

	@Test
	public void testGenerateTestTable5() throws Exception {
		assertChangeLogEquals(new TestTable5());
	}

	private void assertChangeLogEquals(TestTableSupplier testTableSupplier) throws Exception {
		String expected = testTableSupplier.getExpectedTable();
		String actual = Generator.generate(testTableSupplier.getTable(), testTableSupplier.getOperation(), AUTHOR);
		XmlAssert.assertThat(actual).isValid();
		StreamSource liquibaseXsd = new StreamSource(GeneratorTest.class.getResourceAsStream(LIQUIBASE_XSD));
		XmlAssert.assertThat(actual).isValidAgainst(liquibaseXsd);
		XmlAssert.assertThat(actual).and(expected).normalizeWhitespace().areIdentical();
	}

}
