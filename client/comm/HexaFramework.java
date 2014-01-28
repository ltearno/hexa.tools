package com.hexa.client.comm;

import com.google.gwt.resources.client.ImageResource;

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
	}

	public static ImageResources images = null;
}
