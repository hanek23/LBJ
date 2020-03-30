package gui.builders;

import com.googlecode.lanterna.gui2.Label;

import gui.components.LBJPlainLabel;
import gui.forms.LBJForm;

/**
 * Builder for {@link LBJPlainLabel}
 */
public class LBJPlainLabelBuilder {

	private LBJPlainLabel lbjPlainLabel;

	/**
	 * Starts building a {@link LBJPlainLabel}.
	 * 
	 * @param name will be used as component name as well as {@link Label}
	 * @param form on which is this component used.
	 * 
	 */
	public LBJPlainLabelBuilder(String name, LBJForm form) {
		lbjPlainLabel = new LBJPlainLabel(name, form);
		lbjPlainLabel.setLabel(new Label(name));
	}

	public LBJPlainLabel build() {
		return lbjPlainLabel;
	}

}
