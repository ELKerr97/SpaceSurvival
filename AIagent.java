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
private final static int[][] DIRECTIONS = {{1,0},{-1,0},{0,1},{0,-1}/*,{-1,1},{1,1},{1,-1},{-1,-1}*/};

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

    public void updateAlienPosition(int x, int y){
        this.alienPosition[0] = x;
        this.alienPosition[1] = y;
    }

    // TODO: Determine alien(s) next move
    public int[] getAlienNextMove(){
        
        return null;
    }

    // TODO: Determine human next move
    public int[] getAgentNextMove(int[] currentPosition, int[] goalPosition, int agent){
        List<String> pathToGoal = getPathToGoal(currentPosition, goalPosition, agent);
        System.out.println(pathToGoal);
        int[] change = new int[2];

        if (pathToGoal == null){
            System.out.println("No path from player to goal found.");
            change[0] = 0;
            change[1] = 0;
            return change;
        }

        String nextPosition = pathToGoal.get(0);
        int[] nextPosArray = Arrays.stream(nextPosition.substring(1, nextPosition.length() - 1).split(", "))
            .mapToInt(Integer::parseInt).toArray();
        change[0] = nextPosArray[0] - currentPosition[0];
        change[1] = nextPosArray[1] - currentPosition[1];
        
        return change;
    }

    private List<String> getPathToGoal(int[] currPosition, int[] goal, int agent){
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
                
                if (agent == PLYR){
                    if (isValidPosition(newX, newY) && !isAlienPosition(newX, newY) && !isObstaclePosition(newX, newY) 
                    && !visited.contains(newPosition)) {
                    q.offer(new int[]{newX, newY});
                    parentMap.put(newPosition, curr);
                    visited.add(newPosition);
                }
                } else if (agent == ALIN) {
                    if (isValidPosition(newX, newY) && !isObstaclePosition(newX, newY) 
                    && !visited.contains(newPosition)) {
                    q.offer(new int[]{newX, newY});
                    parentMap.put(newPosition, curr);
                    visited.add(newPosition);
                }
                }

            }
        }
        return null;
    }

    private boolean isAlienPosition(int x, int y){
        if (map[x][y] == ALIN) {
            // System.out.println("ALIEN DETECTED");
            return true;
        }

        // Check if alien is at least one more away. We don't want to get caught!
        for (int[] dir : DIRECTIONS){
            int newX = x + dir[0];
            int newY = y + dir[1];

            if (isValidPosition(newX, newY)){
                if (map[newX][newY] == ALIN){
                    // System.out.println("ALIEN DETECTED ONE MORE AWAY");
                    return true;
                }
            }
        }

        return false;
    }

    private boolean isObstaclePosition(int x, int y){
        if (map[x][y] == OBST) {
            // System.out.println("OBSTACLE DETECTED");
            return true;
        } 
        return false;
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
