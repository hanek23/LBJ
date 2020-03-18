package gui.forms.addcolumn;

import com.googlecode.lanterna.gui2.EmptySpace;
import com.googlecode.lanterna.gui2.Window;

import constants.Labels;
import constants.NamingConventions;
import domain.Column;
import gui.builders.LBJCheckBoxBuilder;
import gui.builders.LBJTextBoxBuilder;
import gui.components.LBJCheckBox;
import gui.components.LBJTextBox;
import gui.forms.LBJEntityForm;
import gui.forms.LBJForm;
import gui.utils.LBJFormUtils;

public class LBJAddColumnForm extends LBJEntityForm<Column> {

	private LBJTextBox tableNameTextBox;
	private LBJTextBox columnNameTextBox;
	private LBJTextBox dataTypeTextBox;
	private LBJCheckBox nullableCheckBox;
	private LBJCheckBox indexCheckBox;
	private LBJTextBox indexNameTextBox;
	private LBJCheckBox foreignKeyCheckBox;
	private LBJTextBox referencedTableNameTextBox;
	private LBJTextBox referencedColumnNameTextBox;
	private LBJTextBox foreignKeyNameTextBox;

	public LBJAddColumnForm(Window window, LBJForm previousForm) {
		super(window, previousForm);
	}

	@Override
	public void initializeComponents() {
		tableNameTextBox = new LBJTextBoxBuilder(Labels.TABLE_NAME, this).required().addLengthValidator()
				.addCaseUpdaterAndValidator(NamingConventions.TABLE_NAME_CASE).build();

		columnNameTextBox = new LBJTextBoxBuilder(Labels.COLUMN_NAME, this).required().addLengthValidator()
				.addCaseUpdaterAndValidator(NamingConventions.COLUMN_NAME_CASE).build();

		dataTypeTextBox = new LBJTextBoxBuilder(Labels.COLUMN_DATA_TYPE, this).required().build();

		nullableCheckBox = new LBJCheckBoxBuilder(Labels.ADD_COLUMN_NULLABLE, this).build();

		indexCheckBox = new LBJCheckBoxBuilder(Labels.ADD_COLUMN_INDEX, this).build();

		indexNameTextBox = new LBJTextBoxBuilder(Labels.COLUMN_DATA_TYPE, this).required().addLengthValidator()
				.addCaseUpdaterAndValidator(NamingConventions.INDEX_NAME_CASE).disabled().build();

		foreignKeyCheckBox = new LBJCheckBoxBuilder(Labels.ADD_COLUMN_FOREIGN_KEY, this).build();

		referencedTableNameTextBox = new LBJTextBoxBuilder(Labels.ADD_COLUMN_REFERENCED_TABLE, this).required()
				.addLengthValidator().addCaseUpdaterAndValidator(NamingConventions.TABLE_NAME_CASE).disabled().build();

		referencedColumnNameTextBox = new LBJTextBoxBuilder(Labels.ADD_COLUMN_REFERENCED_COLUMN, this).required()
				.addLengthValidator().addCaseUpdaterAndValidator(NamingConventions.COLUMN_NAME_CASE).disabled().build();

		foreignKeyNameTextBox = new LBJTextBoxBuilder(Labels.ADD_COLUMN_FOREIGN_KEY_NAME, this).required()
				.addLengthValidator().addCaseUpdaterAndValidator(NamingConventions.FOREIGN_KEY_NAME_CASE).disabled()
				.build();
	}

	@Override
	public void initializeContent() {
		setContent(LBJFormUtils.initializeDefaultContent());
	}

	@Override
	public void addFormUpdaters() {
		// TODO Auto-generated method stub

	}

	@Override
	public void addFormValidators() {
		// TODO Auto-generated method stub

	}

	@Override
	public void addComponentsToContent() {
		LBJFormUtils.addComponentToContent(getContent(), tableNameTextBox);
		LBJFormUtils.addComponentToContent(getContent(), columnNameTextBox);
		LBJFormUtils.addComponentToContent(getContent(), dataTypeTextBox);
		LBJFormUtils.addComponentToContent(getContent(), nullableCheckBox);
		LBJFormUtils.addComponentToContent(getContent(), indexCheckBox);
		LBJFormUtils.addComponentToContent(getContent(), indexNameTextBox);
		LBJFormUtils.addComponentToContent(getContent(), foreignKeyCheckBox);
		LBJFormUtils.addComponentToContent(getContent(), foreignKeyNameTextBox);
		LBJFormUtils.addComponentToContent(getContent(), referencedTableNameTextBox);
		LBJFormUtils.addComponentToContent(getContent(), referencedColumnNameTextBox);
		LBJFormUtils.addComponentToContent(getContent(), foreignKeyNameTextBox);
	}

	@Override
	public void addButtonsToContent() {
		getContent().addComponent(new EmptySpace());
		LBJFormUtils.addDefaultBackButton(getContent(), getPreviousForm());

	}

	@Override
	public Column convert() {
		return null;
	}

	@Override
	public String toString() {
		return Labels.ADD_COLUMN_FORM;
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

	public LBJCheckBox getNullableCheckBox() {
		return nullableCheckBox;
	}

	public LBJCheckBox getIndexCheckBox() {
		return indexCheckBox;
	}

	public LBJTextBox getIndexNameTextBox() {
		return indexNameTextBox;
	}

	public LBJCheckBox getForeignKeyCheckBox() {
		return foreignKeyCheckBox;
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

}
