package generator;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

import domain.Entity;
import testutils.LBJTestCase;
import testutils.LBJTestUtils;

public class GeneratorTestRunner {

	private static final String AUTHOR = "hanek23";
	private static final GeneratorSettings DEFAULT_GENERATOR_SETTINGS = new GeneratorSettings(AUTHOR, false, 1);

	@Test
	public void testGenerate1() throws Exception {
		assertChangeLogEquals(new GeneratorTest1());
	}

	@Test
	public void testGenerate2() throws Exception {
		assertChangeLogEquals(new GeneratorTest2());
	}

	@Test
	public void testGenerate3() throws Exception {
		assertChangeLogEquals(new GeneratorTest3());
	}

	@Test
	public void testGenerate4() throws Exception {
		assertChangeLogEquals(new GeneratorTest4());
	}

	@Test
	public void testGenerate5() throws Exception {
		assertChangeLogEquals(new GeneratorTest5());
	}

	@Test
	public void testGenerate6() throws Exception {
		assertChangeLogEquals(new GeneratorTest6());
	}

	@Test
	public void testGenerate7() throws Exception {
		assertChangeLogEquals(new GeneratorTest7());
	}

	@Test
	public void testGenerate8() throws Exception {
		assertChangeLogEquals(new GeneratorTest8());
	}

	@Test
	public void testGenerate9() throws Exception {
		assertChangeLogEquals(new GeneratorTest9());
	}

	@Test
	public void testGenerate10() throws Exception {
		assertChangeLogEquals(new GeneratorTest10());
	}

	@Test
	public void testGenerate11() throws Exception {
		assertChangeLogEquals(new GeneratorTest11());
	}

	@Test
	public void testGenerate12() throws Exception {
		assertChangeLogEquals(new GeneratorTest12());
	}

	private void assertChangeLogEquals(GeneratorTestCase testTableSupplier) throws Exception {
		String expected = testTableSupplier.getExpectedXml();
		List<Entity> entities = testTableSupplier.getEntities();
		String actual = Generator.generate(entities, DEFAULT_GENERATOR_SETTINGS);
		// used for replacement of names
		assertThat(actual).doesNotContain("$");
		LBJTestUtils.assertLiquibaseXmlEquals(expected, actual, testTableSupplier.checkXsd());
	}

}
