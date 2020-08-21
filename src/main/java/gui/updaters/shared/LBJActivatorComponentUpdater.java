package gui.updaters.shared;

import gui.components.LBJComponent;
import gui.updaters.LBJComponentUpdater;
import gui.utils.SimpleBean;

/**
 * Enables a component if {@link LBJComponent#getActivatorComponent()} returns
 * <code>true</code>
 */
public class LBJActivatorComponentUpdater implements LBJComponentUpdater, SimpleBean<LBJActivatorComponentUpdater> {

	@Override
	public void update(LBJComponent component) {
		if (component.getActivatorComponent() == null) {
			throw new IllegalStateException("Why would you add activator updater when: " + component
					+ " does not have any activator component‽");
		}
		component.setEnabled(component.getActivatorComponent().getValue());
	}

}
