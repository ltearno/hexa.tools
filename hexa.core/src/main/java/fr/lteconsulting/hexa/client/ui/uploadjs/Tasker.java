package fr.lteconsulting.hexa.client.ui.uploadjs;

import java.util.ArrayList;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import fr.lteconsulting.hexa.client.tools.Action;

public class Tasker
{
	private interface Task
	{
	}

	public interface SynchroneTask extends Task
	{
		void execute();
	}

	public interface AsynchroneTask extends Task
	{
		void execute( Action taskFinishedCallback );
	}

	private class TaskInfo
	{
		Task task;
		Action taskAsyncCallback;
		Action taskFinishedCallback;

		public TaskInfo( Task task, Action taskFinishedCallback )
		{
			this.task = task;
			this.taskFinishedCallback = taskFinishedCallback;
		}

		public TaskInfo( Action task, Action taskFinishedCallback )
		{
			this.taskAsyncCallback = task;
			this.taskFinishedCallback = taskFinishedCallback;
		}
	}

	private final ArrayList<TaskInfo> tasks = new ArrayList<TaskInfo>();
	private TaskInfo busyOn;

	public Tasker()
	{
	}

	public void enqueueTask( Action task )
	{
		tasks.add( new TaskInfo( task, null ) );

		if( tasks.size() == 1 && busyOn == null ) // first task and not busy
			Scheduler.get().scheduleDeferred( doSomething );
	}

	public void enqueueTask( Task task )
	{
		enqueueTask( task, null );
	}

	public void enqueueTask( Task task, Action taskFinishedCallback )
	{
		tasks.add( new TaskInfo( task, taskFinishedCallback ) );

		if( tasks.size() == 1 && busyOn == null ) // first task and not busy
			Scheduler.get().scheduleDeferred( doSomething );
	}

	private void taskEnded()
	{
		assert busyOn != null;

		if( busyOn.taskFinishedCallback != null )
			busyOn.taskFinishedCallback.exec();

		busyOn = null;

		if( !tasks.isEmpty() )
			Scheduler.get().scheduleDeferred( doSomething );
	}

	private final ScheduledCommand doSomething = new ScheduledCommand()
	{
		@Override
		public void execute()
		{
			assert busyOn == null;

			if( tasks.isEmpty() )
				return;

			busyOn = tasks.remove( 0 );

			if( busyOn.taskAsyncCallback != null )
			{
				busyOn.taskAsyncCallback.exec();

				taskEnded();
			}
			else if( busyOn.task instanceof SynchroneTask )
			{
				SynchroneTask t = (SynchroneTask) busyOn.task;

				t.execute();

				taskEnded();
			}
			else if( busyOn.task instanceof AsynchroneTask )
			{
				((AsynchroneTask) busyOn.task).execute( taskFinishedCallback );
			}
		}
	};

	private final Action taskFinishedCallback = new Action()
	{
		@Override
		public void exec()
		{
			taskEnded();
		}
	};
}
