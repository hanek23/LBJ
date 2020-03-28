package endtoend;

import generator.Generator;
import gui.forms.generate.GenerateForm;

/**
 * At the end of the test method, {@link Generator} output should be in
 * clipboard as it is after clicking on button on {@link GenerateForm}.
 */
public interface EndToEndTest {

	void test();

	/**
	 * If test case output is only changesets and not the whole changelog it should
	 * return FALSE, TRUE otherwise
	 */
	boolean checkXsd();

	String getExpectedXml();

}
