package fr.lteconsulting.hexa.client.ui.search;

import java.util.ArrayList;
import java.util.HashMap;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.UIObject;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import fr.lteconsulting.hexa.client.common.Pair;
import fr.lteconsulting.hexa.client.css.HexaCss;
import fr.lteconsulting.hexa.client.ui.ListTable;
import fr.lteconsulting.hexa.client.ui.dialog.MyPopupPanel;
import fr.lteconsulting.hexa.client.ui.search.CriteriaSwitch.XCriteriaSwitch;
import fr.lteconsulting.hexa.client.ui.tree.ILeafWidget;
import fr.lteconsulting.hexa.client.ui.tree.INodeWidget;
import fr.lteconsulting.hexa.client.ui.tree.INodeWidget.XNodeWidget;
import fr.lteconsulting.hexa.client.ui.tree.LeafWidget;
import fr.lteconsulting.hexa.client.ui.tree.NodeWidget;
import fr.lteconsulting.hexa.client.ui.tree.NodeWidget.INodeWidgetFactory;
import fr.lteconsulting.hexa.client.ui.tree.TreeWidget;

public class StandardSearchMng implements SearchMng
{
	interface Css extends HexaCss
	{
		public static final Css CSS = GWT.create( Css.class );

		String anchorButton();
	}

	public static JSONObject wrapJson( JSONValue json )
	{
		JSONObject obj = new JSONObject();
		obj.put( "v", new JSONString( "v2" ) );
		obj.put( "data", json );

		return obj;
	}

	public static JSONValue unwrapJson( JSONValue json )
	{
		if( json == null )
			return null;
		JSONObject obj = json.isObject();
		if( obj == null )
			return null;
		JSONValue v = obj.get( "v" );
		if( v == null )
			return null;
		JSONString vs = v.isString();
		if( vs == null )
			return null;
		if( !vs.stringValue().equals( "v2" ) )
			return null;

		return obj.get( "data" );
	}

	String name;

	ArrayList<Pair<String, String>> standardOperators = new ArrayList<Pair<String, String>>();
	ArrayList<String> standardOperatorIds = new ArrayList<String>();

	ArrayList<ICriteriaMng> criteriaMngList = new ArrayList<ICriteriaMng>();

	HashMap<String, ICriteriaMng> standardPointingFieldCriteriaMngs = new HashMap<String, ICriteriaMng>();
	HashMap<String, ICriteriaMng> standardPointedByCriteriaMngs = new HashMap<String, ICriteriaMng>();

	HashMap<ICriteriaMng, Integer> alignments = new HashMap<ICriteriaMng, Integer>();

	HashMap<String, ICriteriaMng> fieldNameToCriteriaMngs = new HashMap<String, ICriteriaMng>();

	public void addStandardCombineOperator( String displayName, String id )
	{
		standardOperators.add( new Pair<String, String>( id, displayName ) );
		standardOperatorIds.add( id );
	}

	public void addStandardCriteriaMng( String displayName, String fieldName, ArrayList<Pair<String, String>> comparators, ValueUIFactory factory )
	{
		ICriteriaMng mng = new StandardCriteriaMng( displayName, fieldName, comparators, factory );
		criteriaMngList.add( mng );

		fieldNameToCriteriaMngs.put( fieldName, mng );

		alignments.put( mng, 0 );
	}

	public void addCriteriaMng( ICriteriaMng mng, String fieldName )
	{
		criteriaMngList.add( mng );

		if( fieldName != null )
			fieldNameToCriteriaMngs.put( fieldName, mng );

		alignments.put( mng, 0 );
	}

	public void addStandardPointingFieldCriteriaMngEx( String displayName, SearchMng searchMng, String pointingField, String pointedTable, String pointedField )
	{
		ICriteriaMng mng = new StandardPointingFieldCriteriaMng( displayName, searchMng, pointingField, pointedTable, pointedField );
		standardPointingFieldCriteriaMngs.put( displayName, mng );
		criteriaMngList.add( mng );

		fieldNameToCriteriaMngs.put( pointingField + "->" + pointedTable + "." + pointedField, mng );

		alignments.put( mng, 1 );
	}

