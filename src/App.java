/*
 * Group 11
 * Airplane Reservation System with double linked list
 */

import java.util.*;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class App {
	
	/*This is the location for the database, please change it with your database location 
	*keep in mind to add extra "\" after each "\" in your destination link
	*/
	static String Destinations_Database = "D:\\Universty\\2021\\Spring - Second Term\\Data Structure\\Project\\Airplane Reservation System\\src\\Destinations.csv";
	static String Booking_info = "D:\\Universty\\2021\\Spring - Second Term\\Data Structure\\Project\\Airplane Reservation System\\src\\DataBase.csv";
	private static final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

	// Searching for ticket number
	static String[] Search(String Ticket) throws IOException {

		// Reading the file
		String line = "";
		String splitBy = ",";
		// parsing a CSV file into BufferedReader class constructor
		BufferedReader br = new BufferedReader(new FileReader(Booking_info));
		DoubleLinkedList DB = new DoubleLinkedList(); // Creating the list where all values will be saved in
		while ((line = br.readLine()) != null) // returns a Boolean value
		{
			String[] temp_DB = line.split(splitBy); // use comma as separator
			DB.AddNode(temp_DB[0]); // Ticket
			DB.AddNode(temp_DB[1]); // Date
			DB.AddNode(temp_DB[2]); // Destination
			DB.AddNode(temp_DB[3]); // Class
			DB.AddNode(temp_DB[4]); // Price
			DB.AddNode(temp_DB[5]); // Name
			DB.AddNode(temp_DB[6]); // Phone
			DB.AddNode(temp_DB[7]); // Passport
		}
		// Declaring the strings to temprary save the values we get from searching then
		// save it into the array
		String Date = null, Destination = null, Class = null, Price = null, Name = null, Phone = null, Passport = null;

		// The searching method by going through every object in the list DB
		for (int i = 0; i < DB.size(); i++) {
			// If a value matches then we will take the 7 values after it
			if (DB.get(i).data.equals(Ticket) == true) {
				/*
				 * Adding the value that't right next to the ticket number as in the CSV file
				 * the values are arranged in a linear fashion
				 */
				Date = (String) DB.get(i + 1).data;
				Destination = (String) DB.get(i + 2).data;
				Class = (String) DB.get(i + 3).data;
				Price = (String) DB.get(i + 4).data;
				Name = (String) DB.get(i + 5).data;
				Phone = (String) DB.get(i + 6).data;
				Passport = (String) DB.get(i + 7).data;
			}
		}

		// Creating an array to store in it the destinations and prices
		String[] Result = { "Ticket", "Date", "Destination", "Class", "Price", "Name", "Phone", "Passport" };
		Result[0] = Ticket; // Ticket
		Result[1] = Date; // Date
		Result[2] = Destination; // Destination
		Result[3] = Class; // Class
		Result[4] = Price; // Price
		Result[5] = Name; // Name
		Result[6] = Phone; // Phone
		Result[7] = Passport; // Passport
		br.close();
		return Result;
	}

	// Printing Destinations
	static String[] Destinations() throws IOException {
		Scanner sc = new Scanner(System.in);
		String[] cities = { "Cairo", "Alex", "Dubai", "Beirut", "Riyadh", "London", "Berlin", "Beijing", "New York" };
		// Ask them for the "from"
		System.out.println("Please choose the number for your option");
		for (int i = 0; i <= (cities.length - 1); i++) {
			System.out.println((i + 1) + ". " + cities[i]);
		}
		// Take the input
		System.out.print("From: ");
		int citychoise = sc.nextInt() - 1;

		// Create a list to save the destinations choise
		DoubleLinkedList temp_to = new DoubleLinkedList();
		// An integer to show the user the number of their choise
		int city_temp_num = 1;
		for (int j = 0; j < cities.length; j++) {
			if (cities[citychoise] != cities[j]) {
				String k = (city_temp_num + ". " + cities[citychoise] + " - " + cities[j]);
				temp_to.AddNode(k);
			} else {
				city_temp_num--;
			}
			city_temp_num++;
		}
		// print the from and to values from the list temp_to
		for (int i = 0; i < temp_to.size(); i++) {
			System.out.println(temp_to.get(i).data);
		}

		// take the input for the destination
		System.out.print("From: ");
		int temp_to_number = sc.nextInt() - 1;
		String destination = temp_to.get(temp_to_number).data;
		destination = destination.substring(3, destination.length());

		String economy = "";
		String business = "";
		// Reading the file
		String line = "";
		String splitBy = ",";
		// parsing a CSV file into BufferedReader class constructor
		BufferedReader br = new BufferedReader(new FileReader(Destinations_Database));
		DoubleLinkedList DB = new DoubleLinkedList(); // Creating the list where all values will be saved in
		while ((line = br.readLine()) != null) // returns a Boolean value
		{
			String[] temp_DB = line.split(splitBy); // use comma as separator
			DB.AddNode(temp_DB[0]); // reconfimring the destinatnation pair
			DB.AddNode(temp_DB[1]); // adding the economic class price
			DB.AddNode(temp_DB[2]); // adding the business class price
		}
		for (int i = 0; i < DB.size(); i++) {
			if (DB.get(i).data.equals(destination) == true) {

				economy = DB.get(i + 1).data;
				business = DB.get(i + 2).data;

			}
		}
		// Creating an array to store in it the destinations and prices
		String[] Final = { "Destination", "Econmicprice", "BusinessPrice" };
		Final[0] = destination; // Destination pair
		Final[1] = economy; // Economic class price
		Final[2] = business; // Business class price

		return Final;

	}

	// Editing the file
	static void Edit(String Ticket_num, String[] Ticket) throws IOException {

		// Reading the file
		String line = "";
		String splitBy = ",";
		int lines_num = 0;
		int location = 0;
		// parsing a CSV file into BufferedReader class constructor
		BufferedReader br = new BufferedReader(new FileReader(Booking_info));
		DoubleLinkedList DB = new DoubleLinkedList(); // Creating the list where all values will be saved in
		while ((line = br.readLine()) != null) // returns a Boolean value
		{
			String[] temp_DB = line.split(splitBy); // use comma as separator
			DB.AddNode(temp_DB[0]); // Ticket
			DB.AddNode(temp_DB[1]); // Date
			DB.AddNode(temp_DB[2]); // Destination
			DB.AddNode(temp_DB[3]); // Class
			DB.AddNode(temp_DB[4]); // Price
			DB.AddNode(temp_DB[5]); // Name
			DB.AddNode(temp_DB[6]); // Phone
			DB.AddNode(temp_DB[7]); // Passport
			lines_num++;
		}
		br.close();

		// The searching method by going through every object in the list DB
		for (int i = 0; i < DB.size(); i++) {
			// If a value matches then we will take the 7 values after it
			if (DB.get(i).data.equals(Ticket_num) == true) {
				location = i;
			}
		}

		/*
		 * Editing the list with the new ticket information: In the next part we will
		 * start at the ticket lcoation in the list and edit 8 items after that (as the
		 * line is 8 items long) by a loop that takes the values from the entered array
		 * "Ticket" and save it to the targeted line We also use "numberOfItems" as the
		 * a counter from 0 to 7 to get the items from the array "Ticket"
		 */
		int numberOfItems = 0;
		for (int i = location; i < (location + 8); i++) // Start at the ticket number location in the
		{
			DB.set(i, Ticket[numberOfItems]);
			numberOfItems++;
		}

		/*
		 * In this part we will need to convert the list "DB" after editing into CSV
		 * format We start by giving the numbers of lines so we can print 8 items from
		 * "DB" then make a new line we use the "itemsinline" to keep track of the index
		 * number that would be printed next
		 */

		FileWriter csvWriter = new FileWriter(Booking_info);
		int itemsinline = 0;
		// Going through the loop with number of lines
		for (int i = 0; i < lines_num; i++) {
			// A loop value with 7 iteration to print till phone
			for (int j = 0; j < 7; j++) {
				csvWriter.append(DB.get(itemsinline).data);
				csvWriter.append(",");
				itemsinline++;
			}
			// printing the "Passport" individually to make sure we won't add a comma after
			// it
			csvWriter.append(DB.get(itemsinline).data);
			// increasing the "itemsinline" counter to take care of the extra iteration that
			// should have been added in the loop
			itemsinline++;
			csvWriter.append("\n");
		}
		csvWriter.flush();
		csvWriter.close();
	}

	// Deleting a ticket
	static void Delete(String Ticket_num) throws IOException {

		// Reading the file
		String line = "";
		String splitBy = ",";
		int lines_num = 0;
		int location = 0;
		// parsing a CSV file into BufferedReader class constructor
		BufferedReader br = new BufferedReader(new FileReader(Booking_info));
		DoubleLinkedList DB = new DoubleLinkedList(); // Creating the list where all values will be saved in
		while ((line = br.readLine()) != null) // returns a Boolean value
		{
			String[] temp_DB = line.split(splitBy); // use comma as separator
			DB.AddNode(temp_DB[0]); // Ticket
			DB.AddNode(temp_DB[1]); // Date
			DB.AddNode(temp_DB[2]); // Destination
			DB.AddNode(temp_DB[3]); // Class
			DB.AddNode(temp_DB[4]); // Price
			DB.AddNode(temp_DB[5]); // Name
			DB.AddNode(temp_DB[6]); // Phone
			DB.AddNode(temp_DB[7]); // Passport
			lines_num++;
		}
		br.close();
		// The searching method by going through every object in the list DB
		for (int i = 0; i < DB.size(); i++) {
			// If a value matches then we will take the 7 values after it
			if (DB.get(i).data.equals(Ticket_num) == true) {
				location = i;
			}
		}

		/*
		 * Identifing the ticket number and deleting the 7 items after it
		 */

		for (int i = 0; i < 8; i++) // Start at the ticket number location in the
		{
			DB.delete(location);
		}
		lines_num--;

		/*
		 * In this part we will need to convert the list "DB" after editing into CSV
		 * format We start by giving the numbers of lines so we can print 8 items from
		 * "DB" then make a new line we use the "itemsinline" to keep track of the index
		 * number that would be printed next
		 */

		FileWriter csvWriter = new FileWriter(Booking_info);
		int itemsinline = 0;
		// Going through the loop with number of lines
		for (int i = 0; i < lines_num; i++) {
			// A loop value with 7 iteration to print till phone
			for (int j = 0; j < 7; j++) {
				csvWriter.append(DB.get(itemsinline).data);
				csvWriter.append(",");
				itemsinline++;
			}
			// printing the "Passport" individually to make sure we won't add a comma after
			// it
			csvWriter.append(DB.get(itemsinline).data);
			// increasing the "itemsinline" counter to take care of the extra iteration that
			// should have been added in the loop
			itemsinline++;
			csvWriter.append("\n");
		}
		csvWriter.flush();
		csvWriter.close();
	}

	// Generating ticket number
	static String GenerateTicket() throws IOException {

		int ticketnumber = 0;
		// Reading the file
		String line = "";
		String splitBy = ",";
		// parsing a CSV file into BufferedReader class constructor
		BufferedReader br = new BufferedReader(new FileReader(Booking_info));
		while ((line = br.readLine()) != null) // returns a Boolean value
		{
			try {
				String[] temp_DB = line.split(splitBy); // use comma as separator
				String temp = temp_DB[0];
				ticketnumber = Integer.parseInt(temp); // get Ticket and convert to integer
			} catch (NumberFormatException e) {
				ticketnumber = 0;
			}
		}
		br.close();
		String result = String.valueOf(ticketnumber + 1); // convert int back to string after adding 1

		return result;
	}

	// Saving the new ticket
	static void Save(String[] Ticket) throws IOException {

		// Reading the file
		String line = "";
		String splitBy = ",";
		int lines_num = 0;
		int location = 0;
		// parsing a CSV file into BufferedReader class constructor
		BufferedReader br = new BufferedReader(new FileReader(Booking_info));
		DoubleLinkedList DB = new DoubleLinkedList(); // Creating the list where all values will be saved in
		while ((line = br.readLine()) != null) // returns a Boolean value
		{
			String[] temp_DB = line.split(splitBy); // use comma as separator
			DB.AddNode(temp_DB[0]); // Ticket
			DB.AddNode(temp_DB[1]); // Date
			DB.AddNode(temp_DB[2]); // Destination
			DB.AddNode(temp_DB[3]); // Class
			DB.AddNode(temp_DB[4]); // Price
			DB.AddNode(temp_DB[5]); // Name
			DB.AddNode(temp_DB[6]); // Phone
			DB.AddNode(temp_DB[7]); // Passport
			lines_num++;
		}
		br.close();
		/*
		 * Editing the list with the new ticket information: In the next part we will
		 * start at the ticket lcoation in the list and edit 8 items after that (as the
		 * line is 8 items long) by a loop that takes the values from the entered array
		 * "Ticket" and save it to the targeted line We also use "numberOfItems" as the
		 * a counter from 0 to 7 to get the items from the array "Ticket"
		 */
		for (int i = 0; i < 8; i++) // Start at the ticket number location in the
		{
			DB.AddNode(Ticket[i]);
		}
		lines_num++;

		/*
		 * In this part we will need to convert the list "DB" after editing into CSV
		 * format We start by giving the numbers of lines so we can print 8 items from
		 * "DB" then make a new line we use the "itemsinline" to keep track of the index
		 * number that would be printed next
		 */

		FileWriter csvWriter = new FileWriter(Booking_info);
		int itemsinline = 0;
		// Going through the loop with number of lines
		for (int i = 0; i < lines_num; i++) {
			// A loop value with 7 iteration to print till phone
			for (int j = 0; j < 7; j++) {
				csvWriter.append(DB.get(itemsinline).data);
				csvWriter.append(",");
				itemsinline++;
			}
			// printing the "Passport" individually to make sure we won't add a comma after
			// it
			csvWriter.append(DB.get(itemsinline).data);
			// increasing the "itemsinline" counter to take care of the extra iteration that
			// should have been added in the loop
			itemsinline++;
			csvWriter.append("\n");
		}
		csvWriter.flush();
		csvWriter.close();
	}

	public static void main(String[] args) throws Exception {

		Scanner read = new Scanner(System.in);

		// Declearing string where we will save the data in
		String Tickets = null, Date = null, Destination = null, Class = null, Price = null, Name = null, Phone = null,
				Passport = null;

		// Greeting
		System.out.println("Hi there!");
		System.out.println("You look like you can use a vacation!");
		System.out.println();

		while (true) {
			System.out.println("1. Book a Flight");
			System.out.println("2. Show My Booking");
			System.out.println("3. Exit");
			System.out.print("Please choose your option: ");
			int option = read.nextInt();

			if (option == 3)
				break;
			// The Main Menu:
			// **************************************************************
			if (option == 1) {
				// Choosing the Date:
				// **************************************************************
				// Getting today's date:
				Date currentDate = new Date();
				// convert date to calendar
				Calendar c = Calendar.getInstance();
				c.setTime(currentDate);

				while (true) {
					{
						System.out.println("Please Choose Date:");
						// Creating an array to save dates then choose from it
						DoubleLinkedList Dates = new DoubleLinkedList();
						// Printing today's date and the next 7 days
						for (int i = 0; i < 7; i++) {
							Date currentDatePlusOne = c.getTime();
							Dates.AddNode(dateFormat.format(currentDatePlusOne));
							c.add(Calendar.DATE, 1);
							System.out.print(i + 1 + ". ");
							System.out.println(Dates.get(i).data);
						}
						System.out.print("Please choose your date by entering its number: ");
						int Date_opt = read.nextInt();
						try {
							Date = Dates.get(Date_opt - 1).data;
							break;
						} catch (Exception e) {
							System.out.println("Please enter a valid option between 1 and 7");
							System.out.println();
							continue;
						}
					}
				} // end of while loop
					// **************************************************************

				// Declearing a puplic variable that will be used to store destinations and
				// prices
				String[] Destinations = null;

				// Choosing the destination:
				// **************************************************************
				while (true) {
					try {
						System.out.println("Please Choose Destination:");

						Destinations = Destinations();
						Destination = Destinations[0];
						break;
					} catch (Exception e) {
						System.out.println("Please enter a valid option between 1 and 7");
						System.out.println();
						continue;
					}
				}
				// **************************************************************

				// Choosing the class:
				// **************************************************************
				while (true) {
					try {
						System.out.println("Please Choose Class:");
						System.out.println("1. Economic (EGP " + Destinations[1] + ")");
						System.out.println("2. Business (EGP " + Destinations[2] + ")");
						System.out.print("Please choose class by entering its number: ");
						int Class_opt = read.nextInt();
						if (Class_opt == 1) {
							Class = "Economic";
							Price = Destinations[1];
						} else if (Class_opt == 2) {
							Class = "Business";
							Price = Destinations[2];
						} else {
							System.out.println("Please enter a valid option between 1 and 7");
							System.out.println();
							continue;
						}
						break;
					} catch (Exception e) {
						System.out.println("Please enter a valid option between 1 and 7");
						System.out.println();
						continue;
					}
				}
				// **************************************************************

				// Entering personal infromation:
				// **************************************************************

				// ************************************
				// This code doesn't do anything but for some reason it wouldn't take
				// the name without entering a dummy string before it. Have no idea why
				System.out.print("");
				String str = read.nextLine();
				// ************************************

				// Taking the name
				System.out.print("Please enter your Name: ");
				Name = read.nextLine();

				// Taking the Phone Number
				System.out.print("Please enter your Phone number: ");
				Phone = read.nextLine();

				// Taking the passport number
				System.out.print("Please enter your Passport number: ");
				Passport = read.nextLine();
				// **************************************************************

				// Generate the ticket number
				Tickets = GenerateTicket();

				// Saving the information into an array
				String[] Ticket = { Tickets, Date, Destination, Class, Price, Name, Phone, Passport };

				// Showing booking details to the user
				System.out.println("Thanks for your time!");
				System.out.println("Your ticket have been booked with the following information:");
				System.out.println("Ticket Number: " + Tickets);
				System.out.println("Date: " + Date);
				System.out.println("Destination: " + Destination);
				System.out.println("Class: " + Class);
				System.out.println("Price: EGP " + Price);
				System.out.println("Name: " + Name);
				System.out.println("Phone: " + Phone);
				System.out.println("Passport: " + Passport);

				// Saving the ticket into the database
				Save(Ticket);
			} // end of the main menu if statment
				// ************************************************************

			String ticket_num;

			if (option == 2) {
				// Show the Booking information:
				// *******************************************************************************************************************
				while (true) {
					try {
						// ************************************
						// This code doesn't do anything but for some reason it wouldn't take
						// the name without entering a dummy string before it. Have no idea why
						System.out.print("");
						String str = read.nextLine();
						// ************************************
						System.out.print("Please Enter Your Ticket Number: ");
						ticket_num = read.nextLine();
						String[] ticketinfo = Search(ticket_num);
						String str1 = ticketinfo[1];

						if (str1 == null) {
							System.out.println(
									"No results were found for this ticket, please confirm your ticket number and try again");
						} else {
							System.out.println("Ticket Number: " + ticketinfo[0]);
							System.out.println("Date: " + ticketinfo[1]);
							System.out.println("Destination: " + ticketinfo[2]);
							System.out.println("Class: " + ticketinfo[3]);
							System.out.println("Price: EGP " + ticketinfo[4]);
							System.out.println("Name: " + ticketinfo[5]);
							System.out.println("Phone: " + ticketinfo[6]);
							System.out.println("Passport: " + ticketinfo[7]);
							break;
						}
					} catch (Exception e) {
						System.out.println(
								"No results were found for this ticket, please confirm your ticket number and try again");
						continue;
					}
				}

				// Helping with the ticket:
				// *******************************************************************************************************************
				while (true) {
					System.out.println("1. Cancel Flight");
					System.out.println("2. Edit Flight Details");
					System.out.println("3. Exit");
					int option2 = read.nextInt();

					// exit
					if (option2 == 3) {
						break;
					}

					// The Cancel Flight menu:
					if (option2 == 1) {
						System.out.println("Are you sure you would like to cancel your flight?");
						System.out.println("1. Yes");
						System.out.println("2. No");
						int deletemenu = read.nextInt();
						// Deleting the ticket
						if (deletemenu == 1) {
							Delete(ticket_num);
							System.out.println("Ticket has been canceled");
							break;
						} else if (deletemenu == 2) {
							System.out.println("Ticket is still available");
							break;
						} else {
							System.out.println("Please enter valid option");
							continue;
						}
					} // end of the cancel flight menu

					// Edit flight menu
					// **********************************************************
					while (true) {

						if (option2 == 2) {
							System.out.println();
							System.out.println("What would you like to edit?");
							System.out.println("1. Date");
							System.out.println("2. Class");
							System.out.println("3. Destination");
							System.out.println("4. Name");
							System.out.println("5. Phone");
							System.out.println("6. Passport");
							System.out.println("7. Exit");

							System.out.print("Please choose your option: ");
							int editmenu = read.nextInt();

							String[] Editedticket = Search(ticket_num);

							// Editing the date
							if (editmenu == 1) {
								// Choosing the Date:
								// **************************************************************
								// Getting today's date:
								Date currentDate = new Date();
								// convert date to calendar
								Calendar c = Calendar.getInstance();
								c.setTime(currentDate);

								while (true) {
									{
										System.out.println();
										System.out.println("Please Choose Date:");
										// Creating an array to save dates then choose from it
										DoubleLinkedList Dates = new DoubleLinkedList();
										// Printing today's date and the next 7 days
										for (int i = 0; i < 7; i++) {
											Date currentDatePlusOne = c.getTime();
											Dates.AddNode(dateFormat.format(currentDatePlusOne));
											c.add(Calendar.DATE, 1);
											System.out.print(i + 1 + ". ");
											System.out.println(Dates.get(i).data);
										}
										System.out.print("Please choose your date by entering its number: ");
										int Date_opt = read.nextInt();
										try {
											Editedticket[1] = Dates.get(Date_opt - 1).data;
											Edit(ticket_num, Editedticket);
											System.out.print("Date have been changed to " + Dates.get(Date_opt - 1).data
													+ " successfully!");

											break;
										} catch (Exception e) {
											System.out.println("Please enter a valid option between 1 and 7");
											System.out.println();
											continue;
										}
									}
								} // end of while loop
									// **************************************************************
							} // end of the date edit menu

							// Editing the class:
							// **************************************************************
							else if (editmenu == 2) {

								while (true) {
									try {

										String economy = "";
										String business = "";
										// Reading the file
										String line = "";
										String splitBy = ",";
										// parsing a CSV file into BufferedReader class constructor
										BufferedReader br = new BufferedReader(new FileReader(Destinations_Database));
										DoubleLinkedList DB = new DoubleLinkedList(); // Creating the list where all
																						// values will be saved in
										while ((line = br.readLine()) != null) // returns a Boolean value
										{
											String[] temp_DB = line.split(splitBy); // use comma as separator
											DB.AddNode(temp_DB[0]); // reconfimring the destinatnation pair
											DB.AddNode(temp_DB[1]); // adding the economic class price
											DB.AddNode(temp_DB[2]); // adding the business class price
										}
										for (int i = 0; i < DB.size(); i++) {
											if (DB.get(i).data.equals(Editedticket[2]) == true) {
												economy = DB.get(i + 1).data;
												business = DB.get(i + 2).data;
											}
										}

										System.out.println("Please choose the class you would like ");
										System.out.println("1. Economic (EGP " + economy + ")");
										System.out.println("2. Business (EGP " + business + ")");
										System.out.print("Please choose the class: ");
										int class_edit = read.nextInt();

										if (class_edit == 1) {
											Editedticket[3] = "Economic";
											Editedticket[4] = economy;
										} else if (class_edit == 2) {
											Editedticket[3] = "Business";
											Editedticket[4] = business;

										} else {
											System.out.println("Please enter a valid option between 1 and 2");
											System.out.println();
											continue;
										}
										Edit(ticket_num, Editedticket);
										System.out.print("You booked " + Editedticket[3] + " with price of "
												+ Editedticket[4] + ", successfully!");
										break;
									} catch (Exception e) {
										System.out.println("Please enter a valid option between 1 and 2");
										System.out.println();
										continue;
									}
								}
							} // end of editing the class
								// **************************************************************

							// Editing the Destinations:
							// **************************************************************
							else if (editmenu == 3) {
								while (true) {
									try {
										System.out.println("Please Choose Destination:");

										String[] edit_Destinations = Destinations();
										Editedticket[2] = edit_Destinations[0];

										// to keep the class they choose
										String edited_ticket_class = Editedticket[3];
										System.out.println(edited_ticket_class);
										if (edited_ticket_class.equals("Economic")) {
											Editedticket[4] = edit_Destinations[1];

										}
										if (edited_ticket_class.equals("Business")) {
											Editedticket[4] = edit_Destinations[2];
										}
										// save our edits
										Edit(ticket_num, Editedticket);
										System.out.print("You booked the destination " + Editedticket[2]
												+ ", with new price of " + Editedticket[4] + ", successfully!");
										break;
									} catch (Exception e) {
										System.out.println("Please enter a valid option between 1 and 2");
										System.out.println();
										continue;
									}
								} // end of while loop
									// **************************************************************
							} // end of the destination edit menu

							// Editing the name:
							// **************************************************************
							else if (editmenu == 4) {
								while (true) {
									try {

										// ************************************
										// This code doesn't do anything but for some reason it wouldn't take
										// the name without entering a dummy string before it. Have no idea why
										System.out.print("");
										String str = read.nextLine();
										// ************************************

										// Taking the name
										System.out.print("Please enter your Name: ");
										Editedticket[5] = read.nextLine();
										// save our edits
										Edit(ticket_num, Editedticket);
										System.out.print("Name changed to " + Editedticket[5] + ", successfully!");
										break;
									} catch (Exception e) {
										System.out.println("Please enter a valid value");
										System.out.println();
										continue;
									}

								} // end of while loop
									// **************************************************************
							} // end of the destination edit menu

							// Editing the number:
							// **************************************************************
							else if (editmenu == 5) {
								while (true) {
									try {

										// ************************************
										// This code doesn't do anything but for some reason it wouldn't take
										// the name without entering a dummy string before it. Have no idea why
										System.out.print("");
										String str = read.nextLine();
										// ************************************

										// Taking the Phone Number
										System.out.print("Please enter your Phone number: ");
										Editedticket[6] = read.nextLine();
										// save our edits
										Edit(ticket_num, Editedticket);
										System.out.print(
												"Phone number changed to " + Editedticket[6] + ", successfully!");
										break;
									} catch (Exception e) {
										System.out.println("Please enter a valid value");
										System.out.println();
										continue;
									}

								} // end of while loop
									// **************************************************************
							} // end of the destination edit menu

							// Editing the passport
							// **************************************************************
							else if (editmenu == 6) {
								while (true) {
									try {

										// ************************************
										// This code doesn't do anything but for some reason it wouldn't take
										// the name without entering a dummy string before it. Have no idea why
										System.out.print("");
										String str = read.nextLine();
										// ************************************

										// Taking the passport number
										System.out.print("Please enter your Passport number: ");
										Editedticket[7] = read.nextLine();
										// save our
										// edits///////////////////////////////////////////////////////////////////
										Edit(ticket_num, Editedticket);
										System.out.print("Passport changed to " + Editedticket[7] + ", successfully!");
										break;
									} catch (Exception e) {
										System.out.println("Please enter a valid value");
										System.out.println();
										continue;
									}

								} // end of while loop
									// **************************************************************
							} // end of the destination edit menu

							// Finally exit
							else if (editmenu == 7) {
								break;
							}

							else {
								System.out.println("Please enter a valid value");
							}
						} // end of the whole flight menu
					} // end of the flight menu

				}
			}

		} // end of the while loop
	} // end of main

}
