package gui.forms.createtable;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import constants.NamingConventions;
import gui.LBJMockWindow;
import gui.components.LBJMockForm;
import gui.components.LBJTextBox;

public class LBJCreateTableFormPrimaryKeyUpdaterTest {

	private static final String TABLE_NAME = "TABLE_NAME";

	/**
	 * Ignoring case when testing values. Testing case updaters and validators is
	 * not the goal.
	 */
	@Test
	public void testUpdate() {
		LBJCreateTableForm form = new LBJCreateTableForm(new LBJMockWindow(), new LBJMockForm());
		form.focus();
		LBJTextBox tableName = form.getTableNameTextBox();
		LBJTextBox primaryKeyName = form.getPrimaryKeyNameTextBox();
		LBJTextBox primaryKeyConstraint = form.getPrimaryKeyConstraintNameTextBox();

		assertThat(tableName.getValue()).isBlank();
		assertThat(primaryKeyName.getValue()).isBlank();
		assertThat(primaryKeyConstraint.getValue()).isBlank();

		tableName.setValue(TABLE_NAME);
		form.update();
		// Table name is not focused so nothing should update
		assertThat(tableName.getValue()).isEqualTo(TABLE_NAME);
		assertThat(primaryKeyName.getValue()).isBlank();
		assertThat(primaryKeyConstraint.getValue()).isBlank();

		tableName.getTextBox().onEnterFocus(null, null);
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
