package movieapp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 *
 * @author Wilderness
 */

public class MovieApp {

	static Scanner scan;

	static User user;
	static Employee employee;
	static Admin admin;
	static int employeeId = 0;
	static int userid = 0;
	static int price = 1000;

	static SortedMap<Integer, ArrayList<Reservation>> reservations = new TreeMap<>();
	static SortedMap<String, Movie> movies = new TreeMap<>();
	static SortedMap<String, ArrayList<Event>> allEvent = new TreeMap<>();
	static List<Employee> employees = new ArrayList<>();
	static List<User> users = new ArrayList<>();

	public static void main(String[] args) {

		// adding test users

		user = new User("JonDoe", userid, "1234");
		users.add(user);
		userid++;
		employee = new Employee("JaneDoe", employeeId);
		createEmployee("JaneDoe", employees, employeeId);
		employeeId++;
		admin = new Admin("root", "root");
		String room1 = "first room";
		String room2 = "second room";

		// adding some test movies
		Movie film1 = new Movie("Hannibal", "horror", 180);
		Movie film2 = new Movie("Silent Hill", "horror", 120);
		Movie film3 = new Movie("It", "horror", 90);
		Movie film4 = new Movie("Exorcist", "horror", 120);
		movies.putIfAbsent(film1.getTitle(), film1);
		movies.putIfAbsent(film2.getTitle(), film2);
		movies.putIfAbsent(film3.getTitle(), film3);
		movies.putIfAbsent(film4.getTitle(), film4);

		// test dates, events

		int date1 = 15;
		int date2 = 10;

		Event event1 = new Event(film1, date1, 9, new Boolean[3][3], room1);
		Event event2 = new Event(film1, date2, 9, new Boolean[3][3], room2);

		ArrayList<Event> movieEvent = new ArrayList<>();

		movieEvent.add(event1);
		allEvent.put(event1.movie.getTitle(), movieEvent);
		movieEvent.add(event2);
		allEvent.put(event2.movie.getTitle(), movieEvent);

		mainmenu();
	}

	public static void mainmenu() {
		System.out.println("Menu:");
		System.out.println("1. Visitor menu");
		System.out.println("2. Employee menu");
		System.out.println("3. Admin menu");
		System.out.println("4. Exit");
		scan = new Scanner(System.in);
		try {
			int selection = scan.nextInt();
			switch (selection) {
			case 1:
				visitorLogin();
				break;
			case 2:
				employeeLogin();
				break;
			case 3:
				adminLogin();
				break;
			case 4:
				System.out.println("Good bye!");
				System.exit(0);
				break;
			default:
				System.out.println("Please add a valid number!");
				mainmenu();
				break;
			}
		} catch (Exception e) {
			System.out.println("Not a number, please enter a number between 1 and 4!");
			mainmenu();
		}
	}

	private static void visitorLogin() {
		scan = new Scanner(System.in);
		try {
			System.out.println("Give me your name: (JonDoe)");
			String userName = scan.nextLine();

			// check if user exists
			if (users.stream().filter(e -> e.getUsername().equals(userName)).findFirst().isPresent()) {
				scan = new Scanner(System.in);
				try {
					// get pw
					System.out.println("Give me the password ( 1234 )" + userName);
					String pw = scan.nextLine();
					user = users.stream().filter(e -> e.getUsername().equals(userName)).findFirst().get();

					// check if pw is valid

					if (users.stream().filter(e -> e.getUsername().equals(userName)).findFirst()
							.filter(e -> e.getPassword().equals(pw)).isPresent()) {
						System.out.println("Succesfull login!");
						visitormenu();
					} else {
						System.out.println("Incorrect password!");
						mainmenu();
					}

				} catch (Exception e) {
					System.out.println("Wrong input...");
					mainmenu();
				}
			} else {
				System.out.println("Not existing User! Creating the following user: " + userName);
				user = new User(userName, userid, "1234");
				users.add(user);
				userid++;
				visitormenu();
			}
		} catch (Exception e) {
			System.out.println("Wrong input....");
			mainmenu();
		}

	}

