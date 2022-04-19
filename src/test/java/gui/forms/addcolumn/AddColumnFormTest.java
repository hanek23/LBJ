package gui.forms.addcolumn;

import static org.assertj.core.api.Assertions.assertThat;
import static testutils.asserts.LBJFormAssert.assertThat;
import static testutils.asserts.LBJValueComponentAssert.assertThat;

import org.junit.jupiter.api.Test;

import constants.Labels;
import constants.NamingConventions;
import domain.AddColumn;
import main.LBJ;
import testutils.LBJFormTestCase;
import testutils.LBJTestUtils;

class AddColumnFormTest extends LBJFormTestCase {

	private static final String TABLE_NAME = "ACTION";
	private static final String COLUMN_NAME = "lbj";
	private static final String COLUMN_DATA_TYPE = "integer";
	private static final boolean NULLABLE = false;
	private static final String DEFAULT_VALUE = "0";
	private static final boolean HAS_INDEX = true;
	private static final String INDEX_NAME = NamingConventions.DEFAULT_INDEX_NAME + TABLE_NAME
			+ NamingConventions.SEPARATOR + COLUMN_NAME;
	private static final boolean HAS_FOREIGNKEY = true;
	private static final String REFERENCED_TABLE_NAME = "NBA";
	private static final String REFERENCED_COLUMN_NAME = "goat";
	private static final String FOREIGN_KEY_NAME = NamingConventions.DEFAULT_FOREIGN_KEY_NAME + REFERENCED_TABLE_NAME
			+ NamingConventions.SEPARATOR + REFERENCED_COLUMN_NAME;

	@Test
	void testInitialize() {
		AddColumnForm form = LBJTestUtils.getAddColumnForm();

		// Has all components
		assertThat(form).hasName(Labels.ADD_COLUMN_FORM);
		assertThat(form).hasComponentWithName(Labels.TABLE_NAME);
		assertThat(form).hasComponentWithName(Labels.COLUMN_NAME);
		assertThat(form).hasComponentWithName(Labels.DATA_TYPE);
		assertThat(form).hasComponentWithName(Labels.ADD_COLUMN_NULLABLE);
		assertThat(form).hasComponentWithName(Labels.ADD_COLUMN_DEFAULT_VALUE);
		assertThat(form).hasComponentWithName(Labels.ADD_COLUMN_INDEX);
		assertThat(form).hasComponentWithName(Labels.INDEX_NAME);
		assertThat(form).hasComponentWithName(Labels.ADD_COLUMN_FOREIGN_KEY);
		assertThat(form).hasComponentWithName(Labels.REFERENCED_TABLE);
		assertThat(form).hasComponentWithName(Labels.REFERENCED_COLUMN);
		assertThat(form).hasComponentWithName(Labels.FOREIGN_KEY_NAME);

		// in right states
		assertThat(form.getNullableCheckBox()).isChecked();
		assertThat(form.getDefaultValueTextBox()).isNotEnabled();
		assertThat(form.getIndexCheckBox()).isNotChecked();
		assertThat(form.getIndexNameTextBox()).isNotEnabled();
		assertThat(form.getForeignKeyCheckBox()).isNotChecked();
		assertThat(form.getReferencedTableNameTextBox()).isNotEnabled();
		assertThat(form.getReferencedColumnNameTextBox()).isNotEnabled();
		assertThat(form.getForeignKeyNameTextBox()).isNotEnabled();

		// with all validators
		assertThat(form.getTableNameTextBox()).isRequired().hasCaseValidator(LBJ.preferences.getTableNameCase())
				.hasLengthValidator();
		assertThat(form.getColumnNameTextBox()).isRequired().hasCaseValidator(LBJ.preferences.getColumnNameCase())
				.hasLengthValidator();
		assertThat(form.getDataTypeComboBox()).hasCaseValidator(LBJ.preferences.getDataTypeCase()).isRequired();
		assertThat(form.getIndexNameTextBox()).isRequired().hasCaseValidator(LBJ.preferences.getIndexNameCase())
				.hasLengthValidator();
		assertThat(form.getReferencedTableNameTextBox()).isRequired()
				.hasCaseValidator(LBJ.preferences.getTableNameCase()).hasLengthValidator();
		assertThat(form.getReferencedColumnNameTextBox()).isRequired()
				.hasCaseValidator(LBJ.preferences.getColumnNameCase()).hasLengthValidator();
		assertThat(form.getForeignKeyNameTextBox()).isRequired()
				.hasCaseValidator(LBJ.preferences.getForeignKeyNameCase()).hasLengthValidator();

	}

