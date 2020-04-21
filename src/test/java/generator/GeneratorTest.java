package generator;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

import domain.Entity;
import testutils.LBJTestUtils;

public class GeneratorTest {

	private static final String AUTHOR = "hanek23";
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

	@Test
	public void testGenerate6() throws Exception {
		assertChangeLogEquals(new EntitiesSupplier6());
	}

	@Test
	public void testGenerate7() throws Exception {
		assertChangeLogEquals(new EntitiesSupplier7());
	}

	@Test
	public void testGenerate8() throws Exception {
		assertChangeLogEquals(new EntitiesSupplier8());
	}

	@Test
	public void testGenerate9() throws Exception {
		assertChangeLogEquals(new EntitiesSupplier9());
	}

	@Test
	public void testGenerate10() throws Exception {
		assertChangeLogEquals(new EntitiesSupplier10());
	}

	@Test
	public void testGenerate11() throws Exception {
		assertChangeLogEquals(new EntitiesSupplier11());
	}

	@Test
	public void testGenerate12() throws Exception {
		assertChangeLogEquals(new EntitiesSupplier12());
	}

	private void assertChangeLogEquals(EntitiesSupplier testTableSupplier) throws Exception {
		String expected = testTableSupplier.getExpectedXml();
		List<Entity> entities = testTableSupplier.getEntities();
		String actual = Generator.generate(entities, DEFAULT_GENERATOR_SETTINGS);
		// used for replacement of names
		assertThat(actual).doesNotContain("$");
		LBJTestUtils.assertLiquibaseXmlEquals(expected, actual, testTableSupplier.checkXsd());
	}

}
