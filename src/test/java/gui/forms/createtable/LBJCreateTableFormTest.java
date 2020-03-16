package gui.forms.createtable;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import constants.Labels;
import constants.NamingConventions;
import gui.components.LBJMockForm;
import gui.components.LBJTextBox;
import gui.suppliers.LBJValidatorSupplier;
import gui.validators.LBJValueValidator;

public class LBJCreateTableFormTest {

	private LBJCreateTableForm tested;

	@BeforeEach
	public void beforeEach() {
		tested = new LBJCreateTableForm(null, new LBJMockForm());
	}

	@Test
	public void testInitializeComponents() {
		assertThat(tested.getTableNameTextBox().getLabel().getText()).isEqualTo(Labels.TABLE_NAME);
		assertThat(tested.getPrimaryKeyNameTextBox().getLabel().getText())
				.isEqualTo(Labels.CREATE_TABLE_PRIMARY_KEY_NAME);
		assertThat(tested.getPrimaryKeyConstraintNameTextBox().getLabel().getText())
				.isEqualTo(Labels.CREATE_TABLE_PRIMARY_KEY_CONSTRAIN);
		assertThat(tested.getDatabasesLabel().getLabel().getText()).isEqualTo(Labels.CREATE_TABLE_DATABASES);
		assertThat(tested.getOracleCheckBox().getLabel().getText()).isEqualTo(Labels.CREATE_TABLE_DATABASES_ORACLE);
		assertThat(tested.getMssqlCheckBox().getLabel().getText()).isEqualTo(Labels.CREATE_TABLE_DATABASES_MSSQL);
		assertThat(tested.getPostgreCheckBox().getLabel().getText())
				.isEqualTo(Labels.CREATE_TABLE_DATABASES_POSTGRESQL);
		assertThat(tested.getSequenceNameTextBox().getLabel().getText()).isEqualTo(Labels.CREATE_TABLE_SEQUENCE_NAME);
		assertThat(tested.toString()).isEqualTo(Labels.TABLE_FORM);

		assertTrue(tested.getOracleCheckBox().isChecked());
		assertTrue(tested.getMssqlCheckBox().isChecked());
		assertTrue(tested.getPostgreCheckBox().isChecked());

		assertValidator(tested.getTableNameTextBox(),
				LBJValidatorSupplier.caseValidator(NamingConventions.TABLE_NAME_CASE));
		assertValidator(tested.getTableNameTextBox(), LBJValidatorSupplier.getStringRequiredValidator());
		assertValidator(tested.getTableNameTextBox(), LBJValidatorSupplier.getStringLengthValidator());

		assertValidator(tested.getPrimaryKeyNameTextBox(),
				LBJValidatorSupplier.caseValidator(NamingConventions.PRIMARY_KEY_NAME_CASE));
		assertValidator(tested.getPrimaryKeyNameTextBox(), LBJValidatorSupplier.getStringRequiredValidator());
		assertValidator(tested.getPrimaryKeyNameTextBox(), LBJValidatorSupplier.getStringLengthValidator());

		assertValidator(tested.getPrimaryKeyConstraintNameTextBox(),
				LBJValidatorSupplier.caseValidator(NamingConventions.PRIMARY_KEY_CONSTRAINT_NAME_CASE));
		assertValidator(tested.getPrimaryKeyConstraintNameTextBox(), LBJValidatorSupplier.getStringRequiredValidator());
		assertValidator(tested.getPrimaryKeyConstraintNameTextBox(), LBJValidatorSupplier.getStringLengthValidator());

		assertValidator(tested.getSequenceNameTextBox(),
				LBJValidatorSupplier.caseValidator(NamingConventions.SEQUENCE_NAME_CASE));
		assertValidator(tested.getSequenceNameTextBox(), LBJValidatorSupplier.getStringRequiredValidator());
		assertValidator(tested.getSequenceNameTextBox(), LBJValidatorSupplier.getStringLengthValidator());

	}

	private void assertValidator(LBJTextBox tableNameTextBox, LBJValueValidator<String> caseValidator) {
		assertThat(tableNameTextBox.getValidators()).contains(caseValidator);
	}

}
