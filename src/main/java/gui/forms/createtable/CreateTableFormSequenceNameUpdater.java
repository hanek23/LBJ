package gui.forms.createtable;

import constants.NamingConventions;
import gui.components.LBJTextBox;
import gui.updaters.LBJFormUpdater;

/**
 * Custom updater for {@link CreateTableForm} that updates Sequence Name to
 * value "SEQ_TABLENAME"
 */
public class CreateTableFormSequenceNameUpdater implements LBJFormUpdater<CreateTableForm> {

	@Override
	public void update(CreateTableForm form) {
		LBJTextBox tableName = form.getTableNameTextBox();
		LBJTextBox sequenceName = form.getSequenceNameTextBox();

		if (tableName.isFocused()) {
			// Update only when table name is focused so that user can still choose whatever
			// name he/she wishes
			sequenceName.setValue(NamingConventions.SEQUENCE_NAME_DEFAULT_VALUE + tableName.getValue());
		}
	}

}
