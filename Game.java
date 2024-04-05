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

    public Game(int[][] map, int playerMovementSpeed, int alienMovementSpeed) {
        this.map = map;
        this.playerArmed = false;
        this.playerMovementSpeed = playerMovementSpeed;
        this.alienMovementSpeed = alienMovementSpeed;
    }   

    public void PlayGame() {
        boolean gameEnd = false;
        AIagent aiAgent = new AIagent(map, playerArmed, playerMovementSpeed, alienMovementSpeed);
        getAlienPositions();
        getAlienPositions();
        
        while (!gameEnd){
            printMap();
            int[] playerPosition = getPLayerPosition();
            ArrayList<int[]> alienPositions = getAlienPositions();
            pause(5);
        }
        return;
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
        for (int i = 0; i < map[0].length; i++){
            for (int j = 0; j < map.length; j++){
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
}
