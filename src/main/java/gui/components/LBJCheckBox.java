package gui.components;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.lanterna.gui2.CheckBox;
import com.googlecode.lanterna.gui2.Component;

import gui.forms.LBJWizardForm;

public class LBJCheckBox extends LBJValueHolderComponent<Boolean> {

	private List<LBJComponent> relatedComponents;
	private CheckBox checkBox;

	public LBJCheckBox(String name, LBJWizardForm form) {
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
		for (LBJComponent component : relatedComponents) {
			component.setEnabled(value);
		}
		checkBox.setChecked(value);
	}

	public boolean isChecked() {
		return getValue();
	}

	public List<LBJComponent> getRelatedComponents() {
		if (relatedComponents == null) {
			relatedComponents = new ArrayList<>();
		}
		return relatedComponents;
	}

	public boolean addRelatedComponent(LBJComponent relatedComponent) {
		return getRelatedComponents().add(relatedComponent);
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

}
