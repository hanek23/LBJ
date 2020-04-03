package gui.updaters.shared;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static testutils.asserts.LBJValueHolderComponentAssert.assertThat;

import org.junit.jupiter.api.Test;

import constants.NamingConventions;
import gui.components.LBJCheckBox;
import gui.components.LBJTextBox;
import gui.forms.addcolumn.AddColumnForm;
import gui.forms.createtable.CreateTableForm;
import testutils.LBJTestUtils;

/**
 * Ignoring case when testing values. Testing case updaters and validators is
 * not the goal.
 */
public class LBJNamingConventionUpdaterTest {

	private static final String TABLE_NAME = "TABLE_NAME";
	private static final String COLUMN_NAME = "COLUMN";
	private static final String REFERENCED_TABLE_NAME = "REF_TABLE";
	private static final String REFERENCED_COLUMN_NAME = "REF_COLUMN";
	private static final String SEQUENCE_NAME = "MY SEQUENCE NAME";
	private static final String NEW_INDEX_NAME = "INDEX_NAME";

	private static final String INDEX_NAME_START = "I_";
	private static final String FOREIGN_KEY_START = "F_";
	private static final String PRIMARY_KEY_START = "id_";
	private static final String PRIMARY_KEY_CONSTRAINT_START = "P_";
	private static final String SEQUENCE_START = "SEQ_";

	@Test
	public void testUpdateAddColumnForeignKey() {
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
		assertThat(foreignKeyName.getValue()).isBlank();

		LBJTestUtils.setValueOf(referencedTable, REFERENCED_TABLE_NAME);

		// After filling referenced table name, updater should update foreign key name
		// accordingly
		assertThat(referencedTable.getValue()).isEqualToIgnoringCase(REFERENCED_TABLE_NAME);
		assertThat(foreignKeyName.getValue())
				.isEqualToIgnoringCase(FOREIGN_KEY_START + REFERENCED_TABLE_NAME + NamingConventions.SEPARATOR);

		LBJTestUtils.setValueOf(referencedColumn, REFERENCED_COLUMN_NAME);

		// After filling referenced column name, updater should update foreign key name
		// accordingly
		assertThat(referencedColumn.getValue()).isEqualToIgnoringCase(REFERENCED_COLUMN_NAME);
		assertThat(foreignKeyName.getValue()).isEqualToIgnoringCase(
				FOREIGN_KEY_START + REFERENCED_TABLE_NAME + NamingConventions.SEPARATOR + REFERENCED_COLUMN_NAME);

		LBJTestUtils.setValueOf(foreignKeyCheckBox, false);

		// After unchecking fk check box everything should be as it was at the start.
		assertFalse(foreignKeyCheckBox.isChecked());
		assertTrue(foreignKeyCheckBox.isEnabled());
		assertFalse(referencedTable.isEnabled());
		assertFalse(referencedColumn.isEnabled());
		assertFalse(foreignKeyName.isEnabled());
	}

