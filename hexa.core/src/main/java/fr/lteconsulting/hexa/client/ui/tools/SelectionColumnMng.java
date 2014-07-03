package fr.lteconsulting.hexa.client.ui.tools;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.CheckBox;
import fr.lteconsulting.hexa.client.interfaces.IHasIntegerId;
import fr.lteconsulting.hexa.client.tools.DoubleMap;
import fr.lteconsulting.hexa.client.ui.miracle.Printer;

public abstract class SelectionColumnMng<T extends IHasIntegerId> extends ROColumnMng<T>
{
	final DoubleMap<Integer, CheckBox> checkBoxes = new DoubleMap<>();
	final Set<Integer> selected = new HashSet<>();

	protected abstract boolean isDisplayed( int recordId );

	public SelectionColumnMng( String title )
	{
		super( title );
	}

	public List<Integer> getSelectedRecords()
	{
		List<Integer> set = new ArrayList<>();
		for( int recordId : selected )
			if( isDisplayed( recordId ) )
				set.add( recordId );
		return set;
	}

	@Override
	public void fillCell( Printer printer, T record )
	{
		CheckBox cb = getCheckBoxForRecord( record );

		cb.setValue( selected.contains( record.getId() ), false );

		printer.setWidget( cb );
	}

	private CheckBox getCheckBoxForRecord( T record )
	{
		CheckBox cb = checkBoxes.get( record.getId() );
		if( cb == null )
		{
			cb = new CheckBox();
			checkBoxes.put( record.getId(), cb );

			cb.addValueChangeHandler( valueChangeHandler );
		}

		return cb;
	}

	private ValueChangeHandler<Boolean> valueChangeHandler = new ValueChangeHandler<Boolean>()
	{
		@Override
		public void onValueChange( ValueChangeEvent<Boolean> event )
		{
			int recordId = checkBoxes.getReverse( (CheckBox) event.getSource() );

			boolean isSelected = ((CheckBox) event.getSource()).getValue();
			if( isSelected )
				selected.add( recordId );
			else
				selected.remove( recordId );
		}
	};
}
