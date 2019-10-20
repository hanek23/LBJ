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
import domain.Column;
import domain.DataType;
import domain.ForeignKey;
import domain.Table;
import generator.Generator;

public class AddColumn implements Runnable, Updatable {

	private static final int MAX_LENGTH_OF_NAMES = 32;
	private static final int NUMBER_OF_COLUMNS = 20;

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

	public AddColumn(MainMenu mainMenu, Window window, Runnable previousWindow) {
		this(mainMenu, window, previousWindow, new Table(""));
		tableName.setEnabled(true);
	}

	public AddColumn(MainMenu mainMenu, Window window, Runnable previousWindow, Table table) {
		this.mainMenu = mainMenu;
		this.window = window;
		this.previousWindow = previousWindow;
		this.table = table;
		initialize();
		tableName.setEnabled(false);
	}

	private void initialize() {
		content = new Panel(new GridLayout(NUMBER_OF_COLUMNS));
		GridLayout gridLayout = (GridLayout) content.getLayoutManager();
		gridLayout.setVerticalSpacing(1);

		tableNameLabel = new Label(Labels.CREATE_TABLE_TABLE_NAME);
		content.addComponent(tableNameLabel);
		tableName = new TextBox(table.getName());
		content.addComponent(
				tableName.setLayoutData(GridLayout.createHorizontallyFilledLayoutData(NUMBER_OF_COLUMNS - 1)));

		columnNameLabel = new Label(Labels.ADD_COLUMN_NAME);
		content.addComponent(columnNameLabel);
		columnName = new TextBox();
		content.addComponent(
				columnName.setLayoutData(GridLayout.createHorizontallyFilledLayoutData(NUMBER_OF_COLUMNS - 1)));

		content.addComponent(new Label(Labels.ADD_COLUMN_NULLABLE));
		nullable = new CheckBox();
		content.addComponent(
				nullable.setLayoutData(GridLayout.createHorizontallyFilledLayoutData(NUMBER_OF_COLUMNS - 1)));

		content.addComponent(new Label(Labels.ADD_COLUMN_INDEX));
		index = new CheckBox();
		content.addComponent(index.setLayoutData(GridLayout.createHorizontallyFilledLayoutData(NUMBER_OF_COLUMNS - 1)));

		indexNameLabel = new Label(Labels.ADD_COLUMN_INDEX_NAME);
		content.addComponent(indexNameLabel);
		indexName = new TextBox();
		indexName.setEnabled(false);
		content.addComponent(
				indexName.setLayoutData(GridLayout.createHorizontallyFilledLayoutData(NUMBER_OF_COLUMNS - 1)));

		content.addComponent(new Label(Labels.ADD_COLUMN_FOREIGN_KEY));
		foreignKey = new CheckBox();
		content.addComponent(
				foreignKey.setLayoutData(GridLayout.createHorizontallyFilledLayoutData(NUMBER_OF_COLUMNS - 1)));

		referencedTableLabel = new Label(Labels.ADD_COLUMN_REFERENCED_TABLE);
		content.addComponent(referencedTableLabel);
		referencedTable = new TextBox();
		referencedTable.setEnabled(false);
		content.addComponent(
				referencedTable.setLayoutData(GridLayout.createHorizontallyFilledLayoutData(NUMBER_OF_COLUMNS - 1)));

		referencedColumnLabel = new Label(Labels.ADD_COLUMN_REFERENCED_COLUMN);
		content.addComponent(referencedColumnLabel);
		referencedColumn = new TextBox();
		referencedColumn.setEnabled(false);
		content.addComponent(
				referencedColumn.setLayoutData(GridLayout.createHorizontallyFilledLayoutData(NUMBER_OF_COLUMNS - 1)));

		foreignKeyNameLabel = new Label(Labels.ADD_COLUMN_FOREIGN_KEY_NAME);
		content.addComponent(foreignKeyNameLabel);
		foreignKeyName = new TextBox("F_");
		foreignKeyName.setEnabled(false);
		content.addComponent(
				foreignKeyName.setLayoutData(GridLayout.createHorizontallyFilledLayoutData(NUMBER_OF_COLUMNS - 1)));

		content.addComponent(new Label(Labels.ADD_COLUMN_TYPE));
		typesComboBox = new ComboBox<>(DataType.values());
		typesComboBox.setReadOnly(true);
		typesComboBox.setPreferredSize(new TerminalSize(10, 1));
		content.addComponent(typesComboBox);
		content.addComponent(
				new EmptySpace().setLayoutData(GridLayout.createHorizontallyFilledLayoutData(NUMBER_OF_COLUMNS - 2)));

		additionalInfoLabel = new Label(Labels.ADD_COLUMN_ADDITIONAL_INFO);
		content.addComponent(additionalInfoLabel);
		additionalInfo = new TextBox();
		content.addComponent(additionalInfo);

		content.addComponent(
				new EmptySpace().setLayoutData(GridLayout.createHorizontallyFilledLayoutData(NUMBER_OF_COLUMNS - 2)));
		content.addComponent(new Separator(Direction.HORIZONTAL)
				.setLayoutData(GridLayout.createHorizontallyFilledLayoutData(NUMBER_OF_COLUMNS)));

		Button backButton = new Button(Labels.BUTTON_BACK);
		content.addComponent(backButton);
		backButton.addListener(new Listener() {

			@Override
			public void onTriggered(Button button) {
				table.removeColumn(column);
				previousWindow.run();
			}
		});

		Button addColumnButton = new Button(Labels.BUTTON_ADD_ANOTHER_COLUMN);
		content.addComponent(addColumnButton);
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
				AddColumn newAddColumn = new AddColumn(mainMenu, window, AddColumn.this, table);
				mainMenu.addUpdatableChild(newAddColumn);
				newAddColumn.run();
			}

		});

		Button generateButton = new Button(Labels.BUTTON_GENERATE);
		content.addComponent(generateButton);
		generateButton.addListener(new Listener() {

			@Override
			public void onTriggered(Button button) {
				if (!AddColumn.this.validate()) {
					return;
				}
				table.setName(tableName.getText());
				table.addColumn(AddColumn.this.createColumn());
				Generator.generate(table);
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
		boolean toReturn = validateTextBox(tableName, tableNameLabel);
		toReturn = validateTextBox(columnName, columnNameLabel) && toReturn;
		if (index.isChecked()) {
			boolean indexValidation = validateTextBox(indexName, indexNameLabel);
			if (indexValidation) {
				indexValidation = validateTextBoxValueLength(indexName, indexNameLabel, MAX_LENGTH_OF_NAMES)
						&& indexValidation;
			}
			if (indexValidation) {
				indexValidation = validateTextBoxDefaultValue(indexName, indexNameLabel, "I__") && indexValidation;
			}
			toReturn = indexValidation && toReturn;
		}
		if (foreignKey.isChecked()) {
			toReturn = validateTextBox(referencedTable, referencedTableLabel) && toReturn;
			toReturn = validateTextBox(referencedColumn, referencedColumnLabel) && toReturn;
			boolean foreignKeyValidation = validateTextBox(foreignKeyName, foreignKeyNameLabel);
			if (foreignKeyValidation) {
				foreignKeyValidation = validateTextBoxValueLength(foreignKeyName, foreignKeyNameLabel,
						MAX_LENGTH_OF_NAMES) && foreignKeyValidation;
			}
			if (foreignKeyValidation) {
				foreignKeyValidation = validateTextBoxDefaultValue(foreignKeyName, foreignKeyNameLabel, "F__")
						&& foreignKeyValidation;
			}
			if (foreignKeyValidation) {
				foreignKeyValidation = validateTextBoxDefaultValue(foreignKeyName, foreignKeyNameLabel, "F_")
						&& foreignKeyValidation;
			}
			toReturn = foreignKeyValidation && toReturn;
		}
		if (typesComboBox.getSelectedItem().isAdditionalInfoRequired()) {
			toReturn = validateTextBox(additionalInfo, additionalInfoLabel) && toReturn;
		}

		return toReturn;
	}

	private boolean validateTextBoxDefaultValue(TextBox textBox, Label label, String defaultValue) {
		if (textBox.getText().equals(defaultValue)) {
			label.setBackgroundColor(TextColor.ANSI.RED);
			return false;
		} else {
			label.setBackgroundColor(null);
			return true;
		}
	}

	private boolean validateTextBox(TextBox textBox, Label label) {
		if (StringUtils.isEmpty(textBox.getText())) {
			label.setBackgroundColor(TextColor.ANSI.RED);
			return false;
		} else {
			label.setBackgroundColor(null);
			return true;
		}
	}

	private boolean validateTextBoxValueLength(TextBox textBox, Label label, int maxLength) {
		if (textBox.getText().length() > maxLength) {
			label.setBackgroundColor(TextColor.ANSI.RED);
			return false;
		} else {
			label.setBackgroundColor(null);
			return true;
		}
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
				indexName.setText("I_" + tableName.getText() + "_" + columnName.getText());
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
				foreignKeyName.setText("F_" + referencedTable.getText() + "_" + referencedColumn.getText());
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
