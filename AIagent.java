import java.util.*;

public class AIagent extends LocationServices{
    // Game map
    private int[][] map;
    // Is the player armed with a weapon
    private boolean playerArmed;
    // Player movement speed
    private int playerMovementSpeed;
    // Alien movement speed
    private int alienMovementSpeed;
    // Current alien position
    private int[] alienPosition;
    // Current player position
    private int[] playerPosition;
    // Portal (goal) position
    private int[] portalPosition;
    // Movement directions
    private final static int[][] DIRECTIONS = {{1,0},{-1,0},{0,1},{0,-1}};

    public AIagent(int[][] map, boolean playerArmed, int playerMovementSpeed, int alienMovementSpeed){
        this.map = map;
        this.playerArmed = false;
        this.playerMovementSpeed = playerMovementSpeed;
        this.alienMovementSpeed = alienMovementSpeed;
        initializeValues();
    }

    public void updatePlayerPosition(int x, int y){
        this.playerPosition[0] = x;
        this.playerPosition[1] = y;
    }

    // TODO: Determine alien(s) next move
    public int[] getAlienNextMove(){
        
        return null;
    }

    // TODO: Determine human next move
    public int[] getPlayerNextMove(){
        List<String> pathToGoal = getPathToGoal(playerPosition, portalPosition);
        System.out.println(pathToGoal);

        if (pathToGoal.isEmpty()){
            System.out.println("No path from player to goal found.");
            return null;
        }
        System.out.println("Player position: " + "[" + playerPosition[0] + "," + playerPosition[1] + "]");
        String nextPosition = pathToGoal.get(0);
        System.out.println("Next position: " + nextPosition);
        int[] nextPosArray = Arrays.stream(nextPosition.substring(1, nextPosition.length() - 1).split(", "))
            .mapToInt(Integer::parseInt).toArray();
        int dx = nextPosArray[0] - playerPosition[0];
        int dy = nextPosArray[1] - playerPosition[1];
        
        return new int[]{dx, dy};
    }

    // TODO: make this dynamic so alien can seek out player
    private List<String> getPathToGoal(int[] currPosition, int[] goal){
        Queue<int[]> q = new LinkedList<>();
        Map<String, int[]> parentMap = new HashMap<>();
        Set<String> visited = new HashSet<>();

        q.offer(currPosition);
        visited.add(Arrays.toString(currPosition));

        while(!q.isEmpty()){
            int[] curr = q.poll();

            if (Arrays.equals(curr, goal)){
                List<String> path = new ArrayList<>();
                while (!Arrays.equals(curr, currPosition)) {
                    path.add(Arrays.toString(curr));
                    curr = parentMap.get(Arrays.toString(curr));
                }
                Collections.reverse(path);
                return path;
            }

            for (int[] dir : DIRECTIONS){
                int newX = curr[0] + dir[0];
                int newY = curr[1] + dir[1];
                String newPosition = Arrays.toString(new int[]{newX, newY});

                if (isValidPosition(newX, newY) && !isAlienPosition(newX, newY) && !isObstaclePosition(newX, newY) 
                    && !visited.contains(newPosition)) {

                    q.offer(new int[]{newX, newY});
                    parentMap.put(newPosition, curr);
                    visited.add(newPosition);
                }
            }
        }
        return null;
    }

    private boolean isAlienPosition(int x, int y){
        if (map[x][y] == ALIEN) {
            System.out.println("ALIEN DETECTED");
            return true;
        } 
        return false;
    }

    private boolean isObstaclePosition(int x, int y){
        if (map[x][y] == OBSTACLE) {
            System.out.println("OBSTACLE DETECTED");
            return true;
        } 
        return false;
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

    // Initialize alien, player, and portal positions
    private void initializeValues(){
        alienPosition = getAlienPositions(map);
        playerPosition = getPLayerPosition(map);
        portalPosition = getPortalPosition(map);
    }
}
