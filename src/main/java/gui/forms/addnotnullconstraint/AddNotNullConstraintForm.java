package gui.forms.addnotnullconstraint;

import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.Window;

import constants.Labels;
import domain.AddNotNullConstraint;
import domain.Column;
import domain.ColumnOperation;
import gui.attribute.Attribute;
import gui.builders.LBJComboBoxStringBuilder;
import gui.builders.LBJTextBoxBuilder;
import gui.components.LBJComboBoxString;
import gui.components.LBJTextBox;
import gui.forms.LBJEntityForm;
import gui.forms.LBJForm;
import gui.forms.generate.GenerateForm;
import gui.utils.LBJFormUtils;
import transformers.DataTypesLoader;

/**
 * Form for adding not null constraint to column. There are only 3
 * {@link LBJTextBox} all of which are required. They are table name, column
 * name and data type. Only next step is to go to {@link GenerateForm} as you
 * cannot, at the moment, add multiple not null constraints at once.
 */
public class AddNotNullConstraintForm extends LBJEntityForm<AddNotNullConstraint> {

	private LBJTextBox tableNameTextBox;
	private LBJTextBox columnNameTextBox;
	private LBJComboBoxString dataTypeComboBox;

	private Button generateButton;

	public AddNotNullConstraintForm(Window window, LBJForm previousForm) {
		super(window, previousForm);
	}

	@Override
	public void initializeComponents() {
		tableNameTextBox = new LBJTextBoxBuilder(Attribute.TABLE_NAME, this).build();
		columnNameTextBox = new LBJTextBoxBuilder(Attribute.COLUMN_NAME, this).build();
		LBJComboBoxStringBuilder dataTypesBuilder = new LBJComboBoxStringBuilder(Attribute.DATA_TYPE, this)
				.readOnly(false);
		DataTypesLoader.loadDataTypesValues(dataTypesBuilder);
		dataTypeComboBox = dataTypesBuilder.build();
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
		LBJFormUtils.addValueAndLabeledComponentTo(this, dataTypeComboBox);
	}

	@Override
	public void addButtons() {
		initializeBackButton();
		LBJFormUtils.addButtonTo(this, generateButton);
	}

	@Override
	public AddNotNullConstraint convert() {
		AddNotNullConstraint c = new Column(getColumnNameTextBox().getValue(), ColumnOperation.ADD_NOT_NULL_CONSTRAINT);
		c.setDataType(dataTypeComboBox.getValue());
		c.setTableName(tableNameTextBox.getValue());
		return c;
	}

	@Override
	public String toString() {
		return Labels.ADD_NOT_NULL_CONSTRAINT_FORM;
	}

	public LBJTextBox getTableNameTextBox() {
		return tableNameTextBox;
	}

	public LBJTextBox getColumnNameTextBox() {
		return columnNameTextBox;
	}

	public LBJComboBoxString getDataTypeComboBox() {
		return dataTypeComboBox;
	}

	public Button getGenerateButton() {
		return generateButton;
	}

}
