package gui.forms.createtable;

import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.Window;

import constants.Labels;
import domain.CreateTable;
import domain.Table;
import domain.TableOperation;
import gui.attribute.Attribute;
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
public class CreateTableForm extends LBJEntityForm<CreateTable> {

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
		tableNameTextBox = new LBJTextBoxBuilder(Attribute.TABLE_NAME, this).build();
		primaryKeyNameTextBox = new LBJTextBoxBuilder(Attribute.PRIMARY_KEY_NAME, this).build();
		primaryKeyConstraintNameTextBox = new LBJTextBoxBuilder(Attribute.PRIMARY_KEY_CONSTRAINT_NAME, this).build();
		sequenceNameTextBox = new LBJTextBoxBuilder(Attribute.SEQUENCE_NAME, this).build();
	}

	@Override
	public void initializeButtons() {
		addColumnButton = LBJFormUtils.createAddColumnButton(this, true);
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
		LBJFormUtils.addValueAndLabeledComponentTo(this, tableNameTextBox);
		LBJFormUtils.addValueAndLabeledComponentTo(this, primaryKeyNameTextBox);
		LBJFormUtils.addValueAndLabeledComponentTo(this, primaryKeyConstraintNameTextBox);
		LBJFormUtils.addValueAndLabeledComponentTo(this, sequenceNameTextBox);
	}

	@Override
	public void addButtons() {
		initializeBackButton();
		LBJFormUtils.addButtonTo(this, addColumnButton);
	}

	@Override
	public CreateTable convert() {
		CreateTable table = new Table(tableNameTextBox.getValue(), TableOperation.CREATE);
		table.setPrimaryKeyColumnName(primaryKeyNameTextBox.getValue());
		table.setPrimaryKeyContrainName(primaryKeyConstraintNameTextBox.getValue());
		table.setSequenceName(sequenceNameTextBox.getValue());
		return table;
	}

	@Override
	public String toString() {
		return Labels.CREATE_TABLE_FORM;
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

}
