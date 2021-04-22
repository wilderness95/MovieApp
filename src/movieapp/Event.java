package movieapp;

/**
 *
 * @author Wilderness
 */
public class Event {

	Movie movie;
	Integer date;
	int pieces;
	Boolean[][] occupancy;
	String room;

	public Event(Movie movie, int date, int pieces, Boolean[][] occupancy, String room) {

		this.movie = movie;
		this.date = date;
		this.pieces = pieces;
		this.occupancy = occupancy;
		this.room = room;

		for (int i = 0; i < occupancy.length; i++) {
			for (int j = 0; j < occupancy[i].length; j++) {
				occupancy[i][j] = false;
			}
		}
	}

	@Override
	public String toString() {
		return "Date:" + date + " free seats: " + pieces + " in room:" + room;
	}
}