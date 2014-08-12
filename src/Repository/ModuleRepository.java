package Repository;

import Model.ModuleModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alin.timu on 8/12/2014.
 */
public class ModuleRepository implements ModuleInterface {
    private List<ModuleModel> modelList;

    public ModuleRepository() {
        modelList = new ArrayList<ModuleModel>();
    }

    public ModuleRepository(List<ModuleModel> modelList) {

        this.modelList = modelList;
    }

    public List<ModuleModel> getModelList() {
        return modelList;
    }

    public void setModelList(List<ModuleModel> modelList) {
        this.modelList = modelList;
    }

    @Override
    public boolean addModule(ModuleModel module) {
        boolean check = true;
        for (ModuleModel m : modelList) {
            if (m.getId() == module.getId()) {
                check = false;
            }
        }
        if (check) {
            modelList.add(module);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void removeModule(ModuleModel module) {
        modelList.remove(module);
    }
}
