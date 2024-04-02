import javax.swing.*;
import java.awt.*;

public class GridDisplay extends JPanel {
    private int [][] map;
    private int rows;
    private int cols;

    public GridDisplay(int[][] map){
        this.map = map;
        this.rows = map.length;
        this.cols = map[0].length;
        // Can adjust cell size as needed
        setPreferredSize(new Dimension(cols * 30, rows * 30));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int cellWidth = getWidth() / cols;
        int cellHeight = getHeight() / rows;

        for(int i = 0; i < rows; i++){
            for(int j = 0; j < rows; j++){
                int value = map[i][j];
                Color color;
                switch (value) {
                    case 0: // Empty Space
                        color = Color.WHITE;
                        break;
                    case 1: // Obstacle
                        color = Color.BLACK;
                        break;
                    case 2: // Current Character Position
                        color = Color.GREEN;
                        break;
                    case 3: // Weapons
                        color = Color.YELLOW;
                        break;
                    case 4: // Enemies
                        color = Color.RED;
                        break;
                    default:
                        color = Color.WHITE;
                }
                g.setColor(color);
                g.fillRect(j * cellWidth, i * cellHeight, cellWidth, cellHeight);
                g.setColor(Color.BLACK);
                g.drawRect(j * cellWidth, i * cellHeight, cellWidth, cellHeight);
            }
        }
    }
}