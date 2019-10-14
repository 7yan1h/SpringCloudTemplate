package top.ryan1h.springcloud.template.common.util.poi;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * @author huangxin
 * @date 2019/8/16
 */
public class Mock {

    public static void main(String[] args) {
        String excelPath = "C:\\Users\\acer\\Desktop\\政策全文库（0816录入）.xlsx";
        List<PolicyInformationParam> list = null;
        try {
            list = ExcelUtils.readData(
                    excelPath,
                    0,
                    30,
                    53,
                    PolicyInformationParam.class);
        } catch (IOException | InstantiationException | IllegalAccessException | NoSuchFieldException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }

        for (PolicyInformationParam item : list) {
            System.out.println(item);
        }
    }

}
