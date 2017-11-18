import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Point;

public class GenerationsPanel extends JPanel {

private int zoom = 3;  
private int gap = 3;
private int width = 1;
private int height = 1;
private World current = null;

public Dimension getPreferredSize() {
return new Dimension(width, height);
}

public void setZoom(int value){
zoom = value;
}


protected void paintComponent(Graphics g) {
if (current == null) return;
g.setColor(java.awt.Color.WHITE);
g.fillRect(0, 0, (width/20)-gap, height);
current.draw(g, 0, (width/20)-gap, height);

for (int i=1; i<19; i++) {
g.setColor(java.awt.Color.WHITE);
int startX = (width/20)*i;
g.fillRect(startX, 0, (width/20)-gap, height);
(current.stepGeneration(i)).draw(g, startX, (width/20)-gap, height);
}

}

private void computeSize() {
if (current == null) return;
int newWidth = 20*(current.getWidth() * zoom + gap);
int newHeight = current.getHeight() * zoom;
if (newWidth != width || newHeight != height) {
width = newWidth;
height = newHeight;
revalidate(); 
}
}
public void display(World w) {
current = w;
computeSize();
repaint();
}
}