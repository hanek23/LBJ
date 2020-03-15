package gui.forms.mainmenu;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.googlecode.lanterna.gui2.ActionListBox;
import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.GridLayout;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.Window;
import com.googlecode.lanterna.gui2.WindowBasedTextGUI;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;

import constants.Labels;
import gui.LBJFormUtils;
import gui.builders.LBJPlainLabelBuilder;
import gui.components.LBJPlainLabel;
import gui.forms.LBJForm;
import gui.forms.createtable.LBJCreateTableForm;
import gui.suppliers.LBJFormSupplier;

public class LBJMainMenuForm extends LBJForm {

	private static final Logger LOGGER = Logger.getLogger(LBJMainMenuForm.class.getSimpleName());

	private boolean terminalStarted;
	private List<LBJForm> formsToUpdate;
	private LBJPlainLabel questionLabel;
	private ActionListBox mainMenu;
	private LBJCreateTableForm createTableForm;

	public LBJMainMenuForm() {
		this(new BasicWindow(Labels.WINDOW_NAME));
	}

	private LBJMainMenuForm(Window window) {
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
		createTableForm = LBJFormSupplier.getCreateTableForm(getWindow(), this);

	}

	@Override
	public void addComponentsToContent() {
		LBJFormUtils.addLabelToMainMenuContent(getContent(), questionLabel);
		LBJFormUtils.addMenuToMainMenuContent(getContent(), mainMenu);
		LBJFormUtils.addItemToMenu(mainMenu, createTableForm);
	}

	@Override
	public void addButtonsToContent() {
		mainMenu.addItem(Labels.BUTTON_EXIT, new Runnable() {
			@Override
			public void run() {
				System.exit(0);
			}
		});
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
		DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
		try (Screen screen = terminalFactory.createScreen()) {
			screen.startScreen();
			WindowBasedTextGUI gui = new MultiWindowTextGUI(screen);
			terminalStarted = true;
			gui.addWindowAndWait(getWindow());
		} catch (IOException e) {
			LOGGER.severe("IOException has occurred: " + e.getMessage());
			throw new IllegalStateException();
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
