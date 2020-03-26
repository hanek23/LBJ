package constants;

import com.googlecode.lanterna.TerminalSize;

/**
 * Miscellaneous settings of either lanterna (number of columns etc.) or
 * databases constraints like maximum length of names
 */
public class Settings {

	private Settings() {
		// only static constants
	}

	// GUI
	public static final int GUI_NUMBER_OF_COLUMNS = 23;
	public static final int GUI_VERTICAL_SPACING = 1;

	// TERMINAL
	public static final TerminalSize TERMINAL_SIZE = new TerminalSize(100, 35);

	// GENERATOR
	public static final int MAX_LENGTH_OF_NAMES = 30;

}
