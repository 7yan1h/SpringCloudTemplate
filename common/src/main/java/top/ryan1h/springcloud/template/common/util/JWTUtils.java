package top.ryan1h.springcloud.template.common.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import top.ryan1h.springcloud.template.common.util.crypto.rsa.RSAUtils;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTUtils {
    /**
     * 检查Token是否合法
     *
     * @param token
     * @param publicKey rsa公钥
     */
    public static JWTCheckResult checkToken(String token, String publicKey) {
        if (StringUtils.isEmpty(token) || StringUtils.isEmpty(publicKey)) {
            return JWTCheckResult.error(JWTTokenStatus.ERROR);
        }

        Jws<Claims> claimsJws;
        try {
            claimsJws = Jwts.parser().setSigningKey(RSAUtils.publicKey(publicKey)).parseClaimsJws(token);
        } catch (ExpiredJwtException e) {
            return JWTCheckResult.error(JWTTokenStatus.EXPIRE);
        } catch (Exception e) {
            return JWTCheckResult.error(JWTTokenStatus.ERROR);
        }

        return JWTCheckResult.result(JWTTokenStatus.SUCCESS, claimsJws);
    }

    /**
     * 生成token
     *
     * @param privateKey rsa私钥
     * @param claims     自定义数据
     * @param expire     token过期时间,单位秒
     * @return
     */
    public static String buildToken(String privateKey, Map<String, Object> claims, int expire) {
        return Jwts.builder()
                .addClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * expire))
                .signWith(RSAUtils.privateKey(privateKey))
                .compact();
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        String privateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQChinCzpswYwQdshlo1B3I2OgLhk2zdHQvq+odcnDkOnZTrCC1LQq7OleF59O8k3jRRB3pgi42m50AVcV6TAQJrkUAnBCVBY5P66n5IIbawmHBqmpbsTNRFIn4LJMrAVJC9pNe2oP0ZwyxK9titB0HyJjxUjkQyVhUjDKJ7eB+7NeT02ck/frjuSrSmJQ1bxb65A9KaB73LGUNmB4UWSUK0obJw+6x0mP8S2ZdTMOKeomSjMcS+FhLVqCiThXDlBxt9YO1csCnjiWoSkkR//NL77APM3LsPKXRWu9llgVB22KD2l9zMvuy5iNoTdg76r5Xun5eJcZCTLs4VrPxm7iA9AgMBAAECggEAbEZVqiS3kjP3nOckmXynMe+KNNZS1pX8ggBwMEPJ/qDiyvJNIa/NaUyOt+bTT9vcJx+jCAkh5egETRsS7+dyTtUpsb0Wl5B+BGWeRQzL3ewfFWUOmc4ZTUmEQEXl3CoSZfqSV6fqXZUzH//6VWeS7RJxx58f2LlYK4jxc16driWm3iGsxJ/7HdmmCEJVaAXrsJg1RavqmEiGAfCkWBR4YLSzrFHeBMI5haAlzhbeiGD0TT+Vgy7ivezckHYSG4aIO+cah1k8p4PCjdEYmdPMfXyHNlGGpzCn9FbiLUXaBEQkl4/iE8JuRDRBhfZ3b4uVicQRLc57I71qY9KFry2f4QKBgQD/XdIH7BlPaNm9AUBeBfqDAN1DXzaEV8NJ8SCtds9pZaFVyfuaeff1x1IGwVzG1G6a56uSKf1V7A/NuYRpaa4Kmah6ml53x0cqTtQtd+v6+0h5WIX7/HZyqwOZTJEP3aX3IkRcERkBLmIN6g4mJuf50aEnIRP6/3l1PBONy+XR6QKBgQCh8QhPNXi6KYaZ3OWVl2hiCNIBBO0Rfl4FYLLYM5eFiD94MdQD+hRulAA0XadY10nvitzBE56NOWpquwf3hY34/KrlYOtsOuYyoZBxnT6qkp7c4mwy6LgszAHIf1JutSuwM3IScWj161TT1HWOVROI2aTLoD04+J1Iemnfs95zNQKBgQCT3oLpHXjGd8NlY10fVLzrsMtu4KpT1fH6VgsDBwIxWK0kXRI5MBk6E5HW4Y7Uf1wQjMfXmkLcxxWbnaoj1BL4hmN9ovfCyfzWbdEFByT5LxqXUYA8GsOHwKkT0rdJBaK9yPTmjuv4uCAFSJuq0eqPyShU3R2RwO9WyFPWTtQkSQKBgGjTEuPi4vDuLFiwalQ4m7LJrtZGEAbA269zdpEIZE5lcWAmMO0aFdu2JaO/87FIOm26X0cDoiAcSEwqIPASHWJMe3rbD8kRHy0q88r3VDcpjXfBgPQ7O+yTaZwU/pD26P63bmGN07lLya8M2cN2QpzU9R1x7e9Qzs65norVbWr5AoGAG7Hcqbg8WztNCZAvX7WtXkOHMfQRIaQZVVEfSH4lAb5Ie8UOR/ZmIDUPjg9pzOYjdsqsrx721xyFNvrlJeJGtaEwKgxSnzxaXVOnnaj9ZTddjV+MuPE+vJVVXGVjlKjNqopx9wROo4WQllVRW8BPf+H5Kyw707IeyivuzU5ssNU=";
        String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAoYpws6bMGMEHbIZaNQdyNjoC4ZNs3R0L6vqHXJw5Dp2U6wgtS0KuzpXhefTvJN40UQd6YIuNpudAFXFekwECa5FAJwQlQWOT+up+SCG2sJhwapqW7EzURSJ+CyTKwFSQvaTXtqD9GcMsSvbYrQdB8iY8VI5EMlYVIwyie3gfuzXk9NnJP3647kq0piUNW8W+uQPSmge9yxlDZgeFFklCtKGycPusdJj/EtmXUzDinqJkozHEvhYS1agok4Vw5QcbfWDtXLAp44lqEpJEf/zS++wDzNy7Dyl0VrvZZYFQdtig9pfczL7suYjaE3YO+q+V7p+XiXGQky7OFaz8Zu4gPQIDAQAB";

        Map<String, Object> claims = new HashMap<>();
        claims.put("id", 123);
        claims.put("JWTTokenStatus", JWTCheckResult.error(JWTTokenStatus.ERROR));
        claims.put("name1", "hx");
        claims.put("name2", "hx");
        claims.put("name3", "hx");
        claims.put("name4", "hx");
        // 生成token
        String token = JWTUtils.buildToken(privateKey, claims, 60);

        System.out.println(token);
        // 检查token合法性
        JWTCheckResult jwtCheckResult = JWTUtils.checkToken(token, publicKey);
        System.out.println(jwtCheckResult.getJwtTokenStatus());
        System.out.println(jwtCheckResult.getClaimsJws().getBody());
    }

    public enum JWTTokenStatus {
        /**
         * token过期
         */
        EXPIRE,
        /**
         * token解析错误
         */
        ERROR,
        /**
         * token解析成功
         */
        SUCCESS,
        ;
    }

    @Getter
    public static class JWTCheckResult {
        private JWTTokenStatus jwtTokenStatus;

        private Jws<Claims> claimsJws;

        public JWTCheckResult(JWTTokenStatus jwtTokenStatus) {
            this.jwtTokenStatus = jwtTokenStatus;
        }

        public JWTCheckResult(JWTTokenStatus jwtTokenStatus, Jws<Claims> claimsJws) {
            this.jwtTokenStatus = jwtTokenStatus;
            this.claimsJws = claimsJws;
        }

        public static JWTCheckResult error(JWTTokenStatus jwtTokenStatus) {
            return new JWTCheckResult(jwtTokenStatus);
        }

        public static JWTCheckResult result(JWTTokenStatus jwtTokenStatus, Jws<Claims> body) {
            return new JWTCheckResult(jwtTokenStatus, body);
        }
    }
}
