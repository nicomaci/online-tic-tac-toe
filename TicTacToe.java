import java.util.HashMap;
import java.util.Map;

public class TicTacToe {

    // 1 = X
    // 0 = O
    // -1 = Empty Space
    Map<Integer, String> map = new HashMap<Integer, String>() {{
        put(1, "X");
        put(0, "O");
        put(-1, " ");
    }};

    int[] board = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1};

    // Position map for board
    // [0] [1] [2]
    // [3] [4] [5]
    // [6] [7] [8]

    public TicTacToe() {}
    
    // Parameters: [Player (1 or 0), Move Position (from 0 to 8)
    // Return: 1 if valid move, -1 if invalid move
    public int makeMove(int player, int position) {
        if (position < 0 || position > 8 || board[position] == 1 || board[position] == 0) {
            return -1;
        }

        board[position] = player;
        return 1;
    }

    // Parameters: none
    // Return: String version of board
    public String stateString() {
        String stateString = "";

        for (int i = 0; i < 9; i ++) {
            stateString += "[" + map.get(board[i]) + "]";
            if (i == 2 || i == 5) {
                stateString += "\n";
            } else {
                stateString += " ";
            }
        }

        return stateString;
    }

    // Parameters: none
    // Return: 1 if player 1 (X) won, 0 if player 0 (O) won, -1 if no victory yet, 
    public int victoryCheck() {
        for (int i = 0; i <= 6; i += 3) {
            if (board[i] != -1 && board[i] == board[i+1] && board[i] == board[i+2]) {
                return board[i];
            }
        }

        for (int i = 0; i <= 2; i ++) {
            if (board[i] != -1 && board[i] == board[i+3] && board[i] == board[i+6]) {
                return board[i];
            }
        }

        if (board[0] != -1 && board[0] == board[4] && board[0] == board[8]) {
            return board[0];
        }

        if (board[2] != -1 && board[2] == board[4] && board[2] == board[6]) {
            return board[2];
        }

        int draw = 0;
        for (int i : board) {
            if (i == -1) {
                draw += 1;
            }
        }
        if (draw == 0) {
            return 2;
        }

        return -1;
    }

    // Parameters: none
    // Return: none
    public void reset() {
        board = new int[] {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
    }

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        System.out.println(game.stateString());
    }
    
}

