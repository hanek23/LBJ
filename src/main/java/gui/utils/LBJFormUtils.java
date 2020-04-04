package gui.utils;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.ActionListBox;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.Button.Listener;
import com.googlecode.lanterna.gui2.EmptySpace;
import com.googlecode.lanterna.gui2.GridLayout;
import com.googlecode.lanterna.gui2.Panel;

import constants.Labels;
import constants.Settings;
import gui.components.LBJLabeledComponent;
import gui.components.LBJPlainLabel;
import gui.components.LBJValueComponent;
import gui.forms.LBJForm;
import gui.forms.LBJWizardForm;
import gui.forms.mainmenu.MainMenuForm;
import gui.suppliers.LBJFormSupplier;

public class LBJFormUtils {

	private LBJFormUtils() {
		// only static methods
	}

	/**
	 * Initialize default content with fixed number of columns
	 * ({@link Settings#GUI_NUMBER_OF_COLUMNS}) and vertical spacing
	 * ({@link Settings#GUI_VERTICAL_SPACING}). Almost every form should use this
	 * content except of {@link MainMenuForm} which is little different.
	 */
	public static Panel initializeDefaultContent() {
		Panel content = new Panel(new GridLayout(Settings.GUI_NUMBER_OF_COLUMNS));
		GridLayout gridLayout = (GridLayout) content.getLayoutManager();
		gridLayout.setVerticalSpacing(Settings.GUI_VERTICAL_SPACING);
		return content;
	}

	public static void addComponentTo(LBJForm form, LBJValueComponent<?> component) {
		form.getContent()
				.addComponent(component.getLabel().setLayoutData(GridLayout.createHorizontallyFilledLayoutData(1)));
		form.getContent().addComponent(component.getComponent()
				.setLayoutData(GridLayout.createHorizontallyFilledLayoutData(Settings.GUI_NUMBER_OF_COLUMNS - 1)));
	}

	public static void addComponentTo(LBJForm form, LBJLabeledComponent component) {
		form.getContent().addComponent(component.getLabel()
				.setLayoutData(GridLayout.createHorizontallyFilledLayoutData(Settings.GUI_NUMBER_OF_COLUMNS)));
	}

	public static void addHiddenTextAreaTo(LBJForm form, LBJValueComponent<?> component) {
		form.getContent().addComponent(component.getComponent().setPreferredSize(new TerminalSize(0, 0))
				.setLayoutData(GridLayout.createHorizontallyFilledLayoutData(Settings.GUI_NUMBER_OF_COLUMNS)));
	}

	public static void addItemToMenu(ActionListBox menu, LBJForm form, String label) {
		menu.addItem(label, form);
	}

	public static Button createBackButtonOn(LBJForm form, Runnable previousWindow) {
		addEmptySpaceTo(form);
		Button backButton = new Button(Labels.BUTTON_BACK, previousWindow);
		addButtonTo(form, backButton);
		return backButton;
	}

	public static void addEmptySpaceTo(LBJForm form) {
		form.getContent().addComponent(new EmptySpace());
	}

	public static void addButtonTo(LBJForm form, Button button) {
		form.getContent().addComponent(button.setLayoutData(GridLayout.createHorizontallyFilledLayoutData(1)));
	}

	public static void addLabelToMainMenuContent(Panel content, LBJPlainLabel label) {
		content.addComponent(label.getLabel());
	}

	public static void addMenuToMainMenuContent(Panel content, ActionListBox mainMenu) {
		content.addComponent(mainMenu);
	}

	public static void addExitButtonToMainMenu(ActionListBox mainMenu) {
		mainMenu.addItem(Labels.BUTTON_EXIT, new Runnable() {
			@Override
			public void run() {
				System.exit(0);
			}
		});
	}

	public static Button createGenerateButton(LBJForm form) {
		Button generateButton = new Button(Labels.BUTTON_GENERATE);
		generateButton.addListener(new Listener() {
			@Override
			public void onTriggered(Button button) {
				if (!form.validate()) {
					return;
				}
				LBJFormSupplier.getGenerateForm(form.getWindow(), form).focus();
			}
		});
		return generateButton;
	}

	public static Button createAddColumnButton(LBJWizardForm form) {
		Button addColumnButton = new Button(Labels.BUTTON_ADD_COLUMN);
		addColumnButton.addListener(new Listener() {
			@Override
			public void onTriggered(Button button) {
				if (form.validate()) {
					if (form.getNextForm() == null) {
						form.setNextForm(LBJFormSupplier.getAddColumnForm(form.getWindow(), form));
					}
					form.goToNextForm();
				}

			}
		});
		return addColumnButton;
	}

}
