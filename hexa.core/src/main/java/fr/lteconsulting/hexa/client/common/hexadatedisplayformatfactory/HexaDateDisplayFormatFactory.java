package fr.lteconsulting.hexa.client.common.hexadatedisplayformatfactory;

import fr.lteconsulting.hexa.client.common.HexaDateDisplayFormat;

public class HexaDateDisplayFormatFactory
{
	public enum Format
	{
		FORMAT_1,
		FORMAT_2,
		FORMAT_3;
	}

	public static HexaDateDisplayFormat get( Format format )
	{
		switch( format )
		{
			case FORMAT_1:
				return new HexaDateDisplayFormat1();
			case FORMAT_2:
				return new HexaDateDisplayFormat2();
			case FORMAT_3:
				return new HexaDateDisplayFormat3();
		}

		return null;
	}
}
