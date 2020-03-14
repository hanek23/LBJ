package gui.components;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import constants.Labels;
import gui.builders.LBJTextBoxBuilder;

public class LBJTextBoxTest {

	@Test
	public void testBuilder() {
		LBJTextBox tested = new LBJTextBoxBuilder(Labels.TABLE_NAME_FORM, new LBJMockForm()).build();
		assertNotNull(tested.getLabel());
		assertNotNull(tested.getTextBox());
		assertThat(tested.getName()).isEqualTo(Labels.TABLE_NAME_FORM);
		assertThat(tested.getLabel().getText()).isEqualTo(Labels.TABLE_NAME_FORM);
		assertTrue(tested.isEnabled());
	}

}
