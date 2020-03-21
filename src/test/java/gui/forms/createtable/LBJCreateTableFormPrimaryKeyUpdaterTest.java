package gui.forms.createtable;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import constants.NamingConventions;
import gui.components.LBJTextBox;
import testutils.LBJTestUtils;

public class LBJCreateTableFormPrimaryKeyUpdaterTest {

	private static final String TABLE_NAME = "TABLE_NAME";

	/**
	 * Ignoring case when testing values. Testing case updaters and validators is
	 * not the goal.
	 */
	@Test
	public void testUpdate() {
		LBJCreateTableForm form = LBJTestUtils.getCreateTableForm();
		form.focus();
		LBJTextBox tableName = form.getTableNameTextBox();
		LBJTextBox primaryKeyName = form.getPrimaryKeyNameTextBox();
		LBJTextBox primaryKeyConstraint = form.getPrimaryKeyConstraintNameTextBox();

		assertThat(tableName.getValue()).isBlank();
		assertThat(primaryKeyName.getValue()).isBlank();
		assertThat(primaryKeyConstraint.getValue()).isBlank();

		LBJTestUtils.focus(tableName);
		tableName.setValue(TABLE_NAME);
		form.update();

		assertThat(tableName.getValue()).isEqualTo(TABLE_NAME);
		assertThat(primaryKeyName.getValue())
				.isEqualToIgnoringCase(NamingConventions.PRIMARY_KEY_NAME_DEFAULT_VALUE + TABLE_NAME);
		assertThat(primaryKeyConstraint.getValue())
				.isEqualToIgnoringCase(NamingConventions.PRIMARY_KEY_CONSTRAINT_DEFAULT_VALUE + TABLE_NAME);

		tableName.setValue(TABLE_NAME + "A");
		form.update();

		assertThat(tableName.getValue()).isEqualTo(TABLE_NAME + "A");
		assertThat(primaryKeyName.getValue())
				.isEqualToIgnoringCase(NamingConventions.PRIMARY_KEY_NAME_DEFAULT_VALUE + TABLE_NAME + "A");
		assertThat(primaryKeyConstraint.getValue())
				.isEqualToIgnoringCase(NamingConventions.PRIMARY_KEY_CONSTRAINT_DEFAULT_VALUE + TABLE_NAME + "A");
	}

}