	private static void employeeLogin() {

		scan = new Scanner(System.in);
		try {

			System.out.println("Give me the employee name: (JaneDoe)");
			String employeeName = scan.nextLine();

			// check if employee exists
			if (employees.stream().filter(e -> e.getEmployeename().equals(employeeName)).findFirst().isPresent()) {
				scan = new Scanner(System.in);
				try {
					// get pw
					System.out.println("Give me the password (1234) " + employeeName);
					String pw = scan.nextLine();
					employee = employees.stream().filter(e -> e.getEmployeename().equals(employeeName)).findFirst()
							.get();

					// check if pw is valid
					if (employees.stream().filter(e -> e.getEmployeename().equals(employeeName)).findFirst()
							.filter(e -> e.getPassword().equals(pw)).isPresent()) {
						System.out.println("Succesfull login!");
						employeeMenu();
					} else {
						System.out.println("Incorrect password!");
						mainmenu();
					}

				} catch (Exception e) {
					System.out.println("Wrong input...");
					mainmenu();
				}
			} else {
				System.out.println("Not existing employee");
				mainmenu();
			}

		} catch (Exception e) {
			System.out.println("Wrong input...");
			employeeLogin();
		}
	}

	private static void adminLogin() {

		scan = new Scanner(System.in);
		try {
			System.out.println("Give me the admin password: (root) ");
			String input = scan.nextLine();
			if (!input.equals(admin.password)) {
				System.out.println("Incorrect password!");
				mainmenu();
			} else {
				System.out.println("Succesfull login!");
				adminMenu();
			}
		} catch (Exception e) {
			System.out.println("Wrong input...");
			mainmenu();
		}
	}

	static void visitormenu() {
		System.out.println("Visitor menu");
		System.out.println("1. Search/book movie");
		System.out.println("2. See my tickets/reservations");
		System.out.println("3. Back to main menu");
		scan = new Scanner(System.in);
		try {
			int selection = scan.nextInt();
			switch (selection) {
			case 1:
				searchmenu();
				visitormenu();
				break;
			case 2:
				getReservationsMenu();
				visitormenu();
				break;
			case 3:
				mainmenu();
				break;
			default:
				System.out.println("Not a valid number");
				visitormenu();
				break;
			}
		} catch (Exception e) {
			System.out.println("Not a number, please enter a number");
			visitormenu();
		}
	}

	static void employeeMenu() {
		System.out.println("Employee menu");
		System.out.println("1. Add movie");
		System.out.println("2. Delete movie");
		System.out.println("3. Add event");
		System.out.println("4. Delete event");
		System.out.println("5. See all reservations");
		System.out.println("6. Set ticket price");
		System.out.println("7. Back to main menu");
		scan = new Scanner(System.in);
		try {
			int selection = scan.nextInt();
			switch (selection) {
			case 1:
				addMovie();
				employeeMenu();
				break;
			case 2:
				delMovie();
				employeeMenu();
				break;
			case 3:
				addEvent();
				employeeMenu();
				break;
			case 4:
				delEvent();
				employeeMenu();
				break;
			case 5:
				allReservationMenu();
				employeeMenu();
				break;
			case 6:
				setPriceMenu();
				employeeMenu();
				break;
			case 7:
				mainmenu();
				break;
			default:
				System.out.println("Not a valid number");
				employeeMenu();
				break;
			}
		} catch (Exception e) {
			System.out.println("Not a number, please enter a number");
			employeeMenu();
		}
	}

	private static void adminMenu() {

		System.out.println("Admin menu");
		System.out.println("1. Add employee");
		System.out.println("2. Delete employee");
		System.out.println("3. Back to main menu");

		scan = new Scanner(System.in);
		try {
			int selection = scan.nextInt();
			switch (selection) {
			case 1:
				addEmployeeMenu();
				break;
			case 2:
				deleteEmployeeMenu();
				break;
			case 3:
				mainmenu();
				break;
			default:
				System.out.println("Not a valid number");
				adminMenu();
				break;
			}
		} catch (Exception e) {
			System.out.println("Not a number, please enter a number");
			adminMenu();
		}
	}

