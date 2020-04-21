package generator;

import static generator.EntitySupplierUtils.dropTable;

import java.util.Arrays;
import java.util.List;

import domain.Entity;
import testutils.AbstractXmlSupplier;

public class EntitiesSupplier7 extends AbstractXmlSupplier implements EntitiesSupplier {

	private static final String TABLE_NAME = "LBJ_REFERENCE";

	@Override
	public List<Entity> getEntities() {
		return Arrays.asList(dropTable(TABLE_NAME, null));

	}

	@Override
	public boolean checkXsd() {
		return true;
	}

}
