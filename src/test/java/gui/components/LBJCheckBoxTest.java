package gui.components;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import constants.Labels;
import gui.builders.LBJCheckBoxBuilder;

public class LBJCheckBoxTest {

	@Test
	public void testBuilder() {
		LBJCheckBoxBuilder builder = new LBJCheckBoxBuilder(Labels.CREATE_TABLE_DATABASES_ORACLE, new LBJMockForm());

		assertFalse(builder.build().isChecked());
		assertTrue(builder.checked().build().isChecked());
		assertThat(builder.build().getLabel().getText()).isEqualTo(Labels.CREATE_TABLE_DATABASES_ORACLE);
	}

}
