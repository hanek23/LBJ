package testutils.asserts;

import constants.NamingConventions;
import gui.components.LBJValueHolderComponent;
import gui.suppliers.LBJValidatorSupplier;
import gui.validators.LBJValueValidator;

public class LBJValueHolderComponentAssert extends LBJLabeledComponentAssert<LBJValueHolderComponent<?>> {

	public LBJValueHolderComponentAssert(LBJValueHolderComponent<?> actual, Class<?> selfType) {
		super(actual, selfType);
	}

	public static LBJValueHolderComponentAssert assertThat(LBJValueHolderComponent<?> actual) {
		return new LBJValueHolderComponentAssert(actual, LBJValueHolderComponentAssert.class);
	}

	public LBJValueHolderComponentAssert hasValidator(LBJValueValidator<?> validator) {
		if (!actual.getValidators().contains(validator)) {
			failWithMessage("Expecting component '%s' to have validator of type '%s', but it does not", actual,
					validator.getClass().getSimpleName());
		}
		return this;
	}

	private void isForClass(Class<?> clazz) {
		if (!actual.forType().equals(String.class)) {
			failWithMessage("Expecting component '%s' to be for type '%s' but it was '%s'", actual,
					clazz.getSimpleName(), actual.forType().getSimpleName());
		}

	}

	public LBJValueHolderComponentAssert hasStringRequiredValidator() {
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

}
