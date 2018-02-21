package project00_2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.Scanner;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

/**
 * The {@code Main} class gets called
 * when the program runs. It uses
 * javafx and extends Application.
 * It contains methods to set up the 
 * user interface and perform various funtions
 * when certain nodes are clicked.
 * 
 * @author Arnav Singhania
 *
 */
public class Main extends Application {

	private Scene scene;
	private GameMapGenerator gameMapGenerator = new GameMapGenerator();
	private boolean debugButtonStage;
	public static BorderPane mainGameBorderPane = new BorderPane(); /** A static variable representing a BorderPane object.*/
	public static int godzillaNum,rockNum,jokerNum,ammoNum; /** Static integer variables representing number of monsters, rocks, deadly creature and charges.*/
	public static Label infoLabel; /** A static variable representing a Label object used to display warnings to the player.*/
	public static Label bulletLabel; /** A static variable representing a Label object used to display number of charges.*/
	public static boolean lost = false; /** A static variable representing a boolean object keeping track of the lost status.*/
	private AudioClip buttonClickSound = new AudioClip(getClass().getResource("Button Click On-SoundBible.com-459633989.mp3").toExternalForm());
	
	/**
	 * Gets called when program runs and
	 * works to launch the GUI.
	 *  
	 * @param args An Array of Strings.
	 */
	public static void main(String[] args) {
		
		Application.launch(args);
		
	}
	
