package fr.lteconsulting.hexa.classinfo.gwt;

import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JMethod;
import fr.lteconsulting.hexa.client.tools.StringUtils;

public class TypeHelper {

    public static final String[] GETTER_PREFIXES = new String[]{
        "get", "is"
    };

    public static final String[] SETTER_PREFIXES = new String[]{
        "set"
    };

    public static <T> boolean isInstanceOf(JClassType classType, Class<T> interfaceType) {
        for (JClassType type : classType.getImplementedInterfaces()) {
            if (type.getQualifiedSourceName().equals(interfaceType.getName())) {
                return true;
            }
        }
        return false;
    }

    public static String stripSetterOrGetterPrefix(JMethod method) {
        String result = method.getName();

        for (String prefix : GETTER_PREFIXES) {
            if (result.length() > prefix.length() && result.startsWith(prefix)
                    && Character.isUpperCase(result.charAt(prefix.length()))) {
                result = StringUtils.lowerFirstLetter(result.substring(prefix.length()));
                return result;
            }
        }

        for (String prefix : SETTER_PREFIXES) {
            if (result.length() > prefix.length() && result.startsWith(prefix)
                    && Character.isUpperCase(result.charAt(prefix.length()))) {
                result = StringUtils.lowerFirstLetter(result.substring(prefix.length()));
                return result;
            }
        }
        return result;
    }
}
