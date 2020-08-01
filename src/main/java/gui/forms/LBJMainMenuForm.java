package gui.forms;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Logger;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.ActionListBox;
import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.GridLayout;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.TextGUIThread;
import com.googlecode.lanterna.gui2.Window;
import com.googlecode.lanterna.gui2.WindowBasedTextGUI;
import com.googlecode.lanterna.gui2.WindowListener;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.MouseCaptureMode;
import com.googlecode.lanterna.terminal.Terminal;

import constants.Labels;
import constants.Settings;

/**
 * <p>
 * Main menu for application. Main purpose of this form other than getting to
 * another form is to update other forms as this is where {@link Terminal} is
 * running on any application event such as user input, resize or move. To be
 * able to update all forms it has to have their reference in
 * {@link #formsToUpdate} see {@link #updateForms()}.
 * <p>
 * Other small difference of this form is that it does not have any
 * {@link Button}s as it has all "links" to other forms stored in
 * {@link ActionListBox} as {@link Runnable}s ({@link LBJForm} implements
 * {@link Runnable}).
 */
public abstract class LBJMainMenuForm extends LBJForm {

	private static final Logger LOGGER = Logger.getLogger(LBJMainMenuForm.class.getSimpleName());

	private List<LBJForm> formsToUpdate;
	private TextGUIThread thread;

	public LBJMainMenuForm() {
		this(new BasicWindow(Labels.WINDOW_NAME));
	}

	public LBJMainMenuForm(Window window) {
		super(window);
		initialize();
	}

	@Override
	public String toString() {
		return Labels.MAIN_MENU_FORM;
	}

	@Override
	public void initializeButtons() {
		// no buttons in main menu, everything is in ActionListBox - mainMenu
	}

	@Override
	public void addButtons() {
		// no buttons in main menu, everything is in ActionListBox - mainMenu
	}

	@Override
	public void addFormUpdaters() {
		// no updaters
	}

	@Override
	public void addFormValidators() {
		// no validators
	}

	@Override
	public void initializeContent() {
		setContent(new Panel(new GridLayout(1)));
	}

	public void startTerminal() {
		try (Screen screen = new DefaultTerminalFactory().setMouseCaptureMode(MouseCaptureMode.CLICK)
				.setInitialTerminalSize(Settings.TERMINAL_SIZE).createScreen()) {
			screen.startScreen();
			WindowBasedTextGUI gui = new MultiWindowTextGUI(screen);
			thread = gui.getGUIThread();
			getWindow().addWindowListener(new WindowListener() {

				@Override
				public void onUnhandledInput(Window basePane, KeyStroke keyStroke, AtomicBoolean hasBeenHandled) {
					updateForms();
				}

				@Override
				public void onInput(Window basePane, KeyStroke keyStroke, AtomicBoolean deliverEvent) {
					updateForms();
				}

				@Override
				public void onResized(Window window, TerminalSize oldSize, TerminalSize newSize) {
					updateForms();
				}

				@Override
				public void onMoved(Window window, TerminalPosition oldPosition, TerminalPosition newPosition) {
					updateForms();
				}
			});
			gui.addWindowAndWait(getWindow());
		} catch (Exception e) {
			LOGGER.severe("Exception has occurred while running terminal");
			throw new IllegalStateException(e);
		}
	}

	public void updateForms() {
		for (LBJForm lbjForm : getFormsToUpdate()) {
			lbjForm.update();
		}
	}

	/**
	 * Child forms that are going to be updated each time user does some action.
	 * Only currently visible form will be updated.
	 */
	public List<LBJForm> getFormsToUpdate() {
		if (formsToUpdate == null) {
			formsToUpdate = new ArrayList<>();
		}
		return formsToUpdate;
	}

	public boolean addFormToUpdate(LBJForm form) {
		return getFormsToUpdate().add(form);
	}

	public TextGUIThread getThread() {
		return thread;
	}

}
