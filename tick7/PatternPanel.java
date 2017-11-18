import javax.swing.JList;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import java.util.List;
import java.util.ArrayList;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public abstract class PatternPanel extends JPanel {

private Pattern currentPattern;
private JList guiList;
private List<Pattern> patternList;

abstract void onPatternChange();

public PatternPanel() {
super();
currentPattern = null;
setLayout(new BorderLayout());
guiList = new JList();
add(new JScrollPane(guiList));
guiList.addListSelectionListener(new ListSelectionListener() {
public void valueChanged(ListSelectionEvent e) {
if (!e.getValueIsAdjusting() && (patternList != null)) {
int sel = guiList.getSelectedIndex();
if (sel != -1) {
currentPattern = patternList.get(sel);
onPatternChange();
}
}
}
});
}

public Pattern getCurrentPattern() {
return currentPattern;
}

public void setPatterns(List<Pattern> list) {
patternList = list;
if (list == null) {
currentPattern = null; 
guiList.setListData(new String[]{}); 
return;
}
ArrayList<String> names = new ArrayList<String>();
if (list.isEmpty()) {System.out.println ("Input file contains no valid Pattern Strings");}
int index=0;
for(Pattern p: list){
String string = index+") "+p.getName();
names.add(string);
index++;
}
guiList.setListData(names.toArray());
currentPattern = list.get(0); 
guiList.setSelectedIndex(0); 
} 

}