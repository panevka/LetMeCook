package com.letmecook.backend.common;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class PatchUtil {

    public static void applyPatch(Object patchDto, Object entity) {
        BeanWrapper src = new BeanWrapperImpl(patchDto);
        BeanWrapper trg = new BeanWrapperImpl(entity);

        for (var prop : src.getPropertyDescriptors()) {
            String name = prop.getName();

            // ignore irrelevant fields
            if (name.equals("class"))
                continue;

            Object value = src.getPropertyValue(name);

            // apply only NON-null values
            if (value != null) {
                trg.setPropertyValue(name, value);
            }
        }
    }
}
