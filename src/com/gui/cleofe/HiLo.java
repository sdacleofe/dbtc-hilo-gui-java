package com.gui.cleofe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class HiLo extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JLabel currentCardFiller;

	private JLabel nextCardFiller;

	private JLabel scoreLabel;

	private JLabel announcementLabel;

	@SuppressWarnings("unused")
	private ArrayList<String> currentCard = new ArrayList<String>();

	private JPanel[] panels = new JPanel[5];

	private String[] suit = { "S", "D", "C", "H" };

	private String[] rank = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A" };

	private String[] deck = new String[52];

	private JButton hiButton;

	private JButton loButton;

	private ImageIcon[] cards = new ImageIcon[52];

	private Image cardImage[] = new Image[52];

	private ImageIcon cardIcon[] = new ImageIcon[52];

	private ImageIcon animatedCards = new ImageIcon();

	private static final int SCALE_WIDTH = 200;

	private static final int SCALE_HEIGHT = 300;

	private int min = 0;

	private int max = 51;

	@SuppressWarnings("unused")
	private boolean flag = true;

	private boolean isWin = true;

	private int generatedNumber;

	private int previousIndex;

	private char nextChar = 0;

	private char curChar = 0;

	private String nextCardStr;

	private String curCharStr;

	private int guessnumber;

	private int currentnumber;

	private int score = 10;

	HiLo() {
		initGUI();
	}

	private void initGUI() {
		this.setLayout(new BorderLayout());

		panels[0] = new JPanel();
		panels[1] = new JPanel();
		panels[2] = new JPanel();
		panels[3] = new JPanel();
		panels[4] = new JPanel();

		panels[0].setPreferredSize(new Dimension(400, 40));
		// panels[1].setPreferredSize(new Dimension(250, 400));
		panels[2].setPreferredSize(new Dimension(250, 400));
		panels[4].setPreferredSize(new Dimension(250, 400));
		panels[3].setPreferredSize(new Dimension(400, 150));

		panels[0].setBackground(Color.BLACK);
		panels[1].setBackground(Color.WHITE);
		panels[2].setBackground(Color.WHITE);
		panels[3].setBackground(Color.BLACK);
		panels[4].setBackground(Color.WHITE);

		add(panels[0], BorderLayout.NORTH);
		add(panels[1], BorderLayout.EAST);
		add(panels[2], BorderLayout.WEST);
		add(panels[3], BorderLayout.SOUTH);
		add(panels[4], BorderLayout.CENTER);

		ImageIcon cards = new ImageIcon("./cards/2S.png");
		cardImage[0] = cards.getImage().getScaledInstance(SCALE_WIDTH, SCALE_HEIGHT, java.awt.Image.SCALE_SMOOTH);
		cardIcon[0] = new ImageIcon(cardImage[0]);

		JLabel announcement = new JLabel("Let's play Hi-Lo Card Game!", SwingConstants.CENTER);
		announcement.setForeground(Color.WHITE);
		announcement.setFont(new Font("Open Sans Extrabold", Font.BOLD, 28));
		panels[0].setLayout(new BorderLayout());
		panels[0].add(announcement, BorderLayout.NORTH);

		JLabel currentCard = new JLabel("Current Card", SwingConstants.CENTER);
		currentCardFiller = new JLabel("", cardIcon[0], JLabel.CENTER);
		panels[2].setLayout(new BorderLayout());
		panels[2].add(currentCard, BorderLayout.NORTH);
		panels[2].add(currentCardFiller, BorderLayout.CENTER);
		currentCard.setFont(new Font("Open Sans Extrabold", Font.BOLD, 28));

		JLabel nextCard = new JLabel("Next Card", SwingConstants.CENTER);
		animatedCards = new ImageIcon("./cards/animatedcards.gif");
		nextCardFiller = new JLabel("", animatedCards, JLabel.CENTER);
		panels[4].setLayout(new BorderLayout());
		panels[4].add(nextCard, BorderLayout.NORTH);
		panels[4].add(nextCardFiller, BorderLayout.CENTER);
		nextCard.setFont(new Font("Open Sans Extrabold", Font.BOLD, 28));

		hiButton = new JButton("Hi");
		loButton = new JButton("Lo");
		panels[3].setLayout(new BorderLayout());

		hiButton.setBackground(Color.BLACK);
		loButton.setBackground(Color.BLACK);
		hiButton.setForeground(Color.WHITE);
		loButton.setForeground(Color.WHITE);

		JPanel controlPanel = new JPanel();
		panels[3].add(controlPanel, BorderLayout.SOUTH);

		controlPanel.setLayout(new GridLayout(1, 2));
		controlPanel.add(hiButton);
		controlPanel.add(loButton);

		JPanel scorePanel = new JPanel();
		panels[3].add(scorePanel, BorderLayout.CENTER);

		scoreLabel = new JLabel("Your Score is " + score);
		scorePanel.add(scoreLabel);
		scorePanel.setBackground(Color.WHITE);
		scoreLabel.setFont(new Font("Open Sans Extrabold", Font.BOLD, 26));

		JPanel announcementPanel = new JPanel();
		panels[3].add(announcementPanel, BorderLayout.NORTH);

		announcementLabel = new JLabel("");
		announcementPanel.add(announcementLabel);
		announcementPanel.setBackground(Color.WHITE);
		announcementLabel.setFont(new Font("Open Sans Extrabold", Font.BOLD, 22));

		hiButton.setFont(new Font("Open Sans Extrabold", Font.BOLD, 32));
		loButton.setFont(new Font("Open Sans Extrabold", Font.BOLD, 32));

		loButton.addActionListener(this);
		hiButton.addActionListener(this);
	}

	public void cardsToInteger() {
		int nextcard = 0;
		int currentcard = 0;

		if (getNextCardStr().equals("K")) {
			nextcard = 13;
		} else if (getNextCardStr().equals("Q")) {
			nextcard = 12;
		} else if (getNextCardStr().equals("J")) {
			nextcard = 11;
		} else if (getNextCardStr().equals("A")) {
			nextcard = 1;
		} else if (getNextCardStr().equals("0")) {
			nextcard = 10;
		}

		for (int counter = 2; counter < 10; counter++) {
			if (getNextCardStr().equals("" + counter + "")) {
				nextcard = counter;
			}
		}

		if (getCurrentCardStr().equals("K")) {
			currentcard = 13;
		} else if (getCurrentCardStr().equals("Q")) {
			currentcard = 12;
		} else if (getCurrentCardStr().equals("J")) {
			currentcard = 11;
		} else if (getCurrentCardStr().equals("A")) {
			currentcard = 1;
		} else if (getCurrentCardStr().equals("0")) {
			currentcard = 10;
		}

		for (int counter = 2; counter < 10; counter++) {
			if (getCurrentCardStr().equals("" + counter + "")) {
				currentcard = counter;
			}
		}

		setCurrentCard(currentcard);
		setGuessCard(nextcard);
	}

	private void setGuessCard(int guessnumber) {
		this.guessnumber = guessnumber;
	}

	private void setCurrentCard(int currentnumber) {
		this.currentnumber = currentnumber;
	}

	private int getCurrentCard() {
		return this.currentnumber;
	}

	private int getNextCard() {
		return this.guessnumber;
	}

	public void setscore(boolean isWin) {
		if (isWin) {
			for (int i = 0; i < 2; i++) {
				score += 10;
			}
			announcementLabel.setText("You Win");
			announcementLabel.setForeground(Color.GREEN);
			scoreLabel.setText("Your Score is " + score);
		} else {
			for (int i = 0; i < 1; i++) {
				score -= 10;
			}
			announcementLabel.setText("You Lose");
			announcementLabel.setForeground(Color.RED);
			scoreLabel.setText("Your Score is " + score);
		}
	}
	boolean isdraw;
	public void setAnswer(boolean flag) {
		cardsToInteger();
		if (flag) {
			if (getCurrentCard() > getNextCard()) {
				isWin = true;
				setscore(isWin);
			} else if (getCurrentCard() < getNextCard()) {
				isWin = false;
				setscore(isWin);
			} else if (getCurrentCard() == getNextCard()) {
				
			}
		} else {
			if (getCurrentCard() < getNextCard()) {
				isWin = true;
				setscore(isWin);
			} else if (getCurrentCard() > getNextCard()) {
				isWin = false;
				setscore(isWin);
			} else if (getCurrentCard() == getNextCard()) {
				
			}
		}

		if (score == 0) {

			UIManager.put("Panel.background", Color.WHITE);
			String message = "THE GAME IS OVER.";
			JOptionPane.showMessageDialog(null, message, "", JOptionPane.PLAIN_MESSAGE);

			score = 10;
			announcementLabel.setForeground(Color.BLACK);
			announcementLabel.setText("");
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		JButton source = (JButton) e.getSource();

		if (source.getText().equals("Hi")) {
			execute();
			setAnswer(true);
		}

		if (source.getText().equals("Lo")) {
			execute();
			setAnswer(false);
		}
	}

	public void execute() {
		try {
			@SuppressWarnings("unused")
			String extractGeneratedNumberStr;

			@SuppressWarnings("unused")
			int rankNumber = 0;

			generatedNumber = (int) (Math.random() * (max - min + 1) + min);

			// arrange the deck
			for (int index = 0; index < deck.length; index++) {
				deck[index] = rank[index % 13] + suit[index / 13];
			}

			// instantiate the cards into the arr
			for (int index = 0; index < deck.length; index++) {
				cards[index] = new ImageIcon("./cards/" + deck[index] + ".png");
			}

			// scale the size of the cards
			for (int index = 0; index < deck.length; index++) {
				cardImage[index] = cards[index].getImage().getScaledInstance(SCALE_WIDTH, SCALE_HEIGHT,
						java.awt.Image.SCALE_SMOOTH);
				cardIcon[index] = new ImageIcon(cardImage[index]);
			}

			// find the index of the arr from a given value
			String findCardString = deck[generatedNumber];

			@SuppressWarnings("unused")
			int currentIndex = Arrays.asList(deck).indexOf(findCardString);

			// set the image of the previous card
			currentCardFiller.setIcon(cardIcon[previousIndex]);

			// save the previous card
			StringBuffer sbCur = new StringBuffer(deck[previousIndex]);

			// get the previous index card for the next event action
			previousIndex = generatedNumber;

			// save the generated card
			StringBuffer sbNext = new StringBuffer(deck[generatedNumber]);

			// set the image of the random card
			nextCardFiller.setIcon(cardIcon[generatedNumber]);

			// delete the last letter of the values ex. "10S" -> "10"
			sbCur.deleteCharAt(sbCur.length() - 1);
			sbNext.deleteCharAt(sbNext.length() - 1);

			// convert into string
			String curStrNum;
			String nextCardStrNum;
			curStrNum = sbCur.toString();
			nextCardStrNum = sbNext.toString();

			// get the last character of the next card ex. "10" -> "0"
			curChar = curStrNum.charAt(curStrNum.length() - 1);
			curCharStr = Character.toString(curChar);

			switch (curCharStr) {
			case "K":
			case "Q":
			case "J":
				setCurrentCardStr(curCharStr);
				break;
			case "1":
			case "2":
			case "3":
			case "4":
			case "5":
			case "6":
			case "7":
			case "8":
			case "9":
			case "10":
				setCurrentCardStr(curCharStr);
				break;
			}

			// get the last character of the next card ex. "10" -> "0"
			nextChar = nextCardStrNum.charAt(nextCardStrNum.length() - 1);
			nextCardStr = Character.toString(nextChar);

			switch (nextCardStr) {
			case "K":
			case "Q":
			case "J":
				setNextCardStr(nextCardStr);
				break;
			case "1":
			case "2":
			case "3":
			case "4":
			case "5":
			case "6":
			case "7":
			case "8":
			case "9":
			case "10":
				setNextCardStr(nextCardStr);
				break;
			}
		} catch (ArrayIndexOutOfBoundsException aiobe) {
			/* do nothing if index out of bounds */ }
	}

	private String getCurrentCardStr() {
		return this.curCharStr;
	}

	private void setCurrentCardStr(String curCharStr) {
		this.curCharStr = curCharStr;
	}

	private String getNextCardStr() {
		return this.nextCardStr;
	}

	private void setNextCardStr(String nextCardStr) {
		this.nextCardStr = nextCardStr;
	}

	public static void main(String[] args) {
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {

				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
		}
		HiLo frame = new HiLo();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setTitle("Hi - Lo");
	}
}

