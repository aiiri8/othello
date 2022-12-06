package othello;

public class BestScores {

  static int blackScore = 0, whiteScore = 0;

  public static void update(int currentBlackScore, int currentWhiteScore) {
    blackScore = (Math.max(currentBlackScore, blackScore));
    whiteScore = (Math.max(currentWhiteScore, whiteScore));
  }

  public static void printScores() {
    System.out.println(
        "Лучший результат для черных: " + blackScore + ", для белых: " + whiteScore + "!");
  }
}
