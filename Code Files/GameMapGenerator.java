package project00_2; 

import java.util.Random;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.media.AudioClip;

/**
 * <p>
 * The {@code GameMapGenerator} class is used
 * to control the program. It generates an array
 * of objects of {@code Coordinate} class, which is 
 * used to generate an array of objects of
 * {@code Button} class which is then used to generate 
 * an object of the {@code GridPane} class representing
 * the game. This class also contains methods to make
 * the player move and handle the mouse events.
 * 
 * @author Arnav Singhania
 */
public class GameMapGenerator {
	
	private int rowColSpan;
	private Button[][] buttonArray;
	private Coordinate[][] coordinateArray;
	private GridPane gridPane = new GridPane();
	private int playerRow, playerCol;
	private int charges = 3;
	private int godzillaAliveNum;
	private boolean win = false;
	
	/**
	 * Create a GridPane object of the required
	 * size and then returns it.
	 * 
	 * @param i An integer value representing
	 * 		    the size of the grid.
	 * @return	A GridPane object representing
	 * 			the main game board.
	 */
	public GridPane begin(int i) {
		
		this.setRowColSpan(i);
		
		coordinateArray = new Coordinate[rowColSpan+2][rowColSpan+2];
		
		coordinateArray = makeBorders(coordinateArray);
		
		coordinateArray = makeCentralBoard(coordinateArray);
		
		buttonArray = new Button[rowColSpan+2][rowColSpan+2];
		
		buttonArray = setUpButtons(buttonArray, coordinateArray);
		
		gridPane = gridpaneGenerator(gridPane, buttonArray);
		
		godzillaAliveNum = Main.godzillaNum;
		
		return gridPane;
		
	}
	
	/**
	 * Generates an object of the class
	 * {@code GridPane} and then return it.
	 * 
	 * @param gridPane An object of class {@code GridPane} representing
	 * 			       the main game board.
	 * @param buttonArray An array of objects of class {@code Button}.
	 * 
	 * @return A GridPane object representing
	 * 		   the main game board.
	 */
	public GridPane gridpaneGenerator(GridPane gridPane, Button[][] buttonArray) {
		
		for (int a = 0; a < buttonArray.length; a++) {
			
			for (int b = 0; b < buttonArray[a].length; b++) {
				
				buttonArray[a][b].setPrefSize(150, 150);
				GridPane.setConstraints(buttonArray[a][b], b, a);
				gridPane.getChildren().add(buttonArray[a][b]);
								
			}
			
		}
		
		return gridPane;
	}

	/**
	 * Generates an array of objects of the class
	 * {@code Button} and then return it.
	 * 
	 * @param buttonArray An array of objects of class {@code Button}.
	 * @param coordinateArray An array of objects of class {@code Coordinate}.
	 * @return An array of objects of class {@code Button}.
	 */
	public Button[][] setUpButtons(Button[][] buttonArray, Coordinate[][] coordinateArray) {
		
		for (int a = 0; a < coordinateArray.length; a++) {
			
			for (int b = 0; b < coordinateArray[a].length; b++) {
				
				if (coordinateArray[a][b].getCoordinateValue() == 'W') {
					
					buttonArray[a][b] = new Button(""); //wall
					buttonArray[a][b].setStyle("-fx-background-color : #000000;-fx-border-color: blue;");
					
				} else if (coordinateArray[a][b].getCoordinateValue() == 'P') {
					
					buttonArray[a][b] = new Button(""); 
					
					buttonArray[a][b].setStyle("-fx-background-color : #BFBFBF;-fx-border-color: blue;");
					
					Image image = new Image(Main.class.getResource("Iron-Man-PNG-Pic.png").toExternalForm(), 100, 100, true, true);
					
					ImageView imageView = new ImageView(image);

					imageView.setFitWidth(35);

					imageView.setFitHeight(35);

					buttonArray[a][b].setGraphic(imageView);
					
					if (coordinateArray[a+1][b].getCoordinateValue() == 'R' || coordinateArray[a-1][b].getCoordinateValue() == 'R' || coordinateArray[a][b+1].getCoordinateValue() == 'R' || coordinateArray[a][b-1].getCoordinateValue() == 'R') {
						Main.infoLabel.setText("Dont fall down!!");
					};
					
					if (coordinateArray[a+1][b].getCoordinateValue() == 'J' || coordinateArray[a-1][b].getCoordinateValue() == 'J' || coordinateArray[a][b+1].getCoordinateValue() == 'J' || coordinateArray[a][b-1].getCoordinateValue() == 'J') {
						Main.infoLabel.setText("Your death is near");
					};
					
					if (coordinateArray[a+1][b].getCoordinateValue() == 'G' || coordinateArray[a-1][b].getCoordinateValue() == 'G' || coordinateArray[a][b+1].getCoordinateValue() == 'G' || coordinateArray[a][b-1].getCoordinateValue() == 'G') {
						Main.infoLabel.setText("Do you hear the screams?");
					};
					
					
				} else if (coordinateArray[a][b].getCoordinateValue() == 'L') {
					
					buttonArray[a][b] = new Button(""); 
					
					buttonArray[a][b].setStyle("-fx-background-color : #BFBFBF;-fx-border-color: blue;");
					
					Image image = new Image(Main.class.getResource("blood_PNG6151.png").toExternalForm(), 100, 100, true, true);
					
					ImageView imageView = new ImageView(image);

					imageView.setFitWidth(35);

					imageView.setFitHeight(35);

					buttonArray[a][b].setGraphic(imageView);
					
				} else if (coordinateArray[a][b].getCoordinateValue() == 'G'){
					
					buttonArray[a][b] = new Button(""+a+","+b); //empty
					buttonArray[a][b].setStyle("-fx-background-color : #BFBFBF;-fx-border-color: blue; -fx-text-fill : #BFBFBF");
					buttonArray[a][b].setId("G");
					buttonArray[a][b].setOnMouseClicked(mouseEventHandler);
					
				} else {
					
					buttonArray[a][b] = new Button(""); //empty
					buttonArray[a][b].setStyle("-fx-background-color : #BFBFBF;-fx-border-color: blue;");
					buttonArray[a][b].setOnMouseClicked(mouseEventHandler);
					
				} 
				
			}
			
		}
		
		return buttonArray;
		
	}
	
