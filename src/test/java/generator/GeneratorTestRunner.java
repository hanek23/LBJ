package generator;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

import constants.XmlPartsSupplier;
import domain.Entity;
import testutils.LBJTestUtils;

public class GeneratorTestRunner {

	private static final String AUTHOR = "hanek23";
	private static final GeneratorSettings DEFAULT_GENERATOR_SETTINGS = new GeneratorSettings(AUTHOR, false, 1);

	@Test
	void testGenerate1() throws Exception {
		assertChangeLogEquals(new GeneratorTest1());
	}

	@Test
	void testGenerate2() throws Exception {
		assertChangeLogEquals(new GeneratorTest2());
	}

	@Test
	void testGenerate3() throws Exception {
		assertChangeLogEquals(new GeneratorTest3());
	}

	@Test
	void testGenerate4() throws Exception {
		assertChangeLogEquals(new GeneratorTest4());
	}

	@Test
	void testGenerate5() throws Exception {
		assertChangeLogEquals(new GeneratorTest5());
	}

	@Test
	void testGenerate6() throws Exception {
		assertChangeLogEquals(new GeneratorTest6());
	}

	@Test
	void testGenerate7() throws Exception {
		assertChangeLogEquals(new GeneratorTest7());
	}

	@Test
	void testGenerate8() throws Exception {
		assertChangeLogEquals(new GeneratorTest8());
	}

	@Test
	void testGenerate9() throws Exception {
		assertChangeLogEquals(new GeneratorTest9());
	}

	@Test
	void testGenerate10() throws Exception {
		assertChangeLogEquals(new GeneratorTest10());
	}

	@Test
	void testGenerate11() throws Exception {
		assertChangeLogEquals(new GeneratorTest11());
	}

	@Test
	void testGenerate12() throws Exception {
		assertChangeLogEquals(new GeneratorTest12());
	}

	@Test
	void testGenerate13() throws Exception {
		assertChangeLogEquals(new GeneratorTest13());
	}

	@Test
	void testGenerate14() throws Exception {
		assertChangeLogEquals(new GeneratorTest14());
	}

	@Test
	void testGenerate15() throws Exception {
		assertChangeLogEquals(new GeneratorTest15());
	}

	@Test
	void testGenerate16() throws Exception {
		assertChangeLogEquals(new GeneratorTest16());
	}

	@Test
	void testGenerate17() throws Exception {
		assertChangeLogEquals(new GeneratorTest17());
	}

	@Test
	void testGenerate18() throws Exception {
		assertChangeLogEquals(new GeneratorTest18());
	}

	@Test
	void testGenerate19() throws Exception {
		assertChangeLogEquals(new GeneratorTest19());
	}
	
	@Test
	void testGenerate20() throws Exception {
		assertChangeLogEquals(new GeneratorTest20());
	}
	
	@Test
	void testGenerate21() throws Exception {
		assertChangeLogEquals(new GeneratorTest21());
	}

	private void assertChangeLogEquals(GeneratorTestCase testTableSupplier) throws Exception {
		String expected = testTableSupplier.getExpectedXml();
		expected = XmlPartsSupplier.replaceVersion(expected);
		List<Entity> entities = testTableSupplier.getEntities();
		String actual = Generator.generate(entities, DEFAULT_GENERATOR_SETTINGS);
		// used for replacement of names
		assertThat(actual).doesNotContain("$");
		LBJTestUtils.assertLiquibaseXmlEquals(expected, actual, testTableSupplier.checkXsd());
	}

}
