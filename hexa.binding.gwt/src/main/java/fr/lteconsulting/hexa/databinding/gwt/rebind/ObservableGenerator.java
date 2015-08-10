package fr.lteconsulting.hexa.databinding.gwt.rebind;

import com.google.gwt.core.ext.Generator;
import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.NotFoundException;
import com.google.gwt.core.ext.typeinfo.TypeOracle;

public class ObservableGenerator extends Generator {

    protected TreeLogger treeLogger;

    protected JClassType interfaceType(TypeOracle oracle, String s) throws UnableToCompleteException {
        JClassType interfaceType;
        try {
            interfaceType = oracle.getType(s);
        } catch (NotFoundException e) {
            treeLogger.log(TreeLogger.ERROR, String.format("%s: Could not find the interface [%s]. %s",
                e.getClass().getName(), s, e.getMessage()));
            throw new UnableToCompleteException();
        }
        return interfaceType;
    }

    @Override
    public String generate(TreeLogger treeLogger, GeneratorContext generatorContext, String s)
            throws UnableToCompleteException {
        this.treeLogger = treeLogger;
        JClassType interfaceType = interfaceType(generatorContext.getTypeOracle(), s);

        if(interfaceType.getSimpleSourceName().contains("Internal")) {
            return interfaceType.getQualifiedSourceName().replace("Internal", "");
        }
        else {
            String simpleName = interfaceType.getName();
            return interfaceType.getQualifiedSourceName().replace(simpleName, "Observable"+simpleName);
        }
    }
}