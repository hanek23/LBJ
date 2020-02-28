package generator;

import domain.Column;
import domain.ForeignKey;
import domain.Table;

/**
 * Supplies a table with eight column. Full changesets for table and all columns
 * should be generated as {@link #getOperation()} returns
 * {@link Operation#CREATE_TABLE}
 */
public class TestTable4 extends TestTable implements TestTableSupplier {

	@Override
	public Operation getOperation() {
		return Operation.CREATE_TABLE;
	}

	@Override
	public Table getTable() {
		Table table = new Table("NDER_RELATED_ID");
		table.setForMssql(true);
		table.setForOracle(true);
		table.setForPostgreSql(true);
		table.setSequenceName("SEQ_NDER_RELATED_ID");
		table.setPrimaryKeyColumnName("id_nder_related_id");
		table.setPrimaryKeyContrainName("P_NDER_RELATED_ID");

		table.addColumn(new Column("version_id", null, null, false, "integer"));

		table.addColumn(new Column("messageid", null, null, true, "varchar(50)"));

		table.addColumn(new Column("containersn", null, null, true, "varchar(9)"));

		table.addColumn(new Column("actionsn", null, null, true, "varchar(9)"));

		table.addColumn(new Column("relatedmessage", "I_NDER_RELATED_MESSAGE",
				new ForeignKey("F_NDER_REL_REL_MESSAGE", "NDER_MESSAGE", "id_nder_message"), true, "integer"));

		table.addColumn(new Column("relatedcontainer", "I_NDER_RELATED_CONTAINER",
				new ForeignKey("F_NDER_REL_REL_CONTAINER", "NDER_CONTAINER", "id_nder_container"), true, "integer"));

		table.addColumn(new Column("relatedaction", "I_NDER_RELATED_ACTION",
				new ForeignKey("F_NDER_REL_REL_ACTION", "NDER_ACTION", "id_nder_action"), true, "integer"));

		table.addColumn(new Column("container", "I_NDER_RELATED_ID_CONT",
				new ForeignKey("F_NDER_REL_CONTAINER", "NDER_CONTAINER", "id_nder_container"), false, "integer"));

		return table;
	}

}
