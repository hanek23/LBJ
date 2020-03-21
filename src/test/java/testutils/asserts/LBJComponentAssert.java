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
			failWithMessage("Was expecting that component '%s' is enabled but it was not", actual.getName());
		}
		return this;
	}

}
