package gui.components;

import java.lang.reflect.ParameterizedType;

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
 * place for custom {@link LBJValueUpdater}s and {@link LBJValueValidator}s to
 * be added which LBJ Framework calls when they are needed.
 */
public abstract class LBJValueComponent<T> extends LBJLabeledComponent {

	private List<LBJValueValidator<T>> valueValidators;
	private List<LBJValueUpdater<T>> valueUpdaters;

	public LBJValueComponent(String name, LBJForm form) {
		super(name, form);
	}

	/**
	 * Value of the {@link Component} used to implement this type.
	 */
	public abstract T getValue();

	/**
	 * Value of the {@link Component} used to implement this type, see
	 * {@link LBJValueComponent#getComponent()}.
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
		for (LBJValueValidator<T> validator : getValueValidators()) {
			isValid = validator.isValid(getValue()) && isValid;
		}
		super.setLabelColorByValidity(isValid);
		return isValid;
	}

	/**
	 * If component is enabled it iterates throught all updaters associated with
	 * this component and updates its value
	 */
	@Override
	public void update() {
		super.update();
		if (!isEnabled()) {
			return;
		}
		for (LBJValueUpdater<T> updater : getValueUpdaters()) {
			updater.update(this);
		}
	}

	@SuppressWarnings("unchecked")
	public Class<T> forType() {
		return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public List<LBJValueValidator<T>> getValueValidators() {
		if (valueValidators == null) {
			valueValidators = new ArrayList<>();
		}
		return valueValidators;
	}

	public boolean addValueValidator(LBJValueValidator<T> validator) {
		return getValueValidators().add(validator);
	}

	public boolean removeValueValidator(LBJValueValidator<T> validator) {
		return getValueValidators().remove(validator);
	}

	public List<LBJValueUpdater<T>> getValueUpdaters() {
		if (valueUpdaters == null) {
			valueUpdaters = new ArrayList<>();
		}
		return valueUpdaters;
	}

	public boolean addValueUpdater(LBJValueUpdater<T> updater) {
		return getValueUpdaters().add(updater);
	}

}
