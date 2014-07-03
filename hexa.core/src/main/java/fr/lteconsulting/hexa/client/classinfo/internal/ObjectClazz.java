package fr.lteconsulting.hexa.client.classinfo.internal;

import java.util.ArrayList;
import java.util.List;

import fr.lteconsulting.hexa.client.classinfo.Field;
import fr.lteconsulting.hexa.client.classinfo.Method;

public class ObjectClazz extends fr.lteconsulting.hexa.client.classinfo.internal.ClazzBase<java.lang.Object>
{
	public ObjectClazz()
	{
		super( java.lang.Object.class, "Object", null );
	}

	@Override
	protected List<Field> _getDeclaredFields()
	{
		ArrayList<Field> res = new ArrayList<Field>();
		// res.add( new ___clazz_FieldImpl() );
		// res.add( new expando_FieldImpl() );
		// res.add( new castableTypeMap_FieldImpl() );
		// res.add( new typeMarker_FieldImpl() );
		return res;
	}

	@Override
	protected List<Method> _getMethods()
	{
		ArrayList<Method> res = new ArrayList<Method>();
		// res.add( new equals_MethodImpl() );
		// res.add( new getClass_MethodImpl() );
		// res.add( new hashCode_MethodImpl() );
		// res.add( new toString_MethodImpl() );
		return res;
	}

	@Override
	public java.lang.Object NEW()
	{
		return new java.lang.Object();
	}

	@Override
	protected void _ensureSuperClassInfoRegistered()
	{
	}

