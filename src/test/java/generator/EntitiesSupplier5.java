package generator;

import static generator.EntitySupplierUtils.createColumn;

import java.util.Arrays;
import java.util.List;

import domain.ColumnOperation;
import domain.Entity;

public class EntitiesSupplier5 extends AbstractEntitiesSupplier {

	private static final String TABLE_NAME = "NDER_REFERENCE";

	@Override
	public List<Entity> getEntities() {
		return Arrays.asList(
				createColumn("version_id", ColumnOperation.ADD_COLUMN, TABLE_NAME, null, null, true, "integer"),
				createColumn("isAlive", ColumnOperation.ADD_COLUMN, TABLE_NAME, null, null, false, "boolean"));
	}

}
