package gui.updaters;

import gui.forms.LBJForm;

/**
 * Updater specific to some {@link LBJForm} passed as parameter F.
 * 
 * @param <F> specific form that this updater operates on.
 */
public interface LBJFormUpdater<F extends LBJForm> {

	void update(F form);

}
