import java.util.ArrayList;

public class Game extends GameConstants{
    // Game map
    private int[][] map;
    // Is the player armed with a weapon
    private boolean playerArmed;
    // Player movement speed
    private int playerMovementSpeed;
    // Alien movement speed
    private int alienMovementSpeed;
    // Toggle to describe positions
    private boolean describePositions = true;
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
        while (!gameEnd){
            // Print map
            printMap();
            
            // Get current positions of player and aliens
            playerPosition = getPLayerPosition();
            alienPositions = getAlienPositions();

            // Run AI to determine next move for alien and player
            int playerMove = aiAgent.getPlayerNextMove(playerPosition);
            int alienMove = aiAgent.getAlienNextMove(alienPositions);

            updateMap(playerMove, alienMove);
            
            totalRounds += 1;
            System.out.println("Total Rounds: " + totalRounds);

            // Wait 5 seconds
            pause(2);
        }
        return;
    }

    private void updateMap(int playerMove, int alienMove) {
        // Update player position 
        switch (playerMove) {
            case MOVE_DOWN:
                if (playerPosition[0] < map.length - playerMovementSpeed){
                    map[playerPosition[0] + playerMovementSpeed][playerPosition[1]] = PLAYER;
                    map[playerPosition[0]][playerPosition[1]] = EMPTY_SPACE;
                } else {
                    System.out.println("Error: Player tried to move DOWN, but out of bounds.");
                }
                break;
            case MOVE_LEFT:
                if (playerPosition[1] >= playerMovementSpeed){
                    map[playerPosition[0]][playerPosition[1] - playerMovementSpeed] = PLAYER;
                    map[playerPosition[0]][playerPosition[1]] = EMPTY_SPACE;
                } else {
                    System.out.println("Error: Player tried to move LEFT, but out of bounds.");
                }
                break;
            case MOVE_RIGHT:
                if (playerPosition[1] > map[0].length - playerMovementSpeed){
                    map[playerPosition[0]][playerPosition[1] + playerMovementSpeed] = PLAYER;
                    map[playerPosition[0]][playerPosition[1]] = EMPTY_SPACE;
                } else {
                    System.out.println("Error: Player tried to move RIGHT, but out of bounds.");
                }  
                break;
            case MOVE_UP:
                if (playerPosition[0] >= playerMovementSpeed){
                    map[playerPosition[0] - playerMovementSpeed][playerPosition[1]] = PLAYER;
                    map[playerPosition[0]][playerPosition[1]] = EMPTY_SPACE;
                } else {
                    System.out.println("Error: Player tried to move UP, but out of bounds.");
                }
                break;
            case STAY:
                break;
            default:
                break;
        }

        // Update alien position
    }

    private int[] getPLayerPosition(){
        int[] position = new int[2];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                if (map[i][j] == PLAYER) {
                    position[0] = i;
                    position[1] = j;
                    if (describePositions) {System.out.println("Player at position (" + position[0] + "," + position[1] + ")");}
                    return position;
                }
            }
        }
        
        System.out.println("Player not found in map.");
        return null;
    }

    private ArrayList<int[]> getAlienPositions(){
        ArrayList<int[]> positions = new ArrayList<>();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                if (map[i][j] == ALIEN) {
                    // add location to positions array
                    positions.add(new int[] {i,j});
                }
            }
        }
        
        if (describePositions) {
            System.out.println(positions.size() + " alien(s) found on map.");
            System.out.println("Alien Positions:");
            for (int[] pos : positions) {
                System.out.println("(" + pos[0] + "," + pos[1] + ")");
            }
        }
        return positions;
    }

    private void pause(int seconds) {
        try {
            Thread.sleep(seconds * 1000); // Sleep for 1000 milliseconds (1 second)
        } catch (InterruptedException e) {
            // Handle interrupted exception if needed
        }
    }

    private void printMap(){
        for (int i = 0; i < map[0].length + 10; i++) {
            System.out.print("- ");
        }
        System.out.println();
        for (int i = 0; i < map[0].length; i++){
            for (int j = 0; j < map.length; j++){
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println();
    }
}
