package gui.utils;

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

	/**
	 * Label and value will be in the same row
	 */
	public static void addValueAndLabeledComponentTo(LBJForm form, LBJValueComponent<?> component) {
		form.getContent()
				.addComponent(component.getLabel().setLayoutData(GridLayout.createHorizontallyFilledLayoutData(2)));
		form.getContent().addComponent(component.getComponent()
				.setLayoutData(GridLayout.createHorizontallyFilledLayoutData(Settings.GUI_NUMBER_OF_COLUMNS - 2)));
	}

	/**
	 * Label and value will be in seperate rows
	 */
	public static void addSeperateValueAndLabeledComponentTo(LBJForm form, LBJValueComponent<?> component) {
		addLabeledComponentTo(form, component);
		addValueComponentTo(form, component);
	}

	public static void addLabeledComponentTo(LBJForm form, LBJLabeledComponent label) {
		form.getContent().addComponent(label.getLabel()
				.setLayoutData(GridLayout.createHorizontallyFilledLayoutData(Settings.GUI_NUMBER_OF_COLUMNS)));
	}

	public static void addValueComponentTo(LBJForm form, LBJValueComponent<?> component) {
		form.getContent().addComponent(component.getComponent()
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

	public static void addButtonToSeperateRow(LBJForm form, Button button) {
		form.getContent().addComponent(
				button.setLayoutData(GridLayout.createHorizontallyFilledLayoutData(Settings.GUI_NUMBER_OF_COLUMNS)));
	}

	public static void addLabelToMenuContent(Panel content, LBJPlainLabel label) {
		content.addComponent(label.getLabel());
	}

	public static void addMenuContent(Panel content, ActionListBox mainMenu) {
		content.addComponent(mainMenu);
	}

	public static void addExitButtonToMenu(ActionListBox mainMenu) {
		mainMenu.addItem(Labels.BUTTON_EXIT, () -> System.exit(0));
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

	public static Button createAddColumnButton(LBJWizardForm form, boolean another) {
		Button addColumnButton = new Button(another ? Labels.BUTTON_ADD_ANOTHER_COLUMN : Labels.BUTTON_ADD_COLUMN);
		addColumnButton.addListener(button -> {
			if (form.validate()) {
				if (form.getNextForm() == null) {
					form.setNextForm(LBJFormSupplier.getAddColumnForm(form.getWindow(), form, true));
				}
				form.goToNextForm();
			}
		});
		return addColumnButton;
	}

	public static Button dropAnotherIndexButton(LBJWizardForm form) {
		Button dropAnotherIndexButton = new Button(Labels.BUTTON_CREATE_INDEX);
		dropAnotherIndexButton.addListener(button -> {
			if (form.validate()) {
				if (form.getNextForm() == null) {
					form.setNextForm(LBJFormSupplier.getDropIndexForm(form.getWindow(), form, true));
				}
				form.goToNextForm();
			}
		});
		return dropAnotherIndexButton;
	}

	public static Button createAnotherIndexButton(LBJWizardForm form) {
		Button createAnotherIndexButton = new Button(Labels.BUTTON_DROP_INDEX);
		createAnotherIndexButton.addListener(button -> {
			if (form.validate()) {
				if (form.getNextForm() == null) {
					form.setNextForm(LBJFormSupplier.getCreateIndexForm(form.getWindow(), form, true));
				}
				form.goToNextForm();
			}
		});
		return createAnotherIndexButton;
	}

	public static Button createModifyAnotherColumnButton(LBJWizardForm form) {
		Button modifyAnotherButton = new Button(Labels.BUTTON_MODIFY_ANOTHER_COLUMN);
		modifyAnotherButton.addListener(button -> {
			if (form.validate()) {
				if (form.getNextForm() == null) {
					form.setNextForm(LBJFormSupplier.getModifyDataTypeForm(form.getWindow(), form, true));
				}
				form.goToNextForm();
			}
		});
		return modifyAnotherButton;
	}

	public static Button createDropColumnButton(LBJWizardForm form) {
		Button addDropButton = new Button(Labels.BUTTON_DROP_ANOTHER_COLUMN);
		addDropButton.addListener(button -> {
			if (form.validate()) {
				if (form.getNextForm() == null) {
					form.setNextForm(LBJFormSupplier.getDropColumnForm(form.getWindow(), form, true));
				}
				form.goToNextForm();
			}
		});
		return addDropButton;
	}

	public static Button addAnotherForeignKeyConstraintButton(LBJWizardForm form) {
		Button addAnotherFKeyCButton = new Button(Labels.BUTTON_ADD_ANOTHER_FOREIGN_KEY_CONSTRAINT);
		addAnotherFKeyCButton.addListener(button -> {
			if (form.validate()) {
				if (form.getNextForm() == null) {
					form.setNextForm(LBJFormSupplier.getAddForeignKeyConstraintForm(form.getWindow(), form, true));
				}
				form.goToNextForm();
			}
		});
		return addAnotherFKeyCButton;
	}
	
	public static Button dropAnotherForeignKeyConstraintButton(LBJWizardForm form) {
		Button dropAnotherFKeyCButton = new Button(Labels.BUTTON_DROP_ANOTHER_FOREIGN_KEY_CONSTRAINT);
		dropAnotherFKeyCButton.addListener(button -> {
			if (form.validate()) {
				if (form.getNextForm() == null) {
					form.setNextForm(LBJFormSupplier.getDropForeignKeyConstraintForm(form.getWindow(), form, true));
				}
				form.goToNextForm();
			}
		});
		return dropAnotherFKeyCButton;
	}

	public static void addUpdatableFormToMainMenu(LBJForm form) {
		LBJFormSupplier.getMainMenuForm().addFormToUpdate(form);
	}

}
