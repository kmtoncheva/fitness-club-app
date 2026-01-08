package bg.fitness_club.systems.software.integration.design.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import bg.fitness_club.systems.software.integration.design.command.CommandExecutor;
import bg.fitness_club.systems.software.integration.design.fitness_club.FitnessClub;
import bg.fitness_club.systems.software.integration.design.fitness_club.FitnessClubAPI;
import bg.fitness_club.systems.software.integration.design.logger.ErrorLogger;
import bg.fitness_club.systems.software.integration.design.storage.DatabaseConnection;
import bg.fitness_club.systems.software.integration.design.storage.Queries;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class AdminPanel {
    public static void main(String[] args) throws InterruptedException, IOException {
        final int serverPortNumber = 7777;

        File file = new File("src/bg/fitness_club/systems/software/integration/design/logger/logs.txt");
        if (file.createNewFile()) {
            System.out.println("File created: " + file.getName());
        }

        ErrorLogger errorLogger =
            new ErrorLogger("src/bg/fitness_club/systems/software/integration/design/logger/logs.txt");

        DatabaseConnection databaseConnection = new DatabaseConnection(
            "jdbc:mysql://localhost:3306/fitness_club", "root", "");

        Queries queries = new Queries(databaseConnection);

        Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

        FitnessClubAPI fitnessClub = new FitnessClub(queries, gson);

        Server server = new Server(serverPortNumber, new CommandExecutor(fitnessClub, errorLogger));

        Scanner adminInput = new Scanner(System.in);
        String adminCommand;

        System.out.println("\nHello admin, to start the server please enter (start) or to stop (stop) commands.");
        System.out.print("Please input command: ");
        while (true) {
            adminCommand = adminInput.nextLine();
            adminCommand = adminCommand.strip();

            if (adminCommand.equals("start")) {
                System.out.println("Starting server...");
                System.out.println("Listening for client requests");
                server.start();
            }
            if (adminCommand.equals("stop")) {
                if (server.isAlive()) {
                    System.out.println("Stopping server...");
                    server.shutdown();
                }
                break;
            }
        }

        adminInput.close();
        server.join();
    }
}