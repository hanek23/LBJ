package gui.builders;

import com.googlecode.lanterna.gui2.Label;

import gui.components.LBJPlainLabel;
import gui.forms.LBJWizardForm;

public class LBJPlainLabelBuilder {

	private LBJPlainLabel lbjPlainLabel;

	public LBJPlainLabelBuilder(String name, LBJWizardForm form) {
		lbjPlainLabel = new LBJPlainLabel(name, form);
		lbjPlainLabel.setLabel(new Label(name));
	}

	public LBJPlainLabelBuilder label(String label) {
		lbjPlainLabel.getLabel().setText(label);
		return this;
	}

	public LBJPlainLabel build() {
		return lbjPlainLabel;
	}

}
