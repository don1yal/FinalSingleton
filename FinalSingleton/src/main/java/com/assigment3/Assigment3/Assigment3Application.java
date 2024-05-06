package com.assigment3.Assigment3;

import com.assigment3.Assigment3.DAO.EmployeeDAO;
import com.assigment3.Assigment3.DAO.EmployeeDAOImpl;
import com.assigment3.Assigment3.Entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

@SpringBootApplication
@EnableAsync
public class Assigment3Application {

	Scanner scanner  = new Scanner(System.in);
	@Autowired
	EmployeeDAO DAOManager;
	public static void main(String[] args) {
		SpringApplication.run(Assigment3Application.class, args);
	}
	@Bean
	public CommandLineRunner running(EmployeeDAO employeeDAO) {
		DAOManager = employeeDAO;
		return runner -> {
			startServers();
		};
	}

	public void startServers() {
		CountDownLatch latch = new CountDownLatch(5);

		Thread serverThread1 = createServerThread(1, "john_doe", "pass123", "my_database", latch);
		Thread serverThread2 = createServerThread(2, "jane_doe", "secret", "another_db", latch);
		Thread serverThread3 = createServerThread(3, "admin", "admin123", "admin_db", latch);
		Thread serverThread4 = createServerThread(4, "user1", "123456", "user_db", latch);
		Thread serverThread5 = createServerThread(5, "user2", "abcdef", "user_db", latch);

		serverThread1.start();
		serverThread2.start();
		serverThread3.start();
		serverThread4.start();
		serverThread5.start();

		try {
			latch.await();
			System.out.println("All servers started successfully.");
			System.out.println("Server Connected with this user: " + ConnectionDatabase.getServerInfo());
			sqlQueries();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public Thread createServerThread(int id, String username, String password, String database, CountDownLatch latch) {
		return new Thread(() -> {
			ConnectionDatabase server = ConnectionDatabase.getInstance(id, username, password, database);
			System.out.println("Server " + id + " started.");
			latch.countDown();
		});
	}
	public void sqlQueries() {
		System.out.println("Find Employee");

		boolean exit = false;
		while(!exit) {
			display();
			System.out.println("Enter a number: ");
			int num = scanner.nextInt();

			switch (num) {
				case 1:
					getUserById();
					break;
				case 2:
					getUserByFirstName();
					break;
				case 3:
					break;
				case 4:
					break;
				case 5 :
					exit = true;
					break;
				default:
					System.out.println("Invalid choise");
			}
		}
	}
	public void display() {
		System.out.println("Select Queries");
		System.out.println("Find by ID number --> 1");
		System.out.println("Find by Firstname --> 2");
		System.out.println("Update --> 3");
		System.out.println("Delete by Email -->  4");
	}
	public void getUserById() {
		System.out.println("Enter the id of user: ");
		int Id = scanner.nextInt();
		scanner.nextLine();
		Employee theEmployee = DAOManager.findById(Id);
		System.out.println(theEmployee.toString());
	}
	public void getUserByFirstName() {
		System.out.println("Enter the firstname of user: ");
		scanner.nextLine();
		String firstName = scanner.nextLine();
		List<Employee> employees = DAOManager.findByFirstName(firstName);
		for (Employee employee: employees) {
			System.out.println(employee.toString());
		}
	}
	public void updateEmployee() {
		System.out.println("Enter the ID of the employee to update: ");
		int id = scanner.nextInt();
		scanner.nextLine();

		Employee existingEmployee = DAOManager.findById(id);
		if (existingEmployee == null) {
			System.out.println("Employee with ID " + id + " does not exist.");
			return;
		}

		System.out.println("Enter the new first name: ");
		String newFirstName = scanner.nextLine();

		System.out.println("Enter the new last name: ");
		String newLastName = scanner.nextLine();

		System.out.println("Enter the new email: ");
		String newEmail = scanner.nextLine();

		// Update the employee details
		existingEmployee.setFirstName(newFirstName);
		existingEmployee.setLastName(newLastName);
		existingEmployee.setEmail(newEmail);

		DAOManager.update(existingEmployee);
		System.out.println("Employee with ID " + id + " has been updated successfully.");
	}

	public void deleteEmployee() {
		System.out.println("Enter the ID of the employee to delete: ");
		int id = scanner.nextInt();
		scanner.nextLine();

		Employee existingEmployee = DAOManager.findById(id);
		if (existingEmployee == null) {
			System.out.println("Employee with ID " + id + " does not exist.");
			return;
		}
		DAOManager.delete(existingEmployee.getId());
		System.out.println("Employee with ID " + id + " has been deleted successfully.");
	}

}