package gui;

import org.apache.commons.lang3.StringUtils;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.CheckBox;
import com.googlecode.lanterna.gui2.EmptySpace;
import com.googlecode.lanterna.gui2.GridLayout;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.TextBox;
import com.googlecode.lanterna.gui2.Window;
import com.googlecode.lanterna.gui2.Button.Listener;

import constants.Labels;
import constants.NamingConventions;
import constants.Settings;
import domain.Column;
import domain.ForeignKey;
import domain.Table;
import generator.Generator;
import generator.Operation;

public class AddColumn implements Runnable, Updatable {

	private Window window;
	private Runnable previousWindow;
	private boolean initialized;
	private TextBox referencedColumn;
	private TextBox referencedTable;
	private TextBox foreignKeyName;
	private CheckBox foreignKey;
	private Label dataTypeLabel;
	private TextBox dataType;
	private Panel content;
	private Table table;
	private TextBox tableName;
	private TextBox columnName;
	private Label tableNameLabel;
	private Label columnNameLabel;
	private Label referencedTableLabel;
	private Label referencedColumnLabel;
	private Label foreignKeyNameLabel;
	private CheckBox index;
	private TextBox indexName;
	private Label indexNameLabel;
	private CheckBox nullable;
	private MainMenu mainMenu;
	private Column column;
	private Operation operation;

	public AddColumn(MainMenu mainMenu, Window window, Runnable previousWindow, Operation operation) {
		this(mainMenu, window, previousWindow, new Table(""), operation);
	}

	public AddColumn(MainMenu mainMenu, Window window, Runnable previousWindow, Table table, Operation operation) {
		this.mainMenu = mainMenu;
		this.window = window;
		this.previousWindow = previousWindow;
		this.table = table;
		this.operation = operation;
		initialize();
		if (operation == Operation.CREATE_TABLE) {
			tableName.setEnabled(false);
		}
	}

	private void initialize() {
		initializeComponents();
		addComponentsToContent();
		addButtonsToContent();
		initialized = true;
	}

	protected Column createColumn() {
		ForeignKey foreignKeyObject = createForeignKey();
		return new Column(columnName.getText(), indexName.getText(), foreignKeyObject, nullable.isChecked(),
				dataType.getText());
	}

	private ForeignKey createForeignKey() {
		if (foreignKey.isChecked()) {
			return new ForeignKey(foreignKeyName.getText(), referencedTable.getText(), referencedColumn.getText());
		}
		return null;
	}

	private boolean validate() {
		boolean toReturn = GuiValidator.validateTextBox(tableName, tableNameLabel);
		toReturn = GuiValidator.validateTextBox(columnName, columnNameLabel) && toReturn;
		if (index.isChecked()) {
			toReturn = GuiValidator.validateAll(indexName, indexNameLabel, NamingConventions.INDEX_NAME_DEFAULT_VALUE,
					NamingConventions.INDEX_NAME_DEFAULT_VALUE + NamingConventions.SEPARATOR) && toReturn;
		}
		if (foreignKey.isChecked()) {
			toReturn = GuiValidator.validateTextBox(referencedTable, referencedTableLabel) && toReturn;
			toReturn = GuiValidator.validateTextBox(referencedColumn, referencedColumnLabel) && toReturn;
			toReturn = GuiValidator.validateAll(foreignKeyName, foreignKeyNameLabel,
					NamingConventions.FOREIGN_KEY_NAME_DEFAULT_VALUE,
					NamingConventions.FOREIGN_KEY_NAME_DEFAULT_VALUE + NamingConventions.SEPARATOR) && toReturn;
		}
		toReturn = GuiValidator.validateTextBox(dataType, dataTypeLabel) && toReturn;

		return toReturn;
	}

	@Override
	public void run() {
		window.setComponent(content);
	}

	public void update() {
		if (!initialized) {
			return;
		}
		if (index.isChecked()) {
			indexName.setEnabled(true);
			if (tableName.isFocused() || columnName.isFocused() || StringUtils.isEmpty(indexName.getText())) {
				indexName.setText(NamingConventions.INDEX_NAME_DEFAULT_VALUE + tableName.getText()
						+ NamingConventions.SEPARATOR + columnName.getText());
			}
		} else {
			indexName.setText("");
			indexName.setEnabled(false);
		}
		if (foreignKey.isChecked()) {
			referencedTable.setEnabled(true);
			referencedColumn.setEnabled(true);
			foreignKeyName.setEnabled(true);
			if (referencedTable.isFocused() || referencedColumn.isFocused()) {
				foreignKeyName.setText(NamingConventions.FOREIGN_KEY_NAME_DEFAULT_VALUE + referencedTable.getText()
						+ NamingConventions.SEPARATOR + referencedColumn.getText());
			}
		} else {
			referencedTable.setEnabled(false);
			referencedColumn.setEnabled(false);
			foreignKeyName.setEnabled(false);
			referencedTable.setText("");
			referencedColumn.setText("");
			foreignKeyName.setText("");
		}
		changeCase();
	}

