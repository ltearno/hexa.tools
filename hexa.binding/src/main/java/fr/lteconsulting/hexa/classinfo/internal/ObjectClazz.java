package fr.lteconsulting.hexa.classinfo.internal;

import java.util.ArrayList;
import java.util.List;

import fr.lteconsulting.hexa.classinfo.Field;
import fr.lteconsulting.hexa.classinfo.Method;

public class ObjectClazz extends fr.lteconsulting.hexa.classinfo.internal.ClazzBase<java.lang.Object> {
    public ObjectClazz() {
        super(java.lang.Object.class, "Object", null);
    }

    @Override
    protected List<Field> _getDeclaredFields() {
        ArrayList<Field> res = new ArrayList<Field>();
        return res;
    }

    @Override
    protected List<Method> _getMethods() {
        return new ArrayList<Method>();
    }

    @Override
    public java.lang.Object NEW() {
        return new java.lang.Object();
    }

    @Override
    protected void _ensureSuperClassInfoRegistered() {
    }
}
