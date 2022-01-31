package Logic;

import java.io.Serializable;
import java.util.Random;

import Computer.Tree;
import SentObjects.Movement;
import Tools.DummyPawn;
import Tools.King;
import Tools.Tool;

public class Game implements Serializable, Cloneable {

	private static final long serialVersionUID = 1L;
	private Player[] players;
	private Board board;
	private boolean turn;
	private Colors currentPlayerColor = Colors.WHITE;
	private int gameIndex, amount =0;
	private boolean againstComputer = false;
	private Movement lastMove;
	private String retiredPlayer;
	
	
	public Object clone() throws CloneNotSupportedException {
		Game g = (Game) super.clone();
		g.players = new Player[2];
		for(int i = 0; i < players.length; i++)
			g.players[i] = (Player) players[i].clone();
		g.board = (Board) board.clone();
		g.turn = turn;
		g.currentPlayerColor = currentPlayerColor;
		g.gameIndex = gameIndex;
		g.amount = amount;
		g.againstComputer = againstComputer;
		g.lastMove = (Movement) lastMove.clone();
		g.retiredPlayer = retiredPlayer;
		
		return g;
	}
	
	public Game(String name, int gameIndex, int amount) {
		
		this.players = new Player[2];
		this.gameIndex = gameIndex;
		players[0] = new Player(name, false);
		this.amount = amount;
	}
	
	public void buildBoard() {
		Tree tree = new Tree();
		this.board  = new Board(tree);
		board.buildBoard();
	}
	
	public Board getBoard() {
		return this.board;
	}
	
	public void setLastMove(Movement lastMove) {
		this.lastMove = lastMove;
	}
	
	public Movement getLastMove() {
		return lastMove;
	}
	
	public boolean isAgainstComputer() {
		return againstComputer;
	}

	public void setAgainstComputer(boolean againstComputer) {
		this.againstComputer = againstComputer;
	}

	public void drawColors() {
		if((new Random()).nextInt(2) == 1) {
			players[0].setColor(Colors.WHITE);
			players[1].setColor(Colors.BLACK);
			this.turn = true; // player0 turn
		}
		else {
			players[0].setColor(Colors.BLACK);
			players[1].setColor(Colors.WHITE);
			this.turn = false; // player1 turn
		}	
	}
	
	public void setSecondPlayer(String name) {
		players[1] = new Player(name, false );
	}
	
	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public String[] getUsernames() {
		String[] usernames = new String[2];
		int i = 0;
		for(Player player: players) 
			usernames[i++] = player.getUsername();
		return usernames;
	}
	
	public int getGameIndex() {
		return gameIndex;
	}

	public void setGameIndex(int gameIndex) {
		this.gameIndex = gameIndex;
	}

	public void startGame(String username) {
		setSecondPlayer(username);
		drawColors();
		buildBoard();
	}
	
	public void startGameVSComputer() {
		players[1] = new Player("Computer", true );
		this.againstComputer = true;
		players[0].setColor(Colors.WHITE);
		players[1].setColor(Colors.BLACK);
		this.turn = true; // player1 turn
		buildBoard();
	}
	
	public Player getPlayer(int idx) {
		return players[idx];
	}
	
	public Player[] getPlayers() {
		return players;
	}
	
	public boolean isMyTurn(Colors color) {
		if(currentPlayerColor == color)
			return true;
		return false;
	}
	
	public boolean getTurn() {
		return turn; //true - player 0 turn
	}
	
	public Colors getCurrentPlayerColor() {
		return this.currentPlayerColor;
	}

	public void changeTurn()
	{
		turn = !turn;
		if(currentPlayerColor == Colors.WHITE ) 
			currentPlayerColor = Colors.BLACK;
		else
			currentPlayerColor = Colors.WHITE;
		board.setCurrentPlayerColor(currentPlayerColor);
	}
	
	public String getOpponentUserName(String username) {
		if(players[0].getUsername().equals(username))
			return players[1].getUsername();
		return players[0].getUsername();
	}

	public String getRetiredPlayer() {
		return retiredPlayer;
	}

	public void setRetiredPlayer(String retiredPlayer) {
		this.retiredPlayer = retiredPlayer;
	}
	
}
