package testutils;

import org.junit.jupiter.api.BeforeEach;

import constants.NamingConventions;
import gui.suppliers.LBJFormSupplier;

/**
 * <ul>
 * <li>Resets instances of all forms supplied by {@link LBJFormSupplier} before
 * each test</li>
 * <li>Resets {@link NamingConventions}'s preferences to default values</li>
 * </ul>
 */
public abstract class LBJFormTest {

	@BeforeEach
	public void beforeEach() {
		LBJFormSupplier.reset();
		NamingConventions.setDefaultPreferences();
	}

}
