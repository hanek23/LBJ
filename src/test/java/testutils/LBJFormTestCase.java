package testutils;

import org.junit.jupiter.api.BeforeEach;

import constants.NamingConventions;
import gui.suppliers.LBJFormSupplier;
import gui.utils.BeanSupplier;

/**
 * <ul>
 * <li>Resets instances of all forms supplied by {@link LBJFormSupplier} before
 * each test</li>
 * <li>Resets {@link NamingConventions}'s preferences to default values</li>
 * </ul>
 */
public abstract class LBJFormTestCase extends LBJTestCase {

	@BeforeEach
	public void beforeEach() {
		super.beforeEach();
		LBJFormSupplier.reset();
		NamingConventions.setDefaultPreferences();
	}

}
