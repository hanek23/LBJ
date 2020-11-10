package gui.forms.createindex;

import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.Window;

import constants.Labels;
import domain.Column;
import domain.ColumnOperation;
import domain.CreateIndex;
import gui.attribute.Attribute;
import gui.builders.LBJTextBoxBuilder;
import gui.components.LBJTextBox;
import gui.forms.LBJEntityForm;
import gui.forms.LBJForm;
import gui.utils.LBJFormUtils;

public class CreateIndexForm extends LBJEntityForm<CreateIndex> {

	private LBJTextBox tableNameTextBox;
	private LBJTextBox columnNameTextBox;
	private LBJTextBox indexNameTextBox;

	private Button createIndexButton;
	private Button generateButton;

	public CreateIndexForm(Window window, LBJForm previousForm) {
		super(window, previousForm);
	}

	@Override
	public void initializeComponents() {
		tableNameTextBox = new LBJTextBoxBuilder(Attribute.TABLE_NAME, this).build();
		columnNameTextBox = new LBJTextBoxBuilder(Attribute.COLUMN_NAME, this).build();
		indexNameTextBox = new LBJTextBoxBuilder(Attribute.INDEX_NAME, this).build();
	}

	@Override
	public void initializeButtons() {
		createIndexButton = LBJFormUtils.createAnotherIndexButton(this);
		generateButton = LBJFormUtils.createGenerateButton(this);
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
		LBJFormUtils.addValueAndLabeledComponentTo(this, indexNameTextBox);
	}

	@Override
	public void addButtons() {
		initializeBackButton();
		LBJFormUtils.addButtonTo(this, createIndexButton);
		LBJFormUtils.addButtonTo(this, generateButton);
	}

	@Override
	public CreateIndex convert() {
		CreateIndex column = new Column(columnNameTextBox.getValue(), ColumnOperation.CREATE_INDEX);
		column.setTableName(tableNameTextBox.getValue());
		column.setIndexName(indexNameTextBox.getValue());
		return column;
	}

	@Override
	public String toString() {
		return Labels.CREATE_INDEX_FORM;
	}

	public LBJTextBox getTableNameTextBox() {
		return tableNameTextBox;
	}

	public LBJTextBox getColumnNameTextBox() {
		return columnNameTextBox;
	}

	public LBJTextBox getIndexNameTextBox() {
		return indexNameTextBox;
	}

	public Button getCreateIndexButton() {
		return createIndexButton;
	}

	public Button getGenerateButton() {
		return generateButton;
	}

}
