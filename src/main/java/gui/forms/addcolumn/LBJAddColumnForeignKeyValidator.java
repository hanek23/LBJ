package gui.forms.addcolumn;

import java.util.Arrays;

import constants.NamingConventions;
import constants.NamingConventions.LetterCase;
import gui.components.LBJCheckBox;
import gui.components.LBJTextBox;
import gui.components.LBJValueHolderComponent;
import gui.suppliers.LBJValidatorSupplier;
import gui.utils.LBJComponentUtils;
import gui.validators.LBJFormValidator;

public class LBJAddColumnForeignKeyValidator implements LBJFormValidator<LBJAddColumnForm> {

	@Override
	public boolean isValid(LBJAddColumnForm form) {
		LBJCheckBox foreignKeyCheckBox = form.getForeignKeyCheckBox();
		LBJTextBox referencedTable = form.getReferencedTableNameTextBox();
		LBJTextBox referencedColumn = form.getReferencedColumnNameTextBox();
		LBJTextBox foreignKeyName = form.getForeignKeyNameTextBox();

		editValidators(referencedTable, NamingConventions.TABLE_NAME_CASE, foreignKeyCheckBox.isChecked());
		editValidators(referencedColumn, NamingConventions.COLUMN_NAME_CASE, foreignKeyCheckBox.isChecked());
		editValidators(foreignKeyName, NamingConventions.FOREIGN_KEY_NAME_CASE, foreignKeyCheckBox.isChecked());
		return LBJComponentUtils.areComponentsValid(Arrays.asList(referencedTable, referencedColumn, foreignKeyName));
	}

	private void editValidators(LBJValueHolderComponent<String> component, LetterCase letterCase, boolean add) {
		if (add) {
			component.addValidator(LBJValidatorSupplier.getStringRequiredValidator());
			component.addValidator(LBJValidatorSupplier.getStringLengthValidator());
			component.addValidator(LBJValidatorSupplier.caseValidator(letterCase));
			return;
		}
		component.removeValidator(LBJValidatorSupplier.getStringRequiredValidator());
		component.removeValidator(LBJValidatorSupplier.getStringLengthValidator());
		component.removeValidator(LBJValidatorSupplier.caseValidator(letterCase));
	}

}
