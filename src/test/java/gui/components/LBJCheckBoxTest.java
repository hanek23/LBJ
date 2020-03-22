package gui.components;

import static testutils.asserts.LBJValueHolderComponentAssert.assertThat;

import org.junit.jupiter.api.Test;

import constants.Labels;
import gui.builders.LBJCheckBoxBuilder;
import testutils.LBJMockForm;

public class LBJCheckBoxTest {

	@Test
	public void testBuilder() {
		LBJCheckBoxBuilder builder = new LBJCheckBoxBuilder(Labels.CREATE_TABLE_DATABASES_ORACLE, new LBJMockForm());

		assertThat(builder.build()).isNotChecked();
		assertThat(builder.checked().build()).isChecked();
		assertThat(builder.build()).hasLabel(Labels.CREATE_TABLE_DATABASES_ORACLE);
	}

}
