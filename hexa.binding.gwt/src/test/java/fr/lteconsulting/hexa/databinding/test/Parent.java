package fr.lteconsulting.hexa.databinding.test;

import fr.lteconsulting.hexa.databinding.gwt.annotation.Observable;

import java.util.HashMap;
import java.util.List;

@Observable(inheritDepth = 1)
public class Parent extends Grandparent {

    String parentsString;
    int parentsNumber;

    HashMap<String, List<SampleDTO1>> parentsMap;

    private double parentsPrivateNoGetterSetter;
    private double parentsPrivateHasGetterSetter;

    public double getParentsPrivateHasGetterSetter() {
        return parentsPrivateHasGetterSetter;
    }

    public void setParentsPrivateHasGetterSetter(double parentsPrivateHasGetterSetter) {
        this.parentsPrivateHasGetterSetter = parentsPrivateHasGetterSetter;
    }
}
