package gui.attribute;

import static main.LBJ.preferences;

import constants.Labels;
import constants.NamingConventions.LetterCase;

public enum Attribute {

	TABLE_NAME(Labels.TABLE_NAME, preferences.getTableNameCase(), null, true, true),
	COLUMN_NAME(Labels.COLUMN_NAME, preferences.getColumnNameCase(), null, true, true),
	DATA_TYPE(Labels.DATA_TYPE, preferences.getDataTypeCase(), null, true, false),
	INDEX_NAME(Labels.INDEX_NAME, preferences.getIndexNameCase(), preferences.getIndexName(), true, true),
	REFERENCED_TABLE(Labels.REFERENCED_TABLE, preferences.getTableNameCase(), null, true, true),
	REFERENCED_COLUMN(Labels.REFERENCED_COLUMN, preferences.getColumnNameCase(), null, true, true),
	FOREIGN_KEY_NAME(Labels.FOREIGN_KEY_NAME, preferences.getForeignKeyNameCase(), preferences.getForeignKeyName(), true, true),
	PRIMARY_KEY_NAME(Labels.PRIMARY_KEY_NAME, preferences.getPrimaryKeyNameCase(), preferences.getPrimaryKeyName(), true, true),
	PRIMARY_KEY_CONSTRAINT_NAME(Labels.PRIMARY_KEY_CONSTRAIN_NAME, preferences.getPrimaryKeyConstraintNameCase(), preferences.getPrimaryKeyConstraintName(), true, true),
	SEQUENCE_NAME(Labels.SEQUENCE_NAME, preferences.getSequenceNameCase(), preferences.getSequenceName(), true, true);

	private String label;
	private LetterCase letterCase;
	private String namingConvention;
	private boolean required;
	private boolean validateLength;

	private Attribute(String label, LetterCase letterCase, String namingConvention, boolean required,
			boolean validateLength) {
		this.label = label;
		this.letterCase = letterCase;
		this.required = required;
		this.validateLength = validateLength;
		this.namingConvention = namingConvention;
	}

	public String getLabel() {
		return label;
	}

	public LetterCase getLetterCase() {
		return letterCase;
	}

	public boolean isRequired() {
		return required;
	}

	public boolean shouldValidateLength() {
		return validateLength;
	}

	public String getNamingConvention() {
		return namingConvention;
	}

}
