//Copyright 2012 Giovanni Pini
//Licensed under the Apache License, Version 2.0 (the "License");
//you may not use this file except in compliance with the License.
//You may obtain a copy of the License at
//http://www.apache.org/licenses/LICENSE-2.0
//Unless required by applicable law or agreed to in writing, software
//distributed under the License is distributed on an "AS IS" BASIS,
//WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//See the License for the specific language governing permissions and
//limitations under the License.

package it.bova.rtmapi;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A Remember the Milk task. The contained fields are:
 * <ul>
 * <li> <b>id</b> - The string unique ID
 * <li> <b>added</b> - A Date object identifying when the task was added.
 * <li> <b>completed</b> - A Date object identifying when the task was completed, or null if it is not completed yet
 * <li> <b>deleted</b> - A Date object identifying when the task was deleted, or null if it is not completed yet
 * <li> <b>due</b> - A date object identifying the due date/time of the task. It is null if the task has no due date/time
 * <li> <b>estimate</b> - The estimated time for task completion, once started, in String format (e.g. "1 hour")
 * <li> <b>hasDueTime</b> - A boolean value that is true if due date has also a time set
 * <li> <b>postponed</b> - The number of times that the task has been postponed
 * <li> <b>priority</b> - The Priority of the task (enum)
 * <li> <b>taskserieId</b> - The string ID of the taskserie
 * <li> <b>name</b> - The name of the task (common to the whole taskserie)
 * <li> <b>locationId</b> - The string ID of the location of the task (common to the whole taskserie)
 * <li> <b>listId</b> - The string ID of the list where the task is inserted (common to the whole taskserie)
 * <li> <b>created</b> - A Date object identifying when the taskserie, to which the task is belonging, was created
 * <li> <b>modified</b> - A Date object identifying when the taskserie, to which the task is belonging, was last modified. Not necessarily this task is modified. It is null if the taskserie was never modified
 * <li> <b>notes</b> - An array of Note of the task (common to the whole taskserie)
 * <li> <b>participants</b> - An array of Contact partipating to the task (common to the whole taskserie)
 * <li> <b>recurrence</b> - A Recurrence object representing the repetition rule of the taskserie. It is null if the task has no repetition rule
 * <li> <b>source</b> - The source of the task in string format (common to the whole taskserie)
 * <li> <b>tags</b> - An array of tag strings for the task (common to the whole taskserie)
 * <li> <b>url</b> - An url associated to the task (common to the whole taskserie)
 * <ul>
 * @author Giovanni Pini
 *
 */
public class Task implements RtmObject,Serializable {
	
	private String id = "";
	private Date added;
	private Date completed;
	private Date deleted;
	private Date due;
	private String estimate = "";
	private boolean hasDueTime;
	private int postponed;
	private Priority priority = Priority.NONE;
	
	//TaskSerieInfo
	private String taskserieId;
	private String name;
	private String locationId;
	private String listId;
	private Date created;
	private Date modified;
	private Note[] notes = new Note[0];
	private Contact[] participants = new Contact[0];
	private Recurrence recurrence;
	private String source = "";
	private String[] tags = new String[0];
	private String url = "";
	
	public Task(String id, String taskserieName, Date added, Date completed,
			Date deleted, Date due, String estimate, boolean hasDueTime,
			int postponed, Priority priority, String taskserieId, String locationId, String listId,
			Date created, Date modified, Note[] notes, Recurrence recurrence,
			Contact[] participants, String source, String[] tags, String url) {
		this.id = id;
		this.name = taskserieName;
		this.added = added;
		this.completed = completed;
		this.deleted = deleted;
		this.due = due;
		this.estimate = estimate;
		this.hasDueTime = hasDueTime;
		this.postponed = postponed;
		this.priority = priority;
		this.taskserieId = taskserieId;
		this.locationId = locationId;
		this.listId = listId;
		this.created = created;
		this.modified = modified;
		this.notes = notes;
		this.participants = participants;
		this.recurrence = recurrence;
		this.source = source;
		this.tags = tags;
		this.url = url;
	}
	
