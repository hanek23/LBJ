package gui.components;

import com.googlecode.lanterna.gui2.CheckBox;
import com.googlecode.lanterna.gui2.Component;

import gui.forms.LBJForm;

/**
 * Boolean implementation of {@link LBJValueHolderComponent} using
 * {@link CheckBox}.
 */
public class LBJCheckBox extends LBJValueHolderComponent<Boolean> {

	private CheckBox checkBox;

	public LBJCheckBox(String name, LBJForm form) {
		super(name, form);
	}

	@Override
	public Boolean getValue() {
		return checkBox.isChecked();
	}

	@Override
	public void setValue(Boolean value) {
		if (value == null) {
			throw new IllegalArgumentException("Cannot set null to checkbox value! name: " + getName());
		}
		checkBox.setChecked(value);
	}

	public void check() {
		setValue(true);
	}

	public void unCheck() {
		setValue(false);
	}

	public boolean isChecked() {
		return getValue();
	}

	public CheckBox getCheckBox() {
		return checkBox;
	}

	public void setCheckBox(CheckBox checkBox) {
		this.checkBox = checkBox;
	}

	@Override
	public Component getComponent() {
		return getCheckBox();
	}

	public boolean isFocused() {
		return getCheckBox().isFocused();
	}

}
