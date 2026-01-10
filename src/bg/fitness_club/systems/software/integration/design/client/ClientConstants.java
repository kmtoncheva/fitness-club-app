package bg.fitness_club.systems.software.integration.design.client;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public final class ClientConstants {

    private ClientConstants() {
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

    // Server connection
    public static final String SERVER_HOST = "localhost";
    public static final int SERVER_PORT = 8080;

    // Buffer
    public static final int BUFFER_SIZE = 16_384;
    public static final Charset CHARSET = StandardCharsets.UTF_8;

    // File command parsing
    public static final int FILE_COMMAND_ARGUMENTS_COUNT = 6;
    public static final int PATH_ARGUMENT_INDEX = 4;
    public static final int INPUT_PATH_INDEX = 5;

    // Commands
    public static final String DISCONNECT_COMMAND = "disconnect";
    public static final String FILE_COMMAND_PREFIX = "get file --training_name";
    public static final String TRAINING_COMMAND_PREFIX =
        "get training --training_name ";

    // Input / Output with enhanced UI
    public static final String ENTER_MESSAGE_PROMPT = CYAN + "💬 Enter command" + RESET + " ➤ ";
    public static final String CONNECTED_MESSAGE = GREEN + "✅ Connected to Fitness Club Server!" + RESET;

    public static final String RESPONSE_PREFIX = BOLD + BLUE + "\n📨 Server Response:\n" + RESET + 
                                                  CYAN + "─".repeat(80) + "\n" + RESET;
    public static final String RESPONSE_SUFFIX = CYAN + "\n" + "─".repeat(80) + "\n" + RESET;

    // File messages with emojis
    public static final String FILE_CREATED_MESSAGE =
        GREEN + "✅ File successfully created at: " + RESET;

    public static final String FILE_OVERWRITTEN_MESSAGE =
        YELLOW + "⚠️  File was overwritten!" + RESET;

    public static final String FILE_COMMAND_ERROR_MESSAGE =
        RED + """
        ❌ Cannot create file - incorrect command format!
        💡 Correct format: get file --training_name "..." --path "..."
        """ + RESET;

    // Errors with emojis
    public static final String NETWORK_ERROR_MESSAGE =
        RED + "🔌 Network communication error!" + RESET;
    public static final String DISCONNECTING_MESSAGE =
        YELLOW + "\n👋 Disconnecting from Fitness Club..." + RESET;
    public static final String GOODBYE_MESSAGE =
        GREEN + "🎉 Thanks for using Fitness Club! Stay healthy! 💪\n" + RESET;

    // UI / Menu with colors and emojis
    public static final String FITNESS_MENU = BOLD + MAGENTA + """
        
        ╔════════════════════════════════════════════════════════════════════════════╗
        ║                    💪 WELCOME TO FITNESS CLUB 2.0 💪                       ║
        ║                     Your Personal Training Assistant                       ║
        ╚════════════════════════════════════════════════════════════════════════════╝
        """ + RESET + CYAN + """
        
        📝 NOTE: Use quotes (" ") around values for proper command execution!
        """ + RESET + """
        
        """ + BOLD + GREEN + """
        ┌─────────────────────────────────────────────────────────────────────────┐
        │ 🏋️  TRAINING PROGRAMS                                                    │
        └─────────────────────────────────────────────────────────────────────────┘
        """ + RESET + """
          📋 View all trainings          → get trainings --all
          🎯 Find training by name       → get training --training_name "..."
          🏃 Filter by type              → get trainings --type ["CARDIO", "STRENGTH", ...]
          💪 Filter by exercises         → get trainings --exercises ["push-ups", "deadlift", ...]
          🎯 Filter by muscle groups     → get trainings --muscle_groups ["chest", "legs", ...]
        
        """ + BOLD + BLUE + """
        ┌─────────────────────────────────────────────────────────────────────────┐
        │ 🏋️‍♀️ EQUIPMENT & GEAR                                                      │
        └─────────────────────────────────────────────────────────────────────────┘
        """ + RESET + """
          📦 View all equipment          → get equipment --all
          🔧 Equipment for training      → get equipment --training_name "..."
        
        """ + BOLD + YELLOW + """
        ┌─────────────────────────────────────────────────────────────────────────┐
        │ 💾 FILE OPERATIONS                                                       │
        └─────────────────────────────────────────────────────────────────────────┘
        """ + RESET + """
          📄 Export training to file     → get file --training_name "..." --path "..."
        
        """ + BOLD + MAGENTA + """
        ┌─────────────────────────────────────────────────────────────────────────┐
        │ 🎯 MUSCLE GROUPS                                                         │
        └─────────────────────────────────────────────────────────────────────────┘
        """ + RESET + """
          💪 Muscles for training        → get muscle_groups --training_name "..."
        
        """ + BOLD + CYAN + """
        ┌─────────────────────────────────────────────────────────────────────────┐
        │ 🏃‍♂️ EXERCISES                                                             │
        └─────────────────────────────────────────────────────────────────────────┘
        """ + RESET + """
          🤸 Exercises in training       → get exercises --training_name "..."
        
        """ + BOLD + GREEN + """
        ┌─────────────────────────────────────────────────────────────────────────┐
        │ ⏱️  TRAINING DETAILS                                                     │
        └─────────────────────────────────────────────────────────────────────────┘
        """ + RESET + """
          ⏰ Get training duration       → get duration --training_name "..."
          📊 Get difficulty level        → get difficulty --training_name "..."
        
        """ + BOLD + MAGENTA + """
        ╔════════════════════════════════════════════════════════════════════════════╗
        ║  🚪 Type 'disconnect' to exit  |  💡 Tip: Start with 'get trainings --all' ║
        ╚════════════════════════════════════════════════════════════════════════════╝
        """ + RESET + GREEN + """
        
        🎉 Ready to start your fitness journey!
        """ + RESET;
}

