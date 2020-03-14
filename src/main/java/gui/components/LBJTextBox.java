package gui.components;

import com.googlecode.lanterna.gui2.Component;
import com.googlecode.lanterna.gui2.TextBox;

import gui.forms.LBJForm;

/**
 * String implementation of {@link LBJValueHolderComponent} using
 * {@link TextBox}.
 */
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

	@Override
	public Component getComponent() {
		return getTextBox();
	}

	public boolean isFocused() {
		return getTextBox().isFocused();
	}

}
