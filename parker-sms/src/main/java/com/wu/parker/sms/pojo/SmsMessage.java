package com.wu.parker.sms.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * 短信
 * @author wusq
 * @date 2019/4/25
 */
@ApiModel("短信参数")
public class SmsMessage {

    @ApiModelProperty("手机号-非空")
    @NotBlank(message = "手机号不能为空")
    private String phoneNumbers;

    @ApiModelProperty("短信模板ID-非空")
    @NotBlank(message = "短信模板ID不能为空")
    private String templateCode;

    @ApiModelProperty("短信模板变量对应的实际值，JSON格式-可空")
    private String templateParam;

    public String getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(String phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }

    public String getTemplateParam() {
        return templateParam;
    }

    public void setTemplateParam(String templateParam) {
        this.templateParam = templateParam;
    }
}
