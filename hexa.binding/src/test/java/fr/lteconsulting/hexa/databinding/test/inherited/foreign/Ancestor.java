package fr.lteconsulting.hexa.databinding.test.inherited.foreign;

import fr.lteconsulting.hexa.databinding.annotation.Observable;

@Observable
public class Ancestor {

    public boolean ancestorsBoolean;
    protected int ancestorsProtectedInt;
    String ancestorsString;
    int ancestorsInt;

    public int getAncestorsProtectedInt() {
        return ancestorsProtectedInt;
    }

    public void setAncestorsProtectedInt(int ancestorsProtectedInt) {
        this.ancestorsProtectedInt = ancestorsProtectedInt;
    }

    public int getAncestorsInt() {
        return ancestorsInt;
    }

    public void setAncestorsInt(int ancestorsInt) {
        this.ancestorsInt = ancestorsInt;
    }

    public String getAncestorsString() {
        return ancestorsString;
    }

    public void setAncestorsString(String ancestorsString) {
        this.ancestorsString = ancestorsString;
    }
}
