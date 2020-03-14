package gui.validators;

public class LBJValidatorSupplier {

	public static final LBJLowerCaseValidator lowerCaseValidator = new LBJLowerCaseValidator();
	public static final LBJUpperCaseValidator upperCaseValidator = new LBJUpperCaseValidator();
	public static final LBJRequiredStringValidator stringRequiredValidator = new LBJRequiredStringValidator();
	public static final LBJStringLengthValidator stringLengthValidator = new LBJStringLengthValidator();

	private LBJValidatorSupplier() {
		// only static access
	}

}
