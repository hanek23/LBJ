package gui.forms.addcolumn;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import constants.NamingConventions;
import gui.components.LBJCheckBox;
import gui.components.LBJTextBox;
import testutils.LBJTestUtils;

public class LBJAddColumnIndexNameUpdaterTest {

	private static final String TABLE_NAME = "TABLE_NAME";
	private static final String COLUMN_NAME = "COLUMN";
	private static final String NEW_INDEX_NAME = "INDEX_NAME";

	@Test
	public void testUpdate() {
		LBJAddColumnForm form = LBJTestUtils.getAddColumnForm();
		form.focus();

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

		LBJTestUtils.focus(tableName);
		tableName.setValue(TABLE_NAME);
		form.update();

		assertThat(tableName.getValue()).isEqualToIgnoringCase(TABLE_NAME);
		// Everything else should have stay the same
		assertThat(columnName.getValue()).isBlank();
		assertFalse(indexCheckBox.isChecked());
		assertFalse(indexName.isEnabled());

		LBJTestUtils.focus(columnName);
		columnName.setValue(COLUMN_NAME);
		form.update();

		assertThat(tableName.getValue()).isEqualToIgnoringCase(TABLE_NAME);
		assertThat(columnName.getValue()).isEqualToIgnoringCase(COLUMN_NAME);
		// Everything else should have stay the same
		assertFalse(indexCheckBox.isChecked());
		assertFalse(indexName.isEnabled());

		LBJTestUtils.focus(indexCheckBox);
		indexCheckBox.check();
		form.update();

		// Index name should be automatically filled
		assertThat(tableName.getValue()).isEqualToIgnoringCase(TABLE_NAME);
		assertThat(columnName.getValue()).isEqualToIgnoringCase(COLUMN_NAME);
		assertTrue(indexCheckBox.isChecked());
		assertTrue(indexName.isEnabled());
		assertThat(indexName.getValue()).isEqualToIgnoringCase(
				NamingConventions.INDEX_NAME_DEFAULT_VALUE + TABLE_NAME + NamingConventions.SEPARATOR + COLUMN_NAME);

		LBJTestUtils.focus(indexName);
		indexName.setValue(NEW_INDEX_NAME);
		form.update();

		// if index name is focused, updater should not interfere and let the user name
		// it however he wants
		assertThat(indexName.getValue()).isEqualToIgnoringCase(NEW_INDEX_NAME);

		LBJTestUtils.focus(indexCheckBox);
		indexCheckBox.unCheck();
		form.update();

		assertThat(tableName.getValue()).isEqualToIgnoringCase(TABLE_NAME);
		assertThat(columnName.getValue()).isEqualToIgnoringCase(COLUMN_NAME);
		assertFalse(indexCheckBox.isChecked());
		assertFalse(indexName.isEnabled());
	}

}
