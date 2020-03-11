package gui.builders;

import com.googlecode.lanterna.gui2.Label;
import gui.components.LBJCheckBox;
import gui.components.LBJComponent;
import gui.forms.LBJForm;

public class LBJCheckBoxBuilder {

	private LBJCheckBox lbjCheckBox;

	public LBJCheckBoxBuilder(String name, LBJForm form) {
		lbjCheckBox = new LBJCheckBox(name, form);
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
		lbjCheckBox.setLabel(new Label(label));
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