	/**
	 * Handles all the mouse events
	 * arising in the game.
	 */
	EventHandler<MouseEvent> mouseEventHandler = new EventHandler<MouseEvent>()  {
		
		AudioClip fireSound = new AudioClip(getClass().getResource("Laser Blasts-SoundBible.com-108608437.mp3").toExternalForm());
		
		@Override
		public void handle( final MouseEvent mouseEvent ) {
			
			Object obj = mouseEvent.getSource();  

			if ( obj instanceof Button ) {

				if((GridPane.getRowIndex((Node) obj) - playerRow >= -1 && GridPane.getRowIndex((Node) obj) - playerRow <= 1) &&
						(GridPane.getColumnIndex((Node) obj) - playerCol >= -1 	&&  GridPane.getColumnIndex((Node) obj) - playerCol<= 1)){

					if (charges!=0 && !Main.lost && !win) {
						
						fireSound.play();
						charges--;
						
						Main.bulletLabel.setText("Charges: "+charges);

						if (((Button) obj).getId() == "G") {

							String rowColString = ((Button) obj).getText();
							String[] rowCol = rowColString.split(",");

							int row = Integer.parseInt(rowCol[0]);
							int col = Integer.parseInt(rowCol[1]);

							coordinateArray[row][col].setCoordinateValue('E');
							Main.infoLabel.setText("You just killed a beast");

							godzillaAliveNum--;

							if (godzillaAliveNum == 0) {

								win = true;
								Main.infoLabel.setText("You saved the universe!");
								new Alert(AlertType.INFORMATION, "You saved the universe!").showAndWait();

							}

						}

						if (charges == 0 && godzillaAliveNum > 0) { 

							Main.lost = true;
							coordinateArray[playerRow][playerCol].setCoordinateValue('L');
							Main.infoLabel.setText("You just got burned");
							Main.mainGameBorderPane.setCenter(gridpaneGenerator(gridPane, setUpButtons(buttonArray, coordinateArray)));

						}

						if (((Button) obj).getId() != "G") {

							int godzillaRow = 0;
							int godzillaCol = 0;

							for (int a = 0; a < coordinateArray.length; a++) {

								for (int b = 0; b < coordinateArray[a].length; b++) {


									if (coordinateArray[a][b].getCoordinateValue() == 'G') {

										godzillaRow = a;
										godzillaCol = b;

									}

								}

							}

							Random rand = new Random();

							boolean moved = false;

							while (!moved) {

								int x = rand.nextInt(4);

								if (x == 0) {

									if (coordinateArray[godzillaRow+1][godzillaCol].getCoordinateValue() == 'P' || coordinateArray[godzillaRow+1][godzillaCol].getCoordinateValue() == 'E') {

										if (coordinateArray[godzillaRow+1][godzillaCol].getCoordinateValue() == 'P') {

											Main.lost = true;
											coordinateArray[playerRow][playerCol].setCoordinateValue('L');
											Main.infoLabel.setText("You just got burned");
											Main.mainGameBorderPane.setCenter(gridpaneGenerator(gridPane, setUpButtons(buttonArray, coordinateArray)));

										} else {

											coordinateArray[godzillaRow+1][godzillaCol].setCoordinateValue('G');
											coordinateArray[godzillaRow][godzillaCol].setCoordinateValue('E');
											moved = true;
										}

									}
								}

								else if (x == 1) {



									if (coordinateArray[godzillaRow][godzillaCol+1].getCoordinateValue() == 'P' || coordinateArray[godzillaRow][godzillaCol+1].getCoordinateValue() == 'E') {


										if (coordinateArray[godzillaRow][godzillaCol+1].getCoordinateValue() == 'P') {

											Main.lost = true;
											coordinateArray[playerRow][playerCol].setCoordinateValue('L');
											Main.infoLabel.setText("You just got burned");
											Main.mainGameBorderPane.setCenter(gridpaneGenerator(gridPane, setUpButtons(buttonArray, coordinateArray)));

										} else {

											coordinateArray[godzillaRow][godzillaCol+1].setCoordinateValue('G');
											coordinateArray[godzillaRow][godzillaCol].setCoordinateValue('E');
											moved = true;

										}

									}
								}

								else if (x == 2) {

									if (coordinateArray[godzillaRow-1][godzillaCol].getCoordinateValue() == 'P' || coordinateArray[godzillaRow-1][godzillaCol].getCoordinateValue() == 'E') {


										if (coordinateArray[godzillaRow-1][godzillaCol].getCoordinateValue() == 'P') {

											Main.lost = true;
											coordinateArray[playerRow][playerCol].setCoordinateValue('L');
											Main.infoLabel.setText("You just got burned");
											Main.mainGameBorderPane.setCenter(gridpaneGenerator(gridPane, setUpButtons(buttonArray, coordinateArray)));

										} else {

											coordinateArray[godzillaRow-1][godzillaCol].setCoordinateValue('G');
											coordinateArray[godzillaRow][godzillaCol].setCoordinateValue('E');
											moved = true;

										}

									}
								}

								if (x == 3) {

									if (coordinateArray[godzillaRow][godzillaCol-1].getCoordinateValue() == 'P' || coordinateArray[godzillaRow][godzillaCol-1].getCoordinateValue() == 'E') {


										if (coordinateArray[godzillaRow][godzillaCol-1].getCoordinateValue() == 'P') {

											Main.lost = true;
											coordinateArray[playerRow][playerCol].setCoordinateValue('L');
											Main.infoLabel.setText("You just got burned");
											Main.mainGameBorderPane.setCenter(gridpaneGenerator(gridPane, setUpButtons(buttonArray, coordinateArray)));

										} else {
											coordinateArray[godzillaRow][godzillaCol-1].setCoordinateValue('G');
											coordinateArray[godzillaRow][godzillaCol].setCoordinateValue('E');
											moved = true;
										}

									}
								}



							}

						}


					}

				}
			}

		}

	};

