import java.util.HashMap;
import java.util.Map;

public class Connect4 {

    // 1 = X
    // 0 = O
    // -1 = Empty Space
    Map<Integer, String> map = new HashMap<Integer, String>() {{
        put(1, "X");
        put(0, "O");
        put(-1, " ");
    }};

    int[][] board = new int[6][7];

    //  Position map for board

// Cols:    0  1  2  3  4  5  6 
    //     [ ][ ][ ][ ][ ][ ][ ]
    //     [ ][ ][ ][ ][ ][ ][ ]
    //     [ ][ ][ ][ ][ ][ ][ ]
    //     [ ][ ][ ][ ][ ][ ][ ]
    //     [ ][ ][ ][ ][ ][ ][ ]
    //     [ ][ ][ ][ ][ ][ ][ ]

    public Connect4() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = -1;
            }
        }
    }

    // Parameters: [Player (1 or 0), Move Position (from 0 to 6)
    // Return: 1 if valid move, -1 if invalid move
    public int makeMove(int player, int column) {
        if (column < 0 || column > 6 || board[0][column] != -1) {
            return -1;
        }

        int row = -1;

        while (row + 1 != 6 && board[row + 1][column] == -1) {
            row += 1;
        }

        board[row][column] = player;
        return 1;
    }

    // Parameters: none
    // Return: String version of board
    public String stateString() {
        String stateString = "";

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                stateString += "[" + map.get(board[i][j]) + "]";
            }
            stateString += "\n";
        }

        return stateString;
    }

    // Parameters: none
    // Return: 1 if player 1 (X) won, 0 if player 0 (O) won, -1 if no victory yet
    public int victoryCheck() {

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] != -1 && board[i][j+1] == board[i][j] && board[i][j+2] == board[i][j] && board[i][j+3] == board[i][j]) {
                    return board[i][j];
                }
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 7; j++) {
                if (board[i][j] != -1 && board[i+1][j] == board[i][j] && board[i+2][j] == board[i][j] && board[i+3][j] == board[i][j]) {
                    return board[i][j];
                }
            }
        }

        for (int i = 3; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] != -1 && board[i-1][j+1] == board[i][j] && board[i-2][j+2] == board[i][j] && board[i-3][j+3] == board[i][j]) {
                    return board[i][j];
                }
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] != -1 && board[i+1][j+1] == board[i][j] && board[i+2][j+2] == board[i][j] && board[i+3][j+3] == board[i][j]) {
                    return board[i][j];
                }
            }
        }

        return -1;

    }

    // Parameters: none
    // Return: none
    public void reset() {
        board = new int[6][7];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = -1;
            }
        }
    }

    public static void main(String[] args) {
        Connect4 game = new Connect4();
        game.makeMove(1, 3);
        System.out.println(game.stateString());
        game.makeMove(1, 3);
        System.out.println(game.stateString());
        game.makeMove(1, 3);
        System.out.println(game.stateString());
        game.makeMove(1, 3);
        System.out.println(game.stateString());
        game.makeMove(1, 3);
        System.out.println(game.stateString());
        game.makeMove(1, 3);
        System.out.println(game.stateString());
        game.makeMove(1, 3);
        System.out.println(game.stateString());
        game.makeMove(1, 3);
        System.out.println(game.stateString());
        game.makeMove(1, 3);
        System.out.println(game.stateString());
        System.out.println(game.victoryCheck());
    }
    
}
