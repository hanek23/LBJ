package generator;

import java.util.List;

import javax.xml.transform.stream.StreamSource;

import org.junit.jupiter.api.Test;
import org.xmlunit.assertj.XmlAssert;

import domain.Entity;

public class GeneratorTest {

	private static final String AUTHOR = "hanek23";
	private static final String LIQUIBASE_XSD = "/generator/liquibase-3.8.xsd";
	private static final GeneratorSettings DEFAULT_GENERATOR_SETTINGS = new GeneratorSettings(AUTHOR, false, 1);

	@Test
	public void testGenerate1() throws Exception {
		assertChangeLogEquals(new EntitiesSupplier1());
	}

	@Test
	public void testGenerate2() throws Exception {
		assertChangeLogEquals(new EntitiesSupplier2());
	}

	@Test
	public void testGenerate3() throws Exception {
		assertChangeLogEquals(new EntitiesSupplier3());
	}

	@Test
	public void testGenerate4() throws Exception {
		assertChangeLogEquals(new EntitiesSupplier4());
	}

	@Test
	public void testGenerate5() throws Exception {
		assertChangeLogEquals(new EntitiesSupplier5());
	}

	private void assertChangeLogEquals(EntitiesSupplier testTableSupplier) throws Exception {
		String expected = testTableSupplier.getExpectedXml();
		List<Entity> entities = testTableSupplier.getEntities();
		String actual = Generator.generate(entities, DEFAULT_GENERATOR_SETTINGS);
		StreamSource liquibaseXsd = new StreamSource(GeneratorTest.class.getResourceAsStream(LIQUIBASE_XSD));

		XmlAssert.assertThat(actual).isValid();
		XmlAssert.assertThat(actual).isValidAgainst(liquibaseXsd);
		XmlAssert.assertThat(actual).and(expected).normalizeWhitespace().areIdentical();
	}

}
