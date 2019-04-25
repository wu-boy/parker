package com.wu.parker.sms.service;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.wu.parker.sms.config.GlobalConfig;
import com.wu.parker.sms.pojo.SmsMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wusq
 * @date 2019/4/25
 */
@Service
public class SmsServiceImpl implements SmsService{

    @Autowired
    private GlobalConfig globalConfig;

    @Override
    public void send(SmsMessage message) {

        DefaultProfile profile = DefaultProfile.getProfile(globalConfig.getRegionId(),
                globalConfig.getAccessKeyId(), globalConfig.getAccessSecret());

        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        //request.setProtocol(ProtocolType.HTTPS);
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", globalConfig.getRegionId());
        request.putQueryParameter("SignName", globalConfig.getSignName());

        request.putQueryParameter("PhoneNumbers", message.getPhoneNumbers());
        request.putQueryParameter("TemplateCode", message.getTemplateCode());
        request.putQueryParameter("TemplateParam", message.getTemplateParam());
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }

    }

}