	private void changeCase() {
		GuiUtils.upperCase(tableName);
		GuiUtils.lowerCase(columnName);
		GuiUtils.upperCase(indexName);
		GuiUtils.upperCase(referencedTable);
		GuiUtils.lowerCase(referencedColumn);
		GuiUtils.upperCase(foreignKeyName);
	}

	private void initializeComponents() {
		content = GuiUtils.initializeDefaultContent();

		tableNameLabel = new Label(Labels.TABLE_NAME);
		tableName = new TextBox(table.getName());

		columnNameLabel = new Label(Labels.COLUMN_NAME);
		columnName = new TextBox();

		dataTypeLabel = new Label(Labels.COLUMN_DATA_TYPE);
		dataType = new TextBox();

		nullable = new CheckBox();

		indexNameLabel = new Label(Labels.ADD_COLUMN_INDEX_NAME);
		indexName = new TextBox();
		indexName.setEnabled(false);

		index = new CheckBox();

		foreignKey = new CheckBox();

		referencedTableLabel = new Label(Labels.ADD_COLUMN_REFERENCED_TABLE);
		referencedTable = new TextBox();
		referencedTable.setEnabled(false);

		referencedColumnLabel = new Label(Labels.ADD_COLUMN_REFERENCED_COLUMN);
		referencedColumn = new TextBox();
		referencedColumn.setEnabled(false);

		foreignKeyNameLabel = new Label(Labels.ADD_COLUMN_FOREIGN_KEY_NAME);
		foreignKeyName = new TextBox(NamingConventions.FOREIGN_KEY_NAME_DEFAULT_VALUE);
		foreignKeyName.setEnabled(false);
	}

	private void addComponentsToContent() {
		GuiUtils.addLabelAndComponentToContent(tableNameLabel, tableName, content);
		GuiUtils.addLabelAndComponentToContent(columnNameLabel, columnName, content);
		GuiUtils.addLabelAndComponentToContent(dataTypeLabel, dataType, content);
		GuiUtils.addLabelAndComponentToContent(new Label(Labels.ADD_COLUMN_NULLABLE), nullable, content);
		GuiUtils.addLabelAndComponentToContent(new Label(Labels.ADD_COLUMN_INDEX), index, content);
		GuiUtils.addLabelAndComponentToContent(new Label(Labels.ADD_COLUMN_INDEX_NAME), indexName, content);
		GuiUtils.addLabelAndComponentToContent(new Label(Labels.ADD_COLUMN_FOREIGN_KEY), foreignKey, content);
		GuiUtils.addLabelAndComponentToContent(referencedTableLabel, referencedTable, content);
		GuiUtils.addLabelAndComponentToContent(referencedColumnLabel, referencedColumn, content);
		GuiUtils.addLabelAndComponentToContent(foreignKeyNameLabel, foreignKeyName, content);
	}

	private void addButtonsToContent() {
		Button backButton = new Button(Labels.BUTTON_BACK);
		content.addComponent(backButton.setLayoutData(GridLayout.createHorizontallyFilledLayoutData(1)));
		backButton.addListener(new Listener() {

			@Override
			public void onTriggered(Button button) {
				table.removeColumn(column);
				previousWindow.run();
			}
		});

		Button addColumnButton = new Button(Labels.BUTTON_ADD_ANOTHER_COLUMN);
		content.addComponent(addColumnButton.setLayoutData(GridLayout.createHorizontallyFilledLayoutData(1)));
		addColumnButton.addListener(new Listener() {

			@Override
			public void onTriggered(Button button) {
				if (!AddColumn.this.validate()) {
					return;
				}
				table.removeColumn(column);
				table.setName(tableName.getText());
				column = AddColumn.this.createColumn();
				table.addColumn(column);
				AddColumn newAddColumn = new AddColumn(mainMenu, window, AddColumn.this, table, operation);
				mainMenu.addUpdatableChild(newAddColumn);
				newAddColumn.run();
			}

		});

		Button generateButton = new Button(Labels.BUTTON_GENERATE);
		content.addComponent(generateButton.setLayoutData(GridLayout.createHorizontallyFilledLayoutData(1)));
		generateButton.addListener(new Listener() {

			@Override
			public void onTriggered(Button button) {
				if (!AddColumn.this.validate()) {
					return;
				}
				table.setName(tableName.getText());
				table.addColumn(AddColumn.this.createColumn());
				Generate generate = new Generate(window, AddColumn.this, table, operation);
				generate.run();
			}
		});
	}

	public Window getWindow() {
		return window;
	}

	public void setWindow(Window window) {
		this.window = window;
	}

}
