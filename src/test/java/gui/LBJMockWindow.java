package gui;

import java.util.Collection;
import java.util.Set;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.Theme;
import com.googlecode.lanterna.gui2.Component;
import com.googlecode.lanterna.gui2.Interactable;
import com.googlecode.lanterna.gui2.TextGUIGraphics;
import com.googlecode.lanterna.gui2.Window;
import com.googlecode.lanterna.gui2.WindowBasedTextGUI;
import com.googlecode.lanterna.gui2.WindowListener;
import com.googlecode.lanterna.gui2.WindowPostRenderer;
import com.googlecode.lanterna.input.KeyStroke;

public class LBJMockWindow implements Window {

	@Override
	public void setStrictFocusChange(boolean strictFocusChange) {
		// mock
	}

	@Override
	public void setEnableDirectionBasedMovements(boolean enableDirectionBasedMovements) {
		// mock
	}

	@Override
	public Theme getTheme() {
		// mock
		return null;
	}

	@Override
	public void setTheme(Theme theme) {
		// mock

	}

	@Override
	public WindowBasedTextGUI getTextGUI() {
		// mock
		return null;
	}

	@Override
	public void setTextGUI(WindowBasedTextGUI textGUI) {
		// mock

	}

	@Override
	public String getTitle() {
		// mock
		return null;
	}

	@Override
	public boolean isVisible() {
		// mock
		return false;
	}

	@Override
	public void setVisible(boolean visible) {
		// mock

	}

	@Override
	public boolean isInvalid() {
		// mock
		return false;
	}

	@Override
	public void invalidate() {
		// mock

	}

	@Override
	public TerminalSize getPreferredSize() {
		// mock
		return null;
	}

	@Override
	public void close() {
		// mock

	}

	@Override
	public void setHints(Collection<Hint> hints) {
		// mock

	}

	@Override
	public Set<Hint> getHints() {
		// mock
		return null;
	}

	@Override
	public TerminalPosition getPosition() {
		// mock
		return null;
	}

	@Override
	public void setPosition(TerminalPosition topLeft) {
		// mock

	}

	@Override
	public TerminalSize getSize() {
		// mock
		return null;
	}

	@Override
	public void setSize(TerminalSize size) {
		// mock

	}

	@Override
	public TerminalSize getDecoratedSize() {
		// mock
		return null;
	}

	@Override
	public void setDecoratedSize(TerminalSize decoratedSize) {
		// mock

	}

	@Override
	public void setContentOffset(TerminalPosition offset) {
		// mock

	}

	@Override
	public void waitUntilClosed() {
		// mock

	}

	@Override
	public WindowPostRenderer getPostRenderer() {
		// mock
		return null;
	}

	@Override
	public void addWindowListener(WindowListener windowListener) {
		// mock

	}

	@Override
	public void removeWindowListener(WindowListener windowListener) {
		// mock

	}

	@Override
	public void draw(TextGUIGraphics graphics) {
		// mock

	}

	@Override
	public boolean handleInput(KeyStroke key) {
		// mock
		return false;
	}

	@Override
	public void setComponent(Component component) {
		// mock

	}

	@Override
	public Component getComponent() {
		// mock
		return null;
	}

	@Override
	public Interactable getFocusedInteractable() {
		// mock
		return null;
	}

	@Override
	public void setFocusedInteractable(Interactable interactable) {
		// mock
	}

	@Override
	public TerminalPosition getCursorPosition() {
		// mock
		return null;
	}

	@Override
	public TerminalPosition toGlobal(TerminalPosition localPosition) {
		// mock
		return null;
	}

	@Override
	public TerminalPosition fromGlobal(TerminalPosition position) {
		// mock
		return null;
	}

}
