package com.reservationapp;

import com.reservationapp.user.*;
import com.reservationapp.workspace.*;
import com.reservationapp.booking.*;
import com.reservationapp.booking.timeperiod.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Map;
import java.util.Scanner;

public class ReservationApp {
    public static void main(String[] args) {
        new ReservationApp().run();
    }

    private boolean running = true;
    private User user = null;
    private final UserService userService = new UserService();
    private final WorkspaceService workspaceService = new WorkspaceService();
    private final BookingService bookingService = new BookingService();

    private void run() {
        try (Scanner scanner = new Scanner(System.in)) {
            while (running) {
                if (user == null) {
                    showStartMenu(scanner);
                } else if (user.getIsAdmin()) {
                    showAdminMenu(scanner);
                } else {
                    showUserMenu(scanner);
                }
            }
            System.out.println("Exiting.");
        }
    }

    private void showStartMenu(Scanner scanner) {
        System.out.println("\n** Menu **");
        System.out.println("1. Log in or sign up");
        System.out.println("0. Exit");

        switch (scanner.nextLine()) {
            case "0":
                exitApplication();
                break;
            case "1":
                handleLogin(scanner);
                break;
            default:
                System.out.println("Invalid input, please try again.");
        }
    }

    private void handleLogin(Scanner scanner) {
        System.out.println("Your username:");
        String username = scanner.nextLine();

        if (userService.userExists(username)) {
            user = userService.login(username);
            System.out.println("Hello, " + user.getUsername());
        } else {
            handleSignup(username, scanner);
        }
    }

    private void handleSignup(String username, Scanner scanner) {
        System.out.println("Signing up...");
        System.out.println("1. Regular user");
        System.out.println("2. Admin user");
        System.out.println("0. Back");

        switch (scanner.nextLine()) {
            case "0":
                System.out.println("Canceling creation of new user...");
                break;
            case "1":
                user = userService.signup(username, false);
                System.out.println("Welcome, " + username);
                break;
            case "2":
                user = userService.signup(username, true);
                System.out.println("Welcome, " + username);
                break;
            default:
                System.out.println("Invalid input, canceling creation of new user...");
        }
    }

    private void showUserMenu(Scanner scanner) {
        System.out.println("\n** Menu **");
        System.out.println("1. Log out");
        System.out.println("2. Make reservation");
        System.out.println("3. Show my reservations");
        System.out.println("4. Cancel my reservation");
        System.out.println("0. Exit");

        switch (scanner.nextLine()) {
            case "0":
                exitApplication();
                break;
            case "1":
                logoutUser();
                break;
            case "2":
                makeReservation(user, scanner);
                break;
            case "3":
                showUserReservations(user, scanner);
                break;
            case "4":
                deleteBooking(user, scanner);
                break;
            default:
                System.out.println("Invalid input, please try again.");
        }
    }

    private void showAdminMenu(Scanner scanner) {
        System.out.println("\n** Admin Menu **");
        System.out.println("1. Log out");
        System.out.println("2. Add a new coworking space");
        System.out.println("3. Show all coworking spaces");
        System.out.println("4. Edit a coworking space");
        System.out.println("5. Make reservation");
        System.out.println("6. Show all reservations");
        System.out.println("7. Cancel reservation");
        System.out.println("0. Exit");

        switch (scanner.nextLine()) {
            case "0":
                exitApplication();
                break;
            case "1":
                logoutUser();
                break;
            case "2":
                addWorkspace(scanner);
                break;
            case "3":
                showWorkspaces();
                break;
            case "4":
                editWorkspace(scanner);
                break;
            case "5":
                makeReservation(user, scanner);
                break;
            case "6":
                showUserReservations(user, scanner);
                break;
            case "7":
                deleteBooking(user, scanner);
                break;
            default:
                System.out.println("Invalid input, please try again.");
        }
    }
    
    private void addWorkspace(Scanner scanner) {
        System.out.println("Please, write a description for the new workspace:");
        String description = scanner.nextLine();
        Workspace workspace = workspaceService.createWorkspace(description);
        if (workspace != null) {
            System.out.println("Workspace " + workspace.getDescription() + " with ID " + workspace.getId() + " was created.");
        } else {
            System.out.println("Workspace was not created.");
        }
    }

    private void showWorkspaces() {
        Map<Integer, Workspace> workspaces = workspaceService.getAllWorkspaces();
        if (workspaces.isEmpty()) {
            System.out.println("No workspaces available.");
        } else {
            for (Map.Entry<Integer, Workspace> entry : workspaces.entrySet()) {
                System.out.println(entry.getValue().toString());
            }
        }
    }

