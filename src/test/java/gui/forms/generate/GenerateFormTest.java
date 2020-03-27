package gui.forms.generate;

import static org.assertj.core.api.Assertions.assertThat;
import static testutils.asserts.LBJFormAssert.assertThat;
import static testutils.asserts.LBJValueHolderComponentAssert.assertThat;

import org.junit.jupiter.api.Test;

import constants.Labels;
import generator.GeneratorSettings;
import gui.suppliers.LBJUpdaterSupplier;
import gui.suppliers.LBJValidatorSupplier;
import testutils.LBJTestUtils;

public class GenerateFormTest {

	private static final String AUTHOR = "hanek23";
	private static final String STARTING_ID = "23";

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

	@Test
	public void testCreateGeneratorSettings() throws Exception {
		GenerateForm form = LBJTestUtils.getGenerateForm();

		LBJTestUtils.setValueOf(form.getAuthorTextBox(), AUTHOR);
		LBJTestUtils.setValueOf(form.getOnlyChangesetsCheckBox(), true);
		LBJTestUtils.setValueOf(form.getStartingIdTextBox(), STARTING_ID);

		GeneratorSettings settings = form.createGeneratorSettings();

		assertThat(settings.getAuthor()).isEqualTo(AUTHOR);
		assertThat(settings.isOnlyChangeSets()).isEqualTo(true);
		assertThat(settings.getStartingId()).isEqualTo(Integer.parseInt(STARTING_ID));

		LBJTestUtils.setValueOf(form.getOnlyChangesetsCheckBox(), false);

		settings = form.createGeneratorSettings();

		assertThat(settings.getAuthor()).isEqualTo(AUTHOR);
		assertThat(settings.isOnlyChangeSets()).isEqualTo(false);
		assertThat(settings.getStartingId()).isEqualTo(1);
	}

}
