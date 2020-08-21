package gui.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

public class BeanSupplier {

	private BeanSupplier() {
		// static supplier
	}

	private static Map<String, Object> beans;

	private static Object get(String name) {
		Object bean = beans.get(name);
		if (bean == null) {
			throw new IllegalStateException("No bean found with name " + name);
		}
		return bean;
	}

	@SuppressWarnings("unchecked")
	public static <T extends Bean<T>> T get(Class<T> clazz) {
		return (T) get(clazz.getSimpleName());
	}

	@SuppressWarnings("rawtypes")
	public static void init() {
		beans = new HashMap<>();
		ServiceLoader<Bean> loader = ServiceLoader.load(Bean.class);
		for (Bean implBean : loader) {
			beans.put(implBean.getClass().getSimpleName(), implBean.create());
		}
	}

}
