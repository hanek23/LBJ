package gui.forms.removenotnullconstraint;

import com.googlecode.lanterna.gui2.Window;

import constants.Labels;
import constants.NamingConventions;
import domain.Column;
import gui.builders.LBJTextBoxBuilder;
import gui.components.LBJTextBox;
import gui.forms.LBJEntityForm;
import gui.forms.LBJForm;
import gui.utils.LBJFormUtils;

public class RemoveNotNullConstraintForm extends LBJEntityForm<Column> {

	private LBJTextBox tableNameTextBox;
	private LBJTextBox columnNameTextBox;
	private LBJTextBox dataTypeTextBox;

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
	public void addFormUpdaters() {
		// no updaters
	}

	@Override
	public void addFormValidators() {
		// no validators
	}

	@Override
	public void addComponentsToContent() {
		LBJFormUtils.addComponentToContent(getContent(), tableNameTextBox);
		LBJFormUtils.addComponentToContent(getContent(), columnNameTextBox);
		LBJFormUtils.addComponentToContent(getContent(), dataTypeTextBox);
	}

	@Override
	public void addButtonsToContent() {
		LBJFormUtils.addEmptySpace(getContent());
		LBJFormUtils.addBackButton(getContent(), getPreviousForm());
		LBJFormUtils.addGenerateButton(this);
	}

	@Override
	public Column convert() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		return Labels.MAIN_MENU_REMOVE_NOT_NULL_CONSTRAINT;
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

}
