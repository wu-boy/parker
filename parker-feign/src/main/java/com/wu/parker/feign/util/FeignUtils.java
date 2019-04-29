package com.wu.parker.feign.util;

import com.wu.parker.common.web.BaseResult;
import com.wu.parker.feign.client.UserClient;
import com.wu.parker.feign.pojo.entity.User;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author wusq
 * @date 2019/4/29
 */
public class FeignUtils {

    /**
     * 根据ID查询
     * @param client
     * @param id
     * @return
     */
    public static User get(UserClient client, String id){
        User result = null;
        ResponseEntity<BaseResult> entity = client.get(id);
        Map<String, Object> map = (Map<String, Object>)entity.getBody().getData();
        if(HttpStatus.OK.value() == entity.getBody().getCode() && map != null){
            result = new User();
            try {
                // 转换时间
                // ConvertUtils.register(new DateConverter(null), java.util.Date.class);
                BeanUtils.populate(result, map);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 查询列表
     * @param client
     * @return
     */
    public static List<User> list(UserClient client){
        List<User> result = new ArrayList<>();
        ResponseEntity<BaseResult> entity = client.list();
        List<Map<String, Object>> mapList = (ArrayList)entity.getBody().getData();
        if(HttpStatus.OK.value() == entity.getBody().getCode() && mapList != null && mapList.size() > 0){
            mapList.forEach(m -> {
                User o = new User();
                try {
                    // 转换时间
                    // ConvertUtils.register(new DateConverter(null), java.util.Date.class);
                    BeanUtils.populate(o, m);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                result.add(o);
            });
        }
        return result;
    }

    /**
     * 新增
     * @param client
     * @param user
     * @return
     */
    public static User create(UserClient client, User user){
        User result = null;
        ResponseEntity<BaseResult> entity = client.create(user);
        Map<String, Object> map = (Map<String, Object>)entity.getBody().getData();
        if(HttpStatus.OK.value() == entity.getBody().getCode() && map != null){
            result = new User();
            try {
                // 转换时间
                // ConvertUtils.register(new DateConverter(null), java.util.Date.class);
                BeanUtils.populate(result, map);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
