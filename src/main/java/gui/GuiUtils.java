package gui;

import org.apache.commons.lang3.StringUtils;

import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.Component;
import com.googlecode.lanterna.gui2.GridLayout;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.TextBox;

import constants.Labels;
import constants.Settings;

public class GuiUtils {

	private GuiUtils() {
		// only static methods
	}

	public static void addLabelAndComponentToContent(Label label, Component component, Panel content) {
		content.addComponent(label);
		content.addComponent(component
				.setLayoutData(GridLayout.createHorizontallyFilledLayoutData(Settings.GUI_NUMBER_OF_COLUMNS - 1)));
	}

	public static void addComponentToContent(Component component, Panel content) {
		content.addComponent(
				component.setLayoutData(GridLayout.createHorizontallyFilledLayoutData(Settings.GUI_NUMBER_OF_COLUMNS)));
	}

	public static void lowerCase(TextBox textBox) {
		textBox.setText(StringUtils.lowerCase(textBox.getText()));
	}

	public static void upperCase(TextBox textBox) {
		textBox.setText(StringUtils.upperCase(textBox.getText()));
	}

	public static void addDefaultBackButton(Panel content, Runnable previousWindow) {
		content.addComponent(new Button(Labels.BUTTON_BACK, previousWindow)
				.setLayoutData(GridLayout.createHorizontallyFilledLayoutData(1)));
	}

	public static Panel initializeDefaultContent() {
		Panel content = new Panel(new GridLayout(Settings.GUI_NUMBER_OF_COLUMNS));
		GridLayout gridLayout = (GridLayout) content.getLayoutManager();
		gridLayout.setVerticalSpacing(Settings.GUI_VERTICAL_SPACING);
		return content;
	}

}
