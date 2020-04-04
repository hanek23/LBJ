package gui.forms.removenotnullconstraint;

import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.Window;

import constants.Labels;
import constants.NamingConventions;
import domain.Column;
import domain.ColumnOperation;
import domain.RemoveNotNullConstraint;
import gui.builders.LBJTextBoxBuilder;
import gui.components.LBJTextBox;
import gui.forms.LBJEntityForm;
import gui.forms.LBJForm;
import gui.forms.generate.GenerateForm;
import gui.utils.LBJFormUtils;

/**
 * Form for removing not null constraint from column. There are only 3
 * {@link LBJTextBox} all of which are required. They are table name, column
 * name and data type. Only next step is to go to {@link GenerateForm} as you
 * cannot, at the moment, remove multiple not null constraints at once.
 */
public class RemoveNotNullConstraintForm extends LBJEntityForm<RemoveNotNullConstraint> {

	private LBJTextBox tableNameTextBox;
	private LBJTextBox columnNameTextBox;
	private LBJTextBox dataTypeTextBox;

	private Button generateButton;

	public RemoveNotNullConstraintForm(Window window, LBJForm previousForm) {
		super(window, previousForm);
	}

	@Override
	public void initializeComponents() {
		tableNameTextBox = new LBJTextBoxBuilder(Labels.TABLE_NAME, this).required().addLengthValidator()
				.addCaseUpdaterAndValidator(NamingConventions.TABLE_NAME_CASE).build();
		columnNameTextBox = new LBJTextBoxBuilder(Labels.COLUMN_NAME, this).required().addLengthValidator()
				.addCaseUpdaterAndValidator(NamingConventions.COLUMN_NAME_CASE).build();
		dataTypeTextBox = new LBJTextBoxBuilder(Labels.COLUMN_DATA_TYPE, this).required()
				.addCaseUpdaterAndValidator(NamingConventions.DATA_TYPE_CASE).build();
	}

	@Override
	public void initializeButtons() {
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
		LBJFormUtils.addComponentTo(this, tableNameTextBox);
		LBJFormUtils.addComponentTo(this, columnNameTextBox);
		LBJFormUtils.addComponentTo(this, dataTypeTextBox);
	}

	@Override
	public void addButtons() {
		initializeBackButton();
		LBJFormUtils.addButtonTo(this, generateButton);
	}

	@Override
	public RemoveNotNullConstraint convert() {
		RemoveNotNullConstraint c = new Column(getColumnNameTextBox().getValue(),
				ColumnOperation.REMOVE_NOT_NULL_CONSTRAINT);
		c.setDataType(dataTypeTextBox.getValue());
		c.setTableName(tableNameTextBox.getValue());
		return c;
	}

	@Override
	public String toString() {
		return Labels.REMOVE_NOT_NULL_CONSTRAINT_FORM;
	}

	public LBJTextBox getTableNameTextBox() {
		return tableNameTextBox;
	}

	public LBJTextBox getColumnNameTextBox() {
		return columnNameTextBox;
	}

	public LBJTextBox getDataTypeTextBox() {
		return dataTypeTextBox;
	}

	public Button getGenerateButton() {
		return generateButton;
	}

	public void setGenerateButton(Button generateButton) {
		this.generateButton = generateButton;
	}

}
