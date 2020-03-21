package gui.validators.shared;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.googlecode.lanterna.TextColor;

import constants.Labels;
import gui.builders.LBJTextBoxBuilder;
import gui.components.LBJTextBox;
import testutils.LBJMockForm;

public class LBJStringLengthValidatorTest {

	private LBJStringLengthValidator tested = new LBJStringLengthValidator();

	@Test
	public void testIsValid40() {
		assertFalse(tested.isValid("SC30 is the greatest shooter of all time"));
	}

	@Test
	public void testIsValid31() {
		assertFalse(tested.isValid("Rachmaninoff - Piano Concerto 2"));
	}

	@Test
	public void testIsValid30() {
		assertTrue(tested.isValid("Inky Johnson is a bad somebody"));
	}

	@Test
	public void testIsValid16() {
		assertTrue(tested.isValid("STAY OF THE WEED"));
	}

	@ParameterizedTest
	@ValueSource(strings = { "Inky Johnson is a bad somebody", "STAY OF THE WEED" })
	public void testWithComponentTrue(String validValue) {
		LBJTextBox textBox = new LBJTextBoxBuilder(Labels.TABLE_NAME, new LBJMockForm()).addLengthValidator().build();
		textBox.setValue(validValue);

		assertTrue(textBox.isValid());
		// null is a default color AKA no color at all
		assertThat(textBox.getLabel().getBackgroundColor()).isEqualTo(null);

	}

	@ParameterizedTest
	@ValueSource(strings = { "Rachmaninoff - Piano Concerto 2", "SC30 is the greatest shooter of all time" })
	public void testWithComponentFalse(String invalidValue) {
		LBJTextBox textBox = new LBJTextBoxBuilder(Labels.TABLE_NAME, new LBJMockForm()).addLengthValidator().build();

		textBox.setValue(invalidValue);

		assertFalse(textBox.isValid());
		assertThat(textBox.getLabel().getBackgroundColor()).isEqualTo(TextColor.ANSI.RED);
	}

}
