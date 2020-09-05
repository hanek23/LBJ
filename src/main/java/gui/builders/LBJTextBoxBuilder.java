package gui.builders;

import org.apache.commons.lang3.StringUtils;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.TextBox;

import constants.NamingConventions.LetterCase;
import gui.attribute.Attribute;
import gui.components.LBJTextBox;
import gui.components.LBJValueComponent;
import gui.forms.LBJForm;
import gui.updaters.LBJComponentUpdater;
import gui.updaters.LBJValueUpdater;
import gui.updaters.shared.LBJActivatorComponentUpdater;
import gui.updaters.shared.LBJDeactivatorComponentUpdater;
import gui.updaters.shared.LBJLowerCaseUpdater;
import gui.updaters.shared.LBJNamingConventionUpdater;
import gui.updaters.shared.LBJNoneCaseUpdater;
import gui.updaters.shared.LBJRequiredLabelComponentUpdater;
import gui.updaters.shared.LBJUpperCaseUpdater;
import gui.utils.BeanSupplier;
import gui.utils.LBJCaseUtils;
import gui.validators.LBJValueValidator;
import gui.validators.shared.LBJLowerCaseValidator;
import gui.validators.shared.LBJNoneCaseValidator;
import gui.validators.shared.LBJNumbersOnlyValidator;
import gui.validators.shared.LBJStringLengthValidator;
import gui.validators.shared.LBJStringRequiredValidator;
import gui.validators.shared.LBJUpperCaseValidator;

/**
 * Builder for {@link LBJTextBox}.
 */
public class LBJTextBoxBuilder {

	private LBJTextBox lbjTextBox;

	/**
	 * Starts building a {@link LBJTextBox}.
	 * 
	 * @param name will be used as component name as well as {@link Label}
	 * @param form on which is this component used.
	 * 
	 */
	public LBJTextBoxBuilder(String name, LBJForm form) {
		lbjTextBox = new LBJTextBox(name, form);
		lbjTextBox.setTextBox(new TextBox());
		lbjTextBox.setLabel(new Label(name));
	}

	public LBJTextBoxBuilder(Attribute attribute, LBJForm form) {
		this(attribute.getLabel(), form);
		addCaseUpdaterAndValidator(attribute.getLetterCase());
		if (StringUtils.isNotBlank(attribute.getNamingConvention())) {
			addNamingConventionUpdater(attribute.getNamingConvention());
		}
		if (attribute.isRequired()) {
			required();
		}
		if (attribute.shouldValidateLength()) {
			addLengthValidator();
		}
	}

	public LBJTextBoxBuilder readOnly() {
		lbjTextBox.getTextBox().setReadOnly(true);
		return this;
	}

	public LBJTextBoxBuilder disabled() {
		lbjTextBox.setEnabled(false);
		return this;
	}

	public LBJTextBoxBuilder hidden() {
		return size(0, 0);
	}

	public LBJTextBoxBuilder size(int columns, int rows) {
		lbjTextBox.getComponent().setPreferredSize(new TerminalSize(columns, rows));
		return this;
	}

	/**
	 * Value of this component when it is disabled.
	 */
	public LBJTextBoxBuilder defaultValue(String defaultValue) {
		lbjTextBox.setDefaultValue(defaultValue);
		return this;
	}

	/**
	 * Value of this component when it is initialized
	 */
	public LBJTextBoxBuilder value(String value) {
		lbjTextBox.setValue(value);
		return this;
	}

	/**
	 * Adds custom {@link LBJValueValidator} to the component
	 */
	public LBJTextBoxBuilder addValueValidator(LBJValueValidator<String> validator) {
		lbjTextBox.addValueValidator(validator);
		return this;
	}

	/**
	 * Adds custom {@link LBJValueUpdater} to the component
	 */
	public LBJTextBoxBuilder addValueUpdater(LBJValueUpdater<String> updater) {
		lbjTextBox.addValueUpdater(updater);
		return this;
	}

	/**
	 * Adds custom {@link LBJComponentUpdater} to the component
	 */
	public LBJTextBoxBuilder addUpdater(LBJComponentUpdater updater) {
		lbjTextBox.addUpdater(updater);
		return this;
	}

