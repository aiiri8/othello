package othello.rule;

import othello.entity.Board;
import othello.entity.CellColor;
import othello.entity.Point;

public class CellWeightChecker {

  public static double getCellWeight(Board board, Point point, CellColor color) {
    double ans = getWeightForPlacedCell(board.getSize(), point);
    for (int i = -1; i <= 1; ++i) {
      for (int j = -1; j <= 1; ++j) {
        if (i != 0 || j != 0) {
          if (checkDirection(board, point, i, j, color)) {
            ans += getWeightForDirection(board, point, i, j, color);
          }
        }
      }
    }
    return ans;
  }

  private static double getWeightForDirection(Board board, Point point, int rowStep, int colStep,
      CellColor color) {
    CellColor otherColor = ((color == CellColor.BLACK) ? CellColor.WHITE : CellColor.BLACK);
    double ans = 0;
    int row = point.x();
    int col = point.y();
    for (int i = 1; (!(rowStep > 0 && !(row + rowStep * i < board.getSize() - 1)) &&
        !(rowStep < 0 && !(row + rowStep * i > 0)) &&
        !(colStep > 0 && !(col + colStep * i < board.getSize() - 1)) &&
        !(colStep < 0 && !(col + colStep * i > 0))) &&
        board.getCellColor(new Point(row + rowStep * i, col + colStep * i)) == otherColor; i++) {
      ans += getWeightForRecoloredCell(board.getSize(),
          new Point(row + rowStep * i, col + colStep * i));
    }
    return ans;
  }

  private static double getWeightForRecoloredCell(int boardSize, Point point) {
    if (point.x() == 0 || point.x() == boardSize || point.y() == 0 || point.y() == boardSize) {
      return 2;
    }
    return 1;
  }

  private static double getWeightForPlacedCell(int boardSize, Point point) {
    if ((point.x() == 0 || point.x() == boardSize) && (point.y() == 0 || point.y() == boardSize)) {
      return 0.8;
    } else if (point.x() == 0 || point.x() == boardSize || point.y() == 0
        || point.y() == boardSize) {
      return 0.4;
    }
    return 0;
  }

  private static boolean checkDirection(Board board, Point point, int rowStep, int colStep,
      CellColor color) {
    CellColor otherColor = ((color == CellColor.BLACK) ? CellColor.WHITE : CellColor.BLACK);
    int distance = 0;
    int row = point.x();
    int col = point.y();
    for (int i = 1; (!(rowStep > 0 && !(row + rowStep * i < board.getSize() - 1)) &&
        !(rowStep < 0 && !(row + rowStep * i > 0)) &&
        !(colStep > 0 && !(col + colStep * i < board.getSize() - 1)) &&
        !(colStep < 0 && !(col + colStep * i > 0))) &&
        board.getCellColor(new Point(row + rowStep * i, col + colStep * i)) == otherColor; i++) {
      distance = i + 1;
    }
    return distance != 0 && board.getCellColor(new Point(row + distance * rowStep,
        col + distance * colStep)) == color;
  }
}
