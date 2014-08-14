package Tests;

import Controller.MainController;
import View.MainWindowView;

/**
 * Created by alin.timu on 8/14/2014.
 */
public class ModulesTest {

    private static MainWindowView mwv;

    public static void main(String[] args) {

        mwv = new MainWindowView("Test");

        for(int i=0; i< 10; i++) {
            mwv.createNewPanel("Test " + i);
        }

    }
}
