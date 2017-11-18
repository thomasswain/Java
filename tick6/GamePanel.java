import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;

public class GamePanel extends JPanel {

private int zoom = 10; //Number of pixels used to represent a cell
private int width = 1; //Width of game board in pixels
private int height = 1;//Height of game board in pixels
private World current = null;

public Dimension getPreferredSize() {
return new Dimension(width, height);
}

protected void paintComponent(Graphics g) {
if (current == null) return;
g.setColor(java.awt.Color.WHITE);
g.fillRect(0, 0, width, height);
current.draw(g, width, height);
if (zoom > 4) {
g.setColor(java.awt.Color.LIGHT_GRAY);
//TODO: Using for loops call the drawLine method on "g", 
// repeatedly to draw a grid of grey lines to delimit
// the border of the cells in the game board

for(int col = 1; col<current.getWidth(); ++col){
g.drawLine(col*zoom, 0, col*zoom, height);
}

for(int row = 1; row<current.getWidth(); ++row){
g.drawLine(0, row*zoom, width, row*zoom);
}

}
}

private void computeSize() {
if (current == null) return;
int newWidth = current.getWidth() * zoom;
int newHeight = current.getHeight() * zoom;
if (newWidth != width || newHeight != height) {
width = newWidth;
height = newHeight;
revalidate(); //trigger the GamePanel to re-layout its components
}
}
public void display(World w) {
current = w;
computeSize();
repaint();
}
}