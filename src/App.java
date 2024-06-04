import itemmanager.ItemManager;

public class App {
    public static void main(String[] args) throws Exception{

        ItemManager im = new ItemManager();
        im.initialise();
        im.run();

    }
}
