//package com.winjean.utils;
//
//import com.fasterxml.jackson.core.JsonParseException;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.JsonMappingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.SerializationFeature;
//
//import java.io.IOException;
//import java.text.SimpleDateFormat;
//
///**
// * 项目名称：工具类
// * 类名称：JsonUtils
// * 类描述：JSON格式转化工具类
// * 创建人：admin
// * 创建时间：2018/10/31
// * 修改人：aidun
// * 修改时间：2018/10/31
// * 修改备注：
// * 版权所有权：
// *
// * @version V1.0
// */
//public class JsonUtils {
//    /**
//     * 转换Json字符串为指定类型对象
//     *
//     * @param json         JSON字符串
//     * @param tartgetClass 目标类
//     * @return
//     */
//    @SuppressWarnings("unchecked")
//    public static <T> T convertJsonToObject(String json, Class<?> tartgetClass) {
//        ObjectMapper mapper = new ObjectMapper();
//        T rs = null;
//        try {
//            rs = (T) mapper.readValue(json, tartgetClass);
//        } catch (JsonParseException e) {
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        } catch (JsonMappingException e) {
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        }
//        return rs;
//    }
//
//    /**
//     * 将一个Object转换成字符串
//     *
//     * @param o Object
//     * @return String 转化后的字符串
//     */
//    public static String convertJson(Object o) {
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
//        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
//        try {
//            String jsonString = objectMapper.writeValueAsString(o);
//            /*formatting the json string*/
//            return jsonString;
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//
//}
