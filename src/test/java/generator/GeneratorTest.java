package generator;

import java.util.List;

import javax.xml.transform.stream.StreamSource;

import org.junit.jupiter.api.Test;
import org.xmlunit.assertj.XmlAssert;

import domain.Entity;

public class GeneratorTest {

	private static final String AUTHOR = "hanek23";
	private static final String LIQUIBASE_XSD = "/generator/liquibase-3.8.xsd";

	@Test
	public void testGenerate1() throws Exception {
		assertChangeLogEquals(new TestSupplier1());
	}

	@Test
	public void testGenerate2() throws Exception {
		assertChangeLogEquals(new TestSupplier2());
	}

	@Test
	public void testGenerate3() throws Exception {
		assertChangeLogEquals(new TestSupplier3());
	}

	@Test
	public void testGenerate4() throws Exception {
		assertChangeLogEquals(new TestSupplier4());
	}

	@Test
	public void testGenerate5() throws Exception {
		assertChangeLogEquals(new TestSupplier5());
	}

	private void assertChangeLogEquals(EntitiesSupplier testTableSupplier) throws Exception {
		String expected = testTableSupplier.getExpectedXml();
		List<Entity> entities = testTableSupplier.getEntities();
		String actual = Generator.generate(entities, AUTHOR);
		StreamSource liquibaseXsd = new StreamSource(GeneratorTest.class.getResourceAsStream(LIQUIBASE_XSD));

		XmlAssert.assertThat(actual).isValid();
		XmlAssert.assertThat(actual).isValidAgainst(liquibaseXsd);
		XmlAssert.assertThat(actual).and(expected).normalizeWhitespace().areIdentical();
	}

}
