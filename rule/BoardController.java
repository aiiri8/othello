package othello.rule;

import java.util.LinkedList;
import othello.entity.Board;
import othello.entity.CellColor;
import othello.entity.Point;

public class BoardController {

  public static LinkedList<Point> findPossibleMoves(Board board, CellColor currentColor) {
    LinkedList<Point> availablePoints = new LinkedList<Point>();
    for (int row = 0; row < board.getSize(); ++row) {
      for (int col = 0; col < board.getSize(); ++col) {
        Point point = new Point(row, col);
        if (board.getCellColor(point) == null) {
          if (checkAvailability(board, point, currentColor)) {
            availablePoints.add(point);
          }
        }
      }
    }
    return availablePoints;
  }

  public static void recolorBoard(Board board, Point point, CellColor color) {
    board.setCellColor(point, color);
    for (int i = -1; i <= 1; ++i) {
      for (int j = -1; j <= 1; ++j) {
        if (i != 0 || j != 0) {
          if (checkDirection(board, point, i, j, color)) {
            recolorDirection(board, point, i, j, color);
          }
        }
      }
    }
  }

  private static void recolorDirection(Board board, Point point, int rowStep, int colStep,
      CellColor currentColor) {
    CellColor otherColor = ((currentColor == CellColor.BLACK) ? CellColor.WHITE : CellColor.BLACK);
    int row = point.x();
    int col = point.y();
    for (int i = 1; (!(rowStep > 0 && !(row + rowStep * i < board.getSize() - 1)) &&
        !(rowStep < 0 && !(row + rowStep * i > 0)) &&
        !(colStep > 0 && !(col + colStep * i < board.getSize() - 1)) &&
        !(colStep < 0 && !(col + colStep * i > 0))) &&
        board.getCellColor(new Point(row + rowStep * i, col + colStep * i)) == otherColor; i++) {
      board.setCellColor(new Point(row + rowStep * i, col + colStep * i), currentColor);
    }
  }

  private static boolean checkAvailability(Board board, Point point, CellColor currentColor) {
    for (int i = -1; i <= 1; ++i) {
      for (int j = -1; j <= 1; ++j) {
        if (i != 0 || j != 0) {
          if (checkDirection(board, point, i, j, currentColor)) {
            return true;
          }
        }
      }
    }
    return false;
  }

  private static boolean checkDirection(Board board, Point point, int rowStep, int colStep,
      CellColor currentColor) {
    CellColor otherColor = ((currentColor == CellColor.BLACK) ? CellColor.WHITE : CellColor.BLACK);
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
        col + distance * colStep)) == currentColor;
  }
}
