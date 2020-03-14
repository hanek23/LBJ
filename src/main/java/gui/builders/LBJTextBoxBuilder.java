package gui.builders;

import java.util.regex.Pattern;

import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.TextBox;

import gui.components.LBJTextBox;
import gui.forms.LBJWizardForm;
import gui.updaters.LBJValueUpdater;
import gui.validators.LBJValueValidator;

public class LBJTextBoxBuilder {

	private LBJTextBox lbjTextBox;

	public LBJTextBoxBuilder(String name, LBJWizardForm form) {
		lbjTextBox = new LBJTextBox(name, form);
		lbjTextBox.setTextBox(new TextBox());
		lbjTextBox.setLabel(new Label(name));
	}

	public LBJTextBoxBuilder disabled() {
		lbjTextBox.setEnabled(false);
		return this;
	}

	public LBJTextBoxBuilder label(String label) {
		lbjTextBox.getLabel().setText(label);
		return this;
	}

	public LBJTextBoxBuilder pattern(String regex) {
		lbjTextBox.getTextBox().setValidationPattern(Pattern.compile(regex));
		return this;
	}

	public LBJTextBoxBuilder addValidator(LBJValueValidator<String> validator) {
		lbjTextBox.addValidator(validator);
		return this;
	}

	public LBJTextBoxBuilder addUpdater(LBJValueUpdater<String> updater) {
		lbjTextBox.addUpdater(updater);
		return this;
	}

	public LBJTextBox build() {
		return lbjTextBox;
	}

}
