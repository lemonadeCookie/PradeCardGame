// File Name:    testCard
// Author:       wang luyao 王璐瑶
// Student Number: 3012218125
// Description:	  The parade has started in Wonderland. 
//				  Everybody is dressed up, the streets are decorated, 
//  			  excitement is in the air. Let’s find more people to join the 
//			      parade. You are one of the organizers of the parade and are 
//			      constantly trying your best to encourage people to join. If 
//				  only the people already taking part didn’t leave so early...
//				  After a short while they simply lose all interest in the 
//				  parade. And if somebody shows up with the same dress or
//				  of higher rank, all they do is leave the parade immediately 
//				  and complain to you. 
// Future Improvements:  a.Make the game can play in the phone/pad
//                       b.Make a web page game vision
//                       c.Put a introduction video
//                       d.Make two vision, one add some AD. but free, the other
//						   no AD. but should pay for it
//                       e.Make it can play with keyboard
//						 f.Make it can play with other people through web 
//						   or play with more people.

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class stage extends Application {

	public AnchorPane root = new AnchorPane();
	// deckPart
	public ImageView deckPart = new ImageView();
	// color&number
	public Image cardImage[][] = new Image[6][11]; // 动态初始化一个二维数组
	// majority list
	public ArrayList<Rectangle> majorityPlayer = new ArrayList<Rectangle>();
	public ArrayList<Rectangle> majorityComputer = new ArrayList<Rectangle>();
	public ArrayList<Integer> majorityNum = new ArrayList<Integer>();
	// store the cards' information list
	public ArrayList<informationForCard> list = new 
												ArrayList<informationForCard>();
	// deck list
	public ArrayList<ImageView> deckList = new ArrayList<ImageView>();
	// store the player's five cards
	public LinkedList<cards> playerList = new LinkedList<cards>();
	// final statement player's four cards
	public LinkedList<cards> newPlayerList = new LinkedList<cards>();
	// store the parade's cards
	public LinkedList<cards> paradeList = new LinkedList<cards>();
	// store the computer's cards
	public LinkedList<cards> computerList = new LinkedList<cards>();
	// save the parade's safe cards
	public ArrayList<Integer> safeArray = new ArrayList<Integer>();
	// save the parade's safe updated cards
	public ArrayList<Integer> updatedArray = new ArrayList<Integer>();
	// player's rest cards' part - 6 list
	public ArrayList<ArrayList<cards>> player = new
												  ArrayList<ArrayList<cards>>();
	// computer's rest cards' part - 6 list
	public ArrayList<ArrayList<cards>> computer = new 
												  ArrayList<ArrayList<cards>>();
	// computer's AI test list
	public ArrayList<ArrayList<cards>> computerTest = new
												  ArrayList<ArrayList<cards>>();
	// try two cards
	public ArrayList<cards> tryTwoCards = new ArrayList<cards>();
	// choose the cards - player last
	public ArrayList<Integer> lastTwoCardsIndex = new ArrayList<Integer>();
	// show number of cards left in the draw deck
	public Text restCount = new Text();
	public StackPane restCountPane = new StackPane();
	// score place
	public Text computerScore = new Text();
	public Text playerScore = new Text();
	// space to show the whole score
	public StackPane computerScorePane = new StackPane();
	public StackPane playerScorePane = new StackPane();
	// score
	public int compScore = 0;
	public int playScore = 0;
	// debug mode sign
	public int debugInt = -1;
	// final score showing
	public Text computerFinalScore = new Text();
	public Text playerFinalScore = new Text();
	// space to show the final score
	public StackPane computerScorePlace = new StackPane();
	public StackPane playerScorePlace = new StackPane();
	// play button
	public Button btn = new Button();
	// sign the card
	public int count = 0;
	// sign the computer clicked card
	int clicked = -1;
	// sign the color & number of player's clicked card
	public int clickColor = -1;
	public int clickNum = 1;
	// the numbers of the cards have been clicked
	public int clickThisCard = 1;
	// sign the statement if the card changed
	public int change = 0;
	// computer's choosing card
	public int computerPlayColor = -1;
	public int computerPlayNum = -1;
	// AI start
	public int aiStart = 0;
	// statement: 2 nomal； 1 last； 0 over the cards
	public int computerStatement = 2;
	public int playerStatement = 2;
	// store the choosing cards in 0 mode
	public int lastOne;
	public int lastTwo;

	public int playerOne;
	public int playerTwo;

	public int clickTime = 2;
	public int playerStart = 1;
	public int canPlay = 1;
	public int finish = 1;
	public int lastDebug = 1;
	public int canRestart = 1;

	// music
	public AudioClip music;

	public static void main(String[] args) {
		stage.launch(args);
	}

	public void start(Stage primaryStage) {
		primaryStage.setTitle("Parade");
		cardImage();
		// initialize the majority
		for (int i = 0; i < 6; i++) {
			Rectangle newRec = new Rectangle();
			newRec.setFill(Color.RED);
			newRec.setHeight(5.0);
			newRec.setWidth(5.0);
			majorityPlayer.add(newRec);
		}
		for (int i = 0; i < 6; i++) {
			Rectangle newRec2 = new Rectangle();
			newRec2.setFill(Color.RED);
			newRec2.setHeight(5.0);
			newRec2.setWidth(5.0);
			majorityComputer.add(newRec2);
		}
		// initialize the list
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 11; j++) {
				list.add(new informationForCard(i, j));
			}
		}
		// initialize the playerlist
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 11; j++) {
				player.add(new ArrayList<cards>());
			}
		}
		// initialize the computerlist
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 11; j++) {
				computer.add(new ArrayList<cards>());
			}
		}
		// initialize the templist
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 11; j++) {
				computerTest.add(new ArrayList<cards>());
			}
		}
		setUpStage();
		// scene
		primaryStage.setScene(new Scene(root, 1000, 600));
		primaryStage.show();
	}

	// card's image Array
	public void cardImage() {
		for (int i = 0; i < 6; i++)
			for (int j = 0; j < 11; j++)
				cardImage[i][j] = new Image(100 * (i + 1) + j + ".jpg");
	}

	// class for storing card's information - number/color/where it was used
	public class informationForCard {
		public int colorInfo;
		public int numInfo;

		informationForCard() {
			numInfo = 0;
			colorInfo = 0;
		}

		informationForCard(int i, int j) {
			colorInfo = i;
			numInfo = j;
		}
	}

	// card class
	public class cards extends ImageView {
		public int number;
		public int colorNum;
		public int clickCount; // times of choosing the card
		public int event; // record of putting it
		public int alreadyPut;
		public InnerShadow innerShadow = new InnerShadow();

		public cards(int col, int num) {
			setFitHeight(150);
			setFitWidth(100);
			number = num;
			colorNum = col;
			clickCount = 1;
			event = 0;
			alreadyPut = 0;
			innerShadow.setOffsetX(4);
			innerShadow.setOffsetY(4);
			innerShadow.setColor(Color.WHITE);
			setImage(cardImage[colorNum][number]);
			setEffect(innerShadow);
		}
	}

	// ALL POSITION CLASS

	// position of the player's five cards position(number 0-4)
	public class playerCardPosition {
		public double playerToLeft;
		public double playerToBottom;

		playerCardPosition() {
			playerToLeft = 10 + 82.5;
			playerToBottom = 50;
		}

		playerCardPosition(int num) {
			playerToLeft = 10 + 82.5 + 105 * num;
			playerToBottom = 50;
		}
	}

	// position of the parade(number 0-65)
	public class paradeCardPosition {
		public double paradeToLeft;
		public double paradeToTop;

		paradeCardPosition() {
			paradeToLeft = 115;
			paradeToTop = 5;
		}

		paradeCardPosition(int num) {
			paradeToLeft = 115 + 15 * (num % 32);
			paradeToTop = 5 + 105 * (num / 32);
		}
	}

	// position of the rest cards for players
	public class playerRestCardPosition {
		public double playerRestToRight;
		public double playerRestToTop;

		playerRestCardPosition() {
			playerRestToRight = 17.5;
			playerRestToTop = 375 + 10;
		}

		playerRestCardPosition(int color, int index) {
			playerRestToRight = 17.5 + (5 - color) * 47;
			playerRestToTop = 375 + 10 + index * 8;
		}
	}

	// majority put for player
	public void majority(ArrayList<Rectangle> recList, double num1,
			double num2, double top) {
		for (int i = 0; i < 6; i++) {
			AnchorPane.setRightAnchor(recList.get(i), num1 + (5 - i) * num2);
			AnchorPane.setTopAnchor(recList.get(i), top);
			root.getChildren().add(recList.get(i));
		}
	}

	// position of the rest cards for computer
	public class computerRestCardPosition {
		public double computerRestToRight;
		public double computerRestToTop;

		computerRestCardPosition() {
			computerRestToRight = 17.5;
			computerRestToTop = 20 + 135;
		}

		computerRestCardPosition(int color, int index) {
			computerRestToRight = 17.5 + (5 - color) * 47;
			computerRestToTop = 20 + 135 + index * 8;
		}
	}

	// position of the cards for computers
	public class computerCardPosition {
		public double computerToRight;
		public double computerToTop;

		computerCardPosition() {
			computerToRight = 17.5;
			computerToTop = 10;
		}

		computerCardPosition(int index) {
			computerToRight = 17.5 + (5 - index) * 47;
			computerToTop = 10;
		}
	}

	// ALL SCORE CLASS
	// each score class
	public class eachScore {
		public int computers;
		public int players;
		public int majority = 0;

		eachScore() {
			computers = 0;
			players = 0;
		}

		eachScore(int a, int b) {
			computers = a;
			players = b;
		}
	}

	// total score
	public class totalScore {
		public int computerTotal;
		public int playersTotal;

		totalScore() {
			computerTotal = 0;
			playersTotal = 0;
		}

		totalScore(int computer, int players) {
			computerTotal = computer;
			playersTotal = players;
		}
	}

	// FUNCTION FOR CARDS' PUTTING
	// put player's card & put action on these cards
	public void putPlayerCard() {
		int length = playerList.size();
		for (int i = 0; i < length; i++) {
			// change the information into new card
			playerCardPosition newPosition = new playerCardPosition(i);
			AnchorPane.setLeftAnchor(playerList.get(i),
					newPosition.playerToLeft);
			AnchorPane.setBottomAnchor(playerList.get(i),
					newPosition.playerToBottom);
			playerList.get(i).clickCount = 1;
			root.getChildren().add(playerList.get(i));

			if (playerList.get(i).event == 0) {
				playerList.get(i).addEventHandler(MouseEvent.MOUSE_CLICKED,
						new EventHandler<MouseEvent>() {
							public void handle(MouseEvent actEvt) {
								cards thisCard = (cards) (actEvt.getSource());
								// do something when computer not play
								if (aiStart == 0 && canPlay == 1) {
									// when choosing the card
									if (clickThisCard == 1
											&& thisCard.clickCount == 1
											&& change == 0) {
										clickColor = thisCard.colorNum;
										clickNum = thisCard.number;
										AudioClip playChooseMusic = new 
											AudioClip(getClass().getResource(
														"sendcard.wav")
														.toString());
										playChooseMusic.play();
										thisCard.setOpacity(0.5);
										playerStart = 0;
										compareCards();
										changeSafeCards();
										changeUpdateCards();
										clickThisCard = 0;
										thisCard.clickCount = (-1)
												* thisCard.clickCount;
										change = 1;
									}
									// stop choosing
									if (thisCard.clickCount == -1
											&& change == 0) {
										clickColor = -1;
										clickNum = -1;
										thisCard.setOpacity(1);
										playerStart = 1;
										recoverColor();
										safeArray.clear();
										updatedArray.clear();
										clickThisCard = 1;
										thisCard.clickCount = (-1)
												* thisCard.clickCount;
									}
									change = 0;
									if (thisCard.clickCount == 1
											&& clickThisCard == 0) {
										change = 0;
									}
								}
							}
						});
				// record this card have a mouse clicked event handler
				playerList.get(i).event = 1;
			}
		}
	}

	// put parade's card
	public void putParadeCard() {
		int length = paradeList.size();
		for (int j = 0; j < length; j++) {
			paradeCardPosition newPosition = new paradeCardPosition(j);
			AnchorPane.setLeftAnchor(paradeList.get(j),
					newPosition.paradeToLeft);
			AnchorPane.setTopAnchor(paradeList.get(j), newPosition.paradeToTop);
			root.getChildren().add(paradeList.get(j));
		}
	}

	// put computer's card
	public void putComputerCard() {
		// when debug put the cards
		if (debugInt == 1) {
			int length = computerList.size();
			for (int j = 0; j < length; j++) {
				computerCardPosition newPosition = new computerCardPosition(j);
				computerList.get(j).setFitHeight(60);
				computerList.get(j).setFitWidth(40);
				AnchorPane.setRightAnchor(computerList.get(j),
						newPosition.computerToRight);
				AnchorPane.setTopAnchor(computerList.get(j),
						newPosition.computerToTop);
				root.getChildren().add(computerList.get(j));
			}
		}
		return;
	}

	// DESIDE WHICH CARDS SHOULD BE UPDATED
	// compare clicked card and parade's cards
	public void compareCards() {
		int num = clickNum;
		int color = clickColor;
		if (num > paradeList.size()) {
			for (int i = 0; i < paradeList.size(); i++) {
				safeArray.add(i);
			}
		}
		if (num <= paradeList.size()) {
			int endOfList = paradeList.size() - 1;
			for (int i = 0; i < num; i++) {
				safeArray.add(endOfList);
				--endOfList;
			}
			for (int j = 0; j <= endOfList; j++) {
				if ((paradeList.get(j).number <= num)
						|| (paradeList.get(j).colorNum == color)) {
					updatedArray.add(j);
				}
			}
		}
	}

	// change the safe cards in parade
	public void changeSafeCards() {
		int size = safeArray.size();
		for (int i = 0; i < size; i++) {
			int index = safeArray.get(i);
			paradeList.get(index).setFitHeight(120);
			paradeList.get(index).setFitWidth(80);
		}
	}

	// change the updated cards in parade
	public void changeUpdateCards() {
		int size = updatedArray.size();
		for (int i = 0; i < size; i++) {
			int index = updatedArray.get(i);
			paradeList.get(index).innerShadow.setColor(Color.CADETBLUE);

		}
	}

	// WHEN DECIDED UPDATED ALL
	// 1.store the updating information to the players' list
	public void storeRestCard(ArrayList<ArrayList<cards>> storeList) {
		int size = updatedArray.size();
		for (int i = 0; i < size; i++) {
			int index = updatedArray.get(i);
			int colorStore = paradeList.get(index).colorNum;
			int numStore = paradeList.get(index).number;
			cards newCard = new cards(colorStore, numStore);
			storeList.get(colorStore).add(newCard);
		}
	}

	// 2. put the player's rest cards
	public void putPlayerRestCard() {
		for (int i = 0; i < 6; i++) {
			int length = player.get(i).size();
			for (int j = 0; j < length; j++) {
				if (player.get(i).get(j).alreadyPut == 0) {
					playerRestCardPosition newPosition = new 
												   playerRestCardPosition(i, j);			
					player.get(i).get(j).setFitHeight(60);
					player.get(i).get(j).setFitWidth(40);
					AnchorPane.setRightAnchor(player.get(i).get(j),
							newPosition.playerRestToRight);
					AnchorPane.setTopAnchor(player.get(i).get(j),
							newPosition.playerRestToTop);
					root.getChildren().add(player.get(i).get(j));
					FadeTransition ft = new FadeTransition(
							Duration.millis(200), player.get(i).get(j));
					ft.setFromValue(0);
					ft.setToValue(1);
					ft.play();
					canRestart = 0;
					/*ft.setOnFinished(new EventHandler<ActionEvent>() {
						public void handle(ActionEvent event) {
							canRestart = 1;
						}
					});*/
					player.get(i).get(j).alreadyPut = 1;
				}
			}
		}

	}

	// 2. put the computer's rest cards
	public void putComputerRestCard() {

		for (int i = 0; i < 6; i++) {
			int length = computer.get(i).size();
			for (int j = 0; j < length; j++) {
				if (computer.get(i).get(j).alreadyPut == 0) {
					computerRestCardPosition newPosition = new 
												 computerRestCardPosition(i, j);
					computer.get(i).get(j).setFitHeight(60);
					computer.get(i).get(j).setFitWidth(40);
					AnchorPane.setRightAnchor(computer.get(i).get(j),
							newPosition.computerRestToRight);
					AnchorPane.setTopAnchor(computer.get(i).get(j),
							newPosition.computerRestToTop);
					computer.get(i).get(j).setOpacity(0);
					root.getChildren().add(computer.get(i).get(j));
					FadeTransition ft = new FadeTransition(
							Duration.millis(200), computer.get(i).get(j));
					ft.setFromValue(0);
					ft.setToValue(1);
					ft.play();
					canRestart = 0;
					ft.setOnFinished(new EventHandler<ActionEvent>() {
						public void handle(ActionEvent event) {
							canRestart = 1;
						}
					});
					computer.get(i).get(j).alreadyPut = 1;
				}
			}
		}

	}

	// 3. update the parade list
	public void updatedParadeList() {
		// remove the parade list in root
		int paradeLength = paradeList.size();
		for (int i = 0; i < paradeLength; i++) {
			root.getChildren().remove(paradeList.get(i));
		}
		// move the updating card
		int size = updatedArray.size();
		for (int i = 0; i < size; i++) {
			int index = updatedArray.get(i) - i;
			paradeList.remove(index);
		}
		updatedArray.clear();
		// new card put on the parade
		cards newParadeCard = new cards(clickColor, clickNum);
		paradeList.add(newParadeCard);
		// put the parade into the root
		paradeList.get(paradeList.size() - 1).setOpacity(0);
		putParadeCard();
		canRestart = 0;
		FadeTransition ft = new FadeTransition(Duration.millis(1000),
				paradeList.get(paradeList.size() - 1));
		ft.setFromValue(0);
		ft.setToValue(1);
		ft.play();
		ft.setOnFinished(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				if(updatedArray.size() == 0)
				canRestart = 1;
			}
		});
	

	}

	// 4.change the player's 5 cards
	public void updatedPlayerList() {
		// move all cards belong player on root
		int playerLength = playerList.size();
		for (int i = 0; i < playerLength; i++) {
			root.getChildren().remove(playerList.get(i));
		}
		// remove the choosing card
		int index = signThePosition();
		playerList.remove(index);
		// when not end the game new card in player's card
		if (playerStatement == 2) {
			playerList.add(new cards(list.get(count).colorInfo,
					list.get(count).numInfo));
			count++;
			restCount.setText("                              CARDS LEFT: "
					+ (66 - count) + "");
		} else {
			playerStatement = 0;
		}
		if ((count == 66) && (playerStatement) == 2 
				     && (computerStatement) == 2) {
			playerStatement = 1;
			computerStatement = 1;
			btn.setText("END");
		}
		// put the player's cards
		putPlayerCard();

	}

	// 4.change the computer's 5 cards
	public void updatedComputerList() {
		// remove all computer's cards on root
		int computerLength = computerList.size();
		for (int i = 0; i < computerLength; i++) {
			root.getChildren().remove(computerList.get(i));
		}
		// remove the clicked card
		int index = clicked;
		computerList.remove(index);
		// update the game mode statement
		if (computerStatement == 2) {
			computerList.add(new cards(list.get(count).colorInfo, list
					.get(count).numInfo));
			count++;
			restCount.setText("                              CARDS LEFT: "
					+ (66 - count) + "");
		} else {
			computerStatement = 0;
		}
		if ((count == 66) && (playerStatement) == 2 
					 && (computerStatement) == 2) {
			playerStatement = 1;
			computerStatement = 1;
			btn.setText("END");
		}
		// put the cards
		putComputerCard();
		// when near game's end go into mode2
		if ((computerStatement == 0) && (playerStatement == 0)) {
			PauseTransition duration = new PauseTransition(Duration.seconds(1));
			duration.play();
			duration.setOnFinished(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent event) {
					newStatement();
				}
			});
		}
	}

	// AI FUNCTION
	// choose the good card's index
	public int compareToChoose() {
		int min = 0;
		// computer's score - player's score
		int minDif = thisCardScore(computerList.get(0));
		// computer's score
		int minComputer = calculateScore(computerTest, player).computerTotal;
		for (int i = 1; i < 5; i++) {
			int nowMinDif = thisCardScore(computerList.get(i));
			int nowMinComputer = calculateScore(computerTest, player)
								 .computerTotal;
			// case1
			if ((minDif > 0) && (nowMinDif < 0)) {
				min = i;
				minDif = nowMinDif;
				minComputer = nowMinComputer;
				continue;
			}
			// case2
			if ((minDif > 0) && (nowMinDif > 0)) {
				if (nowMinComputer < minComputer) {
					min = i;
					minDif = nowMinDif;
					minComputer = nowMinComputer;
					continue;
				}
			}
			// case3
			if ((minDif < 0) && (nowMinDif > 0)) {
				continue;
			}
			// case4
			if ((minDif < 0) && (nowMinDif < 0)) {
				if (nowMinDif < minDif) {
					min = i;
					minDif = nowMinDif;
					minComputer = nowMinComputer;
					continue;
				}
			}
		}
		return min;
	}

	// choose the last two cards
	public void choiceTwo() {

		lastOne = 0;
		lastTwo = 1;
		twoCardScore(playerList.get(0), playerList.get(1));
		int minScore = calculateScore(computerTest, player).computerTotal;
		int i = 0;
		int j = i + 1;
		for (i = 0; i < 3; i++) {
			for (j = i + 1; j < 4; j++) {
				twoCardScore(playerList.get(i), playerList.get(j));
				int nowMinScore = calculateScore(computerTest, player)
								  .computerTotal;
				if (nowMinScore < minScore) {
					lastOne = i;
					lastTwo = j;
					minScore = nowMinScore;
				}
			}
		}
	}

	// show the cards computer chosen
	public void showLastCards() {
		if (lastDebug == 1) {
			root.getChildren().remove(deckList.get(lastOne));
			root.getChildren().remove(deckList.get(lastTwo));

			cards compCard1 = new cards(computerList.get(lastOne).colorNum,
					computerList.get(lastOne).number);
			cards compCard2 = new cards(computerList.get(lastTwo).colorNum,
					computerList.get(lastTwo).number);
			AnchorPane.setTopAnchor(compCard1, 50.0);
			AnchorPane.setLeftAnchor(compCard1, 10.0 + 110 * lastOne);
			compCard1.setOpacity(0.6);
			root.getChildren().add(compCard1);
			AnchorPane.setTopAnchor(compCard2, 50.0);
			AnchorPane.setLeftAnchor(compCard2, 10.0 + 110 * lastTwo);
			compCard2.setOpacity(0.6);
			root.getChildren().add(compCard2);
			return;
		}
		if (lastDebug == -1) {
			computerList.get(lastOne).setOpacity(0.6);
			computerList.get(lastTwo).setOpacity(0.6);
			return;
		}
	}

	// put deck cover
	public void putDeck() {
		// set the computer's card -- covered
		for (int i = 0; i < 4; i++) {
			Image deckImage = new Image("deck.jpg");
			deckList.add(new ImageView());
			deckList.get(i).setImage(deckImage);
			deckList.get(i).setFitHeight(150);
			deckList.get(i).setFitWidth(100);
			AnchorPane.setTopAnchor(deckList.get(i), 50.0);
			AnchorPane.setLeftAnchor(deckList.get(i), 10.0 + 110 * i);
			root.getChildren().add(deckList.get(i));
		}
	}

	// show last for computer cards -- for debug
	public void showLastFourCards() {

		for (int i = 0; i < 4; i++) {
			root.getChildren().remove(deckList.get(i));
			computerList.get(i).setOpacity(1);
			computerList.get(i).setFitHeight(150);
			computerList.get(i).setFitWidth(100);
			AnchorPane.setTopAnchor(computerList.get(i), 50.0);
			AnchorPane.setLeftAnchor(computerList.get(i), 10.0 + 110 * i);
			root.getChildren().add(computerList.get(i));
		}
	}

	// FUNCTION IN MODE2
	// store the two cards - computer or player
	public class twoCardsPosition {
		public int cardOne;
		public int cardTwo;

		twoCardsPosition() {
			cardOne = 0;
			cardTwo = 0;
		}

		twoCardsPosition(int x, int y) {
			cardOne = x;
			cardTwo = y;
		}

	}

	public twoCardsPosition storeTwoCards(ArrayList<ArrayList<cards>> store) {
		twoCardsPosition position = new twoCardsPosition();
		int size = tryTwoCards.size();
		for (int i = 0; i < size; i++) {
			int colorStore = tryTwoCards.get(i).colorNum;
			int numStore = tryTwoCards.get(i).number;
			if (i == 0) {
				position.cardOne = store.get(colorStore).size();
			}
			if (i == 1) {
				position.cardTwo = store.get(colorStore).size();
			}
			cards newCard = new cards(colorStore, numStore);
			store.get(colorStore).add(newCard);
		}
		return position;
	}

	public int twoCardScore(cards card1, cards card2) {

		tryTwoCards.clear();
		tryTwoCards.add(card1);
		tryTwoCards.add(card2);
		// copy to test
		for (int i = 0; i < 6; i++) {
			computerTest.get(i).clear();
			for (int j = 0; j < computer.get(i).size(); j++) {
				cards newCards = new cards(computer.get(i).get(j).colorNum,
						computer.get(i).get(j).number);
				computerTest.get(i).add(newCards);
			}

		}
		storeTwoCards(computerTest);
		// calculate
		int differenceScore = calculateScore(computerTest, player).computerTotal
				- calculateScore(computerTest, player).playersTotal;
		return differenceScore;
	}

	// computer's turn
	public void computerAI() {

		clicked = compareToChoose();
		clickColor = computerList.get(clicked).colorNum;
		clickNum = computerList.get(clicked).number;
		
		canRestart = 0;
		cards computerNewCard = new cards(clickColor, clickNum);

		AnchorPane.setBottomAnchor(computerNewCard, 250.0);
		AnchorPane.setLeftAnchor(computerNewCard, 10.0);
		root.getChildren().addAll(computerNewCard);

		AudioClip playCompMusic = new AudioClip(getClass().getResource(
				"sendcard.wav").toString());
		playCompMusic.play();

		safeArray.clear();
		updatedArray.clear();

		compareCards();

		changeSafeCards();
		changeUpdateCards();	

		PauseTransition pt = new PauseTransition(Duration.seconds(1));
		pt.play();
		pt.setOnFinished(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				recoverColor();
				root.getChildren().remove(computerNewCard);

				AudioClip playRemoveMusic = new AudioClip(getClass()
						.getResource("removecard.wav").toString());
				playRemoveMusic.play();

				storeRestCard(computer);
				updatedParadeList();
				putComputerRestCard();

				updatedComputerList();
				clickColor = -1;
				clickNum = -1;
				clicked = -1;
				updatedArray.clear();
				safeArray.clear();

				totalScore totalScore2 = calculateScore(computer, player);
				playScore = totalScore2.playersTotal;
				playerScore.setText(" " + playScore + " ");
				compScore = totalScore2.computerTotal;
				computerScore.setText(" " + compScore + " ");

				for (int i = 0; i < 6; i++) {
					if (majorityNum.get(i) == 2) {
						majorityComputer.get(i).setFill(Color.GREEN);
					}
					if (majorityNum.get(i) == 1) {
						majorityPlayer.get(i).setFill(Color.GREEN);
					}
					if (majorityNum.get(i) == 0) {
						majorityComputer.get(i).setFill(Color.RED);
						majorityPlayer.get(i).setFill(Color.RED);
					}
				}

				if ((calculateKind(computer) == 6) && (playerStatement) == 2
						&& (computerStatement) == 2) {
					btn.setText("END");
					playerStatement = 1;
					computerStatement = 1;
				}

				aiStart = 0;

				canPlay = 1;
				
			}
		});

	}

	// calculate the rest cards' kind
	public int calculateKind(ArrayList<ArrayList<cards>> restPart) {
		int kind = 0;
		int size = restPart.size();
		for (int i = 0; i < size; i++) {
			if (restPart.get(i).size() != 0)
				++kind;
		}
		return kind;
	}

	// calculate the score
	public totalScore calculateScore(ArrayList<ArrayList<cards>> computerPart,
			ArrayList<ArrayList<cards>> playerPart) {
		totalScore total = new totalScore();
		total.computerTotal = 0;
		total.playersTotal = 0;
		eachScore each[] = new eachScore[6];
		majorityNum.clear();
		for (int i = 0; i < 6; i++) {
			each[i]  
					=calculateEachScore(computerPart.get(i), playerPart.get(i));
			majorityNum.add(each[i].majority);
		}
		for (int i = 0; i < 6; i++) {
			total.computerTotal += each[i].computers;
			total.playersTotal += each[i].players;
		}
		return total;
	}

	// compare the score
	public eachScore calculateEachScore(ArrayList<cards> computerPart,
			ArrayList<cards> playerPart) {
		eachScore each = new eachScore(0, 0);
		int computer = computerPart.size();
		int player = playerPart.size();
		int difference = computer - player;
		if (difference >= 2) {
			each.computers = computer;
			each.majority = 2;
			for (int i = 0; i < player; i++) {
				each.players = each.players + playerPart.get(i).number;
			}
			return each;
		}
		if (difference <= -2) {
			each.players = player;
			each.majority = 1;
			for (int i = 0; i < computer; i++) {
				each.computers = each.computers + computerPart.get(i).number;
			}
			return each;
		}
		for (int i = 0; i < player; i++) {
			each.players = each.players + playerPart.get(i).number;
		}
		for (int i = 0; i < computer; i++) {
			each.computers = each.computers + computerPart.get(i).number;
		}
		return each;
	}

	// sign the position
	public int signThePosition() {
		int colP = clickColor;
		int numP = clickNum;
		int size = playerList.size();
		for (int i = 0; i < size; i++) {
			if ((playerList.get(i).colorNum == colP)
					&& (playerList.get(i).number == numP))
				return i;
		}
		return -1;
	}

	// recover the color in parade
	public void recoverColor() {
		int size = paradeList.size();
		for (int i = 0; i < size; i++) {
			paradeList.get(i).innerShadow.setColor(Color.WHITE);
			// paradeList.get(i).setOpacity(1);
			paradeList.get(i).setFitHeight(150);
			paradeList.get(i).setFitWidth(100);
		}
	}

	// computer decide the card to play
	public int thisCardScore(cards thisCard) {
		clickColor = thisCard.colorNum;
		clickNum = thisCard.number;
		updatedArray.clear();
		safeArray.clear();
		// updateArray safeArray信息录入
		compareCards();
		// copy computer's cards into test
		for (int i = 0; i < 6; i++) {
			computerTest.get(i).clear();
			for (int j = 0; j < computer.get(i).size(); j++) {
				cards newCards = new cards(computer.get(i).get(j).colorNum,
						computer.get(i).get(j).number);
				computerTest.get(i).add(newCards);

			}

		}
		// store the cards into computerTest list
		storeRestCard(computerTest);

		// calculate the score
		int differenceScore = calculateScore(computerTest, player).computerTotal
				- calculateScore(computerTest, player).playersTotal;

		return differenceScore;

	}

	// initialize game
	public void initializeAll() {

		compScore = 0;
		playScore = 0;
		debugInt = -1;
		lastDebug = 1;
		count = 0;
		clicked = -1;
		clickColor = -1;
		clickNum = 1;
		// the numbers of the cards have been clicked
		clickThisCard = 1;
		// sign the statement if the card changed
		change = 0;
		// computer's choosing card
		computerPlayColor = -1;
		computerPlayNum = -1;
		// AI start
		aiStart = 0;
		computerStatement = 2;
		playerStatement = 2;
		clickTime = 2;
		playerStart = 1;
		finish = 1;
		canPlay = 1;
		canRestart = 1;
		btn.setText("PLAY");

		deckList.clear();
		playerList.clear();
		newPlayerList.clear();
		paradeList.clear();
		computerList.clear();
		safeArray.clear();
		updatedArray.clear();
		player.clear();
		computer.clear();
		computerTest.clear();
		tryTwoCards.clear();
		lastTwoCardsIndex.clear();
		list.clear();

		// initialize the list
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 11; j++) {
				list.add(new informationForCard(i, j));
			}
		}
		// initialize the playerlist
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 11; j++) {
				player.add(new ArrayList<cards>());
			}
		}

		// initialize the computerlist
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 11; j++) {
				computer.add(new ArrayList<cards>());
			}
		}

		// initialize the templist
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 11; j++) {
				computerTest.add(new ArrayList<cards>());
			}
		}

	}

	// new statement
	public void newStatement() {
		root.getChildren().clear();

		// set on the back image 600*1000
		Image backImage = new Image("back4.jpg");
		ImageView backView = new ImageView();
		backView.setFitHeight(600);
		backView.setFitWidth(1000);
		backView.setImage(backImage);
		root.getChildren().addAll(backView);

		putDeck();

		// AI choose
		choiceTwo();

		// set the score
		// computer
		computerFinalScore.setText("COMPUTER SCORE");
		computerFinalScore.setFill(Color.GREENYELLOW);
		computerFinalScore.setFont(new Font(15));

		computerScorePlace.setMinSize(150.0, 25);
		computerScorePlace.setMaxSize(150.0, 25);
		AnchorPane.setTopAnchor(computerScorePlace, 225.0);
		AnchorPane.setLeftAnchor(computerScorePlace, 10.0);
		computerScorePlace.getChildren().add(computerFinalScore);
		root.getChildren().addAll(computerScorePlace);

		AnchorPane.setTopAnchor(computerScore, 300.0);
		AnchorPane.setLeftAnchor(computerScore, 80.0);
		root.getChildren().addAll(computerScore);

		// player
		playerFinalScore.setText("  PLAYER SCORE");
		playerFinalScore.setFill(Color.GREENYELLOW);
		playerFinalScore.setFont(new Font(15));

		playerScorePlace.setMinSize(150.0, 25);
		playerScorePlace.setMaxSize(150.0, 25);
		AnchorPane.setTopAnchor(playerScorePlace, 225.0);
		AnchorPane.setLeftAnchor(playerScorePlace, 200.0);
		playerScorePlace.getChildren().add(playerFinalScore);
		root.getChildren().addAll(playerScorePlace);

		AnchorPane.setTopAnchor(playerScore, 300.0);
		AnchorPane.setLeftAnchor(playerScore, 280.0);
		root.getChildren().addAll(playerScore);

		majority(majorityPlayer, 40, 90, 390);
		majority(majorityComputer, 40, 90, 40);

		// restart the game
		// decide choose cards
		Button debugGame = new Button();
		AnchorPane.setTopAnchor(debugGame, 10.0);
		AnchorPane.setBottomAnchor(debugGame, 560.0);
		AnchorPane.setRightAnchor(debugGame, 900.0);
		AnchorPane.setLeftAnchor(debugGame, 10.0);
		debugGame.setStyle("-fx-font: 8 Helvetica;-fx-text-fill: white; "
				+ "-fx-background-color: #00688B;");
		debugGame.setOpacity(0.6);
		debugGame.setText("DEBUG");
		root.getChildren().add(debugGame);
		debugGame.addEventHandler(MouseEvent.MOUSE_CLICKED,
				new EventHandler<MouseEvent>() {
					public void handle(MouseEvent event) {
						if (finish == 1) {
							if (lastDebug == 1) {
								showLastFourCards();
								lastDebug = lastDebug * (-1);
								return;
							}
							if (lastDebug == -1) {
								for (int i = 0; i < 4; i++) {
									root.getChildren().remove(
											computerList.get(i));
									AnchorPane.setTopAnchor(deckList.get(i),
											50.0);
									AnchorPane.setLeftAnchor(deckList.get(i),
											10.0 + 110 * i);
									root.getChildren().add(deckList.get(i));
								}
								lastDebug = lastDebug * (-1);
								return;
							}
						}
					}
				});

		// decide choose cards
		Button restartGame = new Button();
		AnchorPane.setTopAnchor(restartGame, 10.0);
		AnchorPane.setBottomAnchor(restartGame, 560.0);
		AnchorPane.setRightAnchor(restartGame, 670.0);
		AnchorPane.setLeftAnchor(restartGame, 120.0);
		restartGame.setStyle("-fx-font: 8 Helvetica;-fx-text-fill: white; "
				+ "-fx-background-color: #00688B;");
		restartGame.setOpacity(0.6);
		restartGame.setText("RESTART");
		root.getChildren().add(restartGame);
		restartGame.addEventHandler(MouseEvent.MOUSE_CLICKED,
				new EventHandler<MouseEvent>() {
					public void handle(MouseEvent event) {
						root.getChildren().clear();
						// initialize the majority
						for (int i = 0; i < 6; i++) {
							majorityPlayer.get(i).setFill(Color.RED);
						}
						for (int i = 0; i < 6; i++) {
							majorityComputer.get(i).setFill(Color.RED);
						}
						initializeAll();
						restCountPane.getChildren().clear();
						computerScorePane.getChildren().clear();
						playerScorePane.getChildren().clear();
						computerScorePlace.getChildren().clear();
						playerScorePlace.getChildren().clear();
						setUpStage();

					}
				});

		// decide choose cards
		Button ok = new Button();
		AnchorPane.setTopAnchor(ok, 560.0);
		AnchorPane.setBottomAnchor(ok, 10.0);
		AnchorPane.setRightAnchor(ok, 670.0);
		AnchorPane.setLeftAnchor(ok, 120.0);
		ok.setStyle("-fx-font: 8 Helvetica;-fx-text-fill: white; "
				+ "-fx-background-color: #00688B;");
		ok.setOpacity(0.6);
		ok.setText("OK");
		root.getChildren().add(ok);
		ok.addEventHandler(MouseEvent.MOUSE_CLICKED,
				new EventHandler<MouseEvent>() {
					public void handle(MouseEvent event) {
						int length = lastTwoCardsIndex.size();
						if (length == 2 && finish == 1) {
							music.stop();
							AudioClip musicGameOver = new AudioClip(getClass()
									.getResource("gameover.wav").toString());
							musicGameOver.play();

							// 把玩家选择的牌放到player中
							tryTwoCards.clear();
							tryTwoCards.add(playerList.get(lastTwoCardsIndex
									.get(0)));
							tryTwoCards.add(playerList.get(lastTwoCardsIndex
									.get(1)));

							twoCardsPosition playTwoPosition = new 
															 twoCardsPosition();
							playTwoPosition = storeTwoCards(player);

							int colorX = playerList.get(lastTwoCardsIndex
									.get(0)).colorNum;
							int numX = playerList.get(lastTwoCardsIndex.get(0)).
									   number;
							int numY = playTwoPosition.cardOne;

							cards newOnePlayer = new cards(colorX, numX);
							newOnePlayer.setFitHeight(90);
							newOnePlayer.setFitWidth(60);
							newOnePlayer.setLayoutX(480 + 90 * colorX);
							newOnePlayer.setLayoutY(400 + numY * 12);
							root.getChildren().add(newOnePlayer);

							TranslateTransition movePlayCardOne = new 
												TranslateTransition(
														Duration.millis(1500),
														newOnePlayer
												);
												

							movePlayCardOne.setFromX(10 + 110
									* lastTwoCardsIndex.get(0)
									- (480 + 90 * colorX));
							movePlayCardOne.setFromY(400 - (400 + numY * 12));
							movePlayCardOne.setToX(0);
							movePlayCardOne.setToY(0);

							movePlayCardOne.play();

							int color2X = playerList.get(lastTwoCardsIndex
									.get(1)).colorNum;
							int num2X = playerList.
										   get(lastTwoCardsIndex.get(1)).number;
							int num2Y = playTwoPosition.cardTwo;

							cards newtwoPlayer = new cards(color2X, num2X);
							newtwoPlayer.setFitHeight(90);
							newtwoPlayer.setFitWidth(60);

							newtwoPlayer.setLayoutX(480 + 90 * color2X);
							newtwoPlayer.setLayoutY(400 + num2Y * 12);
							root.getChildren().add(newtwoPlayer);

							TranslateTransition movePlayCardTwo = new 
									TranslateTransition(
											Duration.millis(1500), 
											newtwoPlayer
									);

							movePlayCardTwo.setFromX(10 + 110
									* lastTwoCardsIndex.get(1)
									- (480 + 90 * color2X));
							movePlayCardTwo.setFromY(400 - (400 + num2Y * 12));
							movePlayCardTwo.setToX(0);
							movePlayCardTwo.setToY(0);

							movePlayCardTwo.play();

							showLastCards();

							tryTwoCards.clear();
							tryTwoCards.add(computerList.get(lastOne));
							tryTwoCards.add(computerList.get(lastTwo));

							// copy into test
							for (int i = 0; i < 6; i++) {
								computerTest.get(i).clear();
								for (int j = 0; j < computer.get(i).size(); 
																		j++) {
									cards newCards = new cards(computer.get(i)
											.get(j).colorNum, computer.get(i)
											.get(j).number);
									computerTest.get(i).add(newCards);
								}
							}

							// store information into computerTest list
							// storeTwoCards(computerTest);

							twoCardsPosition computerTwoPosition = new
														     twoCardsPosition();
							computerTwoPosition = storeTwoCards(computerTest);

							int colorC = computerList.get(lastOne).colorNum;
							int numC = computerList.get(lastOne).number;
							int numM = computerTwoPosition.cardOne;

							cards newOneComputer = new cards(colorC, numC);
							newOneComputer.setFitHeight(90);
							newOneComputer.setFitWidth(60);

							newOneComputer.setLayoutX(480 + 90 * colorC);
							newOneComputer.setLayoutY(50 + numM * 12);
							root.getChildren().add(newOneComputer);

							TranslateTransition moveComputerCardOne = new
									TranslateTransition(
											Duration.millis(1500), 
											newOneComputer
									);

							moveComputerCardOne.setFromX(10 + 110
									* lastTwoCardsIndex.get(0)
									- (480 + 90 * colorC));
							moveComputerCardOne.setFromY(50 - (50 + numM * 12));
							moveComputerCardOne.setToX(0);
							moveComputerCardOne.setToY(0);

							moveComputerCardOne.play();

							int color2C = computerList.get(lastTwo).colorNum;
							int num2C = computerList.get(lastTwo).number;

							int num2M = computerTwoPosition.cardTwo;

							cards newtwoComputer = new cards(color2C, num2C);
							newtwoComputer.setFitHeight(90);
							newtwoComputer.setFitWidth(60);

							newtwoComputer.setLayoutX(480 + 90 * color2C);
							newtwoComputer.setLayoutY(50 + num2M * 12);
							root.getChildren().add(newtwoComputer);

							TranslateTransition moveComputerCardTwo = new 
									TranslateTransition(
											Duration.millis(1500),
											newtwoComputer
									);

							moveComputerCardTwo.setFromX(10 + 110
									* lastTwoCardsIndex.get(1)
									- (480 + 90 * color2C));
							moveComputerCardTwo
									.setFromY(50 - (50 + num2M * 12));
							moveComputerCardTwo.setToX(0);
							moveComputerCardTwo.setToY(0);

							moveComputerCardTwo.play();

							totalScore totalScore = calculateScore(
									computerTest, player);
							playerScore.setText("" + totalScore.playersTotal
									+ "");
							computerScore.setText("" + totalScore.computerTotal
									+ "");

							for (int i = 0; i < 6; i++) {
								if (majorityNum.get(i) == 2) {
									majorityComputer.get(i)
											.setFill(Color.GREEN);
								}
								if (majorityNum.get(i) == 1) {
									majorityPlayer.get(i).setFill(Color.GREEN);
								}
								if (majorityNum.get(i) == 0) {
									majorityComputer.get(i).setFill(Color.RED);
									majorityPlayer.get(i).setFill(Color.RED);
								}
							}

							// show the winner
							if (totalScore.computerTotal >
													 totalScore.playersTotal) {
								computerFinalScore.setText("COMPUTER LOSE");
								playerFinalScore.setText("  PLAYER WIN");
							}
							if (totalScore.computerTotal < 
													 totalScore.playersTotal) {
								computerFinalScore.setText("COMPUTER WIN");
								playerFinalScore.setText("  PLAYER LOSE");
							}
							if (totalScore.computerTotal ==
													 totalScore.playersTotal) {
								int numPlayer = 0;
								int numComputer = 0;
								for (int i = 0; i < 6; i++) {
									numPlayer = numPlayer
											+ player.get(i).size();
									numComputer = numComputer
											+ computerTest.get(i).size();
								}
								if (numPlayer < numComputer) {
									computerFinalScore.setText("COMPUTER LOSE");
									playerFinalScore.setText("  PLAYER WIN");
								}
								if (numPlayer > numComputer) {
									computerFinalScore.setText("COMPUTER WIN");
									playerFinalScore.setText("  PLAYER LOSE");
								}
								if (numPlayer == numComputer) {
									computerFinalScore.setText("TIE");
									playerFinalScore.setText("TIE");
								}
							}
							finish = 0;
						}
					}
				});

		// set the player's card -- uncovered
		int length = playerList.size();
		for (int i = 0; i < length; i++) {
			newPlayerList.add(new cards(playerList.get(i).colorNum, playerList
					.get(i).number));
			AnchorPane.setTopAnchor(newPlayerList.get(i), 400.0);
			AnchorPane.setLeftAnchor(newPlayerList.get(i), 10.0 + 110 * i);

			newPlayerList.get(i).addEventHandler(MouseEvent.MOUSE_CLICKED,
					new EventHandler<MouseEvent>() {
						public void handle(MouseEvent actEvt) {
							if (finish == 1) {
								// Get the index of this card
								cards thisCard = (cards) (actEvt.getSource());

								int index = 0;
								int size = playerList.size();
								for (int i = 0; i < size; i++) {
									if ((playerList.get(i).colorNum ==
															 thisCard.colorNum)
											&& (playerList.get(i).number ==
															  thisCard.number))
										index = i;
								}

								// this card can be clicked
								if ((clickTime != 0)
										&& (thisCard.clickCount == 1)) {
									lastTwoCardsIndex.add(index);
									thisCard.setOpacity(0.5);
									--clickTime;
									thisCard.clickCount = 0;
									return;
								}
								// this card clicked do not want to be clicked
								if (thisCard.clickCount == 0) {
									// remove
									lastTwoCardsIndex.remove(lastTwoCardsIndex
											.indexOf(index));
									// recover
									thisCard.setOpacity(1);
									thisCard.clickCount = 1;
									// clickTime＋1
									++clickTime;
									return;
								}
								if (clickTime == 0) {
									return;
								}
							}
						}
					});
			root.getChildren().add(newPlayerList.get(i));
		}

		// put computer's cards
		for (int i = 0; i < 6; i++) {
			int lengthComp = computer.get(i).size();
			for (int j = 0; j < lengthComp; j++) {
				computer.get(i).get(j).setFitHeight(90);
				computer.get(i).get(j).setFitWidth(60);
				AnchorPane.setRightAnchor(computer.get(i).get(j),
						10.0 + (5 - i) * 90);
				AnchorPane.setTopAnchor(computer.get(i).get(j), 50.0 + 12 * j);
				root.getChildren().add(computer.get(i).get(j));
			}
		}

		// put player's cards
		for (int i = 0; i < 6; i++) {
			int lengthPlayer = player.get(i).size();
			for (int j = 0; j < lengthPlayer; j++) {
				player.get(i).get(j).setFitHeight(90);
				player.get(i).get(j).setFitWidth(60);
				AnchorPane.setRightAnchor(player.get(i).get(j),
						(double) (10 + (5 - i) * 90));
				AnchorPane.setTopAnchor(player.get(i).get(j),
						(double) (400 + 12 * j));
				root.getChildren().add(player.get(i).get(j));
			}
		}
	}

	// initialize beginning five cards for players & five cards for computers &
	// six for parade
	public void beginGame() {
		finish = 1;
		// get coordinate randomly
		Collections.shuffle(list);
		// initialize beginning five cards for players & computers
		for (int i = 0; i < 5; i++) {
			// list.get(i).positionInfo = 1;
			playerList
					.add(new cards(list.get(i).colorInfo, list.get(i).numInfo));
			count++;
		}
		putPlayerCard();
		// for computer 5 cards
		int k = 0;
		while (k < 5) {
			// list.get(count).positionInfo = 3;
			computerList.add(new cards(list.get(count).colorInfo, list
					.get(count).numInfo));
			count++;
			k++;
		}
		putComputerCard();
		// for parade - 6 cards
		int j = 0;
		while (j < 6) {
			// list.get(count).positionInfo = 3;
			paradeList.add(j,
					new cards(list.get(count).colorInfo,
							list.get(count).numInfo));
			count++;
			j++;
		}
		putParadeCard();
		AudioClip putParadeMusic = new AudioClip(getClass().getResource(
				"putcard.mp3").toString());
		putParadeMusic.play();

		for (int i = 0; i < paradeList.size(); i++) {
			TranslateTransition tt = new TranslateTransition(
					Duration.millis(1500), paradeList.get(i));
			tt.setFromX(10 - 115 - 15 * i);
			tt.setToX(0);
			tt.play();
		}

		music = new AudioClip(getClass().getResource("startgame.wav")
				.toString());
		music.setCycleCount(AudioClip.INDEFINITE);
		music.play();
	}

	public void setUpStage() {
		// set on the back image 600*1000
		Image backImage = new Image("back4.jpg");
		ImageView backView = new ImageView();
		backView.setFitHeight(600);
		backView.setFitWidth(1000);
		backView.setImage(backImage);
		root.getChildren().addAll(backView);
		
		// the deck part
		Image deckImage = new Image("deck.jpg");
		deckPart.setFitHeight(150);
		deckPart.setFitWidth(100);
		deckPart.setImage(deckImage);
		AnchorPane.setTopAnchor(deckPart, 5.0);
		AnchorPane.setBottomAnchor(deckPart, 445.0);
		AnchorPane.setRightAnchor(deckPart, 890.0);
		AnchorPane.setLeftAnchor(deckPart, 10.0);
		root.getChildren().addAll(deckPart);
		
		// the paradeGround part
		Rectangle paradeGround = new Rectangle(580, 305);
		paradeGround.setFill(Color.BROWN);
		paradeGround.setOpacity(0.2);
		AnchorPane.setTopAnchor(paradeGround, 5.0);
		AnchorPane.setBottomAnchor(paradeGround, 290.0);
		AnchorPane.setRightAnchor(paradeGround, 305.0);
		AnchorPane.setLeftAnchor(paradeGround, 115.0);
		root.getChildren().addAll(paradeGround);
		
		// the player's 5 cards' ground part
		Rectangle playerGround = new Rectangle(685, 240);
		playerGround.setFill(Color.BROWN);
		playerGround.setOpacity(0.2);
		AnchorPane.setTopAnchor(playerGround, 355.0);
		AnchorPane.setBottomAnchor(playerGround, 5.0);
		AnchorPane.setRightAnchor(playerGround, 305.0);
		AnchorPane.setLeftAnchor(playerGround, 10.0);
		root.getChildren().addAll(playerGround);
		
		// dubug button
		Button debug = new Button();
		AnchorPane.setTopAnchor(debug, 120.0);
		AnchorPane.setBottomAnchor(debug, 465.0);
		AnchorPane.setRightAnchor(debug, 10.0);
		AnchorPane.setLeftAnchor(debug, 800.0);
		debug.setStyle("-fx-font: 8 Helvetica;-fx-text-fill: white; "
				+ "-fx-background-color: #00688B;");
		debug.setOpacity(0.6);
		debug.setText("DEBUG");
		root.getChildren().add(debug);
		debug.addEventHandler(MouseEvent.MOUSE_CLICKED,
				new EventHandler<MouseEvent>() {
					public void handle(MouseEvent event) {
						debugInt = (-1) * debugInt;
						if (debugInt == 1)
							putComputerCard();
						if (debugInt == -1) {
							int computerLength = computerList.size();
							for (int i = 0; i < computerLength; i++) {
								root.getChildren().remove(computerList.get(i));
							}
						}
					}
				});

		// play cards button part
		AnchorPane.setTopAnchor(btn, 315.0);
		AnchorPane.setBottomAnchor(btn, 255.0);
		AnchorPane.setRightAnchor(btn, 532.0);
		AnchorPane.setLeftAnchor(btn, 237.0);
		btn.setStyle("-fx-font: 11 Helvetica;" + "-fx-text-fill: white; "
				+ "-fx-background-color: #00688B;");
		btn.setOpacity(0.6);
		btn.setText("PLAY");
		btn.addEventHandler(MouseEvent.MOUSE_CLICKED,
				new EventHandler<MouseEvent>() {
					public void handle(MouseEvent event) {
						if (playerStart == 0) {
							// music.stop();
							canRestart = 0;
							AudioClip playMusic = new AudioClip(getClass()
									.getResource("removecard.wav").toString());
							playMusic.play();
							canPlay = 0;
							playerStart = 1;
							safeArray.clear();
							int index = signThePosition();
							playerList.get(index).setOpacity(1);
							playerList.get(index).clickCount = 1;
							clickThisCard = 1;
							change = 0;
							recoverColor();
							storeRestCard(player);

							// putPlayerRestCard();
							updatedParadeList();

							updatedPlayerList();

							putPlayerRestCard();
							
							canRestart = 0;
							clickColor = -1;
							clickNum = -1;
							totalScore totalScore = calculateScore(computer,
									player);
							playScore = totalScore.playersTotal;
							playerScore.setText(" " + playScore + " ");

							compScore = totalScore.computerTotal;
							computerScore.setText(" " + compScore + " ");

							for (int i = 0; i < 6; i++) {
								if (majorityNum.get(i) == 2) {
									majorityComputer.get(i)
											.setFill(Color.GREEN);
								}
								if (majorityNum.get(i) == 1) {
									majorityPlayer.get(i).setFill(Color.GREEN);
								}
								if (majorityNum.get(i) == 0) {
									majorityComputer.get(i).setFill(Color.RED);
									majorityPlayer.get(i).setFill(Color.RED);
								}
							}

							if ((calculateKind(player) == 6)
									&& (playerStatement) == 2
									&& (computerStatement) == 2) {
								playerStatement = 1;
								computerStatement = 1;
								btn.setText("END");
							}

							if ((computerStatement == 0)
									&& (playerStatement == 0)) {
								PauseTransition durationEnd = new 
										   PauseTransition(Duration.seconds(1));	
								durationEnd.play();
								durationEnd.setOnFinished
								       (new EventHandler<ActionEvent>() {
										public void handle(ActionEvent event) {
												newStatement();
											}
										});
							}

							else {
								PauseTransition duration = new PauseTransition(
										Duration.seconds(1));
								duration.play();
								canRestart = 0;
								duration.setOnFinished
									   (new EventHandler<ActionEvent>() {
									    public void handle(ActionEvent event) {

										aiStart = 1;
										computerAI();
										// aiStart = 0;
										canRestart = 0;
									    }
									   });
							}
						}
					}
				});

		root.getChildren().addAll(btn);

		// restart
		Button restart = new Button();
		AnchorPane.setTopAnchor(restart, 120.0);
		AnchorPane.setBottomAnchor(restart, 465.0);
		AnchorPane.setRightAnchor(restart, 210.0);
		AnchorPane.setLeftAnchor(restart, 700.0);
		restart.setStyle("-fx-font: 8 Helvetica;-fx-text-fill: white; "
				+ "-fx-background-color: #00688B;");
		restart.setOpacity(0.6);
		restart.setText("RESTART");
		root.getChildren().add(restart);
		restart.addEventHandler(MouseEvent.MOUSE_CLICKED,
				new EventHandler<MouseEvent>() {
					public void handle(MouseEvent event) {
						if (canRestart == 1) {
							music.stop();
							// move paradeLength
							int paradeLength = paradeList.size();
							for (int i = 0; i < paradeLength; i++) {
								root.getChildren().remove(paradeList.get(i));
							}
							// move player's cards on root
							int playerLength = playerList.size();
							for (int i = 0; i < playerLength; i++) {
								root.getChildren().remove(playerList.get(i));
							}
							// move computer's cards on root
							if (debugInt == 1) {
								int computerLength = computerList.size();
								for (int i = 0; i < computerLength; i++) {
									root.getChildren().remove(
											computerList.get(i));
								}
							}
							// move rest cards
							for (int i = 0; i < 6; i++) {
								int length = computer.get(i).size();
								for (int j = 0; j < length; j++) {
									root.getChildren().remove(
											computer.get(i).get(j));
								}
							}
							for (int i = 0; i < 6; i++) {
								int length = player.get(i).size();
								for (int j = 0; j < length; j++) {
									root.getChildren().remove(
											player.get(i).get(j));
								}
							}
							// initialize the majority
							for (int i = 0; i < 6; i++) {
								majorityPlayer.get(i).setFill(Color.RED);
							}

							for (int i = 0; i < 6; i++) {
								majorityComputer.get(i).setFill(Color.RED);
							}

							initializeAll();
							computerScore.setText("" + 0 + "");
							playerScore.setText("" + 0 + "");
							restCount.setText(" NUMBER OF CARDS LEFT: " + 50
									+ "");
							beginGame();
							btn.setText("PLAY");
						}
					}

				});

		// computer's rest cards and score
		Rectangle computerRest = new Rectangle(290, 220);
		computerRest.setFill(Color.BROWN);
		computerRest.setOpacity(0.2);
		AnchorPane.setBottomAnchor(computerRest, 235.0);
		AnchorPane.setRightAnchor(computerRest, 10.0);
		root.getChildren().addAll(computerRest);

		computerScore.setText(" " + compScore + " ");
		computerScore.setFill(Color.BURLYWOOD);
		computerScore.setFont(new Font(40));

		computerScorePane.setMinSize(40.0, 40.0);
		computerScorePane.setMaxSize(40.0, 40.0);
		AnchorPane.setBottomAnchor(computerScorePane, 235.0);
		AnchorPane.setRightAnchor(computerScorePane, 10.0);
		computerScorePane.getChildren().add(computerScore);
		root.getChildren().addAll(computerScorePane);

		// player's rest cards and score
		Rectangle playerRest = new Rectangle(290, 220);
		playerRest.setFill(Color.BROWN);
		playerRest.setOpacity(0.2);
		AnchorPane.setBottomAnchor(playerRest, 5.0);
		AnchorPane.setRightAnchor(playerRest, 10.0);
		root.getChildren().addAll(playerRest);

		playerScore.setText(" " + playScore + " ");
		playerScore.setFill(Color.BURLYWOOD);
		playerScore.setFont(new Font(40));

		playerScorePane.setMinSize(40.0, 40.0);
		playerScorePane.setMaxSize(40.0, 40.0);
		AnchorPane.setBottomAnchor(playerScorePane, 5.0);
		AnchorPane.setRightAnchor(playerScorePane, 10.0);
		playerScorePane.getChildren().add(playerScore);
		root.getChildren().addAll(playerScorePane);
		restCount.setText("                              CARDS LEFT: " + 50
				+ "");
		restCount.setFill(Color.BURLYWOOD);
		restCount.setFont(new Font(10));
		restCountPane.setMinSize(10.0, 50.0);
		restCountPane.setMaxSize(10.0, 50.0);
		AnchorPane.setTopAnchor(restCountPane, 160.0);
		AnchorPane.setLeftAnchor(restCountPane, 5.0);
		restCountPane.getChildren().add(restCount);
		root.getChildren().addAll(restCountPane);

		majority(majorityPlayer, 37.5, 47, 365);
		majority(majorityComputer, 37.5, 47, 145);
		// start the game
		beginGame();

	}

}