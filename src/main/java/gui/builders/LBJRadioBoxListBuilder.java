package gui.builders;

import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.RadioBoxList;

import gui.components.LBJRadioBoxList;
import gui.forms.LBJForm;

/**
 * Builder for {@link LBJRadioBoxList}.
 */
public class LBJRadioBoxListBuilder<T> {

	private LBJRadioBoxList<T> lbjRadioBoxList;


	public LBJRadioBoxListBuilder(String name, LBJForm form) {
		lbjRadioBoxList = new LBJRadioBoxList<>(name, form);
		lbjRadioBoxList.setRadioBoxList(new RadioBoxList<>());
		lbjRadioBoxList.setLabel(new Label(name));
	}

	public LBJRadioBoxListBuilder<T> addItem(T item) {
		lbjRadioBoxList.addItem(item);
		return this;
	}

	public LBJRadioBoxList<T> build() {
		return lbjRadioBoxList;
	}

}
