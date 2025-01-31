package gamestates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import entities.Player;
import levels.LevelManager;
import main.Game;
import ui.PauseOverlay;

public class Playing extends State implements Statemethods {
	private Player player;
	private LevelManager levelManager;
	private PauseOverlay pauseOverlay;
	private boolean paused = false;

	// TODO: add an int field named xLvlOffset
	// TODO: add an int field named leftBorder and assign (int) (0.2 * Game.GAME_WIDTH) to it
	// TODO: add an int field named rightBorder and assign (int) (0.8 * Game.GAME_WIDTH) to it
	// TODO: add an int field named lvlTilesWide and assign to LoadSave.GetLevelData()[0].length
	// TODO: add an int field named maxTilesOffset and assign lvlTilesWide - Game.TILES_IN_WIDTH to it
	// TODO: add an int field named maxLvlOffsetX and assign maxTilesOffset * Game.TILE_SIZE to it

	public Playing(Game game) {
		super(game);
		initClasses();
		// TODO:  add the commented out code here
		// if (!paused) {
		// 	levelManager.update();
		// 	player.update();
		// 	checkCloseToBorder();
		// } else {
		// 	pauseOverlay.update();
		// }
	}

	private void initClasses() {
		levelManager = new LevelManager(game);
		player = new Player(200, 200, (int) (64 * Game.SCALE), (int) (40 * Game.SCALE));
		player.loadLvlData(levelManager.getCurrentLevel().getLevelData());
		pauseOverlay = new PauseOverlay(this);
	}

	@Override
	public void update() {
		if (!paused) {
			levelManager.update();
			player.update();
		} else {
			pauseOverlay.update();
		}
	}

	// TODO:  add the commented out code here
	// private void checkCloseToBorder() {
	// 	int playerX = (int) player.getHitbox().x;
	// 	int diff = playerX - xLvlOffset;
	// 	if (diff > rightBorder)
	// 		xLvlOffset += diff - rightBorder;
	// 	else if (diff < leftBorder)
	// 		xLvlOffset += diff - leftBorder;
	// 	if (xLvlOffset > maxLvlOffsetX)
	// 		xLvlOffset = maxLvlOffsetX;
	// 	else if (xLvlOffset < 0)
	// 		xLvlOffset = 0;
	// }

	@Override
	public void draw(Graphics g) {
		levelManager.draw(g); // TODO: also pass in xLvlOffset
		player.render(g); // TODO: also pass in xLvlOffset

		if (paused)
			// TODO: call g.setColor and pass in a new Color(0, 0, 0, 150)
			// TODO: call g.fillRect and pass in 0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT
			pauseOverlay.draw(g);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1)
			player.setAttacking(true);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_A:
			player.setLeft(true);
			break;
		case KeyEvent.VK_D:
			player.setRight(true);
			break;
		case KeyEvent.VK_SPACE:
			player.setJump(true);
			break;
		case KeyEvent.VK_ESCAPE:
			paused = !paused;
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_A:
			player.setLeft(false);
			break;
		case KeyEvent.VK_D:
			player.setRight(false);
			break;
		case KeyEvent.VK_SPACE:
			player.setJump(false);
			break;
		}

	}

	public void mouseDragged(MouseEvent e) {
		if (paused)
			pauseOverlay.mouseDragged(e);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (paused)
			pauseOverlay.mousePressed(e);

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (paused)
			pauseOverlay.mouseReleased(e);

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (paused)
			pauseOverlay.mouseMoved(e);

	}

	public void unpauseGame() {
		paused = false;
	}

	public void windowFocusLost() {
		player.resetDirBooleans();
	}

	public Player getPlayer() {
		return player;
	}

}
