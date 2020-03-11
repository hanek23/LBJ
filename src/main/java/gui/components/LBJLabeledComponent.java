package gui.components;

import com.googlecode.lanterna.gui2.Label;

import gui.forms.LBJEntityForm;
import gui.forms.LBJForm;

public abstract class LBJLabeledComponent extends LBJComponent {

	public LBJLabeledComponent(String name, LBJForm form) {
		super(name, form);
	}

	private Label label;

	public Label getLabel() {
		return label;
	}

	public void setLabel(Label label) {
		this.label = label;
	}

}
