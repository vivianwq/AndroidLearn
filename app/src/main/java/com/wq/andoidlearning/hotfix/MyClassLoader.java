package com.wq.andoidlearning.hotfix;

import dalvik.system.DexClassLoader;

public class MyClassLoader extends DexClassLoader {
    public MyClassLoader(String dexPath, String optimizedDirectory, String librarySearchPath, ClassLoader parent) {
        super(dexPath, optimizedDirectory, librarySearchPath, parent);
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        if (true) {
            //条件判断是否自己加载
            return this.loadClass(name);
        } else {
            //双亲委派机制加载
            return super.loadClass(name, resolve);
        }
    }
}
