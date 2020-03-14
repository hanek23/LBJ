package gui.forms.createtable;

import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.Button.Listener;
import com.googlecode.lanterna.gui2.EmptySpace;
import com.googlecode.lanterna.gui2.GridLayout;
import com.googlecode.lanterna.gui2.Window;

import constants.Labels;
import constants.NamingConventions;
import domain.Table;
import gui.LBJFormUtils;
import gui.builders.LBJCheckBoxBuilder;
import gui.builders.LBJPlainLabelBuilder;
import gui.builders.LBJTextBoxBuilder;
import gui.components.LBJCheckBox;
import gui.components.LBJPlainLabel;
import gui.components.LBJTextBox;
import gui.forms.LBJEntityForm;
import gui.forms.LBJForm;
import gui.updaters.LBJUpdaterSupplier;
import gui.validators.LBJValidatorSupplier;

public class LBJCreateTableForm extends LBJEntityForm<Table> {

	private LBJTextBox tableNameTextBox;
	private LBJTextBox primaryKeyNameTextBox;
	private LBJTextBox primaryKeyConstraintNameTextBox;
	private LBJPlainLabel databasesLabel;
	private LBJCheckBox oracleCheckBox;
	private LBJCheckBox mssqlCheckBox;
	private LBJCheckBox postgreCheckBox;
	private LBJTextBox sequenceNameTextBox;

	public LBJCreateTableForm(Window window, LBJForm previousForm) {
		super(window, previousForm);
	}

	@Override
	public void initializeComponents() {
		tableNameTextBox = new LBJTextBoxBuilder(Labels.TABLE_NAME, this)
				.addValidator(LBJValidatorSupplier.stringRequiredValidator)
				.addValidator(LBJValidatorSupplier.stringLengthValidator)
				.addValidator(LBJValidatorSupplier.caseValidator(NamingConventions.TABLE_NAME_CASE))
				.addUpdater(LBJUpdaterSupplier.caseUpdater(NamingConventions.TABLE_NAME_CASE)).build();
		primaryKeyNameTextBox = new LBJTextBoxBuilder(Labels.CREATE_TABLE_PRIMARY_KEY_NAME, this)
				.addValidator(LBJValidatorSupplier.stringRequiredValidator)
				.addValidator(LBJValidatorSupplier.stringLengthValidator)
				.addValidator(LBJValidatorSupplier.caseValidator(NamingConventions.PRIMARY_KEY_NAME_CASE))
				.addUpdater(LBJUpdaterSupplier.caseUpdater(NamingConventions.PRIMARY_KEY_NAME_CASE)).build();
		primaryKeyConstraintNameTextBox = new LBJTextBoxBuilder(Labels.CREATE_TABLE_PRIMARY_KEY_CONSTRAIN, this)
				.addValidator(LBJValidatorSupplier.stringRequiredValidator)
				.addValidator(LBJValidatorSupplier.stringLengthValidator)
				.addValidator(LBJValidatorSupplier.caseValidator(NamingConventions.PRIMARY_KEY_CONSTRAINT_NAME_CASE))
				.addUpdater(LBJUpdaterSupplier.caseUpdater(NamingConventions.PRIMARY_KEY_CONSTRAINT_NAME_CASE)).build();
		databasesLabel = new LBJPlainLabelBuilder(Labels.CREATE_TABLE_DATABASES, this).build();
		oracleCheckBox = new LBJCheckBoxBuilder(Labels.CREATE_TABLE_DATABASES_ORACLE, this).checked().build();
		mssqlCheckBox = new LBJCheckBoxBuilder(Labels.CREATE_TABLE_DATABASES_MSSQL, this).checked().build();
		postgreCheckBox = new LBJCheckBoxBuilder(Labels.CREATE_TABLE_DATABASES_POSTGRESQL, this).checked().build();
		sequenceNameTextBox = new LBJTextBoxBuilder(Labels.CREATE_TABLE_SEQUENCE_NAME, this)
				.addValidator(LBJValidatorSupplier.stringRequiredValidator)
				.addValidator(LBJValidatorSupplier.stringLengthValidator)
				.addValidator(LBJValidatorSupplier.caseValidator(NamingConventions.SEQUENCE_NAME_CASE))
				.addUpdater(LBJUpdaterSupplier.caseUpdater(NamingConventions.SEQUENCE_NAME_CASE)).build();
	}

	@Override
	public void addFormUpdaters() {
		addUpdater(new LBJCreateTableFormPrimaryKeyUpdater());
		addUpdater(new LBJCreateTableFormSequenceNameUpdater());
	}

	@Override
	public void addFormValidators() {
		addValidator(new LBJCreateTableFormDatabasesValidator());
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
		getContent().addComponent(new EmptySpace());
		LBJFormUtils.addDefaultBackButton(getContent(), getPreviousForm());
		Button addColumnButton = new Button(Labels.BUTTON_ADD_COLUMN);
		getContent().addComponent(addColumnButton.setLayoutData(GridLayout.createHorizontallyFilledLayoutData(1)));
		addColumnButton.addListener(new Listener() {

			@Override
			public void onTriggered(Button button) {
				validate();
			}
		});
	}

	@Override
	public Table convert() {
		return null;
	}

	@Override
	public String toString() {
		return Labels.TABLE_NAME_FORM;
	}

	public LBJTextBox getTableNameTextBox() {
		return tableNameTextBox;
	}

	public void setTableNameTextBox(LBJTextBox tableNameTextBox) {
		this.tableNameTextBox = tableNameTextBox;
	}

	public LBJTextBox getPrimaryKeyNameTextBox() {
		return primaryKeyNameTextBox;
	}

	public void setPrimaryKeyNameTextBox(LBJTextBox primaryKeyNameTextBox) {
		this.primaryKeyNameTextBox = primaryKeyNameTextBox;
	}

	public LBJTextBox getPrimaryKeyConstraintNameTextBox() {
		return primaryKeyConstraintNameTextBox;
	}

	public void setPrimaryKeyConstraintNameTextBox(LBJTextBox primaryKeyConstraintNameTextBox) {
		this.primaryKeyConstraintNameTextBox = primaryKeyConstraintNameTextBox;
	}

	public LBJPlainLabel getDatabasesLabel() {
		return databasesLabel;
	}

	public void setDatabasesLabel(LBJPlainLabel databasesLabel) {
		this.databasesLabel = databasesLabel;
	}

	public LBJCheckBox getOracleCheckBox() {
		return oracleCheckBox;
	}

	public void setOracleCheckBox(LBJCheckBox oracleCheckBox) {
		this.oracleCheckBox = oracleCheckBox;
	}

	public LBJCheckBox getMssqlCheckBox() {
		return mssqlCheckBox;
	}

	public void setMssqlCheckBox(LBJCheckBox mssqlCheckBox) {
		this.mssqlCheckBox = mssqlCheckBox;
	}

	public LBJCheckBox getPostgreCheckBox() {
		return postgreCheckBox;
	}

	public void setPostgreCheckBox(LBJCheckBox postgreCheckBox) {
		this.postgreCheckBox = postgreCheckBox;
	}

	public LBJTextBox getSequenceNameTextBox() {
		return sequenceNameTextBox;
	}

	public void setSequenceNameTextBox(LBJTextBox sequenceNameTextBox) {
		this.sequenceNameTextBox = sequenceNameTextBox;
	}

}
