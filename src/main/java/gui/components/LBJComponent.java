package gui.components;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.Label;

public abstract class LBJComponent<T> {

	private String name;
	private Label label;
	private T value;
	private boolean enabled;
	private LBJForm form;
	private List<LBJValidator<T>> validators;

	public boolean isValid() {
		boolean isValid = true;
		for (LBJValidator<T> validator : validators) {
			isValid = validator.isValid(value) && isValid;
		}
		if (isValid) {
			label.setBackgroundColor(TextColor.ANSI.DEFAULT);
		} else {
			label.setBackgroundColor(TextColor.ANSI.RED);
		}
		return isValid;
	}

	public Label getLabel() {
		return label;
	}

	public void setLabel(Label label) {
		this.label = label;
	}

	public abstract T getValue();

	public abstract void setValue(T value);

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public LBJForm getForm() {
		return form;
	}

	public void setForm(LBJForm form) {
		this.form = form;
	}

	public List<LBJValidator<T>> getValidators() {
		if (validators == null) {
			validators = new ArrayList<>();
		}
		return validators;
	}

	public boolean addValidator(LBJValidator<T> validator) {
		return getValidators().add(validator);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " with name: " + name;
	}

}
