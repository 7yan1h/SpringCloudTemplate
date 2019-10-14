package top.ryan1h.springcloud.template.common.util.file.web.path;

import java.io.File;

import static top.ryan1h.springcloud.template.common.util.file.FileUtils.SEPARATOR;

/**
 * 类路径必须是:  项目目录/target/项目名/WEB-INF/classes
 */
public class PathUtils {

    public static String getTargetDirClassPath() {
        return new File(PathUtils.class.getResource("/").getPath()).getPath();
    }

    public static String getProjectPath() {
        //return new File(getTargetDirClassPath()).getParentFile().getParentFile().getParentFile().getParentFile().getPath();
        return new File(getTargetDirClassPath()).getParentFile().getParentFile().getParentFile().getParentFile().getParentFile().getParentFile().getPath();
    }

    public static String getMainJavaPath() {
        return getProjectPath() + SEPARATOR + "src" + SEPARATOR + "main" + SEPARATOR + "java";
    }

   /* public static String getWebappPath() {
        return getProjectPath() + CommonUtils.SEPARATOR + "src" + CommonUtils.SEPARATOR + "main" + CommonUtils.SEPARATOR + "webapp";
    }*/

   /* public static String getTargetDirWebInfPath() {
        return new File(getTargetDirClassPath()).getParentFile().getPath();
    }*/

    public static String getResourcesPath() {
        return new File(getMainJavaPath()).getParentFile().getPath() + SEPARATOR + "resources";
    }

    /*public static String getTargetDirResourcesPath() {
        return new File(getTargetDirWebInfPath()).getParentFile().getPath() + CommonUtils.SEPARATOR + "resources";
    }*/

}
