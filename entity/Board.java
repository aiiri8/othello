package othello.entity;

public class Board {

  private final int size;
  private final CellColor[][] board;

  public Board(int size) {
    this.size = size;
    board = new CellColor[size][size];
    board[4][3] = CellColor.WHITE;
    board[3][4] = CellColor.WHITE;
    board[3][3] = CellColor.BLACK;
    board[4][4] = CellColor.BLACK;
  }

  public int getNumberOfCells(CellColor color) {
    int ans = 0;
    for (int i = 0; i < size; ++i) {
      for (int j = 0; j < size; ++j) {
        if (board[i][j] == color) {
          ++ans;
        }
      }
    }
    return ans;
  }

  public int getSize() {
    return size;
  }

  public CellColor getCellColor(Point point) {
    return board[point.x()][point.y()];
  }

  public void setCellColor(Point point, CellColor color) {
    board[point.x()][point.y()] = color;
  }

  public Board copy() {
    Board copyBoard = new Board(size);
    for (int row = 0; row < size; row++) {
      System.arraycopy(this.board[row], 0, copyBoard.board[row], 0, size);
    }
    return copyBoard;
  }

  public void setFrom(Board other) {
    for (int row = 0; row < size; row++) {
      System.arraycopy(other.board[row], 0, this.board[row], 0, size);
    }
  }
}
