package gui.components;

import java.lang.reflect.ParameterizedType;

import java.util.HashSet;
import java.util.Set;

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

	private Set<LBJValueValidator<T>> validators;
	private Set<LBJValueUpdater<T>> updaters;

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

	public abstract boolean isFocused();

	/**
	 * Visible for testing!
	 */
	@SuppressWarnings("unchecked")
	public void setRawValue(Object value) {
		setValue((T) value);
	}

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

	@SuppressWarnings("unchecked")
	public Class<T> forType() {
		return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public Set<LBJValueValidator<T>> getValidators() {
		if (validators == null) {
			validators = new HashSet<>();
		}
		return validators;
	}

	public boolean addValidator(LBJValueValidator<T> validator) {
		return getValidators().add(validator);
	}

	public boolean removeValidator(LBJValueValidator<T> validator) {
		return getValidators().remove(validator);
	}

	public Set<LBJValueUpdater<T>> getUpdaters() {
		if (updaters == null) {
			updaters = new HashSet<>();
		}
		return updaters;
	}

	public boolean addUpdater(LBJValueUpdater<T> updater) {
		return getUpdaters().add(updater);
	}

}
