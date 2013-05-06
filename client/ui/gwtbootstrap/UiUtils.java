package com.hexa.client.ui.gwtbootstrap;

import com.github.gwtbootstrap.client.ui.Brand;
import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.ButtonGroup;
import com.github.gwtbootstrap.client.ui.ButtonToolbar;
import com.github.gwtbootstrap.client.ui.ControlGroup;
import com.github.gwtbootstrap.client.ui.ControlLabel;
import com.github.gwtbootstrap.client.ui.Controls;
import com.github.gwtbootstrap.client.ui.Dropdown;
import com.github.gwtbootstrap.client.ui.Fieldset;
import com.github.gwtbootstrap.client.ui.Form;
import com.github.gwtbootstrap.client.ui.Heading;
import com.github.gwtbootstrap.client.ui.HelpBlock;
import com.github.gwtbootstrap.client.ui.Hero;
import com.github.gwtbootstrap.client.ui.Label;
import com.github.gwtbootstrap.client.ui.Modal;
import com.github.gwtbootstrap.client.ui.ModalFooter;
import com.github.gwtbootstrap.client.ui.Nav;
import com.github.gwtbootstrap.client.ui.NavLink;
import com.github.gwtbootstrap.client.ui.NavSearch;
import com.github.gwtbootstrap.client.ui.NavText;
import com.github.gwtbootstrap.client.ui.NavWidget;
import com.github.gwtbootstrap.client.ui.Navbar;
import com.github.gwtbootstrap.client.ui.Paragraph;
import com.github.gwtbootstrap.client.ui.PasswordTextBox;
import com.github.gwtbootstrap.client.ui.TextArea;
import com.github.gwtbootstrap.client.ui.TextBox;
import com.github.gwtbootstrap.client.ui.Well;
import com.github.gwtbootstrap.client.ui.WellForm;
import com.github.gwtbootstrap.client.ui.constants.Alignment;
import com.github.gwtbootstrap.client.ui.constants.AlternateSize;
import com.github.gwtbootstrap.client.ui.constants.ButtonType;
import com.github.gwtbootstrap.client.ui.constants.FormType;
import com.github.gwtbootstrap.client.ui.constants.IconType;
import com.github.gwtbootstrap.client.ui.constants.LabelType;
import com.github.gwtbootstrap.datepicker.client.ui.DateBox;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.IsWidget;

public class UiUtils
{
	public static Modal modal( String title, IsWidget... children )
	{
		Modal res = new Modal( true, true );
		res.setTitle( title );
		return addIn( res, children );
	}

	public static ModalFooter modalFooter( IsWidget... children )
	{
		return addIn( new ModalFooter(), children );
	}

	public static Form form( FormType formType, IsWidget... children )
	{
		Form form = new Form();
		form.setType( FormType.HORIZONTAL );

		return addIn( form, children );
	}

	public static Well well( String html )
	{
		return new Well( html );
	}

	public static WellForm wellForm( String url, String method, String encoding, IsWidget... children )
	{
		WellForm form = new WellForm();
		form.setAction( url );
		form.setMethod( method );
		form.setEncoding( encoding );

		return addIn( form, children );
	}

	public static Fieldset fieldSet( IsWidget... children )
	{
		return addIn( new Fieldset(), children );
	}

	public static ControlGroup controlGroup( IsWidget... children )
	{
		return addIn( new ControlGroup(), children );
	}

	public static Controls controls( IsWidget... children )
	{
		return addIn( new Controls(), children );
	}

	public static ButtonToolbar buttonToolbar( IsWidget... children )
	{
		return addIn( new ButtonToolbar(), children );
	}

	public static ButtonGroup buttonGroup( IsWidget... children )
	{
		return addIn( new ButtonGroup(), children );
	}

	public static Button button( String text, IconType iconType )
	{
		return new Button( text, iconType );
	}

	public static Button button( String text, ButtonType buttonType )
	{
		Button res = new Button( text );
		res.setType( buttonType );
		return res;
	}

	public static Button button( String text, IconType iconType, ButtonType buttonType )
	{
		Button res = button( text, iconType );
		res.setType( buttonType );
		return res;
	}

	public static Button buttonOk( String text )
	{
		return button( text, IconType.OK_SIGN, ButtonType.INVERSE );
	}

	public static Button buttonCancel()
	{
		return button( "Annuler", IconType.BACKWARD );
	}

	public static TextBox textBox( String placeholder )
	{
		TextBox textBox = new TextBox();
		textBox.setPlaceholder( placeholder );

		return textBox;
	}

	public static TextBox textBox( String placeHolder, AlternateSize alternateSize )
	{
		TextBox textBox = textBox( placeHolder );
		textBox.setAlternateSize( alternateSize );

		return textBox;
	}

	public static PasswordTextBox passwordTextBox( String placeHolder, AlternateSize alternateSize )
	{
		PasswordTextBox passwordTextBox = new PasswordTextBox();
		passwordTextBox.setAlternateSize( alternateSize );
		passwordTextBox.setPlaceholder( placeHolder );

		return passwordTextBox;
	}

	public static TextArea textArea( String placeholder )
	{
		TextArea textArea = new TextArea();
		textArea.setPlaceholder( placeholder );

		return textArea;
	}

	public static DateBox dateBox()
	{
		return new DateBox();
	}

	public static ControlLabel controlLabel( String label )
	{
		return new ControlLabel( label );
	}

	public static HelpBlock helpBlock( String html )
	{
		return new HelpBlock( html );
	}

	public static Hero hero( IsWidget... children )
	{
		return addIn( new Hero(), children );
	}

	public static Heading heading( int size, String text )
	{
		return new Heading( size, text );
	}

	public static Paragraph paragraph( String html )
	{
		return new Paragraph( html );
	}

	public static Label label( LabelType labelType, String text )
	{
		return new Label( labelType, text );
	}

	public static Navbar navBar( IsWidget... children )
	{
		return addIn( new Navbar(), children );
	}

	public static Nav nav( Alignment alignment, IsWidget... children )
	{
		Nav nav = new Nav();
		nav.setAlignment( alignment );

		return addIn( nav, children );
	}

	public static NavLink navLink( String text )
	{
		return new NavLink( text );
	}

	public static NavWidget navWidget( IconType iconType )
	{
		NavWidget navWidget = new NavWidget();
		navWidget.setIcon( iconType );

		return navWidget;
	}

	public static Dropdown dropdown( String caption, IsWidget... children )
	{
		return addIn( new Dropdown( caption ), children );
	}

	public static NavSearch navSearch( int size, String placeholder )
	{
		NavSearch navSearch = new NavSearch( size );
		navSearch.setPlaceholder( placeholder );

		return navSearch;
	}

	public static Brand brand( String text )
	{
		return new Brand( text );
	}

	public static NavText navText( Alignment alignment, String text )
	{
		NavText navText = new NavText( text );
		navText.setAlignment( alignment );

		return navText;
	}

	public static <T extends HasWidgets.ForIsWidget> T addIn( T parent, IsWidget... children )
	{
		for( IsWidget widget : children )
			parent.add( widget );

		return parent;
	}
}
