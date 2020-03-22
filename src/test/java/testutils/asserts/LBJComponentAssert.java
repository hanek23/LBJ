package testutils.asserts;

import org.assertj.core.api.AbstractAssert;

import gui.components.LBJComponent;

public class LBJComponentAssert<COMPONENT extends LBJComponent>
		extends AbstractAssert<LBJComponentAssert<COMPONENT>, COMPONENT> {

	public LBJComponentAssert(COMPONENT actual, Class<?> selfType) {
		super(actual, selfType);
	}

	public static LBJComponentAssert<LBJComponent> assertThat(LBJComponent actual) {
		return new LBJComponentAssert<LBJComponent>(actual, LBJComponentAssert.class);
	}

	public LBJComponentAssert<COMPONENT> isEnabled() {
		isNotNull();
		if (!actual.isEnabled()) {
			failWithMessage("Expecting component '%s' to be enabled but it is not", actual.getName());
		}
		return this;
	}

	public LBJComponentAssert<COMPONENT> isNotEnabled() {
		isNotNull();
		if (actual.isEnabled()) {
			failWithMessage("Expecting component '%s' to NOT be enabled but it is", actual.getName());
		}
		return this;
	}

}
