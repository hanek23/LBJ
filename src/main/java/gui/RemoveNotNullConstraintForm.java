package gui;

import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.EmptySpace;
import com.googlecode.lanterna.gui2.GridLayout;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.TextBox;
import com.googlecode.lanterna.gui2.Window;
import com.googlecode.lanterna.gui2.Button.Listener;

import constants.Labels;
import domain.Column;
import domain.Table;
import generator.Operation;
import gui.utils.LBJFormUtils;

public class RemoveNotNullConstraintForm implements Runnable, Updatable {

	private Window window;
	private Runnable previousWindow;
	private Panel content;
	private boolean initialized;
	private Label tableNameLabel;
	private TextBox tableName;
	private Label columnNameLabel;
	private TextBox columnName;
	private Label columnDataTypeLabel;
	private TextBox columnDataType;

	public RemoveNotNullConstraintForm(Window window, Runnable previousWindow) {
		this.window = window;
		this.previousWindow = previousWindow;
		initialize();
	}

	private void initialize() {
		initializeComponents();
		addComponentsToContent();
		addButtonsToContent();
		initialized = true;
	}

	private void initializeComponents() {
		content = LBJFormUtils.initializeDefaultContent();

		tableNameLabel = new Label(Labels.TABLE_NAME);
		tableName = new TextBox();

		columnNameLabel = new Label(Labels.COLUMN_NAME);
		columnName = new TextBox();

		columnDataTypeLabel = new Label(Labels.COLUMN_DATA_TYPE);
		columnDataType = new TextBox();
	}

	private void addComponentsToContent() {
		LBJFormUtils.addLabelAndComponentToContent(tableNameLabel, tableName, content);
		LBJFormUtils.addLabelAndComponentToContent(columnNameLabel, columnName, content);
		LBJFormUtils.addLabelAndComponentToContent(columnDataTypeLabel, columnDataType, content);
	}

	private void addButtonsToContent() {
		content.addComponent(new EmptySpace());
		LBJFormUtils.addDefaultBackButton(content, previousWindow);

		Button generateButton = new Button(Labels.BUTTON_GENERATE);
		content.addComponent(generateButton.setLayoutData(GridLayout.createHorizontallyFilledLayoutData(1)));
		generateButton.addListener(new Listener() {

			@Override
			public void onTriggered(Button button) {
				if (!RemoveNotNullConstraintForm.this.validate()) {
					return;
				}
				Table table = RemoveNotNullConstraintForm.this.createTable();
				GenerateForm generate = new GenerateForm(window, RemoveNotNullConstraintForm.this, table,
						Operation.REMOVE_NOT_NULL_CONSTRAINT);
				generate.run();
			}
		});
	}

	private Table createTable() {
		Table table = new Table(tableName.getText());
		table.addColumn(createColumn());
		return table;
	}

	private Column createColumn() {
		Column column = new Column(columnName.getText());
		column.setDataType(columnDataType.getText());
		return column;
	}

	private boolean validate() {
		boolean toReturn = GuiValidator.validateTextBox(tableName, tableNameLabel);
		toReturn = GuiValidator.validateTextBox(columnName, columnNameLabel) && toReturn;
		toReturn = GuiValidator.validateTextBox(columnDataType, columnDataTypeLabel) && toReturn;
		return toReturn;
	}

	@Override
	public void update() {
		if (!initialized) {
			return;
		}
		changeCase();
	}

	private void changeCase() {
		LBJFormUtils.upperCase(tableName);
		LBJFormUtils.lowerCase(columnName);
	}

	@Override
	public void run() {
		window.setComponent(content);
	}

}
