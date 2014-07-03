package fr.lteconsulting.hexa.client.appcache;

import com.google.gwt.core.client.JavaScriptObject;

public final class AppCache extends JavaScriptObject
{
	public interface Callback
	{
		void handleAppCacheEvent( AppCacheEvent event );
	}

	// status
	private static final int UNCACHED = 0;
	private static final int IDLE = 1;
	private static final int CHECKING = 2;
	private static final int DOWNLOADING = 3;
	private static final int UPDATEREADY = 4;
	private static final int OBSOLETE = 5;
	private static final int UNKNOWN = -1;

	protected AppCache()
	{
	}

	public static native final AppCache getIfSupported()
	/*-{
		return $wnd.applicationCache || null;
	}-*/;

	public final AppCacheStatus getStatus()
	{
		int s = getStatusImpl();
		switch( s )
		{
			case UNCACHED:
				return AppCacheStatus.UNCACHED;
			case IDLE:
				return AppCacheStatus.IDLE;
			case CHECKING:
				return AppCacheStatus.CHECKING;
			case DOWNLOADING:
				return AppCacheStatus.DOWNLOADING;
			case UPDATEREADY:
				return AppCacheStatus.UPDATEREADY;
			case OBSOLETE:
				return AppCacheStatus.OBSOLETE;
			default:
				return AppCacheStatus.UNKNOWN;
		}
	}

	private final native int getStatusImpl()
	/*-{
		return this.status;
	}-*/;

	// to launch an app cache update
	public final native void update()
	/*-{
		this.update();
	}-*/;

	// To be used when UPDATEREADY status is reached,
	// new version of the cache will be used on next reload
	public final native void swap()
	/*-{
		this.swapCache();
	}-*/;

	public final native void registerEvents( Callback callback )
	/*-{
		handleCached = function(s) {
			callback.@fr.lteconsulting.hexa.client.appcache.AppCache.Callback::handleAppCacheEvent(Lfr/lteconsulting/hexa/client/appcache/AppCacheEvent;)(@fr.lteconsulting.hexa.client.appcache.AppCacheEvent::CACHED);
		};

		this.addEventListener('cached', handleCached, false);

		handleChecking = function(s) {
			callback.@fr.lteconsulting.hexa.client.appcache.AppCache.Callback::handleAppCacheEvent(Lfr/lteconsulting/hexa/client/appcache/AppCacheEvent;)(@fr.lteconsulting.hexa.client.appcache.AppCacheEvent::CHECKING);
		};

		this.addEventListener('checking', handleChecking, false);

		handleDownloading = function(s) {
			callback.@fr.lteconsulting.hexa.client.appcache.AppCache.Callback::handleAppCacheEvent(Lfr/lteconsulting/hexa/client/appcache/AppCacheEvent;)(@fr.lteconsulting.hexa.client.appcache.AppCacheEvent::DOWNLOADING);
		};

		this.addEventListener('downloading', handleDownloading, false);

		handleError = function(s) {
			callback.@fr.lteconsulting.hexa.client.appcache.AppCache.Callback::handleAppCacheEvent(Lfr/lteconsulting/hexa/client/appcache/AppCacheEvent;)(@fr.lteconsulting.hexa.client.appcache.AppCacheEvent::ERROR);
		};

		this.addEventListener('error', handleError, false);

		handleNoUpdate = function(s) {
			callback.@fr.lteconsulting.hexa.client.appcache.AppCache.Callback::handleAppCacheEvent(Lfr/lteconsulting/hexa/client/appcache/AppCacheEvent;)(@fr.lteconsulting.hexa.client.appcache.AppCacheEvent::NOUPDATE);
		};

		this.addEventListener('noupdate', handleNoUpdate, false);

		handleObsolete = function(s) {
			callback.@fr.lteconsulting.hexa.client.appcache.AppCache.Callback::handleAppCacheEvent(Lfr/lteconsulting/hexa/client/appcache/AppCacheEvent;)(@fr.lteconsulting.hexa.client.appcache.AppCacheEvent::OBSOLETE);
		};

		this.addEventListener('obsolete', handleObsolete, false);

		handleProgress = function(s) {
			callback.@fr.lteconsulting.hexa.client.appcache.AppCache.Callback::handleAppCacheEvent(Lfr/lteconsulting/hexa/client/appcache/AppCacheEvent;)(@fr.lteconsulting.hexa.client.appcache.AppCacheEvent::PROGRESS);
		};

		this.addEventListener('progress', handleProgress, false);

		handleUpdateReady = function(s) {
			callback.@fr.lteconsulting.hexa.client.appcache.AppCache.Callback::handleAppCacheEvent(Lfr/lteconsulting/hexa/client/appcache/AppCacheEvent;)(@fr.lteconsulting.hexa.client.appcache.AppCacheEvent::UPDATEREADY);
		};

		this.addEventListener('updateready', handleUpdateReady, false);
	}-*/;
}
