package constants;

/**
 * Naming conventions of table, column, primary key and foreign key names.
 * Includes basic pattern of names for example for primary key (id_) and their
 * capitalization (table name should be all upper case)
 */
public class NamingConventions {

	private NamingConventions() {
		// only static constants
	}

	public static final String SEPARATOR = "_";
	public static final String PRIMARY_KEY_NAME_DEFAULT_VALUE = "id" + SEPARATOR;
	public static final String PRIMARY_KEY_CONSTRAINT_DEFAULT_VALUE = "P" + SEPARATOR;
	public static final String SEQUENCE_NAME_DEFAULT_VALUE = "SEQ" + SEPARATOR;
	public static final String INDEX_NAME_DEFAULT_VALUE = "I" + SEPARATOR;
	public static final String FOREIGN_KEY_NAME_DEFAULT_VALUE = "F" + SEPARATOR;

	public static final LetterCase TABLE_NAME_CASE = LetterCase.UPPER;
	public static final LetterCase PRIMARY_KEY_NAME_CASE = LetterCase.LOWER;
	public static final LetterCase PRIMARY_KEY_CONSTRAINT_NAME_CASE = LetterCase.UPPER;
	public static final LetterCase SEQUENCE_NAME_CASE = LetterCase.UPPER;
	public static final LetterCase COLUMN_NAME_CASE = LetterCase.LOWER;
	public static final LetterCase DATA_TYPE_CASE = LetterCase.NONE;
	public static final LetterCase FOREIGN_KEY_NAME_CASE = LetterCase.UPPER;
	public static final LetterCase INDEX_NAME_CASE = LetterCase.UPPER;

	public enum LetterCase {
		UPPER, LOWER, NONE,
	}

}
