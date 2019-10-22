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
		boolean foreignKeyValidation = validateTextBox(textBox, label);
		if (foreignKeyValidation) {
			foreignKeyValidation = validateTextBoxValueLength(textBox, label, Settings.MAX_LENGTH_OF_NAMES)
					&& foreignKeyValidation;
		}
		for (String defaultValue : defaultValues) {
			if (foreignKeyValidation) {
				foreignKeyValidation = validateTextBoxDefaultValue(textBox, label, defaultValue)
						&& foreignKeyValidation;
			}
		}
		return foreignKeyValidation;
	}

}
