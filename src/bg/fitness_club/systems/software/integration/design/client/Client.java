package bg.fitness_club.systems.software.integration.design.client;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Client {
    private static final int SERVER_PORT = 7777;
    private static final String SERVER_HOST = "localhost";
    private static final int BUFFER_SIZE = 16384;
    private static final ByteBuffer buffer = ByteBuffer.allocateDirect(BUFFER_SIZE);
    private static final int FILE_COMMAND_ARGUMENTS_COUNT = 6;
    private static final int PATH_ARGUMENT_INDEX = 4;
    private static final int INPUT_PATH_INDEX = 5;
    private static final String WHITESPACE_REGEX = "\\s+";
    private static final String SPACE_REGEX = " ";

    private static void printRestaurantInfo() {
        System.out.println("""

            Welcome to the Fitness Club!
            Please note that for each command quotes(" ") are necessary to execute properly!
            Here is the menu:""");
        System.out.println("------------------------------------------Trainings------------------------------------------");
        System.out.println("Get all trainings -> get trainings --all");
        System.out.println("Get training by name -> get trainings --training_name \"...\"");
        System.out.println("Get trainings by type -> get trainings --type [\"CARDIO\", \"STRENGTH\", ...]\"");
        System.out.println(
            "Get trainings with certain exercise -> get trainings --exercises [\"push-ups\", \"deadlift\", ...]");
        System.out.println(
            "Get trainings with certain muscle groups -> get trainings --muscle_groups [\"milk\", \"gluten\", ...]\n");
        System.out.println("------------------------------------------Equipment------------------------------------------");
        System.out.println("Get all equipment -> get equipment --all");
        System.out.println("Get suitable equipment for training -> get equipment --training_name \"...\"\n");
        System.out.println("------------------------------------------Files------------------------------------------");
        System.out.println("Get file with certain training -> get file --training_name \"...\" --path \"...\"\n");
        System.out.println("------------------------------------------Muscle Group------------------------------------------");
        System.out.println("Get muscle groups for certain training -> get muscle_groups --training_name \"...\"\n");
        System.out.println("------------------------------------------Exercise------------------------------------------");
        System.out.println("Get all exercises for training -> get exercises --training_name \"...\"\n");
        System.out.println("------------------------------------------Duration------------------------------------------");
        System.out.println("Get duration for certain training -> get duration --training_name \"...\"\n");
        System.out.println("------------------------------------------Difficulty------------------------------------------");
        System.out.println("Get difficulty for training -> get difficulty --training_name \"...\"");
        System.out.println(
            "----------------------------------------------------------------------------------------\n");
        System.out.println("To disconnect from the Fitness Club please enter (disconnect).");
        System.out.println("Enjoy your training!\n");
    }

    private static void createFile(String reply, List<String> messageParts) throws IOException {
        if (messageParts != null) {
            if (messageParts.size() == FILE_COMMAND_ARGUMENTS_COUNT &&
                messageParts.get(PATH_ARGUMENT_INDEX).equals("--path") &&
                !messageParts.get(INPUT_PATH_INDEX).isEmpty()) {

                String pathFromInput = messageParts.get(INPUT_PATH_INDEX).replaceAll("\"", "");
                Path filePath = Path.of(pathFromInput);
                File file = new File(pathFromInput);

                if (file.createNewFile()) {
                    System.out.println("The file created at " + pathFromInput + "!");
                } else {
                    System.out.println("The file was overwritten!");
                }

                if (Files.size(filePath) != 0) {
                    Files.writeString(filePath, System.lineSeparator(), StandardOpenOption.CREATE,
                        StandardOpenOption.TRUNCATE_EXISTING);
                }
                Files.writeString(filePath, reply, StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING);
            } else {
                System.out.println("""
                    Cannot create file because command is incorrect!
                    The command looks like this: get file --recipe_name "..." --path "..."
                    """);
            }
        }
    }

    private static List<String> getCommandArguments(String input) {
        List<String> tokens = new LinkedList<>();
        return getStrings(input, tokens);
    }

    public static List<String> getStrings(String input, List<String> tokens) {
        StringBuilder s = new StringBuilder();

        boolean insideQuote = false;

        for (char c : input.toCharArray()) {
            if (c == '"') {
                insideQuote = !insideQuote;
            }
            if (c == ' ' && !insideQuote) {
                tokens.add(s.toString().replace("\"", ""));
                s.delete(0, s.length());
            } else {
                s.append(c);
            }
        }
        tokens.add(s.toString().replace("\"", ""));

        return tokens;
    }

    public static void main(String[] args) {

        try (SocketChannel socketChannel = SocketChannel.open();
             Scanner scanner = new Scanner(System.in)) {

            socketChannel.connect(new InetSocketAddress(SERVER_HOST, SERVER_PORT));

            System.out.println("Connected to the server.");
            printRestaurantInfo();

            while (true) {
                System.out.print("Enter message: ");
                String message = scanner.nextLine();

                if ("disconnect".equals(message)) {
                    break;
                }

                List<String> messageParts = null;
                if (message.contains("get file --training_name")) {
                    messageParts = getStrings(message, new LinkedList<>());

                    message = "get training --training_name " + '"' + messageParts.get(3) + '"';
                }
                //System.out.println("Sending message {" + message + "} to the server...");

                buffer.clear();
                buffer.put(message.getBytes());
                buffer.flip();
                socketChannel.write(buffer);

                buffer.clear();
                socketChannel.read(buffer);
                buffer.flip();

                byte[] byteArray = new byte[buffer.remaining()];
                buffer.get(byteArray);
                String reply = new String(byteArray, StandardCharsets.UTF_8);

                //String reply = new String(buffer.array(), 0, buffer.position(), "UTF-8"); // buffer drain

                if (messageParts == null) {
                    System.out.println("The Fitness Club's response is:\n" + reply + "\n");
                }
                createFile(reply, messageParts);
            }

        } catch (IOException e) {
            throw new RuntimeException("There is a problem with the network communication", e);
        }
    }
}