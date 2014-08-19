package Controller;

import org.tmatesoft.svn.core.SVNDepth;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.internal.wc.DefaultSVNOptions;
import org.tmatesoft.svn.core.wc.*;

import java.io.File;

/**
 * Created by rares.urdea on 19.08.2014.
 */
public class SVNHandler {
    private String projectName;
    private File destinationPath;
    private SVNURL projectUrl;
    private String username;
    private String password;

    public SVNHandler(String projectName, String destinationPath, String projectUrl) {
        this.projectName = projectName;
        setDestinationPath(destinationPath);
        setProjectUrl(projectUrl);
    }

    public SVNHandler() {
        this("default", null, null);
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public File getDestinationPath() {
        return destinationPath;
    }

    /**
     * Sets the file path to the project destination - creates a new File from the destionationPath String.
     *
     * @param destinationPath - the path to the project
     */
    public void setDestinationPath(String destinationPath) {
        this.destinationPath = new File(destinationPath);
    }

    public SVNURL getProjectUrl() {
        return projectUrl;
    }

    /**
     * Parses the URL String into a SVNURL object and sets the projectUrl field;
     * Sets projectUrl field to null if exception is met.
     *
     * @param projectUrl - the URL to the svn project you wish to checkout
     */
    public void setProjectUrl(String projectUrl) {
        try {
            this.projectUrl = SVNURL.parseURIEncoded(projectUrl);
        } catch (SVNException e) {
            this.projectUrl = null;
            e.printStackTrace();
        }
    }

    public void doAuthentication(String user, String pass) {
        this.username = user;
        this.password = pass;
    }

    public void svnCheckout() {
        DefaultSVNOptions options = SVNWCUtil.createDefaultOptions(true);
        SVNClientManager clientManager = SVNClientManager.newInstance(options, this.username, this.password);
        SVNUpdateClient updateClient = clientManager.getUpdateClient();

        try {
            updateClient.doCheckout(projectUrl, destinationPath, SVNRevision.UNDEFINED, SVNRevision.HEAD, SVNDepth.INFINITY, true);
        } catch (SVNException e) {
            System.out.println("Exception ");
            e.printStackTrace();
        }
        System.out.println("Checkout finished successfully !");
    }
}
