package gui.forms.droptable;

import static org.assertj.core.api.Assertions.assertThat;
import static testutils.asserts.LBJFormAssert.assertThat;
import static testutils.asserts.LBJValueComponentAssert.assertThat;

import org.junit.jupiter.api.Test;

import constants.Labels;
import constants.NamingConventions;
import domain.DropTable;
import testutils.LBJFormTestCase;
import testutils.LBJTestUtils;

public class DropTableFormTest extends LBJFormTestCase {

	private static final String TABLE_NAME = "ACTION";
	private static final String SEQUENCE_NAME = NamingConventions.DEFAULT_SEQUENCE_NAME + TABLE_NAME;

	@Test
	public void testInitialize() {
		DropTableForm form = LBJTestUtils.getDropTableForm();

		assertThat(form).hasName(Labels.DROP_TABLE_FORM);
		assertThat(form).hasComponentWithName(Labels.TABLE_NAME);
		assertThat(form).hasComponentWithName(Labels.TABLE_SEQUENCE_NAME);
		assertThat(form).hasComponentWithName(Labels.TABLE_SEQUENCE_NAME);

		assertThat(form.getTableNameTextBox()).isRequired().hasLengthValidator();
		assertThat(form.getDropSequenceCheckBox()).isNotChecked();
		assertThat(form.getSequenceNameTextBox()).isRequired().hasLengthValidator().isNotEnabled();
	}

	@Test
	public void testConvert() {
		DropTableForm form = LBJTestUtils.getDropTableForm();

		LBJTestUtils.setValueOf(form.getTableNameTextBox(), TABLE_NAME);
		LBJTestUtils.setValueOf(form.getDropSequenceCheckBox(), true);
		LBJTestUtils.setValueOf(form.getSequenceNameTextBox(), SEQUENCE_NAME);

		DropTable table = form.convert();
		// ignoring case because testing case upadaters is not the goal of this test
		assertThat(table.getName()).isEqualToIgnoringCase(TABLE_NAME);
		assertThat(table.getSequenceName()).isEqualToIgnoringCase(SEQUENCE_NAME);
	}

}
