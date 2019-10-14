package top.ryan1h.springcloud.template.common.util.file;

import java.io.File;

public class FileUtils {

    // 不同操作系统的路径分隔符不同
    public final static String SEPARATOR = File.separator;

    /**
     * 删除指定目录中的所有文件
     *
     * @param dirPath 要删除文件的目录
     */
    public static void deleteFilesFromDir(String dirPath) {
        try {
            File path = new File(dirPath);
            File[] files = path.listFiles();
            for (File file : files) {
                if (file.exists() && file.isFile())
                    file.delete();
            }
        } catch (Exception e) {
            throw e;
        }
    }

}
