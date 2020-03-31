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

	public static final String PRIMARY_KEY_NAME_DEFAULT_VALUE = "id" + SEPARATOR;
	public static final String PRIMARY_KEY_CONSTRAINT_DEFAULT_VALUE = "P" + SEPARATOR;
	public static final String SEQUENCE_NAME_DEFAULT_VALUE = "SEQ" + SEPARATOR;
	public static final String INDEX_NAME = "I" + SEPARATOR + toNamingConvention(Labels.TABLE_NAME) + SEPARATOR
			+ toNamingConvention(Labels.COLUMN_NAME);

	public static final String FOREIGN_KEY_NAME = "F" + SEPARATOR
			+ toNamingConvention(Labels.ADD_COLUMN_REFERENCED_TABLE) + SEPARATOR
			+ toNamingConvention(Labels.ADD_COLUMN_REFERENCED_COLUMN);

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

}
