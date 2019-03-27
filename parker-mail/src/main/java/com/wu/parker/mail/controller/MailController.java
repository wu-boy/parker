package com.wu.parker.mail.controller;

import com.alibaba.fastjson.JSONObject;
import com.wu.parker.common.web.BaseResult;
import com.wu.parker.mail.pojo.dto.MailHtmlMessageDto;
import com.wu.parker.mail.pojo.dto.MailMessageDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.MimeMessage;

/**
 * @author wusq
 * @date 2019/3/27
 */
@Api(description = "邮件服务")
@RestController
@RequestMapping("mail")
public class MailController {

    private static final Logger log = LoggerFactory.getLogger(MailController.class);

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    private String from;

    @ApiOperation("发送邮件")
    @PostMapping("send")
    public BaseResult send(@RequestBody MailMessageDto dto){
        BaseResult result = new BaseResult();

        if(StringUtils.isBlank(dto.getTo())){
            result.setData(0);
            result.setMessage("缺少收信人");
            return result;
        }

        if(StringUtils.isBlank(dto.getSubject())){
            result.setData(0);
            result.setMessage("缺少主题");
            return result;
        }

        if(StringUtils.isBlank(dto.getText())){
            result.setData(0);
            result.setMessage("缺少内容");
            return result;
        }

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(dto.getTo());
        message.setSubject(dto.getSubject());
        message.setText(dto.getText());

        try {
            javaMailSender.send(message);
        } catch (Exception e) {
            result.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            result.setMessage(e.getMessage());
            log.error("发送邮件参数错误{}", e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    @ApiOperation("发送HTML格式的邮件")
    @PostMapping("send/html")
    public BaseResult sendHtml(@RequestBody MailHtmlMessageDto dto){
        BaseResult result = new BaseResult();

        if(StringUtils.isBlank(dto.getTo())){
            result.setData(0);
            result.setMessage("缺少收信人");
            return result;
        }

        if(StringUtils.isBlank(dto.getSubject())){
            result.setData(0);
            result.setMessage("缺少主题");
            return result;
        }

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            // true表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(from);
            helper.setTo(dto.getTo());
            helper.setSubject(dto.getSubject());

            // 创建邮件正文
            Context context = new Context();
            if(StringUtils.isNotBlank(dto.getParams())){
                JSONObject params = JSONObject.parseObject(dto.getParams());
                context.setVariables(params);
            }
            String text = templateEngine.process(dto.getMailCode(), context);

            if(StringUtils.isNotBlank(text)){
                helper.setText(text, true);
            }else{
                result.setData(0);
                result.setMessage("缺少内容");
                return result;
            }

            javaMailSender.send(mimeMessage);
        } catch (Exception e) {
            result.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            result.setMessage(e.getMessage());
            log.error("发送HTML格式的邮件参数错误{}", e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

}
