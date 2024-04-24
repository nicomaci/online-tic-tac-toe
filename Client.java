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
                    
                    if (socket.getInputStream().available() > 0) {
                        String line;
                        while (!(line = inFromServer.readLine()).equals("END_OF_BOARD_STATE")) {
                        System.out.println(line);
                        }
                    }
                    
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
                    // System.out.println(inFromServer.readLine());
                    break;
                }

                // Player's turn (client)
                System.out.println("Your turn. Enter position (0-8): ");
                int clientMove = Integer.parseInt(userInput.readLine());
                outToServer.println(clientMove);

                String line2;
                while (!(line2 = inFromServer.readLine()).equals("END_OF_BOARD_STATE")) {
                    System.out.println(line2);
                }

                
            }

            // Close all resources
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}