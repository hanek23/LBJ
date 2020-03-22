package testutils.asserts;

import org.assertj.core.util.Objects;

import com.googlecode.lanterna.TextColor;

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

	public LBJLabeledComponentAssert<COMPONENT> isValid() {
		isNotNull();
		actual.getForm().validate();
		// null is a default AKA valid color (red would be invalid)
		if (actual.getLabel().getBackgroundColor() != null) {
			failWithMessage("Expecting component '%s' to be valid but it is not", actual);
		}
		return this;
	}

	public LBJLabeledComponentAssert<COMPONENT> isNotValid() {
		isNotNull();
		actual.getForm().validate();
		if (!Objects.areEqual(actual.getLabel().getBackgroundColor(), TextColor.ANSI.RED)) {
			failWithMessage("Expecting component '%s' to NOT be valid but it is", actual);
		}
		return this;
	}

}
