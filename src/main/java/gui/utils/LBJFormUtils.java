package gui.utils;

import org.apache.commons.lang3.StringUtils;

import com.googlecode.lanterna.gui2.ActionListBox;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.Component;
import com.googlecode.lanterna.gui2.EmptySpace;
import com.googlecode.lanterna.gui2.GridLayout;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.TextBox;

import constants.Labels;
import constants.Settings;
import gui.components.LBJLabeledComponent;
import gui.components.LBJPlainLabel;
import gui.components.LBJValueHolderComponent;
import gui.forms.LBJForm;
import gui.suppliers.LBJFormSupplier;

public class LBJFormUtils {

	private LBJFormUtils() {
		// only static methods
	}

	public static Panel initializeDefaultContent() {
		Panel content = new Panel(new GridLayout(Settings.GUI_NUMBER_OF_COLUMNS));
		GridLayout gridLayout = (GridLayout) content.getLayoutManager();
		gridLayout.setVerticalSpacing(Settings.GUI_VERTICAL_SPACING);
		return content;
	}

	public static void addComponentToContent(Panel content, LBJValueHolderComponent<?> component) {
		content.addComponent(component.getLabel().setLayoutData(GridLayout.createHorizontallyFilledLayoutData(1)));
		content.addComponent(component.getComponent()
				.setLayoutData(GridLayout.createHorizontallyFilledLayoutData(Settings.GUI_NUMBER_OF_COLUMNS - 1)));
	}

	public static void addComponentToContent(Panel content, LBJLabeledComponent component) {
		content.addComponent(component.getLabel()
				.setLayoutData(GridLayout.createHorizontallyFilledLayoutData(Settings.GUI_NUMBER_OF_COLUMNS)));
	}

	public static void addItemToMenu(ActionListBox menu, LBJForm form, String label) {
		menu.addItem(label, form);
	}

	public static void addEmptySpace(Panel content) {
		content.addComponent(new EmptySpace());
	}

	public static void addUpdatableFormToMainMenu(LBJForm form) {
		LBJFormSupplier.getMainMenuForm().addFormToUpdate(form);
	}

	// OLD

	public static void addLabelAndComponentToContent(Label label, Component component, Panel content) {
		content.addComponent(label);
		content.addComponent(component
				.setLayoutData(GridLayout.createHorizontallyFilledLayoutData(Settings.GUI_NUMBER_OF_COLUMNS - 1)));
	}

	public static void addComponentToContent(Panel content, Component component) {
		content.addComponent(
				component.setLayoutData(GridLayout.createHorizontallyFilledLayoutData(Settings.GUI_NUMBER_OF_COLUMNS)));
	}

	public static void lowerCase(TextBox textBox) {
		textBox.setText(StringUtils.lowerCase(textBox.getText()));
	}

	public static void upperCase(TextBox textBox) {
		textBox.setText(StringUtils.upperCase(textBox.getText()));
	}

	public static void addBackButton(Panel content, Runnable previousWindow) {
		addButtonToConent(content, new Button(Labels.BUTTON_BACK, previousWindow));
	}

	public static void addButtonToConent(Panel content, Button button) {
		content.addComponent(button.setLayoutData(GridLayout.createHorizontallyFilledLayoutData(1)));
	}

	public static void addLabelToMainMenuContent(Panel content, LBJPlainLabel label) {
		content.addComponent(label.getLabel());
	}

	public static void addMenuToMainMenuContent(Panel content, ActionListBox mainMenu) {
		content.addComponent(mainMenu);
	}

	public static void addExitButton(ActionListBox mainMenu) {
		mainMenu.addItem(Labels.BUTTON_EXIT, new Runnable() {
			@Override
			public void run() {
				System.exit(0);
			}
		});
	}

}
