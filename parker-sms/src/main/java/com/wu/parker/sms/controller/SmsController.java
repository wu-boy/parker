package com.wu.parker.sms.controller;

import com.wu.parker.common.web.BaseResult;
import com.wu.parker.sms.pojo.SmsMessage;
import com.wu.parker.sms.service.SmsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 发送短信
 * @author wusq
 * @date 2019/4/25
 */
@Api(description = "发送短信服务")
@RestController
@RequestMapping("sms")
public class SmsController {

    private static final Logger log = LoggerFactory.getLogger(SmsController.class);

    @Autowired
    private SmsService service;

    @ApiOperation("发送")
    @PostMapping("send")
    public BaseResult send(@RequestBody @Validated SmsMessage message, BindingResult bindingResult){
        BaseResult result = new BaseResult();

        // 校验参数
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            log.error("参数错误:{}", fieldError.getDefaultMessage());
            result.setCode(HttpStatus.BAD_REQUEST.value());
            result.setMessage(fieldError.getDefaultMessage());
            return result;
        }

        try {
            service.send(message);
        } catch (Exception e) {
            result.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            result.setMessage(e.getMessage());
            log.error("发送错误{}", e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

}
