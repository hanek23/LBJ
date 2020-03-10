package gui.forms;

import com.googlecode.lanterna.gui2.Window;

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

	public LBJCreateTableForm(String name, Window window, LBJForm<?> previousForm) {
		super(name, window, previousForm);
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

}
