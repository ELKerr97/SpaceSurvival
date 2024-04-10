import java.util.ArrayList;

public class Game extends LocationServices{
    // Game map
    private int[][] map;
    // Is the player armed with a weapon
    private boolean playerArmed;
    // Player movement speed
    private int playerMovementSpeed;
    // Alien movement speed
    private int alienMovementSpeed;
    // Current player position
    private int[] playerPosition;
    // Current alien position(s)
    private ArrayList<int[]> alienPositions;

    public Game(int[][] map, int playerMovementSpeed, int alienMovementSpeed) {
        this.map = map;
        this.playerArmed = false;
        this.playerMovementSpeed = playerMovementSpeed;
        this.alienMovementSpeed = alienMovementSpeed;
        this.playerPosition = null;
        this.alienPositions = null;
    }   

    public void PlayGame() {
        boolean gameEnd = false;
        AIagent aiAgent = new AIagent(map, playerArmed, playerMovementSpeed, alienMovementSpeed);
        int totalRounds = 0;
        printMap(map);

        while (!gameEnd){
            // Get current positions of player and aliens (May not need this actually)
            playerPosition = getPLayerPosition(map);
            alienPositions = getAlienPositions(map);

            // Run AI to determine next move for alien and player
            int[] playerMove = aiAgent.getPlayerNextMove();
            int[] alienMove = aiAgent.getAlienNextMove();

            System.out.println("Player move x: " + playerMove[0] + ", y: " + playerMove[1]);

            updateMap(playerMove, alienMove);
            printMap(map);

            totalRounds += 1;
            System.out.println("Total Rounds: " + totalRounds);

            // Pause 
            pause(2);
        }
        return;
    }

    private void updateMap(int[] playerMove, int[] alienMove) {
        // Update player position 
        map[playerPosition[0]][playerPosition[1]] = EMPTY_SPACE;
        map[playerPosition[0] + playerMove[0]][playerPosition[1] + playerMove[1]] = PLAYER;

        // TODO: Update alien position
    }

    private void pause(int seconds) {
        try {
            Thread.sleep(seconds * 1000); // Sleep for 1000 milliseconds (1 second)
        } catch (InterruptedException e) {
            // Handle interrupted exception if needed
        }
    }
}
