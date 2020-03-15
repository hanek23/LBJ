package gui.forms.addcolumn;

import com.googlecode.lanterna.gui2.Window;

import constants.Labels;
import domain.Column;
import gui.LBJFormUtils;
import gui.forms.LBJEntityForm;
import gui.forms.LBJForm;

public class LBJAddColumnForm extends LBJEntityForm<Column> {

	public LBJAddColumnForm(Window window, LBJForm previousForm) {
		super(window, previousForm);
	}

	@Override
	public Column convert() {
		return null;
	}

	@Override
	public String toString() {
		return Labels.ADD_COLUMN_FORM;
	}

	@Override
	public void initializeComponents() {

	}

	@Override
	public void initializeContent() {
		setContent(LBJFormUtils.initializeDefaultContent());
	}

	@Override
	public void addFormUpdaters() {
		// TODO Auto-generated method stub

	}

	@Override
	public void addFormValidators() {
		// TODO Auto-generated method stub

	}

	@Override
	public void addComponentsToContent() {
		// TODO Auto-generated method stub

	}

	@Override
	public void addButtonsToContent() {
		// TODO Auto-generated method stub

	}

}
