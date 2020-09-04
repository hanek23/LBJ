package gui.forms.preferences;

import static testutils.asserts.LBJFormAssert.assertThat;
import static testutils.asserts.LBJValueComponentAssert.assertThat;

import org.junit.jupiter.api.Test;

import constants.Labels;
import gui.utils.BeanSupplier;
import testutils.LBJFormTestCase;
import testutils.LBJTestUtils;
import utils.LBJPreferences;

class DropTableColumnPreferencesFormTest extends LBJFormTestCase {

	@Test
	void testInitializeComponents() {
		DropTableColumnPreferencesForm form = LBJTestUtils.getDropTableColumnPreferencesForm();

		assertThat(form).hasName(Labels.DROP_TABLE_COLUMN_PREFERENCES_FORM);
		assertThat(form).hasComponentWithName(Labels.PREFERENCES_USE_LETTER_CASE_CONVENTIONS);
		assertThat(form).hasComponentWithName(Labels.PREFERENCES_COPY_TABLE_NAME);

		assertThat(form.getUseLetterCaseConvensionCheckBox())
				.hasValueEqualTo(BeanSupplier.get(LBJPreferences.class).getUseLetterCaseConventions());
		assertThat(form.getCopyTableNameCheckBox())
				.hasValueEqualTo(BeanSupplier.get(LBJPreferences.class).getCopyTableName());
	}

	@Test
	void testApplyButton() {
		DropTableColumnPreferencesForm form = LBJTestUtils.getDropTableColumnPreferencesForm();

		LBJTestUtils.setValueOf(form.getUseLetterCaseConvensionCheckBox(), false);
		LBJTestUtils.setValueOf(form.getCopyTableNameCheckBox(), true);

		LBJTestUtils.click(form.getApplyButton());
		assertThat(form).isNotFocused();
		form.focus();
		assertThat(form).isFocused();
		// Values should have been saved so components still have same values
		assertThat(form.getUseLetterCaseConvensionCheckBox()).hasValueEqualTo(false);
		assertThat(form.getCopyTableNameCheckBox()).hasValueEqualTo(true);
	}

	@Test
	void testBackButton() {
		DropTableColumnPreferencesForm form = LBJTestUtils.getDropTableColumnPreferencesForm();

		LBJTestUtils.setValueOf(form.getUseLetterCaseConvensionCheckBox(), false);
		LBJTestUtils.setValueOf(form.getCopyTableNameCheckBox(), true);

		LBJTestUtils.click(form.getBackButton());
		assertThat(form).isNotFocused();
		form.focus();
		assertThat(form).isFocused();
		// Values should have been restarted as back button was pressed
		assertThat(form.getUseLetterCaseConvensionCheckBox())
				.hasValueEqualTo(BeanSupplier.get(LBJPreferences.class).getUseLetterCaseConventions());
		assertThat(form.getCopyTableNameCheckBox())
				.hasValueEqualTo(BeanSupplier.get(LBJPreferences.class).getCopyTableName());
	}

	@Test
	void testRestartButton() {
		DropTableColumnPreferencesForm form = LBJTestUtils.getDropTableColumnPreferencesForm();

		LBJTestUtils.setValueOf(form.getUseLetterCaseConvensionCheckBox(), false);
		LBJTestUtils.setValueOf(form.getCopyTableNameCheckBox(), true);

		LBJTestUtils.click(form.getResetToDefaultButton());
		assertThat(form).isFocused();
		// Values should have been restarted to defaults
		assertThat(form.getUseLetterCaseConvensionCheckBox())
				.hasValueEqualTo(BeanSupplier.get(LBJPreferences.class).getUseLetterCaseConventions());
		assertThat(form.getCopyTableNameCheckBox())
				.hasValueEqualTo(BeanSupplier.get(LBJPreferences.class).getCopyTableName());
	}

}
