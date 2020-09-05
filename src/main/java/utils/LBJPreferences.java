package utils;

import static constants.NamingConventions.DEFAULT_COLUMN_NAME_CASE;
import static constants.NamingConventions.DEFAULT_COPY_TABLE_NAME;
import static constants.NamingConventions.DEFAULT_DATA_TYPE_CASE;
import static constants.NamingConventions.DEFAULT_FOREIGN_KEY_NAME;
import static constants.NamingConventions.DEFAULT_FOREIGN_KEY_NAME_CASE;
import static constants.NamingConventions.DEFAULT_INDEX_NAME;
import static constants.NamingConventions.DEFAULT_INDEX_NAME_CASE;
import static constants.NamingConventions.DEFAULT_PRIMARY_KEY_CONSTRAINT_NAME;
import static constants.NamingConventions.DEFAULT_PRIMARY_KEY_CONSTRAINT_NAME_CASE;
import static constants.NamingConventions.DEFAULT_PRIMARY_KEY_NAME;
import static constants.NamingConventions.DEFAULT_PRIMARY_KEY_NAME_CASE;
import static constants.NamingConventions.DEFAULT_SEQUENCE_NAME;
import static constants.NamingConventions.DEFAULT_SEQUENCE_NAME_CASE;
import static constants.NamingConventions.DEFAULT_TABLE_NAME_CASE;
import static constants.NamingConventions.DEFAULT_USE_LETTER_CASE_CONVENTIONS;
import static constants.NamingConventions.PKEY_COLUMN_NAME_CASE;
import static constants.NamingConventions.PKEY_COPY_TABLE_NAME;
import static constants.NamingConventions.PKEY_DATA_TYPE_CASE;
import static constants.NamingConventions.PKEY_FOREIGN_KEY_NAME;
import static constants.NamingConventions.PKEY_FOREIGN_KEY_NAME_CASE;
import static constants.NamingConventions.PKEY_INDEX_NAME;
import static constants.NamingConventions.PKEY_INDEX_NAME_CASE;
import static constants.NamingConventions.PKEY_PRIMARY_KEY_CONSTRAINT_NAME;
import static constants.NamingConventions.PKEY_PRIMARY_KEY_CONSTRAINT_NAME_CASE;
import static constants.NamingConventions.PKEY_PRIMARY_KEY_NAME;
import static constants.NamingConventions.PKEY_PRIMARY_KEY_NAME_CASE;
import static constants.NamingConventions.PKEY_SEQUENCE_NAME;
import static constants.NamingConventions.PKEY_SEQUENCE_NAME_CASE;
import static constants.NamingConventions.PKEY_TABLE_NAME_CASE;
import static constants.NamingConventions.PKEY_USE_LETTER_CASE_CONVENTIONS;

import java.util.prefs.Preferences;

import constants.NamingConventions.LetterCase;
import main.LBJ;

public class LBJPreferences {

	private final Preferences preferences;

	public LBJPreferences() {
		preferences = Preferences.userRoot().node(LBJ.class.getSimpleName());
	}

	public void setDefaultPreferences() {
		preferences.put(PKEY_PRIMARY_KEY_NAME, DEFAULT_PRIMARY_KEY_NAME);
		preferences.put(PKEY_PRIMARY_KEY_CONSTRAINT_NAME, DEFAULT_PRIMARY_KEY_CONSTRAINT_NAME);
		preferences.put(PKEY_SEQUENCE_NAME, DEFAULT_SEQUENCE_NAME);
		preferences.put(PKEY_INDEX_NAME, DEFAULT_INDEX_NAME);
		preferences.put(PKEY_FOREIGN_KEY_NAME, DEFAULT_FOREIGN_KEY_NAME);
		preferences.put(PKEY_TABLE_NAME_CASE, DEFAULT_TABLE_NAME_CASE.toString());
		preferences.put(PKEY_PRIMARY_KEY_NAME_CASE, DEFAULT_PRIMARY_KEY_NAME_CASE.toString());
		preferences.put(PKEY_PRIMARY_KEY_CONSTRAINT_NAME_CASE, DEFAULT_PRIMARY_KEY_CONSTRAINT_NAME_CASE.toString());
		preferences.put(PKEY_SEQUENCE_NAME_CASE, DEFAULT_SEQUENCE_NAME_CASE.toString());
		preferences.put(PKEY_COLUMN_NAME_CASE, DEFAULT_COLUMN_NAME_CASE.toString());
		preferences.put(PKEY_DATA_TYPE_CASE, DEFAULT_DATA_TYPE_CASE.toString());
		preferences.put(PKEY_FOREIGN_KEY_NAME_CASE, DEFAULT_FOREIGN_KEY_NAME_CASE.toString());
		preferences.put(PKEY_INDEX_NAME_CASE, DEFAULT_INDEX_NAME_CASE.toString());
		preferences.putBoolean(PKEY_USE_LETTER_CASE_CONVENTIONS, DEFAULT_USE_LETTER_CASE_CONVENTIONS);
		preferences.putBoolean(PKEY_COPY_TABLE_NAME, DEFAULT_COPY_TABLE_NAME);
	}

