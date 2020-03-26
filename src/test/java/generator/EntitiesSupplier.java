package generator;

import java.util.List;

import domain.Entity;

public interface EntitiesSupplier {

	String getExpectedXml();

	List<Entity> getEntities();

}
