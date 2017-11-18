import javax.swing.JFrame; 
import java.awt.event.ActionListener;
import javax.swing.JLabel; 
import java.awt.event.ActionEvent;
import javax.swing.JButton; 
import javax.swing.BoxLayout;

public class HelloActionWorld2 extends JFrame {


HelloActionWorld2() {
super("Hello Action"); 
setDefaultCloseOperation(EXIT_ON_CLOSE);
setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));
final JLabel label;
label = new JLabel("Button pressed 0 times"); 
add(label); 
JButton button = new JButton("Press me");
add(button); 

//create an instance of an anonymous inner class to hand the event

button.addActionListener(new ActionListener(){
int count = 0;
public void actionPerformed(ActionEvent e) {
count++;
String countString = Integer.toString(count);
label.setText("Button pressed "+countString+" times");
}
});
setSize(320,240); //set size of window
}

public static void main(String[] args) {
HelloActionWorld2 hello = new HelloActionWorld2();
hello.setVisible(true); 
}

}