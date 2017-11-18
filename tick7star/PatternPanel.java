import javax.swing.JList;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JSpinner;
import java.util.List;
import java.util.ArrayList;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.border.EtchedBorder;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;


public abstract class PatternPanel extends JPanel {

private Pattern currentPattern;
private static String startString = "::8:8:0:0:";
private int startWidth = 8;
private int startHeight = 8;
private World world;

private JList guiList;
private List<Pattern> patternList;
private JTextField patternStringText;
private JTextField nameField;
private JTextField authorField;
private JSpinner widthSpinner;
private JSpinner heightSpinner;
private JTextField startXText;
private JTextField startYText;
private JTextField rowsText;

protected abstract void changeWorldWidth(int w);
protected abstract void changeWorldHeight(int h);

private void addBorder(JComponent component, String title) {
Border etch = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
Border tb = BorderFactory.createTitledBorder(etch,title);
component.setBorder(tb);
}

private JTextField createNewTextField(String label, String content, int length) {
Box panel = Box.createVerticalBox();
addBorder(panel,"");
panel.add(new JLabel(label));
JTextField textField = new JTextField(content, length);
textField.setPreferredSize(textField.getPreferredSize());
panel.add(textField);
add(panel);
return textField;
}

private JSpinner createNewSpinner(String label,int initial,int min,int max,int step){
Box panel = Box.createVerticalBox();
addBorder(panel,"");
add(panel);
panel.add(new JLabel(label));
JSpinner spinner = new JSpinner(new SpinnerNumberModel(initial,min,max,step));
panel.add(spinner);
return spinner;
}



public PatternPanel() throws PatternFormatException {
super();
setPreferredSize(new Dimension (250,250));
setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

patternStringText = createNewTextField(Strings.TEXT_PATTERN_STRING, null, 40);
patternStringText.setEditable(false);
add(Box.createVerticalStrut(10)); 

nameField = createNewTextField(Strings.TEXT_NAME, null, 20);
add(Box.createVerticalStrut(10)); 
nameField.getDocument().addDocumentListener(new DocumentListener() {
public void changedUpdate(DocumentEvent de){
resetPatternString();
currentPattern.setName(nameField.getText());
}
public void insertUpdate(DocumentEvent de){
resetPatternString();
currentPattern.setName(nameField.getText());
}
public void removeUpdate(DocumentEvent de){
resetPatternString();
currentPattern.setName(nameField.getText());
}
});

authorField = createNewTextField(Strings.TEXT_AUTHOR, null, 40);
add(Box.createVerticalStrut(10)); 
authorField.getDocument().addDocumentListener(new DocumentListener() {
public void changedUpdate(DocumentEvent de){
resetPatternString();
currentPattern.setAuthor(nameField.getText());
}
public void insertUpdate(DocumentEvent de){
resetPatternString();
currentPattern.setAuthor(nameField.getText());
}
public void removeUpdate(DocumentEvent de){
resetPatternString();
currentPattern.setAuthor(nameField.getText());
}
});

widthSpinner = createNewSpinner(Strings.TEXT_WIDTH, startWidth, startWidth, 1024, 1);
add(Box.createVerticalStrut(10));
widthSpinner.addChangeListener(new ChangeListener() {
public void stateChanged (ChangeEvent e) {
changeWorldWidth((int)widthSpinner.getValue());
}
});
 
heightSpinner = createNewSpinner(Strings.TEXT_HEIGHT, startHeight, startHeight, 1024, 1);
add(Box.createVerticalStrut(10));
heightSpinner.addChangeListener(new ChangeListener() {
public void stateChanged (ChangeEvent e) {
changeWorldHeight((int)heightSpinner.getValue());
}
});

startXText = createNewTextField(Strings.TEXT_STARTX, "0", 40);
startXText.setEditable(false);
add(Box.createVerticalStrut(10));

startYText = createNewTextField(Strings.TEXT_STARTY, "0", 40);
startYText.setEditable(false);
add(Box.createVerticalStrut(10));

rowsText = createNewTextField(Strings.TEXT_ROWS, null, 40);
rowsText.setEditable(false);
add(Box.createVerticalStrut(10)); 


resetPatternString();
currentPattern = new Pattern(startWidth, startHeight);

}

public void setStartXText(String s){
startXText.setText(s);
}

public void setStartYText(String s){
startYText.setText(s);
}

public void setRowsText(String s){
rowsText.setText(s);
}

public void resetPatternString(){
String name = nameField.getText();
String author = authorField.getText();
String width = widthSpinner.getValue().toString();
String height = heightSpinner.getValue().toString();
String startX= startXText.getText();
String startY= startYText.getText();
String rows = rowsText.getText();
patternStringText.setText(name+":"+author+":"+width+":"+height+":"+startX+":"+startY+":"+rows);
}


}