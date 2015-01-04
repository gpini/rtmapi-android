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


class JSONResponse {

	private JSONObject jsonObject;
	private boolean isServerError = false;

	JSONResponse(String stringResponse) throws ServerException, RtmApiException {
		try {
			this.jsonObject = new JSONObject(stringResponse);
			this.jsonObject = JSONParser.getObject(this.jsonObject, ResponseParameter.RSP);
			this.throwExceptionIfServerError(this.jsonObject);
			//if (!(this.isStatusOk())) throw new ParsingException("Status field is not OK, but it's not an error message!");
		} catch (JSONException e) {
			throw new RtmApiException("Parsing error: " + e.getMessage());
		} catch (ParsingException e) {
			throw new RtmApiException("Parsing error: " + e.getMessage());
		}
	}

	String getString(ResponseParameter param) throws RtmApiException {
		try {
			return JSONParser.getString(this.jsonObject, param);
		} catch (ParsingException e) {
			throw new RtmApiException("Parsing error: " + e.getMessage());
		}
	}	

	boolean getStatus() throws RtmApiException {
		return this.isStatusOk();
	}

	Date getDate() throws RtmApiException {
		JSONObject entry;
		try {
			entry = JSONParser.getObject(this.jsonObject, ResponseParameter.TIME);
			return DateParser.parseDate(JSONParser.getString(entry, ResponseParameter.T));
		} catch (ParsingException e) {
			throw new RtmApiException("Parsing error: " + e.getMessage());
		}
	}

	Date getDate(ResponseParameter param) throws RtmApiException {
		try {
			JSONObject entry = JSONParser.getObject(this.jsonObject, param);
			return DateParser.parseDate(JSONParser.getString(entry, ResponseParameter.T));
		} catch (ParsingException e) {
			throw new RtmApiException("Parsing error: " + e.getMessage());
		}
	}

	Token getToken() throws RtmApiException {
		try {
			JSONObject entry = JSONParser.getObject(this.jsonObject, ResponseParameter.AUTH);
			return JSONParser.getToken(entry);
		} catch (ParsingException e) {
			throw new RtmApiException("Parsing error: " + e.getMessage());
		}
	}

	String getLogin() throws RtmApiException {
		try {
			//returns id and username separated by a comma
			JSONObject entry = JSONParser.getObject(this.jsonObject, ResponseParameter.USER);
			int id = JSONParser.getInt(entry, ResponseParameter.ID);
			String username = JSONParser.getString(entry, ResponseParameter.USERNAME);
			return id + ", " + username;
		} catch (ParsingException e) {
			throw new RtmApiException("Parsing error: " + e.getMessage());
		}
	}

	List<Location> getLocations() throws RtmApiException {
		try {
			JSONArray entries = JSONParser.getArray(this.jsonObject, ResponseParameter.LOCATIONS, ResponseParameter.LOCATION);
			List<Location> locations = new ArrayList<Location>();
			for (int i = 0; i < entries.length(); i++) {
				Location tmpLoc = JSONParser.getLocation(JSONParser.get(entries, i));
				locations.add(tmpLoc);
			}
			//return cloned? 
			return locations;
		} catch (ParsingException e) {
			throw new RtmApiException("Parsing error: " + e.getMessage());
		}
	}

	List<Timezone> getTimezones() throws RtmApiException {
		try {
			JSONArray entries = JSONParser.getArray(this.jsonObject, ResponseParameter.TIMEZONES, ResponseParameter.TIMEZONE);
			List<Timezone> timezones = new ArrayList<Timezone>();
			for (int i = 0; i < entries.length(); i++) {
				Timezone tmpTimezone = JSONParser.getTimezone(JSONParser.get(entries, i));
				timezones.add(tmpTimezone);
			}
			//return cloned?
			return timezones;
		} catch (ParsingException e) {
			throw new RtmApiException("Parsing error: " + e.getMessage());
		}
	}

	List<TaskList> getLists() throws RtmApiException {
		try {
			JSONArray entries = JSONParser.getArray(this.jsonObject, ResponseParameter.LISTS, ResponseParameter.LIST);
			List<TaskList> lists = new ArrayList<TaskList>();
			for (int i = 0; i < entries.length(); i++) {
				TaskList tmpRtmList = JSONParser.getList(JSONParser.get(entries, i));
				lists.add(tmpRtmList);
			}
			//return cloned?
			return lists;
		} catch (ParsingException e) {
			throw new RtmApiException("Parsing error: " + e.getMessage());
		}
	}

