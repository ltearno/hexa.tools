package fr.lteconsulting.hexa.client.databinding.watchablecollection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;

import fr.lteconsulting.hexa.client.tools.Action1;

/**
 * A Watchable List. Notifications are grouped and
 * are deferred through Scheduler.scheduleDeferred()
 * method.
 * 
 * @author Arnaud
 *
 * @param <T>
 */
public class WatchableCollection<T> implements List<T>
{
	public enum ChangeType
	{
		ADD,
		REMOVE;
	}
	
	public static class Change
	{
		ChangeType type;
		Object item;
		
		public Change( ChangeType type, Object item )
		{
			super();
			this.type = type;
			this.item = item;
		}
		
		public static <T> List<Change> ForItems( ChangeType type, Collection<T> items )
		{
			List<Change> res = new ArrayList<>();
			for( T item : items )
				res.add( new Change( type, item ) );
			
			return res;
		}
		
		public ChangeType getType()
		{
			return type;
		}
		
		@SuppressWarnings( "unchecked" )
		public <T> T getItem()
		{
			return (T) item;
		}
	}
	
	private final List<T> list;
	
	private boolean scheduled;
	private List<Change> scheduledChanges = new ArrayList<>();
	
	private List<Action1<List<Change>>> callbacks = new ArrayList<>();
	
	public WatchableCollection()
	{
		this( new ArrayList<T>() );
	}
	
	public WatchableCollection( List<T> list )
	{
		this.list = list;
	}
	
	public void addCallback( Action1<List<Change>> callback )
	{
		callbacks.add( callback );
	}
	
	public void addCallbackAndSendAll( Action1<List<Change>> callback )
	{
		callbacks.add( callback );
		callback.exec( Change.ForItems( ChangeType.ADD, list ) );
	}
	
	public void removeCallback( Action1<List<Change>> callback )
	{
		callbacks.remove( callback );
	}
	
	private void scheduleChange( Change change )
	{
		if( ! scheduled )
		{
			Scheduler.get().scheduleDeferred( command );
			scheduled = true;
		}
		
		scheduledChanges.add( change );
	}
	
	private void scheduleChanges( Collection<Change> changes )
	{
		if( ! scheduled )
		{
			Scheduler.get().scheduleDeferred( command );
			scheduled = true;
		}
		
		scheduledChanges.addAll( changes );
	}
	
	private ScheduledCommand command = new ScheduledCommand() {
		@Override
		public void execute()
		{
			scheduled = false;
			
			for( Action1<List<Change>> callback : callbacks )
				callback.exec( scheduledChanges );
			scheduledChanges.clear();
		}
	};
	
	public void add(int arg0, T arg1)
	{
		list.add(arg0, arg1);
		
		scheduleChange( new Change( ChangeType.ADD, arg1 ) );
	}

	public boolean add(T arg0)
	{
		boolean res = list.add(arg0);
		
		scheduleChange( new Change( ChangeType.ADD, arg0 ) );
		
		return res;
	}

	public boolean addAll(Collection<? extends T> arg0) {
		boolean res = list.addAll(arg0);
		scheduleChanges( Change.ForItems( ChangeType.ADD, arg0 ) );
		return res;
	}

	public boolean addAll(int arg0, Collection<? extends T> arg1) {
		boolean res = list.addAll(arg0, arg1);
		scheduleChanges( Change.ForItems( ChangeType.ADD, arg1 ) );
		return res;
	}

	public void clear() {
		Collection<Change> changes = Change.ForItems( ChangeType.REMOVE, list );
		list.clear();
		scheduleChanges( changes );
	}

	public boolean contains(Object arg0) {
		return list.contains(arg0);
	}

	public boolean containsAll(Collection<?> arg0) {
		return list.containsAll(arg0);
	}

	public boolean equals(Object arg0) {
		return list.equals(arg0);
	}

	public T get(int arg0) {
		return list.get(arg0);
	}

	public int hashCode() {
		return list.hashCode();
	}

	public int indexOf(Object arg0) {
		return list.indexOf(arg0);
	}

	public boolean isEmpty() {
		return list.isEmpty();
	}

	public Iterator<T> iterator() {
		return list.iterator();
	}

	public int lastIndexOf(Object arg0) {
		return list.lastIndexOf(arg0);
	}

	public ListIterator<T> listIterator() {
		return list.listIterator();
	}

	public ListIterator<T> listIterator(int arg0) {
		return list.listIterator(arg0);
	}

	public T remove(int arg0) {
		T res = list.remove(arg0);
		scheduleChange( new Change( ChangeType.REMOVE, arg0 ) );
		return res;
	}

	public boolean remove(Object arg0) {
		boolean res = list.remove(arg0);
		scheduleChange( new Change( ChangeType.REMOVE, arg0 ) );
		return res;
	}

	public boolean removeAll(Collection<?> arg0) {
		boolean res = list.removeAll(arg0);
		scheduleChanges( Change.ForItems( ChangeType.REMOVE, arg0 ) );
		return res;
	}

	public boolean retainAll(Collection<?> c) {
		throw new RuntimeException();
	}

	public T set(int index, T element) {
		// TODO maybe have a better and clearer behavior for that
		if( ! list.contains(element) )
			scheduleChange( new Change( ChangeType.ADD, element ) );
		return list.set(index, element);
	}

	public int size() {
		return list.size();
	}

	public List<T> subList(int fromIndex, int toIndex) {
		return list.subList(fromIndex, toIndex);
	}

	public Object[] toArray() {
		return list.toArray();
	}

	@SuppressWarnings( "hiding" )
	public <T> T[] toArray(T[] a) {
		return list.toArray(a);
	}
}
