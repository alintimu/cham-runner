package Controller;

import Repository.AbstractRepository;
import View.AbstractWindowView;

/**
 * Created by alin.timu on 8/8/2014.
 */
public interface BasicController {
    void addView(AbstractWindowView mainView);

    void addRepository(AbstractRepository mainRepository);

    void run();

    void changeView(String view_identifier);

    void changeView(Class<? extends AbstractWindowView> view);

    AbstractWindowView getView(String s);

    AbstractRepository getRepository(String s);
}
