package Util;

import java.io.File;
import java.io.FilenameFilter;

/**
 * Created by rares.urdea on 18.08.2014.
 */
public class GenericExtensionFilter implements FilenameFilter {
    private String ext;

    public GenericExtensionFilter(String ext) {
        this.ext = ext;
    }

    @Override
    public boolean accept(File dir, String name) {
        return (name.endsWith(ext));
    }
}
