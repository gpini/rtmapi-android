package it.bova.rtmapi;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * A container for synchronization results (See {@link RtmApi} for methods including last_sync parameter).
 * The content is:
 * <ul>
 * <li> <b>tasks</b> - The list of new or modified Task objects since last synchronization
 * <li> <b>deletedTasks</b> - The list of DeletedTasks objects since last syncronization
 * <li> <b>currentTime</b> - The current server time, when this synchronization is performed
 * </ul>
 * @author Giovanni Pini
 *
 */
public class SynchedTasks implements Serializable{
	
	private List<Task> tasks;
	private List<DeletedTask> deletedTasks;
	private Date currentTime;
	
	public SynchedTasks(List<Task> tasks, List<DeletedTask> deletedTasks,
			Date currentTime) {
		this.tasks = tasks;
		this.deletedTasks = deletedTasks;
		this.currentTime = currentTime;
	}
	
	public List<Task> getTasks() {
		return tasks;
	}
	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
	public List<DeletedTask> getDeletedTasks() {
		return deletedTasks;
	}
	public void setDeletedTasks(List<DeletedTask> deletedTasks) {
		this.deletedTasks = deletedTasks;
	}
	public Date getCurrentTime() {
		return currentTime;
	}
	public void setCurrentTime(Date currentTime) {
		this.currentTime = currentTime;
	}

	@Override
	public String toString() {
		return "SynchedTask [tasks=" + tasks + ", deletedTasks=" + deletedTasks
				+ ", currentTime=" + currentTime + "]";
	}

}
