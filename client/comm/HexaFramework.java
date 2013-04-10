package com.hexa.client.comm;

import com.google.gwt.resources.client.ImageResource;

public class HexaFramework
{
	public interface ImageResources
	{
		ImageResource blank();
		
		ImageResource ok();
		ImageResource cancel();
		
		ImageResource add();
		ImageResource delete();
		
		ImageResource addPlus();
		
		ImageResource treePlus();
		ImageResource treeMinus();
	}
	
	public static ImageResources images = null;
}
