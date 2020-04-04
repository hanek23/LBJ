package gui.forms.createtable;

import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.Window;

import constants.Labels;
import constants.NamingConventions;
import domain.Table;
import gui.builders.LBJTextBoxBuilder;
import gui.components.LBJTextBox;
import gui.forms.LBJEntityForm;
import gui.forms.LBJForm;
import gui.forms.addcolumn.AddColumnForm;
import gui.utils.LBJFormUtils;

/**
 * Form for creating table. You can choose table name, primary key name, primary
 * key constraint name and sequence name. As a next step you can go to
 * {@link AddColumnForm} in which you can add more columns.
 */
public class CreateTableForm extends LBJEntityForm<Table> {

	private LBJTextBox tableNameTextBox;
	private LBJTextBox primaryKeyNameTextBox;
	private LBJTextBox primaryKeyConstraintNameTextBox;
	private LBJTextBox sequenceNameTextBox;

	private Button addColumnButton;

	public CreateTableForm(Window window, LBJForm previousForm) {
		super(window, previousForm);
	}

	@Override
	public void initializeComponents() {
		tableNameTextBox = new LBJTextBoxBuilder(Labels.TABLE_NAME, this).required().addLengthValidator()
				.addCaseUpdaterAndValidator(NamingConventions.TABLE_NAME_CASE).build();

		primaryKeyNameTextBox = new LBJTextBoxBuilder(Labels.CREATE_TABLE_PRIMARY_KEY_NAME, this).required()
				.addNamingConventionUpdater(NamingConventions.getPrimaryKeyName())
				.addCaseUpdaterAndValidator(NamingConventions.PRIMARY_KEY_NAME_CASE).addLengthValidator().build();

		primaryKeyConstraintNameTextBox = new LBJTextBoxBuilder(Labels.CREATE_TABLE_PRIMARY_KEY_CONSTRAIN_NAME, this)
				.required().addLengthValidator()
				.addNamingConventionUpdater(NamingConventions.getPrimaryKeyConstraintName())
				.addCaseUpdaterAndValidator(NamingConventions.PRIMARY_KEY_CONSTRAINT_NAME_CASE).build();

		sequenceNameTextBox = new LBJTextBoxBuilder(Labels.CREATE_TABLE_SEQUENCE_NAME, this).required()
				.addCaseUpdaterAndValidator(NamingConventions.SEQUENCE_NAME_CASE).addLengthValidator()
				.addNamingConventionUpdater(NamingConventions.getSequenceName()).build();

	}

	@Override
	public void initializeButtons() {
		addColumnButton = LBJFormUtils.createAddColumnButton(this);
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
		LBJFormUtils.addComponentTo(this, tableNameTextBox);
		LBJFormUtils.addComponentTo(this, primaryKeyNameTextBox);
		LBJFormUtils.addComponentTo(this, primaryKeyConstraintNameTextBox);
		LBJFormUtils.addComponentTo(this, sequenceNameTextBox);
	}

	@Override
	public void addButtons() {
		initializeBackButton();
		LBJFormUtils.addButtonTo(this, addColumnButton);
	}

	@Override
	public Table convert() {
		Table table = new Table(tableNameTextBox.getValue());
		table.setPrimaryKeyColumnName(primaryKeyNameTextBox.getValue());
		table.setPrimaryKeyContrainName(primaryKeyConstraintNameTextBox.getValue());
		table.setSequenceName(sequenceNameTextBox.getValue());
		return table;
	}

	@Override
	public void run() {
		applyPreferences();
		super.run();
	}

	/**
	 * Applies potential new Naming convention as preferences could have been
	 * changed since last visit
	 */
	private void applyPreferences() {
		primaryKeyNameTextBox.setNamingConvention(NamingConventions.getPrimaryKeyName());
		primaryKeyConstraintNameTextBox.setNamingConvention(NamingConventions.getPrimaryKeyConstraintName());
		sequenceNameTextBox.setNamingConvention(NamingConventions.getSequenceName());
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

	public LBJTextBox getSequenceNameTextBox() {
		return sequenceNameTextBox;
	}

	public Button getAddColumnButton() {
		return addColumnButton;
	}

	public void setAddColumnButton(Button addColumnButton) {
		this.addColumnButton = addColumnButton;
	}

}