	private static void searchmenu() {
		for(Map.Entry<String, Movie> item : movies.entrySet()) {
			System.out.println(item.toString());
		}
		int i = 0;
		String titleToFind = null;
		ArrayList<Event> movieEvents;
		scan = new Scanner(System.in);
		try {
			System.out.println("Give me the title of the movie:");
			titleToFind = scan.next();
		} catch (Exception inpuException) {
			System.out.println("Bad input somewhere! Try again!");
			visitormenu();
		}

		if (findMovie(titleToFind, movies)) {
			movieEvents = listEventsForMovie(titleToFind, allEvent);
			if (movieEvents.isEmpty()) {
				System.out.println("No event for that movie");
				visitormenu();
			}
			// list all events for searched movie
			else {
				for (Event item : movieEvents) {
					System.out.println(i + 1 + ".:" + item.toString());
					i++;
				}

				int index = 0;
				Event movieEvent = null;
				scan = new Scanner(System.in);
				try {
					System.out.println(
							"Give me the number of event you want to book a ticket! \n If you want to go back to the visitor menu, please type some text:");
					index = scan.nextInt();
					// show avail seats for that event
					movieEvent = movieEvents.get(index - 1);
					showSeats(index, movieEvent);

				} catch (Exception e) {
					System.out.println("Back to visitor menu...");
					visitormenu();
				}

				int[] seats = { 0, 0 };
				int counter = 0;
				boolean isValid = false;
				do {
					isValid = reserveSeat(movieEvent, seats);
					counter++;
				} while (!isValid && counter < 4);
				if (counter > 3) {
					System.out.println("Too much invalid input, redirecting to the visitor menu!");
					visitormenu();
				}
				makeReservation(reservations, movieEvent, seats);
				System.out.println("Do you want to pay now for this reservation? Y/N");
				scan = new Scanner(System.in);
				try {
					String answ = scan.nextLine();
					if (answ.startsWith("Y") || answ.startsWith("y"))
						payReservation(reservations, movieEvent, seats);
					visitormenu();
				} catch (Exception e) {
					// TODO: handle exception
				}

			}
		} else {
			System.out.println("There is no such movie!\n");
			visitormenu();
		}
	}

	private static void getReservationsMenu() {

		ArrayList<Reservation> userreservations;
		userreservations = getUserReservations(reservations);
		if (userreservations.size() != 0) {
			System.out.println("Your reservations, " + user.getUsername());
			for (Reservation reservation : userreservations) {
				System.out.println(reservation.toString());
			}
		} else {
			System.out.println("There is no reservations for you, " + user.getUsername());
		}

	}

	private static void addMovie() {
		// TODO check if movie already in movies
		scan = new Scanner(System.in);
		String title = null;
		String genre = null;
		int lengthInMin = 0;
		try {
			System.out.println("Give me the title of the movie:");
			title = scan.nextLine();

			System.out.println("Give me the genre of the movie:");
			genre = scan.nextLine();

			System.out.println("Give me the length of the movie in mins:");
			lengthInMin = scan.nextInt();

		} catch (Exception e) {
			System.out.println("Wrong input.... redirecting!");
			employeeMenu();
		}
		addMovie(title, genre, lengthInMin, movies);
	}

	private static void addEvent() {
		for(Map.Entry<String, Movie> item : movies.entrySet()) {
			System.out.println(item.toString());
		}
		String titleToFind = null;
		ArrayList<Event> movieEvent;
		scan = new Scanner(System.in);
		try {
			System.out.println("Give me the title of the movie:");
			titleToFind = scan.nextLine();
		} catch (Exception e) {
			System.out.println("Wrong input.... redirecting!");
			employeeMenu();
		}

		if (movies.containsKey(titleToFind)) {
			System.out.println(movies.get(titleToFind).toString());
			if (allEvent.containsKey(titleToFind)) {
				movieEvent = allEvent.get(titleToFind);
			} else {
				movieEvent = new ArrayList<>();
			}

			// get input of event data
			int date = 0;
			String room = null;
			scan = new Scanner(System.in);
			try {
				System.out.println("Give me the start of the movie:");
				date = scan.nextInt();

			} catch (Exception e) {
				System.out.println("Not a valid start date");
				employeeMenu();
			}
			scan = new Scanner(System.in);
			try {
				System.out.println("Give me the room of the event:");
				room = scan.nextLine();
			} catch (Exception e) {
				employeeMenu();
			}

			Event event = new Event(movies.get(titleToFind), date, 9, new Boolean[3][3], room);
			movieEvent.add(event);
			allEvent.put(titleToFind, movieEvent);

		} else {
			System.out.println("There is no such movie!");
			employeeMenu();
		}
	}

