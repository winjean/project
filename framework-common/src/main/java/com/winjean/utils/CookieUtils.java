//package com.winjean.utils;
//
//
//import org.apache.commons.lang.math.RandomUtils;
//import org.apache.commons.lang3.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * 项目名称：工具类
// * 类名称：CookieUtils
// * 类描述：cookie工具类
// * 创建人：aidun
// * 创建时间：2018/10/31
// * 修改人：aidun
// * 修改时间：2018/10/31
// * 修改备注：
// * 版权所有权：
// *
// * @version V1.0
// */
//public final class CookieUtils {
//
//    private static Logger logger = LoggerFactory.getLogger(IPUtils.class);
//
//    private CookieUtils() {
//    }
//
//    /**
//     * 获取cookie中某个属性
//     *
//     * @param name 需要获取的属性名
//     */
//    public static Cookie getCookieByName(HttpServletRequest request, String name) {
//        Map<String, Cookie> cookieMap = ReadCookieMap(request);
//        if (cookieMap.containsKey(name)) {
//            return cookieMap.get(name);
//        }
//        return null;
//    }
//
//    /**
//     * 获取cookie中某个属性
//     *
//     * @param name 需要获取的属性名
//     */
//    public static String getCookieValueByName(HttpServletRequest request, String name) {
//        Cookie cookie = getCookieByName(request, name);
//        if (cookie == null) {
//            return null;
//        }
//        return cookie.getValue();
//    }
//
//    /**
//     * 将cookie存入map中
//     *
//     * @return
//     */
//    private static Map<String, Cookie> ReadCookieMap(HttpServletRequest request) {
//        Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
//        Cookie[] cookies = request.getCookies();
//        if (null != cookies) {
//            for (Cookie cookie : cookies) {
//                cookieMap.put(cookie.getName(), cookie);
//            }
//        }
//        return cookieMap;
//    }
//
//
//    /**
//     * 写入cookie
//     *
//     * @param domain 跨域共享域名
//     * @param name   key
//     * @param value  value
//     * @param maxAge 过期时间
//     * @return
//     */
//    public static void addCookie(HttpServletResponse response, String domain, String name, String value, int maxAge) {
//        Cookie cookie = new Cookie(name, value);
//        cookie.setPath("/");
//        if (maxAge > 0) {
//            cookie.setMaxAge(maxAge);
//        }
//        cookie.setDomain(domain);
//        try {
//            response.addCookie(cookie);
//        } catch (Exception e) {
//            logger.error("Cookie 写入失败 - " + e.getMessage(), e);
//        }
//    }
//
//    /**
//     * ABTest相关cookie控制
//     *
//     * @param site       cookie 所属域名
//     * @param cookieName cookie 名称
//     * @param percent    ABtest百分比, 例如:2代表对半分流
//     * @param seconds    cookie有效秒数
//     * @return
//     */
//    public static int getAbTestValueFromCookie(HttpServletRequest request, HttpServletResponse response, String site,
//                                               String
//                                                       cookieName, int percent, int seconds) {
//        String abtestCookieFlag = CookieUtils.getCookieValueByName(request, cookieName);
//        int abtestFlag;
//        if (StringUtils.isNotBlank(abtestCookieFlag)) {
//            abtestFlag = NumberUtils.toInt(abtestCookieFlag);
//        } else {
//            abtestFlag = RandomUtils.nextInt(percent);
//            CookieUtils.addCookie(response, site, cookieName, String.valueOf(abtestFlag), seconds);
//        }
//        return abtestFlag;
//    }
//
//}
