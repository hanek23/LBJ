package gui.forms.addcolumn;

import constants.NamingConventions;
import gui.components.LBJCheckBox;
import gui.components.LBJTextBox;
import gui.updaters.LBJFormUpdater;

public class LBJAddColumnIndexNameUpdater implements LBJFormUpdater<LBJAddColumnForm> {

	@Override
	public void update(LBJAddColumnForm form) {
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
