import Controller.MainController;
import Tests.ModulesTest;
import View.AbstractMainWindowView;
import View.MainWindowView;

public class Main {
    private static final String MAIN_PAGE_VIEW = "mainView";

    public static void main(String[] args) {
        /*ProjectPathList pathList = new ProjectPathList();
        pathList.getModelList().add(new ProjectPathsModel(1, "test/estes/fs/fsa/dsf/ds", "SS", true, ""));
        pathList.getModelList().add(new ProjectPathsModel(2, "dsa/dsa/dsa", "API", false, ""));

        try {
            String xxx = JaxbUtils.convertToXml("config.xml", ProjectPathList.class, pathList);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        try {
            ProjectPathList pathList2 = (ProjectPathList) JaxbUtils.convertToObject("config.xml", ProjectPathList.class);

            System.out.println(pathList2);

        } catch (JAXBException e) {
            e.printStackTrace();
        }*/


        MainWindowView mainView = new MainWindowView(MAIN_PAGE_VIEW);

        MainController controller = new MainController();
        controller.addView(mainView);

        controller.run();
        controller.getTomcatPath("CATALINA_BASE");

        /**
         * Testing the module view interface by creating 10 modules. Comment when not needed.         *
         */
        ModulesTest modulesTest = new ModulesTest(mainView);
    }
}
