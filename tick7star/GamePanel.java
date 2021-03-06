import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Point;

public abstract class GamePanel extends JPanel {

protected abstract void updatePattern(World world);

private int zoom = 10; 
private int width = 1; 
private int height = 1;
private World current = null;

public Dimension getPreferredSize() {
return new Dimension(width, height);
}

public void setZoom(int value){
zoom = value;
}

public GamePanel(){
super();
addMouseListener(new MouseAdapter(){ 
public void mousePressed(MouseEvent me) { 
Point p = me.getPoint();
int col = p.x/zoom;
int row = p.y/zoom;
current.setCell(col, row, !current.getCell(col,row));
current.setBorders();
computeSize();
repaint();
updatePattern(current);
} 
});
}


protected void paintComponent(Graphics g) {
if (current == null) return;
g.setColor(java.awt.Color.WHITE);
g.fillRect(0, 0, width, height);
current.draw(g, width, height);
if (zoom > 4) {
g.setColor(java.awt.Color.LIGHT_GRAY);

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