package gui.forms;

import com.googlecode.lanterna.gui2.Window;

import constants.Labels;
import domain.Table;
import gui.components.LBJCheckBox;
import gui.components.LBJForm;
import gui.components.LBJPlainLabel;
import gui.components.LBJTextBox;

public class LBJCreateTableForm extends LBJForm<Table> {

	private LBJTextBox tableNameTextBox;
	private LBJTextBox primaryKeyNameTextBox;
	private LBJTextBox primeryKeyConstraintNameTextBox;
	private LBJPlainLabel databasesLabel;
	private LBJCheckBox oracleCheckBox;
	private LBJCheckBox mssqlCheckBox;
	private LBJCheckBox postgreCheckBox;
	private LBJTextBox sequenceNameTextBox;

	public LBJCreateTableForm(Window window, LBJForm<?> previousForm) {
		super(window, previousForm);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
	}

	@Override
	public void initializeComponents() {

	}

	@Override
	public void addButtonsToContent() {
		// TODO Auto-generated method stub

	}

	@Override
	public Table convert() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		return Labels.TABLE_NAME_FORM;
	}

}
