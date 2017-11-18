import javax.swing.JList;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import java.util.List;
import java.util.ArrayList;

public class PatternPanel extends JPanel {

private JList guiList;
public PatternPanel() {
super();
setLayout(new BorderLayout());
guiList = new JList();
add(new JScrollPane(guiList));
}

public void setPatterns(List<Pattern> list) {
ArrayList<String> names = new ArrayList<String>();
if (list.isEmpty()) {System.out.println ("Input file contains no valid Pattern Strings");}
int index=0;
for(Pattern p: list){
String string = index+") "+p.getName();
names.add(string);
index++;
}

guiList.setListData(names.toArray());
} 

}