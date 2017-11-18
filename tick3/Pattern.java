public class Pattern {

private String name;
private String author;
private int width;
private int height;
private int startCol;
private int startRow;
private String cells;


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


public Pattern(String format) {

String[] formatArray = format.split(":");
name = formatArray[0];
author = formatArray[1];
width = Integer.parseInt(formatArray[2]);
height = Integer.parseInt(formatArray[3]);
startCol = Integer.parseInt(formatArray[4]);
startRow = Integer.parseInt(formatArray[5]);
cells = formatArray[6];
}


public void initialise(boolean[][] world) {
//TODO: update the values in the 2D array representing the state of "world"
// as expressed by the contents of the field "cells".

String[] cellStrings = cells.split(" ");

int gridWidth = ( (cellStrings[0]).toCharArray() ).length;
int gridHeight = cellStrings.length;


char[][] Grid = new char [gridHeight][gridWidth];

for (int i=0; i < gridHeight ; i++) {
Grid [i] = (cellStrings[i]).toCharArray(); }

int[][]intGrid = new int [gridHeight][gridWidth];


for (int j=0; j < gridHeight; j++) {
for (int k=0; k < gridWidth; k++){
intGrid [j][k] = Grid[j][k] - '0';
}}



for (int row = startRow; row < (gridHeight+startRow); row++) {
for (int col = startCol; col < (gridWidth+startCol); col++) {
world[row][col] = (intGrid[row - startRow][col-startCol]==1);
}}











}
}