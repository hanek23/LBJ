package gui;

import org.apache.commons.lang3.StringUtils;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.TextBox;

import constants.Settings;

public class GuiValidator {

	private GuiValidator() {
		// only static methods
	}

	public static boolean validateTextBoxDefaultValue(TextBox textBox, Label label, String defaultValue) {
		if (textBox.getText().equals(defaultValue)) {
			label.setBackgroundColor(TextColor.ANSI.RED);
			return false;
		} else {
			label.setBackgroundColor(null);
			return true;
		}
	}

	public static boolean validateTextBox(TextBox textBox, Label label) {
		if (StringUtils.isEmpty(textBox.getText())) {
			label.setBackgroundColor(TextColor.ANSI.RED);
			return false;
		} else {
			label.setBackgroundColor(null);
			return true;
		}
	}

	public static boolean validateTextBoxValueLength(TextBox textBox, Label label, int maxLength) {
		if (textBox.getText().length() > maxLength) {
			label.setBackgroundColor(TextColor.ANSI.RED);
			return false;
		} else {
			label.setBackgroundColor(null);
			return true;
		}
	}

	public static boolean validateAll(TextBox textBox, Label label, String... defaultValues) {
		boolean validationResult = validateTextBox(textBox, label);
		if (validationResult) {
			validationResult = validateTextBoxValueLength(textBox, label, Settings.MAX_LENGTH_OF_NAMES)
					&& validationResult;
		}
		for (String defaultValue : defaultValues) {
			if (validationResult) {
				validationResult = validateTextBoxDefaultValue(textBox, label, defaultValue) && validationResult;
			}
		}
		return validationResult;
	}

}
