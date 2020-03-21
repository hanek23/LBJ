package gui.components;

import com.googlecode.lanterna.gui2.CheckBox;
import com.googlecode.lanterna.gui2.Component;
import com.googlecode.lanterna.gui2.Label;

import gui.forms.LBJForm;
import gui.updaters.LBJValueUpdater;
import gui.validators.LBJValueValidator;

/**
 * Main purpouse of this class is to encapsulate {@link CheckBox} and
 * {@link Label} in one class for ease of use. Since it extends
 * {@link LBJValueHolderComponent} it can have custom {@link LBJValueUpdater}s
 * and {@link LBJValueValidator}s which calls will be handled by LBJ framework
 * on each update of {@link LBJForm} that this component is on.
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
