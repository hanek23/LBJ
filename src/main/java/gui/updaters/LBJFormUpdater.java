package gui.updaters;

import gui.forms.LBJForm;

/**
 * Updater on {@link LBJForm} passed as parameter F.
 * 
 * @param <F> form that this updater operates on.
 */
public interface LBJFormUpdater<F extends LBJForm> {

	void update(F form);

}
