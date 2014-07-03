package fr.lteconsulting.hexa.client.ui.chart;

import java.util.ArrayList;

import com.google.gwt.core.client.Scheduler;
import fr.lteconsulting.hexa.client.ui.chart.raphael.Raphael.Text;

public class Labels
{
	Layer layer = null;
	Referential ref = null;
	boolean fVertical;

	// list of the text shape we actually have in the layer, to reuse them is
	// possible
	ArrayList<Text> existingPaths = new ArrayList<Text>();

	// current updating shapes working task, to be cancelled if another is to be
	// created
	UpdateShapes updateShapes = null;

	public void init( Layer layer, Referential ref, boolean fVertical )
	{
		this.layer = layer;
		this.ref = ref;
		this.fVertical = fVertical;
	}

	public void update( ArrayList<Float> labelsPositions, ArrayList<String> labelsTexts )
	{
		// stop previous updating task
		if( updateShapes != null )
			updateShapes.cancel();

		// create a new updating task
		updateShapes = new UpdateShapes( labelsPositions, labelsTexts, existingPaths );

		// reset the basket of existing shapes in the layer
		existingPaths = new ArrayList<Text>();

		// schedule the updating task
		Scheduler.get().scheduleIncremental( updateShapes );
	}

	private class UpdateShapes implements Scheduler.RepeatingCommand
	{
		boolean fCancelled = false;

		ArrayList<Float> labelsPositions;
		ArrayList<String> labelsTexts;

		ArrayList<Text> availableShapes;

		float origin;

		UpdateShapes( ArrayList<Float> labelsPositions, ArrayList<String> labelsTexts, ArrayList<Text> availableShapes )
		{
			this.labelsPositions = new ArrayList<Float>( labelsPositions );
			this.labelsTexts = new ArrayList<String>( labelsTexts );
			this.availableShapes = new ArrayList<Text>( availableShapes );

			if( fVertical )
				origin = ref.getRealX( ref.getMinX() ) + 25;// - 5;
			else
				origin = ref.getRealY( ref.getMinY() ) + 10;
		}

		void cancel()
		{
			fCancelled = true;
		}

		@Override
		public boolean execute()
		{
			if( fCancelled || labelsPositions.isEmpty() )
			{
				// clean up
				while( !availableShapes.isEmpty() )
					availableShapes.remove( 0 ).removeFromParent();
				return false;
			}

			float x, y;

			if( fVertical )
			{
				x = origin;
				y = ref.getRealY( labelsPositions.remove( 0 ) );
			}
			else
			{
				x = ref.getRealX( labelsPositions.remove( 0 ) );
				y = origin;
			}

			Text text;
			if( availableShapes.isEmpty() )
			{
				text = layer.addText( x, y, labelsTexts.remove( 0 ) );
				text.attr( "text-anchor", "end" );
			}
			else
			{
				text = availableShapes.remove( 0 );
				text.attr( "text", labelsTexts.remove( 0 ) );
				text.attr( "x", x );
				text.attr( "y", y );
			}

			// mark them to be removed later on
			existingPaths.add( text );

			return true;
		}
	}
}
