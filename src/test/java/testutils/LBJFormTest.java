package testutils;

import org.junit.jupiter.api.BeforeEach;

import gui.suppliers.LBJFormSupplier;

/**
 * Resets instances of all forms supplied by {@link LBJFormSupplier} before each
 * test.
 */
public abstract class LBJFormTest {

	@BeforeEach
	public void beforeEach() {
		LBJFormSupplier.reset();
	}

}
