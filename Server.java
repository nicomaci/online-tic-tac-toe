import java.io.*;
import java.net.*;

public class Server {
    private static final int PORT = 3351; // Choose any available port
    
    public static void main(String[] args) throws InterruptedException {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Server started. Waiting for client to connect...");

            // Accepting client (Player 2)
            Socket clientSocket = serverSocket.accept();
            System.out.println("Player 2 connected.");

            // Sending player 2 message
            PrintWriter outToClient = new PrintWriter(clientSocket.getOutputStream(), true);
            outToClient.println("You are player 2. Game is starting...");

            // Creating instance of TicTacToe for player 1 (server)
            TicTacToe board = new TicTacToe();

            // Creating input and output streams for communication with client
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter outToClientGame = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader serverInput = new BufferedReader(new InputStreamReader(System.in));

            // Main game loop
            while (true) {
                // Player 1's turn (server)
                System.out.println("Player 1 (Server)'s turn.");
                System.out.println("Server, enter position (0-8): ");
                // System.out.println(board.stateString());
                int serverMove = Integer.parseInt(serverInput.readLine());
                int x = board.makeMove(0, serverMove);
                if (x == -1) {
                    System.out.println("Invalid move, please input a new position");
                    continue;
                }
                System.out.println("Server's move:");
                System.out.println(board.stateString());
                
                if (board.victoryCheck() == 0) {
                    outToClientGame.println("Server wins!");
                    outToClientGame.println(board.stateString());
                    outToClientGame.println("END_OF_BOARD_STATE");
                    System.out.println("Server wins!");
                    Thread.sleep(100);
                    break;
                }

                outToClientGame.println(board.stateString());
                outToClientGame.println("END_OF_BOARD_STATE");

                // Player 2's turn (client)
                //outToClientGame.println("Your turn. Enter position (0-8): ");
                System.out.println("Waiting for client move...");
                String clientResponse = inFromClient.readLine();
                int clientMove = Integer.parseInt(clientResponse);
                int y = board.makeMove(1, clientMove);
                System.out.println("Client's move:");
                System.out.println(board.stateString());

                outToClientGame.println(board.stateString());
                outToClientGame.println("END_OF_BOARD_STATE");

                // Check for client (player 2) victory
                if (board.victoryCheck() == 1) {
                    outToClientGame.println("Client (Player 2) wins!");
                    System.out.println("Client (Player 2) wins!");
                    Thread.sleep(100);
                    break;
                }
            }

            // Close all resources
            serverSocket.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}