import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;


public class SourcePanel extends JPanel {

public SourcePanel() {
super();
setLayout(new BoxLayout(this,BoxLayout.X_AXIS));

JRadioButton none = new JRadioButton(Strings.BUTTON_SOURCE_NONE, true);
JRadioButton file = new JRadioButton(Strings.BUTTON_SOURCE_FILE, true);
JRadioButton library = new JRadioButton(Strings.BUTTON_SOURCE_LIBRARY, true);
JRadioButton fourStar = new JRadioButton(Strings.BUTTON_SOURCE_FOURSTAR, true); 

add(none);
add(file);
add(library);
add(fourStar);

ButtonGroup group = new ButtonGroup();
group.add(none);
group.add(file);
group.add(library);
group.add(fourStar);
}

}