import java.io.*;
import java.net.*;

public class Client {
    private static final String SERVER_IP = "127.0.0.1"; // Server's IP address
    private static final int PORT = 3351; // Server's port number

    public static void main(String[] args) {
        try {
            // Connect to the server
            Socket socket = new Socket(SERVER_IP, PORT);
            System.out.println("Connected to server.");

            // Creating input and output streams for communication with server
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter outToServer = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

            // Receive initial message from server
            System.out.println(inFromServer.readLine());

            // Main game loop
            while (true) {
                // Receive and display server's move
                System.out.println("Waiting for server's move...");
                String serverResponse = inFromServer.readLine(); //this is going to be the board

                // Check for game over message from server
                if (serverResponse.contains("Server wins!") || serverResponse.contains("Client (Player 2) wins!")) {
                    System.out.println(serverResponse);
                    break;
                }

                System.out.println(serverResponse);
                String line;
                while (!(line = inFromServer.readLine()).equals("END_OF_BOARD_STATE")) {
                    System.out.println(line);
                }
                

                // Check for game over message from server
                if (serverResponse.contains("Server wins!") || serverResponse.contains("Client (Player 2) wins!")) {
                    System.out.println(serverResponse);
                    break;
                }

                // Player's turn (client)
                System.out.println("Your turn. Enter position (0-8): ");
                int clientMove = Integer.parseInt(userInput.readLine());
                outToServer.println(clientMove);

                //Check for game over message from server
                String clientResponse = inFromServer.readLine();
                if (clientResponse.contains("Server wins!") || serverResponse.contains("Client (Player 2) wins!")) {
                    System.out.println(clientResponse);
                    break;
                }
            }

            // Close all resources
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
