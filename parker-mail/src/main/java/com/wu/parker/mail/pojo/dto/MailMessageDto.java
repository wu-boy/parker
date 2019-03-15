package com.wu.parker.mail.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * @author wusq
 * @date 2019/3/15
 */
@ApiModel("邮件参数")
public class MailMessageDto {

    @ApiModelProperty("收件人")
    @NotBlank(message = "收件人不能为空")
    private String to;

    @ApiModelProperty("主题")
    @NotBlank(message = "主题不能为空")
    private String subject;

    @ApiModelProperty("内容")
    @NotBlank(message = "内容不能为空")
    private String text;

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
