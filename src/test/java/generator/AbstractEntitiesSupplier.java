package generator;

import utils.FileUtils;

public abstract class AbstractEntitiesSupplier implements EntitiesSupplier {

	public String getExpectedXml() {
		Class<?> childClazz = this.getClass();
		return FileUtils.getStringFromFileResource(childClazz, "/generator/" + childClazz.getSimpleName() + ".txt");
	}

}
