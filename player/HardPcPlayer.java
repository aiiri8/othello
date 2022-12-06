package othello.player;

import java.util.List;
import othello.rule.BoardController;
import othello.rule.CellWeightChecker;
import othello.entity.Board;
import othello.entity.CellColor;
import othello.entity.Point;

public class HardPcPlayer extends Player {

  public HardPcPlayer(CellColor color) {
    super(color);
  }

  @Override
  public Point chooseMove(Board board, List<Point> availablePoints) {
    Point selectedPoint = availablePoints.get(0);
    double maxWeight = CellWeightChecker.getCellWeight(board, selectedPoint, color)
        - getMaxWeightForOpponent(board, selectedPoint);
    for (var point : availablePoints) {
      if (CellWeightChecker.getCellWeight(board, point, color) > maxWeight) {
        selectedPoint = point;
        maxWeight = CellWeightChecker.getCellWeight(board, selectedPoint, color)
            - getMaxWeightForOpponent(board, selectedPoint);
      }
    }
    return selectedPoint;
  }

  private double getMaxWeightForOpponent(Board board, Point selectedPoint) {
    Board tempBoard;
    tempBoard = board.copy();
    BoardController.recolorBoard(tempBoard, selectedPoint, color);
    CellColor otherColor = ((color == CellColor.BLACK) ? CellColor.WHITE : CellColor.BLACK);
    EasyPcPlayer possibleOpponent = new EasyPcPlayer(otherColor);
    try{
      return CellWeightChecker.getCellWeight(tempBoard, possibleOpponent.chooseMove(tempBoard,
          BoardController.findPossibleMoves(tempBoard, otherColor)), otherColor);
    } catch (Exception e) { // Если у второго игрока не останется ходов
      return 0;
    }
  }
}
