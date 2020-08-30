package utils;

import static constants.NamingConventions.DEFAULT_TABLE_NAME_CASE;
import static constants.NamingConventions.PKEY_TABLE_NAME_CASE;

import java.util.prefs.Preferences;

import constants.NamingConventions;
import constants.NamingConventions.LetterCase;
import gui.utils.SimpleBean;
import main.LBJ;

public class LBJPreferences implements SimpleBean {

	private final Preferences preferences;

	public LBJPreferences() {
		preferences = Preferences.userRoot().node(LBJ.class.getSimpleName());
	}

	public void setDefaultPreferences() {
		preferences.put(NamingConventions.PKEY_PRIMARY_KEY_NAME, NamingConventions.DEFAULT_PRIMARY_KEY_NAME);
		preferences.put(NamingConventions.PKEY_PRIMARY_KEY_CONSTRAINT_NAME,
				NamingConventions.DEFAULT_PRIMARY_KEY_CONSTRAINT_NAME);
		preferences.put(NamingConventions.PKEY_SEQUENCE_NAME, NamingConventions.DEFAULT_SEQUENCE_NAME);
		preferences.put(NamingConventions.PKEY_INDEX_NAME, NamingConventions.DEFAULT_INDEX_NAME);
		preferences.put(NamingConventions.PKEY_FOREIGN_KEY_NAME, NamingConventions.DEFAULT_FOREIGN_KEY_NAME);
		preferences.put(NamingConventions.PKEY_TABLE_NAME_CASE, NamingConventions.DEFAULT_TABLE_NAME_CASE.toString());
	}

	public void put(String key, String value) {
		preferences.put(key, value);
	}

	/**
	 * @return either default value for table name case or preset one
	 */
	public LetterCase getTableNameCase() {
		return LetterCase.fromString(preferences.get(PKEY_TABLE_NAME_CASE, DEFAULT_TABLE_NAME_CASE.toString()));
	}

	/**
	 * @return either default value for naming foreign key nem or preset one
	 */
	public String getForeignKeyName() {
		return preferences.get(NamingConventions.PKEY_FOREIGN_KEY_NAME, NamingConventions.DEFAULT_FOREIGN_KEY_NAME);
	}

	/**
	 * @return either default value for naming index name or preset one
	 */
	public String getIndexName() {
		return preferences.get(NamingConventions.PKEY_INDEX_NAME, NamingConventions.DEFAULT_INDEX_NAME);
	}

	/**
	 * @return either default value for naming sequence name or preset one
	 */
	public String getSequenceName() {
		return preferences.get(NamingConventions.PKEY_SEQUENCE_NAME, NamingConventions.DEFAULT_SEQUENCE_NAME);
	}

	/**
	 * @return either default value for naming primary key constraint or preset one
	 */
	public String getPrimaryKeyConstraintName() {
		return preferences.get(NamingConventions.PKEY_PRIMARY_KEY_CONSTRAINT_NAME,
				NamingConventions.DEFAULT_PRIMARY_KEY_CONSTRAINT_NAME);
	}

	/**
	 * @return either default value for naming primary key name or preset one
	 */
	public String getPrimaryKeyName() {
		return preferences.get(NamingConventions.PKEY_PRIMARY_KEY_NAME, NamingConventions.DEFAULT_PRIMARY_KEY_NAME);
	}

}
