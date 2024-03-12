package TicTacToe;

import com.sun.xml.internal.ws.api.message.Message;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class ServerGame extends UnicastRemoteObject implements ServerGameInterface {
    protected ServerGame() throws RemoteException {
        super();
    }
    private static final List<ClientPlayerInterface> players = new ArrayList<>();
    private static final char[][] gameBoard = {{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};
    @Override
    public String connect(ClientPlayerInterface user) throws RemoteException {
        if (players.size() == 2)
            return "Game already started";
        players.add(user);
        String message = MessageFormat.format("{0} joined the game", user.getName());
        System.out.println(message);

        if (players.size() == 2) {
            System.out.println("Game starts");
            char winner = StartGame();
            String endMessage = winner == ' ' ?
                    "It's a draw!" :
                    MessageFormat.format("Winner is {0}!", winner);
            printGameboard();
            for (ClientPlayerInterface player : players)
                player.printMessage(endMessage);
            return "";
        } else {
            return "Waiting for second player";
        }
    }
    @Override
    public void checkConnection() throws RemoteException {
        System.out.println("Server responded to checkConnection");
    }
    private char StartGame() throws RemoteException {
        players.get(0).printMessage("Game starts! You're X\n\n\n");
        players.get(1).printMessage("Game starts! You're 0\n\n\n");

        boolean gameInProgress = true;
        int playerId = 1;
        int waitPlayerId = 0;
        while (gameInProgress) {
            playerId = waitPlayerId;
            waitPlayerId = playerId == 1 ? 0 : 1;
            printGameboard();
            players.get(waitPlayerId).printMessage("It's your opponent turn!");
            List<ClientPlayerMove> legalMoves = getLegalMoves(gameBoard);
            ClientPlayerMove move = players.get(playerId).makeMove(legalMoves);
            gameBoard[move.x][move.y] = playerId == 1 ? '0' : 'X';
            char winner = checkWinner(gameBoard);
            if (winner != ' ')
                return winner;
        }
        return 'n';
    }
    private List<ClientPlayerMove> getLegalMoves(char[][] gameboard) {
        List<ClientPlayerMove> rtn = new ArrayList<>();
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                if (gameboard[x][y] == ' ')
                    rtn.add(new ClientPlayerMove(x, y));
            }
        }
        return rtn;
    }
    private void printGameboard() throws RemoteException {
        String pattern = "\n\n {0} | {1} | {2} \n" +
                         "-----------\n" +
                         " {3} | {4} | {5} \n" +
                         "-----------\n" +
                         " {6} | {7} | {8} \n\n";
        String gmbrd = MessageFormat.format(pattern, convertTo1DCharacterArray(gameBoard));
        for (ClientPlayerInterface player : players) {
            player.printMessage(gmbrd);
        }
    }
    private static Character[] convertTo1DCharacterArray(char[][] charArray2D) {
        int numRows = charArray2D.length;
        int numCols = charArray2D[0].length;
        Character[] result = new Character[numRows * numCols];
        int index = 0;
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                result[index++] = charArray2D[i][j];
            }
        }
        return result;
    }
    private static char checkWinner(char[][] board) {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != ' ') {
                return board[i][0];
            }
        }

        // Check columns
        for (int j = 0; j < 3; j++) {
            if (board[0][j] == board[1][j] && board[1][j] == board[2][j] && board[0][j] != ' ') {
                return board[0][j];
            }
        }

        // Check diagonals
        if ((board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != ' ') ||
                (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != ' ')) {
            return board[1][1];
        }

        // Check for draw
        boolean isFull = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    isFull = false;
                    break;
                }
            }
        }
        if (isFull) {
            return 'D'; // D represents a draw
        }

        // No winner
        return ' ';
    }
}