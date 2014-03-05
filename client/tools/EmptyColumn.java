package com.hexa.client.tools;

import com.hexa.client.tools.ColumnsSet.IColumnMng;
import com.hexa.client.tools.ColumnsSet.IEditor;
import com.hexa.client.ui.miracle.Printer;

public class EmptyColumn<T> implements IColumnMng<T>
{
	@Override
	public void fillCell( Printer printer, T record )
	{
	}

	@Override
	public IEditor editCell( T record )
	{
		return null;
	}

	@Override
	public String getTitle()
	{
		return "";
	}
}
