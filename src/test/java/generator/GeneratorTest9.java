package generator;

import static generator.GeneratorTestUtils.dropColumn;

import java.util.Arrays;
import java.util.List;

import domain.Entity;
import domain.ForeignKey;
import testutils.AbstractXmlSupplier;

public class GeneratorTest9 extends AbstractXmlSupplier implements GeneratorTestCase {

	private static final String TABLE_NAME_1 = "LBJ_REFERENCE";
	private static final String COLUMN_NAME_1 = "ACTION";
	private static final String INDEX_NAME_1 = "I_ACTION";
	private static final String FK_NAME_1 = "F_REF_ACTION";

	private static final String TABLE_NAME_2 = "LBJ_KEY";
	private static final String COLUMN_NAME_2 = "ESC";
	private static final String INDEX_NAME_2 = "I_ESC";
	private static final String FK_NAME_2 = "F_KEY_ESC";

	@Override
	public List<Entity> getEntities() {
		return Arrays.asList(dropColumn(COLUMN_NAME_1, TABLE_NAME_1, true, INDEX_NAME_1, new ForeignKey(FK_NAME_1)),
				dropColumn(COLUMN_NAME_2, TABLE_NAME_2, false, INDEX_NAME_2, new ForeignKey(FK_NAME_2)));

	}

	@Override
	public boolean checkXsd() {
		return true;
	}

}
