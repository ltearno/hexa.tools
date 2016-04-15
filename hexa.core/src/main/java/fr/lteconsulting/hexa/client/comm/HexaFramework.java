package fr.lteconsulting.hexa.client.comm;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

import fr.lteconsulting.hexa.client.comm.HexaFramework.ImageResources;

public class HexaFramework
{
	public interface ImageResources
	{
		ImageResource blank();

		ImageResource blank16();

		ImageResource ok();

		ImageResource cancel();

		ImageResource add();

		ImageResource delete();

		ImageResource addPlus();

		ImageResource treePlus();

		ImageResource treeMinus();

		ImageResource dropdown();

		ImageResource ellipsis();
	}

	public static ImageResources images = GWT.create( DefaultImages.class );
}

interface DefaultImages extends ClientBundle, ImageResources
{
	@Override
	@Source( "blank.png" )
	ImageResource blank();

	@Override
	@Source( "blank16.png" )
	ImageResource blank16();

	@Override
	@Source( "16-em-cross.png" )
	ImageResource delete();

	@Override
	@Source( "16-em-plus.png" )
	ImageResource add();

	@Override
	@Source( "16-em-plus.png" )
	ImageResource addPlus();

	@Override
	@Source( "16-em-cross.png" )
	ImageResource cancel();

	@Override
	@Source( "16-em-check.png" )
	ImageResource ok();

	@Override
	@Source( "16-arrow-down.png" )
	ImageResource treeMinus();

	@Override
	@Source( "16-arrow-right.png" )
	ImageResource treePlus();

	@Override
	@Source( "dropdown.png" )
	ImageResource dropdown();
	
	@Override
	@Source( "ellipsis.png" )
	ImageResource ellipsis();
}