package gui.forms.dropnotnullconstraint;

import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.Window;

import constants.Labels;
import domain.Column;
import domain.ColumnOperation;
import domain.DropNotNullConstraint;
import gui.attribute.Attribute;
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
public class DropNotNullConstraintForm extends LBJEntityForm<DropNotNullConstraint> {

	private LBJTextBox tableNameTextBox;
	private LBJTextBox columnNameTextBox;
	private LBJTextBox dataTypeTextBox;

	private Button generateButton;

	public DropNotNullConstraintForm(Window window, LBJForm previousForm) {
		super(window, previousForm);
	}

	@Override
	public void initializeComponents() {
		tableNameTextBox = new LBJTextBoxBuilder(Attribute.TABLE_NAME, this).build();
		columnNameTextBox = new LBJTextBoxBuilder(Attribute.COLUMN_NAME, this).build();
		dataTypeTextBox = new LBJTextBoxBuilder(Attribute.DATA_TYPE, this).build();
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
		LBJFormUtils.addValueAndLabeledComponentTo(this, tableNameTextBox);
		LBJFormUtils.addValueAndLabeledComponentTo(this, columnNameTextBox);
		LBJFormUtils.addValueAndLabeledComponentTo(this, dataTypeTextBox);
	}

	@Override
	public void addButtons() {
		initializeBackButton();
		LBJFormUtils.addButtonTo(this, generateButton);
	}

	@Override
	public DropNotNullConstraint convert() {
		DropNotNullConstraint c = new Column(getColumnNameTextBox().getValue(),
				ColumnOperation.DROP_NOT_NULL_CONSTRAINT);
		c.setDataType(dataTypeTextBox.getValue());
		c.setTableName(tableNameTextBox.getValue());
		return c;
	}

	@Override
	public String toString() {
		return Labels.DROP_NOT_NULL_CONSTRAINT_FORM;
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

}
