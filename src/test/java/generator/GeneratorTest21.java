package generator;

import java.util.Arrays;
import java.util.List;

import domain.Column;
import domain.ColumnOperation;
import domain.DropForeignKeyConstraint;
import domain.Entity;
import domain.ForeignKey;
import testutils.AbstractXmlSupplier;

public class GeneratorTest21 extends AbstractXmlSupplier implements GeneratorTestCase {

	@Override
	public List<Entity> getEntities() {
		DropForeignKeyConstraint column = new Column(null, ColumnOperation.DROP_FOREIGN_KEY_CONSTRAINT);
		column.setTableName("NDER_ACTION");
		column.setForeignKey(new ForeignKey("F_KPOLKAT_ID_KPOLKAT", null, null));
		return Arrays.asList(column);
	}

	@Override
	public boolean checkXsd() {
		return true;
	}

}
