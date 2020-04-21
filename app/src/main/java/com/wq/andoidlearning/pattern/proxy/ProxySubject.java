package com.wq.andoidlearning.pattern.proxy;

/**
 * 代理类
 */
public class ProxySubject extends Subject {
    private Subject subject;

    public ProxySubject(Subject subject) {
        this.subject = subject;
    }

    @Override
    void visit() {
        subject.visit();
    }
}
