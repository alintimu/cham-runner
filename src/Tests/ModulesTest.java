package Tests;

import Controller.MainController;
import View.MainWindowView;

/**
 * Created by alin.timu on 8/14/2014.
 */
public class ModulesTest {

    public ModulesTest(MainWindowView mainWindowView) {

        for(int i=0; i< 10; i++) {
            mainWindowView.createNewPanel("Test " + i, null);
        }

    }
}
