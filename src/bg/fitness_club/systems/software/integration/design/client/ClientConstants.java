package bg.fitness_club.systems.software.integration.design.client;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public final class ClientConstants {

    private ClientConstants() {
    }

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

    // Input / Output
    public static final String ENTER_MESSAGE_PROMPT = "Enter message: ";
    public static final String CONNECTED_MESSAGE = "Connected to the server.";

    public static final String RESPONSE_PREFIX =
        "The Fitness Club's response is:\n";

    // File messages
    public static final String FILE_CREATED_MESSAGE =
        "The file created at ";

    public static final String FILE_OVERWRITTEN_MESSAGE =
        "The file was overwritten!";

    public static final String FILE_COMMAND_ERROR_MESSAGE =
        """
        Cannot create file because command is incorrect!
        The command looks like this: get file --training_name "..." --path "..."
        """;

    // Errors
    public static final String NETWORK_ERROR_MESSAGE =
        "There is a problem with the network communication";

    // UI / Menu
    public static final String FITNESS_MENU = """

            Welcome to the Fitness Club!
            Please note that for each command quotes(" ") are necessary to execute properly!
            Here is the menu:
            ------------------------------------------Trainings------------------------------------------
            Get all trainings -> get trainings --all
            Get training by name -> get training --training_name "..."
            Get trainings by type -> get trainings --type ["CARDIO", "STRENGTH", ...]
            Get trainings with certain exercise -> get trainings --exercises ["push-ups", "deadlift", ...]
            Get trainings with certain muscle groups -> get trainings --muscle_groups ["milk", "gluten", ...]

            ------------------------------------------Equipment------------------------------------------
            Get all equipment -> get equipment --all
            Get suitable equipment for training -> get equipment --training_name "..."

            ------------------------------------------Files------------------------------------------
            Get file with certain training -> get file --training_name "..." --path "..."

            ------------------------------------------Muscle Group------------------------------------------
            Get muscle groups for certain training -> get muscle_groups --training_name "..."

            ------------------------------------------Exercise------------------------------------------
            Get all exercises for training -> get exercises --training_name "..."

            ------------------------------------------Duration------------------------------------------
            Get duration for certain training -> get duration --training_name "..."

            ------------------------------------------Difficulty------------------------------------------
            Get difficulty for training -> get difficulty --training_name "..."
            ----------------------------------------------------------------------------------------

            To disconnect from the Fitness Club please enter (disconnect).
            Enjoy your training!
            """;
}