	/**
	 * Adds component (usually {@link Checkbox}) to this component so that when
	 * value of this activatorComponent changes to <code>true</code>, this component
	 * will get activated. Also adds {@link LBJActivatorComponentUpdater} which does
	 * the work.
	 * 
	 */
	public LBJTextBoxBuilder activatorComponent(LBJValueComponent<Boolean> activatorComponent) {
		lbjTextBox.setActivatorComponent(activatorComponent);
		addUpdater(BeanSupplier.get(LBJActivatorComponentUpdater.class));
		return this;
	}

	/**
	 * Adds component (usually {@link Checkbox}) to this component so that when
	 * value of this activatorComponent changes to <code>false</code>, this
	 * component will get activated. Also adds
	 * {@link LBJDeactivatorComponentUpdater} which does the work.
	 * 
	 */
	public LBJTextBoxBuilder deactivatorComponent(LBJValueComponent<Boolean> activatorComponent) {
		lbjTextBox.setActivatorComponent(activatorComponent);
		addUpdater(BeanSupplier.get(LBJDeactivatorComponentUpdater.class));
		return this;
	}

	/**
	 * Adds {@link LBJStringRequiredValidator} to this {@link LBJTextBox}
	 */
	public LBJTextBoxBuilder required() {
		addUpdater(BeanSupplier.get(LBJRequiredLabelComponentUpdater.class));
		return addValueValidator(BeanSupplier.get(LBJStringRequiredValidator.class));
	}

	/**
	 * Adds {@link LBJStringLengthValidator} to this {@link LBJTextBox}
	 */
	public LBJTextBoxBuilder addLengthValidator() {
		return addValueValidator(BeanSupplier.get(LBJStringLengthValidator.class));
	}

	/**
	 * Adds
	 * {@link LBJUpperCaseUpdater}/{@link LBJLowerCaseUpdater}/{@link LBJNoneCaseUpdater}
	 * and
	 * {@link LBJUpperCaseValidator}/{@link LBJLowerCaseValidator}/{@link LBJNoneCaseValidator}
	 * according to passed {@link LetterCase}.
	 */
	public LBJTextBoxBuilder addCaseUpdaterAndValidator(LetterCase letterCase) {
		addValueUpdater(LBJCaseUtils.caseUpdater(letterCase));
		return addValueValidator(LBJCaseUtils.getCaseValidator(letterCase));
	}

	/**
	 * Conditionally adds letter case validators with
	 * {@link #addCaseUpdaterAndValidator}
	 */
	public LBJTextBoxBuilder addCaseUpdaterAndValidator(LetterCase letterCase, boolean add) {
		if (!add) {
			return this;
		}
		return addCaseUpdaterAndValidator(letterCase);
	}

	/**
	 * Adds {@link LBJNumbersOnlyValidator}
	 */
	public LBJTextBoxBuilder numbersOnly() {
		addValueValidator(BeanSupplier.get(LBJNumbersOnlyValidator.class));
		return this;
	}

	/**
	 * Adds {@link LBJNamingConventionUpdater}
	 */
	public LBJTextBoxBuilder addNamingConventionUpdater(String namingConvention) {
		lbjTextBox.setNamingConvention(namingConvention);
		addValueUpdater(BeanSupplier.get(LBJNamingConventionUpdater.class));
		return this;
	}

	public LBJTextBox build() {
		lbjTextBox.getValueUpdaters().sort(LBJTextBoxBuilder::compareUpdaters);
		return lbjTextBox;
	}

	/**
	 * Case validators should always be the last one that gets called by
	 * {@link LBJValueComponent#update()}
	 */
	private static int compareUpdaters(LBJValueUpdater<String> u1, LBJValueUpdater<String> u2) {
		if (u1 instanceof LBJLowerCaseUpdater || u1 instanceof LBJUpperCaseUpdater) {
			return 1;
		}
		if (u2 instanceof LBJLowerCaseUpdater || u2 instanceof LBJUpperCaseUpdater) {
			return -1;
		}
		return 0;
	}

}
