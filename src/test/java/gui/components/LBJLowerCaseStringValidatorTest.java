package gui.components;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

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
