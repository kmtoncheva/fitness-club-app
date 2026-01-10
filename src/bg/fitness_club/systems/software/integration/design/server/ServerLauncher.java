package bg.fitness_club.systems.software.integration.design.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import bg.fitness_club.systems.software.integration.design.commands.CommandExecutor;
import bg.fitness_club.systems.software.integration.design.fitness_club.FitnessClub;
import bg.fitness_club.systems.software.integration.design.fitness_club.FitnessClubAPI;
import bg.fitness_club.systems.software.integration.design.logger.ErrorLogger;
import bg.fitness_club.systems.software.integration.design.database.DatabaseConnection;
import bg.fitness_club.systems.software.integration.design.database.Queries;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import static bg.fitness_club.systems.software.integration.design.server.ServerConstants.*;

public class ServerLauncher {

    public static void main(String[] args) throws InterruptedException, IOException {

        File file = new File(LOG_FILE_PATH);
        if (file.createNewFile()) {
            System.out.println(FILE_CREATED_MESSAGE + file.getName());
        }

        ErrorLogger errorLogger = new ErrorLogger(LOG_FILE_PATH);

        DatabaseConnection databaseConnection =
            new DatabaseConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

        Queries queries = new Queries(databaseConnection);

        Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

        FitnessClubAPI fitnessClub = new FitnessClub(queries, gson);

        Server server =
            new Server(SERVER_PORT, new CommandExecutor(fitnessClub, errorLogger));

        Scanner adminInput = new Scanner(System.in);

        System.out.println(ADMIN_GREETING);
        System.out.print(ADMIN_PROMPT);

        while (true) {
            String adminCommand = adminInput.nextLine().strip();

            if (adminCommand.equals(START_COMMAND)) {
                System.out.println(SERVER_STARTING_MESSAGE);
                System.out.println(SERVER_LISTENING_MESSAGE);
                server.start();
            }

            if (adminCommand.equals(STOP_COMMAND)) {
                if (server.isAlive()) {
                    System.out.println(SERVER_STOPPING_MESSAGE);
                    server.shutdown();
                }
                System.out.println(SERVER_STOPPED_MESSAGE);
                break;
            }
        }

        adminInput.close();
        server.join();
    }
}