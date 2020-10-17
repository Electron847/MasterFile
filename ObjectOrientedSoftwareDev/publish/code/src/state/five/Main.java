package state.five;

public class Main {
  public static void main(String[] args) {
    C x = new C();
    System.out.println(x.f());
    System.out.println(x.f());
    System.out.println(x.f());
    x.changeDirection();
    System.out.println(x.f());
    System.out.println(x.f());
    System.out.println(x.f());
    x.changeDirection();
    System.out.println(x.g());
    System.out.println(x.g());
    System.out.println(x.g());
    x.changeDirection();
    System.out.println(x.g());
    System.out.println(x.g());
    System.out.println(x.g());
  }
}
