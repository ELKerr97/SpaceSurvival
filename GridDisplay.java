import javax.swing.*;
import java.awt.*;

public class GridDisplay extends JPanel {
    private int [][] map;
    private int rows;
    private int cols;
    // "Pieces" on board
    private final int EMPTY_SPACE = 0;
    private final int PLAYER = 1;
    private final int ALIEN = 2;
    private final int PORTAL = 3;
    private final int OBSTACLE = 10;

    public GridDisplay(int[][] map){
        this.map = map;
        this.rows = map.length;
        this.cols = map[0].length;
        // Can adjust cell size as needed
        setPreferredSize(new Dimension(cols * 50, rows * 50));
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
                    case EMPTY_SPACE: 
                        color = Color.WHITE;
                        break;
                    case OBSTACLE: 
                        color = Color.BLACK;
                        break;
                    case PLAYER: 
                        color = Color.GREEN;
                        break;
                    case PORTAL: 
                        color = Color.YELLOW;
                        break;
                    case ALIEN: // Enemies
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