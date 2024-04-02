public class Game extends GameConstants{
    // Game map
    private int[][] map;
    // Is the player armed with a weapon
    private boolean playerArmed;
    // Player movement speed
    private int playerMovementSpeed;
    // Alien movement speed
    private int alienMovementSpeed;

    public Game(int[][] map, int playerMovementSpeed, int alienMovementSpeed) {
        this.map = map;
        this.playerArmed = false;
        this.playerMovementSpeed = playerMovementSpeed;
        this.alienMovementSpeed = alienMovementSpeed;
    }   

    public void PlayGame() {
        boolean gameEnd = false;
        AIagent aiAgent = new AIagent(map, playerArmed, playerMovementSpeed, alienMovementSpeed);
        while (!gameEnd){
            int[] playerPosition = getPLayerPosition();
            System.out.println("Player position = " + playerPosition[0] + "," + playerPosition[1]);
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
                    return position;
                }
            }
        }
        
        System.out.println("Player not found in map.");
        return null;
    }
}
