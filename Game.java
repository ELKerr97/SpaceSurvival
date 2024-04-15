import javax.swing.*;

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
    private int[] alienPosition;
    // Goal position
    private int[] portalPosition;
    // AI Agent
    private AIagent aiAgent;
    // JFrame
    private JFrame frame;
    // Pause time
    private int pause;

    public Game(int[][] map, int playerMovementSpeed, int alienMovementSpeed, int gameSpeed) {
        this.map = map;
        this.playerArmed = false;
        this.playerMovementSpeed = playerMovementSpeed;
        this.alienMovementSpeed = alienMovementSpeed;
        this.playerPosition = null;
        this.alienPosition = null;
        this.portalPosition = null;
        this.pause = gameSpeed;
    }   

    public void playGame() {
        boolean gameEnd = false;
        aiAgent = new AIagent(map, playerArmed, playerMovementSpeed, alienMovementSpeed);
        int totalRounds = 0;
        printMap(map);
        portalPosition = getPortalPosition(map);

        // Display map on GUI
        frame = new JFrame("Space Survival");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GridDisplay gridDisplay = new GridDisplay(map);
        frame.add(gridDisplay);

        frame.pack();
        frame.setVisible(true);

        frame.repaint();

        while (!gameEnd){
            // Get current positions of player and aliens (May not need this actually)
            playerPosition = getPLayerPosition(map);
            alienPosition = getAlienPositions(map);

            // Run AI to determine next move for alien and player
            int[] playerMove = aiAgent.getAgentNextMove(playerPosition, portalPosition, PLYR);
            int[] alienMove = aiAgent.getAgentNextMove(alienPosition, playerPosition, ALIN);

            updateMap(playerMove, alienMove);
            frame.repaint();
            printMap(map);

            totalRounds += 1;
            System.out.println("Total Rounds: " + totalRounds);

            gameEnd = gameEnded();
            // Pause 
            pause(pause);
        }
        return;
    }

    private boolean gameEnded(){
        if (playerPosition[0] == portalPosition[0] && playerPosition[1] == portalPosition[1]){
            JOptionPane.showMessageDialog(frame, "The human got to the portal!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } else if (playerPosition[0] == alienPosition[0] && playerPosition[1] == alienPosition[1]){
            JOptionPane.showMessageDialog(frame, "The alien trapped the human!", "Game Over", JOptionPane.ERROR_MESSAGE);
            return true;
        } else {
            return false;
        }
    }

    private void updateMap(int[] playerMove, int[] alienMove) {
        // Update player position 
        int newPlayerX = playerPosition[0] + playerMove[0];
        int newPlayerY = playerPosition[1] + playerMove[1];

        map[playerPosition[0]][playerPosition[1]] = EMTY;
        map[newPlayerX][newPlayerY] = PLYR;

        aiAgent.updatePlayerPosition(newPlayerX, newPlayerY);
        playerPosition[0] = newPlayerX;
        playerPosition[1] = newPlayerY;

        // Update alien position
        int newAlienX = alienPosition[0] + alienMove[0];
        int newAlienY = alienPosition[1] + alienMove[1];

        map[alienPosition[0]][alienPosition[1]] = EMTY;
        map[newAlienX][newAlienY] = ALIN;

        aiAgent.updateAlienPosition(newAlienX, newAlienY);
        alienPosition[0] = newAlienX;
        alienPosition[1] = newAlienY;
    }

    // Add time between turns
    private void pause(int seconds) {
        try {
            Thread.sleep(seconds * 500); 
        } catch (InterruptedException e) {
            // Handle interrupted exception if needed
        }
    }
}
