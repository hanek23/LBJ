package gui.builders;

import com.googlecode.lanterna.gui2.CheckBoxList;
import com.googlecode.lanterna.gui2.Label;

import gui.components.LBJCheckBoxList;
import gui.forms.LBJForm;

/**
 * Builder for {@link LBJCheckBoxList}.
 */
public class LBJCheckBoxListBuilder {

	private LBJCheckBoxList lbjCheckBoxList;

	/**
	 * Starts building a {@link LBJCheckBoxList}.
	 * 
	 * @param name will be used as component name as well as {@link Label}
	 * @param form on which is this component used.
	 * 
	 */
	public LBJCheckBoxListBuilder(String name, LBJForm form) {
		lbjCheckBoxList = new LBJCheckBoxList(name, form);
		lbjCheckBoxList.setCheckBoxList(new CheckBoxList<>());
		lbjCheckBoxList.setLabel(new Label(name));
	}

	public LBJCheckBoxListBuilder addValue(String value) {
		((CheckBoxList<String>) lbjCheckBoxList.getComponent()).addItem(value);
		return this;
	}

	public LBJCheckBoxList build() {
		return lbjCheckBoxList;
	}

}
