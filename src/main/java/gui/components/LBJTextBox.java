package gui.components;

import com.googlecode.lanterna.gui2.Component;
import com.googlecode.lanterna.gui2.TextBox;

import gui.forms.LBJForm;

/**
 * String implementation of {@link LBJValueComponent} using {@link TextBox}.
 */
public class LBJTextBox extends LBJValueComponent<String> {

	private TextBox textBox;
	private String namingConvention;
	private String defaultValue;

	public LBJTextBox(String name, LBJForm form) {
		super(name, form);
		defaultValue = "";
	}

	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		if (!enabled) {
			setValue(defaultValue);
		}
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

	public String getNamingConvention() {
		return namingConvention;
	}

	public void setNamingConvention(String namingConvention) {
		this.namingConvention = namingConvention;
	}

	/**
	 * Value shown when this component is disabled. Default value of default value
	 * :), is empty string
	 */
	public String getDefaultValue() {
		return defaultValue;
	}

	/**
	 * Value shown when this component is disabled. Default value of default value
	 * :), is empty string
	 */
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

}
