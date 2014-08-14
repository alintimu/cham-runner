package Util;

/**
 * Created by rares.urdea on 14.08.2014.
 */
public class StringUtils {
    /**
     *
     * @param path a normal path
     * @return a windows specific path with \\ or null if the initial path was incorrectly formatted
     */
    public static String makeWindowsPath(String path) {
        String windowsPath = null;
        char backslash = '\\';
        char forward_slash = '/';
        String double_backslash = '\\' + "\\";
        for (int i = 0; i < path.length(); i++) {
            if (path.charAt(i) == backslash) {
                path = new StringBuilder(path).insert(i,backslash).toString();
                i++;
            } else if (path.charAt(i) == forward_slash) {
                path = new StringBuilder(path).replace(i,i+1,double_backslash).toString();
                i += 2;
            }
        }
        windowsPath = path;
        return windowsPath;
    }
}
