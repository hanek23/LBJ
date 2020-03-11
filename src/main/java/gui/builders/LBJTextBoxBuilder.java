package gui.builders;

import java.util.regex.Pattern;

import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.TextBox;

import gui.components.LBJTextBox;
import gui.forms.LBJForm;

public class LBJTextBoxBuilder {

	private LBJTextBox lbjTextBox;

	public LBJTextBoxBuilder(String name, LBJForm form) {
		lbjTextBox = new LBJTextBox(name, form);
		lbjTextBox.setTextBox(new TextBox());
	}

	public LBJTextBoxBuilder disabled() {
		lbjTextBox.setEnabled(false);
		return this;
	}

	public LBJTextBoxBuilder label(String label) {
		lbjTextBox.setLabel(new Label(label));
		return this;
	}

	public LBJTextBoxBuilder pattern(String regex) {
		lbjTextBox.getTextBox().setValidationPattern(Pattern.compile(regex));
		return this;
	}

	public LBJTextBox build() {
		return lbjTextBox;
	}

}
