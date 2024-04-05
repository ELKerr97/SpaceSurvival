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
    // Possible movements for alien and human
    private final int MOVE_UP = 1;
    private final int MOVE_DOWN = 2;
    private final int MOVE_LEFT = 3;
    private final int MOVE_RIGHT = 4;
    private final int STAY = 5;
    // Possible human action to shoot alien if wielding a gun
    private final int SHOOT_ALIEN = 6;

    public AIagent(int[][] map, boolean playerArmed, int playerMovementSpeed, int alienMovementSpeed){
        this.map = map;
        this.playerArmed = false;
        this.playerMovementSpeed = playerMovementSpeed;
        this.alienMovementSpeed = alienMovementSpeed;
    }

    // Update the map based on human and alien decisions
    private void updateMap(){
        int alienTurn = alienTurn();
        int humanTurn = humanTurn();

        return;
    }

    // Retrieve updated map
    public int[][] getUpdatedMap(){
        return map;
    }

    // Determine alien(s) next move
    public int alienTurn(){

        return MOVE_DOWN;
    }

    // Determine human next move
    public int humanTurn(){

        return MOVE_UP;
    }

    public boolean getPlayerArmed() {
        return playerArmed;
    }



    
}
