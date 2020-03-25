package constants;

/**
 * Labels for the application's GUI
 */
public class Labels {

	private Labels() {
		// only static constants
	}

	public static final String WINDOW_NAME = "LBJ";
	public static final String BUTTON_BACK = "Back";
	public static final String BUTTON_ADD_COLUMN = "Add column";
	public static final String BUTTON_ADD_ANOTHER_COLUMN = "Add another column";
	public static final String BUTTON_GENERATE = "Generate";
	public static final String BUTTON_COPY_TO_CLIPBOARD = "Copy to clipboard";

	// FORM NAMES
	public static final String MAIN_MENU_FORM = "Main menu form";
	public static final String TABLE_FORM = "Create table form";
	public static final String ADD_COLUMN_FORM = "Add column form";
	public static final String REMOVE_NOT_NULL_CONSTRAINT_FORM = "Remove not null constraint form";

	// MAIN MENU
	public static final String MAIN_MENU_QUESTION = "What do you want to do?";
	public static final String MAIN_MENU_ADD_COLUMN = "Add column(s)";
	public static final String MAIN_MENU_CREATE_TABLE = "Create table";
	public static final String MAIN_MENU_REMOVE_NOT_NULL_CONSTRAINT = "Remove not null constraint";
	public static final String BUTTON_EXIT = "Exit";

	// CREATE TABLE
	public static final String TABLE_NAME = "Table name";
	public static final String CREATE_TABLE_PRIMARY_KEY_NAME = "Primary key name";
	public static final String CREATE_TABLE_PRIMARY_KEY_CONSTRAIN = "Primary key constraint name";
	public static final String CREATE_TABLE_DATABASES = "Databases";
	public static final String CREATE_TABLE_DATABASES_ORACLE = "Oracle";
	public static final String CREATE_TABLE_DATABASES_MSSQL = "MSSQL";
	public static final String CREATE_TABLE_DATABASES_POSTGRESQL = "PostgreSQL";
	public static final String CREATE_TABLE_SEQUENCE_NAME = "Sequence name";

	// ADD COLUMN
	public static final String COLUMN_NAME = "Column name";
	public static final String ADD_COLUMN_INDEX = "Index";
	public static final String ADD_COLUMN_FOREIGN_KEY = "Foreign key";
	public static final String ADD_COLUMN_NULLABLE = "Nullable";
	public static final String COLUMN_DATA_TYPE = "Data type";
	public static final String ADD_COLUMN_REFERENCED_TABLE = "Referenced table";
	public static final String ADD_COLUMN_REFERENCED_COLUMN = "Referenced column";
	public static final String ADD_COLUMN_FOREIGN_KEY_NAME = "Foreign key name";
	public static final String ADD_COLUMN_INDEX_NAME = "Index name";

	// GENERATE
	public static final String GENERATE_FORM = "Generate form";
	public static final String GENERATE_GENERATED_XML = "Generated XML";
	public static final String GENERATE_AUTHOR = "Author";

}
