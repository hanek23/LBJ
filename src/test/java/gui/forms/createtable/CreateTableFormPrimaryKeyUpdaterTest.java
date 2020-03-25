package gui.forms.createtable;

import static testutils.asserts.LBJValueHolderComponentAssert.assertThat;

import org.junit.jupiter.api.Test;

import constants.NamingConventions;
import gui.components.LBJTextBox;
import testutils.LBJTestUtils;

public class CreateTableFormPrimaryKeyUpdaterTest {

	private static final String TABLE_NAME = "TABLE_NAME";

	/**
	 * Ignoring case when testing values. Testing case updaters and validators is
	 * not the goal.
	 */
	@Test
	public void testUpdate() {
		CreateTableForm form = LBJTestUtils.getCreateTableForm();
		form.focus();
		LBJTextBox tableName = form.getTableNameTextBox();
		LBJTextBox primaryKeyName = form.getPrimaryKeyNameTextBox();
		LBJTextBox primaryKeyConstraint = form.getPrimaryKeyConstraintNameTextBox();

		assertThat(tableName).isBlank();
		assertThat(primaryKeyName).isBlank();
		assertThat(primaryKeyConstraint).isBlank();

		LBJTestUtils.focus(tableName);
		tableName.setValue(TABLE_NAME);
		form.update();

		assertThat(tableName).isValueEqualTo(TABLE_NAME);
		assertThat(primaryKeyName).isEqualToIgnoringCase(NamingConventions.PRIMARY_KEY_NAME_DEFAULT_VALUE + TABLE_NAME);
		assertThat(primaryKeyConstraint)
				.isEqualToIgnoringCase(NamingConventions.PRIMARY_KEY_CONSTRAINT_DEFAULT_VALUE + TABLE_NAME);

		tableName.setValue(TABLE_NAME + "A");
		form.update();

		assertThat(tableName).isValueEqualTo(TABLE_NAME + "A");
		assertThat(primaryKeyName)
				.isEqualToIgnoringCase(NamingConventions.PRIMARY_KEY_NAME_DEFAULT_VALUE + TABLE_NAME + "A");
		assertThat(primaryKeyConstraint)
				.isEqualToIgnoringCase(NamingConventions.PRIMARY_KEY_CONSTRAINT_DEFAULT_VALUE + TABLE_NAME + "A");
	}

}
