import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class CommandLineChat {

    public static void main(String[] args) {
            if (args.length == 1 && args[0].equals("server")) {
                runServer();
            } else if (args.length == 2 && args[0].equals("client")) {
                runClient(args[1]);
            } else {
                System.out.println("Usage: java CommandLineChatApp <server|client> [hostname]");
        }
    }

    private static void runServer() {
        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            System.out.println("Server started. Waiting for clients...");

            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected.");

            BufferedReader clientReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter clientWriter = new PrintWriter(clientSocket.getOutputStream(), true);

            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

            Thread clientReaderThread = new Thread(() -> {
                try {
                    String message;
                    while ((message = clientReader.readLine()) != null) {
                        System.out.println("Client: " + message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            clientReaderThread.start();

            String message;
            while ((message = consoleReader.readLine()) != null) {
                clientWriter.println(message);
            }

            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void runClient(String hostname) {
        try {
            Socket socket = new Socket(hostname, 8080);
            System.out.println("Connected to the server.");

            BufferedReader serverReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter serverWriter = new PrintWriter(socket.getOutputStream(), true);

            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

            Thread serverReaderThread = new Thread(() -> {
                try {
                    String message;
                    while ((message = serverReader.readLine()) != null) {
                        System.out.println("Server: " + message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            serverReaderThread.start();

            String message;
            while ((message = consoleReader.readLine()) != null) {
                serverWriter.println(message);
            }

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
