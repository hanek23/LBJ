package generator;

import static generator.GeneratorTestUtils.modifyDataType;

import java.util.Arrays;
import java.util.List;

import domain.Entity;
import testutils.AbstractXmlSupplier;

/**
 * Modify data type of two columns
 */
public class GeneratorTest13 extends AbstractXmlSupplier implements GeneratorTestCase {

	private static final String TABLE_NAME_1 = "SIBELIUS";
	private static final String COLUMN_NAME_1 = "jean";
	private static final String NEW_DATA_TYPE_1 = "awesomeness(9999)";

	private static final String TABLE_NAME_2 = "HAHN";
	private static final String COLUMN_NAME_2 = "hilary";
	private static final String NEW_DATA_TYPE_2 = "skill(9999)";

	@Override
	public List<Entity> getEntities() {
		return Arrays.asList(modifyDataType(COLUMN_NAME_1, TABLE_NAME_1, NEW_DATA_TYPE_1),
				modifyDataType(COLUMN_NAME_2, TABLE_NAME_2, NEW_DATA_TYPE_2));

	}

	@Override
	public boolean checkXsd() {
		return true;
	}

}
