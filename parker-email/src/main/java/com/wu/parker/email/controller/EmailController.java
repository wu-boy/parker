package com.wu.parker.email.controller;

import com.wu.parker.common.web.BaseResult;
import com.wu.parker.email.pojo.dto.EmailMessageDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.MimeMessage;

/**
 * @author wusq
 * @date 2019/3/15
 */
@Api(description = "Email服务")
@RestController
@RequestMapping("email")
public class EmailController {

    private static final Logger log = LoggerFactory.getLogger(EmailController.class);

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    private String from;

    @ApiOperation("发送邮件")
    @PostMapping("send")
    public BaseResult send(@RequestBody @Validated EmailMessageDto dto, BindingResult bindingResult){
        BaseResult result = new BaseResult();

        // 校验参数
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            log.error("发送邮件参数错误:{}", fieldError.getDefaultMessage());
            result.setCode(HttpStatus.BAD_REQUEST.value());
            result.setMessage(fieldError.getDefaultMessage());
            return result;
        }

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(dto.getTo());
        message.setSubject(dto.getSubject());
        message.setText(dto.getText());

        try {
            javaMailSender.send(message);
            log.info("发送邮件了");
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
    public BaseResult sendHtml(@RequestBody @Validated EmailMessageDto dto, BindingResult bindingResult){
        BaseResult result = new BaseResult();

        // 校验参数
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            log.error("发送HTML格式的邮件参数错误:{}", fieldError.getDefaultMessage());
            result.setCode(HttpStatus.BAD_REQUEST.value());
            result.setMessage(fieldError.getDefaultMessage());
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
            context.setVariable("id", "006");
            String text = templateEngine.process("htmlTemplate", context);

            helper.setText(text, true);

            javaMailSender.send(mimeMessage);
            log.info("发送HTML格式的邮件了");
        } catch (Exception e) {
            result.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            result.setMessage(e.getMessage());
            log.error("发送HTML格式的邮件参数错误{}", e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

}
