package de.wetego.vaadin;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.Widgetset;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.server.SpringVaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.v7.ui.Label;


/**
 * The Demo UI.
 */
@SuppressWarnings ( "serial" )
@Theme ( "demo" )
@Title ( "Vaadin8 - Spring Boot Application" )
@SpringUI
@PreserveOnRefresh
@Widgetset("MyAppWidgetset")
public class DemoUI extends UI {

    @Override
    protected void init ( VaadinRequest request ) {
        final VerticalLayout layout = new VerticalLayout ();

        final TextField name = new TextField ();
        name.setCaption ( "Name:" );
        name.setWidth ( 200, Unit.PIXELS );
        name.setPlaceholder ( "Type your name here" );

        Button button = new Button ( "Click Me" );
        button.addClickListener ( e -> {
            layout.addComponent ( new Label ( "Thanks " + name.getValue () + ", it works!" ) );
        } );

        layout.addComponents ( name, button );

        setContent ( layout );
    }

    /**
     * The UI.
     *
     * @return UI with correct type
     */
    public static DemoUI get () {
        return (DemoUI) UI.getCurrent ();
    }

    /**
     * The Servlet.
     */
    @WebServlet ( urlPatterns = "/*", name = "DemoUIServlet", asyncSupported = true )
    @VaadinServletConfiguration ( ui = DemoUI.class, productionMode = false )
    public static class DemoUIServlet extends SpringVaadinServlet {
    }
}
