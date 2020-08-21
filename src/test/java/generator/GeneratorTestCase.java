package generator;

import java.util.List;

import domain.Entity;

public interface GeneratorTestCase {

	String getExpectedXml();

	List<Entity> getEntities();

	/**
	 * If test case output is only changesets and not the whole changelog it should
	 * return FALSE, TRUE otherwise
	 */
	boolean checkXsd();

}
