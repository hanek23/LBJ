package gui.forms.addforeignkeyconstraint;

import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.Window;

import constants.Labels;
import domain.AddColumn;
import domain.AddForeignKeyConstraint;
import domain.AddNotNullConstraint;
import domain.Column;
import domain.ColumnOperation;
import domain.ForeignKey;
import gui.attribute.Attribute;
import gui.builders.LBJTextBoxBuilder;
import gui.components.LBJTextBox;
import gui.forms.LBJEntityForm;
import gui.forms.LBJForm;
import gui.forms.generate.GenerateForm;
import gui.utils.LBJFormUtils;

/**
 * Form for adding foreign key constraint to column.
 */
public class AddForeignKeyConstraintForm extends LBJEntityForm<AddForeignKeyConstraint> {

	private LBJTextBox tableNameTextBox;
	private LBJTextBox columnNameTextBox;
	private LBJTextBox referencedTableNameTextBox;
	private LBJTextBox referencedColumnNameTextBox;
	private LBJTextBox foreignKeyNameTextBox;

	private Button generateButton;
	private Button addAnotherForeignKeyConstraintButton;

	public AddForeignKeyConstraintForm(Window window, LBJForm previousForm) {
		super(window, previousForm);
	}

	@Override
	public void initializeComponents() {
		tableNameTextBox = new LBJTextBoxBuilder(Attribute.TABLE_NAME, this).build();
		columnNameTextBox = new LBJTextBoxBuilder(Attribute.COLUMN_NAME, this).build();
		referencedTableNameTextBox = new LBJTextBoxBuilder(Attribute.REFERENCED_TABLE, this).build();
		referencedColumnNameTextBox = new LBJTextBoxBuilder(Attribute.REFERENCED_COLUMN, this).build();
		foreignKeyNameTextBox = new LBJTextBoxBuilder(Attribute.FOREIGN_KEY_NAME, this).build();
	}

	@Override
	public void initializeButtons() {
		addAnotherForeignKeyConstraintButton = LBJFormUtils.addAnotherForeignKeyConstraintButton(this);
		generateButton = LBJFormUtils.createGenerateButton(this);
	}

	@Override
	public void addFormUpdaters() {
		// no updaters
	}

	@Override
	public void addFormValidators() {
		// no validators
	}

	@Override
	public void addComponents() {
		LBJFormUtils.addValueAndLabeledComponentTo(this, tableNameTextBox);
		LBJFormUtils.addValueAndLabeledComponentTo(this, columnNameTextBox);
		LBJFormUtils.addValueAndLabeledComponentTo(this, referencedTableNameTextBox);
		LBJFormUtils.addValueAndLabeledComponentTo(this, referencedColumnNameTextBox);
		LBJFormUtils.addValueAndLabeledComponentTo(this, foreignKeyNameTextBox);
	}

	@Override
	public void addButtons() {
		initializeBackButton();
		LBJFormUtils.addButtonTo(this, addAnotherForeignKeyConstraintButton);
		LBJFormUtils.addButtonTo(this, generateButton);
	}

	@Override
	public AddForeignKeyConstraint convert() {
		AddForeignKeyConstraint column = new Column(columnNameTextBox.getValue(),
				ColumnOperation.ADD_FOREIGN_KEY_CONSTRAINT);
		column.setTableName(tableNameTextBox.getValue());
		ForeignKey key = new ForeignKey(foreignKeyNameTextBox.getValue());
		key.setReferencedColumn(referencedColumnNameTextBox.getValue());
		key.setReferencedTable(referencedTableNameTextBox.getValue());
		column.setForeignKey(key);
		return column;
	}

	@Override
	public String toString() {
		return Labels.ADD_FOREIGN_KEY_CONSTRAINT_FORM;
	}

	public LBJTextBox getTableNameTextBox() {
		return tableNameTextBox;
	}

	public LBJTextBox getColumnNameTextBox() {
		return columnNameTextBox;
	}

	public Button getGenerateButton() {
		return generateButton;
	}

	public LBJTextBox getReferencedTableNameTextBox() {
		return referencedTableNameTextBox;
	}

	public LBJTextBox getReferencedColumnNameTextBox() {
		return referencedColumnNameTextBox;
	}

	public LBJTextBox getForeignKeyNameTextBox() {
		return foreignKeyNameTextBox;
	}

	public Button getAddAnotherForeignKeyConstraintButton() {
		return addAnotherForeignKeyConstraintButton;
	}

}
