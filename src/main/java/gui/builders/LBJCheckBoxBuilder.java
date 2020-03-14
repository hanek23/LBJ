package gui.builders;

import com.googlecode.lanterna.gui2.CheckBox;
import com.googlecode.lanterna.gui2.Label;

import gui.components.LBJCheckBox;
import gui.components.LBJComponent;
import gui.forms.LBJWizardForm;

public class LBJCheckBoxBuilder {

	private LBJCheckBox lbjCheckBox;

	public LBJCheckBoxBuilder(String name, LBJWizardForm form) {
		lbjCheckBox = new LBJCheckBox(name, form);
		lbjCheckBox.setCheckBox(new CheckBox());
		lbjCheckBox.setLabel(new Label(name));
	}

	public LBJCheckBoxBuilder checked() {
		lbjCheckBox.getCheckBox().setChecked(true);
		return this;
	}

	public LBJCheckBoxBuilder notChecked() {
		lbjCheckBox.getCheckBox().setChecked(false);
		return this;
	}

	public LBJCheckBoxBuilder label(String label) {
		lbjCheckBox.getLabel().setText(label);
		return this;
	}

	public LBJCheckBoxBuilder addRelatedComponent(LBJComponent relatedComponent) {
		lbjCheckBox.addRelatedComponent(relatedComponent);
		return this;
	}

	public LBJCheckBox build() {
		return lbjCheckBox;
	}

}
