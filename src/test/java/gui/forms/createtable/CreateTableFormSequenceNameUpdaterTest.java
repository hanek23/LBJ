package gui.forms.createtable;

import static testutils.asserts.LBJValueHolderComponentAssert.assertThat;

import org.junit.jupiter.api.Test;

import constants.NamingConventions;
import gui.components.LBJTextBox;
import testutils.LBJTestUtils;

public class CreateTableFormSequenceNameUpdaterTest {

	private static final String SEQUENCE_NAME = "MY SEQUENCE NAME";
	private static final String TABLE_NAME = "TABLE_NAME";

	@Test
	public void testUpdate() {
		CreateTableForm form = LBJTestUtils.getCreateTableForm();
		LBJTextBox tableName = form.getTableNameTextBox();
		LBJTextBox sequenceName = form.getSequenceNameTextBox();

		assertThat(sequenceName).isEnabled();
		assertThat(sequenceName).isBlank();

		LBJTestUtils.setValueOf(tableName, TABLE_NAME);

		assertThat(sequenceName).isEqualToIgnoringCase(NamingConventions.SEQUENCE_NAME_DEFAULT_VALUE + TABLE_NAME);

		LBJTestUtils.setValueOf(tableName, TABLE_NAME + "A");

		assertThat(sequenceName)
				.isEqualToIgnoringCase(NamingConventions.SEQUENCE_NAME_DEFAULT_VALUE + TABLE_NAME + "A");

		LBJTestUtils.setValueOf(sequenceName, SEQUENCE_NAME);

		// Only if table name is focused the value should be updated, so that user can
		// choose whatever name he/she wishes
		assertThat(sequenceName).isEqualToIgnoringCase(SEQUENCE_NAME);
	}

}
