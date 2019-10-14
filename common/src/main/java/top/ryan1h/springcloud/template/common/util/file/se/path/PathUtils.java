package top.ryan1h.springcloud.template.common.util.file.se.path;

import java.io.File;

import static top.ryan1h.springcloud.template.common.util.file.FileUtils.SEPARATOR;

/**
 * 类路径必须是:  项目目录/target/test-classes
 */
public class PathUtils {

    public static String getClassPath() {
        return new File(PathUtils.class.getResource("/").getPath()).getPath();
    }

    public static String getProjectPath() {
        return new File(getClassPath()).getParentFile().getParentFile().getPath();
    }

    public static String getJavaPath() {
        return new File(getProjectPath()) + SEPARATOR + "src" + SEPARATOR + "test" + SEPARATOR + "java";
    }

    public static String getResourcesPath() {
        return new File(getJavaPath()).getParentFile().getPath() + SEPARATOR + "resources";
    }
}