	@Test
	void testConvert() {
		AddColumnForm form = LBJTestUtils.getAddColumnForm();

		LBJTestUtils.setValueOf(form.getTableNameTextBox(), TABLE_NAME);
		LBJTestUtils.setValueOf(form.getColumnNameTextBox(), COLUMN_NAME);
		LBJTestUtils.setValueOf(form.getDataTypeComboBox(), COLUMN_DATA_TYPE);
		LBJTestUtils.setValueOf(form.getNullableCheckBox(), NULLABLE);
		LBJTestUtils.setValueOf(form.getDefaultValueTextBox(), DEFAULT_VALUE);
		LBJTestUtils.setValueOf(form.getIndexCheckBox(), HAS_INDEX);
		LBJTestUtils.setValueOf(form.getIndexNameTextBox(), INDEX_NAME);
		LBJTestUtils.setValueOf(form.getForeignKeyCheckBox(), HAS_FOREIGNKEY);
		LBJTestUtils.setValueOf(form.getReferencedTableNameTextBox(), REFERENCED_TABLE_NAME);
		LBJTestUtils.setValueOf(form.getReferencedColumnNameTextBox(), REFERENCED_COLUMN_NAME);
		LBJTestUtils.setValueOf(form.getForeignKeyNameTextBox(), FOREIGN_KEY_NAME);

		AddColumn column = form.convert();
		assertThat(column.isAddColumn()).isTrue();
		// ignoring case because testing case upadaters is not the goal of this test
		assertThat(column.getTableName()).isEqualToIgnoringCase(TABLE_NAME);
		assertThat(column.getName()).isEqualToIgnoringCase(COLUMN_NAME);
		assertThat(column.getDataType()).isEqualToIgnoringCase(COLUMN_DATA_TYPE);
		assertThat(column.isNullable()).isEqualTo(NULLABLE);
		assertThat(column.getDefaultValue()).isEqualTo(DEFAULT_VALUE);
		assertThat(column.hasIndex()).isEqualTo(HAS_INDEX);
		assertThat(column.getIndexName()).isEqualToIgnoringCase(INDEX_NAME);
		assertThat(column.hasForeignKey()).isEqualTo(HAS_FOREIGNKEY);
		assertThat(column.getForeignKey()).isNotNull();
		assertThat(column.getForeignKey().getReferencedTable()).isEqualToIgnoringCase(REFERENCED_TABLE_NAME);
		assertThat(column.getForeignKey().getReferencedColumn()).isEqualToIgnoringCase(REFERENCED_COLUMN_NAME);
		assertThat(column.getForeignKey().getName()).isEqualToIgnoringCase(FOREIGN_KEY_NAME);
	}

