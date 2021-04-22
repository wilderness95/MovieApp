package movieapp;

/**
 *
 * @author Wilderness
 */
public class Reservation {
	String username;
	int date;
	int[] seat;
	String room;
	String title;
	Boolean paid;


	public String getUsername() {
		return username;
	}

	public int getDate() {
		return date;
	}

	public int[] getSeat() {
		return seat;
	}

	public String getRoom() {
		return room;
	}

	public String getTitle() {
		return title;
	}

	public String payStatus() {
		if (paid) {
			return "Paid";
		} else {
			return "Not paid.";
		}
	}

	public Reservation(String username, int date, int[] seat, String room, String title) {
		this.username = username;
		this.date = date;
		this.seat = seat;
		this.room = room;
		this.title = title;
		this.paid = false;
	}

	@Override
	public String toString() {
		return "Title=" + title + " date=" + date + " " + (int) (seat[0] + 1) + ".row " + (int) (seat[1] + 1) + ".seat"
				+ " room= " + room + " " + payStatus();
	}

}