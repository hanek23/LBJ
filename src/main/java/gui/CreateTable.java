package gui;

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
import domain.Table;

public class CreateTable implements Runnable, Updatable {

	private Window window;
	private Runnable previousWindow;
	private Panel content;
	private TextBox primaryKeyName;
	private TextBox tableName;
	private boolean initialized;
	private CreateTable thisInstance;
	private MainMenu mainMenu;

	public CreateTable(MainMenu mainMenu, Window window, Runnable previousWindow) {
		this.mainMenu = mainMenu;
		this.window = window;
		this.previousWindow = previousWindow;
		thisInstance = this;
		initialize();
	}

	private void initialize() {
		content = new Panel(new GridLayout(3));
		GridLayout gridLayout = (GridLayout) content.getLayoutManager();
		gridLayout.setVerticalSpacing(1);

		content.addComponent(new Label(Labels.CREATE_TABLE_TABLE_NAME));
		tableName = new TextBox();
		content.addComponent(tableName.setLayoutData(GridLayout.createHorizontallyFilledLayoutData(2)));

		content.addComponent(new Label(Labels.CREATE_TABLE_PRIMARY_KEY_NAME));
		primaryKeyName = new TextBox("id_");
		content.addComponent(primaryKeyName.setLayoutData(GridLayout.createHorizontallyFilledLayoutData(2)));

		content.addComponent(new Label(Labels.CREATE_TABLE_DATABASES)
				.setLayoutData(GridLayout.createHorizontallyFilledLayoutData(3)));

		CheckBox oracle = new CheckBox(Labels.CREATE_TABLE_DATABASES_ORACLE);
		oracle.setChecked(true);
		content.addComponent(oracle.setLayoutData(GridLayout.createHorizontallyFilledLayoutData(3)));

		CheckBox mssql = new CheckBox(Labels.CREATE_TABLE_DATABASES_MSSQL);
		mssql.setChecked(true);
		content.addComponent(mssql.setLayoutData(GridLayout.createHorizontallyFilledLayoutData(3)));

		CheckBox postgresql = new CheckBox(Labels.CREATE_TABLE_DATABASES_POSTGRESQL);
		postgresql.setChecked(true);
		content.addComponent(postgresql.setLayoutData(GridLayout.createHorizontallyFilledLayoutData(3)));

		content.addComponent(
				new Separator(Direction.HORIZONTAL).setLayoutData(GridLayout.createHorizontallyFilledLayoutData(3)));
		content.addComponent(new Button(Labels.BUTTON_BACK, previousWindow));
		Button addColumnButton = new Button(Labels.BUTTON_ADD_COLUMN);
		content.addComponent(addColumnButton);
		addColumnButton.addListener(new Listener() {

			@Override
			public void onTriggered(Button button) {
				AddColumn addColumn = new AddColumn(mainMenu, window, thisInstance, new Table(tableName.getText()));
				mainMenu.addUpdatableChild(addColumn);
				addColumn.run();
			}
		});

		initialized = true;
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
			primaryKeyName.setText("id_" + tableName.getText());
		}
	}

	public Window getWindow() {
		return window;
	}

	public void setWindow(Window window) {
		this.window = window;
	}

}
