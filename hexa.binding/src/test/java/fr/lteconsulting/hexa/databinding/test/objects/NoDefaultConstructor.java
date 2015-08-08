package fr.lteconsulting.hexa.databinding.test.objects;

import fr.lteconsulting.hexa.databinding.annotation.Observable;

@Observable
public class NoDefaultConstructor {

    protected NoDefaultConstructor(String overriding) {
        // No default constructor!
    }
}
