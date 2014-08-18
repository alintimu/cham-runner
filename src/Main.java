import Controller.MainController;
import View.MainWindowView;

public class Main {
    private static final String MAIN_PAGE_VIEW = "mainView";

    public static void main(String[] args) {
        MainWindowView mainView = new MainWindowView(MAIN_PAGE_VIEW);

        MainController controller = new MainController();
        controller.addView(mainView);

        controller.run();
        System.out.println(controller.getFileList());

        /**
         * Testing the module view interface by creating 10 modules. Comment when not needed.         *
         */
//        ModulesTest modulesTest = new ModulesTest(mainView);
    }
}
