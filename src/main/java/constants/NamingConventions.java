package constants;

import org.apache.commons.lang3.StringUtils;

import main.LBJ;

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
	public static final String START = "€{";
	public static final String END = "}€";

	// PREFERENCE KEYS
	private static final String PKEY_START = "naming_convention" + SEPARATOR;
	public static final String PKEY_PRIMARY_KEY_NAME = PKEY_START + "primary_key_name";
	public static final String PKEY_PRIMARY_KEY_CONSTRAINT_NAME = PKEY_START + "primary_key_constraint_name";
	public static final String PKEY_SEQUENCE_NAME = PKEY_START + "sequence_name";
	public static final String PKEY_INDEX_NAME = PKEY_START + "index_name";
	public static final String PKEY_FOREIGN_KEY_NAME = PKEY_START + "foreign_key_name";

	// DEFAULT VALUES
	public static final String DEFAULT_PRIMARY_KEY_NAME = "id" + SEPARATOR + toNamingConvention(Labels.TABLE_NAME);
	public static final String DEFAULT_PRIMARY_KEY_CONSTRAINT_NAME = "P" + SEPARATOR
			+ toNamingConvention(Labels.TABLE_NAME);
	public static final String DEFAULT_SEQUENCE_NAME = "SEQ" + SEPARATOR + toNamingConvention(Labels.TABLE_NAME);
	public static final String DEFAULT_INDEX_NAME = "I" + SEPARATOR + toNamingConvention(Labels.TABLE_NAME) + SEPARATOR
			+ toNamingConvention(Labels.COLUMN_NAME);
	public static final String DEFAULT_FOREIGN_KEY_NAME = "F" + SEPARATOR
			+ toNamingConvention(Labels.ADD_COLUMN_REFERENCED_TABLE) + SEPARATOR
			+ toNamingConvention(Labels.ADD_COLUMN_REFERENCED_COLUMN);

	// LETTER CASE
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

	public static String toNamingConvention(String name) {
		name = StringUtils.upperCase(name);
		return START + StringUtils.replace(name, " ", SEPARATOR) + END;
	}

	public static String[] fromNamingConvention(String namingConvention) {
		namingConvention = StringUtils.lowerCase(namingConvention);
		namingConvention = StringUtils.replace(namingConvention, SEPARATOR, " ");
		return StringUtils.substringsBetween(namingConvention, START, END);
	}

	public static void setDefaultPreferences() {
		LBJ.preferences.put(PKEY_PRIMARY_KEY_NAME, DEFAULT_PRIMARY_KEY_NAME);
		LBJ.preferences.put(PKEY_PRIMARY_KEY_CONSTRAINT_NAME, DEFAULT_PRIMARY_KEY_CONSTRAINT_NAME);
		LBJ.preferences.put(PKEY_SEQUENCE_NAME, DEFAULT_SEQUENCE_NAME);
		LBJ.preferences.put(PKEY_INDEX_NAME, DEFAULT_INDEX_NAME);
		LBJ.preferences.put(PKEY_FOREIGN_KEY_NAME, DEFAULT_FOREIGN_KEY_NAME);
	}

	/**
	 * @return either default value for naming primary key name or preset one
	 */
	public static String getPrimaryKeyName() {
		return LBJ.preferences.get(PKEY_PRIMARY_KEY_NAME, DEFAULT_PRIMARY_KEY_NAME);
	}

	/**
	 * @return either default value for naming primary key constraint or preset one
	 */
	public static String getPrimaryKeyConstraintName() {
		return LBJ.preferences.get(PKEY_PRIMARY_KEY_CONSTRAINT_NAME, DEFAULT_PRIMARY_KEY_CONSTRAINT_NAME);
	}

	/**
	 * @return either default value for naming sequence name or preset one
	 */
	public static String getSequenceName() {
		return LBJ.preferences.get(PKEY_SEQUENCE_NAME, DEFAULT_SEQUENCE_NAME);
	}

	/**
	 * @return either default value for naming index name or preset one
	 */
	public static String getIndexName() {
		return LBJ.preferences.get(PKEY_INDEX_NAME, DEFAULT_INDEX_NAME);
	}

	/**
	 * @return either default value for naming foreign key nem or preset one
	 */
	public static String getForeignKeyName() {
		return LBJ.preferences.get(PKEY_FOREIGN_KEY_NAME, DEFAULT_FOREIGN_KEY_NAME);
	}

}
