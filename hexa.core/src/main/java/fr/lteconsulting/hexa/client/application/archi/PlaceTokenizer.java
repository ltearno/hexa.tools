package fr.lteconsulting.hexa.client.application.archi;

public interface PlaceTokenizer {
    String getToken(Place place);

    Place getPlace(String token);
}
