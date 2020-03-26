package generator;

import static generator.EntitySupplierUtils.createColumn;
import static generator.EntitySupplierUtils.createTable;

import java.util.Arrays;
import java.util.List;

import domain.ColumnOperation;
import domain.Entity;
import domain.ForeignKey;

public class EntitiesSupplier4 extends AbstractEntitiesSupplier {

	private static final String TABLE_NAME = "NDER_RELATED_ID";

	@Override
	public List<Entity> getEntities() {
		return Arrays.asList(createTable(TABLE_NAME, "id_nder_related_id", "P_NDER_RELATED_ID", "SEQ_NDER_RELATED_ID"),
				createColumn("version_id", ColumnOperation.ADD_COLUMN, TABLE_NAME, null, null, false, "integer"),

				createColumn("messageid", ColumnOperation.ADD_COLUMN, TABLE_NAME, null, null, true, "varchar(50)"),

				createColumn("containersn", ColumnOperation.ADD_COLUMN, TABLE_NAME, null, null, true, "varchar(9)"),

				createColumn("actionsn", ColumnOperation.ADD_COLUMN, TABLE_NAME, null, null, true, "varchar(9)"),

				createColumn("relatedmessage", ColumnOperation.ADD_COLUMN, TABLE_NAME, "I_NDER_RELATED_MESSAGE",
						new ForeignKey("F_NDER_REL_REL_MESSAGE", "NDER_MESSAGE", "id_nder_message"), true, "integer"),

				createColumn("relatedcontainer", ColumnOperation.ADD_COLUMN, TABLE_NAME, "I_NDER_RELATED_CONTAINER",
						new ForeignKey("F_NDER_REL_REL_CONTAINER", "NDER_CONTAINER", "id_nder_container"), true,
						"integer"),

				createColumn("relatedaction", ColumnOperation.ADD_COLUMN, TABLE_NAME, "I_NDER_RELATED_ACTION",
						new ForeignKey("F_NDER_REL_REL_ACTION", "NDER_ACTION", "id_nder_action"), true, "integer"),

				createColumn("container", ColumnOperation.ADD_COLUMN, TABLE_NAME, "I_NDER_RELATED_ID_CONT",
						new ForeignKey("F_NDER_REL_CONTAINER", "NDER_CONTAINER", "id_nder_container"), false,
						"integer"));
	}

}
