package com.wu.parker.mail.pojo.dto;

/**
 * 邮件HTML参数
 * @author wusq
 * @date 2019/3/27
 */
public class MailHtmlMessageDto {

    /**
     * 收件人
     */
    private String to;

    /**
     * 主题
     */
    private String subject;

    /**
     * 参数，json字符串，例如{"id":"123"}
     */
    private String params;

    /**
     * 邮件模版代码，例如需要使用resource/templates/htmlTemplate.html，则mailCode是htmlTemplate
     */
    private String mailCode;

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

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getMailCode() {
        return mailCode;
    }

    public void setMailCode(String mailCode) {
        this.mailCode = mailCode;
    }
}
