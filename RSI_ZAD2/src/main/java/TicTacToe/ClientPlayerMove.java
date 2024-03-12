package TicTacToe;

import java.io.Serializable;

public class ClientPlayerMove implements Serializable {
    public final int x;
    public final int y;
    private final String playerName;
    public ClientPlayerMove(int x, int y, String playerName) {
        this.x = x;
        this.y = y;
        this.playerName = playerName;
    }
    public ClientPlayerMove(int x, int y) {
        this.x = x;
        this.y = y;
        this.playerName = null;
    }
}
