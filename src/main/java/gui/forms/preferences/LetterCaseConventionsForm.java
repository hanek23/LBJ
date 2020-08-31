package gui.forms.preferences;

import com.googlecode.lanterna.gui2.Window;

import constants.Labels;
import constants.NamingConventions;
import constants.NamingConventions.LetterCase;
import gui.builders.LBJComboBoxBuilder;
import gui.components.LBJComboBox;
import gui.forms.LBJForm;
import gui.forms.LBJPreferencesForm;
import gui.utils.BeanSupplier;
import gui.utils.LBJFormUtils;
import utils.LBJPreferences;

public class LetterCaseConventionsForm extends LBJPreferencesForm {

	private LBJComboBox<LetterCase> tableNameCase;
	private LBJComboBox<LetterCase> primaryKeyNameCase;
	private LBJComboBox<LetterCase> primaryKeyConstraintNameCase;
	private LBJComboBox<LetterCase> sequenceNameCase;
	private LBJComboBox<LetterCase> columnNameCase;
	private LBJComboBox<LetterCase> dataTypeCase;
	private LBJComboBox<LetterCase> foreignKeyNameCase;
	private LBJComboBox<LetterCase> indexNameCase;

	public LetterCaseConventionsForm(Window window, LBJForm previousForm) {
		super(window, previousForm);
	}

	@Override
	public void initializeComponents() {
		tableNameCase = createComboBoxLetterCase(Labels.TABLE_NAME,
				BeanSupplier.get(LBJPreferences.class).getTableNameCase());

		primaryKeyNameCase = createComboBoxLetterCase(Labels.CREATE_TABLE_PRIMARY_KEY_NAME,
				BeanSupplier.get(LBJPreferences.class).getPrimaryKeyNameCase());

		primaryKeyConstraintNameCase = createComboBoxLetterCase(Labels.CREATE_TABLE_PRIMARY_KEY_CONSTRAIN_NAME,
				BeanSupplier.get(LBJPreferences.class).getPrimaryKeyConstraintNameCase());

		sequenceNameCase = createComboBoxLetterCase(Labels.TABLE_SEQUENCE_NAME,
				BeanSupplier.get(LBJPreferences.class).getSequenceNameCase());

		columnNameCase = createComboBoxLetterCase(Labels.COLUMN_NAME,
				BeanSupplier.get(LBJPreferences.class).getColumnNameCase());

		dataTypeCase = createComboBoxLetterCase(Labels.COLUMN_DATA_TYPE,
				BeanSupplier.get(LBJPreferences.class).getDataTypeCase());

		foreignKeyNameCase = createComboBoxLetterCase(Labels.ADD_COLUMN_FOREIGN_KEY_NAME,
				BeanSupplier.get(LBJPreferences.class).getForeignKeyNameCase());

		indexNameCase = createComboBoxLetterCase(Labels.ADD_COLUMN_INDEX_NAME,
				BeanSupplier.get(LBJPreferences.class).getIndexNameCase());
	}

	private LBJComboBox<LetterCase> createComboBoxLetterCase(String name, LetterCase startValue) {
		return new LBJComboBoxBuilder<LetterCase>(name, this).addItem(LetterCase.NONE).addItem(LetterCase.LOWER)
				.addItem(LetterCase.UPPER).selectItem(startValue).build();
	}

	@Override
	public void setComponentsToPreferenceValues() {
		tableNameCase.setValue(BeanSupplier.get(LBJPreferences.class).getTableNameCase());
		primaryKeyNameCase.setValue(BeanSupplier.get(LBJPreferences.class).getPrimaryKeyNameCase());
		primaryKeyConstraintNameCase.setValue(BeanSupplier.get(LBJPreferences.class).getPrimaryKeyConstraintNameCase());
		sequenceNameCase.setValue(BeanSupplier.get(LBJPreferences.class).getSequenceNameCase());
		columnNameCase.setValue(BeanSupplier.get(LBJPreferences.class).getColumnNameCase());
		dataTypeCase.setValue(BeanSupplier.get(LBJPreferences.class).getDataTypeCase());
		foreignKeyNameCase.setValue(BeanSupplier.get(LBJPreferences.class).getForeignKeyNameCase());
		indexNameCase.setValue(BeanSupplier.get(LBJPreferences.class).getIndexNameCase());
	}

	@Override
	public void applyToPreferences() {
		BeanSupplier.get(LBJPreferences.class).put(NamingConventions.PKEY_TABLE_NAME_CASE,
				tableNameCase.getValue().toString());

		BeanSupplier.get(LBJPreferences.class).put(NamingConventions.PKEY_PRIMARY_KEY_NAME_CASE,
				primaryKeyNameCase.getValue().toString());

		BeanSupplier.get(LBJPreferences.class).put(NamingConventions.PKEY_PRIMARY_KEY_CONSTRAINT_NAME_CASE,
				primaryKeyConstraintNameCase.getValue().toString());

		BeanSupplier.get(LBJPreferences.class).put(NamingConventions.PKEY_SEQUENCE_NAME_CASE,
				sequenceNameCase.getValue().toString());

		BeanSupplier.get(LBJPreferences.class).put(NamingConventions.PKEY_COLUMN_NAME_CASE,
				columnNameCase.getValue().toString());

		BeanSupplier.get(LBJPreferences.class).put(NamingConventions.PKEY_DATA_TYPE_CASE,
				dataTypeCase.getValue().toString());

		BeanSupplier.get(LBJPreferences.class).put(NamingConventions.PKEY_FOREIGN_KEY_NAME_CASE,
				foreignKeyNameCase.getValue().toString());

		BeanSupplier.get(LBJPreferences.class).put(NamingConventions.PKEY_INDEX_NAME_CASE,
				indexNameCase.getValue().toString());
	}

	@Override
	public void addFormUpdaters() {
		// no form updaters

	}

	@Override
	public void addFormValidators() {
		// no form validators

	}

	@Override
	public void addComponents() {
		LBJFormUtils.addValueAndLabeledComponentTo(this, tableNameCase);
		LBJFormUtils.addValueAndLabeledComponentTo(this, primaryKeyNameCase);
		LBJFormUtils.addValueAndLabeledComponentTo(this, primaryKeyConstraintNameCase);
		LBJFormUtils.addValueAndLabeledComponentTo(this, sequenceNameCase);
		LBJFormUtils.addValueAndLabeledComponentTo(this, columnNameCase);
		LBJFormUtils.addValueAndLabeledComponentTo(this, dataTypeCase);
		LBJFormUtils.addValueAndLabeledComponentTo(this, foreignKeyNameCase);
		LBJFormUtils.addValueAndLabeledComponentTo(this, indexNameCase);
	}

	@Override
	public String toString() {
		return Labels.LETTER_CASE_FORM;
	}
}
