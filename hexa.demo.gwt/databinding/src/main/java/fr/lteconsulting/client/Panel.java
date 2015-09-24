package fr.lteconsulting.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.HeadingElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.Widget;

public class Panel extends ComplexPanel {
    private static PanelUiBinder uiBinder = GWT.create(PanelUiBinder.class);
    @UiField
    HeadingElement title;
    @UiField
    DivElement body;

    public Panel(String title) {
        setElement(uiBinder.createAndBindUi(this));

        this.title.setInnerHTML(title);
    }

    @Override
    public void add(Widget child) {
        add(child, body);
        child.setWidth("100%");
    }

    interface PanelUiBinder extends UiBinder<Element, Panel> {
    }
}
