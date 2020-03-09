package gui;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.Button.Listener;
import com.googlecode.lanterna.gui2.CheckBox;
import com.googlecode.lanterna.gui2.EmptySpace;
import com.googlecode.lanterna.gui2.GridLayout;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.TextBox;
import com.googlecode.lanterna.gui2.Window;

import constants.Labels;
import constants.NamingConventions;
import domain.Table;
import generator.Operation;

public class CreateTableForm implements Runnable, Updatable {

	private Window window;
	private Runnable previousWindow;
	private Panel content;
	private TextBox primaryKeyName;
	private TextBox tableName;
	private boolean initialized;
	private MainMenuForm mainMenu;
	private Label tableNameLabel;
	private Label primaryKeyNameLabel;
	private Label databasesLabel;
	private CheckBox oracle;
	private CheckBox mssql;
	private CheckBox postgresql;
	private TextBox primaryKeyConstraint;
	private Label primaryKeyConstraintLabel;
	private TextBox sequenceName;
	private Label sequenceNameLabel;

	public CreateTableForm(MainMenuForm mainMenu, Window window, Runnable previousWindow) {
		this.mainMenu = mainMenu;
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

	protected boolean validate() {
		boolean toReturn = GuiValidator.validateTextBox(tableName, tableNameLabel);
		toReturn = GuiValidator.validateAll(primaryKeyName, primaryKeyNameLabel,
				NamingConventions.PRIMARY_KEY_NAME_DEFAULT_VALUE) && toReturn;
		toReturn = validateDatabasesCheckBoxes() && toReturn;
		toReturn = GuiValidator.validateAll(primaryKeyConstraint, primaryKeyConstraintLabel,
				NamingConventions.PRIMARY_KEY_CONSTRAINT_DEFAULT_VALUE) && toReturn;
		return toReturn;
	}

	private boolean validateDatabasesCheckBoxes() {
		if (oracle.isChecked() || mssql.isChecked() || postgresql.isChecked()) {
			databasesLabel.setBackgroundColor(null);
			return true;
		} else {
			databasesLabel.setBackgroundColor(TextColor.ANSI.RED);
			return false;
		}
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
		if (tableName.isFocused()) {
			primaryKeyName.setText(NamingConventions.PRIMARY_KEY_NAME_DEFAULT_VALUE + tableName.getText());
			primaryKeyConstraint.setText(NamingConventions.PRIMARY_KEY_CONSTRAINT_DEFAULT_VALUE + tableName.getText());
		}
		if (oracle.isChecked() || postgresql.isChecked()) {
			sequenceName.setText(NamingConventions.SEQUENCE_NAME_DEFAULT_VALUE + tableName.getText());
			sequenceName.setEnabled(true);
		} else {
			sequenceName.setText("");
			sequenceName.setEnabled(false);
		}
		changeCase();
	}

	private void changeCase() {
		GuiUtils.upperCase(tableName);
		GuiUtils.lowerCase(primaryKeyName);
		GuiUtils.upperCase(primaryKeyConstraint);
		GuiUtils.upperCase(sequenceName);
	}

	private void initializeComponents() {
		content = GuiUtils.initializeDefaultContent();

		tableNameLabel = new Label(Labels.TABLE_NAME);
		tableName = new TextBox();

		primaryKeyNameLabel = new Label(Labels.CREATE_TABLE_PRIMARY_KEY_NAME);
		primaryKeyName = new TextBox(NamingConventions.PRIMARY_KEY_NAME_DEFAULT_VALUE);

		primaryKeyConstraintLabel = new Label(Labels.CREATE_TABLE_PRIMARY_KEY_CONSTRAIN);
		primaryKeyConstraint = new TextBox(NamingConventions.PRIMARY_KEY_CONSTRAINT_DEFAULT_VALUE);

		databasesLabel = new Label(Labels.CREATE_TABLE_DATABASES);

		oracle = new CheckBox(Labels.CREATE_TABLE_DATABASES_ORACLE);
		oracle.setChecked(true);

		mssql = new CheckBox(Labels.CREATE_TABLE_DATABASES_MSSQL);
		mssql.setChecked(true);

		postgresql = new CheckBox(Labels.CREATE_TABLE_DATABASES_POSTGRESQL);
		postgresql.setChecked(true);

		sequenceNameLabel = new Label(Labels.CREATE_TABLE_SEQUENCE_NAME);
		sequenceName = new TextBox();
	}

	private void addComponentsToContent() {
		GuiUtils.addLabelAndComponentToContent(tableNameLabel, tableName, content);
		GuiUtils.addLabelAndComponentToContent(primaryKeyNameLabel, primaryKeyName, content);
		GuiUtils.addLabelAndComponentToContent(primaryKeyConstraintLabel, primaryKeyConstraint, content);
		GuiUtils.addComponentToContent(databasesLabel, content);
		GuiUtils.addComponentToContent(oracle, content);
		GuiUtils.addComponentToContent(mssql, content);
		GuiUtils.addComponentToContent(postgresql, content);
		GuiUtils.addLabelAndComponentToContent(sequenceNameLabel, sequenceName, content);
	}

	private void addButtonsToContent() {
		content.addComponent(new EmptySpace());
		GuiUtils.addDefaultBackButton(content, previousWindow);
		Button addColumnButton = new Button(Labels.BUTTON_ADD_COLUMN);
		content.addComponent(addColumnButton.setLayoutData(GridLayout.createHorizontallyFilledLayoutData(1)));
		addColumnButton.addListener(new Listener() {

			@Override
			public void onTriggered(Button button) {
				if (!CreateTableForm.this.validate()) {
					return;
				}
				Table table = new Table(tableName.getText());
				table.setForOracle(oracle.isChecked());
				table.setForMssql(mssql.isChecked());
				table.setForPostgreSql(postgresql.isChecked());
				table.setPrimaryKeyColumnName(primaryKeyName.getText());
				table.setPrimaryKeyContrainName(primaryKeyConstraint.getText());
				table.setSequenceName(sequenceName.getText());
				AddColumnForm addColumn = new AddColumnForm(mainMenu, window, CreateTableForm.this, table,
						Operation.CREATE_TABLE);
				mainMenu.addUpdatableChild(addColumn);
				addColumn.run();
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
