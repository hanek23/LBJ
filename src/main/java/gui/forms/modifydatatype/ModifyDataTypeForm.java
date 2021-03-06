package gui.forms.modifydatatype;

import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.Window;

import constants.Labels;
import domain.Column;
import domain.ColumnOperation;
import domain.ModifyDataType;
import gui.attribute.Attribute;
import gui.builders.LBJTextBoxBuilder;
import gui.components.LBJTextBox;
import gui.forms.LBJEntityForm;
import gui.forms.LBJForm;
import gui.utils.LBJFormUtils;

public class ModifyDataTypeForm extends LBJEntityForm<ModifyDataType> {

	private LBJTextBox tableNameTextBox;
	private LBJTextBox columnNameTextBox;
	private LBJTextBox dataTypeTextBox;

	private Button modifyAnotherColumnButton;
	private Button generateButton;

	public ModifyDataTypeForm(Window window, LBJForm previousForm) {
		super(window, previousForm);
	}

	@Override
	public void initializeComponents() {
		tableNameTextBox = new LBJTextBoxBuilder(Attribute.TABLE_NAME, this).build();
		setTableNameIfPossible();
		columnNameTextBox = new LBJTextBoxBuilder(Attribute.COLUMN_NAME, this).build();
		dataTypeTextBox = new LBJTextBoxBuilder(Attribute.DATA_TYPE, this).build();

	}

	/**
	 * If previousForm has Table name textBox, this method will copy that value into
	 * this form's table name
	 */
	private void setTableNameIfPossible() {
		if (getPreviousForm() instanceof ModifyDataTypeForm) {
			tableNameTextBox.setValue(((ModifyDataTypeForm) getPreviousForm()).getTableNameTextBox().getValue());
		}
	}

	@Override
	public void initializeButtons() {
		generateButton = LBJFormUtils.createGenerateButton(this);
		modifyAnotherColumnButton = LBJFormUtils.createModifyAnotherColumnButton(this);
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
		LBJFormUtils.addValueAndLabeledComponentTo(this, columnNameTextBox);
		LBJFormUtils.addValueAndLabeledComponentTo(this, dataTypeTextBox);
	}

	@Override
	public void addButtons() {
		initializeBackButton();
		LBJFormUtils.addButtonTo(this, modifyAnotherColumnButton);
		LBJFormUtils.addButtonTo(this, generateButton);
	}

	@Override
	public ModifyDataType convert() {
		ModifyDataType column = new Column(columnNameTextBox.getValue(), ColumnOperation.MODIFY_DATA_TYPE);
		column.setTableName(tableNameTextBox.getValue());
		column.setDataType(dataTypeTextBox.getValue());
		return column;
	}

	@Override
	public String toString() {
		return Labels.MODIFY_DATA_TYPE_FORM;
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

	public Button getModifyDataTypeButton() {
		return modifyAnotherColumnButton;
	}

	public void setModifyDataTypeButton(Button modifyDataTypeButton) {
		this.modifyAnotherColumnButton = modifyDataTypeButton;
	}

}
