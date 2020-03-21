package gui.validators.shared;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.googlecode.lanterna.TextColor;

import constants.Labels;
import gui.builders.LBJTextBoxBuilder;
import gui.components.LBJTextBox;
import gui.suppliers.LBJValidatorSupplier;
import testutils.LBJMockForm;

public class LBJLowerCaseValidatorTest {

	private LBJLowerCaseValidator tested = new LBJLowerCaseValidator();

	@Test
	public void testIsValidFalse() {
		assertFalse(tested.isValid("NBA"));
	}

	@Test
	public void testIsValidFalse1() {
		assertFalse(tested.isValid("TwoSetViolin"));
	}

	@Test
	public void testIsValidFalse2() {
		assertFalse(tested.isValid("Id_Nder_Container"));
	}

	@Test
	public void testIsValidTrue() {
		assertTrue(tested.isValid("java"));
	}

	@Test
	public void testIsValidTrue1() {
		assertTrue(tested.isValid("java_1_8_a"));
	}

	@Test
	public void testWithComponentTrue() {
		LBJTextBox textBox = new LBJTextBoxBuilder(Labels.TABLE_NAME, new LBJMockForm())
				.addValidator(LBJValidatorSupplier.getLowerCasevalidator()).build();
		textBox.setValue("table_name");

		assertTrue(textBox.isValid());
		// null is a default color AKA no color at all
		assertThat(textBox.getLabel().getBackgroundColor()).isEqualTo(null);

	}

	@Test
	public void testWithComponentFalse() {
		LBJTextBox textBox = new LBJTextBoxBuilder(Labels.TABLE_NAME, new LBJMockForm())
				.addValidator(LBJValidatorSupplier.getLowerCasevalidator()).build();
		textBox.setValue("table_namE");

		assertFalse(textBox.isValid());
		assertThat(textBox.getLabel().getBackgroundColor()).isEqualTo(TextColor.ANSI.RED);
	}

}
