
import java.io.Reader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.LinkedList;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.FileReader;
import java.net.URL;
import java.net.URLConnection; 
import java.io.IOException;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.awt.Graphics;
import java.awt.image.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

class RefactorLife {







public static void play(World inputWorld) throws Exception {
int userResponse = 0;
while (userResponse != 'q') {

Writer w = new OutputStreamWriter(System.out);
inputWorld.print(w);


userResponse = System.in.read();
int step = 0;
inputWorld = inputWorld.nextGeneration(step);
inputWorld.print(w);

}

}




public static void main(String[] args) throws Exception {
try{
if (args.length==1) {

List<Pattern> patternlist = PatternLoader.loadFromDisk(args[0]);
if (patternlist.isEmpty()) {System.out.println ("Input file contains no valid Pattern Strings");}
int listlength = patternlist.size();
int index=0;

for(Pattern p: patternlist) {
String name = p.getName();
String author = p.getAuthor();
int width = p.getWidth();
int height = p.getHeight();
int startcol = p.getStartCol();
int startrow = p.getStartRow();
String cells = p.getCells();
System.out.println (index+") "+name+":"+author+":"+width+":"+height+":"+startcol+":"+startrow+":"+cells);
index=index+1;}

}



else {

String worldType = args.length == 3 ? args[2] : "--array"; 
//if args.length is not 3, have default use of Array to store world.

int patternnumber = Integer.parseInt(args[1]);
List<Pattern> patternlist = PatternLoader.loadFromDisk(args[0]);
Pattern d = patternlist.get(patternnumber);

World world = null;

if (worldType.equals("--array")) {world = new ArrayWorld(d.getHeight(), d.getWidth());} 

else {	if (worldType.equals("--aging")){world = new AgingWorld(d.getHeight(), d.getWidth());}

else {	if (worldType.equals("--long")) {
	world = new PackedWorld(); } 
	else {
	System.out.println("Please type either --array or --long to specify how to store world states.");
	return; 
	}
}
}
d.initialise(world);
play(world);
}

}
catch (IOException error) {System.out.println("Filename format not recognised - please correct.");}


}

}