	public Task(Taskserie taskserie, String id, Date added,
			Date completed, Date deleted, Date due, String estimate,
			boolean hasDueTime, int postponed, Priority priority) {
		this.id = id;
		this.name = taskserie.getName();
		this.added = added;
		this.completed = completed;
		this.deleted = deleted;
		this.due = due;
		this.estimate = estimate;
		this.hasDueTime = hasDueTime;
		this.postponed = postponed;
		this.priority = priority;
		this.taskserieId = taskserie.getId();
		this.locationId = taskserie.getLocationId();
		this.listId = taskserie.getListId();
		this.created = taskserie.getCreated();
		this.modified = taskserie.getModified();
		this.notes = taskserie.getNotes();
		this.participants = taskserie.getParticipants();
		this.recurrence = taskserie.getRecurrence();
		this.source = taskserie.getSource();
		this.tags = taskserie.getTags();
		this.url = taskserie.getUrl();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getAdded() {
		return added;
	}

	public void setAdded(Date added) {
		this.added = added;
	}

	public Date getCompleted() {
		return completed;
	}

	public void setCompleted(Date completed) {
		this.completed = completed;
	}

	public Date getDeleted() {
		return deleted;
	}

	public void setDeleted(Date deleted) {
		this.deleted = deleted;
	}

	public Date getDue() {
		return due;
	}

	public void setDue(Date due) {
		this.due = due;
	}

	public String getEstimate() {
		return estimate;
	}
	
	/**
	 * Gets the detailed estimate duration time
	 * @return an array of Estimate objects or an empty array if no estimate id foreseen
	 */
	public Estimate[] getEstimateDetail() {
		String pattern = "\\d*(\\.\\d*)?\\s*[hmd]";
		String estimateString = this.estimate;
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(estimateString);
		List<Estimate> estimates = new ArrayList<Estimate>();
		while(m.find()) {
			String estimate = m.group();
			String floatString = estimate.substring(0,estimate.length()-1).trim();
			if(floatString.equals("") || floatString.equals(".")) continue;
			float quantity;
			try {
				quantity = Float.parseFloat(floatString);
			} catch(NumberFormatException nfe) {
				quantity = 0f;
			}
			String unitString = estimate.substring(estimate.length()-1,estimate.length()).trim();
			Estimate.DurationUnit unit;
			if(unitString.equals("d"))
				unit = Estimate.DurationUnit.DAYS;
			else if(unitString.equals("h"))
				unit = Estimate.DurationUnit.HOURS;
			else if(unitString.equals("m"))
				unit = Estimate.DurationUnit.MINUTES;
			else
				unit = Estimate.DurationUnit.NONE;
			estimates.add(new Estimate(quantity, unit));
		}
		Estimate[] array = new Estimate[estimates.size()];
		for(int i = 0; i < estimates.size(); i++)
			array[i] = estimates.get(i);
		return array;
	}

	public void setEstimate(String estimate) {
		this.estimate = estimate;
	}

	public boolean getHasDueTime() {
		return hasDueTime;
	}

	public void setHasDueTime(boolean hasDueTime) {
		this.hasDueTime = hasDueTime;
	}

	public int getPostponed() {
		return postponed;
	}

	public void setPostponed(int postponed) {
		this.postponed = postponed;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public String getTaskserieId() {
		return this.taskserieId;
	}

	public void setTaskserieId(String taskserieId) {
		this.taskserieId = taskserieId;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public String getListId() {
		return listId;
	}

	public void setListId(String listId) {
		this.listId = listId;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	public Note[] getNotes() {
		return notes;
	}

	public void setNotes(Note[] notes) {
		this.notes = notes;
	}

	public Contact[] getParticipants() {
		return participants;
	}

	public void setParticipants(Contact[] participants) {
		this.participants = participants;
	}

	public Recurrence getRecurrence() {
		return recurrence;
	}

	public void setRecurrence(Recurrence recurrence) {
		this.recurrence = recurrence;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String[] getTags() {
		return tags;
	}

	public void setTags(String[] tags) {
		this.tags = tags;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Task)  return this.getId().equals(((Task)o).getId());
		else return false;
	}
	
	@Override
	public String toString() {
		return "RtmTask [id=" + id + ", added=" + added + ", completed="
				+ completed + ", deleted=" + deleted + ", due=" + due
				+ ", estimate=" + estimate + ", hasDueTime=" + hasDueTime
				+ ", postponed=" + postponed + ", priority=" + priority
				+ ", taskserieId=" + taskserieId + ", name=" + name
				+ ", locationId=" + locationId + ", listId=" + listId
				+ ", created=" + created + ", modified=" + modified
				+ ", notes=" + Arrays.toString(notes) + ", participants="
				+ Arrays.toString(participants) + ", recurrence=" + recurrence
				+ ", source=" + source + ", tags=" + Arrays.toString(tags)
				+ ", url=" + url + "]";
	}
	
}
