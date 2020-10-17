package music;
class Main {
  public static void main(String[] args) {
    EventGroup eg1 = new EventGroup();
    eg1.add(new Note(250, 2.0));
    eg1.add(new Rest(250));
    eg1.add(new Note(500, 1.0));
    eg1.add(new Rest(500));
    eg1.add(new Note(1000, 0.5));

    EventGroup eg2 = new EventGroup();
    eg2.add(eg1);
    eg1.add(new Rest(1000));
    eg2.add(new Transpose(eg1, 2.0));

    eg2.play();
  }
}
