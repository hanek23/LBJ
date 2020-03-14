package gui;

import org.apache.commons.lang3.StringUtils;

import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.Button.Listener;
import com.googlecode.lanterna.gui2.CheckBox;
import com.googlecode.lanterna.gui2.GridLayout;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.TextBox;
import com.googlecode.lanterna.gui2.Window;

import constants.Labels;
import constants.NamingConventions;
import domain.Column;
import domain.ForeignKey;
import domain.Table;
import generator.Operation;

public class AddColumnForm implements Runnable, Updatable {

	private Window window;
	private Runnable previousWindow;
	private boolean initialized;
	private CheckBox foreignKeyCheckBox;
	private CheckBox nullableCheckBox;
	private CheckBox indexCheckBox;
	private TextBox referencedColumn;
	private TextBox referencedTable;
	private TextBox foreignKeyName;
	private TextBox dataType;
	private TextBox tableName;
	private TextBox columnName;
	private TextBox indexName;
	private Label dataTypeLabel;
	private Label tableNameLabel;
	private Label columnNameLabel;
	private Label referencedTableLabel;
	private Label referencedColumnLabel;
	private Label foreignKeyNameLabel;
	private Label indexNameLabel;
	private MainMenuForm mainMenu;
	private Column column;
	private Table table;
	private Panel content;
	private Operation operation;

	public AddColumnForm(MainMenuForm mainMenu, Window window, Runnable previousWindow, Operation operation) {
		this(mainMenu, window, previousWindow, Table.emptyTable(), operation);
	}

	public AddColumnForm(MainMenuForm mainMenu, Window window, Runnable previousWindow, Table table,
			Operation operation) {
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
		Column column = new Column(columnName.getText());
		handleIndex(column);
		handleForeignKey(column);
		column.setNullable(nullableCheckBox.isChecked());
		column.setDataType(dataType.getText());
		return column;
	}

	private void handleIndex(Column column) {
		if (indexCheckBox.isChecked()) {
			column.setIndex(true);
			column.setIndexName(indexName.getText());
		}
	}

	private void handleForeignKey(Column column) {
		if (foreignKeyCheckBox.isChecked()) {
			ForeignKey fk = new ForeignKey(foreignKeyName.getText());
			fk.setReferencedColumn(referencedColumn.getText());
			fk.setReferencedTable(referencedTable.getText());
			column.setForeignKey(fk);
		}
	}

	private boolean validate() {
		boolean toReturn = GuiValidator.validateTextBox(tableName, tableNameLabel);
		toReturn = GuiValidator.validateTextBox(columnName, columnNameLabel) && toReturn;
		if (indexCheckBox.isChecked()) {
			toReturn = GuiValidator.validateAll(indexName, indexNameLabel, NamingConventions.INDEX_NAME_DEFAULT_VALUE,
					NamingConventions.INDEX_NAME_DEFAULT_VALUE + NamingConventions.SEPARATOR) && toReturn;
		}
		if (foreignKeyCheckBox.isChecked()) {
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

	@Override
	public void update() {
		if (!initialized) {
			return;
		}
		if (indexCheckBox.isChecked()) {
			indexName.setEnabled(true);
			if (tableName.isFocused() || columnName.isFocused() || StringUtils.isEmpty(indexName.getText())) {
				indexName.setText(NamingConventions.INDEX_NAME_DEFAULT_VALUE + tableName.getText()
						+ NamingConventions.SEPARATOR + columnName.getText());
			}
		} else {
			indexName.setText("");
			indexName.setEnabled(false);
		}
		if (foreignKeyCheckBox.isChecked()) {
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
		LBJFormUtils.upperCase(tableName);
		LBJFormUtils.lowerCase(columnName);
		LBJFormUtils.upperCase(indexName);
		LBJFormUtils.upperCase(referencedTable);
		LBJFormUtils.lowerCase(referencedColumn);
		LBJFormUtils.upperCase(foreignKeyName);
	}

	private void initializeComponents() {
		content = LBJFormUtils.initializeDefaultContent();

		tableNameLabel = new Label(Labels.TABLE_NAME);
		tableName = new TextBox(table.getName());

		columnNameLabel = new Label(Labels.COLUMN_NAME);
		columnName = new TextBox();

		dataTypeLabel = new Label(Labels.COLUMN_DATA_TYPE);
		dataType = new TextBox();

		nullableCheckBox = new CheckBox();

		indexNameLabel = new Label(Labels.ADD_COLUMN_INDEX_NAME);
		indexName = new TextBox();
		indexName.setEnabled(false);

		indexCheckBox = new CheckBox();

		foreignKeyCheckBox = new CheckBox();

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
		LBJFormUtils.addLabelAndComponentToContent(tableNameLabel, tableName, content);
		LBJFormUtils.addLabelAndComponentToContent(columnNameLabel, columnName, content);
		LBJFormUtils.addLabelAndComponentToContent(dataTypeLabel, dataType, content);
		LBJFormUtils.addLabelAndComponentToContent(new Label(Labels.ADD_COLUMN_NULLABLE), nullableCheckBox, content);
		LBJFormUtils.addLabelAndComponentToContent(new Label(Labels.ADD_COLUMN_INDEX), indexCheckBox, content);
		LBJFormUtils.addLabelAndComponentToContent(indexNameLabel, indexName, content);
		LBJFormUtils.addLabelAndComponentToContent(new Label(Labels.ADD_COLUMN_FOREIGN_KEY), foreignKeyCheckBox,
				content);
		LBJFormUtils.addLabelAndComponentToContent(referencedTableLabel, referencedTable, content);
		LBJFormUtils.addLabelAndComponentToContent(referencedColumnLabel, referencedColumn, content);
		LBJFormUtils.addLabelAndComponentToContent(foreignKeyNameLabel, foreignKeyName, content);
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
				if (!AddColumnForm.this.validate()) {
					return;
				}
				table.removeColumn(column);
				table.setName(tableName.getText());
				column = AddColumnForm.this.createColumn();
				table.addColumn(column);
				AddColumnForm newAddColumn = new AddColumnForm(mainMenu, window, AddColumnForm.this, table, operation);
				mainMenu.addUpdatableChild(newAddColumn);
				newAddColumn.run();
			}

		});

		Button generateButton = new Button(Labels.BUTTON_GENERATE);
		content.addComponent(generateButton.setLayoutData(GridLayout.createHorizontallyFilledLayoutData(1)));
		generateButton.addListener(new Listener() {

			@Override
			public void onTriggered(Button button) {
				if (!AddColumnForm.this.validate()) {
					return;
				}
				table.setName(tableName.getText());
				table.addColumn(AddColumnForm.this.createColumn());
				GenerateForm generate = new GenerateForm(window, AddColumnForm.this, table, operation);
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
