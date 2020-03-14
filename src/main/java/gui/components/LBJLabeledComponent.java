package gui.components;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.Label;

import gui.forms.LBJForm;

/**
 * Abstract base for all {@link LBJComponent}s that have {@link Label}.
 * {@link Label}'s text serves as component's name.
 */
public abstract class LBJLabeledComponent extends LBJComponent {

	private Label label;

	public LBJLabeledComponent(String name, LBJForm form) {
		super(name, form);
	}

	public Label getLabel() {
		return label;
	}

	public void setLabel(Label label) {
		this.label = label;
	}

	/**
	 * Overrides the current background color according to passed boolean.
	 * 
	 * @param isValid if {@code TRUE} is passed, color will be set to default. If
	 *                {@code FALSE} is passed color will be set to
	 *                {@link TextColor.ANSI.RED}.
	 * 
	 */
	public void setLabelColorByValidity(boolean isValid) {
		getLabel().setBackgroundColor(isValid ? null : TextColor.ANSI.RED);
	}

}
