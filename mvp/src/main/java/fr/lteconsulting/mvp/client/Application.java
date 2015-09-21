package fr.lteconsulting.mvp.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootLayoutPanel;

import fr.lteconsulting.hexa.client.ui.UiBuilder;

/**
 * The GWT EntryPoint class to MVP application
 * 
 * @author Arnaud Tournier
 * 
 */
public class Application implements EntryPoint
{
	@Override
	public void onModuleLoad()
	{
		WorkPanelController ctrl = new WorkPanelController();

		RootLayoutPanel.get().add( ctrl );

		ctrl.startWorkPanel( new IWorkPanel()
		{
			IWPContext context;
			
			Button cancel = new Button( "cancel" );
			Button validate = new Button( "validate" );
			Button exception = new Button( "exception" );

			@Override
			public void start( final IWPContext context )
			{
				this.context = context;

				context.display( UiBuilder.vert( new Label( "Welcome !" ), validate, exception, cancel ) );

				validate.addClickHandler( new ClickHandler()
				{
					@Override
					public void onClick( ClickEvent event )
					{
						context.exit((Integer)53);
					}
				} );

				exception.addClickHandler( new ClickHandler()
				{
					@Override
					public void onClick( ClickEvent event )
					{
						context.exit( new IllegalStateException( "bad bad bad!" ) );
					}
				} );

				cancel.addClickHandler( new ClickHandler()
				{
					@Override
					public void onClick( ClickEvent event )
					{
						context.exit();
					}
				} );
			}

			@Override
			public void close( IClosingProcess closingProcess )
			{
				boolean res = Window.confirm( "Really quit ?" );
				if( res )
					context.exit();
				else
					closingProcess.abort();
			}
		}, new XWPExec()
		{
			@Override
			public void onResult( Object result )
			{
				Window.alert( "Result ! : " + result );
			}

			@Override
			public void onError( Throwable throwable )
			{
				Window.alert( "Error ! : " + throwable );
			}

			@Override
			public void onCancel()
			{
				Window.alert( "Cancelled..." );
			}
		}, WPDisplayMode.DIALOG );
	}
}