	/**
	 * Sets up the GUI for the program.
	 * 
	 * @param primaryStage A stage object representing 
	 * 					  the main window of the game.
	 * @throws Exception An object of the class Exception
	 * 					in case any exception is thrown. 
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		primaryStage.setTitle("Monster Hunter");
		
		BorderPane borderPane = new BorderPane();
		
		Label header = new Label("Monster Hunter");

		borderPane.setTop(header);
		
		BorderPane.setAlignment(header, Pos.CENTER);

//------------------------------------------------------------------------------------------
		
		Label sizeSelectionText = new Label("Select the size of the grid: ");
		
		Button sizeButton1 = new Button("5x5");
		Button sizeButton2 = new Button("7x7");
		Button sizeButton3 = new Button("10x10");
		Button loadButton = new Button("Load Game");
		
		loadButton.setPrefSize(470, 40);
	
		sizeButton1.setPrefSize(150, 40); sizeButton2.setPrefSize(150, 40); sizeButton3.setPrefSize(150, 40);
		
		HBox buttonHBox = new HBox();
		buttonHBox.getChildren().addAll(sizeButton1, sizeButton2, sizeButton3);
		buttonHBox.setSpacing(10);
		buttonHBox.setAlignment(Pos.CENTER);
		
		VBox centreSizeSelectorVBox = new VBox();
		centreSizeSelectorVBox.getChildren().addAll(sizeSelectionText, buttonHBox, loadButton);
		
		centreSizeSelectorVBox.setSpacing(15);
		
		centreSizeSelectorVBox.setAlignment(Pos.CENTER);
		
		borderPane.setCenter(centreSizeSelectorVBox);
				
//------------------------------------------------------------------------------------------
		
		Label bottomWarningText = new Label("GAME IS NOT FOR THE WEAK!");
		
		borderPane.setBottom(bottomWarningText);
		
		BorderPane.setAlignment(bottomWarningText, Pos.CENTER);
		
//------------------------------------------------------------------------------------------
	
		scene = new Scene(borderPane,625,625);
		
		scene.getStylesheets().add("style/welcomeScreenDesign.css");
		
		primaryStage.setScene(scene);
		
		primaryStage.show();
		
//-----------------------------------------------------------------------------------------
	
		sizeButton1.setOnAction( e-> {
			
			setOnActionForSizeButton(5);
			buttonClickSound.play();
			
		});
		
		sizeButton2.setOnAction( e-> {
			
			setOnActionForSizeButton(7);
			buttonClickSound.play();
			
		});
		
		sizeButton3.setOnAction( e-> {
			
			setOnActionForSizeButton(10);
			buttonClickSound.play();
			
		});
		
		loadButton.setOnAction(e->{
			
			setOnActionForLoadButton();
			buttonClickSound.play();
			
		});
		
//-----------------------------------------------------------------------------------------
		
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {			
			
			@Override
			public void handle(final KeyEvent keyEvent)  {
				
				if (!lost && !gameMapGenerator.getWinStatus()) {
					
					if (keyEvent.getCode() == KeyCode.DOWN) {
						
						buttonClickSound.play();
						downKeyPressed();

					} else if (keyEvent.getCode() == KeyCode.UP) {
						
						buttonClickSound.play();
						upKeyPressed();

					} else if (keyEvent.getCode() == KeyCode.RIGHT) {
						
						buttonClickSound.play();
						rightKeyPressed();

					} else if (keyEvent.getCode() == KeyCode.LEFT) {
						
						buttonClickSound.play();
						leftKeyPressed();

					}
				
				}
				
				
			}
			
		});

	
	}

	private void setOnActionForLoadButton() {
		
		File file = new File("gameData.txt");
		
		if (!file.exists()) { 
		    
			new Alert(AlertType.INFORMATION, "Saved file not found.").showAndWait();
			return;
			
		}
		
		HBox topHBox = new HBox();
		
		topHBox.setStyle(" -fx-background-color: #092238; -fx-border-color: black; -fx-border-width:2");
		
		Label monsterHunterLabel = new Label("Monster Hunter");
		
		monsterHunterLabel.setStyle("-fx-font-size: 20px;-fx-font-family: \"Luminari\";-fx-text-fill: white;");
		
		
		infoLabel = new Label("Save the World");
		
		infoLabel.setStyle("-fx-font-size: 10px;-fx-font-family: \"Luminari\";-fx-text-fill: white;");
		
		topHBox.setSpacing(100);
		
		
		bulletLabel = new Label("Charges: 3"); 
		
		bulletLabel.setStyle("-fx-font-size: 12px;-fx-font-family: \"Luminari\";-fx-text-fill: white;");


		topHBox.getChildren().addAll(bulletLabel, monsterHunterLabel, infoLabel);
		
		topHBox.setAlignment(Pos.CENTER);
		
		mainGameBorderPane.setTop(topHBox);
		
		//-----------------------------------------------------------------------------------------------------------------------------------------------------------        		

		getInput("gameData.txt"); //sets the central node for the BorderPane

		//-----------------------------------------------------------------------------------------------------------------------------------------------------------     			

		Button saveButton = new Button("Save");
		
		saveButton.setPrefSize(300, 10);
		
		saveButton.setStyle("-fx-text-fill: white;-fx-font-family: \"Arial Narrow\";-fx-font-weight: bold;-fx-background-color: linear-gradient(#61a2b1, #2A5058);-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );");
		
		saveButton.setOnAction(e->{setOnActionForSaveButton();});

		Button debugButton = new Button("Debug Mode");
		
		debugButton.setPrefSize(300, 10);
		
		debugButton.setStyle("-fx-text-fill: white;-fx-font-family: \"Arial Narrow\";-fx-font-weight: bold;-fx-background-color: linear-gradient(#61a2b1, #2A5058);-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );");
		
		debugButtonStage = true;

		debugButton.setOnAction(e->{
			
			if (debugButtonStage) {
				
				mainGameBorderPane.setCenter(gameMapGenerator.setOnActionForDebugButton());
				debugButtonStage = false;
				
			} else {
				
				mainGameBorderPane.setCenter(gameMapGenerator.debugButtonOff());
				debugButtonStage = true;
				
			}
			
		});

		HBox resetDebugButtonsHBox = new HBox();
		
		resetDebugButtonsHBox.setStyle(" -fx-background-color: #092238; -fx-border-color: black; -fx-border-width:2");

		resetDebugButtonsHBox.getChildren().addAll(debugButton,saveButton);

		resetDebugButtonsHBox.setAlignment(Pos.CENTER);

		resetDebugButtonsHBox.setSpacing(35);

		mainGameBorderPane.setBottom(resetDebugButtonsHBox);

		//-----------------------------------------------------------------------------------------------------------------------------------------------------------     			

		scene.setRoot(mainGameBorderPane);
		
		scene.getStylesheets().clear();		
		
	}

	private void getInput(String inputFile) {
		
		try {
			
			Scanner sc = new Scanner(new File(inputFile));
			
			int gridSize = 0;
			
			while(sc.hasNextLine()) {
				
				sc.nextLine();
				gridSize++;
				
			}
			
			sc = new Scanner(new File(inputFile));
			
			gridSize = gridSize - 1;
			
			Coordinate[][] coordinateArray = new Coordinate[gridSize][gridSize];
			
			int row = 0;
			
			int lineNum = 0;
			
			while(sc.hasNextLine()) {
				
				String line = sc.nextLine();
				line =  line.replaceAll("\\s","");
				
				char[] charArray = line.toCharArray();
				
				if (lineNum == 0) {
					
					gameMapGenerator.setPlayerRow(Integer.parseInt(""+charArray[0]));
					gameMapGenerator.setPlayerCol(Integer.parseInt(""+charArray[1]));
					gameMapGenerator.setCharges(Integer.parseInt(""+charArray[2]));
					gameMapGenerator.setGodzillaAliveNum(Integer.parseInt(""+charArray[3]));
					
					lineNum++;
					
				} else if (lineNum > 0){
					
					for (int a = 0; a < charArray.length;a++) {

						coordinateArray[row][a] = new Coordinate(row,a,charArray[a]);

					}
					
					row++;
					
				}
				
			}
			
			sc.close();
			
			Button[][] buttonArray = new Button[gridSize][gridSize];
			
			GridPane gridPane = new GridPane();
			
			gridPane = gameMapGenerator.gridpaneGenerator(gridPane, gameMapGenerator.setUpButtons(buttonArray, coordinateArray));
			
			mainGameBorderPane.setCenter(gridPane);
			
			gameMapGenerator.setRowColSpan(gridSize);
			gameMapGenerator.setButtonArray(buttonArray);
			gameMapGenerator.setCoordinateArray(coordinateArray);
			gameMapGenerator.setGridPane(gridPane);

			System.out.println(gameMapGenerator.getCharges());
			bulletLabel.setText("Charges: "+gameMapGenerator.getCharges());
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	private void rightKeyPressed() {
		
		mainGameBorderPane.setCenter(gameMapGenerator.rightArrowPressed());
		
	}

	private void leftKeyPressed() {
		
		mainGameBorderPane.setCenter(gameMapGenerator.leftArrowPressed());
		
	}

	private void upKeyPressed() {
		
		mainGameBorderPane.setCenter(gameMapGenerator.upArrowPressed());
		
	}

	private void downKeyPressed() {
		
		mainGameBorderPane.setCenter(gameMapGenerator.downArrowPressed());
		
	}

	private void setOnActionForSizeButton(int gridSize) {

		BorderPane setupBorderPane = new BorderPane();
		
		Label header = new Label("Monster Hunter");

		setupBorderPane.setTop(header);
		
		BorderPane.setAlignment(header, Pos.CENTER);
		

		ComboBox<Integer> godzillaNumComboBox = new ComboBox<>();
		godzillaNumComboBox.getItems().addAll(1,2,3,4,5,6,7,8,9,10);
		godzillaNumComboBox.setPromptText("Select number of Monsters");
		godzillaNumComboBox.setPrefSize(265, 40);
		
		
		ComboBox<Integer> ammoNumComboBox = new ComboBox<>();
		ammoNumComboBox.getItems().addAll(1,2,3,4,5,6,7,8,9,10);
		ammoNumComboBox.setPromptText("Select number of ammo boxes");
		ammoNumComboBox.setPrefSize(265, 40);
		
		ComboBox<Integer> rockNumComboBox = new ComboBox<>();
		rockNumComboBox.getItems().addAll(1,2,3,4,5,6,7,8,9,10);
		rockNumComboBox.setPromptText("Select number of obstracles");
		rockNumComboBox.setPrefSize(265, 40);
		
		ComboBox<Integer> jokerNumComboBox = new ComboBox<>();
		jokerNumComboBox.getItems().addAll(1,2,3,4,5,6,7,8,9,10);
		jokerNumComboBox.setPromptText("Select Number of deadly creatures");
		jokerNumComboBox.setPrefSize(265, 40);
		
		
		HBox gameSetupInfoHBox1 = new HBox(); gameSetupInfoHBox1.setSpacing(20);
		HBox gameSetupInfoHBox2 = new HBox(); gameSetupInfoHBox2.setSpacing(20);
		
		gameSetupInfoHBox1.setAlignment(Pos.CENTER);
		gameSetupInfoHBox2.setAlignment(Pos.CENTER);
		
		gameSetupInfoHBox1.getChildren().addAll(godzillaNumComboBox,ammoNumComboBox);
		gameSetupInfoHBox2.getChildren().addAll(rockNumComboBox,jokerNumComboBox);
		
		VBox gameSetupInfoVBox = new VBox();
		gameSetupInfoVBox.getChildren().addAll(gameSetupInfoHBox1,gameSetupInfoHBox2);
		gameSetupInfoVBox.setAlignment(Pos.CENTER);
		
		gameSetupInfoVBox.setSpacing(45);
		
		BorderPane.setAlignment(gameSetupInfoVBox, Pos.CENTER);
		setupBorderPane.setCenter(gameSetupInfoVBox);
		
		
		Button continueButton = new Button("Continue");
		BorderPane.setAlignment(continueButton, Pos.BOTTOM_RIGHT);
		setupBorderPane.setBottom(continueButton);
		continueButton.setPrefSize(150, 50);
		continueButton.setStyle("-fx-text-fill: white;\n" + 
				"    -fx-font-family: \"Arial Narrow\";\n" + 
				"    -fx-font-weight: bold;\n" + 
				"    -fx-background-color: linear-gradient(#61a2b1, #2A5058);\n" + 
				"    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );");
		
		scene.setRoot(setupBorderPane);
		
		continueButton.setOnAction(e->{
			buttonClickSound.play();
			godzillaNum = godzillaNumComboBox.getValue();
			rockNum = rockNumComboBox.getValue();
			jokerNum = jokerNumComboBox.getValue();
			ammoNum = ammoNumComboBox.getValue();
			
			setupVideoScene(gridSize); });

	}

	private void setupVideoScene(int gridSize) {

		//------------------------------------------------------------------------------------------------------------------------------------------------------
		
		StackPane stackPane = new StackPane();

		MediaPlayer player = new MediaPlayer( new Media(getClass().getResource("588680657.mp4").toExternalForm()));
		MediaView mediaView = new MediaView(player);
		
		AudioClip lighteningSound = new AudioClip(getClass().getResource("thunder_sound_FX-Grant_Evans-1523270250.mp3").toExternalForm());
		AudioClip mainGameBackgroundSound = new AudioClip(getClass().getResource("creepy-background-daniel_simon.mp3").toExternalForm());

		//-----------------------------------------------------------------------------------------------------------------------------------------------------------

		Label storyLabel = new Label("A horrifying creature has invaded our planet.\n"+
				"Its up to you to save mankind and hunt the mystical creature down…\n\n\n\n\n"+
				"Press continue to begin.");

		storyLabel.setStyle("   -fx-font-size: 18px;\n" + 
				"   -fx-font-family: \"Luminari\";\n" + 
				"   -fx-text-fill: white;");

		//------------------------------------------------------------------------------------------------------------------------------------------------------------

		Button continueButton = new Button("Continue");

		continueButton.setPrefSize(150, 50);

		continueButton.setStyle("-fx-text-fill: white;\n" + 
				"    -fx-font-family: \"Arial Narrow\";\n" + 
				"    -fx-font-weight: bold;\n" + 
				"    -fx-background-color: linear-gradient(#61a2b1, #2A5058);\n" + 
				"    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );");

		//----------------------------------------------------------------------------------------------------------------------------------------------------------

		StackPane.setAlignment(continueButton, Pos.BOTTOM_RIGHT);

		stackPane.getChildren().addAll(mediaView, storyLabel, continueButton);
		
		scene.setRoot(stackPane);

		player.play();
		
		lighteningSound.play();
		continueButton.setOnAction(e -> { setOnActionForContinueButton(gridSize);buttonClickSound.play();lighteningSound.stop();mainGameBackgroundSound.play();});
		
	}

	private void setOnActionForContinueButton(int gridSize) {
		
		HBox topHBox = new HBox();
		
		topHBox.setStyle(" -fx-background-color: #092238; -fx-border-color: black; -fx-border-width:2");
		
		Label monsterHunterLabel = new Label("Monster Hunter");
		
		monsterHunterLabel.setStyle("-fx-font-size: 20px;-fx-font-family: \"Luminari\";-fx-text-fill: white;");
		
		
		infoLabel = new Label("Save the World");
		
		infoLabel.setStyle("-fx-font-size: 10px;-fx-font-family: \"Luminari\";-fx-text-fill: white;");
		
		topHBox.setSpacing(100);
		
		
		bulletLabel = new Label("Charges: 3"); 
		
		bulletLabel.setStyle("-fx-font-size: 12px;-fx-font-family: \"Luminari\";-fx-text-fill: white;");


		topHBox.getChildren().addAll(bulletLabel, monsterHunterLabel, infoLabel);
		
		topHBox.setAlignment(Pos.CENTER);
		
		mainGameBorderPane.setTop(topHBox);

		//-----------------------------------------------------------------------------------------------------------------------------------------------------------        		

		mainGameBorderPane.setCenter(sizeButtonClicked(gridSize));

		//-----------------------------------------------------------------------------------------------------------------------------------------------------------     			

		Button saveButton = new Button("Save");
		
		saveButton.setPrefSize(300, 10);
		
		saveButton.setStyle("-fx-text-fill: white;-fx-font-family: \"Arial Narrow\";-fx-font-weight: bold;-fx-background-color: linear-gradient(#61a2b1, #2A5058);-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );");
		
		saveButton.setOnAction(e->{setOnActionForSaveButton();buttonClickSound.play();});
		
		Button resetButton = new Button("Reset");
		
		resetButton.setPrefSize(300, 10);
		
		resetButton.setStyle("-fx-text-fill: white;-fx-font-family: \"Arial Narrow\";-fx-font-weight: bold;-fx-background-color: linear-gradient(#61a2b1, #2A5058);-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );");
		
		resetButton.setOnAction(e->{buttonClickSound.play();mainGameBorderPane.setCenter(sizeButtonClicked(gridSize));gameMapGenerator.setCharges(3);bulletLabel.setText("Charges: 3");lost = false;gameMapGenerator.setWinStatus(false);infoLabel.setText("");});

		Button debugButton = new Button("Debug Mode");
		
		debugButton.setPrefSize(300, 10);
		
		debugButton.setStyle("-fx-text-fill: white;-fx-font-family: \"Arial Narrow\";-fx-font-weight: bold;-fx-background-color: linear-gradient(#61a2b1, #2A5058);-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );");
		
		debugButtonStage = true;

		debugButton.setOnAction(e->{
			
			buttonClickSound.play();
			
			if (debugButtonStage) {
				
				mainGameBorderPane.setCenter(gameMapGenerator.setOnActionForDebugButton());
				debugButtonStage = false;
				
			} else {
				
				mainGameBorderPane.setCenter(gameMapGenerator.debugButtonOff());
				debugButtonStage = true;
				
			}
			
		});

		HBox resetDebugButtonsHBox = new HBox();
		
		resetDebugButtonsHBox.setStyle(" -fx-background-color: #092238; -fx-border-color: black; -fx-border-width:2");

		resetDebugButtonsHBox.getChildren().addAll(debugButton,resetButton,saveButton);

		resetDebugButtonsHBox.setAlignment(Pos.CENTER);

		resetDebugButtonsHBox.setSpacing(35);

		mainGameBorderPane.setBottom(resetDebugButtonsHBox);

		//-----------------------------------------------------------------------------------------------------------------------------------------------------------     			

		scene.setRoot(mainGameBorderPane);
		
		scene.getStylesheets().clear();
	
	}

	private void setOnActionForSaveButton() {
		
		final Formatter formatter;
		
		try {
			
			formatter = new Formatter("gameData.txt");
			
			String outputString = gameMapGenerator.getOutputStringForFile();
			
			formatter.format("%s", outputString);
			
			formatter.close();
			
			new Alert(AlertType.INFORMATION, "Game saved").showAndWait();
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
			
		}
		
		
	}
	
	private GridPane sizeButtonClicked(int rowColSpan) { 
		
		GridPane gridPane = gameMapGenerator.begin(rowColSpan);

		return gridPane;

	}


}
