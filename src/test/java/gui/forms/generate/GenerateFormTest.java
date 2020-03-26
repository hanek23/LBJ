package gui.forms.generate;

import static testutils.asserts.LBJFormAssert.assertThat;
import static testutils.asserts.LBJValueHolderComponentAssert.assertThat;

import org.junit.jupiter.api.Test;

import constants.Labels;
import gui.suppliers.LBJUpdaterSupplier;
import gui.suppliers.LBJValidatorSupplier;
import testutils.LBJTestUtils;

public class GenerateFormTest {

	@Test
	public void testInitialize() {
		GenerateForm form = LBJTestUtils.getGenerateForm();

		// has all components
		assertThat(form).hasName(Labels.GENERATE_FORM);
		assertThat(form).hasComponentWithName(Labels.GENERATE_FORM_AUTHOR);
		assertThat(form).hasComponentWithName(Labels.GENERATE_FORM_ONLY_CHANGESETS);
		assertThat(form).hasComponentWithName(Labels.GENERATE_FORM_CHANGESETS_STARTING_ID);
		assertThat(form).hasComponentWithName(Labels.GENERATE_FORM_DATABASES);
		assertThat(form).hasComponentWithName(Labels.GENERATE_FORM_DATABASES_ORACLE);
		assertThat(form).hasComponentWithName(Labels.GENERATE_FORM_DATABASES_MSSQL);
		assertThat(form).hasComponentWithName(Labels.GENERATE_FORM_DATABASES_POSTGRESQL);
		assertThat(form).hasComponentWithName(Labels.GENERATE_FORM_GENERATED_XML);

		// in right states
		assertThat(form.getOnlyChangesetsCheckBox()).isNotChecked();
		assertThat(form.getOracleCheckBox()).isChecked();
		assertThat(form.getMssqlCheckBox()).isChecked();
		assertThat(form.getPostgreCheckBox()).isChecked();

		// with all validators
		assertThat(form.getAuthorTextBox()).isRequired();
		assertThat(form.getStartingIdTextBox()).isRequired().isNumbersOnly();
		assertThat(form).hasValidator(LBJValidatorSupplier.getGenerateFormDatabasesValidator());

		// and updaters
		assertThat(form).hasUpdater(LBJUpdaterSupplier.getGenerateFormStartingIdUpdater());
	}

}
