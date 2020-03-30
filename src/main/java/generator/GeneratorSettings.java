package generator;

/**
 * Encapsulates settings for XML generation using {@link Generator}.
 */
public class GeneratorSettings {

	private String author;
	private boolean onlyChangeSets;
	private int startingId;

	public GeneratorSettings(String author, boolean onlyChangeSets, int startingId) {
		this.author = author;
		this.onlyChangeSets = onlyChangeSets;
		this.startingId = startingId;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public boolean isOnlyChangeSets() {
		return onlyChangeSets;
	}

	public void setOnlyChangeSets(boolean onlyChangeSets) {
		this.onlyChangeSets = onlyChangeSets;
	}

	public int getStartingId() {
		return startingId;
	}

	public void setStartingId(int startingId) {
		this.startingId = startingId;
	}

}
