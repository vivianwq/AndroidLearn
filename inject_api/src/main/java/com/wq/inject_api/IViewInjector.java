package com.wq.inject_api;

public interface IViewInjector<T> {
    /**
     * 通过source.findViewById()
     *
     * @param target 泛型参数，调用类 activity、fragment等
     * @param source Activity、View
     */
    void inject(T target, Object source);
}