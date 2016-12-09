package fr.lteconsulting.hexa.rebind.classinfo;

import com.google.gwt.core.ext.typeinfo.JClassType;

public class TypeHelper {

    public static <T> boolean isInstanceOf(JClassType classType, Class<T> interfaceType) {
        for(JClassType type : classType.getImplementedInterfaces()) {
            if(type.getQualifiedSourceName().equals(interfaceType.getName())) {
                return true;
            }
        }
        return false;
    }
}
