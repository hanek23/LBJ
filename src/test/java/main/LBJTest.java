package main;

import static org.junit.Assert.fail;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.googlecode.lanterna.gui2.TextGUIThread.ExceptionHandler;

import gui.forms.LBJForm;
import gui.forms.mainmenu.MainMenuForm;
import gui.suppliers.LBJFormSupplier;

public class LBJTest {

	private List<Exception> exceptions = new ArrayList<>();
	private MainMenuForm mainMenuForm = LBJFormSupplier.getMainMenuForm();

	@Test
	public synchronized void launchTest() throws Exception {
		initTest();
		for (LBJForm form : mainMenuForm.getFormsToUpdate()) {
			form.focus();
			wait(100);
			mainMenuForm.focus();
		}
		assertNoException();
	}

	private void assertNoException() throws InterruptedException {
		wait(100);
		if (!exceptions.isEmpty()) {
			for (Exception exception : exceptions) {
				exception.printStackTrace();
			}
			fail();
		}
	}

	private void initTest() throws InterruptedException {
		mainMenuForm = LBJFormSupplier.getMainMenuForm();
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				LBJ.main(null);
			}
		});
		t.start();
		wait(1000);
		mainMenuForm.getThread().setExceptionHandler(new ExceptionHandler() {

			@Override
			public boolean onRuntimeException(RuntimeException e) {
				if (!exceptions.contains(e)) {
					exceptions.add(e);
				}
				return true;
			}

			@Override
			public boolean onIOException(IOException e) {
				if (!exceptions.contains(e)) {
					exceptions.add(e);
				}
				return true;
			}
		});
	}
}
