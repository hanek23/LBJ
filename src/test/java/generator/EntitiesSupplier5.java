package generator;

import static generator.EntitySupplierUtils.createColumn;

import java.util.Arrays;
import java.util.List;

import domain.ColumnOperation;
import domain.Entity;
import testutils.AbstractXmlSupplier;

public class EntitiesSupplier5 extends AbstractXmlSupplier implements EntitiesSupplier {

	private static final String TABLE_NAME = "LBJ_REFERENCE";

	@Override
	public List<Entity> getEntities() {
		return Arrays.asList(
				createColumn("version_id", ColumnOperation.ADD_COLUMN, TABLE_NAME, null, null, true, "integer"),
				createColumn("isAlive", ColumnOperation.ADD_COLUMN, TABLE_NAME, null, null, false, "boolean"));
	}

	@Override
	public boolean checkXsd() {
		return true;
	}

}
