package gui.builders;

import com.googlecode.lanterna.gui2.CheckBox;
import com.googlecode.lanterna.gui2.Label;

import gui.components.LBJCheckBox;
import gui.forms.LBJForm;

/**
 * Builder for {@link LBJCheckBox}.
 */
public class LBJCheckBoxBuilder {

	private LBJCheckBox lbjCheckBox;

	/**
	 * Starts building a {@link LBJCheckBox}. Default value is UNCHECKED! Use
	 * {@link LBJCheckBoxBuilder#checked()} for changing that.
	 * 
	 * @param name will be used as {@link Label}
	 * @param form on which is this component used.
	 * 
	 */
	public LBJCheckBoxBuilder(String name, LBJForm form) {
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

	public LBJCheckBox build() {
		return lbjCheckBox;
	}

}
