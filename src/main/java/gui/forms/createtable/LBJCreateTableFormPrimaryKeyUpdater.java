package gui.forms.createtable;

import constants.NamingConventions;
import gui.components.LBJTextBox;
import gui.updaters.LBJFormUpdater;

public class LBJCreateTableFormPrimaryKeyUpdater implements LBJFormUpdater<LBJCreateTableForm> {

	@Override
	public void update(LBJCreateTableForm form) {
		LBJTextBox tableName = form.getTableNameTextBox();
		LBJTextBox primaryKeyName = form.getPrimaryKeyNameTextBox();
		LBJTextBox primaryKeyConstraint = form.getPrimaryKeyConstraintNameTextBox();
		if (tableName.isFocused()) {
			primaryKeyName.setValue(NamingConventions.PRIMARY_KEY_NAME_DEFAULT_VALUE + tableName.getValue());
			primaryKeyConstraint
					.setValue(NamingConventions.PRIMARY_KEY_CONSTRAINT_DEFAULT_VALUE + tableName.getValue());
		}

	}

}
