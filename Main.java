package othello;

import othello.io.InputController;

public class Main {

  public static void main(String[] args) {
    try {
      while (true) {
        printMainMenu();
        var option = InputController.getMainMenuOption();
        switch (option) {
          case START_GAME -> {
            printGameModeMenu();
            var mode = InputController.getGameMode();
            GameController.createGame(mode);
          }
          case PRINT_SCORES -> BestScores.printScores();
          default -> {
            System.out.println("До новых встреч!");
            return;
          }
        }
      }
    } catch (Exception e) {
      System.out.println();
      System.out.println("Что-то пошло не так:(");
      System.out.println(e.getMessage());
    }
  }

  private static void printMainMenu() {
    System.out.println("""
        Reversi // Othello!
        1. Новая игра;
        2. Вывести лучший результат в текущей сессии;
        3. Выйти из игры.
        """);
  }

  private static void printGameModeMenu() {
    System.out.println("""
        Режимы игры:
        1. Игрок VS Компьютер (легкий режим);
        2. Игрок VS Компьютер (продвинутый режим);
        3. Игрок VS Игрок.
        """);
  }
}