	// class ___clazz_FieldImpl extends
	// fr.lteconsulting.hexa.client.classinfo.internal.FieldBase
	// {
	// public ___clazz_FieldImpl()
	// {
	// super( java.lang.Class.class, "___clazz",
	// java.lang.reflect.Modifier.PRIVATE & java.lang.reflect.Modifier.TRANSIENT
	// );
	// }
	//
	// @Override
	// public void setValue( Object object, Object value )
	// {
	// setValueInternal_Object( object, value );
	// }
	//
	// @Override
	// public <OUT> OUT getValue( Object object )
	// {
	// return (OUT) getValueInternal_Object( object );
	// }
	//
	// @Override
	// public native final void copyValueTo( Object source, Object destination )
	// /*-{
	// destination.@java.lang.Object::___clazz =
	// source.@java.lang.Object::___clazz;
	// }-*/;
	//
	// private native final void setValueInternal_int( Object object, int value
	// )
	// /*-{
	// object.@java.lang.Object::___clazz = value;
	// }-*/;
	//
	// private native final int getValueInternal_int( Object object )
	// /*-{
	// return object.@java.lang.Object::___clazz;
	// }-*/;
	//
	// private native final void setValueInternal_Object( Object object, Object
	// value )
	// /*-{
	// object.@java.lang.Object::___clazz = value;
	// }-*/;
	//
	// private native final Object getValueInternal_Object( Object object )
	// /*-{
	// return object.@java.lang.Object::___clazz;
	// }-*/;
	// }
	//
	// class expando_FieldImpl extends
	// fr.lteconsulting.hexa.client.classinfo.internal.FieldBase
	// {
	// public expando_FieldImpl()
	// {
	// super( com.google.gwt.core.client.JavaScriptObject.class, "expando",
	// java.lang.reflect.Modifier.PRIVATE & java.lang.reflect.Modifier.TRANSIENT
	// );
	// }
	//
	// @Override
	// public void setValue( Object object, Object value )
	// {
	// setValueInternal_Object( object, value );
	// }
	//
	// @Override
	// public <OUT> OUT getValue( Object object )
	// {
	// return (OUT) getValueInternal_Object( object );
	// }
	//
	// @Override
	// public native final void copyValueTo( Object source, Object destination )
	// /*-{
	// destination.@java.lang.Object::expando =
	// source.@java.lang.Object::expando;
	// }-*/;
	//
	// private native final void setValueInternal_int( Object object, int value
	// )
	// /*-{
	// object.@java.lang.Object::expando = value;
	// }-*/;
	//
	// private native final int getValueInternal_int( Object object )
	// /*-{
	// return object.@java.lang.Object::expando;
	// }-*/;
	//
	// private native final void setValueInternal_Object( Object object, Object
	// value )
	// /*-{
	// object.@java.lang.Object::expando = value;
	// }-*/;
	//
	// private native final Object getValueInternal_Object( Object object )
	// /*-{
	// return object.@java.lang.Object::expando;
	// }-*/;
	// }
	//
	// class castableTypeMap_FieldImpl extends
	// fr.lteconsulting.hexa.client.classinfo.internal.FieldBase
	// {
	// public castableTypeMap_FieldImpl()
	// {
	// super( com.google.gwt.core.client.JavaScriptObject.class,
	// "castableTypeMap", java.lang.reflect.Modifier.PRIVATE
	// & java.lang.reflect.Modifier.TRANSIENT );
	// }
	//
	// @Override
	// public void setValue( Object object, Object value )
	// {
	// setValueInternal_Object( object, value );
	// }
	//
	// @Override
	// public <OUT> OUT getValue( Object object )
	// {
	// return (OUT) getValueInternal_Object( object );
	// }
	//
	// @Override
	// public native final void copyValueTo( Object source, Object destination )
	// /*-{
	// destination.@java.lang.Object::castableTypeMap =
	// source.@java.lang.Object::castableTypeMap;
	// }-*/;
	//
	// private native final void setValueInternal_int( Object object, int value
	// )
	// /*-{
	// object.@java.lang.Object::castableTypeMap = value;
	// }-*/;
	//
	// private native final int getValueInternal_int( Object object )
	// /*-{
	// return object.@java.lang.Object::castableTypeMap;
	// }-*/;
	//
	// private native final void setValueInternal_Object( Object object, Object
	// value )
	// /*-{
	// object.@java.lang.Object::castableTypeMap = value;
	// }-*/;
	//
	// private native final Object getValueInternal_Object( Object object )
	// /*-{
	// return object.@java.lang.Object::castableTypeMap;
	// }-*/;
	// }
	//
	// class typeMarker_FieldImpl extends
	// fr.lteconsulting.hexa.client.classinfo.internal.FieldBase
	// {
	// public typeMarker_FieldImpl()
	// {
	// super( com.google.gwt.core.client.JavaScriptObject.class, "typeMarker",
	// java.lang.reflect.Modifier.PRIVATE & java.lang.reflect.Modifier.TRANSIENT
	// );
	// }
	//
	// @Override
	// public void setValue( Object object, Object value )
	// {
	// setValueInternal_Object( object, value );
	// }
	//
	// @Override
	// public <OUT> OUT getValue( Object object )
	// {
	// return (OUT) getValueInternal_Object( object );
	// }
	//
	// @Override
	// public native final void copyValueTo( Object source, Object destination )
	// /*-{
	// destination.@java.lang.Object::typeMarker =
	// source.@java.lang.Object::typeMarker;
	// }-*/;
	//
	// private native final void setValueInternal_int( Object object, int value
	// )
	// /*-{
	// object.@java.lang.Object::typeMarker = value;
	// }-*/;
	//
	// private native final int getValueInternal_int( Object object )
	// /*-{
	// return object.@java.lang.Object::typeMarker;
	// }-*/;
	//
	// private native final void setValueInternal_Object( Object object, Object
	// value )
	// /*-{
	// object.@java.lang.Object::typeMarker = value;
	// }-*/;
	//
	// private native final Object getValueInternal_Object( Object object )
	// /*-{
	// return object.@java.lang.Object::typeMarker;
	// }-*/;
	// }
	//
	// class equals_MethodImpl extends
	// fr.lteconsulting.hexa.client.classinfo.internal.MethodBase
	// {
	// public equals_MethodImpl()
	// {
	// super( boolean.class, "equals", new Class<?>[] { java.lang.Object.class }
	// );
	// }
	//
	// @Override
	// public Object call( Object target, Object[] parameters )
	// {
	// try
	// {
	// return target.equals( parameters[0] );
	// }
	// catch( Throwable e )
	// {
	// throw new java.lang.RuntimeException(
	// "CALLED METHOD RAISED AN EXCEPTION equals" );
	// }
	// }
	//
	// }
	//
	// class getClass_MethodImpl extends
	// fr.lteconsulting.hexa.client.classinfo.internal.MethodBase
	// {
	// public getClass_MethodImpl()
	// {
	// super( java.lang.Class.class, "getClass", new Class<?>[] {} );
	// }
	//
	// @Override
	// public Object call( Object target, Object[] parameters )
	// {
	// try
	// {
	// return target.getClass();
	// }
	// catch( Throwable e )
	// {
	// throw new java.lang.RuntimeException(
	// "CALLED METHOD RAISED AN EXCEPTION getClass" );
	// }
	// }
	//
	// }
	//
	// class hashCode_MethodImpl extends
	// fr.lteconsulting.hexa.client.classinfo.internal.MethodBase
	// {
	// public hashCode_MethodImpl()
	// {
	// super( int.class, "hashCode", new Class<?>[] {} );
	// }
	//
	// @Override
	// public Object call( Object target, Object[] parameters )
	// {
	// try
	// {
	// return target.hashCode();
	// }
	// catch( Throwable e )
	// {
	// throw new java.lang.RuntimeException(
	// "CALLED METHOD RAISED AN EXCEPTION hashCode" );
	// }
	// }
	//
	// }
	//
	// class toString_MethodImpl extends
	// fr.lteconsulting.hexa.client.classinfo.internal.MethodBase
	// {
	// public toString_MethodImpl()
	// {
	// super( java.lang.String.class, "toString", new Class<?>[] {} );
	// }
	//
	// @Override
	// public Object call( Object target, Object[] parameters )
	// {
	// try
	// {
	// return target.toString();
	// }
	// catch( Throwable e )
	// {
	// throw new java.lang.RuntimeException(
	// "CALLED METHOD RAISED AN EXCEPTION toString" );
	// }
	// }
	// }
}
