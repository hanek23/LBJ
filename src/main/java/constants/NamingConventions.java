package constants;

import org.apache.commons.lang3.StringUtils;

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
	// NAMING CONVENTIONS
	private static final String PKEY_START_NAMING_CONVENTION = "naming_convention" + SEPARATOR;
	public static final String PKEY_PRIMARY_KEY_NAME = PKEY_START_NAMING_CONVENTION + "primary_key_name";
	public static final String PKEY_PRIMARY_KEY_CONSTRAINT_NAME = PKEY_START_NAMING_CONVENTION
			+ "primary_key_constraint_name";
	public static final String PKEY_SEQUENCE_NAME = PKEY_START_NAMING_CONVENTION + "sequence_name";
	public static final String PKEY_INDEX_NAME = PKEY_START_NAMING_CONVENTION + "index_name";
	public static final String PKEY_FOREIGN_KEY_NAME = PKEY_START_NAMING_CONVENTION + "foreign_key_name";
	// DEFAULT NAMING CONVENTIONS VALUES
	public static final String DEFAULT_PRIMARY_KEY_NAME = "id" + SEPARATOR + toNamingConvention(Labels.TABLE_NAME);
	public static final String DEFAULT_PRIMARY_KEY_CONSTRAINT_NAME = "P" + SEPARATOR
			+ toNamingConvention(Labels.TABLE_NAME);
	public static final String DEFAULT_SEQUENCE_NAME = "SEQ" + SEPARATOR + toNamingConvention(Labels.TABLE_NAME);
	public static final String DEFAULT_INDEX_NAME = "I" + SEPARATOR + toNamingConvention(Labels.TABLE_NAME) + SEPARATOR
			+ toNamingConvention(Labels.COLUMN_NAME);
	public static final String DEFAULT_FOREIGN_KEY_NAME = "F" + SEPARATOR + toNamingConvention(Labels.TABLE_NAME)
			+ SEPARATOR + toNamingConvention(Labels.REFERENCED_TABLE) + SEPARATOR
			+ toNamingConvention(Labels.COLUMN_NAME);

	// LETTER CASE
	private static final String PKEY_START_LETTER_CASE = "letter_case" + SEPARATOR;
	public static final String PKEY_TABLE_NAME_CASE = PKEY_START_LETTER_CASE + "table_name";
	public static final String PKEY_PRIMARY_KEY_NAME_CASE = PKEY_START_LETTER_CASE + "primary_key_name";
	public static final String PKEY_PRIMARY_KEY_CONSTRAINT_NAME_CASE = PKEY_START_LETTER_CASE
			+ "primary_key_constraint_name";
	public static final String PKEY_SEQUENCE_NAME_CASE = PKEY_START_LETTER_CASE + "sequence_name";
	public static final String PKEY_COLUMN_NAME_CASE = PKEY_START_LETTER_CASE + "column_name";
	public static final String PKEY_DATA_TYPE_CASE = PKEY_START_LETTER_CASE + "data_type";
	public static final String PKEY_FOREIGN_KEY_NAME_CASE = PKEY_START_LETTER_CASE + "foreign_key_name";
	public static final String PKEY_INDEX_NAME_CASE = PKEY_START_LETTER_CASE + "index_name";
	// DEFAULT LETTER CASE
	public static final LetterCase DEFAULT_TABLE_NAME_CASE = LetterCase.UPPER;
	public static final LetterCase DEFAULT_PRIMARY_KEY_NAME_CASE = LetterCase.LOWER;
	public static final LetterCase DEFAULT_PRIMARY_KEY_CONSTRAINT_NAME_CASE = LetterCase.UPPER;
	public static final LetterCase DEFAULT_SEQUENCE_NAME_CASE = LetterCase.UPPER;
	public static final LetterCase DEFAULT_COLUMN_NAME_CASE = LetterCase.LOWER;
	public static final LetterCase DEFAULT_DATA_TYPE_CASE = LetterCase.NONE;
	public static final LetterCase DEFAULT_FOREIGN_KEY_NAME_CASE = LetterCase.UPPER;
	public static final LetterCase DEFAULT_INDEX_NAME_CASE = LetterCase.UPPER;

	// DROP TABLE COLUMN PREFERENCES
	private static final String PKEY_START_DROP_TABLE_COLUMN_PREFERENCES = "drop_table_column_preferences" + SEPARATOR;
	public static final String PKEY_USE_LETTER_CASE_CONVENTIONS = PKEY_START_DROP_TABLE_COLUMN_PREFERENCES
			+ "use_letter_case_conventions";
	public static final String PKEY_COPY_TABLE_NAME = PKEY_START_DROP_TABLE_COLUMN_PREFERENCES + "copy_table_name";
	// DEFAULT DROP TABLE COLUMN PREFERENCES
	public static final boolean DEFAULT_USE_LETTER_CASE_CONVENTIONS = false;
	public static final boolean DEFAULT_COPY_TABLE_NAME = false;

	public enum LetterCase {
		UPPER, LOWER, NONE;

		public static LetterCase fromString(String name) {
			for (LetterCase value : LetterCase.values()) {
				if (value.toString().equals(name)) {
					return value;
				}
			}
			throw new IllegalArgumentException("Not a valid value of LETTER CASE: " + name);
		}
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

}
