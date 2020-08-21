package gui.utils;

public interface SimpleBean<T> extends Bean<T> {

	@Override
	@SuppressWarnings("unchecked")
	default T create() {
		try {
			return (T) getClass().newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new IllegalStateException("Cannot initialize bean with name " + getClass().getSimpleName());
		}
	}

}
