package fr.lteconsulting.hexa.databinding.test.inherited;

import fr.lteconsulting.hexa.databinding.annotation.Observable;
import fr.lteconsulting.hexa.databinding.test.inherited.foreign.Ancestor;

@Observable(inherit = true)
public class Grandparent extends Ancestor {

    String grandparentsString;
    int grandparentsNumber;
    protected int grandparentsProtectedNumber;
    @SuppressWarnings("unused")
	private double grandparentsDouble;
}