	private Coordinate[][] makeCentralBoard(Coordinate[][] coordinateArray) { 
		
		int counter = 0;
		
		int playerCounter = 0;
		
		for (int a = 1; a < coordinateArray.length-1; a++) {
			
			for (int b = 1; b < coordinateArray[a].length-1; b++) {
				
				coordinateArray[a][b] = new Coordinate(a,b,'E');
			
			}
					
		}
		
		while (counter < Main.godzillaNum) {
			
			int row = 1 + (int)(Math.random() * (((coordinateArray.length-1) - 1) + 1)); 
			int col = 1 + (int)(Math.random() * (((coordinateArray.length-1) - 1) + 1));
			
			
			if(coordinateArray[row][col].getCoordinateValue() == 'E' && playerCounter == 0) {

				coordinateArray[row][col].setCoordinateValue('P');
				
				playerRow = row; playerCol = col;

				playerCounter++;

			} else if (coordinateArray[row][col].getCoordinateValue() == 'E' && playerCounter != 0) {
				
				coordinateArray[row][col].setCoordinateValue('G');

				counter++;
				
			}
			
		}
		
		counter = 0;
		
		while (counter < Main.rockNum) {
			
			int row = 1 + (int)(Math.random() * (((coordinateArray.length-1) - 1) + 1)); 
			int col = 1 + (int)(Math.random() * (((coordinateArray.length-1) - 1) + 1));
			
			if(coordinateArray[row][col].getCoordinateValue() == 'E') {

				coordinateArray[row][col].setCoordinateValue('R');

				counter++;

			}
			
		}
		
		counter = 0;
		
		while (counter < Main.ammoNum) {
			
			int row = 1 + (int)(Math.random() * (((coordinateArray.length-1) - 1) + 1)); 
			int col = 1 + (int)(Math.random() * (((coordinateArray.length-1) - 1) + 1));
			
			if(coordinateArray[row][col].getCoordinateValue() == 'E') {

				coordinateArray[row][col].setCoordinateValue('A');

				counter++;

			}
			
		}
		
		counter = 0;
		
		while (counter < Main.jokerNum) {
			
			int row = 1 + (int)(Math.random() * (((coordinateArray.length-1) - 1) + 1)); 
			int col = 1 + (int)(Math.random() * (((coordinateArray.length-1) - 1) + 1));
			
			if(coordinateArray[row][col].getCoordinateValue() == 'E') {

				coordinateArray[row][col].setCoordinateValue('J');

				counter++;

			}
			
		}
		
		return coordinateArray;
			
	}

