import java.awt.BorderLayout;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;
import java.io.IOException;
import java.util.List;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import java.io.FileReader;
import java.io.File;

public class GuiLife extends JFrame {

private World world;
PatternPanel patternPanel;
GamePanel gamePanel;
GenerationsPanel generationsPanel;

public GuiLife() {
super("GuiLife");
setSize(640, 640);
setDefaultCloseOperation(EXIT_ON_CLOSE);
setLayout(new BorderLayout());

JComponent patternPanel = createPatternPanel();
add(patternPanel, BorderLayout.WEST);

JComponent gamePanel = createGamePanel();
add(gamePanel, BorderLayout.CENTER);

JComponent generationsPanel = createGenerationsPanel();
add(generationsPanel, BorderLayout.SOUTH);

}


private void addBorder(JComponent component, String title) {
Border etch = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
Border tb = BorderFactory.createTitledBorder(etch,title);
component.setBorder(tb);
}

private JComponent createGamePanel() {
world = new ArrayWorld(8,8);
JPanel holder = new JPanel();
addBorder(holder,Strings.PANEL_GAME_BOARD);
gamePanel = new GamePanel(){
public void updatePattern(World world){
patternPanel.setStartXText(Integer.toString(world.getMinX()));
patternPanel.setStartYText(Integer.toString(world.getMinY()));
patternPanel.setRowsText(world.getCellInfo());
patternPanel.resetPatternString();
generationsPanel.display(world);
}
};
holder.add(gamePanel);
gamePanel.display(world);
return new JScrollPane(holder);
}


private JComponent createPatternPanel() { 
try{
patternPanel = new PatternPanel(){
public void updateWorld(Pattern pattern) {
try{
pattern.initialise(world);
gamePanel.display(world);
generationsPanel.display(world);
}
catch(PatternFormatException pfe) {}
}
public void changeWorldWidth(int w){
world = world.changeWidth(w);
gamePanel.display(world);
generationsPanel.display(world);
}
public void changeWorldHeight(int h){
world = world.changeHeight(h);
gamePanel.display(world);
generationsPanel.display(world);
}
};
}
catch (PatternFormatException pfe) {}

addBorder(patternPanel,Strings.PANEL_PATTERN);
return patternPanel;
}

private JComponent createGenerationsPanel() {
JPanel holder = new JPanel();
addBorder(holder, Strings.PANEL_GENERATIONS);
generationsPanel = new GenerationsPanel();
holder.add(generationsPanel);
generationsPanel.display(world);
JScrollPane sp = new JScrollPane(holder);
sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
return sp;
}



public static void main(String[] args) {
GuiLife gui = new GuiLife();
gui.setVisible(true);
}

}