	private static void delMovie() {
		System.out.println("All movies:");
		for (String aMovie : movies.keySet()) {
			System.out.println(movies.get(aMovie));
		}
		String title = null;
		scan = new Scanner(System.in);
		try {
			System.out.println("Give me the title of the movie to delete:");
			title = scan.next();
			if (movies.containsKey(title) && !(allEvent.containsKey(title))) {
				movies.remove(title);
			} else if (movies.containsKey(title)) {
				System.out.println("Can't delete movie, it is in some events");
			} else {
				System.out.println("No such movie");
			}

		} catch (Exception e) {
			System.out.println("Wrong input.... redirecting!");
			delMovie();
		}

	}

	private static void delEvent() {
		String titleToFind = null;
		ArrayList<Event> movieEvents;
		scan = new Scanner(System.in);
		int i = 0;

		try {
			System.out.println("Give me the title of the movie:");
			titleToFind = scan.next();
		} catch (Exception inpuException) {
			System.out.println("Bad input somewhere! Try again!");
			employeeMenu();
		}

		if (findMovie(titleToFind, movies)) {
			movieEvents = listEventsForMovie(titleToFind, allEvent);
			if (movieEvents.isEmpty()) {
				System.out.println("No event for that movie");
				employeeMenu();
			}

			else {
				for (Event item : movieEvents) {
					System.out.println(i + 1 + ".:" + item.toString());
					i++;
				}
				// get event index to delete
				int index = 0;
				scan = new Scanner(System.in);
				try {
					System.out.println(
							"Give me the number of event you want to delete \n or enter some text to go back to visitor menu:");
					index = scan.nextInt();
					if (movieEvents.get(index - 1).pieces >= 9) {
						movieEvents.remove(index - 1);
						allEvent.put(titleToFind, movieEvents);
					} else {
						System.out.println("Can't delete that event, there are some reservation for it");
					}
				} catch (Exception e) {
					System.out.println("Wrong input.... redirecting!");
					employeeMenu();
				}

			}
		}
	}

	private static void allReservationMenu() {

		listAllReservation(reservations);
	}

	private static void setPriceMenu() {
		scan = new Scanner(System.in);
		try {
			System.out.println("The ticket price now: " + price);
			System.out.println("Give me the ticket price:");
			price = scan.nextInt();

		} catch (Exception e) {
			System.out.println("Wrong input.... redirecting!");
			employeeMenu();
		}

	}

	private static void addEmployeeMenu() {
		String employeeName = null;
		scan = new Scanner(System.in);
		try {
			System.out.println("Give me the name of employee:");
			employeeName = scan.nextLine();
			if (createEmployee(employeeName, employees, employeeId)) {
				employeeId++;
			} else {
				System.out.println("The employee already exists!");
			}
			adminMenu();
		} catch (Exception inpuException) {
			System.out.println("Wrong input.... redirecting!");
			adminMenu();
		}
	}

	private static void deleteEmployeeMenu() {
		// print all existing employees to the console
		for (Employee item : employees) {
			System.out.println(item.toString());
		}

		String employeeName = null;
		scan = new Scanner(System.in);
		try {
			System.out.println("Give me the name of employee or a number to go back to admin menu:");
			employeeName = scan.nextLine();
			deleteEmployee(employeeName, employees);
			adminMenu();
		} catch (Exception inpuException) {
			System.out.println("Wrong input.... redirecting!");
			adminMenu();
		}
	}

	public static void deleteEmployee(String employeeName, List<Employee> employees) {
		employees.removeIf(e -> e.getEmployeename().equals(employeeName) ? true : false);
	}

	public static boolean createEmployee(String employeeName, List<Employee> employees, int employeeId) {
		if (!employees.stream().filter(e -> e.getEmployeename().equals(employeeName)).findFirst().isPresent()) {
			employees.add(new Employee(employeeName, employeeId));
			return true;
		} else {
			return false;
		}
	}