	/**
	 * Finds and returns an integer representing
	 * the size of the grid.
	 * 
	 * @return An integer representing
	 * 		   the size of the grid.
	 */
	public int getRowColSpan() {
		return rowColSpan;
	}

	/**
	 * Sets the rowColSpan variable
	 * representing the size of the grid
	 * to the input number.
	 * 
	 * @param i An integer representing
	 * 		    the size of the grid.
	 */
	public void setRowColSpan(int i) {
		this.rowColSpan = i;
	}
		
	private Coordinate[][] makeBorders(Coordinate[][] coordinateArray) {
		
		for (int a = 0; a < coordinateArray.length; a++) { 
			
			coordinateArray[0][a] = new Coordinate(0,a,'W');
			coordinateArray[coordinateArray.length-1][a] = new Coordinate(rowColSpan+1, a, 'W');
			
			coordinateArray[a][0] = new Coordinate(a,0,'W');
			coordinateArray[a][coordinateArray.length-1] = new Coordinate(a,rowColSpan+1, 'W');
			
		}
		
		return coordinateArray;

	}
	
	/**
	 * Exposes the game map whenever
	 * the debug button is clicked.
	 * 
	 * @return  A GridPane object representing
	 * 		    the main game board.
	 */
	public GridPane setOnActionForDebugButton() {
		
		for (int a = 0; a < coordinateArray.length; a++) {

			for (int b = 0; b < coordinateArray[a].length; b++) {

				if (coordinateArray[a][b].getCoordinateValue() == 'W') {

					buttonArray[a][b] = new Button(""); //wall
					buttonArray[a][b].setStyle("-fx-background-color : #000000;-fx-border-color: blue;");

				} else if (coordinateArray[a][b].getCoordinateValue() == 'P') {

					buttonArray[a][b] = new Button(""); //empty
					
					buttonArray[a][b].setStyle("-fx-background-color : #BFBFBF;-fx-border-color: blue;");
					
					Image image = new Image(Main.class.getResource("Iron-Man-PNG-Pic.png").toExternalForm(), 100, 100, true, true);
					
					ImageView imageView = new ImageView(image);

					imageView.setFitWidth(35);

					imageView.setFitHeight(35);

					buttonArray[a][b].setGraphic(imageView);

				} else if (coordinateArray[a][b].getCoordinateValue() == 'J') {

					buttonArray[a][b] = new Button("");
					
					buttonArray[a][b].setStyle("-fx-background-color : #BFBFBF;-fx-border-color: blue;");
				
					Image image = new Image(Main.class.getResource("580b57fbd9996e24bc43c01b.png").toExternalForm(), 100, 100, true, true);
					
					ImageView imageView = new ImageView(image);

					imageView.setFitWidth(35);

					imageView.setFitHeight(35);

					buttonArray[a][b].setGraphic(imageView);

				} else if (coordinateArray[a][b].getCoordinateValue() == 'R') {


					buttonArray[a][b] = new Button("");
					
					buttonArray[a][b].setStyle("-fx-background-color : #BFBFBF;-fx-border-color: blue;");
					
					Image image = new Image(Main.class.getResource("580b585b2edbce24c47b26f5.png").toExternalForm(), 100, 100, true, true);
					
					ImageView imageView = new ImageView(image);

					imageView.setFitWidth(35);

					imageView.setFitHeight(35);

					buttonArray[a][b].setGraphic(imageView);


				} else if (coordinateArray[a][b].getCoordinateValue() == 'G') {

					buttonArray[a][b] = new Button("");
					
					buttonArray[a][b].setStyle("-fx-background-color : #BFBFBF;-fx-border-color: blue;");
					
					Image image = new Image(Main.class.getResource("Godzilla-PNG-File.png").toExternalForm(), 100, 100, true, true);
					
					ImageView imageView = new ImageView(image);

					imageView.setFitWidth(35);

					imageView.setFitHeight(35);

					buttonArray[a][b].setGraphic(imageView);

				} else if (coordinateArray[a][b].getCoordinateValue() == 'A') {
					
					buttonArray[a][b] = new Button("");
					
					buttonArray[a][b].setStyle("-fx-background-color : #BFBFBF;-fx-border-color: blue;");
					
					Image image = new Image(Main.class.getResource("580b585b2edbce24c47b243c.png").toExternalForm(), 100, 100, true, true);
					
					ImageView imageView = new ImageView(image);

					imageView.setFitWidth(35);

					imageView.setFitHeight(35);

					buttonArray[a][b].setGraphic(imageView);

				} else if (coordinateArray[a][b].getCoordinateValue() == 'L') {
					
					buttonArray[a][b] = new Button(""); 
					
					buttonArray[a][b].setStyle("-fx-background-color : #BFBFBF;-fx-border-color: blue;");
					
					Image image = new Image(Main.class.getResource("blood_PNG6151.png").toExternalForm(), 100, 100, true, true);
					
					ImageView imageView = new ImageView(image);

					imageView.setFitWidth(35);

					imageView.setFitHeight(35);

					buttonArray[a][b].setGraphic(imageView);
					
				} else if (coordinateArray[a][b].getCoordinateValue() == 'E') {

					buttonArray[a][b] = new Button("");
					
					buttonArray[a][b].setStyle("-fx-background-color : #BFBFBF;-fx-border-color: blue;");

				} 
			}

		}
		
		return gridpaneGenerator(gridPane, buttonArray);
	}

