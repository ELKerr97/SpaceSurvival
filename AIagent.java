import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

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
    // Movement directions
    private final static int[][] DIRECTIONS = {{1,0},{-1,0},{0,1},{0,-1}};
    // Weight for progress towards portal
    private final double PROG_WEIGHT = 5.0;

    public AIagent(int[][] map, boolean playerArmed, int playerMovementSpeed, int alienMovementSpeed){
        this.map = map;
        this.playerValMap = null;
        this.alienValMap = null;
        this.playerArmed = false;
        this.playerMovementSpeed = playerMovementSpeed;
        this.alienMovementSpeed = alienMovementSpeed;
        initializeValues();
        // initializeAlienValueMap();
        // initializePlayerValueMap();
    }

    // TODO: Determine alien(s) next move
    public int[] getAlienNextMove(){
        
        return null;
    }

    // TODO: Determine human next move
    public int[] getPlayerNextMove(){
        playerPosition = getPLayerPosition(map);
        // Start with possible directions and apply weights
        System.out.println("PlayerX: " + playerPosition[0] + ", PlayerY: " + playerPosition[1]);

        ArrayList<double[]> validMoves = getValidMoves(playerPosition);

        boolean[][] visited = new boolean[map.length][map[0].length];
        visited[playerPosition[0]][playerPosition[1]] = true;
        int[] bestMove = new int[3];

        bestMove[2] = -100000;

        for (double[] move : validMoves){
            int x = (int)move[0];
            int y = (int)move[1];

            System.out.println("X: " + x + ", Y: " + y);
            int mapPositionX = x + playerPosition[0];
            int mapPositionY = y + playerPosition[1];
            // System.out.println("MapX: " + mapPositionX + ", MapY: " + mapPositionY);
            visited[playerPosition[0] + x][playerPosition[1] + y] = true;
            // If alien is in that spot, don't go there.
            if (map[mapPositionX][mapPositionY] == ALIEN){
                move[2] += -10000.0;
            // If portal is in that spot, go there.
            } else if (map[mapPositionX][mapPositionY] == PORTAL){
                move[2] += 10000.00;
            } 

            if (move[2] > bestMove[2]) {
                bestMove[0] = (int)move[0];
                bestMove[1] = (int)move[1];
                bestMove[2] = (int)move[2];
            }
        }
        
        // Go one more layer to make sure alien is 2 spots away
        for (double[] move : validMoves) {
            double weight = 0;
            for (int[] dir : DIRECTIONS){
                int newX = (int)move[0] + dir[0] + playerPosition[0];
                int newY = (int)move[1] + dir[1] + playerPosition[1];
                
                // Only look if it's a valid position
                if (isValidPosition(newX, newY)){
                    // If alien is close, we want to avoid it.
                    if (map[newX][newY] == ALIEN){
                        weight += -500;
                    } 
                    // Weight based on distance to goal
                    double distanceUtility = getDistToGoal(playerPosition, portalPosition) - getDistToGoal(new int[]{newX, newY}, portalPosition);
                    weight += PROG_WEIGHT*distanceUtility;
                } 
                move[2] += weight;
            }

            if (move[2] > bestMove[2]){
                bestMove[0] = (int)move[0];
                bestMove[1] = (int)move[1];
                bestMove[2] = (int)move[2];
            }
        }

        for (double[] move : validMoves){
            System.out.println("x: " + move[0] + ", y: " + move[1] + ", score: " + move[2]);
        }

        return bestMove;
    }

    private double getDistToGoal(int[] currPosition, int[] goalPosition){
        return Math.sqrt(
            Math.pow(goalPosition[0] - currPosition[0], 2) + Math.pow(goalPosition[1] - currPosition[1], 2)
            );
    }
    
    private ArrayList<double[]> getValidMoves(int[] position){
        ArrayList<double[]> validMoves = new ArrayList<>();
        for (int[] dir : DIRECTIONS){
            if (isValidPosition(position[0] + dir[0], position[1] + dir[1])){
                // Add direction along with utility value starting at 0
                validMoves.add(new double[]{dir[0], dir[1], 0});
            }
        }
        return validMoves;
    }

    private boolean isValidPosition(int row, int col) {
        return row >= 0 && row < map.length && col >= 0 && col < map[0].length;
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
}