	@Test
	public void testUpdateAddColumnIndexName() {
		AddColumnForm form = LBJTestUtils.getAddColumnForm();
		LBJCheckBox indexCheckBox = form.getIndexCheckBox();
		LBJTextBox indexName = form.getIndexNameTextBox();
		LBJTextBox tableName = form.getTableNameTextBox();
		LBJTextBox columnName = form.getColumnNameTextBox();

		// At the beggining table and column name are enabled but blank, index check box
		// is onchecked and index name is disabled.
		assertThat(tableName.getValue()).isBlank();
		assertThat(columnName.getValue()).isBlank();
		assertFalse(indexCheckBox.isChecked());
		assertFalse(indexName.isEnabled());

		LBJTestUtils.setValueOf(tableName, TABLE_NAME);

		assertThat(tableName.getValue()).isEqualToIgnoringCase(TABLE_NAME);
		// Everything else should have stay the same
		assertThat(columnName.getValue()).isBlank();
		assertFalse(indexCheckBox.isChecked());
		assertFalse(indexName.isEnabled());

		LBJTestUtils.setValueOf(columnName, COLUMN_NAME);

		assertThat(tableName.getValue()).isEqualToIgnoringCase(TABLE_NAME);
		assertThat(columnName.getValue()).isEqualToIgnoringCase(COLUMN_NAME);
		// Everything else should have stay the same
		assertFalse(indexCheckBox.isChecked());
		assertFalse(indexName.isEnabled());

		LBJTestUtils.setValueOf(indexCheckBox, true);

		// Index name should be automatically filled
		assertThat(tableName.getValue()).isEqualToIgnoringCase(TABLE_NAME);
		assertThat(columnName.getValue()).isEqualToIgnoringCase(COLUMN_NAME);
		assertTrue(indexCheckBox.isChecked());
		assertTrue(indexName.isEnabled());
		assertThat(indexName.getValue())
				.isEqualToIgnoringCase(INDEX_NAME_START + TABLE_NAME + NamingConventions.SEPARATOR + COLUMN_NAME);

		LBJTestUtils.setValueOf(indexName, NEW_INDEX_NAME);

		// if index name is focused, updater should not interfere and let the user name
		// it however he wants
		assertThat(indexName.getValue()).isEqualToIgnoringCase(NEW_INDEX_NAME);

		LBJTestUtils.focus(indexCheckBox);
		LBJTestUtils.setValueOf(indexCheckBox, false);

		assertThat(tableName.getValue()).isEqualToIgnoringCase(TABLE_NAME);
		assertThat(columnName.getValue()).isEqualToIgnoringCase(COLUMN_NAME);
		assertFalse(indexCheckBox.isChecked());
		assertFalse(indexName.isEnabled());
	}

	@Test
	public void testUpdateCreateTablePrimaryKey() {
		CreateTableForm form = LBJTestUtils.getCreateTableForm();
		LBJTextBox tableName = form.getTableNameTextBox();
		LBJTextBox primaryKeyName = form.getPrimaryKeyNameTextBox();
		LBJTextBox primaryKeyConstraint = form.getPrimaryKeyConstraintNameTextBox();

		assertThat(tableName).isBlank();
		assertThat(primaryKeyName).isBlank();
		assertThat(primaryKeyConstraint).isBlank();

		LBJTestUtils.setValueOf(tableName, TABLE_NAME);

		assertThat(tableName).hasValueEqualTo(TABLE_NAME);
		assertThat(primaryKeyName).isEqualToIgnoringCase(PRIMARY_KEY_START + TABLE_NAME);
		assertThat(primaryKeyConstraint).isEqualToIgnoringCase(PRIMARY_KEY_CONSTRAINT_START + TABLE_NAME);

		LBJTestUtils.setValueOf(tableName, TABLE_NAME + "A");

		assertThat(tableName).hasValueEqualTo(TABLE_NAME + "A");
		assertThat(primaryKeyName).isEqualToIgnoringCase(PRIMARY_KEY_START + TABLE_NAME + "A");
		assertThat(primaryKeyConstraint).isEqualToIgnoringCase(PRIMARY_KEY_CONSTRAINT_START + TABLE_NAME + "A");
	}

	@Test
	public void testUpdateCreateTableSequence() {
		CreateTableForm form = LBJTestUtils.getCreateTableForm();
		LBJTextBox tableName = form.getTableNameTextBox();
		LBJTextBox sequenceName = form.getSequenceNameTextBox();

		assertThat(sequenceName).isEnabled();
		assertThat(sequenceName).isBlank();

		LBJTestUtils.setValueOf(tableName, TABLE_NAME);

		assertThat(sequenceName).isEqualToIgnoringCase(SEQUENCE_START + TABLE_NAME);

		LBJTestUtils.setValueOf(tableName, TABLE_NAME + "A");

		assertThat(sequenceName).isEqualToIgnoringCase(SEQUENCE_START + TABLE_NAME + "A");

		LBJTestUtils.setValueOf(sequenceName, SEQUENCE_NAME);

		// Only if table name is focused the value should be updated, so that user can
		// choose whatever name he/she wishes
		assertThat(sequenceName).isEqualToIgnoringCase(SEQUENCE_NAME);
	}

}
