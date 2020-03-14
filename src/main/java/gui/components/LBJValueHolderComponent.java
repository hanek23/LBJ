package gui.components;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.lanterna.gui2.Component;

import gui.forms.LBJWizardForm;
import gui.updaters.LBJValueUpdater;
import gui.validators.LBJValueValidator;

public abstract class LBJValueHolderComponent<T> extends LBJLabeledComponent {

	private T value;
	private List<LBJValueValidator<T>> validators;
	private List<LBJValueUpdater<T>> updaters;

	public LBJValueHolderComponent(String name, LBJWizardForm form) {
		super(name, form);
	}

	@Override
	public boolean isValid() {
		boolean isValid = super.isValid();
		for (LBJValueValidator<T> validator : validators) {
			isValid = validator.isValid(value) && isValid;
		}
		super.setLabelColorByValidity(isValid);
		return isValid;
	}

	public abstract T getValue();

	public abstract void setValue(T value);

	public List<LBJValueValidator<T>> getValidators() {
		if (validators == null) {
			validators = new ArrayList<>();
		}
		return validators;
	}

	public boolean addValidator(LBJValueValidator<T> validator) {
		return getValidators().add(validator);
	}

	public abstract Component getComponent();

	public List<LBJValueUpdater<T>> getUpdaters() {
		if (updaters == null) {
			updaters = new ArrayList<>();
		}
		return updaters;
	}

	public boolean addUpdater(LBJValueUpdater<T> updater) {
		return getUpdaters().add(updater);
	}

}
