package com.hexa.client.databinding.propertyadapters;

import com.hexa.client.tools.Action2;

// Allows the data binding system to perceive something as a property.
// Generally it will be a field in a class, or a pair of getter/setter.
// It can also be an adapter to HasValu<T> widgets, and so on...
public interface PropertyAdapter
{
	// getter and setter
	public Object getValue();
	public void setValue( Object object );

	// registration to changes
	public Object registerPropertyChanged( Action2<PropertyAdapter, Object> callback, Object cookie );
	public void removePropertyChangedHandler( Object handler );
}