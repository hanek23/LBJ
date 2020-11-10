package gui.forms.createindex;

import static org.assertj.core.api.Assertions.assertThat;
import static testutils.asserts.LBJFormAssert.assertThat;
import static testutils.asserts.LBJValueComponentAssert.assertThat;

import org.junit.jupiter.api.Test;

import constants.Labels;
import domain.CreateIndex;
import main.LBJ;
import testutils.LBJTestCase;
import testutils.LBJTestUtils;

public class CreateIndexFormTest extends LBJTestCase {

	private static final String TABLE_NAME = "LBJ_CREATE_INDEX";
	private static final String COLUMN_NAME = "please";
	private static final String INDEX_NAME = "I_LBJ_CREATE_INDEX_PLEASE";

	@Test
	public void testInitialize() {
		CreateIndexForm form = LBJTestUtils.getCreateIndexForm();

		// Has all components
		assertThat(form).hasName(Labels.CREATE_INDEX_FORM);
		assertThat(form).hasComponentWithName(Labels.TABLE_NAME);
		assertThat(form).hasComponentWithName(Labels.COLUMN_NAME);
		assertThat(form).hasComponentWithName(Labels.INDEX_NAME);

		// with all validators
		assertThat(form.getTableNameTextBox()).isRequired().hasCaseValidator(LBJ.preferences.getTableNameCase())
				.hasLengthValidator();
		assertThat(form.getColumnNameTextBox()).isRequired().hasCaseValidator(LBJ.preferences.getColumnNameCase())
				.hasLengthValidator();
		assertThat(form.getIndexNameTextBox()).isRequired().hasCaseValidator(LBJ.preferences.getIndexNameCase())
				.hasLengthValidator();

	}

	@Test
	public void testConvert() {
		CreateIndexForm form = LBJTestUtils.getCreateIndexForm();

		LBJTestUtils.setValueOf(form.getTableNameTextBox(), TABLE_NAME);
		LBJTestUtils.setValueOf(form.getColumnNameTextBox(), COLUMN_NAME);
		LBJTestUtils.setValueOf(form.getIndexNameTextBox(), INDEX_NAME);

		CreateIndex index = form.convert();
		assertThat(index.isCreateIndex()).isTrue();
		// ignoring case because testing case upadaters is not the goal of this test
		assertThat(index.getTableName()).isEqualToIgnoringCase(TABLE_NAME);
		assertThat(index.getName()).isEqualToIgnoringCase(COLUMN_NAME);
		assertThat(index.getIndexName()).isEqualToIgnoringCase(INDEX_NAME);
	}

}
