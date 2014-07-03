package fr.lteconsulting.hexa.client.ui.widget;

import java.util.ArrayList;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;

public class ChoiceControl extends Composite implements ValueChangeHandler<Boolean>
{
	public interface ChoiceControlCallback
	{
		public void onChoiceControlChange( Object cookie );
	}

	private FlexTable table = new FlexTable();
	private int[] ids = null;

	private ChoiceControlCallback callback = null;
	private Object cookie = null;

	public ChoiceControl()
	{
		initWidget( table );
	}

	public void setCallback( ChoiceControlCallback callback, Object cookie )
	{
		this.callback = callback;
		this.cookie = cookie;
	}

	public void setChoices( int[] ids, String[] names, int[] choosenIds )
	{
		assert (ids.length == names.length);

		this.ids = ids;

		table.clear( true );

		for( int i = 0; i < ids.length; i++ )
		{
			CheckBox c = new CheckBox();
			if( choosenIds != null )
			{
				for( int j = 0; j < choosenIds.length; j++ )
					if( ids[i] == choosenIds[j] )
					{
						c.setValue( true );
						break;
					}
			}
			c.addValueChangeHandler( this );
			table.setWidget( i, 0, c );
			table.setText( i, 1, names[i] );
		}
	}

	public void setChoosenIds( int[] choosenIds )
	{
		if( ids == null )
			return;

		for( int i = 0; i < ids.length; i++ )
		{
			CheckBox c = (CheckBox) table.getWidget( i, 0 );

			boolean value = false;
			for( int j = 0; j < choosenIds.length; j++ )
				if( ids[i] == choosenIds[j] )
				{
					value = true;
					break;
				}
			c.setValue( value );
		}
	}

	public ArrayList<Integer> getChoices()
	{
		ArrayList<Integer> list = new ArrayList<Integer>();
		if( ids == null )
			return list;
		for( int i = 0; i < ids.length; i++ )
			if( ((CheckBox) table.getWidget( i, 0 )).getValue() )
				list.add( ids[i] );
		return list;
	}

	@Override
	public void onValueChange( ValueChangeEvent<Boolean> event )
	{
		if( callback == null )
			return;

		callback.onChoiceControlChange( cookie );
	}
}