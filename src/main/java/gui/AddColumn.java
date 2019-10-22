package gui;

import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import org.apache.commons.lang3.StringUtils;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.Theme;
import com.googlecode.lanterna.graphics.ThemeDefinition;
import com.googlecode.lanterna.gui2.AbstractComponent;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.CheckBox;
import com.googlecode.lanterna.gui2.ComboBox;
import com.googlecode.lanterna.gui2.Direction;
import com.googlecode.lanterna.gui2.EmptySpace;
import com.googlecode.lanterna.gui2.GridLayout;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.Separator;
import com.googlecode.lanterna.gui2.TextBox;
import com.googlecode.lanterna.gui2.Window;
import com.googlecode.lanterna.gui2.WindowDecorationRenderer;
import com.googlecode.lanterna.gui2.WindowPostRenderer;
import com.googlecode.lanterna.gui2.Button.Listener;

import constants.Labels;
import constants.NamingConventions;
import constants.Settings;
import domain.Column;
import domain.DataType;
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
	private TextBox additionalInfo;
	private ComboBox<DataType> typesComboBox;
	private Panel content;
	private Table table;
	private TextBox tableName;
	private TextBox columnName;
	private Label tableNameLabel;
	private Label columnNameLabel;
	private Label referencedTableLabel;
	private Label referencedColumnLabel;
	private Label foreignKeyNameLabel;
	private Label additionalInfoLabel;
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
		content = new Panel(new GridLayout(Settings.GUI_NUMBER_OF_COLUMNS));
		GridLayout gridLayout = (GridLayout) content.getLayoutManager();
		gridLayout.setVerticalSpacing(Settings.GUI_VERTICAL_SPACING);

		tableNameLabel = new Label(Labels.CREATE_TABLE_TABLE_NAME);
		content.addComponent(tableNameLabel);
		tableName = new TextBox(table.getName());
		content.addComponent(tableName
				.setLayoutData(GridLayout.createHorizontallyFilledLayoutData(Settings.GUI_NUMBER_OF_COLUMNS - 1)));

		columnNameLabel = new Label(Labels.ADD_COLUMN_NAME);
		content.addComponent(columnNameLabel);
		columnName = new TextBox();
		content.addComponent(columnName
				.setLayoutData(GridLayout.createHorizontallyFilledLayoutData(Settings.GUI_NUMBER_OF_COLUMNS - 1)));

		content.addComponent(new Label(Labels.ADD_COLUMN_NULLABLE));
		nullable = new CheckBox();
		content.addComponent(nullable
				.setLayoutData(GridLayout.createHorizontallyFilledLayoutData(Settings.GUI_NUMBER_OF_COLUMNS - 1)));

		content.addComponent(new Label(Labels.ADD_COLUMN_INDEX));
		index = new CheckBox();
		content.addComponent(
				index.setLayoutData(GridLayout.createHorizontallyFilledLayoutData(Settings.GUI_NUMBER_OF_COLUMNS - 1)));

		indexNameLabel = new Label(Labels.ADD_COLUMN_INDEX_NAME);
		content.addComponent(indexNameLabel);
		indexName = new TextBox();
		indexName.setEnabled(false);
		content.addComponent(indexName
				.setLayoutData(GridLayout.createHorizontallyFilledLayoutData(Settings.GUI_NUMBER_OF_COLUMNS - 1)));

		content.addComponent(new Label(Labels.ADD_COLUMN_FOREIGN_KEY));
		foreignKey = new CheckBox();
		content.addComponent(foreignKey
				.setLayoutData(GridLayout.createHorizontallyFilledLayoutData(Settings.GUI_NUMBER_OF_COLUMNS - 1)));

		referencedTableLabel = new Label(Labels.ADD_COLUMN_REFERENCED_TABLE);
		content.addComponent(referencedTableLabel);
		referencedTable = new TextBox();
		referencedTable.setEnabled(false);
		content.addComponent(referencedTable
				.setLayoutData(GridLayout.createHorizontallyFilledLayoutData(Settings.GUI_NUMBER_OF_COLUMNS - 1)));

		referencedColumnLabel = new Label(Labels.ADD_COLUMN_REFERENCED_COLUMN);
		content.addComponent(referencedColumnLabel);
		referencedColumn = new TextBox();
		referencedColumn.setEnabled(false);
		content.addComponent(referencedColumn
				.setLayoutData(GridLayout.createHorizontallyFilledLayoutData(Settings.GUI_NUMBER_OF_COLUMNS - 1)));

		foreignKeyNameLabel = new Label(Labels.ADD_COLUMN_FOREIGN_KEY_NAME);
		content.addComponent(foreignKeyNameLabel);
		foreignKeyName = new TextBox(NamingConventions.FOREIGN_KEY_NAME_DEFAULT_VALUE);
		foreignKeyName.setEnabled(false);
		content.addComponent(foreignKeyName
				.setLayoutData(GridLayout.createHorizontallyFilledLayoutData(Settings.GUI_NUMBER_OF_COLUMNS - 1)));

		content.addComponent(new Label(Labels.ADD_COLUMN_TYPE));
		typesComboBox = new ComboBox<>(DataType.values());
		typesComboBox.setReadOnly(true);
		content.addComponent(typesComboBox
				.setLayoutData(GridLayout.createHorizontallyFilledLayoutData(Settings.GUI_NUMBER_OF_COLUMNS - 1)));

		additionalInfoLabel = new Label(Labels.ADD_COLUMN_ADDITIONAL_INFO);
		content.addComponent(additionalInfoLabel);
		additionalInfo = new TextBox();
		content.addComponent(additionalInfo
				.setLayoutData(GridLayout.createHorizontallyFilledLayoutData(Settings.GUI_NUMBER_OF_COLUMNS - 1)));

		content.addComponent(new EmptySpace());
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
				Generator.generate(table, operation);
			}
		});

		initialized = true;
	}

	protected Column createColumn() {
		ForeignKey foreignKeyObject = new ForeignKey(foreignKeyName.getText(), referencedTable.getText(),
				referencedColumn.getText());
		return new Column(columnName.getText(), indexName.getText(), foreignKeyObject, nullable.isChecked(),
				typesComboBox.getSelectedItem());
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
		if (typesComboBox.getSelectedItem().isAdditionalInfoRequired()) {
			toReturn = GuiValidator.validateTextBox(additionalInfo, additionalInfoLabel) && toReturn;
		}

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
		additionalInfo.setEnabled(typesComboBox.getSelectedItem().isAdditionalInfoRequired());
		if (!typesComboBox.getSelectedItem().isAdditionalInfoRequired()) {
			additionalInfo.setText("");
		}
		changeCase();
	}

	private void changeCase() {
		upperCase(tableName);
		lowerCase(columnName);
		upperCase(indexName);
		upperCase(referencedTable);
		lowerCase(referencedColumn);
		upperCase(foreignKeyName);
	}

	private void lowerCase(TextBox textBox) {
		textBox.setText(StringUtils.lowerCase(textBox.getText()));
	}

	private void upperCase(TextBox textBox) {
		textBox.setText(StringUtils.upperCase(textBox.getText()));
	}

	public Window getWindow() {
		return window;
	}

	public void setWindow(Window window) {
		this.window = window;
	}

}
