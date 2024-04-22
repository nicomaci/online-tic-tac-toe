import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {

    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    public Client(String ip, int  port) throws IOException {

        Scanner scan = new Scanner(System.in);

        // client socket creation
        socket = new Socket(ip, port);
        
        // input Output stream creation
        in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        out = new DataOutputStream(socket.getOutputStream());

        //introduction message from server and command list
        String serverMessage = in.readUTF();
        System.out.println("Recieved from server: " + serverMessage);
        System.out.println("TicTacToe, Connect Four, bye, or shutdown server: ");

        // program loop
        while (true) {

            // user inputs command, if they input bye/shutdown at any point game ends

            System.out.print("Input row: ");
            String userInputRow = scan.nextLine();

            if (userInput.equals("bye")) {
                String response = in.readUTF();
                System.out.println("Server response: " + response);
                System.out.println("exit");
                break;

            } else if (userInput.equals("shutdown server")) {
                String response = in.readUTF();
                System.out.println("Server response: " + response);
                System.out.println("exit");
                break;

            }

            Sytem.out.print("Input column: ");
            String userInputCol = scan.nextLine();

            int index = stoi(userInputRow) * stoi(userInputCol); //calculate index

            string userInput = String.valueOf(index); //index of array they plan to take, converted to string

            // Write out to server user command and receive response
            out.writeUTF(userInput);
            String response = in.readUTF();

            System.out.println("Server response: " + response); // server responds w/ new board after calling game logic

        }

        scan.close();

    }

    public static void main(String[] args) throws IOException {

        new Client(args[0], Integer.parseInt(args[1]));
        
    }
}