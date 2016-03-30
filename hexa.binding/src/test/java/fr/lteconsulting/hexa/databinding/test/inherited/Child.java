package fr.lteconsulting.hexa.databinding.test.inherited;

import fr.lteconsulting.hexa.databinding.annotation.Observable;

@Observable(inheritDepth = 3)
public class Child extends Parent {

    String childsString;

    @SuppressWarnings("unused")
	private double childsPrivateNoGetterSetter;
    private double childsPrivateHasGetterSetter;

    public double getChildsPrivateHasGetterSetter() {
        return childsPrivateHasGetterSetter;
    }

    public void setChildsPrivateHasGetterSetter(double childsPrivateHasGetterSetter) {
        this.childsPrivateHasGetterSetter = childsPrivateHasGetterSetter;
    }
}
