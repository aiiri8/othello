package othello.entity;

public record Point(int x, int y) {
  @Override
  public String toString() {
    return (char) ('a' + (char) y) + "" + (char) ('1' + (char) x);
  }
}
