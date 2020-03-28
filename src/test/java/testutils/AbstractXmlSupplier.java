package testutils;

import org.apache.commons.lang3.StringUtils;

import utils.FileUtils;

public abstract class AbstractXmlSupplier {

	public String getExpectedXml() {
		Class<?> childClazz = this.getClass();
		return FileUtils.getStringFromFileResource(childClazz,
				"/" + StringUtils.replace(childClazz.getName(), ".", "/") + ".txt");
	}

}
