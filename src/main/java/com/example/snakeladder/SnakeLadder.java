package com.example.snakeladder;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;


import java.io.IOException;

public class SnakeLadder extends Application {

    public static final int tileSize=40,height=10,width=10;
    int lowerLine=tileSize*height-7;
    int diceValue;

    Label rollDiceValueLabel;
    Button startGameButton;
    boolean firstPlayerTurn=true,secondPlayerTurn=false,gameStarted=false;

    Player firstPlayer=new Player(tileSize, Color.BLACK,"Manu");
    Player secondPlayer=new Player(tileSize-10, Color.WHITE,"Anish");


    Pane createContent(){

        Pane root=new Pane();
       root.setPrefSize(width*tileSize,height*tileSize+100);

        for(int i=0;i<width;i++){
            for(int j=0;j<height;j++){
                Tile tile=new Tile(tileSize);
                tile.setTranslateX(j*tileSize);
                tile.setTranslateY(i*tileSize);
                root.getChildren().add(tile);
            }
        }

        Image img=new Image("C:\\Users\\thede\\IdeaProjects\\SnakeLadder\\src\\main\\snakes-ladders-board-game-cartoon-600w-442838617 (1).jpg");
        ImageView boardImage=new ImageView();
        boardImage.setImage(img);
        boardImage.setFitWidth(tileSize*width);
        boardImage.setFitHeight(tileSize*height);

        Button playerOneButton=new Button("Player One");
        playerOneButton.setTranslateX(30);
        playerOneButton.setTranslateY(lowerLine+20);
        playerOneButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (gameStarted) {
                    if (firstPlayerTurn) {
                        setDiceValue();
                        firstPlayer.movePlayer(diceValue);
                        if (firstPlayer.playerWon() != null) {
                            rollDiceValueLabel.setText(firstPlayer.playerWon());
                            rollDiceValueLabel.setTranslateX(130);

                        }
                        firstPlayerTurn = false;
                        secondPlayerTurn = true;
                    }
                }
            }

        });
        Button playerTwoButton=new Button("Player Two");
        playerTwoButton.setTranslateX(300);
        playerTwoButton.setTranslateY(lowerLine+20);
        playerTwoButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (gameStarted) {
                    if (secondPlayerTurn) {
                        setDiceValue();
                        secondPlayer.movePlayer(diceValue);
                        if (secondPlayer.playerWon() != null) {
                            rollDiceValueLabel.setText(secondPlayer.playerWon());
                            rollDiceValueLabel.setTranslateX(130);
                            firstPlayerTurn=true;
                            secondPlayerTurn=false;
                            gameStarted=false;
                            startGameButton.setDisable(false);
                            startGameButton.setText("Ongoing Game");
                        }
                        secondPlayerTurn = false;
                        firstPlayerTurn = true;
                    }
                }
            }
        });

        startGameButton=new Button("Start");
        startGameButton.setTranslateX(170);
        startGameButton.setTranslateY(lowerLine+60);
        startGameButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gameStarted=true;
                startGameButton.setText("Ongoing Game");
                startGameButton.setTranslateX(145);
                startGameButton.setTranslateY(lowerLine+60);
                startGameButton.setDisable(true);
            }
        });


        rollDiceValueLabel=new Label("Start the Game");
        rollDiceValueLabel.setTranslateX(150);
        rollDiceValueLabel.setTranslateY(lowerLine+25);


        root.getChildren().addAll(boardImage,playerOneButton,playerTwoButton,
                firstPlayer.getCoin(),rollDiceValueLabel,startGameButton,secondPlayer.getCoin());

        return root;
    }

        private void setDiceValue(){
        diceValue=(int)(Math.random()*6+1);
        rollDiceValueLabel.setText("Dice Value :"+diceValue);

        }
    @Override
    public void start(Stage stage) throws IOException {
       // FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(createContent());
        stage.setTitle("Snake & Ladder!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}