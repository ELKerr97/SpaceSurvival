import java.util.ArrayList;

public class AIagent extends GameConstants{
    // Game map
    private int[][] map;
    // Is the player armed with a weapon
    private boolean playerArmed;
    // Player movement speed
    private int playerMovementSpeed;
    // Alien movement speed
    private int alienMovementSpeed;


    public AIagent(int[][] map, boolean playerArmed, int playerMovementSpeed, int alienMovementSpeed){
        this.map = map;
        this.playerArmed = false;
        this.playerMovementSpeed = playerMovementSpeed;
        this.alienMovementSpeed = alienMovementSpeed;
    }

    // Determine alien(s) next move
    public int getAlienNextMove(ArrayList<int[]> alienPositions){
        
        return MOVE_DOWN;
    }

    // Determine human next move
    public int getPlayerNextMove(int[] playerPosition){

        return MOVE_UP;
    }

}
