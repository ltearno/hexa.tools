package fr.lteconsulting.hexa.databinding;

/**
 * Interface through which one receives {@link PropertyChangedEvent}
 */
public interface PropertyChangedHandler
{
	void onPropertyChanged( PropertyChangedEvent event );
}