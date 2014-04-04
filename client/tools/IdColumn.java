package com.hexa.client.tools;

import com.hexa.client.interfaces.IHasIntegerId;
import com.hexa.client.ui.miracle.Printer;
import com.hexa.client.ui.tools.ROColumnMng;

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
