package project00_2;

/**
 * <p>
 * The {@code Coordinate} class represents coordinate
 * of an object onto a grid. The class includes methods
 * for getting or setting the row of an object
 * in a grid, for getting or setting the column 
 * of an object in a grid and for getting and
 * setting a {@code char} to represent the 
 * {@code Coordinate} object.
 * 
 * @author Arnav Singhania
 *
 */

public class Coordinate {
	
	private int row, col;
	private char coordinateValue; 
	
	/**
	 * Instantiates an empty 
	 * {@code Coordinate} object.
	 */
	public Coordinate() {}; 
	
	/**
	 * Instantiate a {@code Coordinate}
	 * object setting its row, column and
	 * coordinateValue using the inputs.
	 * 
	 * @param row An integer representing the
	 * 		  	  the row in the grid where the object
	 * 			  is placed.
	 * @param col An integer representing the
	 * 		  	  the column in the grid where the object
	 * 			  is placed.
	 * @param coordinateValue A char representing the
	 * 		  			      the coordinateValue for 
	 * 						 {@code Coordinate} object.
	 */
	public Coordinate(int row, int col, char coordinateValue) {
		
		this.row = row;
		this.col = col;
		this.coordinateValue = coordinateValue;
		
	}
	
	/**
	 * Sets the row of the 
	 * {@code Coordinate} object
	 * to the input integer.
	 * 
	 * @param row An integer representing the
	 * 		  	  the row in the grid where the object
	 * 			  is placed.
	 */
	public void setRow(int row) {
		
		this.row = row;
		
	}
	
	/**
	 * Sets the column of the 
	 * {@code Coordinate} object
	 * to the input integer.
	 * 
	 * @param col An integer representing the
	 * 		  	  the column in the grid where the object
	 * 			  is placed.
	 */
	public void setCol(int col) {
		
		this.col = col;
		
	}
	
	/**
	 * Sets the coordinateValue of the 
	 * {@code Coordinate} object
	 * to the input char.
	 * 
	 * @param coordinateValue A char representing the
	 * 		  			     the coordinateValue for 
	 * 					     {@code Coordinate} object.
	 */
	public void setCoordinateValue(char coordinateValue) {
		
		this.coordinateValue = coordinateValue;
		
	}
	
	/**
	 * Finds and returns the row of the
	 * {@code Coordinate} object.
	 * 
	 * @return An integer representing the row 
	 * 		   in which the object in placed.
	 */
	public int getRow() {
		
		return row;
		
	}
	
	/**
	 * Finds and returns the column of the
	 * {@code Coordinate} object.
	 * 
	 * @return An integer representing the column
	 * 		   in which the object in placed.
	 */
	public int getCol() {
		
		return col;
		
	}
	
	/**
	 * Finds and returns the coordinateValue of the
	 * {@code Coordinate} object.
	 * 
	 * @return A char representing the 
	 * 		   coordinateValue for the object.	   
	 */
	public char getCoordinateValue() {
		
		return coordinateValue;
		
	}
	
}
