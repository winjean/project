package com.winjean.model.vo;

import com.winjean.model.po.UserEntity;
import lombok.Data;

import java.util.List;

/**
 * 项目名称：重庆微警务（一期）
 * 类名称：<....>
 * 类描述：<....>
 * 创建人：Administrator
 * 创建时间：2018/10/20 7:50
 * 修改人：Administrator
 * 修改时间：2018/10/20 7:50
 * 修改备注：
 * 版权所有权：江苏艾盾网络科技有限公司
 *
 * @version V1.0
 */

@Data
public class SearchUsersResponse {
    private int pageNum;
    private int pageSize;
    private long total;

    private List<UserEntity> users;
}
