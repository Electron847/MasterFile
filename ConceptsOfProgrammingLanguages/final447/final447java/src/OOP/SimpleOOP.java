interface Language {
    String greet (String name);
}

class English implements Language {
    public String greet (String name) { return "Hello " + name; }
}

class French implements Language {
    private int count = 0;
    public String greet (String name) { this.count++; return "Bonjour " + name; }
    public int getCount ()            { return this.count; }
}

public class SimpleOOP {
    public static void main (String[] args) {
        Language[] langs = new Language[] { new English (), new French (), };
        String name = System.console ().readLine ("What is your name? ");
        for (Language lang : langs) {
            System.out.println (lang.greet (name));
        }
        System.out.format ("French count = %d%n", ((French) langs[1]).getCount ());
    }
}