	public void addStandardPointedByCriteriaMng( String displayName, SearchMng searchMng, String pointingTable )
	{
		ICriteriaMng mng = new StandardPointedByCriteriaMng( displayName, searchMng, pointingTable );
		standardPointedByCriteriaMngs.put( displayName, mng );
		criteriaMngList.add( mng );

		fieldNameToCriteriaMngs.put( "<-" + pointingTable, mng );

		alignments.put( mng, 1 );
	}

	@Override
	public SearchInstance createSearchWidget( boolean fReadOnly )
	{
		return new SearchInstanceImpl( fReadOnly );
	}

	public void init( String name )
	{
		this.name = name;

		addStandardCombineOperator( "Or", "or" );
		addStandardCombineOperator( "And", "and" );
	}

	boolean getIsWidgetInlined( ICriteriaMng mng )
	{
		Integer alignment = alignments.get( mng );
		return alignment == 0;
	}

	void createSwitchAndAddChild( ICriteriaMng mng, INodeWidget<String, CriteriaSwitch> nodeWidget, JSONValue json, boolean fReadOnly )
	{
		CriteriaSwitch cw = new CriteriaSwitch( criteriaMngList, new XCriteriaSwitch()
		{
			@Override
			public boolean getIsInline( ICriteriaMng mng )
			{
				return getIsWidgetInlined( mng );
			}
		}, null, fReadOnly );
		cw.setCriteriaMng( mng );
		if( json != null )
			cw.setValue( json );

		LeafWidget<String, CriteriaSwitch> leaf = new LeafWidget<String, CriteriaSwitch>( fReadOnly );
		leaf.setWidget( cw );
		leaf.setData( cw );

		nodeWidget.addChild( leaf );
	}

	private class SearchInstanceImpl implements SearchInstance, XNodeWidget<String, CriteriaSwitch>
	{
		private boolean fReadOnly;

		INodeWidgetFactory<String, CriteriaSwitch> nodeFactory = new INodeWidgetFactory<String, CriteriaSwitch>()
		{
			@Override
			public NodeWidget<String, CriteriaSwitch> create()
			{
				NodeWidget<String, CriteriaSwitch> w = new NodeWidget<String, CriteriaSwitch>( standardOperators, SearchInstanceImpl.this, this, fReadOnly );

				if( !fReadOnly )
				{
					for( ICriteriaMng mng : standardPointingFieldCriteriaMngs.values() )
						addButton( w, mng );

					for( ICriteriaMng mng : standardPointedByCriteriaMngs.values() )
						addButton( w, mng );
				}

				return w;
			}

			void addButton( final NodeWidget<String, CriteriaSwitch> nodeWidget, final ICriteriaMng mng )
			{
				Anchor a = new Anchor( mng.getDisplayName() );
				a.getElement().setClassName( Css.CSS.anchorButton() );
				nodeWidget.addButton( a );

				a.addClickHandler( new ClickHandler()
				{
					@Override
					public void onClick( ClickEvent event )
					{
						event.preventDefault();
						event.stopPropagation();

						createSwitchAndAddChild( mng, nodeWidget, null, fReadOnly );
					}
				} );
			}
		};

		NodeWidget<String, CriteriaSwitch> rootNode = nodeFactory.create();

		public SearchInstanceImpl( boolean fReadOnly )
		{
			this.fReadOnly = fReadOnly;
		}

		@Override
		public Widget asWidget()
		{
			return rootNode.asWidget();
		}

		@Override
		public JSONValue getValue()
		{
			return getJSONForTree( rootNode );
		}

		String getJSONString( JSONValue value )
		{
			if( value == null )
				return null;
			JSONString s = value.isString();
			if( s == null )
				return null;
			return s.stringValue();
		}

