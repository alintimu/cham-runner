import Controller.MainController;
import Model.ProjectPathList;
import Model.ProjectPathsModel;
import Repository.AbstractRepository;
import Repository.EmbeddedRepository;
import Util.JaxbUtils;
import View.AbstractWindowView;
import View.MainWindowView;

import javax.xml.bind.JAXBException;

public class Main {
    private static final String MAIN_PAGE_VIEW = "mainView";
    private static final String EMBEDDED_REPOSITORY = "mainRepository";

    public static void main(String[] args) {
        ProjectPathList pathList = new ProjectPathList();
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
        }


        AbstractWindowView mainView = new MainWindowView(MAIN_PAGE_VIEW);
        AbstractRepository mainRepository = new EmbeddedRepository(EMBEDDED_REPOSITORY);

        MainController controller = new MainController();
        controller.addView(mainView);
        controller.addRepository(mainRepository);

        controller.run();
    }
}
