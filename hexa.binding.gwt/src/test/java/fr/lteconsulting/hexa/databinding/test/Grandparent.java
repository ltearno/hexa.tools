package fr.lteconsulting.hexa.databinding.test;

import fr.lteconsulting.hexa.databinding.gwt.annotation.Observable;
import fr.lteconsulting.hexa.databinding.test.iherited.God;

@Observable(inherit = true)
public class Grandparent extends God {

    String grandparentsString;
    int grandparentsNumber;
    protected int grandparentsProtectedNumber;
    private double grandparentsDouble;
}
