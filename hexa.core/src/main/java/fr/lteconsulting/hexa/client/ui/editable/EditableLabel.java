package fr.lteconsulting.hexa.client.ui.editable;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Label;

public class EditableLabel<COOKIE> extends EditableWidget<Label, COOKIE>
{
	public interface Callback<COOKIE> extends EditableWidget.Callback<Label, COOKIE>
	{
	}

	public interface OnEditCallback<COOKIE> extends EditableWidget.OnEditCallback<Label, COOKIE>
	{
	}

	public EditableLabel( ImageResource loadingImage, Callback<COOKIE> callback, COOKIE cookie )
	{
		super( loadingImage, callback, cookie );
	}

	@Override
	Label implInitDisplayWidget()
	{
		return new Label();
	}
}
