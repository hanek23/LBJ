package gui.forms.createtable;

import static testutils.asserts.LBJValueHolderComponentAssert.assertThat;

import org.junit.jupiter.api.Test;

import constants.NamingConventions;
import gui.components.LBJCheckBox;
import gui.components.LBJTextBox;
import testutils.LBJTestUtils;

public class CreateTableFormSequenceNameUpdaterTest {

	private static final String TABLE_NAME = "TABLE_NAME";

	@Test
	public void testUpdate() {
		CreateTableForm form = LBJTestUtils.getCreateTableForm();
		form.focus();

		LBJTextBox tableName = form.getTableNameTextBox();
		LBJCheckBox oracle = form.getOracleCheckBox();
		LBJCheckBox postgre = form.getPostgreCheckBox();
		LBJTextBox sequenceName = form.getSequenceNameTextBox();

		assertThat(sequenceName).isEnabled();
		assertThat(sequenceName).isBlank();
		assertThat(oracle).isChecked();
		assertThat(postgre).isChecked();

		LBJTestUtils.focus(tableName);
		tableName.setValue(TABLE_NAME);
		form.update();

		assertThat(sequenceName).isEqualToIgnoringCase(NamingConventions.SEQUENCE_NAME_DEFAULT_VALUE + TABLE_NAME);
		oracle.unCheck();
		form.update();

		assertThat(sequenceName).isEqualToIgnoringCase(NamingConventions.SEQUENCE_NAME_DEFAULT_VALUE + TABLE_NAME);
		postgre.unCheck();
		form.update();

		// If Oracle and Postgre are not checked sequence name should be disabled
		assertThat(sequenceName).isNotEnabled();

		oracle.check();
		form.update();
		assertThat(sequenceName).isEnabled();
	}

}
