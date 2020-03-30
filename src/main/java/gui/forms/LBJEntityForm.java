package gui.forms;

import com.googlecode.lanterna.gui2.Window;
import com.googlecode.lanterna.gui2.table.Table;

import domain.Entity;
import generator.Generator;
import gui.components.LBJComponent;

/**
 * Any {@link LBJWizardForm} that operates on some {@link Entity} like
 * {@link Table} or {@link Columnm} must provide {@link #convert()} method which
 * converts data from {@link LBJComponent}s to specified entity which is than
 * used in {@link Generator} to generate XML ouput of this application
 */
public abstract class LBJEntityForm<T extends Entity> extends LBJWizardForm {

	public LBJEntityForm(Window window, LBJForm previousForm) {
		super(window, previousForm);
	}

	public abstract T convert();

}
