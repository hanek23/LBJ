package gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.gui2.ActionListBox;
import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.ComboBox;
import com.googlecode.lanterna.gui2.Direction;
import com.googlecode.lanterna.gui2.GridLayout;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.Separator;
import com.googlecode.lanterna.gui2.TextGUI;
import com.googlecode.lanterna.gui2.Window;
import com.googlecode.lanterna.gui2.WindowBasedTextGUI;
import com.googlecode.lanterna.gui2.WindowListener;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;

import constants.Labels;
import domain.Table;

public class MainMenu implements Runnable {

	public static void main(String[] args) {
		MainMenu mainMenu = new MainMenu();
		mainMenu.run();
	}

	private Window window;
	private Panel mainMenuContent;
	private List<Updatable> updatableChilds;

	public MainMenu() {
		initialize();
	}

	public MainMenu(Window window) {
		this.window = window;
	}

	private void initialize() {
		DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
		try (Screen screen = terminalFactory.createScreen()) {
			screen.startScreen();
			WindowBasedTextGUI gui = new MultiWindowTextGUI(screen);
			window = new BasicWindow(Labels.WINDOW_NAME);
			mainMenuContent = new Panel(new GridLayout(1));
			GridLayout gridLayout = (GridLayout) mainMenuContent.getLayoutManager();
			gridLayout.setHorizontalSpacing(3);
			mainMenuContent.addComponent(new Label(Labels.MAIN_MENU_QUESTION));
			ActionListBox mainMenu = new ActionListBox();
			AddColumn addColumn = new AddColumn(this, window, this);
			addUpdatableChild(addColumn);
			mainMenu.addItem(Labels.MAIN_MENU_ADD_COLUMN, addColumn);
			CreateTable createTable = new CreateTable(this, window, this);
			addUpdatableChild(createTable);
			mainMenu.addItem(Labels.MAIN_MENU_CREATE_TABLE, createTable);
			mainMenuContent.addComponent(mainMenu);

			window.setComponent(mainMenuContent);

			window.addWindowListener(new WindowListener() {

				@Override
				public void onUnhandledInput(Window basePane, KeyStroke keyStroke, AtomicBoolean hasBeenHandled) {
					update();
				}

				@Override
				public void onInput(Window basePane, KeyStroke keyStroke, AtomicBoolean deliverEvent) {
					update();
				}

				@Override
				public void onResized(Window window, TerminalSize oldSize, TerminalSize newSize) {
					update();
				}

				@Override
				public void onMoved(Window window, TerminalPosition oldPosition, TerminalPosition newPosition) {
					update();
				}

				private void update() {
					for (Updatable updatable : updatableChilds) {
						updatable.update();
					}
				}
			});

			gui.addWindowAndWait(window);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void run() {
		window.setComponent(mainMenuContent);
	}

	public List<Updatable> getUpdatableChilds() {
		if (updatableChilds == null) {
			updatableChilds = new ArrayList<>();
		}
		return updatableChilds;
	}

	public boolean addUpdatableChild(Updatable toAdd) {
		if (toAdd == null) {
			return false;
		}
		return getUpdatableChilds().add(toAdd);
	}

}
