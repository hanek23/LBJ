package gui.components;

import java.util.regex.Pattern;

import com.googlecode.lanterna.gui2.TextBox;

public class LBJTextBox extends LBJComponent<String> {

	private String defaultValue;
	private Pattern allowedPattern;
	private TextBox textBox;

	public LBJTextBox(String name) {
		setName(name);
	}

	@Override
	public String getValue() {
		return textBox.getText();
	}

	@Override
	public void setValue(String value) {
		textBox.setText(value);
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public Pattern getAllowedPattern() {
		return allowedPattern;
	}

	public void setAllowedPattern(Pattern allowedPattern) {
		this.allowedPattern = allowedPattern;
	}

	public TextBox getTextBox() {
		return textBox;
	}

	public void setTextBox(TextBox textBox) {
		this.textBox = textBox;
	}

}
