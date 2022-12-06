package othello;

import java.util.List;
import java.util.Stack;
import othello.entity.Board;
import othello.entity.BoardSnapshot;
import othello.entity.CellColor;
import othello.entity.Point;
import othello.io.GameMode;
import othello.io.InputController;
import othello.io.MoveType;
import othello.io.OutputController;
import othello.rule.BoardController;
import othello.player.EasyPcPlayer;
import othello.player.HardPcPlayer;
import othello.player.HumanPlayer;
import othello.player.Player;

public class GameController {

  private static final int BOARD_SIZE = 8;

  public static void createGame(GameMode mode) {
    Player blackPlayer = new HumanPlayer(CellColor.BLACK);
    Player whitePlayer = switch (mode) {
      case START_EASY_PVE -> new EasyPcPlayer(CellColor.WHITE);
      case START_HARD_PVE -> new HardPcPlayer(CellColor.WHITE);
      default -> new HumanPlayer(CellColor.WHITE);
    };
    Board board = new Board(BOARD_SIZE);
    runGame(blackPlayer, whitePlayer, board);
    int blackScores = board.getNumberOfCells(CellColor.BLACK);
    int whiteScores = board.getNumberOfCells(CellColor.WHITE);
    System.out.println(
        "Игра завершена! Победитель - " + (blackScores >= whiteScores ? "черные!" : "белые!"));
    System.out.println("На поле " + blackScores + "черных фишек и " + whiteScores + "белых!");
    BestScores.update(blackScores, whiteScores);
  }

  private static void runGame(Player blackPlayer, Player whitePlayer, Board board) {
    Stack<BoardSnapshot> moves = new Stack<>();
    boolean isBlackTurn = true;
    int numOfSkips = 0;
    do {
      Player currentPlayer = (isBlackTurn ? blackPlayer : whitePlayer);
      System.out.println("Ход " + (isBlackTurn ? "черных!" : "белых!"));
      List<Point> availablePoints = BoardController.findPossibleMoves(board,
          currentPlayer.getColor());
      OutputController.printBoard(board, availablePoints);
      MoveType move = InputController.getMove();
      if (move == MoveType.UNDO) {
        if (moves.isEmpty()) {
          board = new Board(BOARD_SIZE);
          isBlackTurn = true;
        } else {
          BoardSnapshot lastBoard = moves.pop();
          board.setFrom(lastBoard.board());
          isBlackTurn = (lastBoard.color() == CellColor.BLACK);
        }
        continue;
      }
      if (availablePoints.isEmpty()) {
        System.out.println("Увы, нет доступных ходов!");
        numOfSkips++;
      } else {
        Point point = currentPlayer.chooseMove(board, availablePoints);
        BoardController.recolorBoard(board, point, currentPlayer.getColor());
        moves.push(new BoardSnapshot(board.copy(), currentPlayer.getColor()));
      }
      isBlackTurn = !isBlackTurn;
    } while (numOfSkips < 2);
  }
}
