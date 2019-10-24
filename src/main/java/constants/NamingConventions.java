package constants;

public class NamingConventions {
	
	private NamingConventions() {
		// only static constants
	}

	public static final String SEPARATOR = "_";
	public static final String PRIMARY_KEY_NAME_DEFAULT_VALUE = "id" + SEPARATOR;
	public static final String PRIMARY_KEY_CONSTRAINT_DEFAULT_VALUE = "P" + SEPARATOR;
	public static final String INDEX_NAME_DEFAULT_VALUE = "I" + SEPARATOR;
	public static final String FOREIGN_KEY_NAME_DEFAULT_VALUE = "F" + SEPARATOR;

}
