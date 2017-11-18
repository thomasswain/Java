import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;

public class HelloActionWorld extends JFrame {

private JLabel label;

private class ButtonAction implements ActionListener {

int count = 0;

public void actionPerformed(ActionEvent e) {
count++;
String countString = Integer.toString(count);
label.setText("Button pressed "+countString+" times");
}

}

HelloActionWorld() {

super("Hello Action"); 
setDefaultCloseOperation(EXIT_ON_CLOSE); 
setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));

label = new JLabel("Button pressed 0 times"); 
add(label); //associate "label" with window

JButton button = new JButton("Press me");
add(button); //associated "button" with window

button.addActionListener(new ButtonAction());

setSize(320,240); 
}

public static void main(String[] args) {
HelloActionWorld hello = new HelloActionWorld();
hello.setVisible(true); 
}

}