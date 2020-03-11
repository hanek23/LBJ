package gui.components;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.Label;

import gui.forms.LBJEntityForm;
import gui.forms.LBJForm;
import gui.validators.LBJValidator;

public abstract class LBJValueHolderComponent<T> extends LBJLabeledComponent {

	private T value;
	private List<LBJValidator<T>> validators;

	public LBJValueHolderComponent(String name, LBJForm form) {
		super(name, form);
	}

	public boolean isValid() {
		boolean isValid = true;
		for (LBJValidator<T> validator : validators) {
			isValid = validator.isValid(value) && isValid;
		}
		if (isValid) {
			getLabel().setBackgroundColor(TextColor.ANSI.DEFAULT);
		} else {
			getLabel().setBackgroundColor(TextColor.ANSI.RED);
		}
		return isValid;
	}

	public abstract T getValue();

	public abstract void setValue(T value);

	public List<LBJValidator<T>> getValidators() {
		if (validators == null) {
			validators = new ArrayList<>();
		}
		return validators;
	}

	public boolean addValidator(LBJValidator<T> validator) {
		return getValidators().add(validator);
	}

}
