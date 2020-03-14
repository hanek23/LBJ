package gui.forms.createtable;

import gui.components.LBJCheckBox;
import gui.components.LBJPlainLabel;
import gui.validators.LBJFormValidator;

public class LBJCreateTableFormDatabasesValidator implements LBJFormValidator<LBJCreateTableForm> {

	@Override
	public boolean isValid(LBJCreateTableForm form) {
		LBJPlainLabel databases = form.getDatabasesLabel();
		LBJCheckBox oracle = form.getOracleCheckBox();
		LBJCheckBox postgre = form.getPostgreCheckBox();
		LBJCheckBox mssql = form.getMssqlCheckBox();

		boolean toReturn = oracle.isChecked() || mssql.isChecked() || postgre.isChecked();
		databases.setLabelColorByValidity(toReturn);
		return toReturn;
	}

}
