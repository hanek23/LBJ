package gui.forms.preferences;

import static testutils.asserts.LBJFormAssert.assertThat;
import static testutils.asserts.LBJValueComponentAssert.assertThat;

import org.junit.jupiter.api.Test;

import constants.Labels;
import constants.NamingConventions;
import testutils.LBJFormTest;
import testutils.LBJTestUtils;

public class PreferencesFormTest extends LBJFormTest {

	@Test
	public void testInitializeComponents() {
		PreferencesForm form = LBJTestUtils.getPreferencesForm();

		assertThat(form).hasName(Labels.PREFERENCES_FORM);
		assertThat(form).hasComponentWithName(Labels.CREATE_TABLE_PRIMARY_KEY_NAME);
		assertThat(form).hasComponentWithName(Labels.CREATE_TABLE_PRIMARY_KEY_CONSTRAIN_NAME);
		assertThat(form).hasComponentWithName(Labels.CREATE_TABLE_SEQUENCE_NAME);
		assertThat(form).hasComponentWithName(Labels.ADD_COLUMN_INDEX_NAME);
		assertThat(form).hasComponentWithName(Labels.ADD_COLUMN_FOREIGN_KEY_NAME);

		assertThat(form.getPrimaryKeyName()).isRequired().hasValueEqualTo(NamingConventions.getPrimaryKeyName());
		assertThat(form.getPrimaryKeyConstraintName()).isRequired()
				.hasValueEqualTo(NamingConventions.getPrimaryKeyConstraintName());
		assertThat(form.getSequenceName()).isRequired().hasValueEqualTo(NamingConventions.getSequenceName());
		assertThat(form.getIndexName()).isRequired().hasValueEqualTo(NamingConventions.getIndexName());
		assertThat(form.getForeignKeyName()).isRequired().hasValueEqualTo(NamingConventions.getForeignKeyName());
	}

	@Test
	public void testApplyButton() {
		PreferencesForm form = LBJTestUtils.getPreferencesForm();

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
	public void testBackButton() {
		PreferencesForm form = LBJTestUtils.getPreferencesForm();

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
		assertThat(form.getPrimaryKeyName()).hasValueEqualTo(NamingConventions.getPrimaryKeyName());
		assertThat(form.getPrimaryKeyConstraintName()).hasValueEqualTo(NamingConventions.getPrimaryKeyConstraintName());
		assertThat(form.getSequenceName()).hasValueEqualTo(NamingConventions.getSequenceName());
		assertThat(form.getIndexName()).hasValueEqualTo(NamingConventions.getIndexName());
		assertThat(form.getForeignKeyName()).hasValueEqualTo(NamingConventions.getForeignKeyName());

	}

	@Test
	public void testRestartButton() {
		PreferencesForm form = LBJTestUtils.getPreferencesForm();

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
