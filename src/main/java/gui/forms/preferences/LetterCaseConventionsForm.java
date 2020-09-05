package gui.forms.preferences;

import com.googlecode.lanterna.gui2.Window;

import constants.Labels;
import constants.NamingConventions;
import constants.NamingConventions.LetterCase;
import gui.builders.LBJComboBoxBuilder;
import gui.components.LBJComboBox;
import gui.forms.LBJForm;
import gui.forms.LBJPreferencesForm;
import gui.utils.LBJFormUtils;
import main.LBJ;

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
		tableNameCase = createComboBoxLetterCase(Labels.TABLE_NAME, LBJ.preferences.getTableNameCase());

		primaryKeyNameCase = createComboBoxLetterCase(Labels.PRIMARY_KEY_NAME, LBJ.preferences.getPrimaryKeyNameCase());

		primaryKeyConstraintNameCase = createComboBoxLetterCase(Labels.PRIMARY_KEY_CONSTRAIN_NAME,
				LBJ.preferences.getPrimaryKeyConstraintNameCase());

		sequenceNameCase = createComboBoxLetterCase(Labels.SEQUENCE_NAME, LBJ.preferences.getSequenceNameCase());

		columnNameCase = createComboBoxLetterCase(Labels.COLUMN_NAME, LBJ.preferences.getColumnNameCase());

		dataTypeCase = createComboBoxLetterCase(Labels.DATA_TYPE, LBJ.preferences.getDataTypeCase());

		foreignKeyNameCase = createComboBoxLetterCase(Labels.FOREIGN_KEY_NAME, LBJ.preferences.getForeignKeyNameCase());

		indexNameCase = createComboBoxLetterCase(Labels.INDEX_NAME, LBJ.preferences.getIndexNameCase());
	}

	private LBJComboBox<LetterCase> createComboBoxLetterCase(String name, LetterCase startValue) {
		return new LBJComboBoxBuilder<LetterCase>(name, this).addItem(LetterCase.NONE).addItem(LetterCase.LOWER)
				.addItem(LetterCase.UPPER).selectItem(startValue).build();
	}

	@Override
	public void setComponentsToPreferenceValues() {
		tableNameCase.setValue(LBJ.preferences.getTableNameCase());
		primaryKeyNameCase.setValue(LBJ.preferences.getPrimaryKeyNameCase());
		primaryKeyConstraintNameCase.setValue(LBJ.preferences.getPrimaryKeyConstraintNameCase());
		sequenceNameCase.setValue(LBJ.preferences.getSequenceNameCase());
		columnNameCase.setValue(LBJ.preferences.getColumnNameCase());
		dataTypeCase.setValue(LBJ.preferences.getDataTypeCase());
		foreignKeyNameCase.setValue(LBJ.preferences.getForeignKeyNameCase());
		indexNameCase.setValue(LBJ.preferences.getIndexNameCase());
	}

	@Override
	public void applyToPreferences() {
		LBJ.preferences.put(NamingConventions.PKEY_TABLE_NAME_CASE, tableNameCase.getValue().toString());

		LBJ.preferences.put(NamingConventions.PKEY_PRIMARY_KEY_NAME_CASE, primaryKeyNameCase.getValue().toString());

		LBJ.preferences.put(NamingConventions.PKEY_PRIMARY_KEY_CONSTRAINT_NAME_CASE,
				primaryKeyConstraintNameCase.getValue().toString());

		LBJ.preferences.put(NamingConventions.PKEY_SEQUENCE_NAME_CASE, sequenceNameCase.getValue().toString());

		LBJ.preferences.put(NamingConventions.PKEY_COLUMN_NAME_CASE, columnNameCase.getValue().toString());

		LBJ.preferences.put(NamingConventions.PKEY_DATA_TYPE_CASE, dataTypeCase.getValue().toString());

		LBJ.preferences.put(NamingConventions.PKEY_FOREIGN_KEY_NAME_CASE, foreignKeyNameCase.getValue().toString());

		LBJ.preferences.put(NamingConventions.PKEY_INDEX_NAME_CASE, indexNameCase.getValue().toString());
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
		LBJFormUtils.addValueAndLabeledComponentTo(this, columnNameCase);
		LBJFormUtils.addValueAndLabeledComponentTo(this, dataTypeCase);
		LBJFormUtils.addValueAndLabeledComponentTo(this, primaryKeyNameCase);
		LBJFormUtils.addValueAndLabeledComponentTo(this, primaryKeyConstraintNameCase);
		LBJFormUtils.addValueAndLabeledComponentTo(this, sequenceNameCase);
		LBJFormUtils.addValueAndLabeledComponentTo(this, foreignKeyNameCase);
		LBJFormUtils.addValueAndLabeledComponentTo(this, indexNameCase);
	}

	@Override
	public String toString() {
		return Labels.LETTER_CASE_FORM;
	}

	public LBJComboBox<LetterCase> getTableNameCase() {
		return tableNameCase;
	}

	public LBJComboBox<LetterCase> getPrimaryKeyNameCase() {
		return primaryKeyNameCase;
	}

	public LBJComboBox<LetterCase> getPrimaryKeyConstraintNameCase() {
		return primaryKeyConstraintNameCase;
	}

	public LBJComboBox<LetterCase> getSequenceNameCase() {
		return sequenceNameCase;
	}

	public LBJComboBox<LetterCase> getColumnNameCase() {
		return columnNameCase;
	}

	public LBJComboBox<LetterCase> getDataTypeCase() {
		return dataTypeCase;
	}

	public LBJComboBox<LetterCase> getForeignKeyNameCase() {
		return foreignKeyNameCase;
	}

	public LBJComboBox<LetterCase> getIndexNameCase() {
		return indexNameCase;
	}

}
