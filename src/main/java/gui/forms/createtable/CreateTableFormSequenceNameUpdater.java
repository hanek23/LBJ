package gui.forms.createtable;

import constants.NamingConventions;
import gui.components.LBJCheckBox;
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
		LBJCheckBox oracle = form.getOracleCheckBox();
		LBJCheckBox postgre = form.getPostgreCheckBox();
		LBJTextBox sequenceName = form.getSequenceNameTextBox();

		if (oracle.isChecked() || postgre.isChecked()) {
			if (tableName.isFocused()) {
				sequenceName.setValue(NamingConventions.SEQUENCE_NAME_DEFAULT_VALUE + tableName.getValue());
			}
			sequenceName.setEnabled(true);
		} else {
			sequenceName.setValue("");
			sequenceName.setEnabled(false);
		}
	}

}
