package gui.components;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class LBJLowerCaseStringValidatorTest {

	private LBJLowerCaseStringValidator tested = new LBJLowerCaseStringValidator();

	@Test
	public void testIsValidFalse() {
		assertFalse(tested.isValid("NBA"));
	}

	@Test
	public void testIsValidFalse1() {
		assertFalse(tested.isValid("TwoSetViolin"));
	}

	@Test
	public void testIsValidTrue() {
		assertTrue(tested.isValid("java"));
	}

}
