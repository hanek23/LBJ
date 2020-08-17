package generator;

import static generator.EntitySupplierUtils.addColumn;

import java.util.Arrays;
import java.util.List;

import domain.Entity;
import testutils.AbstractXmlSupplier;

public class EntitiesSupplier5 extends AbstractXmlSupplier implements EntitiesSupplier {

	private static final String TABLE_NAME = "LBJ_REFERENCE";

	@Override
	public List<Entity> getEntities() {
		return Arrays.asList(addColumn("version_id", TABLE_NAME, null, null, true, "integer", "0"),
				addColumn("isAlive", TABLE_NAME, null, null, false, "boolean", "false"));
	}

	@Override
	public boolean checkXsd() {
		return true;
	}

}
