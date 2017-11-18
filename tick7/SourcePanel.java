import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public abstract class SourcePanel extends JPanel {

private JRadioButton current;

protected abstract boolean setSourceNone(); 
protected abstract boolean setSourceFile(); 
protected abstract boolean setSourceLibrary(); 
protected abstract boolean setSourceFourStar(); 

public SourcePanel() {
super();
setLayout(new BoxLayout(this,BoxLayout.X_AXIS));

JRadioButton none = new JRadioButton(Strings.BUTTON_SOURCE_NONE, true);
JRadioButton file = new JRadioButton(Strings.BUTTON_SOURCE_FILE, true);
JRadioButton library = new JRadioButton(Strings.BUTTON_SOURCE_LIBRARY, true);
JRadioButton fourStar = new JRadioButton(Strings.BUTTON_SOURCE_FOURSTAR, true); 

none.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e) {
if (!setSourceNone())
current = none; 
}
});

file.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e) {
if (setSourceFile())
current = file; 
else
current.setSelected(true); 
}
});

library.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e) {
if (setSourceLibrary())
current = library;
else
current.setSelected(true);
}
});

fourStar.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e) {
if (setSourceFourStar())
current = fourStar; 
else
current.setSelected(true);
}
});

add(none);
add(file);
add(library);
add(fourStar);

ButtonGroup group = new ButtonGroup();
group.add(none);
group.add(file);
group.add(library);
group.add(fourStar);

current = none;
}

}