	@Test
	void testConvertBoolean() {
		AddColumnForm form = LBJTestUtils.getAddColumnForm();

		LBJTestUtils.setValueOf(form.getTableNameTextBox(), TABLE_NAME);
		LBJTestUtils.setValueOf(form.getColumnNameTextBox(), COLUMN_NAME);
		LBJTestUtils.setValueOf(form.getDataTypeComboBox(), "boolean");
		LBJTestUtils.setValueOf(form.getNullableCheckBox(), NULLABLE);
		LBJTestUtils.setValueOf(form.getIndexCheckBox(), HAS_INDEX);
		LBJTestUtils.setValueOf(form.getIndexNameTextBox(), INDEX_NAME);
		LBJTestUtils.setValueOf(form.getForeignKeyCheckBox(), HAS_FOREIGNKEY);
		LBJTestUtils.setValueOf(form.getReferencedTableNameTextBox(), REFERENCED_TABLE_NAME);
		LBJTestUtils.setValueOf(form.getReferencedColumnNameTextBox(), REFERENCED_COLUMN_NAME);
		LBJTestUtils.setValueOf(form.getForeignKeyNameTextBox(), FOREIGN_KEY_NAME);

		AddColumn column = form.convert();
		assertThat(column.isAddColumn()).isTrue();
		// ignoring case because testing case upadaters is not the goal of this test
		assertThat(column.getTableName()).isEqualToIgnoringCase(TABLE_NAME);
		assertThat(column.getName()).isEqualToIgnoringCase(COLUMN_NAME);
		assertThat(column.getDataType()).isEqualToIgnoringCase("boolean");
		assertThat(column.isNullable()).isEqualTo(NULLABLE);
		// boolean is false by default
		assertThat(column.getDefaultValue()).isEqualTo("false");
		assertThat(column.hasIndex()).isEqualTo(HAS_INDEX);
		assertThat(column.getIndexName()).isEqualToIgnoringCase(INDEX_NAME);
		assertThat(column.hasForeignKey()).isEqualTo(HAS_FOREIGNKEY);
		assertThat(column.getForeignKey()).isNotNull();
		assertThat(column.getForeignKey().getReferencedTable()).isEqualToIgnoringCase(REFERENCED_TABLE_NAME);
		assertThat(column.getForeignKey().getReferencedColumn()).isEqualToIgnoringCase(REFERENCED_COLUMN_NAME);
		assertThat(column.getForeignKey().getName()).isEqualToIgnoringCase(FOREIGN_KEY_NAME);
	}

	@Test
	void testConvertBooleanNewDefaultValue() {
		AddColumnForm form = LBJTestUtils.getAddColumnForm();

		LBJTestUtils.setValueOf(form.getTableNameTextBox(), TABLE_NAME);
		LBJTestUtils.setValueOf(form.getColumnNameTextBox(), COLUMN_NAME);
		LBJTestUtils.setValueOf(form.getDataTypeComboBox(), "boolean");
		LBJTestUtils.setValueOf(form.getNullableCheckBox(), NULLABLE);
		LBJTestUtils.setValueOf(form.getDefaultValueTextBox(), "true");
		LBJTestUtils.setValueOf(form.getIndexCheckBox(), HAS_INDEX);
		LBJTestUtils.setValueOf(form.getIndexNameTextBox(), INDEX_NAME);
		LBJTestUtils.setValueOf(form.getForeignKeyCheckBox(), HAS_FOREIGNKEY);
		LBJTestUtils.setValueOf(form.getReferencedTableNameTextBox(), REFERENCED_TABLE_NAME);
		LBJTestUtils.setValueOf(form.getReferencedColumnNameTextBox(), REFERENCED_COLUMN_NAME);
		LBJTestUtils.setValueOf(form.getForeignKeyNameTextBox(), FOREIGN_KEY_NAME);

		AddColumn column = form.convert();
		assertThat(column.isAddColumn()).isTrue();
		// ignoring case because testing case upadaters is not the goal of this test
		assertThat(column.getTableName()).isEqualToIgnoringCase(TABLE_NAME);
		assertThat(column.getName()).isEqualToIgnoringCase(COLUMN_NAME);
		assertThat(column.getDataType()).isEqualToIgnoringCase("boolean");
		assertThat(column.isNullable()).isEqualTo(NULLABLE);
		assertThat(column.getDefaultValue()).isEqualTo("true");
		assertThat(column.hasIndex()).isEqualTo(HAS_INDEX);
		assertThat(column.getIndexName()).isEqualToIgnoringCase(INDEX_NAME);
		assertThat(column.hasForeignKey()).isEqualTo(HAS_FOREIGNKEY);
		assertThat(column.getForeignKey()).isNotNull();
		assertThat(column.getForeignKey().getReferencedTable()).isEqualToIgnoringCase(REFERENCED_TABLE_NAME);
		assertThat(column.getForeignKey().getReferencedColumn()).isEqualToIgnoringCase(REFERENCED_COLUMN_NAME);
		assertThat(column.getForeignKey().getName()).isEqualToIgnoringCase(FOREIGN_KEY_NAME);
	}

}
