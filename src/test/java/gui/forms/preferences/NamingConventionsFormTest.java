package gui.forms.preferences;

import static testutils.asserts.LBJFormAssert.assertThat;
import static testutils.asserts.LBJValueComponentAssert.assertThat;

import org.junit.jupiter.api.Test;

import constants.Labels;
import constants.NamingConventions;
import main.LBJ;
import testutils.LBJFormTestCase;
import testutils.LBJTestUtils;

class NamingConventionsFormTest extends LBJFormTestCase {

	@Test
	void testInitializeComponents() {
		NamingConventionsForm form = LBJTestUtils.getNamingConventionsForm();

		assertThat(form).hasName(Labels.NAMING_CONVENTIONS_FORM);
		assertThat(form).hasComponentWithName(Labels.PRIMARY_KEY_NAME);
		assertThat(form).hasComponentWithName(Labels.PRIMARY_KEY_CONSTRAIN_NAME);
		assertThat(form).hasComponentWithName(Labels.SEQUENCE_NAME);
		assertThat(form).hasComponentWithName(Labels.INDEX_NAME);
		assertThat(form).hasComponentWithName(Labels.FOREIGN_KEY_NAME);

		assertThat(form.getPrimaryKeyName()).isRequired().hasValueEqualTo(LBJ.preferences.getPrimaryKeyName());
		assertThat(form.getPrimaryKeyConstraintName()).isRequired()
				.hasValueEqualTo(LBJ.preferences.getPrimaryKeyConstraintName());
		assertThat(form.getSequenceName()).isRequired().hasValueEqualTo(LBJ.preferences.getSequenceName());
		assertThat(form.getIndexName()).isRequired().hasValueEqualTo(LBJ.preferences.getIndexName());
		assertThat(form.getForeignKeyName()).isRequired().hasValueEqualTo(LBJ.preferences.getForeignKeyName());
	}

	@Test
	void testApplyButton() {
		NamingConventionsForm form = LBJTestUtils.getNamingConventionsForm();

		LBJTestUtils.setValueOf(form.getPrimaryKeyName(), "Humoresque No. 7 in G-flat Major, Op. 101");
		LBJTestUtils.setValueOf(form.getPrimaryKeyConstraintName(), "Antonín Dvořák - Humoreska");
		LBJTestUtils.setValueOf(form.getSequenceName(), "YO YO MA & ITZHAK PERLMAN PLAY DVORAK");
		LBJTestUtils.setValueOf(form.getIndexName(), "CONDUCTOR: SEIJI  OZAWA");
		LBJTestUtils.setValueOf(form.getForeignKeyName(),
				"Japanese conductor, American orchestra, Chinese cellist, Israeli violinist, Czech composer. Is great music what will bring the humanity together?");

		LBJTestUtils.click(form.getApplyButton());
		assertThat(form).isNotFocused();
		form.focus();
		assertThat(form).isFocused();
		// Values should have been saved so components still have same values
		assertThat(form.getPrimaryKeyName()).hasValueEqualTo("Humoresque No. 7 in G-flat Major, Op. 101");
		assertThat(form.getPrimaryKeyConstraintName()).hasValueEqualTo("Antonín Dvořák - Humoreska");
		assertThat(form.getSequenceName()).hasValueEqualTo("YO YO MA & ITZHAK PERLMAN PLAY DVORAK");
		assertThat(form.getIndexName()).hasValueEqualTo("CONDUCTOR: SEIJI  OZAWA");
		assertThat(form.getForeignKeyName()).hasValueEqualTo(
				"Japanese conductor, American orchestra, Chinese cellist, Israeli violinist, Czech composer. Is great music what will bring the humanity together?");

	}

	@Test
	void testBackButton() {
		NamingConventionsForm form = LBJTestUtils.getNamingConventionsForm();

		LBJTestUtils.setValueOf(form.getPrimaryKeyName(), "Humoresque No. 7 in G-flat Major, Op. 101");
		LBJTestUtils.setValueOf(form.getPrimaryKeyConstraintName(), "Antonín Dvořák - Humoreska");
		LBJTestUtils.setValueOf(form.getSequenceName(), "YO YO MA & ITZHAK PERLMAN PLAY DVORAK");
		LBJTestUtils.setValueOf(form.getIndexName(), "CONDUCTOR: SEIJI  OZAWA");
		LBJTestUtils.setValueOf(form.getForeignKeyName(),
				"Japanese conductor, American orchestra, Chinese cellist, Israeli violinist, Czech composer. Is great music what will bring the humanity together?");

		LBJTestUtils.click(form.getBackButton());
		assertThat(form).isNotFocused();
		form.focus();
		assertThat(form).isFocused();
		// Nothing should have been saved, so values are those from preferences
		assertThat(form.getPrimaryKeyName()).hasValueEqualTo(LBJ.preferences.getPrimaryKeyName());
		assertThat(form.getPrimaryKeyConstraintName()).hasValueEqualTo(LBJ.preferences.getPrimaryKeyConstraintName());
		assertThat(form.getSequenceName()).hasValueEqualTo(LBJ.preferences.getSequenceName());
		assertThat(form.getIndexName()).hasValueEqualTo(LBJ.preferences.getIndexName());
		assertThat(form.getForeignKeyName()).hasValueEqualTo(LBJ.preferences.getForeignKeyName());

	}

	@Test
	void testRestartButton() {
		NamingConventionsForm form = LBJTestUtils.getNamingConventionsForm();

		LBJTestUtils.setValueOf(form.getPrimaryKeyName(), "Humoresque No. 7 in G-flat Major, Op. 101");
		LBJTestUtils.setValueOf(form.getPrimaryKeyConstraintName(), "Antonín Dvořák - Humoreska");
		LBJTestUtils.setValueOf(form.getSequenceName(), "YO YO MA & ITZHAK PERLMAN PLAY DVORAK");
		LBJTestUtils.setValueOf(form.getIndexName(), "CONDUCTOR: SEIJI  OZAWA");
		LBJTestUtils.setValueOf(form.getForeignKeyName(),
				"Japanese conductor, American orchestra, Chinese cellist, Israeli violinist, Czech composer. Is great music what will bring the humanity together?");

		LBJTestUtils.click(form.getResetToDefaultButton());
		assertThat(form).isFocused();

		// components should now have default values
		assertThat(form.getPrimaryKeyName()).hasValueEqualTo(NamingConventions.DEFAULT_PRIMARY_KEY_NAME);
		assertThat(form.getPrimaryKeyConstraintName())
				.hasValueEqualTo(NamingConventions.DEFAULT_PRIMARY_KEY_CONSTRAINT_NAME);
		assertThat(form.getSequenceName()).hasValueEqualTo(NamingConventions.DEFAULT_SEQUENCE_NAME);
		assertThat(form.getIndexName()).hasValueEqualTo(NamingConventions.DEFAULT_INDEX_NAME);
		assertThat(form.getForeignKeyName()).hasValueEqualTo(NamingConventions.DEFAULT_FOREIGN_KEY_NAME);

	}

}
