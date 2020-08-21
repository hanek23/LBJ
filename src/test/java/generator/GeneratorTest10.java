package generator;

import static generator.GeneratorTestUtils.dropColumn;

import java.util.Arrays;
import java.util.List;

import domain.Entity;
import testutils.AbstractXmlSupplier;

public class GeneratorTest10 extends AbstractXmlSupplier implements GeneratorTestCase {

	private static final String TABLE_NAME = "LBJ_REFERENCE";
	private static final String COLUMN_NAME = "ACTION";

	@Override
	public List<Entity> getEntities() {
		return Arrays.asList(dropColumn(COLUMN_NAME, TABLE_NAME, null, null));

	}

	@Override
	public boolean checkXsd() {
		return true;
	}

}
