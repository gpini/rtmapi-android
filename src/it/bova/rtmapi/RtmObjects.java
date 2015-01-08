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

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * An utility class for searching and extracting inside RtmObject collections
 * @author Giovanni Pini
 *
 */
public class RtmObjects {
	
	/**
	 * Searches the RtmObject (Task, TaskList, etc.) with a specified name  within a list
	 * @param <V> The RtmObject type
	 * @param list the collection of the specified RtmObject
	 * @param name the name string to be searched inside the collection
	 * @return the first RtmObject with the desired name, if founded; null otherwise
	 */
	public static <V extends RtmObject> V searchByName(List<V> list, String name) {
		for(V v : list) {
			if(v.getName().equals(name)) return v; 
		}
		return null;
	}
	
	/**
	 * Searches the RtmObject (Task, TaskList, etc.) with a specified ID within a list
	 * @param <V> The RtmObject type
	 * @param list the collection of the specified RtmObject
	 * @param id the id string to be searched inside the collection
	 * @return the first RtmObject with the desired id, if founded; null otherwise
	 */
	public static <V extends RtmObject> V searchById(List<V> list, String id) {
		for(V v : list) {
			if(v.getId().equals(id)) return v; 
		}
		return null;
	}
	
	/**
	 * Searches all the tasks beloning to the same taskserie in a list
	 * @param tasks list of task
	 * @param taskserieId the id string of the taskserie to be searched inside the collection
	 * @return a list of Task beloning to the same specified taskserie. If no one is found an empty list
	 */
	public static List<Task> searchByTaskserie(List<Task> tasks, String taskserieId) {
		List<Task> taskInSameTaskserie = new ArrayList<Task>();
		for(Task task : tasks) {
			if(task.getTaskserieId() == taskserieId) taskInSameTaskserie.add(task); 
		}
		return taskInSameTaskserie;
	}
	
	/**
	 * Gets the most recent date founded in a list of task (considering "completed", "created", "added", "modified" or "deleted" fields)
	 * @param tasks list of task
	 * @return the most recent date founded in the list, or 1/1/1970 if all dates founded are null
	 */
	public static Date getLastDate(List<Task> tasks) {
		Date lastDate = new Date();
		for(Task task : tasks) {
			if (task.getCompleted() != null)
				if(task.getCompleted().after(lastDate)) lastDate = task.getCompleted(); 
			if(task.getCreated() != null)
				if(task.getCreated().after(lastDate)) lastDate = task.getCreated(); 
			if(task.getDeleted() != null)
				if(task.getDeleted().after(lastDate)) lastDate = task.getDeleted(); 
			if(task.getModified() != null)
				if(task.getModified().after(lastDate)) lastDate = task.getModified(); 
			if(task.getAdded() != null)
				if(task.getAdded().after(lastDate)) lastDate = task.getAdded();
		}
		return lastDate;
	}
	
	/**
	 * Gets the smart-list within a list of TaskList objects
	 * @param lists the list of TaskList objects
	 * @return the list of Smart TaskList
	 */
	public static List<TaskList> getSmart(List<TaskList> lists) {
		List<TaskList> smartLists = new ArrayList<TaskList>();
		for(TaskList list : lists) {
			if(list.isSmart()) smartLists.add(list); 
		}
		return smartLists;
	}
	
	/**
	 * Gets the unique tags in a list of tasks
	 * @param tasks the list of Task objects
	 * @return the set of unique tags
	 */
	public static Set<String> getTags(List<Task> tasks) {
		Set<String> tags = new HashSet<String>();
		for(Task task : tasks) {
			for (String tag : task.getTags()) {
				tags.add(tag);
			}
		}
		return tags;
	}
	
	/**
	 * Gets a map with ID as key and object as value
	 * @param rtmObjects the list of objects to be put in the map
	 * @return the ID-Object map
	 */
	public static <V extends RtmObject> Map<String,V> toMap(List<V> rtmObjects) {
		Map<String,V> map = new HashMap<String,V>();
		if(rtmObjects == null) return map;
		for(V rtmObject : rtmObjects) {
			map.put(rtmObject.getId(), rtmObject);
		}
		return map;
	}
	
	/**
	 * Gets back a list from an ID-RtmObject map
	 * @param rtmObjectsMap the map with ID as key and object as value
	 * @return the RtmObject list
	 */
	public static <V extends RtmObject> List<V> toList(Map<String,V> rtmObjectsMap) {
		List<V> list = new ArrayList<V>();
		if(rtmObjectsMap == null) return list;
		list.addAll(rtmObjectsMap.values());
		return list;
	}

}
