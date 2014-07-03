package fr.lteconsulting.hexa.client.ui.editable;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Anchor;

public class EditableAnchor<COOKIE> extends EditableWidget<Anchor, COOKIE>
{
	public interface Callback<COOKIE> extends EditableWidget.Callback<Anchor, COOKIE>
	{
	}

	public interface OnEditCallback<COOKIE> extends EditableWidget.OnEditCallback<Anchor, COOKIE>
	{
	}

	public EditableAnchor( ImageResource loadingImage, Callback<COOKIE> callback, COOKIE cookie )
	{
		super( loadingImage, callback, cookie );
	}

	@Override
	Anchor implInitDisplayWidget()
	{
		return new Anchor();
	}
}
