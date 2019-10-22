package gui;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.Button.Listener;
import com.googlecode.lanterna.gui2.CheckBox;
import com.googlecode.lanterna.gui2.Direction;
import com.googlecode.lanterna.gui2.EmptySpace;
import com.googlecode.lanterna.gui2.GridLayout;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.Separator;
import com.googlecode.lanterna.gui2.TextBox;
import com.googlecode.lanterna.gui2.Window;

import constants.Labels;
import constants.NamingConventions;
import constants.Settings;
import domain.Table;
import generator.Operation;

public class CreateTable implements Runnable, Updatable {

	private Window window;
	private Runnable previousWindow;
	private Panel content;
	private TextBox primaryKeyName;
	private TextBox tableName;
	private boolean initialized;
	private CreateTable thisInstance;
	private MainMenu mainMenu;
	private Label tableNameLabel;
	private Label primaryKeyNameLabel;
	private Label databasesLabel;
	private CheckBox oracle;
	private CheckBox mssql;
	private CheckBox postgresql;
	private TextBox primaryKeyConstraint;
	private Label primaryKeyConstrainLabel;

	public CreateTable(MainMenu mainMenu, Window window, Runnable previousWindow) {
		this.mainMenu = mainMenu;
		this.window = window;
		this.previousWindow = previousWindow;
		thisInstance = this;
		initialize();
	}

	private void initialize() {
		content = new Panel(new GridLayout(Settings.GUI_NUMBER_OF_COLUMNS));
		GridLayout gridLayout = (GridLayout) content.getLayoutManager();
		gridLayout.setVerticalSpacing(Settings.GUI_VERTICAL_SPACING);

		tableNameLabel = new Label(Labels.CREATE_TABLE_TABLE_NAME);
		content.addComponent(tableNameLabel);
		tableName = new TextBox();
		content.addComponent(tableName
				.setLayoutData(GridLayout.createHorizontallyFilledLayoutData(Settings.GUI_NUMBER_OF_COLUMNS - 1)));

		primaryKeyNameLabel = new Label(Labels.CREATE_TABLE_PRIMARY_KEY_NAME);
		content.addComponent(primaryKeyNameLabel);
		primaryKeyName = new TextBox(NamingConventions.PRIMARY_KEY_NAME_DEFAULT_VALUE);
		content.addComponent(primaryKeyName
				.setLayoutData(GridLayout.createHorizontallyFilledLayoutData(Settings.GUI_NUMBER_OF_COLUMNS - 1)));

		primaryKeyConstrainLabel = new Label(Labels.CREATE_TABLE_PRIMARY_KEY_CONSTRAIN);
		content.addComponent(primaryKeyConstrainLabel);
		primaryKeyConstraint = new TextBox(NamingConventions.PRIMARY_KEY_CONSTRAINT_DEFAULT_VALUE);
		content.addComponent(primaryKeyConstraint
				.setLayoutData(GridLayout.createHorizontallyFilledLayoutData(Settings.GUI_NUMBER_OF_COLUMNS - 1)));

		databasesLabel = new Label(Labels.CREATE_TABLE_DATABASES)
				.setLayoutData(GridLayout.createHorizontallyFilledLayoutData(Settings.GUI_NUMBER_OF_COLUMNS));
		content.addComponent(databasesLabel);

		oracle = new CheckBox(Labels.CREATE_TABLE_DATABASES_ORACLE);
		oracle.setChecked(true);
		content.addComponent(
				oracle.setLayoutData(GridLayout.createHorizontallyFilledLayoutData(Settings.GUI_NUMBER_OF_COLUMNS)));

		mssql = new CheckBox(Labels.CREATE_TABLE_DATABASES_MSSQL);
		mssql.setChecked(true);
		content.addComponent(
				mssql.setLayoutData(GridLayout.createHorizontallyFilledLayoutData(Settings.GUI_NUMBER_OF_COLUMNS)));

		postgresql = new CheckBox(Labels.CREATE_TABLE_DATABASES_POSTGRESQL);
		postgresql.setChecked(true);
		content.addComponent(postgresql
				.setLayoutData(GridLayout.createHorizontallyFilledLayoutData(Settings.GUI_NUMBER_OF_COLUMNS)));

		content.addComponent(new EmptySpace());
		content.addComponent(new Button(Labels.BUTTON_BACK, previousWindow)
				.setLayoutData(GridLayout.createHorizontallyFilledLayoutData(1)));
		Button addColumnButton = new Button(Labels.BUTTON_ADD_COLUMN);
		content.addComponent(addColumnButton.setLayoutData(GridLayout.createHorizontallyFilledLayoutData(1)));
		addColumnButton.addListener(new Listener() {

			@Override
			public void onTriggered(Button button) {
				if (!CreateTable.this.validate()) {
					return;
				}
				// TODO boolean z databazi
				AddColumn addColumn = new AddColumn(mainMenu, window, thisInstance, new Table(tableName.getText()),
						Operation.CREATE_TABLE);
				mainMenu.addUpdatableChild(addColumn);
				addColumn.run();
			}
		});

		initialized = true;
	}

	protected boolean validate() {
		boolean toReturn = GuiValidator.validateTextBox(tableName, tableNameLabel);
		toReturn = GuiValidator.validateAll(primaryKeyName, primaryKeyNameLabel,
				NamingConventions.PRIMARY_KEY_NAME_DEFAULT_VALUE) && toReturn;
		toReturn = validateDatabasesCheckBoxs() && toReturn;
		toReturn = GuiValidator.validateAll(primaryKeyConstraint, primaryKeyConstrainLabel,
				NamingConventions.PRIMARY_KEY_CONSTRAINT_DEFAULT_VALUE) && toReturn;
		return toReturn;
	}

	private boolean validateDatabasesCheckBoxs() {
		if (oracle.isChecked() || mssql.isChecked() || postgresql.isChecked()) {
			databasesLabel.setBackgroundColor(null);
			return false;
		} else {
			databasesLabel.setBackgroundColor(TextColor.ANSI.RED);
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
		if (tableName.isFocused()) {
			primaryKeyName.setText(NamingConventions.PRIMARY_KEY_NAME_DEFAULT_VALUE + tableName.getText());
			primaryKeyConstraint.setText(NamingConventions.PRIMARY_KEY_CONSTRAINT_DEFAULT_VALUE + tableName.getText());
		}
	}

	public Window getWindow() {
		return window;
	}

	public void setWindow(Window window) {
		this.window = window;
	}

}
