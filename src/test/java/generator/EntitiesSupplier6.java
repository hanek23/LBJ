package generator;

import static generator.EntitySupplierUtils.dropTable;

import java.util.Arrays;
import java.util.List;

import domain.Entity;
import testutils.AbstractXmlSupplier;

public class EntitiesSupplier6 extends AbstractXmlSupplier implements EntitiesSupplier {

	private static final String TABLE_NAME = "LBJ_REFERENCE";
	private static final String SEQUENCE_NAME = "SEQ_LBJ_REFERENCE";

	@Override
	public List<Entity> getEntities() {
		return Arrays.asList(dropTable(TABLE_NAME, SEQUENCE_NAME));

	}

	@Override
	public boolean checkXsd() {
		return true;
	}

}
