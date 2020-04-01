package main;

import static org.junit.Assert.fail;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.googlecode.lanterna.gui2.TextGUIThread.ExceptionHandler;

import gui.forms.LBJForm;
import gui.forms.mainmenu.MainMenuForm;
import gui.suppliers.LBJFormSupplier;
import testutils.LBJFormTest;

public class LBJTest extends LBJFormTest {

	private volatile List<Exception> exceptions = new ArrayList<>();
	private MainMenuForm mainMenuForm;
	private Thread mainMenuThread;

	@Test
	@Tag("slow")
	@SuppressWarnings("deprecation")
	public synchronized void launchTest() throws Exception {
		initTest();
		// By requesting GenerateForm at least once it will get added to MainMenuForm
		// formsToUpdate and will get tested.
		LBJFormSupplier.getGenerateForm(mainMenuForm.getWindow(), mainMenuForm);
		// Tries to access every form available
		for (LBJForm form : mainMenuForm.getFormsToUpdate()) {
			form.focus();
			wait(100);
			mainMenuForm.focus();
		}
		assertNoException();
		mainMenuThread.stop();
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
		mainMenuThread = new Thread(new Runnable() {
			@Override
			public void run() {
				mainMenuForm = LBJFormSupplier.getMainMenuForm();
				mainMenuForm.focus();
				mainMenuForm.startTerminal();
			}
		});
		mainMenuThread.start();
		wait(1000);
		mainMenuForm.focus();
		System.out.println("asd");
		System.out.println("+ěščřžýáíáéď");
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
