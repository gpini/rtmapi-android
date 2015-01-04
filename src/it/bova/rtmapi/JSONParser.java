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

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class JSONParser {
	
	static JSONObject getObject(JSONObject jsonObj, ResponseParameter param) throws ParsingException {
		try {
			return jsonObj.getJSONObject(param.toString());
		} catch (JSONException e) {
			throw new ParsingException(e.getMessage());
		}
	}
	
	static JSONArray getArray(JSONObject jsonObj, ResponseParameter param1, ResponseParameter param2) throws ParsingException {
		//returns an array even if it is one object or an empty array
		try {
			//more than one object
			JSONObject obj = jsonObj.getJSONObject(param1.toString());
			JSONArray array = obj.getJSONArray(param2.toString()); 
			return array;
		} catch (JSONException e) {
			try {
				//one object
				JSONObject obj = jsonObj.getJSONObject(param1.toString());
				JSONArray array = new JSONArray();
				return array.put(obj.getJSONObject(param2.toString())); 
			}
			catch (JSONException e1) { 
				try {
					//no object
					JSONArray arr = jsonObj.getJSONArray(param1.toString());
					return new JSONArray(); //no object;
				} catch (JSONException e2) {
					throw new ParsingException(e.getMessage());
				} 
			}
		}
	}
	
	static JSONArray getStringArray(JSONObject jsonObj, ResponseParameter param1, ResponseParameter param2) throws ParsingException {
		//returns an array even if it is one object or an empty array
		try {
			//more than one object
			JSONObject obj = jsonObj.getJSONObject(param1.toString());
			JSONArray array = obj.getJSONArray(param2.toString()); 
			return array;
		} catch (JSONException e) {
			try {
				//one object
				JSONObject obj = jsonObj.getJSONObject(param1.toString());
				JSONArray array = new JSONArray();
				return array.put(obj.getString(param2.toString())); 
			}
			catch (JSONException e1) { 
				try {
					//no object
					JSONArray arr = jsonObj.getJSONArray(param1.toString());
					return new JSONArray(); //no object;
				} catch (JSONException e2) {
					throw new ParsingException(e.getMessage());
				} 
			}
		}
	}
	
	static JSONArray getArray(JSONObject jsonObj, ResponseParameter param) throws ParsingException {
		//returns an array even if it is one object or an empty array
		try {
			//more than one object
			JSONArray array = jsonObj.getJSONArray(param.toString()); 
			return array;
		} catch (JSONException e) {
			try {
				//one object
				JSONObject obj = jsonObj.getJSONObject(param.toString());
				JSONArray array = new JSONArray();
				return array.put(obj); 
			}
			catch (JSONException e1) {
					return new JSONArray(); //no object;
			}
		}
	}
	
	static JSONObject get(JSONArray entries, int index) throws ParsingException {
		try {
			return entries.getJSONObject(index);
		} catch (JSONException e) {
			throw new ParsingException(e.getMessage());
		}
	}
	
	static String getString(JSONArray array, int index) throws ParsingException {
		try {
			return array.getString(index);
		} catch (JSONException e) {
			throw new ParsingException(e.getMessage());
		}
	}
	
	
	static void fillTransactionInfo(JSONObject jsonObj, Transaction<?> transaction) throws ParsingException  {
		JSONObject transactionObject = getObject(jsonObj, ResponseParameter.TRANSACTION);
		String id = getString(transactionObject, ResponseParameter.ID);
		boolean undoable = getBoolean(transactionObject, ResponseParameter.UNDOABLE);
		transaction.setId(id);
		transaction.setUndoable(undoable);
	}
	
	static String getString(JSONObject jsonObj, ResponseParameter param) throws ParsingException {
		try {
			return jsonObj.getString(param.toString());
		} catch (JSONException e) {
			throw new ParsingException(e.getMessage());
		}
	}
	
	static int getInt(JSONObject jsonObj, ResponseParameter param) throws ParsingException  {
		try {
			if(jsonObj.getString(param.toString()).equals("")) return 0;
			else return jsonObj.getInt(param.toString());
		} catch (JSONException e) {
			throw new ParsingException(e.getMessage());
		}
	}
		
	static double getDouble(JSONObject jsonObj, ResponseParameter param) throws ParsingException {
		try {
			if(jsonObj.getString(param.toString()).equals("")) return 0.0;
			return jsonObj.getDouble(param.toString());
		} catch (JSONException e) {
			throw new ParsingException(e.getMessage());
		}
	}
	
	static boolean getBoolean(JSONObject jsonObj, ResponseParameter param) throws ParsingException  {
		int bool = JSONParser.getInt(jsonObj, param);
		return (bool==1) ? true : false;
	}
	
	static Permission getPermission(String string) {
		if (string.equals("delete")) return Permission.DELETE;
		else if (string.equals("read")) return Permission.READ;
		else if (string.equals("write")) return Permission.WRITE;
		else return Permission.READ;
	}
	
	static Priority getPriority(String string) {
		if (string.equals("N")) return Priority.NONE;
		else if (string.equals("1")) return Priority.HIGH;
		else if (string.equals("2")) return Priority.MEDIUM;
		else if (string.equals("3")) return Priority.LOW;
		else return Priority.NONE;
	}
	
	static Settings getSettings(JSONObject jsonObject) throws ParsingException {
		String timezone = JSONParser.getString(jsonObject, ResponseParameter.TIMEZONE);
		String language = JSONParser.getString(jsonObject, ResponseParameter.LANGUAGE);
		int dateFormat = JSONParser.getInt(jsonObject, ResponseParameter.DATE_FORMAT);
		int timeFormat = JSONParser.getInt(jsonObject, ResponseParameter.TIME_FORMAT);
		String defaultListId = JSONParser.getString(jsonObject, ResponseParameter.DEFAULT_LIST);
		return new Settings(timezone, language, dateFormat, timeFormat, defaultListId);
	}
	
	static Token getToken(JSONObject jsonObject) throws ParsingException {
		String token = JSONParser.getString(jsonObject, ResponseParameter.TOKEN);
		String string = JSONParser.getString(jsonObject, ResponseParameter.PERMS);
		Permission perm = JSONParser.getPermission(string);
		JSONObject userObject = JSONParser.getObject(jsonObject, ResponseParameter.USER);
		String userId = JSONParser.getString(userObject, ResponseParameter.ID);
		String userName = JSONParser.getString(userObject, ResponseParameter.USERNAME);
		String fullName = JSONParser.getString(userObject, ResponseParameter.FULLNAME);
		return new Token(perm, token, userId, userName, fullName);
	}
	
	static Location getLocation(JSONObject jsonObject) throws ParsingException  {
		String addr = getString(jsonObject, ResponseParameter.ADDRESS);
		String name = getString(jsonObject, ResponseParameter.NAME);
		String id = getString(jsonObject, ResponseParameter.ID);
		int zoom = getInt(jsonObject, ResponseParameter.ZOOM);
		double lat = getDouble(jsonObject, ResponseParameter.LATITUDE);
		double lon = getDouble(jsonObject, ResponseParameter.LONGITUDE);
		boolean viewable = getBoolean(jsonObject, ResponseParameter.VIEWABLE);
		return new Location(addr, id, lat, lon, name, viewable, zoom);
	}
	
	static Timezone getTimezone(JSONObject jsonObject) throws ParsingException  {
		String name = getString(jsonObject, ResponseParameter.NAME);
		String id = getString(jsonObject, ResponseParameter.ID);
		int dst = getInt(jsonObject, ResponseParameter.DST);
		int offset = getInt(jsonObject, ResponseParameter.OFFSET);
		int currentOffset = getInt(jsonObject, ResponseParameter.CURRENT_OFFSET);
		return new Timezone(id, name, dst, currentOffset, offset);
	}
	
	static TaskList getList(JSONObject jsonObject) throws ParsingException  {
		String name = getString(jsonObject, ResponseParameter.NAME);
		String id = getString(jsonObject, ResponseParameter.ID);
		boolean archived = getBoolean(jsonObject, ResponseParameter.ARCHIVED);
		boolean deleted = getBoolean(jsonObject, ResponseParameter.DELETED);
		boolean locked = getBoolean(jsonObject, ResponseParameter.LOCKED);
		boolean smart = getBoolean(jsonObject, ResponseParameter.SMART);
		int position = getInt(jsonObject, ResponseParameter.POSITION);
		int sortOrder = getInt(jsonObject, ResponseParameter.SORT_ORDER);
		return new TaskList(id, name, archived, deleted, locked, position, smart, sortOrder);
	}
	
	static Group getGroup(JSONObject jsonObject) throws ParsingException  {
		String name = getString(jsonObject, ResponseParameter.NAME);
		String id = getString(jsonObject, ResponseParameter.ID);
		JSONArray entries = getArray(jsonObject, ResponseParameter.CONTACTS, ResponseParameter.CONTACT);
		String[] contactIds = new String[entries.length()];
		for (int i = 0; i < entries.length(); i++) {
			 contactIds[i]=JSONParser.getString(JSONParser.get(entries, i),ResponseParameter.ID);
		}				
		return new Group(id, name, contactIds);
	}
	
	static Contact getContact(JSONObject jsonObject) throws ParsingException  {
		String fullname = getString(jsonObject, ResponseParameter.FULLNAME);
		String username = getString(jsonObject, ResponseParameter.USERNAME);
		String id = getString(jsonObject, ResponseParameter.ID);
		return new Contact(id, fullname, username);
	}
	
	private static Taskserie getTaskserie(JSONObject jsonObject, String listId) throws ParsingException  {
		String id = getString(jsonObject, ResponseParameter.ID);
		String name = getString(jsonObject, ResponseParameter.NAME);
		String locationId = getString(jsonObject, ResponseParameter.LOCATION_ID);
		String source = getString(jsonObject, ResponseParameter.SOURCE);
		Date created = DateParser.parseDate(getString(jsonObject, ResponseParameter.CREATED));
		Date modified = DateParser.parseDate(getString(jsonObject, ResponseParameter.MODIFIED));
		JSONArray entries = getStringArray(jsonObject, ResponseParameter.TAGS, ResponseParameter.TAG);
		String[] tags = new String[entries.length()];
		for(int i = 0; i < entries.length(); i++) {
			tags[i] = getString(entries,i);
		}
		entries = getArray(jsonObject, ResponseParameter.NOTES, ResponseParameter.NOTE);
		Note[] notes = new Note[entries.length()];
		for(int i = 0; i < entries.length(); i++) {
			JSONObject obj = get(entries,i);
			notes[i] = getNote(obj);
		}
		entries = getArray(jsonObject, ResponseParameter.PARTICIPANTS, ResponseParameter.CONTACT);
		Contact[] contacts = new Contact[entries.length()];
		for(int i = 0; i < entries.length(); i++) {
			JSONObject obj = get(entries,i);
			contacts[i] = getContact(obj);
		}
		Recurrence rrule = null;
		try {
			JSONObject repetition = getObject(jsonObject, ResponseParameter.RRULE);
			boolean every = getBoolean(repetition, ResponseParameter.EVERY);
			String string = getString(repetition, ResponseParameter.T);
			rrule = new Recurrence(every, string);
		} catch (ParsingException e ) {/* no repetition rule */	}
		String url = getString(jsonObject, ResponseParameter.URL);
		return new Taskserie(id, name, locationId, listId, created, modified, notes, rrule, contacts, source, tags, url);
	}
	
	static List<DeletedTask> getDeletedTasks(JSONObject listEntry, String listId) throws ParsingException {
		List<DeletedTask> deletedTasks = new ArrayList<DeletedTask>();
		JSONArray deletedEntries = getArray(listEntry, ResponseParameter.DELETED);
		for(int i = 0; i < deletedEntries.length(); i++) {
			JSONObject obj = get(deletedEntries,i);
			JSONObject serie = getObject(obj, ResponseParameter.TASKSERIES);
			String serieId = getString(serie, ResponseParameter.ID);
			JSONArray taskEntries = getArray(serie, ResponseParameter.TASK);
			for(int j = 0; j < taskEntries.length(); j++) {
				JSONObject task = get(taskEntries,j);
				Date deleted = DateParser.parseDate(getString(task, ResponseParameter.DELETED));
				String id = getString(task, ResponseParameter.ID);
				deletedTasks.add(new DeletedTask(serieId, listId, id, deleted));
			}		
		}
		return deletedTasks;
	}
	
	private static List<Task> getRegularTasks(JSONObject listEntry, String listId) throws ParsingException  {
		List<Task> taskList = new ArrayList<Task>();
		JSONArray series = getArray(listEntry, ResponseParameter.TASKSERIES);
		for(int i = 0; i < series.length(); i++) {
			JSONObject serie = get(series, i);
			Taskserie taskSerie = getTaskserie(serie, listId);
			JSONArray tasks = getArray(serie, ResponseParameter.TASK);
			for(int j = 0; j < tasks.length(); j++) {
				JSONObject task = get(tasks,j);
				Task rtmTask = getTask(task, taskSerie);
				taskList.add(rtmTask);
			}
		}
		return taskList;
	}
	
	static List<Task> getTasks(JSONObject jsonObject) throws ParsingException  {
		List<Task> tasks = new ArrayList<Task>();
		JSONArray entries = getArray(jsonObject, ResponseParameter.LIST);
		for(int i = 0; i < entries.length(); i++) {
			JSONObject entry = get(entries,i);
			String listId = getString(entry, ResponseParameter.ID);
			try {
				tasks.addAll(getRegularTasks(entry, listId));
			} catch (ParsingException e) { /* empty list -> no tasks!! */ }
		}
		return tasks;
	}
	
	static SynchedTasks getSynchedTasks(JSONObject jsonObject) throws ParsingException  {
		List<Task> tasks = new ArrayList<Task>();
		List<DeletedTask> deletedTasks = new ArrayList<DeletedTask>();
		Date current = null;
		JSONArray listEntries = getArray(jsonObject, ResponseParameter.LIST);
		for(int i = 0; i < listEntries.length(); i++) {
			JSONObject entry = get(listEntries,i);
			String listId = getString(entry, ResponseParameter.ID); //list exists
			try {
				if (current == null) current = DateParser.parseDate(getString(entry, ResponseParameter.CURRENT));
			} catch (ParsingException e) { /* no current time (request with last_sync = "") */ }
			try {
				tasks.addAll(getRegularTasks(entry, listId));
			} catch (ParsingException e) { /* no tasks inside */ }
			try {
				deletedTasks.addAll(getDeletedTasks(entry, listId));
			} catch (ParsingException e) { /* no deleted tasks inside */ }
		}	
		SynchedTasks synchedTasks = new SynchedTasks(tasks, deletedTasks, current);
		return synchedTasks;
		
	}
	
	static Task getTask(JSONObject jsonObject, Taskserie taskserie) throws ParsingException  {
		String id = getString(jsonObject, ResponseParameter.ID);
		Date added = DateParser.parseDate(getString(jsonObject, ResponseParameter.ADDED));
		Date completed = DateParser.parseDate(getString(jsonObject, ResponseParameter.COMPLETED));
		Date deleted = DateParser.parseDate(getString(jsonObject, ResponseParameter.DELETED));
		Date due = DateParser.parseDate(getString(jsonObject, ResponseParameter.DUE));
		boolean hasDueTime = getBoolean(jsonObject, ResponseParameter.HAS_DUE_TIME);
		String estimate = getString(jsonObject, ResponseParameter.ESTIMATE);
		int postponed = getInt(jsonObject, ResponseParameter.POSTPONED);
		Priority priority = getPriority(getString(jsonObject, ResponseParameter.PRIORITY));
		
		return new Task(taskserie, id, added, completed, deleted, due, estimate, hasDueTime, postponed, priority);
	}
	
	static Note getNote(JSONObject jsonObject) throws ParsingException  {
		String id = getString(jsonObject, ResponseParameter.ID);
		String title = getString(jsonObject, ResponseParameter.TITLE);
		String text = getString(jsonObject, ResponseParameter.T);
		Date created = DateParser.parseDate(getString(jsonObject, ResponseParameter.CREATED));
		Date modified = DateParser.parseDate(getString(jsonObject, ResponseParameter.MODIFIED));	
		return new Note(id, title, text, created, modified);
	}
	
	static Note getModifiedNote(JSONObject jsonObject) throws ParsingException  {
		JSONObject entry = getObject(jsonObject, ResponseParameter.NOTE);
		String id = getString(entry, ResponseParameter.ID);
		String title = getString(entry, ResponseParameter.TITLE);
		String text = getString(entry, ResponseParameter.T);
		Date created = DateParser.parseDate(getString(entry, ResponseParameter.CREATED));
		Date modified = DateParser.parseDate(getString(entry, ResponseParameter.MODIFIED));	
		return new Note(id, title, text, created, modified);
	}

}
