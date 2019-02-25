package com.winjean.utils;

import java.util.Map;

public final class BeanUtils {

    public static final String FiledClassName = "class";

    private BeanUtils() {
    }

    /**
     * JavaBean转化成map
     *
     * @param object            需要转换成map的实体类
     * @param dateFormatPattern 如果实体类有日期，日期的格式
     */
//    public static Map beanToMap(Object object, String dateFormatPattern) {
//        return beanToMap(object, null, dateFormatPattern);
//    }

    /**
     * JavaBean转化成map
     *
     * @param object 需要转换成map的实体类
     */
//    public static Map beanToMap(Object object) {
//        return beanToMap(object, null, "yyyy-MM-dd HH:mm:ss");
//    }

    /**
     * JavaBean转化成map
     *
     * @param object            需要转换成map的实体类
     * @param dateFormatPattern 如果实体类有日期，日期的格式
     * @param fieldNameArray    不需要转化的属性数组
     */
//    public static Map beanToMap(Object object, String[] fieldNameArray, String dateFormatPattern) {
//        Map map = null;
//        try {
//            map = convertToMap(object, dateFormatPattern);
//            deleteFiled(fieldNameArray, map);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        return map;
//    }

    /**
     * JavaBean转化成map
     *
     * @param object 需要转换成map的实体类
     */
//    private static Map<String, Object> convertToMap(Object object) {
//        return convertToMap(object, null);
//    }

    /**
     * JavaBean转化成map
     *
     * @param object 需要转换成map的实体类
     */
//    private static Map<String, Object> convertToMap(Object object, String dateFormatPattern) {
//        Map<String, Object> map = null;
//        try {
//            map = new HashMap<String, Object>();
//            Field[] fields = object.getClass().getDeclaredFields();
//            for (Field field : fields) {
//                field.setAccessible(true);
//                Object fieldObject = field.get(object);
//                if (fieldObject != null) {
//                    if (field.getType() == Date.class) {
//                        if (StringUtils.isNotEmpty(dateFormatPattern)) {
//                            String dateString = new SimpleDateFormat(dateFormatPattern, Locale.CHINA).format(fieldObject);
//                            map.put(field.getName(), dateString);
//                        }
//                    } else if (field.getType() == Integer.class || field.getType() == Long.class || field.getType() == Double.class || field.getType() == BigDecimal.class) {
//                        String numberString = fieldObject.toString();
//                        map.put(field.getName(), numberString);
//                    } else {
//                        map.put(field.getName(), fieldObject);
//                    }
//                }
//            }
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        return map;
//    }

    /**
     * 移除map中某些属性
     *
     * @param fieldNameArray 不需要转化的属性数组
     * @param map            map
     */
    private static void deleteFiled(String[] fieldNameArray, Map map) {
        if (fieldNameArray != null && fieldNameArray.length > 0) {
            deleteFiledClass(map);
            for (String fieldName : fieldNameArray) {
                map.remove(fieldName);
            }
        }
    }


    private static void deleteFiledClass(Map map) {
        if (map != null && map.keySet().size() > 0) {
            map.remove(FiledClassName);
        }
    }


}
