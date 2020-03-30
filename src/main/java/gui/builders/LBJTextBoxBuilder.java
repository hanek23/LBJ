package gui.builders;

import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.TextBox;

import constants.NamingConventions.LetterCase;
import gui.components.LBJTextBox;
import gui.forms.LBJForm;
import gui.suppliers.LBJUpdaterSupplier;
import gui.suppliers.LBJValidatorSupplier;
import gui.updaters.LBJValueUpdater;
import gui.updaters.shared.LBJLowerCaseUpdater;
import gui.updaters.shared.LBJUpperCaseUpdater;
import gui.validators.LBJValueValidator;
import gui.validators.shared.LBJLowerCaseValidator;
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

	public LBJTextBoxBuilder disabled() {
		lbjTextBox.setEnabled(false);
		return this;
	}

	/**
	 * Adds custom validator to the component
	 */
	public LBJTextBoxBuilder addValidator(LBJValueValidator<String> validator) {
		lbjTextBox.addValidator(validator);
		return this;
	}

	/**
	 * Adds custom updater to the component
	 */
	public LBJTextBoxBuilder addUpdater(LBJValueUpdater<String> updater) {
		lbjTextBox.addUpdater(updater);
		return this;
	}

	/**
	 * Adds {@link LBJStringRequiredValidator} to this {@link LBJTextBox}
	 */
	public LBJTextBoxBuilder required() {
		return addValidator(LBJValidatorSupplier.getStringRequiredValidator());
	}

	/**
	 * Adds {@link LBJStringLengthValidator} to this {@link LBJTextBox}
	 */
	public LBJTextBoxBuilder addLengthValidator() {
		return addValidator(LBJValidatorSupplier.getStringLengthValidator());
	}

	/**
	 * Adds {@link LBJUpperCaseUpdater}/{@link LBJLowerCaseUpdater} and
	 * {@link LBJUpperCaseValidator}/{@link LBJLowerCaseValidator} according to
	 * passed {@link LetterCase}.
	 */
	public LBJTextBoxBuilder addCaseUpdaterAndValidator(LetterCase letterCase) {
		if (LetterCase.NONE == letterCase) {
			return this;
		}
		addUpdater(LBJUpdaterSupplier.caseUpdater(letterCase));
		return addValidator(LBJValidatorSupplier.getCaseValidator(letterCase));
	}

	/**
	 * Adds {@link LBJNumbersOnlyValidator}
	 */
	public LBJTextBoxBuilder numbersOnly() {
		addValidator(LBJValidatorSupplier.getNumbersOnlyValidator());
		return this;
	}

	public LBJTextBox build() {
		return lbjTextBox;
	}

}
