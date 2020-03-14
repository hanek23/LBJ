package gui.forms.createtable;

import gui.components.LBJCheckBox;
import gui.components.LBJPlainLabel;
import gui.validators.LBJFormValidator;

/**
 * Custom validator for {@link LBJCreateTableForm} that validates that at least
 * one checkbox of databases (oracle, mssql, postgre) is checked.
 */
public class LBJCreateTableFormDatabasesValidator implements LBJFormValidator<LBJCreateTableForm> {

	/**
	 * Validates that at least one database checkbox is checked.
	 */
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
