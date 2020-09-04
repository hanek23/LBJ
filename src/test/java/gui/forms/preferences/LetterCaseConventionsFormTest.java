package gui.forms.preferences;

import static testutils.asserts.LBJFormAssert.assertThat;
import static testutils.asserts.LBJValueComponentAssert.assertThat;

import org.junit.jupiter.api.Test;

import constants.Labels;
import constants.NamingConventions.LetterCase;
import gui.utils.BeanSupplier;
import testutils.LBJFormTestCase;
import testutils.LBJTestUtils;
import utils.LBJPreferences;

class LetterCaseConventionsFormTest extends LBJFormTestCase {

	@Test
	void testInitializeComponents() {
		LetterCaseConventionsForm form = LBJTestUtils.getLetterCaseConventionsForm();

		assertThat(form).hasName(Labels.LETTER_CASE_FORM);
		assertThat(form).hasComponentWithName(Labels.TABLE_NAME);
		assertThat(form).hasComponentWithName(Labels.COLUMN_NAME);
		assertThat(form).hasComponentWithName(Labels.COLUMN_DATA_TYPE);
		assertThat(form).hasComponentWithName(Labels.CREATE_TABLE_PRIMARY_KEY_NAME);
		assertThat(form).hasComponentWithName(Labels.CREATE_TABLE_PRIMARY_KEY_CONSTRAIN_NAME);
		assertThat(form).hasComponentWithName(Labels.TABLE_SEQUENCE_NAME);
		assertThat(form).hasComponentWithName(Labels.ADD_COLUMN_FOREIGN_KEY_NAME);
		assertThat(form).hasComponentWithName(Labels.ADD_COLUMN_INDEX_NAME);

		assertThat(form.getTableNameCase()).hasValueEqualTo(BeanSupplier.get(LBJPreferences.class).getTableNameCase());
		assertThat(form.getColumnNameCase())
				.hasValueEqualTo(BeanSupplier.get(LBJPreferences.class).getColumnNameCase());
		assertThat(form.getDataTypeCase()).hasValueEqualTo(BeanSupplier.get(LBJPreferences.class).getDataTypeCase());
		assertThat(form.getPrimaryKeyNameCase())
				.hasValueEqualTo(BeanSupplier.get(LBJPreferences.class).getPrimaryKeyNameCase());
		assertThat(form.getPrimaryKeyConstraintNameCase())
				.hasValueEqualTo(BeanSupplier.get(LBJPreferences.class).getPrimaryKeyConstraintNameCase());
		assertThat(form.getSequenceNameCase())
				.hasValueEqualTo(BeanSupplier.get(LBJPreferences.class).getSequenceNameCase());
		assertThat(form.getForeignKeyNameCase())
				.hasValueEqualTo(BeanSupplier.get(LBJPreferences.class).getForeignKeyNameCase());
		assertThat(form.getIndexNameCase()).hasValueEqualTo(BeanSupplier.get(LBJPreferences.class).getIndexNameCase());
	}

	@Test
	void testApplyButton() {
		LetterCaseConventionsForm form = LBJTestUtils.getLetterCaseConventionsForm();

		LBJTestUtils.setValueOf(form.getTableNameCase(), LetterCase.UPPER);
		LBJTestUtils.setValueOf(form.getColumnNameCase(), LetterCase.UPPER);
		LBJTestUtils.setValueOf(form.getDataTypeCase(), LetterCase.UPPER);
		LBJTestUtils.setValueOf(form.getPrimaryKeyNameCase(), LetterCase.UPPER);
		LBJTestUtils.setValueOf(form.getPrimaryKeyConstraintNameCase(), LetterCase.UPPER);
		LBJTestUtils.setValueOf(form.getSequenceNameCase(), LetterCase.UPPER);
		LBJTestUtils.setValueOf(form.getForeignKeyNameCase(), LetterCase.UPPER);
		LBJTestUtils.setValueOf(form.getIndexNameCase(), LetterCase.UPPER);

		LBJTestUtils.click(form.getApplyButton());
		assertThat(form).isNotFocused();
		form.focus();
		assertThat(form).isFocused();
		// Values should have been saved so components still have same values
		assertThat(form.getTableNameCase()).hasValueEqualTo(LetterCase.UPPER);
		assertThat(form.getColumnNameCase()).hasValueEqualTo(LetterCase.UPPER);
		assertThat(form.getDataTypeCase()).hasValueEqualTo(LetterCase.UPPER);
		assertThat(form.getPrimaryKeyNameCase()).hasValueEqualTo(LetterCase.UPPER);
		assertThat(form.getPrimaryKeyConstraintNameCase()).hasValueEqualTo(LetterCase.UPPER);
		assertThat(form.getSequenceNameCase()).hasValueEqualTo(LetterCase.UPPER);
		assertThat(form.getForeignKeyNameCase()).hasValueEqualTo(LetterCase.UPPER);
		assertThat(form.getIndexNameCase()).hasValueEqualTo(LetterCase.UPPER);
	}

