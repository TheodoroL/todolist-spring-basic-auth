package br.com.theodorol.todolist.utils;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class Utils {

    public static void getNonNullProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target, getNullPropertyName(source));
    }

    private static String[] getNullPropertyName(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);

        PropertyDescriptor[] ads = src.getPropertyDescriptors();
        Set<String> emptyNames = new HashSet<>();
        for (var ad : ads) {
            Object srcValues = src.getPropertyValue(ad.getName());
            if (srcValues == null)
                emptyNames.add(ad.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
}
