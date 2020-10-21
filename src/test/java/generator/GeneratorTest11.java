package generator;

import static generator.GeneratorTestUtils.dropColumn;

import java.util.Arrays;
import java.util.List;

import domain.Entity;
import domain.ForeignKey;
import testutils.AbstractXmlSupplier;

public class GeneratorTest11 extends AbstractXmlSupplier implements GeneratorTestCase {

	private static final String TABLE_NAME = "LBJ_REFERENCE";
	private static final String COLUMN_NAME = "ACTION";
	private static final String FK_NAME = "F_REF_ACTION";

	@Override
	public List<Entity> getEntities() {
		return Arrays.asList(dropColumn(COLUMN_NAME, TABLE_NAME, true, null, new ForeignKey(FK_NAME)));

	}

	@Override
	public boolean checkXsd() {
		return true;
	}

}
