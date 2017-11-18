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

public class PatternLoader {



//  C:/Users/TSwain/java/tick4/Input.txt

public static List<Pattern> loadFromURL(String url) throws IOException, PatternFormatException {

try {
URL destination = new URL(url);
URLConnection conn = destination.openConnection();
return load(new InputStreamReader(conn.getInputStream()));}
catch (IOException error) {throw new IOException();}
}



public static List<Pattern> loadFromDisk(String filename) throws IOException, PatternFormatException {

try {
return load(new FileReader(filename));}
catch (IOException error) {throw new IOException();}
}

public static List<Pattern> load(Reader r) throws IOException, PatternFormatException{

BufferedReader buff = new BufferedReader(r);
List<Pattern> resultList = new LinkedList<Pattern>();

while (buff.readLine()!= null) {
try{

String patternstring = buff.readLine();
Pattern p = new Pattern (patternstring);
resultList.add(p);}
catch (PatternFormatException e) {continue;}
catch (ArrayIndexOutOfBoundsException e) {continue;}
}

if (resultList ==null) {System.out.println("Input file contains no valid pattern Strings");}
return resultList;

//TODO: Complete the implementation of this method.

}


}



