package gui.builders;

import com.googlecode.lanterna.gui2.Label;

import gui.components.LBJPlainLabel;
import gui.forms.LBJForm;

public class LBJPlainLabelBuilder {

	private LBJPlainLabel lbjPlainLabel;

	public LBJPlainLabelBuilder(String name, LBJForm form) {
		lbjPlainLabel = new LBJPlainLabel(name, form);
		lbjPlainLabel.setLabel(new Label(name));
	}

	public LBJPlainLabel build() {
		return lbjPlainLabel;
	}

}
