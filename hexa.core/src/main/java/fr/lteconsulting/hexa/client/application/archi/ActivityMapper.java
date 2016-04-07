package fr.lteconsulting.hexa.client.application.archi;

public interface ActivityMapper<Place>
{
	Activity<Place> getActivity( Place place );
}
