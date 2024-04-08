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
    private double[][] playerValMap;
    // Value map for alien
    private double[][] alienValMap;
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

    private void playerValueIteration(){
        double goalReward = 100.0;

        boolean converged = false;
        while(!converged){
            double[][] oldValMap = playerValMap.clone();

            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[0].length; j++) {
                    playerValMap[i][j] = calculateExpectedValue(i, j, goalReward, oldValMap, playerPosition);
                }
            }

            converged = checkConvergence(oldValMap, playerValMap, 0.1);
        }
    }

    
    // Calculate expected value for a state
    private double calculateExpectedValue(int row, int col, double goalReward, double[][] valMap, int[] currentPosition) {
        // Implement transition function to get next states
        ArrayList<int[]> nextStates = getNextStates(currentPosition);

        // Calculate expected value based on next states and rewards
        double maxValue = Double.NEGATIVE_INFINITY;
        for (int[] nextState : nextStates) {
            // Calculate value for each next state
            double nextStateValue = map[nextState[0]][nextState[1]] == PORTAL ? goalReward : valMap[nextState[0]][nextState[1]];
            // Update maximum value
            maxValue = Math.max(maxValue, nextStateValue);
        }

        // Return expected value (including reward for current state)
        return maxValue;
    }

    // Check for convergence by comparing old and new value functions
    private boolean checkConvergence(double[][] oldValueFunction, double[][] newValueFunction, double threshold) {
        // Implement convergence check based on threshold or maximum iterations
        // Return true if converged, false otherwise
        return false;
    }

    // Initialize alien value map for iteration later on
    private void initializeAlienValueMap(){
        ArrayList<int[]> alienPositions = getAlienPositions(map);
        int[] playerPosition = getPLayerPosition(map);
        alienValMap = new double[map.length][map[0].length];
        alienValMap[playerPosition[0]][playerPosition[1]] = 100;
        System.out.println("Alien Value Map");
        printMap(alienValMap);
        return;
    }

    // Initialize player value map
    private void initializePlayerValueMap(){
        playerValMap = new double[map.length][map[0].length];
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
