package gui.components;

import static org.assertj.core.api.Assertions.assertThat;
import static testutils.asserts.LBJLabeledComponentAssert.assertThat;

import org.junit.jupiter.api.Test;

import com.googlecode.lanterna.TextColor;

import constants.Labels;
import gui.builders.LBJPlainLabelBuilder;
import testutils.LBJMockForm;

public class LBJPlainLabelTest {

	@Test
	public void testBuilder() {
		LBJPlainLabel tested = new LBJPlainLabelBuilder(Labels.GENERATE_FORM_DATABASES, new LBJMockForm()).build();
		assertThat(tested).hasLabel(Labels.GENERATE_FORM_DATABASES);
	}

	@Test
	public void testSetLabelColorByValidity() {
		LBJPlainLabel tested = new LBJPlainLabelBuilder(Labels.GENERATE_FORM_DATABASES, new LBJMockForm()).build();
		assertThat(tested).isValid();

		// There
		tested.setLabelColorByValidity(false);
		assertThat(tested.getLabel().getBackgroundColor()).isEqualTo(TextColor.ANSI.RED);

		// And back again
		tested.setLabelColorByValidity(true);
		assertThat(tested.getLabel().getBackgroundColor()).isEqualTo(null);
	}

}
