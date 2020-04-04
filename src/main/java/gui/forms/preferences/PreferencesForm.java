package gui.forms.preferences;

import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.Window;

import constants.Labels;
import constants.NamingConventions;
import gui.builders.LBJTextBoxBuilder;
import gui.components.LBJTextBox;
import gui.forms.LBJForm;
import gui.forms.LBJWizardForm;
import gui.utils.LBJFormUtils;
import main.LBJ;

public class PreferencesForm extends LBJWizardForm {

	private LBJTextBox primaryKeyName;
	private LBJTextBox primaryKeyConstraintName;
	private LBJTextBox sequenceName;
	private LBJTextBox indexName;
	private LBJTextBox foreignKeyName;

	// cannot use default back button as I need to override a bit with applying and
	// reseting changes to preferences
	private Button backButton;
	private Button applyButton;
	private Button resetToDefaultButton;

	public PreferencesForm(Window window, LBJForm previousForm) {
		super(window, previousForm);
	}

	@Override
	public void initializeComponents() {
		primaryKeyName = new LBJTextBoxBuilder(Labels.CREATE_TABLE_PRIMARY_KEY_NAME, this)
				.value(NamingConventions.getPrimaryKeyName()).required().build();

		primaryKeyConstraintName = new LBJTextBoxBuilder(Labels.CREATE_TABLE_PRIMARY_KEY_CONSTRAIN_NAME, this)
				.value(NamingConventions.getPrimaryKeyConstraintName()).required().build();

		sequenceName = new LBJTextBoxBuilder(Labels.CREATE_TABLE_SEQUENCE_NAME, this)
				.value(NamingConventions.getSequenceName()).required().build();

		indexName = new LBJTextBoxBuilder(Labels.ADD_COLUMN_INDEX_NAME, this).value(NamingConventions.getIndexName())
				.required().build();

		foreignKeyName = new LBJTextBoxBuilder(Labels.ADD_COLUMN_FOREIGN_KEY_NAME, this)
				.value(NamingConventions.getForeignKeyName()).required().build();
	}

	@Override
	public void initializeButtons() {
		applyButton = new Button(Labels.BUTTON_PREFERENCES_APPLY, new Runnable() {
			@Override
			public void run() {
				if (!validate()) {
					return;
				}
				// apply and return to main menu
				applyPreferences();
				goToPreviousForm();
			}

		});

		resetToDefaultButton = new Button(Labels.BUTTON_PREFERENCES_RESET, new Runnable() {
			@Override
			public void run() {
				NamingConventions.setDefaultPreferences();
				setComponentsToPreferenceValues();
			}

		});

		backButton = new Button(Labels.BUTTON_BACK, new Runnable() {
			@Override
			public void run() {
				setComponentsToPreferenceValues();
				goToPreviousForm();
			}

		});
	}

	/**
	 * Will set all components to current preference value
	 */
	private void setComponentsToPreferenceValues() {
		primaryKeyName.setValue(NamingConventions.getPrimaryKeyName());
		primaryKeyConstraintName.setValue(NamingConventions.getPrimaryKeyConstraintName());
		sequenceName.setValue(NamingConventions.getSequenceName());
		indexName.setValue(NamingConventions.getIndexName());
		foreignKeyName.setValue(NamingConventions.getForeignKeyName());
	}

	private void applyPreferences() {
		LBJ.preferences.put(NamingConventions.PKEY_PRIMARY_KEY_NAME, primaryKeyName.getValue());
		LBJ.preferences.put(NamingConventions.PKEY_PRIMARY_KEY_CONSTRAINT_NAME, primaryKeyConstraintName.getValue());
		LBJ.preferences.put(NamingConventions.PKEY_SEQUENCE_NAME, sequenceName.getValue());
		LBJ.preferences.put(NamingConventions.PKEY_INDEX_NAME, indexName.getValue());
		LBJ.preferences.put(NamingConventions.PKEY_FOREIGN_KEY_NAME, foreignKeyName.getValue());
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
		LBJFormUtils.addComponentTo(this, primaryKeyName);
		LBJFormUtils.addComponentTo(this, primaryKeyConstraintName);
		LBJFormUtils.addComponentTo(this, sequenceName);
		LBJFormUtils.addComponentTo(this, indexName);
		LBJFormUtils.addComponentTo(this, foreignKeyName);
	}

	@Override
	public void addButtons() {
		LBJFormUtils.addEmptySpaceTo(this);
		LBJFormUtils.addButtonTo(this, backButton);
		LBJFormUtils.addButtonTo(this, resetToDefaultButton);
		LBJFormUtils.addButtonTo(this, applyButton);
	}

	@Override
	public String toString() {
		return Labels.PREFERENCES_FORM;
	}

	public LBJTextBox getPrimaryKeyName() {
		return primaryKeyName;
	}

	public void setPrimaryKeyName(LBJTextBox primaryKeyName) {
		this.primaryKeyName = primaryKeyName;
	}

	public LBJTextBox getPrimaryKeyConstraintName() {
		return primaryKeyConstraintName;
	}

	public void setPrimaryKeyConstraintName(LBJTextBox primaryKeyConstraintName) {
		this.primaryKeyConstraintName = primaryKeyConstraintName;
	}

	public LBJTextBox getSequenceName() {
		return sequenceName;
	}

	public void setSequenceName(LBJTextBox sequenceName) {
		this.sequenceName = sequenceName;
	}

	public LBJTextBox getIndexName() {
		return indexName;
	}

	public void setIndexName(LBJTextBox indexName) {
		this.indexName = indexName;
	}

	public LBJTextBox getForeignKeyName() {
		return foreignKeyName;
	}

	public void setForeignKeyName(LBJTextBox foreignKeyName) {
		this.foreignKeyName = foreignKeyName;
	}

	public Button getBackButton() {
		return backButton;
	}

	public void setBackButton(Button backButton) {
		this.backButton = backButton;
	}

	public Button getApplyButton() {
		return applyButton;
	}

	public void setApplyButton(Button applyButton) {
		this.applyButton = applyButton;
	}

	public Button getResetToDefaultButton() {
		return resetToDefaultButton;
	}

	public void setResetToDefaultButton(Button resetToDefaultButton) {
		this.resetToDefaultButton = resetToDefaultButton;
	}

}
