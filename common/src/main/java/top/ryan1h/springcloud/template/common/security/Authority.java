package top.ryan1h.springcloud.template.common.security;

public class Authority {

    /**
     * 对应数据库中以ROLE_为前缀的权限名
     */
    public static final String ROLE_USER = AuthorityPrefix.ROLE + "user";

    public static final String ROLE_ADMIN = AuthorityPrefix.ROLE + "admin";

    public static final String ROLE_GUEST = AuthorityPrefix.ROLE + "guest";

    /**
     * 对应数据库中以PERMISSION_为前缀的权限名
     */
    public static final String PERMISSION_USER_ALL = AuthorityPrefix.PERMISSION + "user_all";

    public static final String PERMISSION_USER_ADD = AuthorityPrefix.PERMISSION + "user_add";

    public static final String PERMISSION_USER_UPDATE = AuthorityPrefix.PERMISSION + "user_update";

    public static final String PERMISSION_USER_DELETE = AuthorityPrefix.PERMISSION + "user_delete";

    public static final String PERMISSION_LOGIN = AuthorityPrefix.PERMISSION + "login";

    /**
     * 生成等级制度字符串
     *
     * @param parent
     * @param child
     * @return
     */
    public static String hierarchyString(String parent, String child) {
        return parent + " > " + child + "\n";
    }

    public static class AuthorityPrefix {
        /**
         * 权限前缀
         */
        public static final String PERMISSION = "PERMISSION_";

        /**
         * 角色前缀
         */
        public static final String ROLE = "ROLE_";
    }
}
