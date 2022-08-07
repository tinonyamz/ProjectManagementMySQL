import java.util.Scanner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);

		while (true) {

			try {

				// Connect to the ebookstore database, via the jdbc:mysql: channel on localhost
				// (this PC)
				// Use username "otheruser", password "bigtino".
				Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/poisepms?useSSL=false",
						"otheruser", "bigtino");
				// Create a direct line to the database for running our queries
				Statement statement = connection.createStatement();
				ResultSet results;
				int rowsAffected;
				// Set up finished, do some stuff:

				System.out.print("\n" + "Display All Projects - '1' \n" + "Add a new project - '2' \n"
						+ "Update Project (Update Deadline or make payment or Finalise Project) - '3' \n"
						+ "Display Incomplete Projects - '4' \n" + "Display Incomplete Overdue Projects - '5' \n"
						+ "Update Contractor Contact Details - '6' \n" + "Update Customer Contact Details - '7' \n"
						+ "Exit - 'e' \n");

				String userChoice = input.nextLine();

				if (userChoice.equalsIgnoreCase("1")) {

					// database
					// executeQuery: runs a SELECT statement and returns all data in projects table
					results = statement.executeQuery("SELECT * FROM projects");
					// Loop over the results, printing them all.
					while (results.next()) {
						System.out.println("Project Number: " + results.getString("projectNo") + "\n"
								+ "Customer Name: " + results.getString("customerName") + "\n" + "Project Name: "
								+ results.getString("projectName") + "\n" + "Type Building: "
								+ results.getString("typeBuilding") + "\n" + "Physical Address: : "
								+ results.getString("physicalAddress") + "\n" + "ERF Number: "
								+ results.getString("erfNo") + "\n" + "Project Fee: " + results.getDouble("projectFee")
								+ "\n" + "Total Fee Paid: " + results.getDouble("totalFeePaid") + "\n" + "Deadline: "
								+ results.getString("deadline") + "\n" + "Contractor Name: "
								+ results.getString("contractorName") + "\n" + "Project Status: "
								+ results.getString("projectStatus") + "\n");
					}

					results.close();

				} else if (userChoice.equalsIgnoreCase("2")) {
					// project details
					String projectNo = "";
					String projectName = "";
					String deadline = "";
					String typeBuilding = "";
					String physicalAddress = "";
					String erfNo = "";
					Double projectFee = 0.0;
					Double totalPaid = 0.0;

					// customer variables
					String customerName = "";
					String customerTelephoneNo = "";
					String customerEmailAddress = "";
					String customerPhysicalAddress = "";

					// contractor variables
					String contractorName = "";
					String contractorTelephoneNo = "";
					String contractorEmailAddress = "";
					String contractorPhysicalAddress = "";

					// project information
					System.out.println("Enter Project Details");

					System.out.print("Project Number: ");
					projectNo = input.nextLine();

					System.out.print("Project Name: ");
					projectName = input.nextLine();

					System.out.print("Deadline (eg. 2023/06/24): ");
					deadline = input.nextLine();

					System.out.print("Type of Building: ");
					typeBuilding = input.nextLine();

					System.out.print("Physical Address: ");
					physicalAddress = input.nextLine();

					System.out.print("ERF Number: ");
					erfNo = input.nextLine();

					while (true) {

						try {
							System.out.print("Project Fee: ");
							projectFee = input.nextDouble();
							input.nextLine();
							break;
						} catch (Exception e) {
							System.out.println("Input was not a number! try again!");
							input.nextLine();
						}
					}

					while (true) {

						try {
							System.out.print("Total Paid: ");
							totalPaid = input.nextDouble();
							input.nextLine();
							break;
						} catch (Exception e) {
							System.out.println("Input was not a number! try again!");
							input.nextLine();
						}
					}

					// Customer Details
					System.out.println("Enter Customer Details");

					System.out.print("Name: ");
					customerName = input.nextLine();

					System.out.print("Phone Number: ");
					customerTelephoneNo = input.nextLine();

					System.out.print("Email Address: ");
					customerEmailAddress = input.nextLine();

					System.out.print("Physical Address: ");
					customerPhysicalAddress = input.nextLine();

					// Contractor Details
					System.out.println("Enter Contactor Details");

					System.out.print("Name: ");
					contractorName = input.nextLine();

					System.out.print("Phone Number: ");
					contractorTelephoneNo = input.nextLine();

					System.out.print("Email Address: ");
					contractorEmailAddress = input.nextLine();

					System.out.print("Physical Address: ");
					contractorPhysicalAddress = input.nextLine();

					String customerNameSplit[] = customerName.split(" ");

					if (projectName.length() <= 0) {
						projectName = customerNameSplit[1] + " " + typeBuilding;
					}

					// database
					// Add a new project:
					rowsAffected = statement.executeUpdate(
							"INSERT INTO projects(projectNo, customerName, projectName, typeBuilding, physicalAddress, erfNo, projectFee, totalFeePaid, deadline, contractorName, projectStatus) "
									+ "VALUES ('" + projectNo + "', '" + customerName + "', '" + projectName + "', '"
									+ typeBuilding + "', '" + physicalAddress + "', '" + erfNo + "', '" + projectFee
									+ "', '" + totalPaid + "', '" + deadline + "', '" + contractorName
									+ "', 'Incomplete')");
					System.out.println("Query complete, " + rowsAffected + " rows added.");

					// database
					// Add a new customer:
					rowsAffected = statement.executeUpdate(
							"INSERT INTO customers(customerName, customerTelephone, customerEmailAddress, customerPhysicalAddress) "
									+ "VALUES ('" + customerName + "', '" + customerTelephoneNo + "', '"
									+ customerEmailAddress + "', '" + customerPhysicalAddress + "')");
					System.out.println("Query complete, " + rowsAffected + " rows added.");

					// database
					// Add a new customer:
					rowsAffected = statement.executeUpdate(
							"INSERT INTO contractors(contractorName, contractorTelephone, contractorEmailAddress, contractorPhysicalAddress) "
									+ "VALUES ('" + contractorName + "', '" + contractorTelephoneNo + "', '"
									+ contractorEmailAddress + "', '" + contractorPhysicalAddress + "')");
					System.out.println("Query complete, " + rowsAffected + " rows added.");

					System.out.println("New Project Successfully Added!");

				} else if (userChoice.equalsIgnoreCase("3")) {

					String newDeadline = "";
					Double newTransaction = 0.0;

					// present the user with options to edit the project details
					System.out.print("Change Deadline or  - '1' \n" + "Make Additional Payment - '2' \n"
							+ "Finalise Project - '3' \n");
					String userChoiceProjectUpdate = input.nextLine();

					if (userChoiceProjectUpdate.equalsIgnoreCase("1")) {

						String userProjectSelection;
						System.out.print("Enter project number for the project you want to edit deadline: ");
						userProjectSelection = input.nextLine();

						// getting the project No
						String tempProjectNo = "";
						// executeQuery: runs a SELECT statement and returns the results.
						results = statement.executeQuery(
								"SELECT projectNo FROM projects WHERE projectNo='" + userProjectSelection + "'");
						// Set tempProjectNo to the one found in database which matches
						while (results.next()) {
							tempProjectNo = results.getString("projectNo");
						}

						results.close();

						if (tempProjectNo.equalsIgnoreCase(userProjectSelection)) {
							// getting new deadline and replacing the old with this one
							System.out.print("New Deadline (eg. 2023/06/24): ");
							newDeadline = input.nextLine();

							// Change a deadline:
							rowsAffected = statement.executeUpdate("UPDATE projects SET deadline='" + newDeadline
									+ "' WHERE projectNo='" + tempProjectNo + "'");

							System.out.println("Query complete, " + rowsAffected + " rows updated.");

						} else {
							System.out.println("Project Not Found!");
						}

					} else if (userChoiceProjectUpdate.equalsIgnoreCase("2")) {

						String userProjectSelection;
						System.out.print("Enter project number for the project you want to make payment: ");
						userProjectSelection = input.nextLine();

						// getting the project No
						String tempProjectNo = "";
						double tempTotalFeePaid = 0.0;
						// executeQuery: runs a SELECT statement and returns the results.
						results = statement
								.executeQuery("SELECT projectNo, totalFeePaid FROM projects WHERE projectNo='"
										+ userProjectSelection + "'");
						// Set tempProjectNo to the one found in database which matches
						while (results.next()) {
							tempProjectNo = results.getString("projectNo");
							tempTotalFeePaid = results.getDouble("totalFeePaid");
						}

						results.close();

						if (tempProjectNo.equalsIgnoreCase(userProjectSelection)) {
							// getting additional payment and replacing
							while (true) {

								try {
									System.out.print("Additional Fee Paid: ");
									newTransaction = input.nextDouble();
									input.nextLine();
									break;
								} catch (Exception e) {
									System.out.println("Input was not a number! try again!");
									input.nextLine();
								}
							}

							// Change a total fee paid:
							double newTotalFee = newTransaction + tempTotalFeePaid;

							rowsAffected = statement.executeUpdate("UPDATE projects SET totalFeePaid='" + newTotalFee
									+ "' WHERE projectNo='" + tempProjectNo + "'");

							System.out.println("Query complete, " + rowsAffected + " rows updated.");

						} else {
							System.out.println("Project Not Found!");
						}

					} else if (userChoiceProjectUpdate.equalsIgnoreCase("3")) {

						String userProjectSelection;
						System.out.print("Enter project number for the project you want to finalise: ");
						userProjectSelection = input.nextLine();

						// getting the project No
						String tempProjectNo = "";
						String tempCustomerName = "";
						String tempTypeBuilding = "";
						String tempProjectName = "";
						String tempPhysicalAddress = "";
						String tempErfNo = "";
						String tempDeadline = "";
						String tempContractorName = "";
						String tempProjectStatus = "";
						Double tempProjectFee = 0.0;
						Double tempTotalFeePaid = 0.0;

						// executeQuery: runs a SELECT statement and returns the results.
						results = statement
								.executeQuery("SELECT * FROM projects WHERE projectNo='" + userProjectSelection + "'");
						// Set tempProjectNo to the one found in database which matches
						while (results.next()) {
							tempProjectNo = results.getString("projectNo");
							tempCustomerName = results.getString("customerName");
							tempProjectName = results.getString("projectName");
							tempTypeBuilding = results.getString("typeBuilding");
							tempPhysicalAddress = results.getString("physicalAddress");
							tempErfNo = results.getString("erfNo");
							tempProjectFee = results.getDouble("projectFee");
							tempTotalFeePaid = results.getDouble("totalFeePaid");
							tempDeadline = results.getString("deadline");
							tempContractorName = results.getString("contractorName");
							tempProjectStatus = results.getString("projectStatus");
						}

						results.close();

						int projectComplete = 0;

						if (tempProjectNo.equalsIgnoreCase(userProjectSelection)) {

							// finalising project
							// search for name of customer in customers database and bring back all the
							// contact
							// details

							ProjectManagement currentProject = new ProjectManagement(tempProjectNo, tempCustomerName,
									tempProjectName, tempTypeBuilding, tempPhysicalAddress, tempErfNo, tempProjectFee,
									tempTotalFeePaid, tempDeadline, tempContractorName, tempProjectStatus);

							String tempCustomerName2 = "";
							String tempCustomerTelephone = "";
							String tempCustomerEmailAddress = "";

							// executeQuery: runs a SELECT statement and returns the customer details.
							results = statement.executeQuery(
									"SELECT * FROM customers WHERE customerName='" + tempCustomerName + "'");
							// Set tempProjectNo to the one found in database which matches
							while (results.next()) {
								tempCustomerName2 = results.getString("customerName");
								tempCustomerTelephone = results.getString("customerTelephone");
								tempCustomerEmailAddress = results.getString("customerEmailAddress");
							}

							results.close();

							if (tempCustomerName.equalsIgnoreCase(tempCustomerName2)) {

								projectComplete = currentProject.finaliseProject(tempCustomerName2,
										tempCustomerTelephone, tempCustomerEmailAddress);
							}

							// based on the number returned either print a receipt or mark the project as
							// complete
							if (projectComplete == 1) {

								rowsAffected = statement
										.executeUpdate("UPDATE projects SET projectStatus='Complete' WHERE projectNo='"
												+ tempProjectNo + "'");

								System.out.println("Query complete, " + rowsAffected + " rows updated.");
							}

						} else {
							System.out.println("Project Not Found!");
						}
					}

				} else if (userChoice.equalsIgnoreCase("4")) {

					// executeQuery: runs a SELECT statement and returns the results for incomplete
					// projects
					results = statement.executeQuery("SELECT * FROM projects WHERE projectStatus='Incomplete'");

					// Loop over the results, printing them all the incomplete projects.
					while (results.next()) {
						System.out.println("Project Number: " + results.getString("projectNo") + "\n"
								+ "Customer Name: " + results.getString("customerName") + "\n" + "Project Name: "
								+ results.getString("projectName") + "\n" + "Type Building: "
								+ results.getString("typeBuilding") + "\n" + "Physical Address: : "
								+ results.getString("physicalAddress") + "\n" + "ERF Number: "
								+ results.getString("erfNo") + "\n" + "Project Fee: " + results.getDouble("projectFee")
								+ "\n" + "Total Fee Paid: " + results.getDouble("totalFeePaid") + "\n" + "Deadline: "
								+ results.getString("deadline") + "\n" + "Contractor Name: "
								+ results.getString("contractorName") + "\n" + "Project Status: "
								+ results.getString("projectStatus") + "\n");
					}

					results.close();

				} else if (userChoice.equalsIgnoreCase("5")) {

					// executeQuery: runs a SELECT statement and returns the results for incomplete
					// projects
					results = statement.executeQuery(
							"SELECT * FROM projects WHERE projectStatus='Incomplete' AND deadline<CURRENT_DATE()");

					// Loop over the results, printing them all the incomplete projects.
					while (results.next()) {
						System.out.println("Project Number: " + results.getString("projectNo") + "\n"
								+ "Customer Name: " + results.getString("customerName") + "\n" + "Project Name: "
								+ results.getString("projectName") + "\n" + "Type Building: "
								+ results.getString("typeBuilding") + "\n" + "Physical Address: : "
								+ results.getString("physicalAddress") + "\n" + "ERF Number: "
								+ results.getString("erfNo") + "\n" + "Project Fee: " + results.getDouble("projectFee")
								+ "\n" + "Total Fee Paid: " + results.getDouble("totalFeePaid") + "\n" + "Deadline: "
								+ results.getString("deadline") + "\n" + "Contractor Name: "
								+ results.getString("contractorName") + "\n" + "Project Status: "
								+ results.getString("projectStatus") + "\n");
					}

					results.close();

				} else if (userChoice.equalsIgnoreCase("6")) {

					String contractorNewTelephoneNo = "";
					String contractorNewEmailAddress = "";

					String userPersonSelection;

					System.out.print("Enter name of person whose contact information you want to edit: ");
					userPersonSelection = input.nextLine();

					// getting the project No
					String tempContractorName = "";
					// executeQuery: runs a SELECT statement and returns the results.
					results = statement.executeQuery("SELECT contractorName FROM contractors WHERE contractorName='"
							+ userPersonSelection + "'");
					// Set tempProjectNo to the one found in database which matches
					while (results.next()) {
						tempContractorName = results.getString("contractorName");
					}

					results.close();

					// letting the user choose what they want to edit
					System.out.print("Change phone number only - 'p' \n" + "Change email address only - 'e' \n"
							+ "Change both - 'b' \n");
					String changeContractorDetails = input.nextLine();

					if (changeContractorDetails.equalsIgnoreCase("p")) {

						if (tempContractorName.equalsIgnoreCase(userPersonSelection)) {
							// updating details with new entry
							System.out.print("New Phone Number: ");
							contractorNewTelephoneNo = input.nextLine();

							rowsAffected = statement.executeUpdate(
									"UPDATE contractors SET contractorTelephone='" + contractorNewTelephoneNo
									+ "' WHERE contractorName='" + userPersonSelection + "'");

							System.out.println("Query complete, " + rowsAffected + " rows updated.");

						} else {
							System.out.println("Person Not Found!");
						}

					} else if (changeContractorDetails.equalsIgnoreCase("e")) {

						if (tempContractorName.equalsIgnoreCase(userPersonSelection)) {
							// updating details with new entry
							System.out.print("New Email Address: ");
							contractorNewEmailAddress = input.nextLine();

							rowsAffected = statement.executeUpdate(
									"UPDATE contractors SET contractorEmailAddress='" + contractorNewEmailAddress
									+ "' WHERE contractorName='" + userPersonSelection + "'");

							System.out.println("Query complete, " + rowsAffected + " rows updated.");

						} else {
							System.out.println("Person Not Found!");
						}

					} else if (changeContractorDetails.equalsIgnoreCase("b")) {

						if (tempContractorName.equalsIgnoreCase(userPersonSelection)) {
							// updating details with new entry
							System.out.print("New Phone Number: ");
							contractorNewTelephoneNo = input.nextLine();

							System.out.print("New Email Address: ");
							contractorNewEmailAddress = input.nextLine();

							rowsAffected = statement.executeUpdate(
									"UPDATE contractors SET contractorTelephone='" + contractorNewTelephoneNo
									+ "' WHERE contractorName='" + userPersonSelection + "'");

							System.out.println("Query complete, " + rowsAffected + " rows updated.");

							rowsAffected = statement.executeUpdate(
									"UPDATE contractors SET contractorEmailAddress='" + contractorNewEmailAddress
									+ "' WHERE contractorName='" + userPersonSelection + "'");

							System.out.println("Query complete, " + rowsAffected + " rows updated.");

						} else {
							System.out.println("Person Not Found!");
						}

					} else {
						System.out.println("Error with input!");
					}

				} else if (userChoice.equalsIgnoreCase("7")) {

					String customerNewTelephoneNo = "";
					String customerNewEmailAddress = "";

					String userPersonSelection;

					System.out.print("Enter name of person whose contact information you want to edit: ");
					userPersonSelection = input.nextLine();

					// getting the project No
					String tempCustomerName = "";
					// executeQuery: runs a SELECT statement and returns the results.
					results = statement.executeQuery(
							"SELECT customerName FROM customers WHERE customerName='" + userPersonSelection + "'");
					// Set tempProjectNo to the one found in database which matches
					while (results.next()) {
						tempCustomerName = results.getString("customerName");
					}

					results.close();

					// letting the user decide what they want to edit
					System.out.print("Change phone number only - 'p' \n" + "Change email address only - 'e' \n"
							+ "Change both - 'b' \n");
					String changeCustomerDetails = input.nextLine();

					///
					if (changeCustomerDetails.equalsIgnoreCase("p")) {

						if (tempCustomerName.equalsIgnoreCase(userPersonSelection)) {
							// updating details with new entry
							System.out.print("New Phone Number: ");
							customerNewTelephoneNo = input.nextLine();

							rowsAffected = statement.executeUpdate("UPDATE customers SET customerTelephone='"
									+ customerNewTelephoneNo + "' WHERE customerName='" + userPersonSelection + "'");

							System.out.println("Query complete, " + rowsAffected + " rows updated.");

						} else {
							System.out.println("Person Not Found!");
						}

					} else if (changeCustomerDetails.equalsIgnoreCase("e")) {

						if (tempCustomerName.equalsIgnoreCase(userPersonSelection)) {
							// updating details with new entry
							System.out.print("New Email Address: ");
							customerNewEmailAddress = input.nextLine();

							rowsAffected = statement.executeUpdate("UPDATE customers SET customerEmailAddress='"
									+ customerNewEmailAddress + "' WHERE customerName='" + userPersonSelection + "'");

							System.out.println("Query complete, " + rowsAffected + " rows updated.");

						} else {
							System.out.println("Person Not Found!");
						}

					} else if (changeCustomerDetails.equalsIgnoreCase("b")) {

						if (tempCustomerName.equalsIgnoreCase(userPersonSelection)) {
							// updating details with new entry
							System.out.print("New Phone Number: ");
							customerNewTelephoneNo = input.nextLine();

							System.out.print("New Email Address: ");
							customerNewEmailAddress = input.nextLine();

							rowsAffected = statement.executeUpdate("UPDATE customers SET customerTelephone='"
									+ customerNewTelephoneNo + "' WHERE customerName='" + userPersonSelection + "'");

							System.out.println("Query complete, " + rowsAffected + " rows updated.");

							rowsAffected = statement.executeUpdate("UPDATE customers SET customerEmailAddress='"
									+ customerNewEmailAddress + "' WHERE customerName='" + userPersonSelection + "'");

							System.out.println("Query complete, " + rowsAffected + " rows updated.");

						} else {
							System.out.println("Person Not Found!");
						}

					} else {
						System.out.println("Error with input!");
					}

				} else if (userChoice.equalsIgnoreCase("e")) {
					System.out.println("Goodbye!!!");
					System.exit(0);
				} else {
					System.out.println("Error with input!");
				}

			} catch (SQLException e) {
				// We only want to catch a SQLException - anything else is off-limits for now.
				e.printStackTrace();
			}
		}
	}

}