	public void put(String key, String value) {
		preferences.put(key, value);
	}
	
	public void putBoolean(String key, boolean value) {
		preferences.putBoolean(key, value);
	}

	/**
	 * @return either default value for copy table name or preset one
	 */
	public boolean getCopyTableName() {
		return preferences.getBoolean(PKEY_COPY_TABLE_NAME, DEFAULT_COPY_TABLE_NAME);
	}

	/**
	 * @return either default value for use letter case conventions or preset one
	 */
	public boolean getUseLetterCaseConventions() {
		return preferences.getBoolean(PKEY_USE_LETTER_CASE_CONVENTIONS, DEFAULT_USE_LETTER_CASE_CONVENTIONS);
	}

	/**
	 * @return either default value for index name case or preset one
	 */
	public LetterCase getIndexNameCase() {
		return LetterCase.fromString(preferences.get(PKEY_INDEX_NAME_CASE, DEFAULT_INDEX_NAME_CASE.toString()));
	}

	/**
	 * @return either default value for foreign key name case or preset one
	 */
	public LetterCase getForeignKeyNameCase() {
		return LetterCase
				.fromString(preferences.get(PKEY_FOREIGN_KEY_NAME_CASE, DEFAULT_FOREIGN_KEY_NAME_CASE.toString()));
	}

	/**
	 * @return either default value for data type case or preset one
	 */
	public LetterCase getDataTypeCase() {
		return LetterCase.fromString(preferences.get(PKEY_DATA_TYPE_CASE, DEFAULT_DATA_TYPE_CASE.toString()));
	}

	/**
	 * @return either default value for column name case or preset one
	 */
	public LetterCase getColumnNameCase() {
		return LetterCase.fromString(preferences.get(PKEY_COLUMN_NAME_CASE, DEFAULT_COLUMN_NAME_CASE.toString()));
	}

	/**
	 * @return either default value for sequence name case or preset one
	 */
	public LetterCase getSequenceNameCase() {
		return LetterCase.fromString(preferences.get(PKEY_SEQUENCE_NAME_CASE, DEFAULT_SEQUENCE_NAME_CASE.toString()));
	}

	/**
	 * @return either default value for table name case or preset one
	 */
	public LetterCase getTableNameCase() {
		return LetterCase.fromString(preferences.get(PKEY_TABLE_NAME_CASE, DEFAULT_TABLE_NAME_CASE.toString()));
	}

	/**
	 * @return either default value for primary key name case or preset one
	 */
	public LetterCase getPrimaryKeyNameCase() {
		return LetterCase
				.fromString(preferences.get(PKEY_PRIMARY_KEY_NAME_CASE, DEFAULT_PRIMARY_KEY_NAME_CASE.toString()));
	}

	/**
	 * @return either default value for primary key constraint name case or preset
	 *         one
	 */
	public LetterCase getPrimaryKeyConstraintNameCase() {
		return LetterCase.fromString(preferences.get(PKEY_PRIMARY_KEY_CONSTRAINT_NAME_CASE,
				DEFAULT_PRIMARY_KEY_CONSTRAINT_NAME_CASE.toString()));
	}

	/**
	 * @return either default value for naming foreign key nem or preset one
	 */
	public String getForeignKeyName() {
		return preferences.get(PKEY_FOREIGN_KEY_NAME, DEFAULT_FOREIGN_KEY_NAME);
	}

	/**
	 * @return either default value for naming index name or preset one
	 */
	public String getIndexName() {
		return preferences.get(PKEY_INDEX_NAME, DEFAULT_INDEX_NAME);
	}

	/**
	 * @return either default value for naming sequence name or preset one
	 */
	public String getSequenceName() {
		return preferences.get(PKEY_SEQUENCE_NAME, DEFAULT_SEQUENCE_NAME);
	}

	/**
	 * @return either default value for naming primary key constraint or preset one
	 */
	public String getPrimaryKeyConstraintName() {
		return preferences.get(PKEY_PRIMARY_KEY_CONSTRAINT_NAME, DEFAULT_PRIMARY_KEY_CONSTRAINT_NAME);
	}

	/**
	 * @return either default value for naming primary key name or preset one
	 */
	public String getPrimaryKeyName() {
		return preferences.get(PKEY_PRIMARY_KEY_NAME, DEFAULT_PRIMARY_KEY_NAME);
	}

}
