import Controller.MainController;
import Repository.AbstractRepository;
import Repository.EmbeddedRepository;
import View.AbstractWindowView;
import View.MainWindowView;

public class Main {
    private static final String MAIN_PAGE_VIEW = "mainView";
    private static final String EMBEDDED_REPOSITORY = "mainRepository";

    public static void main(String[] args) {
        AbstractWindowView mainView = new MainWindowView(MAIN_PAGE_VIEW);
        AbstractRepository mainRepository = new EmbeddedRepository(EMBEDDED_REPOSITORY);

        MainController controller = new MainController();
        controller.addView(mainView);
        controller.addRepository(mainRepository);

        controller.run();
    }
}
