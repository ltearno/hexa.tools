package fr.lteconsulting.hexa.demo.client.persistence;

import java.util.List;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonToken;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.tree.CommonTree;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;

import fr.lteconsulting.hexa.client.ui.UiBuilder;
import fr.lteconsulting.hexa.persistence.client.hqlLexer;
import fr.lteconsulting.hexa.persistence.client.hqlParser;
import fr.lteconsulting.hexa.persistence.client.hqlParser.statement_return;

public class PersistenceDemo implements EntryPoint
{
	HTML result = new HTML();

	@Override
	public void onModuleLoad()
	{
		EMTest emtest = new EMTest();
		emtest.run();

		if( RootPanel.get( "place" ) == null )
		{
			DivElement place = Document.get().createDivElement();
			place.setId( "place" );
			Document.get().getBody().appendChild( place );
		}

		final TextBox tb = new TextBox();
		tb.setWidth( "500px" );
		tb.setText( "sElect a.text from Activity a left join Category c" );
		
		Button parseButton = new Button( "Parse !" );
		parseButton.addClickHandler( new ClickHandler()
		{
			@Override
			public void onClick( ClickEvent event )
			{
				parse( tb.getText() );
			}
		} );
		
		UiBuilder.addIn( RootPanel.get("place"), 
				new Label( "Please enter an HQL expression to be parsed :" ),
				tb,
				parseButton,
				result );
	}

	void parse( String expression )
	{
		hqlLexer lexer = new hqlLexer( new ANTLRNoCaseStringStream( expression ) );
		CommonTokenStream tokenStream = new CommonTokenStream( lexer );
		hqlParser parser = new hqlParser( tokenStream );
		try
		{
			statement_return statement = parser.statement();
			if( statement == null )
			{
				result.setHTML( "<span style='color:red;'>Unable to parse. Syntax error ?</span>" );
				return;
			}

			StringBuilder sb = new StringBuilder();
			sb.append( "<span style='color:green;'>Successfully parsed</span><br/>Here is a text representation of the <b>Abstract Syntax Tree</b><br/>" );
			sb.append( statement.getTree().toStringTree() + "<br/><br/>" );
			sb.append( "<div style='border:1px solid grey;'>" + visitTreeHTML( statement.getTree(), parser.getTokenNames(), 0 ) + "</div>" );

			result.setHTML( sb.toString() );
		}
		catch( RecognitionException e )
		{
			result.setHTML( "<span style='color:red;'>Unable to parse. RecognitionException : " + e.getMessage() + "</span>" );
		}
	}

	String visitTreeHTML( CommonTree tree, String[] tokenNames, int indent )
	{
		if( tree == null )
			return "(null-tree)";

		String tokenText = tree.getToken()!=null ? tree.getToken().getText() : "???";
		String tokenType = tokenNames[tree.getType()];

		String res = makeIndent( indent );
		if( tokenText.equalsIgnoreCase( tokenType ) )
			res += tokenType;
		else
			res += tokenText + " (" + tokenNames[tree.getType()] + ")";

		res += "<br/>";

		List<?> children = tree.getChildren();
		if( children == null )
			return res;

		indent++;

		for( Object child : children )
		{
			if( child instanceof CommonTree )
			{
				res += visitTreeHTML( (CommonTree ) child, tokenNames, indent );
			}
			else
			{
				res += "@#&!!!<br/>";
			}
		}

		indent--;

		return res;
	}

	private String makeIndent( int indent )
	{
		StringBuilder sb = new StringBuilder();
		for( int i=0; i<indent; i++ )
			sb.append( "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" );
		return sb.toString();
	}

	String visitTree( CommonTree tree, String[] tokenNames )
	{
		if( tree == null )
			return "(null-tree)";

		String tokenText = tree.getToken()!=null ? tree.getToken().getText() : "???";
		String tokenType = tokenNames[tree.getType()];

		String res;
		if( tokenText.equalsIgnoreCase( tokenType ) )
			res = tokenText;
		else
			res = tokenText + " (" + tokenNames[tree.getType()] + ")";

		if( tree.getToken() == null )
		{
			return tree.toStringTree();
		}

		List<?> children = tree.getChildren();
		if( children == null )
			return res;

		boolean firstChild = true;
		res += " <- { ";

		for( Object child : children )
		{
			if( ! firstChild )
				res += ", ";
			firstChild = false;

			if( child instanceof CommonTree )
			{
				res += visitTree( (CommonTree ) child, tokenNames );
			}
			else if( child instanceof CommonToken )
			{
				CommonToken tok = (CommonToken) child;
				res += " [" + tok.getText() + ";" + tokenNames[tok.getType()] + "] ";
			}
		}

		res += " } ";

		return res;
	}
}


class ANTLRNoCaseStringStream  extends ANTLRStringStream {
    public ANTLRNoCaseStringStream(String stream) {
        super(stream);
    }

    @Override
	public int LA(int i) {
        if ( i==0 ) {
            return 0; // undefined
        }
        if ( i<0 ) {
            i++; // e.g., translate LA(-1) to use offset 0
        }

        if ( (p+i-1) >= n ) {

            return CharStream.EOF;
        }
        return Character.toLowerCase(data[p+i-1]);
    }
}