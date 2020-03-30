package gui.forms.addcolumn;

import constants.NamingConventions;
import gui.components.LBJCheckBox;
import gui.components.LBJTextBox;
import gui.updaters.LBJFormUpdater;

/**
 * Updates index name according to table and column names.
 */
public class AddColumnIndexNameUpdater implements LBJFormUpdater<AddColumnForm> {

	@Override
	public void update(AddColumnForm form) {
		LBJCheckBox indexCheckBox = form.getIndexCheckBox();
		LBJTextBox indexName = form.getIndexNameTextBox();
		LBJTextBox tableName = form.getTableNameTextBox();
		LBJTextBox columnName = form.getColumnNameTextBox();

		if (!indexCheckBox.isChecked()) {
			indexName.setValue("");
			indexName.setEnabled(false);
			return;
		}
		indexName.setEnabled(true);
		if (tableName.isFocused() || columnName.isFocused() || indexCheckBox.isFocused()) {
			indexName.setValue(NamingConventions.INDEX_NAME_DEFAULT_VALUE + tableName.getValue()
					+ NamingConventions.SEPARATOR + columnName.getValue());
		}

	}

}
