package othello.player;

import java.util.List;
import othello.entity.Board;
import othello.entity.CellColor;
import othello.entity.Point;
import othello.io.InputController;

public class HumanPlayer extends Player {

  public HumanPlayer(CellColor color) {
    super(color);
  }

  @Override
  public Point chooseMove(Board board, List<Point> availablePoints) {
    return InputController.getPoint(availablePoints);
  }
}
