package fr.lteconsulting.hexa.persistence.client.legacy.persistence;


public class CategoryProxy extends Category
{
	private EntityManagerImpl emImpl;

	private boolean fLoaded = false;

	public CategoryProxy( EntityManagerImpl emImpl )
	{
		this.emImpl = emImpl;
	}

	@Override
	public String getCodeEAN()
	{
		if( ! fLoaded )
		{
			emImpl.loadProxyInternalObject( this );
			fLoaded = true;
		}

		return super.getCodeEAN();
	}

	@Override
	public void setCodeEAN( String codeEAN )
	{
		if( ! fLoaded )
		{
			emImpl.loadProxyInternalObject( this );
			fLoaded = true;
		}

		super.setCodeEAN( codeEAN );
	}

	@Override
	public String getMarque()
	{
		if( ! fLoaded )
		{
			emImpl.loadProxyInternalObject( this );
			fLoaded = true;
		}

		return super.getMarque();
	}

	@Override
	public void setMarque( String marque )
	{
		if( ! fLoaded )
		{
			emImpl.loadProxyInternalObject( this );
			fLoaded = true;
		}

		super.setMarque( marque );
	}
}
