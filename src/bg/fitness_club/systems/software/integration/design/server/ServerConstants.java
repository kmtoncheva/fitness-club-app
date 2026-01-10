package bg.fitness_club.systems.software.integration.design.server;

public final class ServerConstants {

    private ServerConstants() {
    }

    // Server
    public static final int SERVER_PORT = 8080;
    public static final int BUFFER_SIZE = 16384;
    public static final String HOST = "localhost";

    // Logger
    public static final String LOG_FILE_PATH =
        "logs/logs.txt";

    public static final String FILE_CREATED_MESSAGE = "File created: ";

    // Database
    public static final String DB_URL =
        "jdbc:mysql://localhost:3306/fitness_club";

    public static final String DB_USERNAME = "root";
    public static final String DB_PASSWORD = "";

    // Messages
    public static final String ADMIN_GREETING =
        "\nHello admin, to start the server please enter (start) or to stop (stop) commands.";

    public static final String ADMIN_PROMPT = "Please input command: ";

    public static final String START_COMMAND = "start";
    public static final String STOP_COMMAND = "stop";

    public static final String SERVER_STARTING_MESSAGE = "Starting server...";
    public static final String SERVER_LISTENING_MESSAGE = "Listening for client requests";
    public static final String SERVER_STOPPING_MESSAGE = "Stopping server...";
}
