package top.ryan1h.springcloud.template.common.object;

/**
 * 机器操作的用户
 *
 * @author 59941
 * @date 2019/5/21 11:35
 */
public class Robot extends LoginUser {

    private static volatile Robot INSTANCE;

    private Robot(Long id, String username) {
        super(id, username);
    }

    public static Robot getInstance() {
        if (INSTANCE == null) {
            synchronized (Robot.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Robot(0L, "robot");
                }
            }
        }

        return INSTANCE;
    }
}
