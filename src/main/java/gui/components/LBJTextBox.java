package gui.components;

import com.googlecode.lanterna.gui2.TextBox;

import gui.forms.LBJForm;

public class LBJTextBox extends LBJValueHolderComponent<String> {

	private TextBox textBox;

	public LBJTextBox(String name, LBJForm form) {
		super(name, form);
	}

	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		textBox.setEnabled(enabled);
	}

	@Override
	public String getValue() {
		return textBox.getText();
	}

	@Override
	public void setValue(String value) {
		textBox.setText(value);
	}

	public TextBox getTextBox() {
		return textBox;
	}

	public void setTextBox(TextBox textBox) {
		this.textBox = textBox;
	}

}
