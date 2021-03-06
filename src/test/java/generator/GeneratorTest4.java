package generator;

import static generator.GeneratorTestUtils.addColumn;
import static generator.GeneratorTestUtils.createTable;

import java.util.Arrays;
import java.util.List;

import domain.Entity;
import domain.ForeignKey;
import testutils.AbstractXmlSupplier;

public class GeneratorTest4 extends AbstractXmlSupplier implements GeneratorTestCase {

	private static final String TABLE_NAME = "LBJ_RELATED_ID";

	@Override
	public List<Entity> getEntities() {
		return Arrays.asList(createTable(TABLE_NAME, "id_lbj_related_id", "P_LBJ_RELATED_ID", "SEQ_LBJ_RELATED_ID"),
				addColumn("version_id", TABLE_NAME, null, null, false, "integer", "0"),

				addColumn("messageid", TABLE_NAME, null, null, true, "varchar(50)", null),

				addColumn("containersn", TABLE_NAME, null, null, true, "varchar(9)", null),

				addColumn("actionsn", TABLE_NAME, null, null, true, "varchar(9)", null),

				addColumn("relatedmessage", TABLE_NAME, "I_LBJ_RELATED_MESSAGE",
						new ForeignKey("F_LBJ_REL_REL_MESSAGE", "LBJ_MESSAGE", "id_lbj_message"), true, "integer", null),

				addColumn("relatedcontainer", TABLE_NAME, "I_LBJ_RELATED_CONTAINER",
						new ForeignKey("F_LBJ_REL_REL_CONTAINER", "LBJ_CONTAINER", "id_lbj_container"), true,
						"integer", null),

				addColumn("relatedaction", TABLE_NAME, "I_LBJ_RELATED_ACTION",
						new ForeignKey("F_LBJ_REL_REL_ACTION", "LBJ_ACTION", "id_lbj_action"), true, "integer", null),

				addColumn("container", TABLE_NAME, "I_LBJ_RELATED_ID_CONT",
						new ForeignKey("F_LBJ_REL_CONTAINER", "LBJ_CONTAINER", "id_lbj_container"), false, "integer", null));
	}

	@Override
	public boolean checkXsd() {
		return true;
	}

}
