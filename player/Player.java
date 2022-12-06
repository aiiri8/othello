package othello.player;

import java.util.List;
import othello.entity.Board;
import othello.entity.CellColor;
import othello.entity.Point;

public abstract class Player {

  protected final CellColor color;

  public Player(CellColor color) {
    this.color = color;
  }

  public CellColor getColor() {
    return color;
  }

  public abstract Point chooseMove(Board board, List<Point> availablePoints);
}
