package com.wu.parker.sms.service;

import com.wu.parker.sms.pojo.SmsMessage;

/**
 * @author wusq
 * @date 2019/4/25
 */
public interface SmsService {

    void send(SmsMessage message);
}
