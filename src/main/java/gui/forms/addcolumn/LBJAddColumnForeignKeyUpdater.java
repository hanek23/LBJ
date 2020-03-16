package gui.forms.addcolumn;

import constants.NamingConventions;
import gui.components.LBJCheckBox;
import gui.components.LBJTextBox;
import gui.updaters.LBJFormUpdater;

public class LBJAddColumnForeignKeyUpdater implements LBJFormUpdater<LBJAddColumnForm> {

	@Override
	public void update(LBJAddColumnForm form) {
		LBJCheckBox foreignKeyCheckBox = form.getForeignKeyCheckBox();
		LBJTextBox referencedTable = form.getReferencedTableNameTextBox();
		LBJTextBox referencedColumn = form.getReferencedColumnNameTextBox();
		LBJTextBox foreignKeyName = form.getForeignKeyNameTextBox();

		if (!foreignKeyCheckBox.isChecked()) {
			referencedTable.setEnabled(false);
			referencedColumn.setEnabled(false);
			foreignKeyName.setEnabled(false);
			referencedTable.setValue("");
			referencedColumn.setValue("");
			foreignKeyName.setValue("");
			return;
		}

		if (foreignKeyCheckBox.isChecked()) {
			referencedTable.setEnabled(true);
			referencedColumn.setEnabled(true);
			foreignKeyName.setEnabled(true);
			if (referencedTable.isFocused() || referencedColumn.isFocused()) {
				foreignKeyName.setValue(NamingConventions.FOREIGN_KEY_NAME_DEFAULT_VALUE + referencedTable.getValue()
						+ NamingConventions.SEPARATOR + referencedColumn.getValue());
			}
		}
	}

}
