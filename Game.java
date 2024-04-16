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
    // Alien Detection accuracy
    private double alienDetectionAccuracy;
    // Player risk factor
    private double playerRiskFactor;
    // Game end
    boolean gameEnd;


    public Game(int[][] map, int playerMovementSpeed, int alienMovementSpeed, int gameSpeed, double alienDetectionAccuracy, double playerRiskFactor) {
        this.map = map;
        this.playerArmed = false;
        this.playerMovementSpeed = playerMovementSpeed;
        this.alienMovementSpeed = alienMovementSpeed;
        this.playerPosition = null;
        this.alienPosition = null;
        this.portalPosition = null;
        this.pause = gameSpeed;
        this.alienDetectionAccuracy = alienDetectionAccuracy;
        this.playerRiskFactor = playerRiskFactor;
    }   

    public void playGame() {
        gameEnd = false;
        aiAgent = new AIagent(map, alienDetectionAccuracy, playerRiskFactor);
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

            // Pause 
            pause(pause);
        }

        return;
    }

    private void checkEndGame(int playerX, int playerY, int alienX, int alienY){
        if (alienX == playerX && alienY == playerY || playerX == alienPosition[0] && playerY == alienPosition[1]){
            gameEnd = true;
            JOptionPane.showMessageDialog(frame, "The alien trapped the human!", "Game Over", JOptionPane.ERROR_MESSAGE);
        } else if (playerX == portalPosition[0] && playerY == portalPosition[1]){
            gameEnd = true;
            JOptionPane.showMessageDialog(frame, "The human got to the portal!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
        }
        return;
    }

    private void updateMap(int[] playerMove, int[] alienMove) {
        // Update player position 
        int newPlayerX = playerPosition[0] + playerMove[0];
        int newPlayerY = playerPosition[1] + playerMove[1];

        // Update alien position
        int newAlienX = alienPosition[0] + alienMove[0];
        int newAlienY = alienPosition[1] + alienMove[1];

        checkEndGame(newPlayerX, newPlayerY, newAlienX, newAlienY);

        // If game ended, no need to update map anymore.
        map[playerPosition[0]][playerPosition[1]] = EMTY;
        map[newPlayerX][newPlayerY] = PLYR;

        map[alienPosition[0]][alienPosition[1]] = EMTY;
        map[newAlienX][newAlienY] = ALIN;

        aiAgent.updatePlayerPosition(newPlayerX, newPlayerY);
        playerPosition[0] = newPlayerX;
        playerPosition[1] = newPlayerY;

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
