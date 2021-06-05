package com.ecommerce.ecommerce.modules.utils;

import org.springframework.stereotype.Service;
import java.lang.reflect.Field;

@Service
public class ObjectUtils {
    public Object update (Object a, Object b){
        Field[] fieldsA = a.getClass().getDeclaredFields();
        Field[] fieldsB = b.getClass().getDeclaredFields();
        try {
            for(int i = 0; i < fieldsA.length; i++) {
                fieldsA[i].setAccessible(true);
                fieldsB[i].setAccessible(true);
                if (fieldsA[i].get(a) != fieldsB[i].get(b) && fieldsB[i].get(b) != null){
                    fieldsA[i].set(a,fieldsB[i].get(b));
                }
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return a;
    }


}
