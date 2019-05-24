package com.example.springboot.classesLoader;

/**
 * @author linghongkang
 * @description:
 * @create: 2019-05-02 11:17
 **/
public class HotSwapClassLoader extends ClassLoader {

    public HotSwapClassLoader() {
        super(HotSwapClassLoader.class.getClassLoader());
    }

   public Class   loadByte(byte[] bytes){
        return defineClass(null,bytes,0,bytes.length);
   }
}
