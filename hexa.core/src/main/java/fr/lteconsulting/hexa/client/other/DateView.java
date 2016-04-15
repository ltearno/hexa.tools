package fr.lteconsulting.hexa.client.other;

import fr.lteconsulting.hexa.client.application.archi.View;
import fr.lteconsulting.hexa.client.common.HexaDate;

public interface DateView extends View
{
	void setData( HexaDate date );

	HexaDate getDate();

	HasSelectionHandlers<HexaDate> getChangeHandlerMng();
}