	/**
	 * Hides the game map whenever
	 * the debug button is turned off.
	 * 
	 * @return A GridPane object representing
	 * 		   the main game board.
	 */
	public GridPane debugButtonOff() {
		
		buttonArray = setUpButtons(buttonArray, coordinateArray);

		return gridpaneGenerator(gridPane, buttonArray);
	}

	/**
	 * Performs an action based on
	 * a specified set of rules whenever
	 * the down key is pressed.
	 * 
	 * @return A GridPane object representing
	 * 		   the main game board.
	 */
	public GridPane downArrowPressed() {
		
		if (coordinateArray[playerRow+1][playerCol].getCoordinateValue() == 'E') {
			
			coordinateArray[playerRow+1][playerCol].setCoordinateValue('P');
			coordinateArray[playerRow][playerCol].setCoordinateValue('E');
			
			playerRow++;
			
		} else if (coordinateArray[playerRow+1][playerCol].getCoordinateValue() == 'A') {
			
			coordinateArray[playerRow+1][playerCol].setCoordinateValue('P');
			coordinateArray[playerRow][playerCol].setCoordinateValue('E');
			charges++;
			playerRow++;
			new Alert(AlertType.INFORMATION, "You just found some ammo!").showAndWait();
			Main.bulletLabel.setText("Charges: "+charges);
			
		} else if (coordinateArray[playerRow+1][playerCol].getCoordinateValue() == 'J') {
			
			coordinateArray[playerRow+1][playerCol].setCoordinateValue('L');
			coordinateArray[playerRow][playerCol].setCoordinateValue('E');
			playerRow++;
			Main.infoLabel.setText("You just got destroyed");
			Main.lost = true;
			
		} else if (coordinateArray[playerRow+1][playerCol].getCoordinateValue() == 'G') {
			
			coordinateArray[playerRow+1][playerCol].setCoordinateValue('L');
			coordinateArray[playerRow][playerCol].setCoordinateValue('E');
			playerRow++;
			Main.infoLabel.setText("You just got destroyed");
			new Alert(AlertType.INFORMATION, "You just got destroyed!").showAndWait();
			Main.lost = true;
			
		} else if (coordinateArray[playerRow+1][playerCol].getCoordinateValue() == 'R') {
			
			coordinateArray[playerRow][playerCol].setCoordinateValue('E');
			
			playerRow = 1 + (int)(Math.random() * ((rowColSpan - 1) + 1));
			playerCol = 1 + (int)(Math.random() * ((rowColSpan - 1) + 1));
			
			char c = coordinateArray[playerRow][playerCol].getCoordinateValue();
			
			coordinateArray[playerRow][playerCol].setCoordinateValue('P');
			
			if (c == 'G' || c == 'J') { Main.lost = true; coordinateArray[playerRow][playerCol].setCoordinateValue('L'); new Alert(AlertType.INFORMATION, "You just got destroyed!").showAndWait();}
			
			if (c == 'A') {charges++;}

		}
		
		checkToChangeInfoLabelText();
		
		return gridpaneGenerator(gridPane, setUpButtons(buttonArray, coordinateArray));
		
	}

