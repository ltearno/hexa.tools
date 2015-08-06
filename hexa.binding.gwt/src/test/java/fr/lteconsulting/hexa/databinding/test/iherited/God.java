package fr.lteconsulting.hexa.databinding.test.iherited;

import fr.lteconsulting.hexa.databinding.gwt.annotation.Observable;

@Observable
public class God {

    String godsString;
    int godsInt;
    public boolean godsBoolean;
    protected int godsProtectedInt;

    public String getGodsString() {
        return godsString;
    }

    public void setGodsString(String godsString) {
        this.godsString = godsString;
    }

    public int getGodsInt() {
        return godsInt;
    }

    public void setGodsInt(int godsInt) {
        this.godsInt = godsInt;
    }

    public int getGodsProtectedInt() {
        return godsProtectedInt;
    }

    public void setGodsProtectedInt(int godsProtectedInt) {
        this.godsProtectedInt = godsProtectedInt;
    }
}
