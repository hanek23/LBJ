package generator;

import utils.FileUtils;

public abstract class TestTable implements TestTableSupplier {

	@Override
	public String getExpectedTable() {
		Class<?> childClazz = this.getClass();
		return FileUtils.getStringFromFileResource(childClazz, "/generator/" + childClazz.getSimpleName() + ".txt");
	}

}
