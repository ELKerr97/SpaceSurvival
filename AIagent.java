import java.util.ArrayList;

public class AIagent extends LocationServices{
    // Game map
    private int[][] map;
    // Is the player armed with a weapon
    private boolean playerArmed;
    // Player movement speed
    private int playerMovementSpeed;
    // Alien movement speed
    private int alienMovementSpeed;
    // Value map for player
    private int[][] playerValMap;
    // Value map for alien
    private int[][] alienValMap;
    // Current alien positions
    private ArrayList<int[]> alienPositions;
    // Current player position
    private int[] playerPosition;
    // Portal (goal) position
    private int[] portalPosition;

    public AIagent(int[][] map, boolean playerArmed, int playerMovementSpeed, int alienMovementSpeed){
        this.map = map;
        this.playerValMap = null;
        this.alienValMap = null;
        this.playerArmed = false;
        this.playerMovementSpeed = playerMovementSpeed;
        this.alienMovementSpeed = alienMovementSpeed;
        initializeValues();
        initializeAlienValueMap();
        initializePlayerValueMap();
    }

    // TODO: Determine alien(s) next move
    public int getAlienNextMove(){
        
        return MOVE_DOWN;
    }

    // TODO: Determine human next move
    public int getPlayerNextMove(){

        return MOVE_UP;
    }

    // Initialize alien value map for iteration later on
    private void initializeAlienValueMap(){
        ArrayList<int[]> alienPositions = getAlienPositions(map);
        int[] playerPosition = getPLayerPosition(map);
        alienValMap = new int[map.length][map[0].length];
        alienValMap[playerPosition[0]][playerPosition[1]] = 100;
        System.out.println("Alien Value Map");
        printMap(alienValMap);
        return;
    }

    // Initialize player value map
    private void initializePlayerValueMap(){
        playerValMap = new int[map.length][map[0].length];
        playerValMap[portalPosition[0]][portalPosition[1]] = 100;
        System.out.println("Player Value Map");
        printMap(playerValMap);
        return;
    }

    // Initialize alien, player, and portal positions
    private void initializeValues(){
        alienPositions = getAlienPositions(map);
        playerPosition = getPLayerPosition(map);
        portalPosition = getPortalPosition(map);
    }

    // Get next states for alien or player
    private ArrayList<int[]> getNextStates(int[] currentPosition) {
        ArrayList<int[]> nextStates = new ArrayList<>();
    
        // Define the possible actions (moves)
        int[][] moves = {
            {-1, 0}, // Up
            {1, 0},  // Down
            {0, -1}, // Left
            {0, 1}   // Right
        };
    
        // Check each possible move
        for (int[] move : moves) {
            int nextRow = currentPosition[0] + move[0];
            int nextCol = currentPosition[1] + move[1];
    
            // Check if the next position is within the map bounds and not an obstacle
            if (isValidPosition(nextRow, nextCol) && map[nextRow][nextCol] != OBSTACLE) {
                // Add the next position as a possible next state
                nextStates.add(new int[]{nextRow, nextCol});
            }
        }
    
        return nextStates;
    }
    
    private boolean isValidPosition(int row, int col) {
        return row >= 0 && row < map.length && col >= 0 && col < map[0].length;
    }
}
