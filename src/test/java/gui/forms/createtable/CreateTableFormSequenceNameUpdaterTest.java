package gui.forms.createtable;

import static testutils.asserts.LBJValueHolderComponentAssert.assertThat;

import org.junit.jupiter.api.Test;

import constants.NamingConventions;
import gui.components.LBJTextBox;
import testutils.LBJTestUtils;

public class CreateTableFormSequenceNameUpdaterTest {

	private static final String TABLE_NAME = "TABLE_NAME";

	@Test
	public void testUpdate() {
		CreateTableForm form = LBJTestUtils.getCreateTableForm();
		form.focus();

		LBJTextBox tableName = form.getTableNameTextBox();
		LBJTextBox sequenceName = form.getSequenceNameTextBox();

		assertThat(sequenceName).isEnabled();
		assertThat(sequenceName).isBlank();

		LBJTestUtils.focus(tableName);
		tableName.setValue(TABLE_NAME);
		form.update();

		assertThat(sequenceName).isEqualToIgnoringCase(NamingConventions.SEQUENCE_NAME_DEFAULT_VALUE + TABLE_NAME);

		tableName.setValue(TABLE_NAME + "c");
		form.update();

		assertThat(sequenceName)
				.isEqualToIgnoringCase(NamingConventions.SEQUENCE_NAME_DEFAULT_VALUE + TABLE_NAME + "c");

		LBJTestUtils.focus(sequenceName);
		sequenceName.setValue("MY SEQUENCE NAME");
		form.update();

		// Only if table name is focused the value should be updated, so that user can
		// choose whatever name he/she wishes
		assertThat(sequenceName).isEqualToIgnoringCase("MY SEQUENCE NAME");
	}

}
