package com.wu.parker.shiro.stateless.shiro;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSubjectFactory;

/**
 * @author: wusq
 * @date: 2018/12/10
 */
public class StatelessDefaultSubjectFactory extends DefaultWebSubjectFactory {

    @Override
    public Subject createSubject(SubjectContext context) {

        // 不创建session.
        context.setSessionCreationEnabled(false);
        return super.createSubject(context);

    }

}
