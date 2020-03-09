package gui.components;

import java.util.List;

import com.googlecode.lanterna.gui2.CheckBox;

public class LBJCheckBox extends LBJComponent<Boolean> {

	private Boolean defaultValue;
	private List<LBJComponent<?>> relatedComponents;
	private CheckBox checkBox;

	@Override
	public Boolean getValue() {
		return checkBox.isChecked();
	}

	@Override
	public void setValue(Boolean value) {
		if (value == null) {
			throw new IllegalArgumentException("Cannot set null to checkbox value! name: " + getName());
		}
		if (value.booleanValue()) {
			for (LBJComponent<?> component : relatedComponents) {
				component.setEnabled(true);
			}
		}
		checkBox.setChecked(value);
	}

	public boolean isChecked() {
		return false;
	}

	public Boolean getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(Boolean defaultValue) {
		this.defaultValue = defaultValue;
	}

	public List<LBJComponent<?>> getRelatedComponents() {
		return relatedComponents;
	}

	public void setRelatedComponents(List<LBJComponent<?>> relatedComponents) {
		this.relatedComponents = relatedComponents;
	}

	public CheckBox getCheckBox() {
		return checkBox;
	}

	public void setCheckBox(CheckBox checkBox) {
		this.checkBox = checkBox;
	}

}
