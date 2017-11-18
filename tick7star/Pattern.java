public class Pattern {

private String string;
private String name;
private String author;
private int width;
private int height;
private int startCol;
private int startRow;
private String cells;

public String getString(){
return string;
}

public String getName() {
return name;
}

public String getAuthor() {
return author;
}

public int getWidth() {
return width;
}

public int getHeight() {
return height;
}

public int getStartCol() {
return startCol;
}

public int getStartRow() {
return startRow;
}

public String getCells() {
return cells;
}


public void setName(String s) {
name = s;
}

public void setAuthor(String s) {
author = s;
}

public void setWidth(int w) {
width = w;
}

public void setHeight(int h) {
height = h;
}

public void setStartCol(int c) {
startCol = c;
}

public void setStartRow(int r) {
startRow = r;
}

public void setCells(String s) {
cells = s;
}

public Pattern(int w, int h) {
name = null;
author=null;
width=w;
height=h;
startCol=0;
startRow=0;
cells=null;
}

public Pattern(String format) throws PatternFormatException, ArrayIndexOutOfBoundsException, NumberFormatException {
if (format == null) throw new ArrayIndexOutOfBoundsException();
string = format;
String[] formatArray = format.split(":");
if (formatArray.length != 7) throw new PatternFormatException();name = formatArray[0];
author = formatArray[1];
try{
width = Integer.parseInt(formatArray[2]);
height = Integer.parseInt(formatArray[3]);
startCol = Integer.parseInt(formatArray[4]);
startRow = Integer.parseInt(formatArray[5]);}
catch (NumberFormatException error) { throw new NumberFormatException ();}
cells = formatArray[6];
}

public void initialise(World inputWorld) throws PatternFormatException, NumberFormatException  {


String[] cellStrings = cells.split(" ");

int gridWidth = ( (cellStrings[0]).toCharArray() ).length;
int gridHeight = cellStrings.length;


try {for (int b=0; b<gridHeight; b++){int test = Integer.parseInt(cellStrings[b]);}}
catch (NumberFormatException error) { throw new NumberFormatException ();}




char[][] Grid = new char [gridHeight][gridWidth];

for (int i=0; i < gridHeight ; i++) {
Grid [i] = (cellStrings[i]).toCharArray(); }

int[][]intGrid = new int [gridHeight][gridWidth];

try{
for (int j=0; j < gridHeight; j++) {
for (int k=0; k < gridWidth; k++){
intGrid [j][k] = Grid[j][k] - '0';
}}
}
catch (NumberFormatException error) { throw new NumberFormatException ();}


for (int row = startRow; row < (gridHeight+startRow); row++) {
for (int col = startCol; col < (gridWidth+startCol); col++) {
inputWorld.setCell(col,row, intGrid[row - startRow][col-startCol]==1) ;
}}











}
}