	TaskList getList() throws RtmApiException {
		try {
			JSONObject entry = JSONParser.getObject(this.jsonObject, ResponseParameter.LIST);
			return JSONParser.getList(entry);
		} catch (ParsingException e) {
			throw new RtmApiException("Parsing error: " + e.getMessage());
		}
	}

	List<Contact> getContacts() throws RtmApiException {
		try {
			List<Contact> contacts = new ArrayList<Contact>();
			JSONArray entries = JSONParser.getArray(this.jsonObject, ResponseParameter.CONTACTS, ResponseParameter.CONTACT);
			for (int i = 0; i < entries.length(); i++) {
				Contact tmpRtmContact = JSONParser.getContact(JSONParser.get(entries, i));
				contacts.add(tmpRtmContact);
			}
			//return cloned?
			return contacts;
		} catch (ParsingException e) {
			throw new RtmApiException("Parsing error: " + e.getMessage());
		}
	}

	Contact getContact() throws RtmApiException {
		try {
			JSONObject entry = JSONParser.getObject(this.jsonObject, ResponseParameter.CONTACT);
			return JSONParser.getContact(entry);
		} catch (ParsingException e) {
			throw new RtmApiException("Parsing error: " + e.getMessage());
		}
	}

	List<Group> getGroups() throws RtmApiException {
		try {
			List<Group> groups = new ArrayList<Group>();
			JSONArray entries = JSONParser.getArray(this.jsonObject, ResponseParameter.GROUPS, ResponseParameter.GROUP);
			for (int i = 0; i < entries.length(); i++) {
				Group tmpRtmGroup = JSONParser.getGroup(JSONParser.get(entries, i));
				groups.add(tmpRtmGroup);
			}
			//return cloned?
			return groups;
		} catch (ParsingException e) {
			throw new RtmApiException("Parsing error: " + e.getMessage());
		}
	}

	Group getGroup() throws RtmApiException {
		try {
			JSONObject entry = JSONParser.getObject(this.jsonObject, ResponseParameter.GROUP);
			return JSONParser.getGroup(entry);
		} catch (ParsingException e) {
			throw new RtmApiException("Parsing error: " + e.getMessage());
		}
	}

	List<Task> getTasks() throws RtmApiException {
		try {
			JSONObject entry = JSONParser.getObject(this.jsonObject, ResponseParameter.TASKS);
			return JSONParser.getTasks(entry);
		} catch (ParsingException e) {
			throw new RtmApiException("Parsing error: " + e.getMessage());
		}
	}

	Task getAddedTask() throws RtmApiException {
		try {
			return JSONParser.getTasks(this.jsonObject).get(0);
		} catch (ParsingException e) {
			throw new RtmApiException("Parsing error: " + e.getMessage());
		}
	}

	List<Task> getModifiedTask() throws RtmApiException {
		try {
			return JSONParser.getTasks(this.jsonObject);
		} catch (ParsingException e) {
			throw new RtmApiException("Parsing error: " + e.getMessage());
		}
	}

	SynchedTasks getSynchedTasks() throws RtmApiException {
		try {
			JSONObject entry = JSONParser.getObject(this.jsonObject, ResponseParameter.TASKS);
			return JSONParser.getSynchedTasks(entry);
		} catch (ParsingException e) {
			throw new RtmApiException("Parsing error: " + e.getMessage());
		}
	}

	Settings getSettings() throws RtmApiException {
		try {
			JSONObject entry = JSONParser.getObject(this.jsonObject, ResponseParameter.SETTINGS);
			return JSONParser.getSettings(entry);
		} catch (ParsingException e) {
			throw new RtmApiException("Parsing error: " + e.getMessage());
		}
	}
	
	Note getNote() throws RtmApiException {
		try {
			return JSONParser.getNote(this.jsonObject);
		} catch (ParsingException e) {
			throw new RtmApiException("Parsing error: " + e.getMessage());
		}
	}
	
	Note getModifiedNote() throws RtmApiException {
		try {
			return JSONParser.getModifiedNote(this.jsonObject);
		} catch (ParsingException e) {
			throw new RtmApiException("Parsing error: " + e.getMessage());
		}
	}

