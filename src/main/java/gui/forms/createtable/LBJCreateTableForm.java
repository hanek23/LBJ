package gui.forms.createtable;

import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.Button.Listener;
import com.googlecode.lanterna.gui2.Window;

import constants.Labels;
import constants.NamingConventions;
import domain.Table;
import gui.builders.LBJCheckBoxBuilder;
import gui.builders.LBJPlainLabelBuilder;
import gui.builders.LBJTextBoxBuilder;
import gui.components.LBJCheckBox;
import gui.components.LBJPlainLabel;
import gui.components.LBJTextBox;
import gui.forms.LBJEntityForm;
import gui.forms.LBJForm;
import gui.forms.addcolumn.LBJAddColumnForm;
import gui.suppliers.LBJFormSupplier;
import gui.suppliers.LBJUpdaterSupplier;
import gui.suppliers.LBJValidatorSupplier;
import gui.utils.LBJFormUtils;

/**
 * Form for creating table. You are able to choose table name, primary key name,
 * primary key constraint name, for which databses this table should be
 * (currently supported databases are Oracle, PostgreSQL and MSSQL) and sequence
 * name. As a next step you can go to {@link LBJAddColumnForm} in which you can
 * add more collumns.
 */
public class LBJCreateTableForm extends LBJEntityForm<Table> {

	private LBJTextBox tableNameTextBox;
	private LBJTextBox primaryKeyNameTextBox;
	private LBJTextBox primaryKeyConstraintNameTextBox;
	private LBJPlainLabel databasesLabel;
	private LBJCheckBox oracleCheckBox;
	private LBJCheckBox mssqlCheckBox;
	private LBJCheckBox postgreCheckBox;
	private LBJTextBox sequenceNameTextBox;

	private Button goToAddColumnButton;

	public LBJCreateTableForm(Window window, LBJForm previousForm) {
		super(window, previousForm);
	}

	@Override
	public void initializeContent() {
		setContent(LBJFormUtils.initializeDefaultContent());
	}

	@Override
	public void initializeComponents() {
		tableNameTextBox = new LBJTextBoxBuilder(Labels.TABLE_NAME, this).required().addLengthValidator()
				.addCaseUpdaterAndValidator(NamingConventions.TABLE_NAME_CASE).build();

		primaryKeyNameTextBox = new LBJTextBoxBuilder(Labels.CREATE_TABLE_PRIMARY_KEY_NAME, this).required()
				.addLengthValidator().addCaseUpdaterAndValidator(NamingConventions.PRIMARY_KEY_NAME_CASE).build();

		primaryKeyConstraintNameTextBox = new LBJTextBoxBuilder(Labels.CREATE_TABLE_PRIMARY_KEY_CONSTRAIN, this)
				.required().addLengthValidator()
				.addCaseUpdaterAndValidator(NamingConventions.PRIMARY_KEY_CONSTRAINT_NAME_CASE).build();

		databasesLabel = new LBJPlainLabelBuilder(Labels.CREATE_TABLE_DATABASES, this).build();
		oracleCheckBox = new LBJCheckBoxBuilder(Labels.CREATE_TABLE_DATABASES_ORACLE, this).checked().build();
		mssqlCheckBox = new LBJCheckBoxBuilder(Labels.CREATE_TABLE_DATABASES_MSSQL, this).checked().build();
		postgreCheckBox = new LBJCheckBoxBuilder(Labels.CREATE_TABLE_DATABASES_POSTGRESQL, this).checked().build();

		sequenceNameTextBox = new LBJTextBoxBuilder(Labels.CREATE_TABLE_SEQUENCE_NAME, this).required()
				.addLengthValidator().addCaseUpdaterAndValidator(NamingConventions.SEQUENCE_NAME_CASE).build();

		goToAddColumnButton = new Button(Labels.BUTTON_ADD_COLUMN);
		goToAddColumnButton.addListener(new Listener() {
			@Override
			public void onTriggered(Button button) {
				if (validate()) {
					if (getNextForm() == null) {
						setNextForm(LBJFormSupplier.getAddColumnForm(LBJCreateTableForm.this.getWindow(),
								LBJCreateTableForm.this));
					}
					goToNextForm();
				}

			}
		});
	}

	@Override
	public void addFormUpdaters() {
		addUpdater(LBJUpdaterSupplier.getCreateTablePrimaryKeyUpdater());
		addUpdater(LBJUpdaterSupplier.getCreateTableSequenceNameUpdater());
	}

	@Override
	public void addFormValidators() {
		addValidator(LBJValidatorSupplier.getCreateTableDatabasesValidator());
	}

	@Override
	public void addComponentsToContent() {
		LBJFormUtils.addComponentToContent(getContent(), tableNameTextBox);
		LBJFormUtils.addComponentToContent(getContent(), primaryKeyNameTextBox);
		LBJFormUtils.addComponentToContent(getContent(), primaryKeyConstraintNameTextBox);
		LBJFormUtils.addComponentToContent(getContent(), databasesLabel);
		LBJFormUtils.addComponentToContent(getContent(), oracleCheckBox);
		LBJFormUtils.addComponentToContent(getContent(), mssqlCheckBox);
		LBJFormUtils.addComponentToContent(getContent(), postgreCheckBox);
		LBJFormUtils.addComponentToContent(getContent(), sequenceNameTextBox);
	}

	@Override
	public void addButtonsToContent() {
		LBJFormUtils.addEmptySpace(getContent());
		LBJFormUtils.addBackButton(getContent(), getPreviousForm());
		LBJFormUtils.addButtonToConent(getContent(), goToAddColumnButton);
	}

	@Override
	public Table convert() {
		return null;
	}

	@Override
	public String toString() {
		return Labels.TABLE_FORM;
	}

	public LBJTextBox getTableNameTextBox() {
		return tableNameTextBox;
	}

	public LBJTextBox getPrimaryKeyNameTextBox() {
		return primaryKeyNameTextBox;
	}

	public LBJTextBox getPrimaryKeyConstraintNameTextBox() {
		return primaryKeyConstraintNameTextBox;
	}

	public LBJPlainLabel getDatabasesLabel() {
		return databasesLabel;
	}

	public LBJCheckBox getOracleCheckBox() {
		return oracleCheckBox;
	}

	public LBJCheckBox getMssqlCheckBox() {
		return mssqlCheckBox;
	}

	public LBJCheckBox getPostgreCheckBox() {
		return postgreCheckBox;
	}

	public LBJTextBox getSequenceNameTextBox() {
		return sequenceNameTextBox;
	}

}
