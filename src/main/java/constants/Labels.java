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
	public static final String BUTTON_DROP_ANOTHER_COLUMN = "Drop another column";
	public static final String BUTTON_MODIFY_ANOTHER_COLUMN = "Modify another column";
	public static final String BUTTON_CREATE_INDEX = "Create another index";
	public static final String BUTTON_DROP_INDEX = "Drop another index";
	public static final String BUTTON_GENERATE = "Generate";
	public static final String BUTTON_COPY_TO_CLIPBOARD = "Copy to clipboard";
	public static final String BUTTON_ADD_ANOTHER_FOREIGN_KEY_CONSTRAINT = "Add another foreign key constraint";
	public static final String BUTTON_DROP_ANOTHER_FOREIGN_KEY_CONSTRAINT = "Drop another foreign key constraint";

	// FORM NAMES
	public static final String MAIN_MENU_FORM = "Main menu form";
	public static final String CREATE_TABLE_FORM = "Create table form";
	public static final String DROP_TABLE_FORM = "Drop table form";
	public static final String ADD_COLUMN_FORM = "Add column form";
	public static final String DROP_COLUMN_FORM = "Drop column form";
	public static final String DROP_NOT_NULL_CONSTRAINT_FORM = "Drop not null constraint form";
	public static final String ADD_NOT_NULL_CONSTRAINT_FORM = "Add not null constraint form";
	public static final String GENERATE_FORM = "Generate form";
	public static final String PREFERENCES_FORM = "Preferences form";
	public static final String NAMING_CONVENTIONS_FORM = "Naming conventions form";
	public static final String LETTER_CASE_FORM = "Letter case form";
	public static final String DROP_TABLE_COLUMN_PREFERENCES_FORM = "Drop Table Column preferences form";
	public static final String MODIFY_DATA_TYPE_FORM = "Modify data type form";
	public static final String CREATE_INDEX_FORM = "Create index form";
	public static final String DROP_INDEX_FORM = "Drop index form";
	public static final String ADD_FOREIGN_KEY_CONSTRAINT_FORM = "Add foreign key constraint form";
	public static final String DROP_FOREIGN_KEY_CONSTRAINT_FORM = "Drop foreign key constraint form";

	// MAIN MENU
	public static final String MAIN_MENU_QUESTION = "What do you want to do?";
	public static final String MAIN_MENU_ADD_COLUMN = "Add column(s)";
	public static final String MAIN_MENU_DROP_COLUMN = "Drop column(s)";
	public static final String MAIN_MENU_CREATE_TABLE = "Create table";
	public static final String MAIN_MENU_DROP_TABLE = "Drop table";
	public static final String MAIN_MENU_DROP_NOT_NULL_CONSTRAINT = "Drop not null constraint";
	public static final String MAIN_MENU_ADD_NOT_NULL_CONSTRAINT = "Add not null constraint";
	public static final String MAIN_MENU_MODIFY_DATA_TYPE = "Modify data type(s)";
	public static final String MAIN_MENU_CREATE_INDEX = "Create index";
	public static final String MAIN_MENU_DROP_INDEX = "Drop index";
	public static final String MAIN_MENU_ADD_FOREIGN_KEY_CONSTRAINT = "Add foreign key constraint";
	public static final String MAIN_MENU_DROP_FOREIGN_KEY_CONSTRAINT = "Drop foreign key constraint";
	public static final String MAIN_MENU_PREFERENCES = "Preferences";
	public static final String BUTTON_EXIT = "Exit";

	// CREATE/DROP TABLE
	public static final String TABLE_NAME = "Table name";
	public static final String PRIMARY_KEY_NAME = "Primary key name";
	public static final String PRIMARY_KEY_CONSTRAIN_NAME = "Primary key constraint name";
	public static final String SEQUENCE_NAME = "Sequence name";

	// ADD COLUMN
	public static final String COLUMN_NAME = "Column name";
	public static final String ADD_COLUMN_INDEX = "Index";
	public static final String ADD_COLUMN_FOREIGN_KEY = "Foreign key";
	public static final String ADD_COLUMN_NULLABLE = "Nullable";
	public static final String ADD_COLUMN_DEFAULT_VALUE = "Default value";
	public static final String DATA_TYPE = "Data type";
	public static final String REFERENCED_TABLE = "Referenced table";
	public static final String REFERENCED_COLUMN = "Referenced column";
	public static final String FOREIGN_KEY_NAME = "Foreign key name";
	public static final String INDEX_NAME = "Index name";

	// GENERATE
	public static final String GENERATE_FORM_AUTHOR = "Author";
	public static final String GENERATE_FORM_ONLY_CHANGESETS = "Generete only changeSets";
	public static final String GENERATE_FORM_CHANGESETS_STARTING_ID = "Changesets starting ID";
	public static final String GENERATE_FORM_DATABASES = "Databases";
	public static final String GENERATE_FORM_DATABASES_ORACLE = "Oracle";
	public static final String GENERATE_FORM_DATABASES_MSSQL = "MSSQL";
	public static final String GENERATE_FORM_DATABASES_POSTGRESQL = "PostgreSQL";
	public static final String GENERATE_FORM_GENERATED_XML = "Generated XML";

	// PREFERENCES
	public static final String PREFERENCES_MENU_LABEL = "Set preferences of";
	public static final String PREFERENCES_NAMING_CONVENTIONS = "Naming conventions";
	public static final String PREFERENCES_LETTER_CASE_CONVENTIONS = "Letter case conventions";
	public static final String PREFERENCES_DROP_TABLE_COLUMN = "Preferences on Drop Table/Column forms";
	public static final String BUTTON_PREFERENCES_APPLY = "Apply and return";
	public static final String BUTTON_PREFERENCES_RESET = "Reset to default";
	public static final String PREFERENCES_USE_LETTER_CASE_CONVENTIONS = "Use letter case convensions";
	public static final String PREFERENCES_COPY_TABLE_NAME = "Copy table name to the next form";

}
