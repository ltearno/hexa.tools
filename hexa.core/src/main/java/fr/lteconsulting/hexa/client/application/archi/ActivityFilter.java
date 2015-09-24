package fr.lteconsulting.hexa.client.application.archi;

public interface ActivityFilter {
    Activity canEnter(Activity activity, Place place);
}
