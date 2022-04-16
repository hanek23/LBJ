package gui.components;

import com.googlecode.lanterna.gui2.ComboBox;
import com.googlecode.lanterna.gui2.Component;

import gui.forms.LBJForm;

public class LBJComboBoxString extends LBJComboBox<String> {

	private ComboBox<String> comboBox;

	public LBJComboBoxString(String name, LBJForm form) {
		super(name, form);
	}

	@Override
	public String getValue() {
		return getComboBox().getText();
	}

}
