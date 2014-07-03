package fr.lteconsulting.hexa.shared.data;

public interface ListDTO extends IdDTO
{
	void setBeforeId( int beforeId );

	void setAfterId( int afterId );
}