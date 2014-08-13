package View;

import Controller.ModuleController;
import Model.ModuleModel;

/**
 * Created by alin.timu on 8/12/2014.
 */
public interface AbstractModuleView {

    public void setModel(ModuleModel model);

    public ModuleModel getModel();

}
