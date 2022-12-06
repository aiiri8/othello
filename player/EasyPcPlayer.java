package othello.player;

import java.util.List;
import othello.rule.CellWeightChecker;
import othello.entity.Board;
import othello.entity.CellColor;
import othello.entity.Point;

public class EasyPcPlayer extends Player {

  public EasyPcPlayer(CellColor color) {
    super(color);
  }

  @Override
  public Point chooseMove(Board board, List<Point> availablePoints) {
    Point selectedPoint = availablePoints.get(0);
    double maxWeight = CellWeightChecker.getCellWeight(board, selectedPoint, color);
    for (var point : availablePoints) {
      if (CellWeightChecker.getCellWeight(board, point, color) > maxWeight) {
        selectedPoint = point;
        maxWeight = CellWeightChecker.getCellWeight(board, selectedPoint, color);
      }
    }
    return selectedPoint;
  }
}