		void pushValue( JSONValue json, NodeWidget<String, CriteriaSwitch> parentNode )
		{
			if( json == null )
				return;

			JSONObject obj = json.isObject();
			if( obj == null )
				return;

			String op = obj.get( "op" ).isString().stringValue();
			if( op.equals( "->" ) )
			{
				String field = getJSONString( obj.get( "field" ) );
				String pointedTable = getJSONString( obj.get( "pointed_table" ) );
				String pointedField = getJSONString( obj.get( "pointed_field" ) );
				if( field == null || pointedTable == null || pointedField == null )
					return;
				ICriteriaMng mng = fieldNameToCriteriaMngs.get( field + "->" + pointedTable + "." + pointedField );
				createSwitchAndAddChild( mng, parentNode, obj.get( "sub" ), fReadOnly );
			}
			else if( op.equals( "<-" ) )
			{
				String pointingTable = obj.get( "pointing_table" ).isString().stringValue();
				ICriteriaMng mng = fieldNameToCriteriaMngs.get( "<-" + pointingTable );
				createSwitchAndAddChild( mng, parentNode, obj.get( "sub" ), fReadOnly );
			}
			else if( op.equals( "or" ) || op.equals( "and" ) )
			{
				NodeWidget<String, CriteriaSwitch> node = nodeFactory.create();
				node.setData( op );

				parentNode.addChild( node );

				JSONArray ops = obj.get( "ops" ).isArray();
				for( int i = 0; i < ops.size(); i++ )
				{
					pushValue( ops.get( i ), node );
				}
			}
			else
			{
				String field = obj.get( "field" ).isString().stringValue();
				ICriteriaMng mng = fieldNameToCriteriaMngs.get( field );
				createSwitchAndAddChild( mng, parentNode, obj, fReadOnly );
			}
		}

		@Override
		public void setValue( JSONValue json )
		{
			pushValue( json, rootNode );
		}

		@Override
		public void onWantAdd( final INodeWidget<String, CriteriaSwitch> node, UIObject uiObject )
		{
			// a node or sub node wants a child to be added to itself.
			// we are in charge of creating a new TreeWidget and
			// to give it to the node that asked for a creation

			final ListTable<ICriteriaMng> list = new ListTable<ICriteriaMng>();
			for( ICriteriaMng mng : criteriaMngList )
				list.addItem( mng.getDisplayName(), mng );

			VerticalPanel p = new VerticalPanel();
			p.add( new HTML( "<b>" + name + "</b> fields :" ) );
			p.add( list );

			final MyPopupPanel popup = new MyPopupPanel( true );
			popup.setWidget( p );

			popup.showRelativeTo( uiObject );

			list.addChangeHandler( new ChangeHandler()
			{
				@Override
				public void onChange( ChangeEvent event )
				{
					ICriteriaMng mng = list.getSelected();

					createSwitchAndAddChild( mng, node, null, fReadOnly );

					popup.hide();
				}
			} );
		}

		@Override
		public void onWantDelete( INodeWidget<String, CriteriaSwitch> node, TreeWidget<String, CriteriaSwitch> child, UIObject uiObject )
		{
			node.removeChild( child );
		}

	}

	private JSONValue getJSONForTree( TreeWidget<String, CriteriaSwitch> tree )
	{
		INodeWidget<String, CriteriaSwitch> node = tree.isNode();
		if( node != null )
			return getJSONForNode( node );

		ILeafWidget<String, CriteriaSwitch> leaf = tree.isLeaf();
		if( leaf != null )
			return getJSONForLeaf( leaf );

		return null;
	}

	private JSONValue getJSONForLeaf( ILeafWidget<String, CriteriaSwitch> leaf )
	{
		return leaf.getData().getValue();
	}

	private JSONValue getJSONForNode( INodeWidget<String, CriteriaSwitch> node )
	{
		ArrayList<TreeWidget<String, CriteriaSwitch>> children = node.getChildren();

		if( children.isEmpty() )
			return null;

		if( children.size() == 1 )
			return getJSONForTree( children.get( 0 ) );

		JSONArray array = new JSONArray();
		for( int i = 0; i < children.size(); i++ )
			array.set( i, getJSONForTree( children.get( i ) ) );

		JSONObject obj = new JSONObject();
		obj.put( "op", new JSONString( node.getData() ) );
		obj.put( "ops", array );

		return obj;
	}
}
