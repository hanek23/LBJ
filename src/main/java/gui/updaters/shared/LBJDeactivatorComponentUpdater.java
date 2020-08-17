package gui.updaters.shared;

import gui.components.LBJComponent;
import gui.updaters.LBJComponentUpdater;

/**
 * Disables a component if {@link LBJComponent#getActivatorComponent()} returns
 * <code>true</code>
 */
public class LBJDeactivatorComponentUpdater implements LBJComponentUpdater {

	@Override
	public void update(LBJComponent component) {
		if (component.getActivatorComponent() == null) {
			throw new IllegalStateException("Why would you add deactivator updater when: " + component
					+ " does not have any activator component‽");
		}
		component.setEnabled(!component.getActivatorComponent().getValue());
	}

}
