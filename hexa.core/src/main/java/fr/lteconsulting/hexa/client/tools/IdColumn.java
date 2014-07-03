package fr.lteconsulting.hexa.client.tools;

import fr.lteconsulting.hexa.client.interfaces.IHasIntegerId;
import fr.lteconsulting.hexa.client.ui.miracle.Printer;
import fr.lteconsulting.hexa.client.ui.tools.ROColumnMng;

public class IdColumn<T extends IHasIntegerId> extends ROColumnMng<T>
{
	public IdColumn()
	{
		this( "Id" );
	}
	
	public IdColumn( String title )
	{
		super( title );
	}

	@Override
	public void fillCell( Printer printer, T record )
	{
		printer.setText( String.valueOf( record.getId() ) );
	}
}
