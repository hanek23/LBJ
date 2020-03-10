package gui.components;

public class LBJPlainLabel extends LBJComponent<String> {

	@Override
	public String getValue() {
		return "";
	}

	@Override
	public void setValue(String value) {
		throw new IllegalArgumentException("Plain label does not have any value!");
	}

}
