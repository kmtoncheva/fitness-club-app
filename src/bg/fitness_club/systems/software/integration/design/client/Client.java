package bg.fitness_club.systems.software.integration.design.client;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import static bg.fitness_club.systems.software.integration.design.client.ClientConstants.*;

public class Client {

    private static final ByteBuffer buffer =
        ByteBuffer.allocateDirect(BUFFER_SIZE);

    private static void printFitnessInfo() {
        System.out.println(FITNESS_MENU);
    }

    private static void createFile(String reply, List<String> messageParts)
        throws IOException {

        if (messageParts != null) {
            if (messageParts.size() == FILE_COMMAND_ARGUMENTS_COUNT &&
                messageParts.get(PATH_ARGUMENT_INDEX).equals("--path") &&
                !messageParts.get(INPUT_PATH_INDEX).isEmpty()) {

                String pathFromInput =
                    messageParts.get(INPUT_PATH_INDEX).replaceAll("\"", "");

                Path filePath = Path.of(pathFromInput);
                File file = new File(pathFromInput);

                if (file.createNewFile()) {
                    System.out.println(FILE_CREATED_MESSAGE + pathFromInput + "!");
                } else {
                    System.out.println(FILE_OVERWRITTEN_MESSAGE);
                }

                if (Files.size(filePath) != 0) {
                    Files.writeString(
                        filePath,
                        System.lineSeparator(),
                        StandardOpenOption.CREATE,
                        StandardOpenOption.TRUNCATE_EXISTING);
                }

                Files.writeString(
                    filePath,
                    reply,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING);

            } else {
                System.out.println(FILE_COMMAND_ERROR_MESSAGE);
            }
        }
    }

    private static List<String> getCommandArguments(String input) {
        return getStrings(input, new LinkedList<>());
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
                s.setLength(0);
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

            socketChannel.connect(
                new InetSocketAddress(SERVER_HOST, SERVER_PORT));

            System.out.println(CONNECTED_MESSAGE);
            printFitnessInfo();

            while (true) {
                System.out.print(ENTER_MESSAGE_PROMPT);
                String message = scanner.nextLine();

                if (DISCONNECT_COMMAND.equals(message)) {
                    System.out.println(DISCONNECTING_MESSAGE);
                    System.out.println(GOODBYE_MESSAGE);
                    break;
                }

                List<String> messageParts = null;

                if (message.contains(FILE_COMMAND_PREFIX)) {
                    messageParts = getCommandArguments(message);
                    message = TRAINING_COMMAND_PREFIX +
                        '"' + messageParts.get(3) + '"';
                }

                buffer.clear();
                buffer.put(message.getBytes(CHARSET));
                buffer.flip();
                socketChannel.write(buffer);

                buffer.clear();
                socketChannel.read(buffer);
                buffer.flip();

                byte[] byteArray = new byte[buffer.remaining()];
                buffer.get(byteArray);
                String reply = new String(byteArray, CHARSET);

                if (messageParts == null) {
                    System.out.println(RESPONSE_PREFIX + reply + RESPONSE_SUFFIX);
                }

                createFile(reply, messageParts);
            }

        } catch (IOException e) {
            throw new RuntimeException(NETWORK_ERROR_MESSAGE, e);
        }
    }
}