	/**
	 * Performs an action based on
	 * a specified set of rules whenever
	 * the left key is pressed.
	 * 
	 * @return A GridPane object representing
	 * 		   the main game board.
	 */
	public GridPane leftArrowPressed() {
		
		if (coordinateArray[playerRow][playerCol-1].getCoordinateValue() == 'E') {
			
			coordinateArray[playerRow][playerCol-1].setCoordinateValue('P');
			coordinateArray[playerRow][playerCol].setCoordinateValue('E');
			
			playerCol--;
			
		} else if (coordinateArray[playerRow][playerCol-1].getCoordinateValue() == 'A') {
			
			coordinateArray[playerRow][playerCol-1].setCoordinateValue('P');
			coordinateArray[playerRow][playerCol].setCoordinateValue('E');
			charges++;
			playerCol--;
			new Alert(AlertType.INFORMATION, "You just found some ammo!").showAndWait();
			Main.bulletLabel.setText("Charges: "+charges);
			
		} else if (coordinateArray[playerRow][playerCol-1].getCoordinateValue() == 'J') {
			
			coordinateArray[playerRow][playerCol-1].setCoordinateValue('L');
			coordinateArray[playerRow][playerCol].setCoordinateValue('E');
			playerCol--;
			Main.lost = true;
			Main.infoLabel.setText("You just got destroyed");
			
		} else if (coordinateArray[playerRow][playerCol-1].getCoordinateValue() == 'G') {
			
			coordinateArray[playerRow][playerCol-1].setCoordinateValue('L');
			coordinateArray[playerRow][playerCol].setCoordinateValue('E');
			playerRow++;
			Main.infoLabel.setText("You just got destroyed");
			new Alert(AlertType.INFORMATION, "You just got destroyed!").showAndWait();
			Main.lost = true;
			
		} else if (coordinateArray[playerRow][playerCol-1].getCoordinateValue() == 'R') {

			coordinateArray[playerRow][playerCol].setCoordinateValue('E');
			
			playerRow = 1 + (int)(Math.random() * ((rowColSpan - 1) + 1));
			playerCol = 1 + (int)(Math.random() * ((rowColSpan - 1) + 1));
			
			char c = coordinateArray[playerRow][playerCol].getCoordinateValue();
			
			coordinateArray[playerRow][playerCol].setCoordinateValue('P');
			
			if (c == 'G' || c == 'J') { Main.lost = true; coordinateArray[playerRow][playerCol].setCoordinateValue('L'); new Alert(AlertType.INFORMATION, "You just got destroyed!").showAndWait();}
			
			if (c == 'A') {charges++;}

		}
		
		checkToChangeInfoLabelText();
		
		return gridpaneGenerator(gridPane, setUpButtons(buttonArray, coordinateArray));
		
		
	}

	/**
	 * Performs an action based on
	 * a specified set of rules whenever
	 * the right key is pressed.
	 * 
	 * @return A GridPane object representing
	 * 		   the main game board.
	 */
	public GridPane rightArrowPressed() {
		
		if (coordinateArray[playerRow][playerCol+1].getCoordinateValue() == 'E') {
			
			coordinateArray[playerRow][playerCol+1].setCoordinateValue('P');
			coordinateArray[playerRow][playerCol].setCoordinateValue('E');
			
			playerCol++;
			
		} else if (coordinateArray[playerRow][playerCol+1].getCoordinateValue() == 'A') {
				
				coordinateArray[playerRow][playerCol+1].setCoordinateValue('P');
				coordinateArray[playerRow][playerCol].setCoordinateValue('E');
				
				playerCol++;
				charges++;
				new Alert(AlertType.INFORMATION, "You just found some ammo!").showAndWait();
				Main.bulletLabel.setText("Charges: "+charges);
			
		} else if (coordinateArray[playerRow][playerCol+1].getCoordinateValue() == 'J') {
			
			coordinateArray[playerRow][playerCol+1].setCoordinateValue('L');
			coordinateArray[playerRow][playerCol].setCoordinateValue('E');
			
			playerCol++;
			Main.lost = true;
			Main.infoLabel.setText("You just got destroyed");
		
		} else if (coordinateArray[playerRow][playerCol+1].getCoordinateValue() == 'G') {
			
			coordinateArray[playerRow][playerCol+1].setCoordinateValue('L');
			coordinateArray[playerRow][playerCol].setCoordinateValue('E');
			playerRow++;
			Main.infoLabel.setText("You just got destroyed");
			new Alert(AlertType.INFORMATION, "You just got destroyed!").showAndWait();
			Main.lost = true;
			
		}  else if (coordinateArray[playerRow][playerCol+1].getCoordinateValue() == 'R') {

			coordinateArray[playerRow][playerCol].setCoordinateValue('E');
			
			playerRow = 1 + (int)(Math.random() * ((rowColSpan - 1) + 1));
			playerCol = 1 + (int)(Math.random() * ((rowColSpan - 1) + 1));
			
			char c = coordinateArray[playerRow][playerCol].getCoordinateValue();
			
			coordinateArray[playerRow][playerCol].setCoordinateValue('P');
			
			if (c == 'G' || c == 'J') { Main.lost = true; coordinateArray[playerRow][playerCol].setCoordinateValue('L'); new Alert(AlertType.INFORMATION, "You just got destroyed!").showAndWait();}
			
			if (c == 'A') {charges++;}
		}
		
		checkToChangeInfoLabelText();
		
		return gridpaneGenerator(gridPane, setUpButtons(buttonArray, coordinateArray));
		
	}

