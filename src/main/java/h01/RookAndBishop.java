package h01;

import fopbot.Direction;
import fopbot.Robot;
import fopbot.World;

import java.util.concurrent.ThreadLocalRandom;

public class RookAndBishop {
  private final int NUMBER_OF_ROWS;
  private final int NUMBER_OF_COLUMNS;
  private final int nextFrameDelay;
  private final boolean uiVisible;

  public RookAndBishop(int rows, int columns, int nextFrameDelay, boolean uiVisible) {
    this.nextFrameDelay = nextFrameDelay;
    this.uiVisible = uiVisible;
    this.NUMBER_OF_ROWS = rows > 0 ? rows : Task1.readProperty("NUMBER_OF_ROWS", Task1.TO_INTEGER);
    this.NUMBER_OF_COLUMNS = columns > 0 ? columns : Task1.readProperty("NUMBER_OF_COLUMNS", Task1.TO_INTEGER);
  }

  public RookAndBishop(int nextFrameDelay, boolean uiVisible) {
    this(-1, -1, nextFrameDelay, uiVisible);
  }

  public RookAndBishop() {
    this(20, true);
  }

  public void execute() {
    Task1.initializeTask(NUMBER_OF_ROWS, NUMBER_OF_COLUMNS, nextFrameDelay, uiVisible);
    // Hier programmieren
    Robot rook = new Robot(ThreadLocalRandom.current().nextInt(NUMBER_OF_COLUMNS), ThreadLocalRandom.current().nextInt(NUMBER_OF_ROWS), toDirection(ThreadLocalRandom.current().nextInt(4)), ThreadLocalRandom.current().nextInt(12, 21));
    Robot bishop = new Robot(ThreadLocalRandom.current().nextInt(NUMBER_OF_COLUMNS), ThreadLocalRandom.current().nextInt(NUMBER_OF_ROWS), toDirection(ThreadLocalRandom.current().nextInt(4)), 0);

    // Main Loop
    while (true) {
      rookMovement(rook);
      bishopMovement(rook, bishop);
      if (!rook.hasAnyCoins()) {
        System.out.println("Der Turm hat Gewonnen!");
        break;
      }
      if (bishop.getX() == rook.getX() && bishop.getY() == rook.getY()) {
        System.out.println("Der Läufer hat Gewonnen!");
        break;
      }
    }
  }

  private Direction toDirection(int randomValue) {
    switch (randomValue) {
      case 0:
        return Direction.UP;
      case 1:
        return Direction.RIGHT;
      case 2:
        return Direction.DOWN;
      case 3:
        return Direction.LEFT;
      default:
        throw new IllegalArgumentException("upsss...somthing went wrong!");
    }
  }

  /**
   * Exercise 3.1 of H01
   */
  private void rookMovement(Robot rook) {
    // Hier programmieren
      rook.putCoin();
      if (canMove(rook)) {
        rook.move();
      } else {
        int turn = ThreadLocalRandom.current().nextInt(4);
        if (turn > 0) {
          rook.turnLeft();
          rook.turnLeft();
        }
        if (turn == 1) {
          rook.turnLeft();
        }
        if (turn == 0) {
          rook.turnLeft();
          rook.turnLeft();
          rook.turnLeft();
        }
      }

  }

  private boolean canMove(Robot bot) {
    switch (bot.getDirection()) {
      case UP:
        return bot.getY() != World.getHeight() - 1;
      case DOWN:
        return bot.getY() != 0;
      case RIGHT:
        return bot.getX() != World.getWidth() - 1;
      case LEFT:
        return bot.getX() != 0;
      default:
        throw new IllegalArgumentException("ups...Something went wrong!");
    }
  }

  /**
   * Exercise 3.2 of H01
   */
  private void bishopMovement(Robot rook, Robot bishop) {
    // Hier programmieren
    boolean notFinished = true;
    while (notFinished) {
      if (canMove(bishop)) {
        bishop.move();
        bishop.turnLeft();
        if (canMove(bishop)) {
          bishop.move();
          bishop.turnLeft();
          bishop.turnLeft();
          bishop.turnLeft();
        } else {
          bishop.turnLeft();
          bishop.turnLeft();
          notFinished = false;
        }
      } else {
        bishop.turnLeft();
        notFinished = false;
      }

      if (bishop.getX() == rook.getX() && bishop.getY() == rook.getY()) {
        notFinished = false;
      }

      if (bishop.isNextToACoin()) {
        bishop.pickCoin();
        notFinished = false;
      }
    }
  }
}
