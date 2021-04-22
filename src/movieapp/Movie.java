package movieapp;

/**
 *
 * @author Wilderness
 */
public class Movie {
	private final String title;
	private final String genre;
	private final int lengthMins;

	public String getTitle() {
		return title;
	}

	public String getGenre() {
		return genre;
	}

	public int getLengthMins() {
		return lengthMins;
	}

	public Movie(String title, String genre, int lengthMins) {
		this.title = title;
		this.genre = genre;
		this.lengthMins = lengthMins;
	}

	@Override
	public String toString() {
		return "Title: " + title + " genre: " + genre + " lengthMins: " + lengthMins;
	}

}