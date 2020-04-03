package testutils.asserts;

import org.assertj.core.api.Assertions;
import org.assertj.core.util.Objects;

import constants.NamingConventions;
import gui.components.LBJValueComponent;
import gui.suppliers.LBJValidatorSupplier;
import gui.validators.LBJValueValidator;

public class LBJValueHolderComponentAssert extends LBJLabeledComponentAssert<LBJValueComponent<?>> {

	public LBJValueHolderComponentAssert(LBJValueComponent<?> actual, Class<?> selfType) {
		super(actual, selfType);
	}

	public static LBJValueHolderComponentAssert assertThat(LBJValueComponent<?> actual) {
		return new LBJValueHolderComponentAssert(actual, LBJValueHolderComponentAssert.class);
	}

	public LBJValueHolderComponentAssert hasValidator(LBJValueValidator<?> validator) {
		if (!actual.getValueValidators().contains(validator)) {
			failWithMessage("Expecting component '%s' to have validator of type '%s', but it does not", actual,
					validator.getClass().getSimpleName());
		}
		return this;
	}

	private void isForClass(Class<?> clazz) {
		if (!actual.forType().equals(clazz)) {
			failWithMessage("Expecting component '%s' to be for type '%s' but it was '%s'", actual,
					clazz.getSimpleName(), actual.forType().getSimpleName());
		}

	}

	public LBJValueHolderComponentAssert isRequired() {
		isNotNull();
		isForClass(String.class);
		hasValidator(LBJValidatorSupplier.getStringRequiredValidator());
		return this;
	}

	public LBJValueHolderComponentAssert hasLengthValidator() {
		isNotNull();
		isForClass(String.class);
		hasValidator(LBJValidatorSupplier.getStringLengthValidator());
		return this;
	}

	public LBJValueHolderComponentAssert hasCaseValidator(NamingConventions.LetterCase letterCase) {
		isNotNull();
		isForClass(String.class);
		hasValidator(LBJValidatorSupplier.getCaseValidator(letterCase));
		return this;
	}

	public LBJValueHolderComponentAssert isNumbersOnly() {
		isNotNull();
		isForClass(String.class);
		hasValidator(LBJValidatorSupplier.getNumbersOnlyValidator());
		return this;
	}

	public LBJValueHolderComponentAssert isBlank() {
		isNotNull();
		isForClass(String.class);
		Assertions.assertThat((String) actual.getValue()).isBlank();
		return this;
	}

	public LBJValueHolderComponentAssert isUpperCase() {
		isNotNull();
		isForClass(String.class);
		Assertions.assertThat((String) actual.getValue()).isUpperCase();
		return this;
	}

	public LBJValueHolderComponentAssert isLowerCase() {
		isNotNull();
		isForClass(String.class);
		Assertions.assertThat((String) actual.getValue()).isLowerCase();
		return this;
	}

	public LBJValueHolderComponentAssert hasValueEqualTo(Object expected) {
		isNotNull();
		if (!Objects.areEqual(actual.getValue(), expected)) {
			failWithMessage("Expecting component '%s' to have value of '%s' but insted it has value of '%s'", actual,
					expected, actual.getValue());
		}
		return this;
	}

	public LBJValueHolderComponentAssert isEqualToIgnoringCase(String expected) {
		isNotNull();
		isForClass(String.class);
		Assertions.assertThat((String) actual.getValue()).isEqualToIgnoringCase(expected);
		return this;
	}

	public LBJValueHolderComponentAssert isChecked() {
		isNotNull();
		isForClass(Boolean.class);
		if (!(Boolean) actual.getValue()) {
			failWithMessage("Expecting component '%s' to be checked but it is not", actual);
		}
		return this;
	}

	public LBJValueHolderComponentAssert isNotChecked() {
		isNotNull();
		isForClass(Boolean.class);
		if ((Boolean) actual.getValue()) {
			failWithMessage("Expecting component '%s' to NOT be checked but it is", actual);
		}
		return this;
	}

}