	String[] getMethods() throws RtmApiException {
		try {
			JSONArray entries = JSONParser.getArray(this.jsonObject, ResponseParameter.METHODS, ResponseParameter.METHOD);
			String[] methods = new String[entries.length()];
			for (int i = 0; i < entries.length(); i++) {
				methods[i] = JSONParser.getString(entries,i);
			}
			return methods;
		} catch (ParsingException e) {
			throw new RtmApiException("Parsing error: " + e.getMessage());
		}
	}

	MethodInfo getMethodInfo() throws RtmApiException {
		try {
			MethodInfo methodInfo = new MethodInfo();
			JSONObject obj = JSONParser.getObject(this.jsonObject, ResponseParameter.METHOD);
			methodInfo.setName(JSONParser.getString(obj, ResponseParameter.NAME));
			methodInfo.setNeedsLogin(JSONParser.getBoolean(obj, ResponseParameter.NEEDSLOGIN));
			methodInfo.setNeedsSigning(JSONParser.getBoolean(obj, ResponseParameter.NEEDSSIGNING));
			methodInfo.setPermission(JSONParser.getPermission(JSONParser.getString(obj, ResponseParameter.REQUIREDPERMS)));
			methodInfo.setDescription(JSONParser.getString(obj, ResponseParameter.DESCRIPTION));
			JSONArray arguments = JSONParser.getArray(obj, ResponseParameter.ARGUMENTS, ResponseParameter.ARGUMENT);
			String[] argsName = new String[arguments.length()];
			boolean[] argsOpt = new boolean[arguments.length()];
			String[] argsDesc = new String[arguments.length()];
			for (int i = 0; i < arguments.length(); i++) {
				JSONObject argument = JSONParser.get(arguments, i);
				argsName[i] = JSONParser.getString(argument, ResponseParameter.NAME);
				argsOpt[i] = JSONParser.getBoolean(argument, ResponseParameter.OPTIONAL);
				argsDesc[i] = JSONParser.getString(argument, ResponseParameter.T);
			}
			methodInfo.setArgumentName(argsName);
			methodInfo.setArgumentDescription(argsDesc);
			methodInfo.setArgumentIsOptional(argsOpt);
			JSONArray errors = JSONParser.getArray(obj, ResponseParameter.ERRORS, ResponseParameter.ERROR);
			String[] errMsg = new String[errors.length()];
			int[] errCode = new int[errors.length()];
			String[] errDesc = new String[errors.length()];
			for (int i = 0; i < errors.length(); i++) {
				JSONObject error = JSONParser.get(errors, i);
				errCode[i] = JSONParser.getInt(error, ResponseParameter.CODE);
				errMsg[i] = JSONParser.getString(error, ResponseParameter.MESSAGE);
				errDesc[i] = JSONParser.getString(error, ResponseParameter.T);
			}
			methodInfo.setErrorCode(errCode);
			methodInfo.setErrorMessage(errMsg);
			methodInfo.setErrorDescription(errDesc);
			methodInfo.setExampleResponse(JSONParser.getString(obj, ResponseParameter.RESPONSE));
			return methodInfo;
		} catch (ParsingException e) {
			throw new RtmApiException("Parsing error: " + e.getMessage());
		}
	}
	
	void fillTransaction(Transaction<?> transaction) throws RtmApiException {
		try {
			JSONParser.fillTransactionInfo(this.jsonObject, transaction);
		}  catch (ParsingException e) {
			throw new RtmApiException("Parsing error: " + e.getMessage());
		}
	}


	private boolean isServerError() {
		return isServerError;
	}

	private boolean isStatusOk() throws RtmApiException {
		try {
			String status = JSONParser.getString(this.jsonObject, ResponseParameter.STATUS);
			if (status.equals("ok")) return true;
			else return false;
		} catch (ParsingException e) {
			throw new RtmApiException("Parsing error: " + e.getMessage());
		}
	}

	private void throwExceptionIfServerError(JSONObject jsonObj) throws ServerException {
		//throws exception if response is server error, otherwise does nothing
		try {
			this.jsonObject = JSONParser.getObject(this.jsonObject, ResponseParameter.ERR);
			int code = JSONParser.getInt(this.jsonObject, ResponseParameter.CODE);
			String msg = JSONParser.getString(this.jsonObject, ResponseParameter.MSG);
			this.isServerError = true;
			throw new ServerException(code, msg);
		} catch (ParsingException e) {
			//do nothing
		}	

	}
	
	public String toString() {
		return this.jsonObject.toString();
	}
	

	
	

}
