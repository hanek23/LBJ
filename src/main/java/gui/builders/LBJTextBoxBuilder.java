package gui.builders;

import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.TextBox;

import gui.components.LBJTextBox;
import gui.forms.LBJForm;
import gui.updaters.LBJValueUpdater;
import gui.validators.LBJValueValidator;

public class LBJTextBoxBuilder {

	private LBJTextBox lbjTextBox;

	public LBJTextBoxBuilder(String name, LBJForm form) {
		lbjTextBox = new LBJTextBox(name, form);
		lbjTextBox.setTextBox(new TextBox());
		lbjTextBox.setLabel(new Label(name));
	}

	public LBJTextBoxBuilder disabled() {
		lbjTextBox.setEnabled(false);
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
