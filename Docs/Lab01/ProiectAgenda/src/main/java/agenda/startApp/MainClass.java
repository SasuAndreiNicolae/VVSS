package agenda.startApp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import agenda.controller.ActivityController;
import agenda.controller.ContactController;
import agenda.controller.UserController;
import agenda.exceptions.InvalidFormatException;

import agenda.model.Utility.ActivityUtility;
import agenda.model.base.Activity;
import agenda.model.base.Contact;
import agenda.model.base.User;
import agenda.repository.classes.RepositoryActivityFile;
import agenda.repository.classes.RepositoryContactFile;
import agenda.repository.classes.RepositoryUserFile;
import agenda.repository.interfaces.RepositoryActivity;
import agenda.repository.interfaces.RepositoryContact;
import agenda.repository.interfaces.RepositoryUser;

//functionalitati
//F01.	 adaugarea de contacte (nume, adresa, numar de telefon, adresa email);
//F02.	 programarea unor activitati (denumire, descriere, data, locul, ora inceput, durata, contacte).
//F03.	 generarea unui raport cu activitatile pe care le are utilizatorul (nume, user, parola) la o anumita data, ordonate dupa ora de inceput.

public class MainClass {


	public static void main(String[] args) {
		RepositoryContact contactRep ;
		RepositoryUser userRep ;
		RepositoryActivity activityRep ;

		ContactController contactController=null;
		UserController userController = null;
		ActivityController activityController=null ;

		BufferedReader in ;
		try {
			 contactRep = new RepositoryContactFile();
			 userRep = new RepositoryUserFile();
			 activityRep = new RepositoryActivityFile(
					contactRep);
			 contactController = new ContactController(contactRep);
			 userController = new UserController(userRep);
			 activityController = new ActivityController(activityRep);

			User user = null;
			in = new BufferedReader(new InputStreamReader(System.in));
			while (user == null) {
				System.out.print("Enter username: ");
				String u = in.readLine();
				System.out.print("Enter password: ");
				String p = in.readLine();

				user = userRep.getByUsername(u);
				if (user!=null&&!user.isPassword(p))
					user = null;
			}

			int chosen = 0;
			while (chosen != 4) {
				printMenu();
				chosen = Integer.parseInt(in.readLine());
				try {
					switch (chosen) {
					case 1:
						adaugContact(contactController, in);
						break;
					case 2:
						adaugActivitate(activityController, contactRep, in, user);
						break;
					case 3:
						afisActivitate(activityController, in, user);
						break;
					}
				} catch (Exception e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
			}
			// List<Activity> act =
			// activityRep.activitiesByName(user.getName());
			// for(Activity a : act)
			// System.out.println(a.toString());

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		finally {
			if(contactController!=null)
				contactController.saveContracts();
			if(activityController!=null)
				activityController.saveActivities();
			if(userController!=null)
				userController.save();
		}
		System.out.println("Program over and out\n");
	}

	private static void afisActivitate(ActivityController activityController,
			BufferedReader in, User user) {
		try {
			activityController.getActivities().forEach((x)-> System.out.println(ActivityUtility.toReadableString(x)));
			System.out.print("Afisare Activitate: \n");
			System.out.print("Data(format: mm/dd/yyyy): ");
			String dateS = in.readLine();
			Calendar c = Calendar.getInstance();
			c.set(Integer.parseInt(dateS.split("/")[2]),
					Integer.parseInt(dateS.split("/")[0]) - 1,
					Integer.parseInt(dateS.split("/")[1]));
			Date d = c.getTime();

			System.out.println("Activitatile din ziua " + d.toString() + ": ");

			List<Activity> act = activityController
					.activitiesOnDate(user.getName(), d);
			for (Activity a : act) {
				System.out.println(ActivityUtility.toReadableString(a));
				//System.out.printf("%s - %s: %s - %s with: ", a.getStart()
				//		.toString(), a.getDuration().toString(), a
				//		.getDescription());
				for (Contact con : a.getContacts())
					System.out.printf("%s, ", con.getName());
				System.out.println();

			}
		} catch (IOException e) {
			System.out.print("Eroare de citire: %s\n" + e.getMessage());
		}
	}

	private static void adaugActivitate(ActivityController activityController,
			RepositoryContact contactRep, BufferedReader in, User user) {
		try {
			System.out.print("Adauga Activitate: \n");
			System.out.print("Descriere: ");
			String description = in.readLine();
			System.out.print("Start Date(format: mm/dd/yyyy): ");
			String dateS = in.readLine();
			System.out.print("Start Time(hh:mm): ");
			String timeS = in.readLine();
			Calendar c = Calendar.getInstance();
			c.set(Integer.parseInt(dateS.split("/")[2]),
					Integer.parseInt(dateS.split("/")[0]) - 1,
					Integer.parseInt(dateS.split("/")[1]),
					Integer.parseInt(timeS.split(":")[0]),
					Integer.parseInt(timeS.split(":")[1]));
			Date start = c.getTime();

			System.out.print("End Date(format: mm/dd/yyyy): ");
			String dateE = in.readLine();
			System.out.print("End Time(hh:mm): ");
			String timeE = in.readLine();
			
			c.set(Integer.parseInt(dateE.split("/")[2]),
					Integer.parseInt(dateE.split("/")[0]) - 1,
					Integer.parseInt(dateE.split("/")[1]),
					Integer.parseInt(timeE.split(":")[0]),
					Integer.parseInt(timeE.split(":")[1]));
			Date end = c.getTime();
			System.out.print("Nr de contacte asociate activitatii:");
			int n = Integer.parseInt(in.readLine());
			contactRep.getContacts().forEach(System.out::println);
			List<Contact> contacts = new LinkedList<>();
			for(int i=0;i<n;i++) {
				System.out.print("Nume contact");
				Contact con = null;
				while (con == null) {
					String name = in.readLine();
					con = contactRep.getByName(name);
					if (con != null)
						contacts.add(con);
				}
			}

			Activity act = new Activity(user.getName(), start, end,
					contacts, description);

			//activityRep.addActivity(act);
			activityController.addActivity(act);
			System.out.print("S-a adugat cu succes\n");
		} catch (IOException|InvalidFormatException e) {
			System.out.print("Eroare de citire: %s\n" + e.getMessage());
		}

	}

	private static void adaugContact(ContactController contactController,
			BufferedReader in) {

		try {
			System.out.print("Adauga Contact: \n");
			System.out.print("Nume: ");
			String name = in.readLine();
			System.out.print("Adresa: ");
			String adress = in.readLine();
			System.out.print("Numar de telefon: ");
			String telefon = in.readLine();
			
			Contact c = new Contact(name, adress, telefon);

			contactController.addContact(c);

			System.out.print("S-a adugat cu succes\n");
		} catch (IOException e) {
			System.out.print("Eroare de citire: %s\n" + e.getMessage());
		} catch (InvalidFormatException e) {
			if (e.getCause() != null)
				System.out.printf("Eroare: %s - %s\n" + e.getMessage(), e
						.getCause().getMessage());
			else
				System.out.print("Eroare: %s\n" + e.getMessage());
		}

	}

	private static void printMenu() {
		System.out.print("Please choose option:\n");
		System.out.print("1. Adauga contact\n");
		System.out.print("2. Adauga activitate\n");
		System.out.print("3. Afisare activitati din data de...\n");
		System.out.print("4. Exit\n");
		System.out.print("Alege: ");
	}
}