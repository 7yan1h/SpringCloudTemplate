package top.ryan1h.springcloud.template.common.util.poi;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 转化成的对象的属性只支持Integer,Long,Double,String,Date,BigDecimal类型。
 *
 * @author huangxin
 * @date 2019/8/15
 */
public class ExcelUtils {
    /**
     * @param excelPath  excel文件路径
     * @param sheetIndex sheet索引,从0开始
     * @param startRow   从哪一行开始读,从1开始
     * @param endRow     读取的最后一行
     * @param dataClass  转化成的对象的Class对象,需要给属性定义setter方法
     * @param <T>
     * @return
     * @throws IOException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     */
    public static <T> List<T> readData(String excelPath, Integer sheetIndex, int startRow, int endRow, Class<T> dataClass) throws IOException, InstantiationException, IllegalAccessException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException {
        return readData(excelPath, sheetIndex, null, startRow, endRow, dataClass);
    }

    /**
     * @param excelPath excel文件路径
     * @param sheetName sheet名称
     * @param startRow  从哪一行开始读,从1开始
     * @param endRow    读取的最后一行
     * @param dataClass 转化成的对象的Class对象,需要给属性定义setter方法
     * @param <T>
     * @return
     * @throws IOException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     */
    public static <T> List<T> readData(String excelPath, String sheetName, int startRow, int endRow, Class<T> dataClass) throws IOException, InstantiationException, IllegalAccessException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException {
        return readData(excelPath, null, sheetName, startRow, endRow, dataClass);
    }

    /**
     * 得到Sheet对象
     *
     * @param workbook
     * @param sheetIndex
     * @param sheetName
     * @return
     */
    private static Sheet getSheet(Workbook workbook, Integer sheetIndex, String sheetName) {
        if (sheetIndex != null) {
            return workbook.getSheetAt(sheetIndex);
        }

        if (StringUtils.isNotBlank(sheetName)) {
            return workbook.getSheet(sheetName);
        }

        return null;
    }

    /**
     * 读取Excel文件,并转化为对应的数据列表
     *
     * @param excelPath  excel文件路径
     * @param sheetIndex sheet索引,从0开始
     * @param sheetName  sheet名称
     * @param startRow   从哪一行开始读,从1开始
     * @param endRow     读取的最后一行
     * @param dataClass  转化成的对象的Class对象,需要给属性定义setter方法
     * @param <T>
     * @return
     * @throws IOException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws NoSuchFieldException
     */
    private static <T> List<T> readData(String excelPath, Integer sheetIndex, String sheetName, int startRow, int endRow, Class<T> dataClass) throws IOException, IllegalAccessException, InstantiationException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException {
        List<T> dataList = new ArrayList<>();

        try (InputStream inputStream = new FileInputStream(excelPath)) {
            // Workbook对应Excel文件
            Workbook wb = WorkbookFactory.create(inputStream);
            Sheet sheet = getSheet(wb, sheetIndex, sheetName);
            if (sheet == null) {
                return null;
            }

            addData(startRow, endRow, dataClass, dataList, sheet);
        }

        return dataList;
    }

    /**
     * 遍历行,并添加数据到列表
     *
     * @param startRow
     * @param endRow
     * @param dataClass
     * @param dataList
     * @param sheet
     * @param <T>
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     */
    private static <T> void addData(int startRow, int endRow, Class<T> dataClass, List<T> dataList, Sheet sheet) throws InstantiationException, IllegalAccessException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException {
        Map<Integer, String> columnPropertyMap = generateColumnPropertyMap(dataClass);

        for (int rowIndex = startRow - 1; rowIndex < endRow; rowIndex++) {
            // 每一行的数据
            Row row = sheet.getRow(rowIndex);
            if (row == null) {
                continue;
            }

            T data = dataClass.newInstance();
            // 处理行的每一列
            for (int colIndex = 0; colIndex < row.getLastCellNum(); colIndex++) {
                // 对应单元格
                Cell cell = row.getCell(colIndex);
                if (cell != null) {
                    // 得到该列对应的属性名
                    String propertyName = columnPropertyMap.get(colIndex);
                    if (StringUtils.isNotEmpty(propertyName)) {
                        setPropertyValue(data, propertyName, cell);
                    }
                }
            }
            dataList.add(data);
        }
    }

    private static <T> Map<Integer, String> generateColumnPropertyMap(Class<T> dataClass) {
        Map<Integer, String> columnPropertyMap = new HashMap<>();
        Field[] dataFields = dataClass.getDeclaredFields();
        for (Field dataField : dataFields) {
            if (dataField.isAnnotationPresent(ColumnIndex.class)) {
                ColumnIndex columnIndex = dataField.getDeclaredAnnotation(ColumnIndex.class);
                columnPropertyMap.put(columnIndex.value(), dataField.getName());
            }
        }

        return columnPropertyMap;
    }

    /**
     * 给定义了setter方法的属性设置值
     *
     * @param data
     * @param propertyName
     * @param cell
     * @param <T>
     * @throws NoSuchFieldException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    private static <T> void setPropertyValue(T data, String propertyName, Cell cell) throws NoSuchFieldException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class dataClass = data.getClass();
        Field propertyField = dataClass.getDeclaredField(propertyName);
        Object cellValue = getCellValue(cell);

        if (cellValue != null) {
            String methodName = "set" + propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1);
            // 得到setter方法的参数值
            Object methodPara = getRealValueFromDouble(propertyField, cellValue);
            Method method = dataClass.getDeclaredMethod(methodName, methodPara.getClass());
            // 调用setter方法处理属性值
            method.invoke(data, methodPara);
        }
    }


    /**
     * 根据单元格类型返回不同类型的值
     *
     * @param cell
     * @return
     */
    private static Object getCellValue(Cell cell) {
        switch (cell.getCellType()) {
            case STRING:
                return cell.getRichStringCellValue().getString();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue();
                } else {
                    return cell.getNumericCellValue();
                }
                // 读取带公式的单元格
            case FORMULA:
                //return cell.getCellFormula();
                return cell.getStringCellValue();
            case BOOLEAN:
                return cell.getBooleanCellValue();
            default:
                return null;
        }
    }

    /**
     * 根据Double类型返回对应的真实类型
     *
     * @param field Field对象
     * @param value 处理前的值
     * @return
     * @author huangxin
     */
    private static Object getRealValueFromDouble(Field field, Object value) {
        if (value != null) {
            if (value.getClass().isAssignableFrom(Double.class)) {
                Double d = (Double) value;
                Class fieldType = field.getType();
                // 转换为Integer类型
                if (Integer.class.equals(fieldType)) {
                    return d.intValue();
                }

                // 转换为BigDecimal类型
                if (BigDecimal.class.equals(fieldType)) {
                    return new BigDecimal(d);
                }

                // 转换为Long类型
                if (Long.class.equals(fieldType)) {
                    return d.longValue();
                }
            }
        }

        return value;
    }
}
