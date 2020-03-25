package gui.forms.mainmenu;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Logger;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.ActionListBox;
import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.GridLayout;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.Window;
import com.googlecode.lanterna.gui2.WindowBasedTextGUI;
import com.googlecode.lanterna.gui2.WindowListener;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;

import constants.Labels;
import gui.builders.LBJPlainLabelBuilder;
import gui.components.LBJPlainLabel;
import gui.forms.LBJForm;
import gui.forms.addcolumn.AddColumnForm;
import gui.forms.createtable.CreateTableForm;
import gui.forms.removenotnullconstraint.RemoveNotNullConstraintForm;
import gui.suppliers.LBJFormSupplier;
import gui.utils.LBJFormUtils;

public class MainMenuForm extends LBJForm {

	private static final Logger LOGGER = Logger.getLogger(MainMenuForm.class.getSimpleName());

	private boolean terminalStarted;
	private List<LBJForm> formsToUpdate;
	private LBJPlainLabel questionLabel;
	private ActionListBox mainMenu;
	private CreateTableForm createTableForm;
	private AddColumnForm addColumnForm;
	private RemoveNotNullConstraintForm removeNotNullConstraintForm;

	public MainMenuForm() {
		this(new BasicWindow(Labels.WINDOW_NAME));
	}

	private MainMenuForm(Window window) {
		super(window);
		initialize();
	}

	@Override
	public String toString() {
		return Labels.MAIN_MENU_FORM;
	}

	@Override
	public void initializeComponents() {
		mainMenu = new ActionListBox();
		questionLabel = new LBJPlainLabelBuilder(Labels.MAIN_MENU_QUESTION, this).build();
		createTableForm = LBJFormSupplier.getCreateTableForm(getWindow(), this, false);
		addColumnForm = LBJFormSupplier.getAddColumnForm(getWindow(), this, false);
		removeNotNullConstraintForm = LBJFormSupplier.getRemoveNotNullConstraintForm(getWindow(), this, false);

		addFormToUpdate(createTableForm);
		addFormToUpdate(addColumnForm);
		addFormToUpdate(removeNotNullConstraintForm);
	}

	@Override
	public void addComponentsToContent() {
		LBJFormUtils.addLabelToMainMenuContent(getContent(), questionLabel);
		LBJFormUtils.addMenuToMainMenuContent(getContent(), mainMenu);
		LBJFormUtils.addItemToMenu(mainMenu, createTableForm, Labels.MAIN_MENU_CREATE_TABLE);
		LBJFormUtils.addItemToMenu(mainMenu, addColumnForm, Labels.MAIN_MENU_ADD_COLUMN);
		LBJFormUtils.addItemToMenu(mainMenu, removeNotNullConstraintForm, Labels.MAIN_MENU_REMOVE_NOT_NULL_CONSTRAINT);
		LBJFormUtils.addExitButtonToMainMenu(mainMenu);
	}

	@Override
	public void addButtonsToContent() {
		// no buttons in main menu, everything is in ActionListBox - mainMenu
	}

	@Override
	public void addFormUpdaters() {
		// no updaters
	}

	@Override
	public void addFormValidators() {
		// no validators
	}

	@Override
	public void initializeContent() {
		setContent(new Panel(new GridLayout(1)));
	}

	@Override
	public void focus() {
		super.focus();
		if (terminalStarted) {
			return;
		}
		terminalStarted = true;
		startTerminal();
	}

	private void startTerminal() {
		try (Screen screen = new DefaultTerminalFactory().createScreen()) {
			screen.startScreen();
			WindowBasedTextGUI gui = new MultiWindowTextGUI(screen);
			getWindow().addWindowListener(new WindowListener() {

				@Override
				public void onUnhandledInput(Window basePane, KeyStroke keyStroke, AtomicBoolean hasBeenHandled) {
					updateForms();
				}

				@Override
				public void onInput(Window basePane, KeyStroke keyStroke, AtomicBoolean deliverEvent) {
					updateForms();
				}

				@Override
				public void onResized(Window window, TerminalSize oldSize, TerminalSize newSize) {
					updateForms();
				}

				@Override
				public void onMoved(Window window, TerminalPosition oldPosition, TerminalPosition newPosition) {
					updateForms();
				}
			});
			gui.addWindowAndWait(getWindow());
		} catch (IOException e) {
			LOGGER.severe("While starting terminal IOException has occurred: " + e.getMessage());
			throw new IllegalStateException();
		}
	}

	public void updateForms() {
		for (LBJForm lbjForm : getFormsToUpdate()) {
			lbjForm.update();
		}
	}

	/**
	 * Child forms that are going to be updated each time user does some action.
	 * Only currently visible form will be updated.
	 */
	public List<LBJForm> getFormsToUpdate() {
		if (formsToUpdate == null) {
			formsToUpdate = new ArrayList<>();
		}
		return formsToUpdate;
	}

	public boolean addFormToUpdate(LBJForm form) {
		return getFormsToUpdate().add(form);
	}

}
