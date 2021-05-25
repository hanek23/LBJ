package gui.forms.dropforeignkeyconstraint;

import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.Window;

import constants.Labels;
import domain.Column;
import domain.ColumnOperation;
import domain.DropForeignKeyConstraint;
import domain.ForeignKey;
import gui.attribute.Attribute;
import gui.builders.LBJTextBoxBuilder;
import gui.components.LBJTextBox;
import gui.forms.LBJEntityForm;
import gui.forms.LBJForm;
import gui.utils.LBJFormUtils;

/**
 * Form for dropping foreign key constraint from column.
 */
public class DropForeignKeyConstraintForm extends LBJEntityForm<DropForeignKeyConstraint> {

	private LBJTextBox tableNameTextBox;
	private LBJTextBox foreignKeyNameTextBox;

	private Button generateButton;
	private Button dropAnotherForeignKeyConstraintButton;

	public DropForeignKeyConstraintForm(Window window, LBJForm previousForm) {
		super(window, previousForm);
	}

	@Override
	public void initializeComponents() {
		tableNameTextBox = new LBJTextBoxBuilder(Attribute.TABLE_NAME, this).build();
		// no naming conventions for dropping 
		foreignKeyNameTextBox = new LBJTextBoxBuilder(Attribute.FOREIGN_KEY_NAME, this).clearNamingConvention().build();
	}

	@Override
	public void initializeButtons() {
		dropAnotherForeignKeyConstraintButton = LBJFormUtils.dropAnotherForeignKeyConstraintButton(this);
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
		LBJFormUtils.addValueAndLabeledComponentTo(this, foreignKeyNameTextBox);
	}

	@Override
	public void addButtons() {
		initializeBackButton();
		LBJFormUtils.addButtonTo(this, dropAnotherForeignKeyConstraintButton);
		LBJFormUtils.addButtonTo(this, generateButton);
	}

	@Override
	public DropForeignKeyConstraint convert() {
		DropForeignKeyConstraint column = new Column(null, ColumnOperation.DROP_FOREIGN_KEY_CONSTRAINT);
		column.setTableName(tableNameTextBox.getValue());
		ForeignKey key = new ForeignKey(foreignKeyNameTextBox.getValue());
		column.setForeignKey(key);
		return column;
	}

	@Override
	public String toString() {
		return Labels.DROP_FOREIGN_KEY_CONSTRAINT_FORM;
	}

	public LBJTextBox getTableNameTextBox() {
		return tableNameTextBox;
	}

	public Button getGenerateButton() {
		return generateButton;
	}

	public LBJTextBox getForeignKeyNameTextBox() {
		return foreignKeyNameTextBox;
	}

	public Button getDropAnotherForeignKeyConstraintButton() {
		return dropAnotherForeignKeyConstraintButton;
	}

}
