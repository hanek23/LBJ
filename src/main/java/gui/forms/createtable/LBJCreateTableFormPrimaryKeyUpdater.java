package gui.forms.createtable;

import constants.NamingConventions;
import gui.components.LBJTextBox;
import gui.updaters.LBJFormUpdater;

/**
 * Custom updater for {@link LBJCreateTableForm} that updates Primary key Name
 * to value "id_tablename" and primary key constraint to "P_TABLENAME".
 */
public class LBJCreateTableFormPrimaryKeyUpdater implements LBJFormUpdater<LBJCreateTableForm> {

	/**
	 * Updates value of primary key name to "id_tableName" and primary key
	 * constraint name to "P_TABLENAME". Updates it only if tableName is focused so
	 * that user can still changed it to her/his will.
	 */
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
