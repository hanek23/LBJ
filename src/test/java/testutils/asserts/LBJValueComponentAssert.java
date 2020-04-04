package testutils.asserts;

import org.assertj.core.api.Assertions;
import org.assertj.core.util.Objects;

import constants.NamingConventions;
import gui.components.LBJValueComponent;
import gui.suppliers.LBJValidatorSupplier;
import gui.validators.LBJValueValidator;

public class LBJValueComponentAssert extends LBJLabeledComponentAssert<LBJValueComponent<?>> {

	public LBJValueComponentAssert(LBJValueComponent<?> actual, Class<?> selfType) {
		super(actual, selfType);
	}

	public static LBJValueComponentAssert assertThat(LBJValueComponent<?> actual) {
		return new LBJValueComponentAssert(actual, LBJValueComponentAssert.class);
	}

	public LBJValueComponentAssert hasValidator(LBJValueValidator<?> validator) {
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

	public LBJValueComponentAssert isRequired() {
		isNotNull();
		isForClass(String.class);
		hasValidator(LBJValidatorSupplier.getStringRequiredValidator());
		return this;
	}

	public LBJValueComponentAssert hasLengthValidator() {
		isNotNull();
		isForClass(String.class);
		hasValidator(LBJValidatorSupplier.getStringLengthValidator());
		return this;
	}

	public LBJValueComponentAssert hasCaseValidator(NamingConventions.LetterCase letterCase) {
		isNotNull();
		isForClass(String.class);
		hasValidator(LBJValidatorSupplier.getCaseValidator(letterCase));
		return this;
	}

	public LBJValueComponentAssert isNumbersOnly() {
		isNotNull();
		isForClass(String.class);
		hasValidator(LBJValidatorSupplier.getNumbersOnlyValidator());
		return this;
	}

	public LBJValueComponentAssert isBlank() {
		isNotNull();
		isForClass(String.class);
		Assertions.assertThat((String) actual.getValue()).isBlank();
		return this;
	}

	public LBJValueComponentAssert isUpperCase() {
		isNotNull();
		isForClass(String.class);
		Assertions.assertThat((String) actual.getValue()).isUpperCase();
		return this;
	}

	public LBJValueComponentAssert isLowerCase() {
		isNotNull();
		isForClass(String.class);
		Assertions.assertThat((String) actual.getValue()).isLowerCase();
		return this;
	}

	public LBJValueComponentAssert hasValueEqualTo(Object expected) {
		isNotNull();
		if (!Objects.areEqual(actual.getValue(), expected)) {
			failWithMessage("Expecting component '%s' to have value of '%s' but insted it has value of '%s'", actual,
					expected, actual.getValue());
		}
		return this;
	}

	public LBJValueComponentAssert isEqualToIgnoringCase(String expected) {
		isNotNull();
		isForClass(String.class);
		Assertions.assertThat((String) actual.getValue()).isEqualToIgnoringCase(expected);
		return this;
	}

	public LBJValueComponentAssert isChecked() {
		isNotNull();
		isForClass(Boolean.class);
		if (!(Boolean) actual.getValue()) {
			failWithMessage("Expecting component '%s' to be checked but it is not", actual);
		}
		return this;
	}

	public LBJValueComponentAssert isNotChecked() {
		isNotNull();
		isForClass(Boolean.class);
		if ((Boolean) actual.getValue()) {
			failWithMessage("Expecting component '%s' to NOT be checked but it is", actual);
		}
		return this;
	}

}
