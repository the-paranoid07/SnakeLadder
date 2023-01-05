package com.example.snakeladder;

import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.transform.Translate;
import javafx.util.Duration;

public class Player {
    private Circle coin;
    private String name;
    private int coinPoasition;
    private static Board gameBoard=new Board();

    public Player(int tileSize, Color coinColor, String playerNaame){
        coinPoasition=1;
        name=playerNaame;
        coin=new Circle(tileSize/2);
        coin.setFill(coinColor);
        coin.setTranslateX(20);
        coin.setTranslateY(380);
    }

    public void movePlayer(int diceValue){
        if(coinPoasition+diceValue<=100){
            coinPoasition+=diceValue;
//            coin.setTranslateX(gameBoard.getXCoordinate(coinPoasition));
//            coin.setTranslateY(gameBoard.getYCoordinate(coinPoasition));
            translatePlayer();

            int newPosition= gameBoard.getNextPosition(coinPoasition);
            if(newPosition != coinPoasition){
                coinPoasition=newPosition;
                translatePlayer();
            }
        }

    }
    public String playerWon(){
        if(coinPoasition==100){
            return name+" has Won the Game";
        }
        return null;
    }

    private void translatePlayer(){
        TranslateTransition move=new TranslateTransition(Duration.millis(1000),this.coin);
        move.setToX(gameBoard.getXCoordinate(coinPoasition));
        move.setToY(gameBoard.getYCoordinate(coinPoasition));
        move.setAutoReverse(false);
        move.play();
    }
    public Circle getCoin() {
        return coin;
    }

    public int getCoinPoasition() {
        return coinPoasition;
    }

    public String getName() {
        return name;
    }
}
