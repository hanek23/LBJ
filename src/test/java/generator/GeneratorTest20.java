package generator;

import java.util.Arrays;
import java.util.List;

import domain.AddForeignKeyConstraint;
import domain.Column;
import domain.ColumnOperation;
import domain.Entity;
import domain.ForeignKey;
import testutils.AbstractXmlSupplier;

public class GeneratorTest20 extends AbstractXmlSupplier implements GeneratorTestCase {

	@Override
	public List<Entity> getEntities() {
		AddForeignKeyConstraint column = new Column("catalogueitem", ColumnOperation.ADD_FOREIGN_KEY_CONSTRAINT);
		column.setTableName("NDER_ACTION");
		column.setForeignKey(new ForeignKey("F_NDER_ACTION_KPOLKAT_CATALOGU", "KPOLKAT", "id_kpolkat"));
		return Arrays.asList(column);
	}

	@Override
	public boolean checkXsd() {
		return true;
	}

}
