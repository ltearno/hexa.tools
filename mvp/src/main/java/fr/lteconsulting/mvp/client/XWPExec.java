package fr.lteconsulting.mvp.client;

public interface XWPExec
{
	void onCancel();
	void onResult(Object result);
	void onError(Throwable throwable);
}
