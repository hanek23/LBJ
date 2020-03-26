package gui.forms.generate;

import gui.components.LBJCheckBox;
import gui.components.LBJTextBox;
import gui.updaters.LBJFormUpdater;

public class GenerateFormStartingIdUpdater implements LBJFormUpdater<GenerateForm> {

	@Override
	public void update(GenerateForm form) {
		LBJTextBox startingId = form.getStartingIdTextBox();
		LBJCheckBox onlyChangeSets = form.getOnlyChangesetsCheckBox();
		if (onlyChangeSets.isChecked()) {
			startingId.setEnabled(true);
		} else {
			startingId.setEnabled(false);
			startingId.setValue("");
		}
	}

}