	@Test
	void testBackButton() {
		LetterCaseConventionsForm form = LBJTestUtils.getLetterCaseConventionsForm();

		LBJTestUtils.setValueOf(form.getTableNameCase(), LetterCase.UPPER);
		LBJTestUtils.setValueOf(form.getColumnNameCase(), LetterCase.UPPER);
		LBJTestUtils.setValueOf(form.getDataTypeCase(), LetterCase.UPPER);
		LBJTestUtils.setValueOf(form.getPrimaryKeyNameCase(), LetterCase.UPPER);
		LBJTestUtils.setValueOf(form.getPrimaryKeyConstraintNameCase(), LetterCase.UPPER);
		LBJTestUtils.setValueOf(form.getSequenceNameCase(), LetterCase.UPPER);
		LBJTestUtils.setValueOf(form.getForeignKeyNameCase(), LetterCase.UPPER);
		LBJTestUtils.setValueOf(form.getIndexNameCase(), LetterCase.UPPER);

		LBJTestUtils.click(form.getBackButton());
		assertThat(form).isNotFocused();
		form.focus();
		assertThat(form).isFocused();
		// Values should have been restarted as back button was pressed
		assertThat(form.getTableNameCase()).hasValueEqualTo(BeanSupplier.get(LBJPreferences.class).getTableNameCase());
		assertThat(form.getColumnNameCase())
				.hasValueEqualTo(BeanSupplier.get(LBJPreferences.class).getColumnNameCase());
		assertThat(form.getDataTypeCase()).hasValueEqualTo(BeanSupplier.get(LBJPreferences.class).getDataTypeCase());
		assertThat(form.getPrimaryKeyNameCase())
				.hasValueEqualTo(BeanSupplier.get(LBJPreferences.class).getPrimaryKeyNameCase());
		assertThat(form.getPrimaryKeyConstraintNameCase())
				.hasValueEqualTo(BeanSupplier.get(LBJPreferences.class).getPrimaryKeyConstraintNameCase());
		assertThat(form.getSequenceNameCase())
				.hasValueEqualTo(BeanSupplier.get(LBJPreferences.class).getSequenceNameCase());
		assertThat(form.getForeignKeyNameCase())
				.hasValueEqualTo(BeanSupplier.get(LBJPreferences.class).getForeignKeyNameCase());
		assertThat(form.getIndexNameCase()).hasValueEqualTo(BeanSupplier.get(LBJPreferences.class).getIndexNameCase());
	}

	@Test
	void testRestartButton() {
		LetterCaseConventionsForm form = LBJTestUtils.getLetterCaseConventionsForm();

		LBJTestUtils.setValueOf(form.getTableNameCase(), LetterCase.UPPER);
		LBJTestUtils.setValueOf(form.getColumnNameCase(), LetterCase.UPPER);
		LBJTestUtils.setValueOf(form.getDataTypeCase(), LetterCase.UPPER);
		LBJTestUtils.setValueOf(form.getPrimaryKeyNameCase(), LetterCase.UPPER);
		LBJTestUtils.setValueOf(form.getPrimaryKeyConstraintNameCase(), LetterCase.UPPER);
		LBJTestUtils.setValueOf(form.getSequenceNameCase(), LetterCase.UPPER);
		LBJTestUtils.setValueOf(form.getForeignKeyNameCase(), LetterCase.UPPER);
		LBJTestUtils.setValueOf(form.getIndexNameCase(), LetterCase.UPPER);

		LBJTestUtils.click(form.getResetToDefaultButton());
		assertThat(form).isFocused();
		// Values should have been restarted to defaults
		assertThat(form.getTableNameCase()).hasValueEqualTo(BeanSupplier.get(LBJPreferences.class).getTableNameCase());
		assertThat(form.getColumnNameCase())
				.hasValueEqualTo(BeanSupplier.get(LBJPreferences.class).getColumnNameCase());
		assertThat(form.getDataTypeCase()).hasValueEqualTo(BeanSupplier.get(LBJPreferences.class).getDataTypeCase());
		assertThat(form.getPrimaryKeyNameCase())
				.hasValueEqualTo(BeanSupplier.get(LBJPreferences.class).getPrimaryKeyNameCase());
		assertThat(form.getPrimaryKeyConstraintNameCase())
				.hasValueEqualTo(BeanSupplier.get(LBJPreferences.class).getPrimaryKeyConstraintNameCase());
		assertThat(form.getSequenceNameCase())
				.hasValueEqualTo(BeanSupplier.get(LBJPreferences.class).getSequenceNameCase());
		assertThat(form.getForeignKeyNameCase())
				.hasValueEqualTo(BeanSupplier.get(LBJPreferences.class).getForeignKeyNameCase());
		assertThat(form.getIndexNameCase()).hasValueEqualTo(BeanSupplier.get(LBJPreferences.class).getIndexNameCase());
	}

}
