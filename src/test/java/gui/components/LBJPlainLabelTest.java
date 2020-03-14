package gui.components;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.googlecode.lanterna.TextColor;

import constants.Labels;
import gui.builders.LBJPlainLabelBuilder;

public class LBJPlainLabelTest {

	private LBJPlainLabel tested;

	@BeforeEach
	public void beforeEach() {
		tested = new LBJPlainLabelBuilder(Labels.CREATE_TABLE_DATABASES, new LBJMockForm()).build();
	}

	@Test
	public void testBuilder() {
		assertThat(tested.getLabel().getText()).isEqualTo(Labels.CREATE_TABLE_DATABASES);
	}

	@Test
	public void testSetLabelColorByValidity() {
		// null is default color AKA no color at all
		assertThat(tested.getLabel().getBackgroundColor()).isEqualTo(null);

		// There
		tested.setLabelColorByValidity(false);
		assertThat(tested.getLabel().getBackgroundColor()).isEqualTo(TextColor.ANSI.RED);

		// And back again
		tested.setLabelColorByValidity(true);
		assertThat(tested.getLabel().getBackgroundColor()).isEqualTo(null);
	}

}