	/**
	 * Performs an action based on
	 * a specified set of rules whenever
	 * the up key is pressed.
	 * 
	 * @return A GridPane object representing
	 * 		   the main game board.
	 */
	public GridPane upArrowPressed() {
		
		if (coordinateArray[playerRow-1][playerCol].getCoordinateValue() == 'E') {
			
			coordinateArray[playerRow-1][playerCol].setCoordinateValue('P');
			coordinateArray[playerRow][playerCol].setCoordinateValue('E');
			
			playerRow--;
			
		} else if (coordinateArray[playerRow-1][playerCol].getCoordinateValue() == 'A') {
			
			coordinateArray[playerRow-1][playerCol].setCoordinateValue('P');
			coordinateArray[playerRow][playerCol].setCoordinateValue('E');
			
			playerRow--;
			charges++;
			new Alert(AlertType.INFORMATION, "You just found some ammo!").showAndWait();
			Main.bulletLabel.setText("Charges: "+charges);
			
		} else if (coordinateArray[playerRow-1][playerCol].getCoordinateValue() == 'J') {
			
			coordinateArray[playerRow-1][playerCol].setCoordinateValue('L');
			coordinateArray[playerRow][playerCol].setCoordinateValue('E');
			
			playerRow--;
			Main.lost = true;
			Main.infoLabel.setText("You just got destroyed");
			
		} else if (coordinateArray[playerRow-1][playerCol].getCoordinateValue() == 'G') {
			
			coordinateArray[playerRow-1][playerCol].setCoordinateValue('L');
			coordinateArray[playerRow][playerCol].setCoordinateValue('E');
			
			playerRow--;
			Main.lost = true;
			Main.infoLabel.setText("You just got destroyed");
			new Alert(AlertType.INFORMATION, "You just got destroyed!").showAndWait();
			
		} else if (coordinateArray[playerRow-1][playerCol].getCoordinateValue() == 'R') {

			coordinateArray[playerRow][playerCol].setCoordinateValue('E');
			
			playerRow = 1 + (int)(Math.random() * ((rowColSpan - 1) + 1));
			playerCol = 1 + (int)(Math.random() * ((rowColSpan - 1) + 1));
			
			char c = coordinateArray[playerRow][playerCol].getCoordinateValue();
			
			coordinateArray[playerRow][playerCol].setCoordinateValue('P');
			
			if (c == 'G' || c == 'J') { Main.lost = true; coordinateArray[playerRow][playerCol].setCoordinateValue('L'); new Alert(AlertType.INFORMATION, "You just got destroyed!").showAndWait();}
			
			if (c == 'A') {charges++;}
		}
		
		checkToChangeInfoLabelText();
		
		return gridpaneGenerator(gridPane, setUpButtons(buttonArray, coordinateArray));
		
	}

	private void checkToChangeInfoLabelText() {
		
		if (coordinateArray[playerRow-1][playerCol].getCoordinateValue() != 'J' || coordinateArray[playerRow-1][playerCol].getCoordinateValue() != 'J' || coordinateArray[playerRow][playerCol+1].getCoordinateValue() != 'J' || coordinateArray[playerRow][playerCol-1].getCoordinateValue() != 'J') {
			
			Main.infoLabel.setText("");
		
		}
		
		if (coordinateArray[playerRow-1][playerCol].getCoordinateValue() != 'G' || coordinateArray[playerRow-1][playerCol].getCoordinateValue() != 'G' || coordinateArray[playerRow][playerCol+1].getCoordinateValue() != 'G' || coordinateArray[playerRow][playerCol-1].getCoordinateValue() != 'G') {
			
			Main.infoLabel.setText("");
		
		}
			
	}

	/**
	 * Finds and returns the
	 * number of charges remaining.
	 * 
	 * @return An integer representing 
	 * 		   the number of charges.
	 */
	public int getCharges() {return charges;}
	
	/**
	 * Sets the charges variable indicating
	 * number of remaining charges to the input
	 * integer.
	 * 
	 * @param charges An integer representing 
	 * 		  		  the number of charges.
	 */
	public void setCharges(int charges) {this.charges = charges;};
	
