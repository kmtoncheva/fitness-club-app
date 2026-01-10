package bg.fitness_club.systems.software.integration.design.server;

public final class ServerConstants {

    private ServerConstants() {
    }

    // ANSI Color Codes for terminal styling
    public static final String RESET = "\u001B[0m";
    public static final String BOLD = "\u001B[1m";
    public static final String GREEN = "\u001B[32m";
    public static final String BLUE = "\u001B[34m";
    public static final String CYAN = "\u001B[36m";
    public static final String YELLOW = "\u001B[33m";
    public static final String MAGENTA = "\u001B[35m";
    public static final String RED = "\u001B[31m";

    // Server
    public static final int SERVER_PORT = 8080;
    public static final int BUFFER_SIZE = 16384;
    public static final String HOST = "localhost";

    // Logger
    public static final String LOG_FILE_PATH =
        "logs/logs.txt";

    public static final String FILE_CREATED_MESSAGE = GREEN + "✅ Log file created: " + RESET;

    // Database
    public static final String DB_URL =
        "jdbc:mysql://localhost:3306/fitness_club";

    public static final String DB_USERNAME = "root";
    public static final String DB_PASSWORD = "";

    // Messages with colors and emojis
    public static final String ADMIN_GREETING = BOLD + CYAN + """
        
        ╔════════════════════════════════════════════════════════════════════════════╗
        ║                   🖥️  FITNESS CLUB SERVER  🖥️                             ║
        ║                    Server Management Console                               ║
        ╚════════════════════════════════════════════════════════════════════════════╝
        """ + RESET + "\n\n" + YELLOW + "📋 Available Commands:" + RESET + "\n" +
        "  🟢 " + GREEN + "start" + RESET + " - Start the Fitness Club server\n" +
        "  🔴 " + RED + "stop" + RESET + "  - Stop the server and exit\n\n" +
        CYAN + "💡 Tip: Server will listen on port " + SERVER_PORT + " after starting\n" + RESET;

    public static final String ADMIN_PROMPT = BOLD + MAGENTA + "⚙️  Admin Command" + RESET + " ➤ ";

    public static final String START_COMMAND = "start";
    public static final String STOP_COMMAND = "stop";

    public static final String SERVER_STARTING_MESSAGE = GREEN + "\n🚀 Starting Fitness Club Server..." + RESET;
    public static final String SERVER_LISTENING_MESSAGE = BOLD + CYAN + "👂 Server is now listening for client connections on port " + SERVER_PORT + "!\n" + RESET;
    public static final String SERVER_STOPPING_MESSAGE = YELLOW + "\n🛑 Stopping server..." + RESET;
    public static final String SERVER_STOPPED_MESSAGE = GREEN + "✅ Server stopped successfully. Goodbye! 👋\n" + RESET;
}
