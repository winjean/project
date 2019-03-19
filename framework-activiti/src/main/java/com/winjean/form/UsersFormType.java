package com.winjean.form;

import org.activiti.engine.form.AbstractFormType;
import org.apache.commons.lang3.ObjectUtils;

/**
 * @author ：winjean
 * @date ：Created in 2019/3/18 9:12
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
public class UsersFormType extends AbstractFormType {

    @Override
    public String getName() {
        return "customUser";
    }

    @Override
    public Object convertFormValueToModelValue(String propertyValue) {
        return propertyValue;
    }

    @Override
    public String convertModelValueToFormValue(Object modelValue) {
        return ObjectUtils.identityToString(modelValue);
//        return ObjectUtils.toString(modelValue);
    }
}
