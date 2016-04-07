package fr.lteconsulting.hexa.client.application.archi;

public interface ActivityFilter<Place>
{
	Activity<Place> canEnter( Activity<Place> activity, Place place );
}
