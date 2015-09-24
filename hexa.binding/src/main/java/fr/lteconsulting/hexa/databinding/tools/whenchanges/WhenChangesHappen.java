package fr.lteconsulting.hexa.databinding.tools.whenchanges;

import java.util.ArrayList;

import fr.lteconsulting.hexa.client.tools.Action;
import fr.lteconsulting.hexa.databinding.propertyadapters.WriteOnlyPropertyAdapter;
import fr.lteconsulting.hexa.databinding.tools.SmartRegistration;

/**
 * A fluent style class used to be called when some data changes.
 * 
 * <p>For example you can use it like this :
 * <pre>
 	WhenChangesHappen
		.in( this, "mainOperation.label" )
		.in( this, "mainOperation.account.name" )
		.in( this, "mainOperation.writing.balance" )
		.call( new Action() {
			public void exec() {
				// write code depending on the above datas
			}
	} );
 * </pre>
 * @author Arnaud
 *
 */
public class WhenChangesHappen
{
	public static WhenChangesHappenInstance In( Object object, String path )
	{
		return new WhenChangesHappenInstance().in(object, path);
	}
	
	public static class WhenChangesHappenInstance
	{
		private ArrayList<Object> objects = new ArrayList<>();
		private ArrayList<String> paths = new ArrayList<>();
		
		private ArrayList<SmartRegistration> registrations = new ArrayList<>();
		
		private Action action;
		
		private WriteOnlyPropertyAdapter adapter = new WriteOnlyPropertyAdapter()
		{
			@Override
			public void setValue( Object object )
			{
				if( action == null )
					return;
				
				action.exec();
			}
		};
		
		public WhenChangesHappenInstance in(Object object, String path)
		{
			objects.add( object );
			paths.add( path );
			
			return this;
		}
		
		public void call( Action action )
		{
			this.action = action;
			
			for( SmartRegistration sm : registrations )
				sm.unregister();
			
			for( int i=0; i<objects.size(); i++ )
			{
				if( registrations.size() <= i )
					registrations.add( new SmartRegistration( adapter ) );
				
				registrations.get( i ).register( objects.get(i), paths.get(i) );
			}
		}
	}
}
