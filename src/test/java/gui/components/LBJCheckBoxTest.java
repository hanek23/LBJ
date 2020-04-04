package gui.components;

import static testutils.asserts.LBJValueComponentAssert.assertThat;

import org.junit.jupiter.api.Test;

import constants.Labels;
import gui.builders.LBJCheckBoxBuilder;
import testutils.LBJMockForm;

public class LBJCheckBoxTest {

	@Test
	public void testBuilder() {
		LBJCheckBoxBuilder builder = new LBJCheckBoxBuilder(Labels.GENERATE_FORM_DATABASES_ORACLE, new LBJMockForm());

		assertThat(builder.build()).isNotChecked();
		assertThat(builder.checked().build()).isChecked();
		assertThat(builder.build()).hasLabel(Labels.GENERATE_FORM_DATABASES_ORACLE);
	}

}
