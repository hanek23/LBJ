package testutils.asserts;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.util.Objects;

import gui.components.LBJComponent;
import gui.forms.LBJForm;
import gui.updaters.LBJFormUpdater;
import gui.validators.LBJFormValidator;

public class LBJFormAssert extends AbstractAssert<LBJFormAssert, LBJForm> {

	private LBJFormAssert(LBJForm actual, Class<?> selfType) {
		super(actual, selfType);
	}

	public static LBJFormAssert assertThat(LBJForm form) {
		return new LBJFormAssert(form, LBJFormAssert.class);
	}

	public LBJFormAssert isFocused() {
		isNotNull();
		if (!actual.isVisible()) {
			failWithMessage("Expecting form '%s' to be focused but it was not", actual);
		}
		return this;
	}

	public LBJFormAssert hasComponentWithName(String name) {
		isNotNull();
		for (LBJComponent component : actual.getComponents()) {
			if (Objects.areEqual(component.getName(), name)) {
				return this;
			}
		}
		failWithMessage("Expecting form '%s' to have component with name '%s' but it does not", actual, name);
		throw new AssertionError();
	}

	public LBJFormAssert hasValidator(LBJFormValidator<?> expectedValidator) {
		isNotNull();
		for (LBJFormValidator<?> actualValidator : actual.getValidators()) {
			if (Objects.areEqual(actualValidator, expectedValidator)) {
				return this;
			}
		}
		failWithMessage("Expecting form '%s' to have validator of type '%s' but it does not", actual,
				expectedValidator.getClass().getSimpleName());
		throw new AssertionError();
	}

	public LBJFormAssert hasUpdater(LBJFormUpdater<?> expectedUpdater) {
		isNotNull();
		for (LBJFormUpdater<?> actualUpdater : actual.getUpdaters()) {
			if (Objects.areEqual(actualUpdater, expectedUpdater)) {
				return this;
			}
		}
		failWithMessage("Expecting form '%s' to have updater of type '%s' but it does not", actual,
				expectedUpdater.getClass().getSimpleName());
		throw new AssertionError();
	}

	public LBJFormAssert isValid() {
		isNotNull();
		if (!actual.validate()) {
			failWithMessage("Expecting form '%s' to be valid but it is not", actual);
		}
		return this;
	}

	public LBJFormAssert isNotValid() {
		isNotNull();
		if (actual.validate()) {
			failWithMessage("Expecting form '%s' to not be valid but it is", actual);
		}
		return this;
	}

	public LBJFormAssert hasName(String name) {
		isNotNull();
		if (!Objects.areEqual(actual.toString(), name)) {
			failWithMessage("Expecting form '%s' to have a name '%s'", actual, name);
		}
		return this;
	}

}
