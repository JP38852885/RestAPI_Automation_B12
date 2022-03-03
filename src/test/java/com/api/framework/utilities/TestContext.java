package com.api.framework.utilities;

import java.util.HashMap;

public class TestContext {

    private ThreadLocal<HashMap<String, Object>> testContext = new ThreadLocal<HashMap<String, Object>>(){
        @Override
        protected HashMap<String, Object> initialValue() {
            return new HashMap<String, Object>();
        }
    } ;

    public <T> T get(String name){

        return (T) testContext.get().get(name);
    }

    public <T> T set(String name, T object){
       testContext.get().put(name, object) ;
       return object;
    }

    @Override
    public String toString(){
        return testContext.toString();
    }
}
