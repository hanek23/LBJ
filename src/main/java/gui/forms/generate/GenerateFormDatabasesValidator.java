package gui.forms.generate;

import gui.components.LBJCheckBox;
import gui.components.LBJPlainLabel;
import gui.utils.Bean;
import gui.utils.SimpleBean;
import gui.validators.LBJFormValidator;

/**
 * Custom validator for {@link GenerateForm} that validates that at least one
 * checkbox of databases (oracle, mssql, postgre) is checked.
 */
public class GenerateFormDatabasesValidator
		implements LBJFormValidator<GenerateForm>, SimpleBean<GenerateFormDatabasesValidator> {

	/**
	 * Validates that at least one database checkbox is checked.
	 */
	@Override
	public boolean isValid(GenerateForm form) {
		LBJPlainLabel databases = form.getDatabasesLabel();
		LBJCheckBox oracle = form.getOracleCheckBox();
		LBJCheckBox postgre = form.getPostgreCheckBox();
		LBJCheckBox mssql = form.getMssqlCheckBox();

		boolean toReturn = oracle.isChecked() || mssql.isChecked() || postgre.isChecked();
		databases.setLabelColorByValidity(toReturn);
		return toReturn;
	}

}
