package gui.forms.dropindex;

import static org.assertj.core.api.Assertions.assertThat;
import static testutils.asserts.LBJFormAssert.assertThat;
import static testutils.asserts.LBJValueComponentAssert.assertThat;

import org.junit.jupiter.api.Test;

import constants.Labels;
import domain.DropIndex;
import main.LBJ;
import testutils.LBJTestCase;
import testutils.LBJTestUtils;

public class DropIndexFormTest extends LBJTestCase {

	private static final String TABLE_NAME = "LBJ_DROP_INDEX";
	private static final String INDEX_NAME = "I_LBJ_DROP_INDEX_NOW!";

	@Test
	public void testInitialize() {
		DropIndexForm form = LBJTestUtils.getDropIndexForm();

		// Has all components
		assertThat(form).hasName(Labels.DROP_INDEX_FORM);
		assertThat(form).hasComponentWithName(Labels.TABLE_NAME);
		assertThat(form).hasComponentWithName(Labels.INDEX_NAME);

		// with all validators
		assertThat(form.getTableNameTextBox()).isRequired().hasCaseValidator(LBJ.preferences.getTableNameCase())
				.hasLengthValidator();
		assertThat(form.getIndexNameTextBox()).isRequired().hasCaseValidator(LBJ.preferences.getIndexNameCase())
				.hasLengthValidator();

	}

	@Test
	public void testConvert() {
		DropIndexForm form = LBJTestUtils.getDropIndexForm();

		LBJTestUtils.setValueOf(form.getTableNameTextBox(), TABLE_NAME);
		LBJTestUtils.setValueOf(form.getIndexNameTextBox(), INDEX_NAME);

		DropIndex index = form.convert();
		assertThat(index.isDropIndex()).isTrue();
		// ignoring case because testing case upadaters is not the goal of this test
		assertThat(index.getTableName()).isEqualToIgnoringCase(TABLE_NAME);
		assertThat(index.getName()).isNull();
		assertThat(index.getIndexName()).isEqualToIgnoringCase(INDEX_NAME);
	}
}
