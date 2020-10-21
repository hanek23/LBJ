package gui.forms.dropcolumn;

import static org.assertj.core.api.Assertions.assertThat;
import static testutils.asserts.LBJFormAssert.assertThat;
import static testutils.asserts.LBJValueComponentAssert.assertThat;

import org.junit.jupiter.api.Test;

import constants.Labels;
import constants.NamingConventions;
import domain.DropColumn;
import testutils.LBJFormTestCase;
import testutils.LBJTestUtils;

public class DropColumnFormTest extends LBJFormTestCase {

	private static final String TABLE_NAME = "ACTION";
	private static final String COLUMN_NAME = "lbj";
	private static final boolean HAS_DEFAULT_VALUE = true;
	private static final boolean HAS_INDEX = true;
	private static final String INDEX_NAME = NamingConventions.DEFAULT_INDEX_NAME + TABLE_NAME
			+ NamingConventions.SEPARATOR + COLUMN_NAME;
	private static final boolean HAS_FOREIGNKEY = true;
	private static final String FOREIGN_KEY_NAME = "FK_LBJ_ACTION";

	@Test
	public void testInitialize() {
		DropColumnForm form = LBJTestUtils.getDropColumnForm();

		// Has all components
		assertThat(form).hasName(Labels.DROP_COLUMN_FORM);
		assertThat(form).hasComponentWithName(Labels.TABLE_NAME);
		assertThat(form).hasComponentWithName(Labels.COLUMN_NAME);
		assertThat(form).hasComponentWithName(Labels.ADD_COLUMN_DEFAULT_VALUE);
		assertThat(form).hasComponentWithName(Labels.ADD_COLUMN_INDEX);
		assertThat(form).hasComponentWithName(Labels.INDEX_NAME);
		assertThat(form).hasComponentWithName(Labels.ADD_COLUMN_FOREIGN_KEY);
		assertThat(form).hasComponentWithName(Labels.FOREIGN_KEY_NAME);

		// in right states
		assertThat(form.getDropDefaultValueCheckBox()).isNotChecked();
		assertThat(form.getIndexCheckBox()).isNotChecked();
		assertThat(form.getIndexNameTextBox()).isNotEnabled();
		assertThat(form.getForeignKeyCheckBox()).isNotChecked();
		assertThat(form.getForeignKeyNameTextBox()).isNotEnabled();

		// with all validators
		assertThat(form.getTableNameTextBox()).isRequired().hasLengthValidator();
		assertThat(form.getColumnNameTextBox()).isRequired().hasLengthValidator();
		assertThat(form.getIndexNameTextBox()).isRequired().hasLengthValidator();
		assertThat(form.getForeignKeyNameTextBox()).isRequired().hasLengthValidator();

	}

	@Test
	public void testConvert() {
		DropColumnForm form = LBJTestUtils.getDropColumnForm();

		LBJTestUtils.setValueOf(form.getTableNameTextBox(), TABLE_NAME);
		LBJTestUtils.setValueOf(form.getColumnNameTextBox(), COLUMN_NAME);
		LBJTestUtils.setValueOf(form.getDropDefaultValueCheckBox(), HAS_DEFAULT_VALUE);
		LBJTestUtils.setValueOf(form.getIndexCheckBox(), HAS_INDEX);
		LBJTestUtils.setValueOf(form.getIndexNameTextBox(), INDEX_NAME);
		LBJTestUtils.setValueOf(form.getForeignKeyCheckBox(), HAS_FOREIGNKEY);
		LBJTestUtils.setValueOf(form.getForeignKeyNameTextBox(), FOREIGN_KEY_NAME);

		DropColumn column = form.convert();
		assertThat(column.isDropColumn()).isTrue();
		// ignoring case because testing case upadaters is not the goal of this test
		assertThat(column.getTableName()).isEqualToIgnoringCase(TABLE_NAME);
		assertThat(column.getName()).isEqualToIgnoringCase(COLUMN_NAME);
		assertThat(column.hasDefaultValue()).isEqualTo(HAS_DEFAULT_VALUE);
		assertThat(column.hasIndex()).isEqualTo(HAS_INDEX);
		assertThat(column.getIndexName()).isEqualToIgnoringCase(INDEX_NAME);
		assertThat(column.hasForeignKey()).isEqualTo(HAS_FOREIGNKEY);
		assertThat(column.getForeignKey()).isNotNull();
		assertThat(column.getForeignKey().getName()).isEqualToIgnoringCase(FOREIGN_KEY_NAME);
	}

}