	/**
	 * Generates a {@code String} object
	 * representing the game map and
	 * returns it.
	 * 
	 * @return A {@code String} object representing
	 * 		   the game map.
	 */
	public String getOutputStringForFile() {
		
		String output = "";
		
		output+= playerRow +" " +playerCol+ " " + charges+" "+ godzillaAliveNum;
		output+= '\n';
		
		for (int a = 0; a < coordinateArray.length; a++) {
			
			for (int b = 0; b < coordinateArray[a].length; b++) {
				
				output += coordinateArray[a][b].getCoordinateValue()+" ";
				
			}
			
			output += '\n';
			
		}
		
		return output;
		
	}

	/**
	 * Sets the winStatus variable indicating
	 * whether player won the game or not
	 * to the input boolean value.
	 * 
	 * @param winStatus A boolean variable 
	 * 				    representing whether the player
	 * 					won or not.
	 */
	public void setWinStatus(boolean winStatus) {this.win = winStatus;}
	
	/**
	 * Finds and returns a boolean variable
	 * indicating whether player won or not.
	 * 
	 * @return A boolean representing
	 * 		   whether the player won 
	 * 		   or not.
	 */
	public boolean getWinStatus() {return this.win;}
	
	/**
	 * Finds and returns an array of objects
	 * of class{@code Button}
	 * 
	 * @return An array of objects
	 * 		   of class{@code Button}
	 */
	public Button[][] getButtonArray() {
		return buttonArray;
	}

	/**
	 * Sets the buttonArray variable
	 * comprising of an array of objects of class
	 * {@code Button} to the incoming array of {@code Buttons}.
	 * 
	 * @param buttonArray An array of objects of class
	 *					 {@code Button}.
	 */
	public void setButtonArray(Button[][] buttonArray) {
		this.buttonArray = buttonArray;
	}

	/**
	 * Finds and returns an array of objects
	 * of class {@code Coordinate}.
	 * 
	 * @return An array of objects
	 * 		   of class {@code Coordinate}.
	 */
	public Coordinate[][] getCoordinateArray() {
		return coordinateArray;
	}

	/**
	 * Sets the coordinateArray variable
	 * comprising of an array of objects of class
	 * {@code Coordinate} to the incoming array of {@code Coordinate}.
	 * 
	 * @param coordinateArray an array of objects of class
	 * 						 {@code Coordinate}.
	 */
	public void setCoordinateArray(Coordinate[][] coordinateArray) {
		this.coordinateArray = coordinateArray;
	}

	/**
	 * Finds and returns an object of
	 * the class {@code GridPane}.
	 * 
	 * @return An object of the class {@code GridPane}.
	 */
	public GridPane getGridPane() {
		return gridPane;
	}

	/**
	 * Sets the gridPane variable representing 
	 * an object of the class {@code GridPane}
	 * to the incoming variable of class {@code GridPane}.
	 * 
	 * @param gridPane An object of the class {@code GridPane}.
	 */
	public void setGridPane(GridPane gridPane) {
		this.gridPane = gridPane;
	}

	/**
	 * Finds and returns the row 
	 * the player occupies.
	 * 
	 * @return An integer representing the row 
	 * 		   in which the player in present.
	 */
	public int getPlayerRow() {
		return playerRow;
	}

	/**
	 * Sets the playerRow variable representing
	 * the row in which the player is present to the
	 * incoming integer.
	 * 
	 * @param playerRow An integer value representing
	 * 				    the row occupied by the player.
	 */
	public void setPlayerRow(int playerRow) {
		this.playerRow = playerRow;
	}

	/**
	 * Finds and returns the column 
	 * the player occupies.
	 * 
	 * @return An integer representing the column 
	 * 		   in which the player in present.
	 */
	public int getPlayerCol() {
		return playerCol;
	}

	/**
	 * Sets the playerCol variable representing
	 * the column in which the player is present to the
	 * incoming integer.
	 * 
	 * @param playerCol An integer value representing
	 * 				    the column occupied by the player.
	 */
	public void setPlayerCol(int playerCol) {
		this.playerCol = playerCol;
	}

	/**
	 * Finds and returns the number of
	 * monsters still alive. 
	 * 
	 * @return An integer representing the 
	 * 		   number of Godzillas alive.
	 */
	public int getGodzillaAliveNum() {
		return godzillaAliveNum;
	}

	/**
	 * Set the godzillaAliveNum variable
	 * representing the number of monsters
	 * alive to the incoming integer.
	 * 
	 * @param godzillaAliveNum An integer value representing
	 * 						  the number of monsters alive.
	 */
	public void setGodzillaAliveNum(int godzillaAliveNum) {
		this.godzillaAliveNum = godzillaAliveNum;
	}

}