    private void editWorkspace(Scanner scanner) {
        showWorkspaces();
        System.out.println("Enter ID of the workspace you want to edit:");

        try {
            int workspaceId = Integer.parseInt(scanner.nextLine());
            System.out.println("Write new description:");
            String newDescription = scanner.nextLine();

            if (workspaceService.editWorkspace(workspaceId, newDescription)) {
                System.out.println("Workspace updated successfully.");
            } else {
                System.out.println("Workspace with this ID doesn't exist.");
            }
        } catch (NumberFormatException ex) {
            System.out.println("Invalid input.");
        }
    }

    private Workspace chooseWorkspace(Scanner scanner) {
        Workspace workspace;
        System.out.println("Please, choose workspace for booking:");
        showWorkspaces();
        if (workspaceService.getAllWorkspaces().isEmpty()) {
            return null;
        }

        try {
            int workspaceId = Integer.parseInt(scanner.nextLine());
            workspace = workspaceService.getAllWorkspaces().get(workspaceId);
            if (workspace == null) {
                System.out.println("Workspace not found.");
                return null;
            }
        } catch (NumberFormatException ex) {
            System.out.println("Invalid input.");
            return null;
        }
        return workspace;
    }

    private TimePeriod readDate(Scanner scanner) {
        LocalDateTime startTime;
        LocalDateTime endTime;
        TimePeriod timePeriod;

        try {
            String dateTimeFormat = "dd.MM.yyyy HH:mm";
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateTimeFormat);
            System.out.println("Please, enter start time of booking (" + dateTimeFormat + "):");
            startTime = LocalDateTime.parse(scanner.nextLine(), dateTimeFormatter);
            System.out.println("Please, enter end time of booking(" + dateTimeFormat + "):");
            endTime = LocalDateTime.parse(scanner.nextLine(), dateTimeFormatter);
        } catch (DateTimeParseException ex) {
            System.out.println("Invalid input: " + ex.getMessage());
            return null;
        }

        try {
            timePeriod = new TimePeriod(startTime, endTime);
        } catch (WrongTimePeriod ex) {
            System.out.println(ex.getLocalizedMessage());
            return null;
        }

        return timePeriod;
    }

    private User chooseUser(Scanner scanner) {
        User chosenUser;
        System.out.println("Users list:");
        for (Map.Entry<String, User> entry : userService.getAllUsers().entrySet()) {
            System.out.println(entry.getValue().getUsername());
        }

        System.out.println("Please, enter username:");
        String username = scanner.nextLine();

        if (userService.userExists(username)) {
            chosenUser = userService.login(username);
            System.out.println("Chosen user: " + chosenUser.getUsername());
            return chosenUser;
        } else {
            System.out.println("User was not found");
            return null;
        }
    }

    private void makeReservation(User user, Scanner scanner) {
        User bookingUser;
        if (user.getIsAdmin()) {
            bookingUser = chooseUser(scanner);
        } else {
            bookingUser = user;
        }
        if (bookingUser == null) return;

        Workspace workspace = chooseWorkspace(scanner);
        if (workspace == null) return;
        
        TimePeriod timePeriod = readDate(scanner);
        if (timePeriod == null) return;

        Booking booking = bookingService.createBooking(bookingUser, workspace, timePeriod);
        if (booking == null) {
            Booking existingBooking = bookingService.findBookingForWorkspace(workspace, timePeriod);
            System.out.println("This workspace is already booked " + existingBooking.getTimePeriod().toString());
        } else {
            System.out.println("Booking is created:");
            System.out.println(booking.toString());
        }
    }

    private void showUserReservations(User user, Scanner scanner) {
        Map<Integer, Booking> bookings = user.getIsAdmin() ? bookingService.getAllBookings() : bookingService.getUserBookings(user);
        System.out.println("Booking list:");
        for (Map.Entry<Integer, Booking> entry : bookings.entrySet()) {
            System.out.println(entry.getValue());
        }
    }

    private void deleteBooking(User user, Scanner scanner) {
        showUserReservations(user, scanner);
        System.out.println("Please, enter id of booking you want to cancel:");
        Booking booking;

        try {
            int bookingId = Integer.parseInt(scanner.nextLine());
            booking = bookingService.getAllBookings().get(bookingId);
            if (booking == null) {
                System.out.println("Booking not found.");
                return;
            }
        } catch (NumberFormatException ex) {
            System.out.println("Invalid input.");
            return;
        }

        bookingService.deleteBooking(booking);
        System.out.println("Booking " + booking + " was successfully canceled");
    }

    private void logoutUser() {
        System.out.println("Goodbye, " + user.getUsername());
        user = null;
    }

    private void exitApplication() {
        running = false;
        System.out.println("Goodbye, enjoy your day!");
    }
}