package othello.io;

import java.util.List;
import java.util.Scanner;
import othello.entity.Point;

public class InputController {

  private final static Scanner scanner = new Scanner(System.in);

  public static MenuOption getMainMenuOption() {
    System.out.println("Введите число, соответствующее пункту меню:");
    String input;
    while (true) {
      input = scanner.nextLine().strip();
      if (input.length() == 1 && '1' <= input.charAt(0) && input.charAt(0) <= '3') {
        MenuOption option = MenuOption.values()[Integer.parseInt(input) - 1];
        System.out.println("Вы выбрали команду " + option + "!");
        return option;
      } else {
        System.out.println("Введено некорректное значение, повторите ввод!");
      }
    }
  }

  public static GameMode getGameMode() {
    System.out.println("Введите число, соответствующее режиму игры:");
    String input;
    while (true) {
      input = scanner.nextLine().strip();
      if (input.length() == 1 && '1' <= input.charAt(0) && input.charAt(0) <= '3') {
        GameMode mode = GameMode.values()[Integer.parseInt(input) - 1];
        System.out.println("Вы выбрали режим игры " + mode + "!");
        return mode;
      } else {
        System.out.println("Введено некорректное значение, повторите ввод!");
      }
    }
  }

  public static MoveType getMove() {
    System.out.println("Введите move, чтобы сделать ход, и undo, чтобы отменить последний ход!");
    String input;
    while (true) {
      input = scanner.nextLine().strip().toLowerCase();
      if (input.equals("move")) {
        MoveType move = MoveType.MOVE;
        System.out.println("Вы решили сделать ход!");
        return move;
      } else if (input.equals("undo")) {
        MoveType move = MoveType.UNDO;
        System.out.println("Вы решили отменить прошлый ход!");
        return move;
      } else {
        System.out.println("Введено некорректное значение, повторите ввод!");
      }
    }
  }

  public static Point getPoint(List<Point> availablePoints) {
    String input;
    while (true) {
      System.out.println("Введите координаты клетки в формате [буква][число] (пример - a1):");
      input = scanner.nextLine().toLowerCase().strip();
      if ((input.length() == 2 && 'a' <= input.charAt(0) && input.charAt(0) <= 'h'
          && '1' <= input.charAt(1) && input.charAt(1) <= '8')) {
        Point point = createPoint(input);
        if (availablePoints.contains(point)) {
          return point;
        } else {
          System.out.println("Точка недоступна для хода!");
        }
      } else {
        System.out.println("Введено некорректное значение!");
      }
    }
  }

  private static Point createPoint(String input) {
    return new Point(input.charAt(1) - '1', input.charAt(0) - 'a');
  }
}