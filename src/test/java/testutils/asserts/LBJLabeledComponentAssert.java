package testutils.asserts;

import org.assertj.core.util.Objects;

import gui.components.LBJLabeledComponent;

public class LBJLabeledComponentAssert<COMPONENT extends LBJLabeledComponent> extends LBJComponentAssert<COMPONENT> {

	public LBJLabeledComponentAssert(COMPONENT actual, Class<?> selfType) {
		super(actual, selfType);
	}

	public static LBJLabeledComponentAssert<LBJLabeledComponent> assertThat(LBJLabeledComponent actual) {
		return new LBJLabeledComponentAssert<LBJLabeledComponent>(actual, LBJLabeledComponentAssert.class);
	}

	public LBJLabeledComponentAssert<COMPONENT> hasLabel(String label) {
		isNotNull();
		if (!Objects.areEqual(actual.getLabel().getText(), label)) {
			failWithMessage("Expecting component's label to be '%s' but was '%s'", label, actual.getLabel().getText());
		}
		return this;
	}

}
