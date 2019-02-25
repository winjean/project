package com.winjean.common.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 项目名称：重庆微警务（一期）
 * 类名称：<ExceptionUtil>
 * 类描述：<输出完整的堆栈信息>
 * 创建人：winjean
 * 创建时间：2018/9/14
 * 修改人：winjean
 * 修改时间：2018/9/14
 * 修改备注：
 * 版权所有权：
 *
 * @version V1.0
 */

public class ExceptionUtil {
	public static String getTrace(Throwable t) {
		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		t.printStackTrace(writer);
		StringBuffer buffer = stringWriter.getBuffer();
		return buffer.toString();
	}
}
