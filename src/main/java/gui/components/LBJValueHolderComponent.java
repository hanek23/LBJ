package gui.components;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.lanterna.gui2.CheckBox;
import com.googlecode.lanterna.gui2.Component;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.TextBox;

import gui.forms.LBJForm;
import gui.updaters.LBJValueUpdater;
import gui.validators.LBJValueValidator;

/**
 * Abstract base for {@link LBJComponent}s that hold some value T like
 * {@link String} or {@link Boolean} and also has a {@link Label} It provides
 * place for custom {@link LBJValueUpdater}s and {@link LBJValueValidators}s to
 * be added which LBJ Framework calls when they are needed.
 */
public abstract class LBJValueHolderComponent<T> extends LBJLabeledComponent {

	private List<LBJValueValidator<T>> validators;
	private List<LBJValueUpdater<T>> updaters;

	public LBJValueHolderComponent(String name, LBJForm form) {
		super(name, form);
	}

	/**
	 * Value of the {@link Component} used to implement this type.
	 */
	public abstract T getValue();

	/**
	 * Value of the {@link Component} used to implement this type, see
	 * {@link LBJValueHolderComponent#getComponent()}.
	 */
	public abstract void setValue(T value);

	/**
	 * Lanterna {@link Component} used to implement this type like {@link TextBox}
	 * for {@link String} or {@link CheckBox} for {@link Boolean}
	 */
	public abstract Component getComponent();

	/**
	 * If component is enabled iterates through all validators associated with this
	 * component and calls
	 * {@link LBJLabeledComponent#setLabelColorByValidity(boolean)} with result. If
	 * component is disabled it is considered valid so it returns <code>true</code>.
	 * 
	 * @return <code>true</code> if components passes all validations,
	 *         <code>false</code> otherwise.
	 */
	public boolean isValid() {
		if (!isEnabled()) {
			super.setLabelColorByValidity(true);
			return true;
		}
		boolean isValid = true;
		for (LBJValueValidator<T> validator : getValidators()) {
			isValid = validator.isValid(getValue()) && isValid;
		}
		super.setLabelColorByValidity(isValid);
		return isValid;
	}

	/**
	 * If component is enabled iterates throught all updaters associated with this
	 * component and updates value of this component.
	 */
	public void update() {
		if (!isEnabled()) {
			return;
		}
		for (LBJValueUpdater<T> updater : getUpdaters()) {
			updater.update(this);
		}
	}

	public List<LBJValueValidator<T>> getValidators() {
		if (validators == null) {
			validators = new ArrayList<>();
		}
		return validators;
	}

	public boolean addValidator(LBJValueValidator<T> validator) {
		return getValidators().add(validator);
	}

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
