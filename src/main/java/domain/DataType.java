package domain;

public enum DataType {
	CHAR(true), VARCHAR(true), BOOLEAN(false), DATETIME(false), DOUBLE(false), FLOAT(false);

	private boolean additionalInfoRequired;
	private String additionalInfo;

	private DataType(boolean additionalInfoRequired) {
		this.additionalInfoRequired = additionalInfoRequired;
	}

	public boolean isAdditionalInfoRequired() {
		return additionalInfoRequired;
	}

	public String getAdditionalInfo() {
		return additionalInfo;
	}

	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

}
