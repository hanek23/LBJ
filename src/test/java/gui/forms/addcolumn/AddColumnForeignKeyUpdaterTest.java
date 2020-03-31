package gui.forms.addcolumn;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import constants.NamingConventions;
import gui.components.LBJCheckBox;
import gui.components.LBJTextBox;
import testutils.LBJTestUtils;

public class AddColumnForeignKeyUpdaterTest {

	private static final String REFERENCED_TABLE_NAME = "REF_TABLE";
	private static final String REFERENCED_COLUMN_NAME = "REF_COLUMN";

	@Test
	public void testUpdate() {
		AddColumnForm form = LBJTestUtils.getAddColumnForm();
		LBJCheckBox foreignKeyCheckBox = form.getForeignKeyCheckBox();
		LBJTextBox referencedTable = form.getReferencedTableNameTextBox();
		LBJTextBox referencedColumn = form.getReferencedColumnNameTextBox();
		LBJTextBox foreignKeyName = form.getForeignKeyNameTextBox();

		// At the beggining fk text boxes should be disabled and fk check box should be
		// unchecked but enabled.
		assertFalse(foreignKeyCheckBox.isChecked());
		assertTrue(foreignKeyCheckBox.isEnabled());
		assertFalse(referencedTable.isEnabled());
		assertFalse(referencedColumn.isEnabled());
		assertFalse(foreignKeyName.isEnabled());

		LBJTestUtils.setValueOf(foreignKeyCheckBox, true);

		// After checking fk checkbox, all fk text boxes should be enabled but blank.
		// Only fk name should be ready for updates and have default value
		assertTrue(foreignKeyCheckBox.isChecked());
		assertTrue(foreignKeyCheckBox.isEnabled());
		assertTrue(referencedTable.isEnabled());
		assertTrue(referencedColumn.isEnabled());
		assertTrue(foreignKeyName.isEnabled());
		assertThat(referencedTable.getValue()).isBlank();
		assertThat(referencedColumn.getValue()).isBlank();
		assertThat(foreignKeyName.getValue())
				.isEqualToIgnoringCase(NamingConventions.FOREIGN_KEY_NAME + NamingConventions.SEPARATOR);

		LBJTestUtils.setValueOf(referencedTable, REFERENCED_TABLE_NAME);

		// After filling referenced table name, updater should update foreign key name
		// accordingly
		assertThat(referencedTable.getValue()).isEqualToIgnoringCase(REFERENCED_TABLE_NAME);
		assertThat(foreignKeyName.getValue()).isEqualToIgnoringCase(
				NamingConventions.FOREIGN_KEY_NAME + REFERENCED_TABLE_NAME + NamingConventions.SEPARATOR);

		LBJTestUtils.setValueOf(referencedColumn, REFERENCED_COLUMN_NAME);

		// After filling referenced column name, updater should update foreign key name
		// accordingly
		assertThat(referencedColumn.getValue()).isEqualToIgnoringCase(REFERENCED_COLUMN_NAME);
		assertThat(foreignKeyName.getValue()).isEqualToIgnoringCase(NamingConventions.FOREIGN_KEY_NAME
				+ REFERENCED_TABLE_NAME + NamingConventions.SEPARATOR + REFERENCED_COLUMN_NAME);

		LBJTestUtils.setValueOf(foreignKeyCheckBox, false);

		// After unchecking fk check box everything should be as it was at the start.
		assertFalse(foreignKeyCheckBox.isChecked());
		assertTrue(foreignKeyCheckBox.isEnabled());
		assertFalse(referencedTable.isEnabled());
		assertFalse(referencedColumn.isEnabled());
		assertFalse(foreignKeyName.isEnabled());
	}

}
