package h01;

import fopbot.Robot;

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
  }

  /**
   * Exercise 3.1 of H01
   */
  private void rookMovement(Robot rook) {
    // Hier programmieren
  }

  /**
   * Exercise 3.2 of H01
   */
  private void bishopMovement(Robot rook, Robot bishop) {
    // Hier programmieren
  }
}
