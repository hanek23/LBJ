package gui.updaters;

import gui.forms.LBJForm;

public interface LBJFormUpdater<F extends LBJForm> {

	void update(F form);

}
