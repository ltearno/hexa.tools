package fr.lteconsulting.mvp.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.Widget;

public class WorkPanelController implements IsWidget
{
	private final LayoutPanel panel = new LayoutPanel();

	private final List<WPInfo> panels = new ArrayList<>();

	public WorkPanelController()
	{
	}

	@Override
	public Widget asWidget()
	{
		return panel;
	}

	public void startWorkPanel( IWorkPanel workPanel, XWPExec callback, WPDisplayMode mode )
	{
		WPInfo info = new WPInfo( workPanel, callback, mode );
		panels.add( info );

		info.startPanel();
	}

	private class WPInfo implements IWPContext
	{
		final IWorkPanel workPanel;
		final XWPExec callback;
		WPDisplayMode mode;

		IsWidget view;

		WPInfo( IWorkPanel workPanel, XWPExec callback, WPDisplayMode mode )
		{
			this.workPanel = workPanel;
			this.callback = callback;
			this.mode = mode;
		}

		void startPanel()
		{
			workPanel.start( this );
		}

		@Override
		public void display( IsWidget view )
		{
			removeView();

			setView( view );
		}
		
		@Override
		public void setDisplayMode( WPDisplayMode mode )
		{
			this.mode = mode;
			updateViewMode();
		}

		@Override
		public void exit()
		{
			removeView();
			panels.remove( this );
			
			if( callback != null )
				callback.onCancel();
		}

		@Override
		public void exit( Throwable throwable )
		{
			removeView();
			panels.remove( this );
			
			if( callback != null )
				callback.onError( throwable );
		}

		@Override
		public void exit( Object result )
		{
			removeView();
			panels.remove( this );
			
			if( callback != null )
				callback.onResult( result );
		}

		private void setView( IsWidget view )
		{
			if( view == null )
				return;

			this.view = view;

			panel.add( view );
			
			updateViewMode();
		}
		
		private void updateViewMode()
		{
			if( view == null )
				return;
			
			switch( mode )
			{
				case FULL:
					panel.setWidgetTopBottom( view, 0, Unit.PX, 0, Unit.PX );
					panel.setWidgetLeftRight( view, 0, Unit.PX, 0, Unit.PX );
					break;
				case DIALOG:
					panel.setWidgetTopBottom( view, 25, Unit.PCT, 25, Unit.PCT );
					panel.setWidgetLeftRight( view, 25, Unit.PCT, 25, Unit.PCT );
					break;
			}
		}

		private void removeView()
		{
			if( view != null )
				panel.remove( view );

			view = null;
		}
	}
}
