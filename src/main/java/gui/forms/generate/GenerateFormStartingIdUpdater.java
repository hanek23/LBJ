package gui.forms.generate;

import gui.components.LBJCheckBox;
import gui.components.LBJTextBox;
import gui.updaters.LBJFormUpdater;

/**
 * Custom updater for {@link GenerateForm} that enables startingID text if you
 * check that you want to generate only changesets.
 */
public class GenerateFormStartingIdUpdater implements LBJFormUpdater<GenerateForm> {

	@Override
	public void update(GenerateForm form) {
		LBJTextBox startingId = form.getStartingIdTextBox();
		LBJCheckBox onlyChangeSets = form.getOnlyChangesetsCheckBox();
		if (onlyChangeSets.isChecked()) {
			startingId.setEnabled(true);
		} else {
			startingId.setEnabled(false);
			startingId.setValue("1");
		}
	}

}
