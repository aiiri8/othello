package othello.io;

import java.util.List;
import othello.entity.Board;
import othello.entity.CellColor;
import othello.entity.Point;

public class OutputController {

  public static void printBoard(Board board, List<Point> availablePoints) {
    System.out.printf("\t%s\n", "-".repeat(4 * board.getSize() + 1));
    for (int row = board.getSize() - 1; row >= 0; --row) {
      System.out.print(row + 1);
      System.out.print('\t');
      for (int col = 0; col < board.getSize(); ++col) {
        Point point = new Point(row, col);
        if (board.getCellColor(point) == null) {
          if (availablePoints.contains(point)) {
            System.out.print("| ◌ ");
          } else {
            System.out.print("|   ");
          }
        } else if (board.getCellColor(point) == CellColor.BLACK) {
          System.out.print("| ◯ ");
        } else {
          System.out.print("| ● ");
        }
      }
      System.out.printf("|\n\t%s%n", "-".repeat(4 * board.getSize() + 1));
    }
    System.out.print('\t');
    for (int col = 0; col < board.getSize(); ++col) {
      System.out.printf("  %s ", (char) ('a' + (char) col));
    }
    System.out.println();
    System.out.println("Для хода доступны следующие клетки:");
    for (var point : availablePoints) {
      System.out.print(point + "; ");
    }
    System.out.println();
  }
}
