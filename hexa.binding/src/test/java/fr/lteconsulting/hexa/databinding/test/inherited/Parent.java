package fr.lteconsulting.hexa.databinding.test.inherited;

import fr.lteconsulting.hexa.databinding.annotation.Observable;

import java.util.HashMap;
import java.util.List;

@Observable(inheritDepth = 1)
public class Parent extends Grandparent {

    String parentsString;
    int parentsNumber;

    HashMap<String, List<Integer>> parentsMap;

    @SuppressWarnings("unused")
	private double parentsPrivateNoGetterSetter;
    private double parentsPrivateHasGetterSetter;

    public double getParentsPrivateHasGetterSetter() {
        return parentsPrivateHasGetterSetter;
    }

    public void setParentsPrivateHasGetterSetter(double parentsPrivateHasGetterSetter) {
        this.parentsPrivateHasGetterSetter = parentsPrivateHasGetterSetter;
    }
}
