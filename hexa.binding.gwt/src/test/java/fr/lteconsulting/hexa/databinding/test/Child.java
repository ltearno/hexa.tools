package fr.lteconsulting.hexa.databinding.test;

import fr.lteconsulting.hexa.databinding.gwt.annotation.Observable;

@Observable(inheritDepth = 3)
public class Child extends Parent {

    String childsString;

    private double childsPrivateNoGetterSetter;
    private double childsPrivateHasGetterSetter;

    public double getChildsPrivateHasGetterSetter() {
        return childsPrivateHasGetterSetter;
    }

    public void setChildsPrivateHasGetterSetter(double childsPrivateHasGetterSetter) {
        this.childsPrivateHasGetterSetter = childsPrivateHasGetterSetter;
    }
}
