package gui.forms.preferences;

import com.googlecode.lanterna.gui2.ActionListBox;
import com.googlecode.lanterna.gui2.GridLayout;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.Window;

import constants.Labels;
import gui.builders.LBJPlainLabelBuilder;
import gui.components.LBJPlainLabel;
import gui.forms.LBJForm;
import gui.forms.LBJWizardForm;
import gui.utils.LBJFormUtils;

public class PreferencesForm extends LBJWizardForm {

	private ActionListBox menu;
	private LBJPlainLabel menuLabel;
	private NamingConventionsForm namingConventionsForm;
	private LetterCaseConventionsForm letterCaseConventionsForm;

	public PreferencesForm(Window window, LBJForm previousForm) {
		super(window, previousForm);
	}

	@Override
	public void initializeComponents() {
		menu = new ActionListBox();
		menuLabel = new LBJPlainLabelBuilder(Labels.PREFERENCES_MENU_LABEL, this).build();
		namingConventionsForm = new NamingConventionsForm(getWindow(), this);
		letterCaseConventionsForm = new LetterCaseConventionsForm(getWindow(), this);

		LBJFormUtils.addUpdatableFormToMainMenu(namingConventionsForm);
		LBJFormUtils.addUpdatableFormToMainMenu(letterCaseConventionsForm);
	}

	@Override
	public void initializeContent() {
		setContent(new Panel(new GridLayout(1)));
	}

	@Override
	public void initializeButtons() {
		// no extra buttons
	}

	@Override
	public void addFormUpdaters() {
		// no form updaters

	}

	@Override
	public void addFormValidators() {
		// no form validators

	}

	@Override
	public void addComponents() {
		LBJFormUtils.addLabelToMenuContent(getContent(), menuLabel);
		LBJFormUtils.addMenuContent(getContent(), menu);
		LBJFormUtils.addItemToMenu(menu, namingConventionsForm, Labels.PREFERENCES_NAMING_CONVENTIONS);
		LBJFormUtils.addItemToMenu(menu, letterCaseConventionsForm, Labels.PREFERENCES_LETTER_CASE_CONVENTIONS);
	}

	@Override
	public void addButtons() {
		initializeBackButton();
	}

	@Override
	public String toString() {
		return Labels.PREFERENCES_FORM;
	}

}