	public static ArrayList<Event> listEventsForMovie(String titleToFind,
			SortedMap<String, ArrayList<Event>> allEvent) {
		ArrayList<Event> movieEvents = new ArrayList<Event>();

		if (allEvent.containsKey(titleToFind)) {
			movieEvents = allEvent.get(titleToFind);
			// sort it by "date"
			movieEvents.sort((p1, p2) -> p1.date.compareTo(p2.date));
		}
		return movieEvents;
	}

	public static ArrayList<Reservation> getUserReservations(SortedMap<Integer, ArrayList<Reservation>> reservations) {

		ArrayList<Reservation> userreservations;

		if (reservations.containsKey(userid)) {
			userreservations = reservations.get(userid);
		} else {
			userreservations = new ArrayList<>();
		}

		return userreservations;
	}

	public static Boolean findMovie(String titleToFind, SortedMap<String, Movie> movies) {
		return movies.containsKey(titleToFind);
	}

	public static void showSeats(int index, Event movieEvent) {

		Boolean[][] matrix = movieEvent.occupancy;
		System.out.println("row\\seat:1 2 3");
		for (int i = 0; i < matrix.length; i++) {
			System.out.print(i + 1 + ":\t ");
			for (int j = 0; j < matrix[i].length; j++) {
				if (matrix[i][j]) {
					System.out.print("X ");
				} else {
					System.out.print("O ");
				}
			}
			System.out.println();
		}
	}

	public static boolean reserveSeat(Event movieEvent, int[] seats) {
		Scanner scan = new Scanner(System.in);
		try {
			System.out.println("Give me the row  you want to go:");
			seats[0] = scan.nextInt() - 1;
			if (seats[0] < 0 || seats[0] > 3) {
				System.out.println("Invalid row number");
				return false;
			}
			System.out.println("Give me the seat  you want to go:");
			seats[1] = scan.nextInt() - 1;
			if (seats[1] < 0 || seats[1] > 3) {
				System.out.println("Invalid seat number");
				return false;
			}
			if (movieEvent.occupancy[seats[0]][seats[1]] == true) {
				System.out.println("That seat is already reserved");
				return false;
			}

			return true;
		} catch (Exception e) {
			System.out.println("Invalid input...");
			return false;
		}

	}

	public static void makeReservation(SortedMap<Integer, ArrayList<Reservation>> reservations, Event movieEvent,int[] seats) {

		int date = movieEvent.date;
		String room = movieEvent.room;
		Reservation reservation = new Reservation(user.getUsername(), date, seats, room, movieEvent.movie.getTitle());

		
		movieEvent.pieces--;
		movieEvent.occupancy[seats[0]][seats[1]] = true;

		// check user reservation list, and make if not exists

		ArrayList<Reservation> userreservations;
		userreservations = getUserReservations(reservations);

		
		userreservations.add(reservation);

	
		reservations.put(user.getUserid(), userreservations);
	}

	public static void payReservation(SortedMap<Integer, ArrayList<Reservation>> reservations, Event movieEvent,int[] seats) {

		ArrayList<Reservation> userreservations;
		userreservations = reservations.get(userid);
		userreservations = getUserReservations(reservations);
		Random rng = new Random();
		int bankAuth = rng.nextInt();
		if (bankAuth % 2 == 0) {
			userreservations.stream().filter(r -> r.title.equals(movieEvent.movie.getTitle())).findFirst()
					.orElse(null).paid = true;
			System.out.println("Ticket has been paid succesfully!");
		} else {
			System.out.println("Something wrong with payment, please pay at the Movie!");
		}

	}

	public static void listAllReservation(SortedMap<Integer, ArrayList<Reservation>> reservations) {
		for (int userId : reservations.keySet()) {
			System.out.print("userID:" + userId + ":\n");
			for (Reservation reservation : reservations.get(userId)) {
				System.out.println(reservation.toString());
			}
			System.out.println();
		}
	}

	public static void addMovie(String title, String genre, int lengthInMin, SortedMap<String, Movie> movies) {
		Movie film = new Movie(title, genre, lengthInMin);
		movies.putIfAbsent(title, film);
	}

}
