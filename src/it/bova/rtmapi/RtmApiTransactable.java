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

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * The API with implemented transactions
 * @author Giovanni Pini
 *
 */
public class RtmApiTransactable {
	
	private RtmApi api;
	private final String apiKey;
	private final String sharedSecret;
	private String token;
	private RequestFactory requestFactory;

	/**
	 * Sets the mandatory data to perform any request to the server: API key, shared secret and token
	 * @param key The API application key
	 * @param secret The shared secret of the application
	 * @param token The token string
	 */
	public RtmApiTransactable(String key, String secret, String token) {
		this.api = new RtmApi(key, secret, token);
		this.apiKey = key;
		this.sharedSecret = secret;
		this.token = token;
		this.requestFactory = new RequestFactory(this.apiKey, this.sharedSecret, this.token);
	}
	
	/**
	 * Sets the mandatory data to perform any request to the server: API key, shared secret and token
	 * @param key The API application key
	 * @param secret The shared secret of the application
	 * @param token The RtmToken object, given by {@link RtmApiAuthenticator}
	 */
	public RtmApiTransactable(String key, String secret, Token token) {
		this(key, secret, token.getToken());
	}
	
	/**
	 * A testing method which echos all the request parameters back in the response.
	 * @return the string response of the server
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons

	 */
	public String testEcho() throws ServerException, RtmApiException, IOException {	
		return this.api.testEcho();
	}
	
	/**
	 * A testing method which echos all request parameters and a key-value pair back in the response.
	 * @param key The key to be echoed back
	 * @param value The value to be echoed back
	 * @return the string response of the server
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons

	 */
	public String testEcho(String key, String value) throws ServerException, RtmApiException, IOException {	
		return this.api.testEcho(key, value);
	}
	
	/**
	 * A testing method which checks if the caller is logged in.
	 * @return id and username separated by a comma if user is logged in
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons

	 */
	public String testLogin() throws ServerException, RtmApiException, IOException {	
		return this.api.testLogin();
	}
	
	/**
	 * Retrieves a list of locations.
	 * @return a List of all the RtmLocation object representing the locations available on the server 
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons

	 */
	public List<Location> locationsGetList() throws ServerException, RtmApiException, IOException {
		return this.api.locationsGetList();		
	}
	
	/**
	 * Retrieves a list of user settings.
	 * @return the RtmSetting object representing user settings.
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons

	 */
	public Settings settingsGetList() throws ServerException, RtmApiException, IOException {
		return this.api.settingsGetList();			
	}
	
	/**
	 * Retrieves the complete list of available timezones.
	 * @return List of RtmTimezone known by the server
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons

	 */
	public List<Timezone> timezonesGetList() throws ServerException, RtmApiException, IOException {
		return this.api.timezonesGetList();			
	}
	
	/**
	 * Retrieves a new timeline.
	 * @return the timeline string
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons

	 */
	public String timelinesCreate() throws ServerException, RtmApiException, IOException {
		return this.api.timelinesCreate();			
	}
	
	/**
	 * Retrieve a list of lists (list of TaskList objects)
	 * @return the list of TaskList objects
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons

	 */
	public List<TaskList> listsGetList() throws ServerException, RtmApiException, IOException {
		return this.api.listsGetList();
	}
	
	/**
	 * Creates a new smart-list with a given name
	 * @param timeline the timeline string
	 * @param name the name of the TaskList to be added
	 * @return the added TaskList object
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons

	 */
	public Transaction<TaskList> listsAdd(String timeline, String name) throws ServerException, RtmApiException, IOException {
		AuthenticatedRequest request = this.requestFactory.createAuthenticatedRequest(Method.LISTS_ADD);
		request.put(RequestParameter.TIMELINE, timeline);
		request.put(RequestParameter.NAME, name);
		JSONResponse response = new RestClient(request).execute();
		Transaction<TaskList> transaction = new Transaction<TaskList>(response.getList());
		response.fillTransaction(transaction);
		return transaction;
	}
	
	/**
	 * Creates a new smart-list with a given name and with the criteria specified by filter
	 * @param timeline the timeline string
	 * @param listName the name of the TaskList to be added
	 * @param filter the filter string for smart-list creation
	 * @return the added TaskList object
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons

	 */
	public Transaction<TaskList> listsAdd(String timeline, String listName, String filter) throws ServerException, RtmApiException, IOException {
		//NON PROVATO COL FILTRO
		AuthenticatedRequest request = this.requestFactory.createAuthenticatedRequest(Method.LISTS_ADD);
		request.put(RequestParameter.TIMELINE, timeline);
		request.put(RequestParameter.NAME, listName);
		request.put(RequestParameter.FILTER, filter);
		JSONResponse response = new RestClient(request).execute();
		Transaction<TaskList> transaction = new Transaction<TaskList>(response.getList());
		response.fillTransaction(transaction);
		return transaction;
	}
	
	/**
	 * Deletes a TaskList
	 * @param timeline the timeline string
	 * @param listId the ID of the list to be deleted
	 * @return the deleted TaskList object with transaction info
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons

	 */
	public Transaction<TaskList> listsDelete(String timeline, String listId) throws ServerException, RtmApiException, IOException {
		AuthenticatedRequest request = this.requestFactory.createAuthenticatedRequest(Method.LISTS_DELETE);
		request.put(RequestParameter.TIMELINE, timeline);
		request.put(RequestParameter.LIST_ID, listId);
		JSONResponse response = new RestClient(request).execute();
		Transaction<TaskList> transaction = new Transaction<TaskList>(response.getList());
		response.fillTransaction(transaction);
		return transaction;
	}
	
	/**
	 * Deletes a TaskList
	 * @param timeline the timeline string
	 * @param list the list to be deleted
	 * @return the deleted TaskList object with transaction info
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons

	 */
	public Transaction<TaskList> listsDelete(String timeline, TaskList list) throws ServerException, RtmApiException, IOException {
		return this.listsDelete(timeline, list.getId());
	}
	
	/**
	 * Archives a TaskList
	 * @param timeline the timeline string
	 * @param listId the ID of the list to be archived
	 * @return the archived TaskList object with transaction info
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons

	 */
	public Transaction<TaskList> listsArchive(String timeline, String listId) throws ServerException, RtmApiException, IOException {
		AuthenticatedRequest request = this.requestFactory.createAuthenticatedRequest(Method.LISTS_ARCHIVE);
		request.put(RequestParameter.TIMELINE, timeline);
		request.put(RequestParameter.LIST_ID, listId);
		JSONResponse response = new RestClient(request).execute();
		Transaction<TaskList> transaction = new Transaction<TaskList>(response.getList());
		response.fillTransaction(transaction);
		return transaction;
	}
	
	/**
	 * Archives a TaskList
	 * @param timeline the timeline string
	 * @param list the list to be archived
	 * @return the archived TaskList object with transaction info
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons

	 */
	public Transaction<TaskList> listsArchive(String timeline, TaskList list) throws ServerException, RtmApiException, IOException {
		return this.listsArchive(timeline, list.getId());
	}
	
	/**
	 * Unarchives a TaskList
	 * @param timeline the timeline string
	 * @param listId the ID of the list to be unarchived
	 * @return the unarchived TaskList object with transaction info
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons

	 */
	public Transaction<TaskList> listsUnarchive(String timeline, String listId) throws ServerException, RtmApiException, IOException {
		AuthenticatedRequest request = this.requestFactory.createAuthenticatedRequest(Method.LISTS_UNARCHIVE);
		request.put(RequestParameter.TIMELINE, timeline);
		request.put(RequestParameter.LIST_ID, listId);
		JSONResponse response = new RestClient(request).execute();
		Transaction<TaskList> transaction = new Transaction<TaskList>(response.getList());
		response.fillTransaction(transaction);
		return transaction;	
	}
	
	/**
	 * Unarchives a TaskList
	 * @param timeline the timeline string
	 * @param list the list to be unarchived
	 * @return the unarchived TaskList object with transaction info
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons

	 */
	public Transaction<TaskList> listsUnarchive(String timeline, TaskList list) throws ServerException, RtmApiException, IOException {
		return this.listsUnarchive(timeline, list.getId());
	}
	
	/**
	 * Renames a list
	 * @param timeline the timeline string
	 * @param listId the ID of the list to be archived
	 * @param name the new name for the list
	 * @return the modified TaskList object with new name and transaction info
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons

	 */
	public Transaction<TaskList> listsSetName(String timeline, String listId, String name) throws ServerException, RtmApiException, IOException {
		AuthenticatedRequest request = this.requestFactory.createAuthenticatedRequest(Method.LISTS_SET_NAME);
		request.put(RequestParameter.TIMELINE, timeline);
		request.put(RequestParameter.LIST_ID, listId);
		request.put(RequestParameter.NAME, name);
		JSONResponse response = new RestClient(request).execute();
		Transaction<TaskList> transaction = new Transaction<TaskList>(response.getList());
		response.fillTransaction(transaction);
		return transaction;
	}
	
	/**
	 * Renames a list
	 * @param timeline the timeline string
	 * @param list the list to be archived
	 * @param name the new name for the list
	 * @return the modified TaskList object with new name and transaction info
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons

	 */
	public Transaction<TaskList> listsSetName(String timeline, TaskList list, String name) throws ServerException, RtmApiException, IOException {
		return this.listsSetName(timeline, list.getId(), name);
	}
	
	/**
	 * Sets the default list
	 * @param timeline the timeline string
	 * @param listId the ID of the list to be archived
	 * @return the TaskList object set to default with transaction info
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons

	 */
	public Transaction<Boolean> listsSetDefault(String timeline, String listId) throws ServerException, RtmApiException, IOException {
		AuthenticatedRequest request = this.requestFactory.createAuthenticatedRequest(Method.LISTS_SET_DEFAULT);
		request.put(RequestParameter.TIMELINE, timeline);
		request.put(RequestParameter.LIST_ID, listId);
		JSONResponse response = new RestClient(request).execute();
		Transaction<Boolean> transaction = new Transaction<Boolean>(response.getStatus());
		response.fillTransaction(transaction);
		return transaction;	
	}

	/**
	 * Sets the default list
	 * @param timeline the timeline string
	 * @param list the list to be archived
	 * @return the TaskList object set to default with transaction info
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons

	 */
	public Transaction<Boolean> listsSetDefault(String timeline, TaskList list) throws ServerException, RtmApiException, IOException {
		return this.listsSetDefault(timeline, list.getId());
	}
	
	/**
	 * Retrieves a list of contacts
	 * @return the list of Contact objects
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons

	 */
	public List<Contact> contactsGetList() throws ServerException, RtmApiException, IOException {
		return this.api.contactsGetList();	
	}
	
	/**
	 * Adds a new contact
	 * @param timeline the timeline string
	 * @param contactName the name of the contact to be added. Should be a username or email address of a Remember The Milk user.
	 * @return the added Contact object with transaction info
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons

	 */
	public Transaction<Contact> contactsAdd(String timeline, String contactName) throws ServerException, RtmApiException, IOException {
		AuthenticatedRequest request = this.requestFactory.createAuthenticatedRequest(Method.CONTACTS_ADD);	
		request.put(RequestParameter.TIMELINE, timeline);
		request.put(RequestParameter.CONTACT, contactName);
		JSONResponse response = new RestClient(request).execute();
		Transaction<Contact> transaction = new Transaction<Contact>(response.getContact());
		response.fillTransaction(transaction);
		return transaction;	
	}
	
	/**
	 * Deletes a contact
	 * @param timeline the timeline string
	 * @param contactId the ID of the contact to be deleted
	 * @return true if the contact is deleted, false otherwise (with transaction info)
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons

	 */
	public Transaction<Boolean> contactsDelete(String timeline, String contactId) throws ServerException, RtmApiException, IOException {
		AuthenticatedRequest request = this.requestFactory.createAuthenticatedRequest(Method.CONTACTS_DELETE);	
		request.put(RequestParameter.TIMELINE, timeline);
		request.put(RequestParameter.CONTACT_ID, contactId);
		JSONResponse response = new RestClient(request).execute();
		Transaction<Boolean> transaction = new Transaction<Boolean>(response.getStatus());
		response.fillTransaction(transaction);
		return transaction;		
	}
	
	/**
	 * Deletes a contact
	 * @param timeline the timeline string
	 * @param contact the contact to be deleted
	 * @return true if the contact is deleted, false otherwise (with transaction info)
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons

	 */
	public Transaction<Boolean> contactsDelete(String timeline, Contact contact) throws ServerException, RtmApiException, IOException {
		return this.contactsDelete(timeline, contact.getId());	
	}
	
	/**
	 * Retrieves a list of groups
	 * @return the list of Group objects
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons

	 */
	public List<Group> groupsGetList() throws ServerException, RtmApiException, IOException {
		return this.api.groupsGetList();	
	}
	
	/**
	 * Creates a new group
	 * @param timeline the timeline string
	 * @param groupName the name of the group to be created
	 * @return the added Group object with transaction info
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons

	 */
	public Transaction<Group> groupsAdd(String timeline, String groupName) throws ServerException, RtmApiException, IOException {
		AuthenticatedRequest request = this.requestFactory.createAuthenticatedRequest(Method.GROUPS_ADD);	
		request.put(RequestParameter.TIMELINE, timeline);
		request.put(RequestParameter.GROUP, groupName);
		JSONResponse response = new RestClient(request).execute();
		Transaction<Group> transaction = new Transaction<Group>(response.getGroup());
		response.fillTransaction(transaction);
		return transaction;		
	}
	
	/**
	 * Adds a contact to a group
	 * @param timeline the timeline string
	 * @param groupId the ID of the group in which the contact must be added
	 * @param contactId the ID of the contact to add in the group
	 * @return true if the contact is added, false otherwise (with transaction info)
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons

	 */
	public Transaction<Boolean> groupsAddContact(String timeline, String groupId, String contactId) throws ServerException, RtmApiException, IOException {
		AuthenticatedRequest request = this.requestFactory.createAuthenticatedRequest(Method.GROUPS_ADD_CONTACT);	
		request.put(RequestParameter.TIMELINE, timeline);
		request.put(RequestParameter.GROUP_ID, groupId);
		request.put(RequestParameter.CONTACT_ID, contactId);
		JSONResponse response = new RestClient(request).execute();
		Transaction<Boolean> transaction = new Transaction<Boolean>(response.getStatus());
		response.fillTransaction(transaction);
		return transaction;		
	}
	
	/**
	 * Adds a contact to a group
	 * @param timeline the timeline string
	 * @param group the group in which the contact must be added
	 * @param contact the contact to add in the group
	 * @return true if the contact is added, false otherwise (with transaction info)
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons

	 */
	public Transaction<Boolean> groupsAddContact(String timeline, Group group, Contact contact) throws ServerException, RtmApiException, IOException {
		return this.groupsAddContact(timeline, group.getId(), contact.getId());	
	}
	
	/**
	 * Removes a contact from a group
	 * @param timeline the timeline string
	 * @param groupId the ID of the group in which the contact must be removed
	 * @param contactId the ID of the contact to add in the group
	 * @return true if the contact is removed, false otherwise (with transaction info)
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons
	 */
	public Transaction<Boolean> groupRemoveContact(String timeline, String groupId, String contactId) throws ServerException, RtmApiException, IOException {
		AuthenticatedRequest request = this.requestFactory.createAuthenticatedRequest(Method.GROUPS_REMOVE_CONTACT);	
		request.put(RequestParameter.TIMELINE, timeline);
		request.put(RequestParameter.GROUP_ID, groupId);
		request.put(RequestParameter.CONTACT_ID, contactId);
		JSONResponse response = new RestClient(request).execute();
		Transaction<Boolean> transaction = new Transaction<Boolean>(response.getStatus());
		response.fillTransaction(transaction);
		return transaction;	
	}
	
	/**
	 * Removes a contact from a group
	 * @param timeline the timeline string
	 * @param group the group in which the contact must be removed
	 * @param contact the contact to add in the group
	 * @return true if the contact is removed, false otherwise (with transaction info)
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons

	 */
	public Transaction<Boolean> groupRemoveContact(String timeline, Group group, Contact contact) throws ServerException, RtmApiException, IOException {
		return this.groupRemoveContact(timeline, group.getId(), contact.getId());	
	}
	
	/**
	 * Deletes a group
	 * @param timeline the timeline string
	 * @param groupId the ID of the group to be deleted
	 * @return true if the group is deleted, false otherwise (with transaction info)
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons

	 */
	public Transaction<Boolean> groupDelete(String timeline, String groupId) throws ServerException, RtmApiException, IOException {
		AuthenticatedRequest request = this.requestFactory.createAuthenticatedRequest(Method.GROUPS_DELETE);	
		request.put(RequestParameter.TIMELINE, timeline);
		request.put(RequestParameter.GROUP_ID, groupId);
		JSONResponse response = new RestClient(request).execute();
		Transaction<Boolean> transaction = new Transaction<Boolean>(response.getStatus());
		response.fillTransaction(transaction);
		return transaction;		
	}
	
	/**
	 * Deletes a group
	 * @param timeline the timeline string
	 * @param group the group to be deleted
	 * @return true if the group is deleted, false otherwise (with transaction info)
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons

	 */
	public Transaction<Boolean> groupDelete(String timeline, Group group) throws ServerException, RtmApiException, IOException {
		return this.groupDelete(timeline, group.getId());
	}
	
	/**
	 * Returns the current time in the desired timezone
	 * @param toTimezone the timezone to which convert the time
	 * @return the current server time in the specified timezone
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons

	 */
	public Date timeConvert(Timezone toTimezone) throws ServerException, RtmApiException, IOException {
		return this.api.timeConvert(toTimezone);
	}
	
	/**
	 * Returns the specified time in the desired timezone
	 * @param date the date/time to be converted
	 * @param toTimezone the timezone to which convert the time
	 * @return the specified time converted in the specified timezone
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons

	 */
	public Date timeConvert(Date date, Timezone toTimezone) throws ServerException, RtmApiException, IOException {
		return this.api.timeConvert(date, toTimezone);
	}
	
	/**
	 * Returns the specified time in the desired timezone
	 * @param date the date/time to be converted
	 * @param fromTimezone the timezone from which convert the time
	 * @param toTimezone the timezone to which convert the time
	 * @return the specified time converted between the two specified timezone
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons

	 */
	public Date timeConvert(Date date, Timezone fromTimezone, Timezone toTimezone) throws ServerException, RtmApiException, IOException {
		return this.api.timeConvert(date, fromTimezone, toTimezone);
	}
	
	/**
	 * Returns the time, in UTC, for the parsed input
	 * @param text the text to be parsed in date
	 * @param isEuropeanFormat true if the format is European (e.g. 14/01/2011), false if it is American (01/14/2011)
	 * @return a Date object of the parsed string in UTC
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons

	 */
	public Date timeParse(String text, boolean isEuropeanFormat) throws ServerException, RtmApiException, IOException {
		return this.api.timeParse(text, isEuropeanFormat);
	}
	
	/**
	 * Returns the time, in UTC, for the parsed input int the context of a timezone
	 * @param text the text to be parsed in date
	 * @param timezone the context timezone of the input text
	 * @param isEuropeanFormat true if the format is European (e.g. 14/01/2011), false if it is American (01/14/2011)
	 * @return a Date object of the parsed string in UTC
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons

	 */
	public Date timeParse(String text, Timezone timezone, boolean isEuropeanFormat) throws ServerException, RtmApiException, IOException {
		return this.api.timeParse(text, timezone, isEuropeanFormat);	
	}
	
	/**
	 * Returns a list of available Remember The Milk API methods
	 * @return an array of method names in string format
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons

	 */
	public String[] reflectionGetMethods() throws ServerException, RtmApiException, IOException {
		return this.api.reflectionGetMethods();
	}
	
	/**
	 * Returns information for a given Remember The Milk API method.
	 * @param methodName the name of the method
	 * @return a MethodInfo object containing all the information about the method
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons

	 */
	public MethodInfo reflectionGetMethodInfo(String methodName) throws ServerException, RtmApiException, IOException {
		return this.api.reflectionGetMethodInfo(methodName);
	}
	
	/**
	 * Retrieves the user's whole list of tasks
	 * @return the complete list of task on the server for the user
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons

	 */
	public List<Task> tasksGetList() throws ServerException, RtmApiException, IOException {
		return this.api.tasksGetList();	
	}
	
	/**
	 * Retrieves a list of task matching the desired criteria of a filter (See <a href="https://www.rememberthemilk.com/help/answers/search/advanced.rtm">Filter options</a>)
	 * @param filter the criteria for selecting the tasks (See <a href="https://www.rememberthemilk.com/help/answers/search/advanced.rtm">Filter options</a>)
	 * @return the list of task matching the specified criteria
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons

	 */
	public List<Task> tasksGetByFilter(String filter) throws ServerException, RtmApiException, IOException {
		return this.api.tasksGetByFilter(filter);	
	}
	
	/**
	 * Retrieves a list of task in a specified list
	 * @param listId the ID of the interesting TaskList 
	 * @return the list of task in the specified TaskList
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons

	 */
	public List<Task> tasksGetByListId(String listId) throws ServerException, RtmApiException, IOException {
		return this.api.tasksGetByListId(listId);	
	}
	
	/**
	 * Retrieves a list of task in a specified list
	 * @param list the interesting TaskList 
	 * @return the list of task in the specified TaskList
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons

	 */
	public List<Task> tasksGetByList(TaskList list) throws ServerException, RtmApiException, IOException {
		return this.api.tasksGetByList(list);
	}
	
	/**
	 * Retrieves all the tasks created, modified and deleted after a specified date/time
	 * @param lastSync the time after which changed tasks must be retrieved
	 * @return a SynchedTask object containing all relevant tasks and information
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons

	 */
	public SynchedTasks tasksGetSynchedList(Date lastSync) throws ServerException, RtmApiException, IOException {
		return this.api.tasksGetSynchedList(lastSync);
	}
	
	/**
	 * Retrieves all the tasks created, modified and deleted after a specified date/time and matching the desired criteria of a filter (See <a href="https://www.rememberthemilk.com/help/answers/search/advanced.rtm"> Filter options </a>)
	 * @param filter the criteria for selecting the tasks (See <a href="https://www.rememberthemilk.com/help/answers/search/advanced.rtm"> Filter options </a>)
	 * @param lastSync the time after which changed tasks must be retrieved
	 * @return a SynchedTask object containing all relevant tasks and information
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons

	 */
	public SynchedTasks tasksGetByFilter(String filter, Date lastSync) throws ServerException, RtmApiException, IOException {
		return this.api.tasksGetByFilter(filter, lastSync);
	}
	
	/**
	 * Retrieves all the tasks created, modified and deleted after a specified date/time and in a specified list
	 * @param listId the interesting TaskList 
	 * @param lastSync the time after which changed tasks must be retrieved
	 * @return a SynchedTask object containing all relevant tasks and information
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons

	 */
	public SynchedTasks tasksGetByListId(String listId, Date lastSync) throws ServerException, RtmApiException, IOException {
		return this.api.tasksGetByListId(listId, lastSync);
	}
	
	/**
	 * Retrieves all the tasks created, modified and deleted after a specified date/time and in a specified list
	 * @param list the interesting TaskList 
	 * @param lastSync the time after which changed tasks must be retrieved
	 * @return a SynchedTask object containing all relevant tasks and information
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons

	 */
	public SynchedTasks tasksGetByList(TaskList list, Date lastSync) throws ServerException, RtmApiException, IOException {
		return this.tasksGetByListId(list.getId(), lastSync);
	}
	
	/**
	 * Retrieves all the tasks in a specified list and matching the desired criteria of a filter (See <a href="https://www.rememberthemilk.com/help/answers/search/advanced.rtm"> Filter options </a>)
	 * @param listId the interesting TaskList 
	 * @param filter the criteria for selecting the tasks (See <a href="https://www.rememberthemilk.com/help/answers/search/advanced.rtm"> Filter options </a>)
	 * @return a list of selected tasks
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons

	 */
	public List<Task> tasksGetList(String listId, String filter) throws ServerException, RtmApiException, IOException {
		return this.api.tasksGetList(listId, filter);	
	}
	
	/**
	 * Retrieves all the tasks in a specified list and matching the desired criteria of a filter (See <a href="https://www.rememberthemilk.com/help/answers/search/advanced.rtm"> Filter options </a>)
	 * @param list the interesting TaskList 
	 * @param filter the criteria for selecting the tasks (See <a href="https://www.rememberthemilk.com/help/answers/search/advanced.rtm"> Filter options </a>)
	 * @return a list of selected tasks
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons

	 */
	public List<Task> tasksGetList(TaskList list, String filter) throws ServerException, RtmApiException, IOException {
		return this.tasksGetList(list.getId(), filter);	
	}
	
	/**
	 * Retrieves all the tasks created, modified and deleted after a specified date/time, in a specified list and matching the desired criteria of a filter (See <a href="https://www.rememberthemilk.com/help/answers/search/advanced.rtm"> Filter options </a>)
	 * @param listId the ID of the interesting TaskList 
	 * @param filter the criteria for selecting the tasks (See <a href="https://www.rememberthemilk.com/help/answers/search/advanced.rtm"> Filter options </a>)
	 * @return a list of selected tasks
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons

	 */
	public SynchedTasks tasksGetSynchedList(String listId, String filter, Date lastSync) throws ServerException, RtmApiException, IOException {
		return this.api.tasksGetSynchedList(listId, filter, lastSync);
	}
	
	/**
	 * Retrieves all the tasks created, modified and deleted after a specified date/time, in a specified list and matching the desired criteria of a filter (See <a href="https://www.rememberthemilk.com/help/answers/search/advanced.rtm"> Filter options </a>)
	 * @param list the interesting TaskList 
	 * @param filter the criteria for selecting the tasks (See <a href="https://www.rememberthemilk.com/help/answers/search/advanced.rtm"> Filter options </a>)
	 * @return a list of selected tasks
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons

	 */
	public SynchedTasks tasksGetSynchedList(TaskList list, String filter, Date lastSync) throws ServerException, RtmApiException, IOException {
		return this.tasksGetSynchedList(list.getId(), filter, lastSync);
	}
	
	/**
	 * Adds a new task to 'Inbox' list.
	 * @param timeline the timeline string
	 * @param name the name of the task
	 * @return the Task object with transaction info
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons

	 */
	public Transaction<Task> tasksAdd(String timeline, String name) throws ServerException, RtmApiException, IOException {
		AuthenticatedRequest request = this.requestFactory.createAuthenticatedRequest(Method.TASKS_ADD);
		request.put(RequestParameter.NAME, name);
		request.put(RequestParameter.TIMELINE, timeline);
		JSONResponse response = new RestClient(request).execute();
		Transaction<Task> transaction = new Transaction<Task>(response.getAddedTask());
		response.fillTransaction(transaction);
		return transaction;	
	}
	
	/**
	 * Adds a new task to a specified list.
	 * @param timeline the timeline string
	 * @param name the name of the task
	 * @param listId the ID of the list in which the task must be added
	 * @return the Task object with transaction info
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons

	 */
	public Transaction<Task> tasksAdd(String timeline, String name, String listId) throws ServerException, RtmApiException, IOException {
		AuthenticatedRequest request = this.requestFactory.createAuthenticatedRequest(Method.TASKS_ADD);
		request.put(RequestParameter.NAME, name);
		request.put(RequestParameter.LIST_ID, listId);
		request.put(RequestParameter.TIMELINE, timeline);
		JSONResponse response = new RestClient(request).execute();
		Transaction<Task> transaction = new Transaction<Task>(response.getAddedTask());
		response.fillTransaction(transaction);
		return transaction;	
	}
	
	/**
	 * Adds a new task to a specified list.
	 * @param timeline the timeline string
	 * @param name the name of the task
	 * @param list the list in which the task must be added
	 * @return the Task object with transaction info
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons

	 */
	public Transaction<Task> tasksAdd(String timeline, String name, TaskList list) throws ServerException, RtmApiException, IOException {
		return this.tasksAdd(timeline, name, list.getId());	
	}
	
	/**
	 * Adds a new task. Smart Add will be used to process the task. (See <a href="https://www.rememberthemilk.com/services/smartadd/"> SmartAdd</a>)
	 * @param timeline the timeline string
	 * @param smartName the name of the task in 'SmartAdd' format (See <a href="https://www.rememberthemilk.com/services/smartadd/"> SmartAdd</a>)
	 * @return the Task object with transaction info
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons

	 */
	public Transaction<Task> tasksAddSmartly(String timeline, String smartName) throws ServerException, RtmApiException, IOException {
		AuthenticatedRequest request = this.requestFactory.createAuthenticatedRequest(Method.TASKS_ADD);
		request.put(RequestParameter.NAME, smartName);
		request.put(RequestParameter.TIMELINE, timeline);
		request.put(RequestParameter.PARSE, "1");
		JSONResponse response = new RestClient(request).execute();
		Transaction<Task> transaction = new Transaction<Task>(response.getAddedTask());
		response.fillTransaction(transaction);
		return transaction;	
	}
	
	/**
	 * Adds a new task in a specified list. Smart Add will be used to process the task. (See <a href="https://www.rememberthemilk.com/services/smartadd/"> SmartAdd</a>)
	 * @param timeline the timeline string
	 * @param smartName the name of the task in 'SmartAdd' format (See <a href="https://www.rememberthemilk.com/services/smartadd/"> SmartAdd</a>)
	 * @param listId the ID of the list in which the task must be added
	 * @return the Task object with transaction info
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons

	 */
	public Transaction<Task> tasksAddSmartly(String timeline, String smartName, String listId) throws ServerException, RtmApiException, IOException {
		AuthenticatedRequest request = this.requestFactory.createAuthenticatedRequest(Method.TASKS_ADD);
		request.put(RequestParameter.NAME, smartName);
		request.put(RequestParameter.LIST_ID, listId);
		request.put(RequestParameter.TIMELINE, timeline);
		request.put(RequestParameter.PARSE, "1");
		JSONResponse response = new RestClient(request).execute();
		Transaction<Task> transaction = new Transaction<Task>(response.getAddedTask());
		response.fillTransaction(transaction);
		return transaction;		
	}
	
	/**
	 * Adds a new task in a specified list. Smart Add will be used to process the task. (See <a href="https://www.rememberthemilk.com/services/smartadd/"> SmartAdd</a>)
	 * @param timeline the timeline string
	 * @param name the name of the task in 'SmartAdd' format (See <a href="https://www.rememberthemilk.com/services/smartadd/"> SmartAdd</a>)
	 * @param list the list in which the task must be added
	 * @return the Task object with transaction info
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons

	 */
	public Transaction<Task> tasksAddSmartly(String timeline, String name, TaskList list) throws ServerException, RtmApiException, IOException {
		return this.tasksAddSmartly(timeline, name, list.getId());	
	}
	
	/**
	 * Adds tags to a task
	 * @param timeline the timeline string
	 * @param taskId the ID of the task to be modified
	 * @param taskseriesId the ID of the taskserie in which the specified task is contained
	 * @param listId the ID of the list in which the specified task is contained
	 * @param tags tags to be added
	 * @return a list of Task (taskserie) with the modified task (with transaction info)
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons

	 */
	public Transaction<List<Task>> tasksAddTags(String timeline, String taskId, String taskseriesId, String listId, String... tags) throws ServerException, RtmApiException, IOException {
		AuthenticatedRequest request = this.requestFactory.createAuthenticatedRequest(Method.TASKS_ADD_TAGS);
		request.put(RequestParameter.LIST_ID, listId);
		request.put(RequestParameter.TASK_ID, taskId);
		request.put(RequestParameter.TASKSERIES_ID, taskseriesId);
		request.put(RequestParameter.TIMELINE, timeline);
		String concatenatedTags = "";
		for(int i = 0; i < tags.length; i++) {
			concatenatedTags += tags[i];
			if(i != (tags.length-1)) concatenatedTags += ",";
		}
		request.put(RequestParameter.TAGS, concatenatedTags);
		JSONResponse response = new RestClient(request).execute();
		Transaction<List<Task>> transaction = new Transaction<List<Task>>(response.getModifiedTask());
		response.fillTransaction(transaction);
		return transaction;	
	}
	
	/**
	 * Adds tags to a task
	 * @param timeline the timeline string
	 * @param task the task to be modified
	 * @param tags tags to be added
	 * @return a list of Task (taskserie) with the modified task (with transaction info)
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons

	 */
	public Transaction<List<Task>> tasksAddTags(String timeline, Task task, String... tags) throws ServerException, RtmApiException, IOException {
		return this.tasksAddTags(timeline, task.getId(), task.getTaskserieId(), task.getListId(), tags);
	}
	
	/**
	 * Marks a task as deleted
	 * @param timeline the timeline string
	 * @param taskId the ID of the task to be deleted
	 * @param taskseriesId the ID of the taskserie in which the specified task is contained
	 * @param listId the ID of the list in which the specified task is contained
	 * @return the list of Task (taskserie) of the deleted task with the task marked as deleted (with transaction info)
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons

	 */
	public Transaction<List<Task>> tasksDelete(String timeline, String taskId, String taskseriesId, String listId) throws ServerException, RtmApiException, IOException {
		AuthenticatedRequest request = this.requestFactory.createAuthenticatedRequest(Method.TASKS_DELETE);
		request.put(RequestParameter.LIST_ID, listId);
		request.put(RequestParameter.TASK_ID, taskId);
		request.put(RequestParameter.TASKSERIES_ID, taskseriesId);
		request.put(RequestParameter.TIMELINE, timeline);
		JSONResponse response = new RestClient(request).execute();
		Transaction<List<Task>> transaction = new Transaction<List<Task>>(response.getModifiedTask());
		response.fillTransaction(transaction);
		return transaction;	
	}
	
	/**
	 * Marks a task as deleted
	 * @param timeline the timeline string
	 * @param task the task to be deleted
	 * @return the list of Task (taskserie) of the deleted task with the task marked as deleted (with transaction info)
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons

	 */
	public Transaction<List<Task>> tasksDelete(String timeline, Task task) throws ServerException, RtmApiException, IOException {
		return this.tasksDelete(timeline, task.getId(), task.getTaskserieId(), task.getListId());
	}
	
	/**
	 * Marks a task as completed
	 * @param timeline the timeline string
	 * @param taskId the ID of the task to be deleted
	 * @param taskseriesId the ID of the taskserie in which the specified task is contained
	 * @param listId the ID of the list in which the specified task is contained
	 * @return the list of Task (taskserie) of the completed task with the task marked as completed (with transaction info)
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons

	 */
	public Transaction<List<Task>> tasksComplete(String timeline, String taskId, String taskseriesId, String listId) throws ServerException, RtmApiException, IOException {
		AuthenticatedRequest request = this.requestFactory.createAuthenticatedRequest(Method.TASKS_COMPLETE);
		request.put(RequestParameter.LIST_ID, listId);
		request.put(RequestParameter.TASK_ID, taskId);
		request.put(RequestParameter.TASKSERIES_ID, taskseriesId);
		request.put(RequestParameter.TIMELINE, timeline);
		JSONResponse response = new RestClient(request).execute();
		Transaction<List<Task>> transaction = new Transaction<List<Task>>(response.getModifiedTask());
		response.fillTransaction(transaction);
		return transaction;	
	}
	
	/**
	 * Marks a task as completed
	 * @param timeline the timeline string
	 * @param task the task to be deleted
	 * @return the list of Task (taskserie) of the completed task with the task marked as completed (with transaction info)
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons

	 */
	public Transaction<List<Task>> tasksComplete(String timeline, Task task) throws ServerException, RtmApiException, IOException {
		return this.tasksComplete(timeline, task.getId(), task.getTaskserieId(), task.getListId());	
	}
	
	/**
	 * Moves the priority of a task down
	 * @param timeline the timeline string
	 * @param taskId the ID of the task to be changed
	 * @param taskseriesId the ID of the taskserie in which the specified task is contained
	 * @param listId the ID of the list in which the specified task is contained
	 * @return @return the list of Task (taskserie) of the changed task with the changed task (with transaction info)
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons

	 */
	public Transaction<List<Task>> tasksMovePriorityDown(String timeline, String taskId, String taskseriesId, String listId) throws ServerException, RtmApiException, IOException {
		AuthenticatedRequest request = this.requestFactory.createAuthenticatedRequest(Method.TASKS_MOVE_PRIORITY);
		request.put(RequestParameter.LIST_ID, listId);
		request.put(RequestParameter.TASK_ID, taskId);
		request.put(RequestParameter.TASKSERIES_ID, taskseriesId);
		request.put(RequestParameter.TIMELINE, timeline);
		request.put(RequestParameter.DIRECTION, "down");
		JSONResponse response = new RestClient(request).execute();
		Transaction<List<Task>> transaction = new Transaction<List<Task>>(response.getModifiedTask());
		response.fillTransaction(transaction);
		return transaction;		
	}
	
	/**
	 * Moves the priority of a task down
	 * @param timeline the timeline string
	 * @param task the task to be changed
	 * @return the list of Task (taskserie) of the changed task with the changed task (with transaction info)
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons

	 */
	public Transaction<List<Task>> tasksMovePriorityDown(String timeline, Task task) throws ServerException, RtmApiException, IOException {
		return this.tasksMovePriorityDown(timeline, task.getId(), task.getTaskserieId(), task.getListId());
	}
	
	/**
	 * Moves the priority of a task up
	 * @param timeline the timeline string
	 * @param taskId the ID of the task to be changed
	 * @param taskseriesId the ID of the taskserie in which the specified task is contained
	 * @param listId the ID of the list in which the specified task is contained
	 * @return the list of Task (taskserie) of the changed task with the changed task (with transaction info)
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons

	 */
	public Transaction<List<Task>> tasksMovePriorityUp(String timeline, String taskId, String taskseriesId, String listId) throws ServerException, RtmApiException, IOException {
		AuthenticatedRequest request = this.requestFactory.createAuthenticatedRequest(Method.TASKS_MOVE_PRIORITY);
		request.put(RequestParameter.LIST_ID, listId);
		request.put(RequestParameter.TASK_ID, taskId);
		request.put(RequestParameter.TASKSERIES_ID, taskseriesId);
		request.put(RequestParameter.TIMELINE, timeline);
		request.put(RequestParameter.DIRECTION, "up");
		JSONResponse response = new RestClient(request).execute();
		Transaction<List<Task>> transaction = new Transaction<List<Task>>(response.getModifiedTask());
		response.fillTransaction(transaction);
		return transaction;	
	}
	
	/**
	 * Moves the priority of a task up
	 * @param timeline the timeline string
	 * @param task the task to be changed
	 * @return @return the list of Task (taskserie) of the changed task with the changed task (with transaction info)
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons

	 */
	public Transaction<List<Task>> tasksMovePriorityUp(String timeline, Task task) throws ServerException, RtmApiException, IOException {
		return this.tasksMovePriorityUp(timeline, task.getId(), task.getTaskserieId(), task.getListId());
	}
	
	/**
	 * Move a task between lists
	 * @param timeline the timeline string
	 * @param taskId the ID of the task to be moved
	 * @param taskseriesId the ID of the taskserie in which the specified task is contained
	 * @param fromListId the ID of the list in which the specified task is contained
	 * @param toListId the ID of the list in which the specified task must be moved
	 * @return the list of Task (taskserie) of the moved task (with transaction info)
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons

	 */
	public Transaction<List<Task>> tasksMoveTo(String timeline, String taskId, String taskseriesId, String fromListId, String toListId) throws ServerException, RtmApiException, IOException {
		AuthenticatedRequest request = this.requestFactory.createAuthenticatedRequest(Method.TASKS_MOVE_TO);
		request.put(RequestParameter.FROM_LIST_ID, fromListId);
		request.put(RequestParameter.TASK_ID, taskId);
		request.put(RequestParameter.TASKSERIES_ID, taskseriesId);
		request.put(RequestParameter.TIMELINE, timeline);
		request.put(RequestParameter.TO_LIST_ID, toListId);
		JSONResponse response = new RestClient(request).execute();
		Transaction<List<Task>> transaction = new Transaction<List<Task>>(response.getModifiedTask());
		response.fillTransaction(transaction);
		return transaction;	
	}
	
	/**
	 * Move a task between lists
	 * @param timeline the timeline string
	 * @param task the task to be moved
	 * @param toList the list in which the specified task must be moved
	 * @return the list of Task (taskserie) of the moved task (with transaction info)
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons

	 */
	public Transaction<List<Task>> tasksMoveTo(String timeline, Task task, TaskList toList) throws ServerException, RtmApiException, IOException {
		return this.tasksMoveTo(timeline, task.getId(), task.getTaskserieId(), task.getListId(), toList.getId());
	}

	/**
	 * Postpones a task. If the task has no due date or is overdue, its due date is set to today. Otherwise, the task due date is advanced a day.
	 * @param timeline the timeline string
	 * @param taskId the ID of the task to be postponed
	 * @param taskseriesId the ID of the taskserie in which the specified task is contained
	 * @param listId the ID of the list in which the specified task is contained
	 * @return the list of Task (taskserie) of the postponed task (with transaction info)
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons

	 */
	public Transaction<List<Task>> tasksPostpone(String timeline, String taskId, String taskseriesId, String listId) throws ServerException, RtmApiException, IOException {
		AuthenticatedRequest request = this.requestFactory.createAuthenticatedRequest(Method.TASKS_POSTPONE);
		request.put(RequestParameter.LIST_ID, listId);
		request.put(RequestParameter.TASK_ID, taskId);
		request.put(RequestParameter.TASKSERIES_ID, taskseriesId);
		request.put(RequestParameter.TIMELINE, timeline);
		JSONResponse response = new RestClient(request).execute();
		Transaction<List<Task>> transaction = new Transaction<List<Task>>(response.getModifiedTask());
		response.fillTransaction(transaction);
		return transaction;	
	}
	
	/**
	 * Postpones a task. If the task has no due date or is overdue, its due date is set to today. Otherwise, the task due date is advanced a day.
	 * @param timeline the timeline string
	 * @param task the task to be postponed
	 * @return the list of Task (taskserie) of the postponed task (with transaction info)
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons

	 */
	public Transaction<List<Task>> tasksPostpone(String timeline, Task task) throws ServerException, RtmApiException, IOException {
		return this.tasksPostpone(timeline, task.getId(), task.getTaskserieId(), task.getListId());	
	}
	
	/**
	 * Removes tags from a task
	 * @param timeline the timeline string
	 * @param taskId the ID of the task to be changed
	 * @param taskseriesId the ID of the taskserie in which the specified task is contained
	 * @param listId the ID of the list in which the specified task is contained
	 * @param tags the tags to be removed
	 * @return the list of Task (taskserie) of the changed task (with transaction info)
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons

	 */
	public Transaction<List<Task>> tasksRemoveTags(String timeline, String taskId, String taskseriesId, String listId, String... tags) throws ServerException, RtmApiException, IOException {
		AuthenticatedRequest request = this.requestFactory.createAuthenticatedRequest(Method.TASKS_REMOVE_TAGS);
		request.put(RequestParameter.LIST_ID, listId);
		request.put(RequestParameter.TASK_ID, taskId);
		request.put(RequestParameter.TASKSERIES_ID, taskseriesId);
		request.put(RequestParameter.TIMELINE, timeline);
		String concatenatedTags = "";
		for(int i = 0; i < tags.length; i++) {
			concatenatedTags += tags[i];
			if(i != (tags.length-1)) concatenatedTags += ",";
		}
		request.put(RequestParameter.TAGS, concatenatedTags);
		JSONResponse response = new RestClient(request).execute();
		Transaction<List<Task>> transaction = new Transaction<List<Task>>(response.getModifiedTask());
		response.fillTransaction(transaction);
		return transaction;		
	}
	
	/**
	 * Removes tags from a task
	 * @param timeline the timeline string
	 * @param task the task to be changed
	 * @param tags the tags to be removed
	 * @return the list of Task (taskserie) of the changed task (with transaction info)
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons

	 */
	public Transaction<List<Task>> tasksRemoveTags(String timeline, Task task, String... tags) throws ServerException, RtmApiException, IOException {
		return this.tasksRemoveTags(timeline, task.getId(), task.getTaskserieId(), task.getListId(), tags);
	}
	
	/**
	 * Sets the due date of a task
	 * @param timeline the timeline string
	 * @param taskId the ID of the task to perform the action on
	 * @param taskseriesId the ID of the taskserie in which the specified task is contained
	 * @param listId the ID of the list in which the specified task is contained
	 * @param iso8601Date string of the date to be set
	 * @param hasDueTime specifies whether the due date has a due time
	 * @return the list of Task (taskserie) of the changed task (with transaction info)
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons

	 */
	public Transaction<List<Task>> tasksSetDueDate(String timeline, String taskId, String taskseriesId, String listId, String iso8601Date, boolean hasDueTime) throws ServerException, RtmApiException, IOException {
		AuthenticatedRequest request = this.requestFactory.createAuthenticatedRequest(Method.TASKS_SET_DUE_DATE);
		request.put(RequestParameter.LIST_ID, listId);
		request.put(RequestParameter.TASK_ID, taskId);
		request.put(RequestParameter.TASKSERIES_ID, taskseriesId);
		request.put(RequestParameter.TIMELINE, timeline);
		request.put(RequestParameter.DUE, iso8601Date);
		if (hasDueTime) request.put(RequestParameter.HAS_DUE_TIME, "1");
		JSONResponse response = new RestClient(request).execute();
		Transaction<List<Task>> transaction = new Transaction<List<Task>>(response.getModifiedTask());
		response.fillTransaction(transaction);
		return transaction;	
	}
	
	/**
	 * Sets the due date of a task
	 * @param timeline the timeline string
	 * @param task the task to perform the action on
	 * @param iso8601Date string of the date to be set
	 * @param hasDueTime specifies whether the due date has a due time
	 * @return the list of Task (taskserie) of the changed task (with transaction info)
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons

	 */
	public Transaction<List<Task>> tasksSetDueDate(String timeline, Task task, String iso8601Date, boolean hasDueTime) throws ServerException, RtmApiException, IOException {
		return this.tasksSetDueDate(timeline, task.getId(), task.getTaskserieId(), task.getListId(), iso8601Date, hasDueTime);
	}
	
	/**
	 * Sets the due date of a task
	 * @param timeline the timeline string
	 * @param taskId the ID of the task to perform the action on
	 * @param taskseriesId the ID of the taskserie in which the specified task is contained
	 * @param listId the ID of the list in which the specified task is contained
	 * @param due object of the due date/time to be set
	 * @param hasDueTime specifies whether the due date has a due time
	 * @return the list of Task (taskserie) of the changed task (with transaction info)
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons

	 */
	public Transaction<List<Task>> tasksSetDueDate(String timeline, String taskId, String taskseriesId, String listId, Date due, boolean hasDueTime) throws ServerException, RtmApiException, IOException {
		return this.tasksSetDueDate(timeline, taskId, taskseriesId, listId, DateParser.toISO8601(due), hasDueTime);
	}
	
	
	/**
	 * Sets the due date of a task
	 * @param timeline the timeline string
	 * @param task the task to perform the action on
	 * @param due object of the due date/time to be set
	 * @param hasDueTime specifies whether the due date has a due time
	 * @return the list of Task (taskserie) of the changed task (with transaction info)
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons

	 */
	public Transaction<List<Task>> tasksSetDueDate(String timeline, Task task, Date due, boolean hasDueTime) throws ServerException, RtmApiException, IOException {
		return this.tasksSetDueDate(timeline, task.getId(), task.getTaskserieId(), task.getListId(), due, hasDueTime);
	}
	
	/**
	 * Sets a time estimate for a task
	 * @param timeline the timeline string
	 * @param taskId the ID of the task to perform the action on
	 * @param taskseriesId the ID of the taskserie in which the specified task is contained
	 * @param listId the ID of the list in which the specified task is contained
	 * @param estimate estimate string. Must be provided in a values of 'days', 'hours' or 'minutes'.
	 * @return the list of Task (taskserie) of the changed task (with transaction info)
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons

	 */
	public Transaction<List<Task>> tasksSetEstimate(String timeline, String taskId, String taskseriesId, String listId, String estimate) throws ServerException, RtmApiException, IOException {
		AuthenticatedRequest request = this.requestFactory.createAuthenticatedRequest(Method.TASKS_SET_ESTIMATE);
		request.put(RequestParameter.LIST_ID, listId);
		request.put(RequestParameter.TASK_ID, taskId);
		request.put(RequestParameter.TASKSERIES_ID, taskseriesId);
		request.put(RequestParameter.TIMELINE, timeline);
		request.put(RequestParameter.ESTIMATE, estimate);
		JSONResponse response = new RestClient(request).execute();
		Transaction<List<Task>> transaction = new Transaction<List<Task>>(response.getModifiedTask());
		response.fillTransaction(transaction);
		return transaction;	
	}
	
	/**
	 * Sets a time estimate for a task
	 * @param timeline the timeline string
	 * @param task the task to perform the action on
	 * @param estimate estimate string. Must be provided in a values of 'days', 'hours' or 'minutes'.
	 * @return the list of Task (taskserie) of the changed task (with transaction info)
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons

	 */
	public Transaction<List<Task>> tasksSetEstimate(String timeline, Task task, String estimate) throws ServerException, RtmApiException, IOException {
		return this.tasksSetEstimate(timeline, task.getId(), task.getTaskserieId(), task.getListId(), estimate);
	}
	
	/**
	 * Sets a location for a task
	 * @param timeline the timeline string
	 * @param taskId the ID of the task to perform the action on
	 * @param taskseriesId the ID of the taskserie in which the specified task is contained
	 * @param listId the ID of the list in which the specified task is contained
	 * @param locationId the ID of the location to be set for the specified task
	 * @return the list of Task (taskserie) of the changed task (with transaction info)
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons

	 */
	public Transaction<List<Task>> tasksSetLocation(String timeline, String taskId, String taskseriesId, String listId, String locationId) throws ServerException, RtmApiException, IOException {
		AuthenticatedRequest request = this.requestFactory.createAuthenticatedRequest(Method.TASKS_SET_LOCATION);
		request.put(RequestParameter.LIST_ID, listId);
		request.put(RequestParameter.TASK_ID, taskId);
		request.put(RequestParameter.TASKSERIES_ID, taskseriesId);
		request.put(RequestParameter.TIMELINE, timeline);
		if(locationId != null && !locationId.equals(""))
			request.put(RequestParameter.LOCATION_ID, locationId);
		JSONResponse response = new RestClient(request).execute();
		Transaction<List<Task>> transaction = new Transaction<List<Task>>(response.getModifiedTask());
		response.fillTransaction(transaction);
		return transaction;	
	}
	
	/**
	 * Sets a location for a task
	 * @param timeline the timeline string
	 * @param task the task to perform the action on
	 * @param location the location to be set for the specified task
	 * @return the list of Task (taskserie) of the changed task (with transaction info)
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons

	 */
	public Transaction<List<Task>> tasksSetLocation(String timeline, Task task, Location location) throws ServerException, RtmApiException, IOException {
		if(location == null)
			return this.tasksUnsetLocation(timeline, task.getId(), task.getTaskserieId(), task.getListId());
		else
			return this.tasksSetLocation(timeline, task.getId(), task.getTaskserieId(), task.getListId(), location.getId());
	}
	
	/**
	 * Renames a task
	 * @param timeline the timeline string
	 * @param taskId the ID of the task to perform the action on
	 * @param taskseriesId the ID of the taskserie in which the specified task is contained
	 * @param listId the ID of the list in which the specified task is contained
	 * @param name the new name for the task
	 * @return the list of Task (taskserie) of the renamed task (with transaction info)
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons

	 */
	public Transaction<List<Task>> tasksSetName(String timeline, String taskId, String taskseriesId, String listId, String name) throws ServerException, RtmApiException, IOException {
		AuthenticatedRequest request = this.requestFactory.createAuthenticatedRequest(Method.TASKS_SET_NAME);
		request.put(RequestParameter.LIST_ID, listId);
		request.put(RequestParameter.TASK_ID, taskId);
		request.put(RequestParameter.TASKSERIES_ID, taskseriesId);
		request.put(RequestParameter.TIMELINE, timeline);
		request.put(RequestParameter.NAME, name);
		JSONResponse response = new RestClient(request).execute();
		Transaction<List<Task>> transaction = new Transaction<List<Task>>(response.getModifiedTask());
		response.fillTransaction(transaction);
		return transaction;	
	}
	
	/**
	 * Renames a task
	 * @param timeline the timeline string
	 * @param task the task to perform the action on
	 * @param name the new name for the task
	 * @return the list of Task (taskserie) of the renamed task (with transaction info)
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons

	 */
	public Transaction<List<Task>> tasksSetName(String timeline, Task task, String name) throws ServerException, RtmApiException, IOException {
		return this.tasksSetName(timeline, task.getId(), task.getTaskserieId(), task.getListId(), name);
	}
	
	/**
	 * Sets the priority of a task
	 * @param timeline the timeline string
	 * @param taskId the ID of the task to perform the action on
	 * @param taskseriesId the ID of the taskserie in which the specified task is contained
	 * @param listId the ID of the list in which the specified task is contained
	 * @param priority the Priority to be set
	 * @return the list of Task (taskserie) of the changed task (with transaction info)
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons

	 */
	public Transaction<List<Task>> tasksSetPriority(String timeline, String taskId, String taskseriesId, String listId, Priority priority) throws ServerException, RtmApiException, IOException {
		AuthenticatedRequest request = this.requestFactory.createAuthenticatedRequest(Method.TASKS_SET_PRIORITY);
		request.put(RequestParameter.LIST_ID, listId);
		request.put(RequestParameter.TASK_ID, taskId);
		request.put(RequestParameter.TASKSERIES_ID, taskseriesId);
		request.put(RequestParameter.TIMELINE, timeline);
		request.put(RequestParameter.PRIORITY, priority.getLevel());
		JSONResponse response = new RestClient(request).execute();
		Transaction<List<Task>> transaction = new Transaction<List<Task>>(response.getModifiedTask());
		response.fillTransaction(transaction);
		return transaction;		
	}
	
	/**
	 * Sets the priority of a task
	 * @param timeline the timeline string
	 * @param task the task to perform the action on
	 * @param priority the Priority to be set
	 * @return the list of Task (taskserie) of the changed task (with transaction info)
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons

	 */
	public Transaction<List<Task>> tasksSetPriority(String timeline, Task task, Priority priority) throws ServerException, RtmApiException, IOException {
		return this.tasksSetPriority(timeline, task.getId(), task.getTaskserieId(), task.getListId(), priority);
	}
	
	/**
	 * Sets a recurrence pattern for a task.
	 * Task properties behave a bit differently between tasks set to repeat 'Every...' and those set to repeat 'After...'.
	 * Tasks that repeat 'Every' create a task series: task properties and notes are common across all instances of the task.
	 * Tasks that repeat 'After' work a bit differently: these do not create a task series, but rather create a new, independent task each time a task is generated. Task properties are copied from the previous instance. Each 'after' task has its own properties and notes, and isn't tied to other tasks. Additionally, notes are not copied from previous instances.
	 * @param timeline the timeline string
	 * @param taskId the ID of the task to perform the action on
	 * @param taskseriesId the ID of the taskserie in which the specified task is contained
	 * @param listId the ID of the list in which the specified task is contained
	 * @param recurrence The recurrence pattern for a task (See <a href="https://www.rememberthemilk.com/help/answers/basics/repeatformat.rtm">repeat format<a>)
	 * @return the list of Task (taskserie) of the changed task (with transaction info)
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons

	 */
	public Transaction<List<Task>> tasksSetRecurrence(String timeline, String taskId, String taskseriesId, String listId, String recurrence) throws ServerException, RtmApiException, IOException {
		AuthenticatedRequest request = this.requestFactory.createAuthenticatedRequest(Method.TASKS_SET_RECURRENCE);
		request.put(RequestParameter.LIST_ID, listId);
		request.put(RequestParameter.TASK_ID, taskId);
		request.put(RequestParameter.TASKSERIES_ID, taskseriesId);
		request.put(RequestParameter.TIMELINE, timeline);
		request.put(RequestParameter.RECURRENCE, recurrence);
		JSONResponse response = new RestClient(request).execute();
		Transaction<List<Task>> transaction = new Transaction<List<Task>>(response.getModifiedTask());
		response.fillTransaction(transaction);
		return transaction;		
	}
	
	/**
	 * Sets a recurrence pattern for a task
	 * Task properties behave a bit differently between tasks set to repeat 'Every...' and those set to repeat 'After...'.
	 * Tasks that repeat 'Every' create a task series: task properties and notes are common across all instances of the task.
	 * Tasks that repeat 'After' work a bit differently: these do not create a task series, but rather create a new, independent task each time a task is generated. Task properties are copied from the previous instance. Each 'after' task has its own properties and notes, and isn't tied to other tasks. Additionally, notes are not copied from previous instances.
	 * @param timeline the timeline string
	 * @param task the task to perform the action on
	 * @param recurrence The recurrence pattern for a task (See <a href="https://www.rememberthemilk.com/help/answers/basics/repeatformat.rtm">repeat format<a>)
	 * @return the list of Task (taskserie) of the changed task (with transaction info)
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons

	 */
	public Transaction<List<Task>> tasksSetRecurrence(String timeline, Task task, String recurrence) throws ServerException, RtmApiException, IOException {
		return this.tasksSetRecurrence(timeline, task.getId(), task.getTaskserieId(), task.getListId(), recurrence);
	}
	
	/**
	 * Set tags for a task. Any previous tag will be overwritten
	 * @param timeline the timeline string
	 * @param taskId the ID of the task to perform the action on
	 * @param taskseriesId the ID of the taskserie in which the specified task is contained
	 * @param listId the ID of the list in which the specified task is contained
	 * @param tags The tags to be set
	 * @return the list of Task (taskserie) of the changed task (with transaction info)
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons

	 */
	public Transaction<List<Task>> tasksSetTags(String timeline, String taskId, String taskseriesId, String listId, String... tags) throws ServerException, RtmApiException, IOException {
		AuthenticatedRequest request = this.requestFactory.createAuthenticatedRequest(Method.TASKS_SET_TAGS);
		request.put(RequestParameter.LIST_ID, listId);
		request.put(RequestParameter.TASK_ID, taskId);
		request.put(RequestParameter.TASKSERIES_ID, taskseriesId);
		request.put(RequestParameter.TIMELINE, timeline);
		String concatenatedTags = "";
		for(int i = 0; i < tags.length; i++) {
			concatenatedTags += tags[i];
			if(i != (tags.length-1)) concatenatedTags += ",";
		}
		request.put(RequestParameter.TAGS, concatenatedTags);
		JSONResponse response = new RestClient(request).execute();
		Transaction<List<Task>> transaction = new Transaction<List<Task>>(response.getModifiedTask());
		response.fillTransaction(transaction);
		return transaction;	
	}
	
	/**
	 * Set tags for a task. Any previous tag will be overwritten
	 * @param timeline the timeline string
	 * @param task the task to perform the action on
	 * @param tags The tags to be set
	 * @return the list of Task (taskserie) of the changed task (with transaction info)
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons

	 */
	public Transaction<List<Task>> tasksSetTags(String timeline, Task task, String... tags) throws ServerException, RtmApiException, IOException {
		return this.tasksSetTags(timeline, task.getId(), task.getTaskserieId(), task.getListId(), tags);
	}
	
	/**
	 * Set url for a task
	 * @param timeline the timeline string
	 * @param taskId the ID of the task to perform the action on
	 * @param taskseriesId the ID of the taskserie in which the specified task is contained
	 * @param listId the ID of the list in which the specified task is contained
	 * @param url The url to be set
	 * @return the list of Task (taskserie) of the changed task (with transaction info)
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons

	 */
	public Transaction<List<Task>> tasksSetUrl(String timeline, String taskId, String taskseriesId, String listId, String url) throws ServerException, RtmApiException, IOException {
		AuthenticatedRequest request = this.requestFactory.createAuthenticatedRequest(Method.TASKS_SET_URL);
		request.put(RequestParameter.LIST_ID, listId);
		request.put(RequestParameter.TASK_ID, taskId);
		request.put(RequestParameter.TASKSERIES_ID, taskseriesId);
		request.put(RequestParameter.TIMELINE, timeline);
		request.put(RequestParameter.URL, url);
		JSONResponse response = new RestClient(request).execute();
		Transaction<List<Task>> transaction = new Transaction<List<Task>>(response.getModifiedTask());
		response.fillTransaction(transaction);
		return transaction;		
	}
	
	/**
	 * Set url for a task
	 * @param timeline the timeline string
	 * @param task the task to perform the action on
	 * @param url The url to be set
	 * @return the list of Task (taskserie) of the changed task (with transaction info)
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons

	 */
	public Transaction<List<Task>> tasksSetUrl(String timeline, Task task, String url) throws ServerException, RtmApiException, IOException {
		return this.tasksSetUrl(timeline, task.getId(), task.getTaskserieId(), task.getListId(), url);
	}
	
	/**
	 * Marks a task incomplete
	 * @param timeline the timeline string
	 * @param taskId the ID of the task to perform the action on
	 * @param taskseriesId the ID of the taskserie in which the specified task is contained
	 * @param listId the ID of the list in which the specified task is contained
	 * @return the list of Task (taskserie) of the changed task with the task marked as incomplete (with transaction info)
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons

	 */
	public Transaction<List<Task>> tasksUncomplete(String timeline, String taskId, String taskseriesId, String listId) throws ServerException, RtmApiException, IOException {
		AuthenticatedRequest request = this.requestFactory.createAuthenticatedRequest(Method.TASKS_UNCOMPLETE);
		request.put(RequestParameter.LIST_ID, listId);
		request.put(RequestParameter.TASK_ID, taskId);
		request.put(RequestParameter.TASKSERIES_ID, taskseriesId);
		request.put(RequestParameter.TIMELINE, timeline);
		JSONResponse response = new RestClient(request).execute();
		Transaction<List<Task>> transaction = new Transaction<List<Task>>(response.getModifiedTask());
		response.fillTransaction(transaction);
		return transaction;	
	}
	
	/**
	 * Marks a task incomplete
	 * @param timeline the timeline string
	 * @param task the task to perform the action on
	 * @return the list of Task (taskserie) of the changed task with the task marked as incomplete (with transaction info)
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons

	 */
	public Transaction<List<Task>> tasksUncomplete(String timeline, Task task) throws ServerException, RtmApiException, IOException {
		return this.tasksUncomplete(timeline, task.getId(), task.getTaskserieId(), task.getListId());
	}
	
	/**
	 * Unsets estimate time for a task
	 * @param timeline the timeline string
	 * @param taskId the ID of the task to perform the action on
	 * @param taskseriesId the ID of the taskserie in which the specified task is contained
	 * @param listId the ID of the list in which the specified task is contained
	 * @return the list of Task (taskserie) of the changed task (with transaction info)
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons

	 */
	public Transaction<List<Task>> tasksUnsetEstimate(String timeline, String taskId, String taskseriesId, String listId) throws ServerException, RtmApiException, IOException {
		return this.tasksSetEstimate(timeline, taskId, taskseriesId, listId, "");
	}
	
	/**
	 * Unsets estimate time for a task
	 * @param timeline the timeline string
	 * @param task the task to perform the action on
	 * @return the list of Task (taskserie) of the changed task (with transaction info)
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons

	 */
	public Transaction<List<Task>> tasksUnsetEstimate(String timeline, Task task) throws ServerException, RtmApiException, IOException {
		return this.tasksUnsetEstimate(timeline, task.getId(), task.getTaskserieId(), task.getListId());
	}
	
	/**
	 * Unsets a location for a task
	 * @param timeline the timeline string
	 * @param taskId the ID of the task to perform the action on
	 * @param taskseriesId the ID of the taskserie in which the specified task is contained
	 * @param listId the ID of the list in which the specified task is contained
	 * @return the list of Task (taskserie) of the changed task
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons

	 */
	public Transaction<List<Task>> tasksUnsetLocation(String timeline, String taskId, String taskseriesId, String listId) throws ServerException, RtmApiException, IOException {
		AuthenticatedRequest request = this.requestFactory.createAuthenticatedRequest(Method.TASKS_SET_LOCATION);
		request.put(RequestParameter.LIST_ID, listId);
		request.put(RequestParameter.TASK_ID, taskId);
		request.put(RequestParameter.TASKSERIES_ID, taskseriesId);
		request.put(RequestParameter.TIMELINE, timeline);
		//request.put(RequestParameter.LOCATION_ID, locationId);
		JSONResponse response = new RestClient(request).execute();
		Transaction<List<Task>> transaction = new Transaction<List<Task>>(response.getModifiedTask());
		response.fillTransaction(transaction);
		return transaction;	
	}
	
	/**
	 * Unsets a location for a task
	 * @param timeline the timeline string
	 * @param task the task to perform the action on
	 * @return the list of Task (taskserie) of the changed task
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons

	 */
	public Transaction<List<Task>> tasksUnsetLocation(String timeline, Task task) throws ServerException, RtmApiException, IOException {
		return this.tasksUnsetLocation(timeline, task.getId(), task.getTaskserieId(), task.getListId());
	}
	
	/**
	 * Unsets the priority of a task
	 * @param timeline the timeline string
	 * @param taskId the ID of the task to perform the action on
	 * @param taskseriesId the ID of the taskserie in which the specified task is contained
	 * @param listId the ID of the list in which the specified task is contained
	 * @return the list of Task (taskserie) of the changed task (with transaction info)
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons

	 */
	public Transaction<List<Task>> tasksUnsetPriority(String timeline, String taskId, String taskseriesId, String listId) throws ServerException, RtmApiException, IOException {
		return this.tasksSetPriority(timeline, taskId, taskseriesId, listId, Priority.NONE);
	}
	
	/**
	 * Unsets the priority of a task
	 * @param timeline the timeline string
	 * @param task the task to perform the action on
	 * @return the list of Task (taskserie) of the changed task (with transaction info)
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons

	 */
	public Transaction<List<Task>> tasksUnsetPriority(String timeline, Task task) throws ServerException, RtmApiException, IOException {
		return this.tasksUnsetPriority(timeline, task.getId(), task.getTaskserieId(), task.getListId());
	}
	
	/**
	 * Unsets recurrence pattern for a task
	 * @param timeline the timeline string
	 * @param taskId the ID of the task to perform the action on
	 * @param taskseriesId the ID of the taskserie in which the specified task is contained
	 * @param listId the ID of the list in which the specified task is contained
	 * @return the list of Task (taskserie) of the changed task (with transaction info)
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons

	 */
	public Transaction<List<Task>> tasksUnsetRecurrence(String timeline, String taskId, String taskseriesId, String listId) throws ServerException, RtmApiException, IOException {
		return this.tasksSetRecurrence(timeline, taskId, taskseriesId, listId, "");
	}
	
	/**
	 * Unsets recurrence pattern for a task
	 * @param timeline the timeline string
	 * @param task the task to perform the action on
	 * @return the list of Task (taskserie) of the changed task (with transaction info)
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons

	 */
	public Transaction<List<Task>> tasksUnsetRecurrence(String timeline, Task task) throws ServerException, RtmApiException, IOException {
		return this.tasksUnsetRecurrence(timeline, task.getId(), task.getTaskserieId(), task.getListId());
	}
	
	/**
	 * Removes all tags of a task
	 * @param timeline the timeline string
	 * @param taskId the ID of the task to perform the action on
	 * @param taskseriesId the ID of the taskserie in which the specified task is contained
	 * @param listId the ID of the list in which the specified task is contained
	 * @return the list of Task (taskserie) of the changed task (with transaction info)
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons

	 */
	public Transaction<List<Task>> tasksUnsetTags(String timeline, String taskId, String taskseriesId, String listId) throws ServerException, RtmApiException, IOException {
		return this.tasksSetTags(timeline, taskId, taskseriesId, listId, "");
	}
	
	/**
	 * Removes all tags of a task
	 * @param timeline the timeline string
	 * @param task the task to perform the action on
	 * @return the list of Task (taskserie) of the changed task (with transaction info)
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons

	 */
	public Transaction<List<Task>> tasksUnsetTags(String timeline, Task task) throws ServerException, RtmApiException, IOException {
		return this.tasksUnsetTags(timeline, task.getId(), task.getTaskserieId(), task.getListId());
	}
	
	/**
	 * Removes url of a task
	 * @param timeline the timeline string
	 * @param taskId the ID of the task to perform the action on
	 * @param taskseriesId the ID of the taskserie in which the specified task is contained
	 * @param listId the ID of the list in which the specified task is contained
	 * @return the list of Task (taskserie) of the changed task (with transaction info)
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons

	 */
	public Transaction<List<Task>> tasksUnsetUrl(String timeline, String taskId, String taskseriesId, String listId) throws ServerException, RtmApiException, IOException {
		return this.tasksSetUrl(timeline, taskId, taskseriesId, listId, "");
	}
	
	/**
	 * Removes url of a task
	 * @param timeline the timeline string
	 * @param task the task to perform the action on
	 * @return the list of Task (taskserie) of the changed task (with transaction info)
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons
	 */
	public Transaction<List<Task>> tasksUnsetUrl(String timeline, Task task) throws ServerException, RtmApiException, IOException {
		return this.tasksUnsetUrl(timeline, task.getId(), task.getTaskserieId(), task.getListId());
	}
	
	/**
	 * Adds note to a task
	 * @param timeline the timeline string
	 * @param taskId the ID of the task to which the not must be added
	 * @param taskseriesId the ID of the taskserie in which the specified task is contained
	 * @param listId the ID of the list in which the specified task is contained
	 * @param title the title of the note
	 * @param text he text of the note
	 * @return the added note
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons
	 */
	public Transaction<Note> tasksAddNote(String timeline, String taskId, String taskseriesId, String listId, String title, String text) throws ServerException, RtmApiException, IOException {
		AuthenticatedRequest request = this.requestFactory.createAuthenticatedRequest(Method.TASKS_NOTES_ADD);
		request.put(RequestParameter.LIST_ID, listId);
		request.put(RequestParameter.TASK_ID, taskId);
		request.put(RequestParameter.TASKSERIES_ID, taskseriesId);
		request.put(RequestParameter.TIMELINE, timeline);
		request.put(RequestParameter.TITLE, title);
		request.put(RequestParameter.TEXT, text);
		JSONResponse response = new RestClient(request).execute();
		Transaction<Note> transaction = new Transaction<Note>(response.getModifiedNote());
		response.fillTransaction(transaction);
		return transaction;	
	}
	
	/**
	 * Adds note to a task
	 * @param timeline the timeline string
	 * @param task the task to which the not must be added
	 * @param title the title of the note
	 * @param text he text of the note
	 * @return the added note
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons
	 */
	public Transaction<Note> tasksAddNote(String timeline, Task task, String title, String text) throws ServerException, RtmApiException, IOException {
		return this.tasksAddNote(timeline, task.getId(), task.getTaskserieId(), task.getListId(), title, text);
	}
	
	/**
	 * Deletes a note
	 * @param timeline the timeline string
	 * @param noteId the ID of the note to be deleted
	 * @return true if the note is deleted
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons
	 */
	public boolean tasksDeleteNote(String timeline, String noteId) throws ServerException, RtmApiException, IOException {
		AuthenticatedRequest request = this.requestFactory.createAuthenticatedRequest(Method.TASKS_NOTES_DELETE);
		request.put(RequestParameter.TIMELINE, timeline);
		request.put(RequestParameter.NOTE_ID, noteId);
		return new RestClient(request).execute().getStatus();	
	}
	
	/**
	 * Deletes a note
	 * @param timeline the timeline string
	 * @param note the note to be deleted
	 * @return true if the note is deleted
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons
	 */
	public boolean tasksDeleteNote(String timeline, Note note) throws ServerException, RtmApiException, IOException {
		return this.tasksDeleteNote(timeline, note.getId());
	}
	
	/**
	 * Edits a note
	 * @param timeline the timeline string
	 * @param noteId the ID of the note to be edited
	 * @param title the title of the note
	 * @param text he text of the note
	 * @return the modified note
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons
	 */
	public Transaction<Note> tasksEditNote(String timeline, String noteId, String title, String text) throws ServerException, RtmApiException, IOException {
		AuthenticatedRequest request = this.requestFactory.createAuthenticatedRequest(Method.TASKS_NOTES_EDIT);
		request.put(RequestParameter.NOTE_ID, noteId);
		request.put(RequestParameter.TIMELINE, timeline);
		request.put(RequestParameter.TITLE, title);
		request.put(RequestParameter.TEXT, text);
		JSONResponse response = new RestClient(request).execute();
		Transaction<Note> transaction = new Transaction<Note>(response.getModifiedNote());
		response.fillTransaction(transaction);
		return transaction;	
	}
	
	/**
	 * Edits a note
	 * @param timeline the timeline string
	 * @param note the note to be edited
	 * @param title the title of the note
	 * @param text he text of the note
	 * @return the modified note
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons
	 */
	public Transaction<Note> tasksEditNote(String timeline, Note note, String title, String text) throws ServerException, RtmApiException, IOException {
		return this.tasksEditNote(timeline, note.getId(), title, text);
	}
	
	/**
	 * Undos a transaction
	 * @param transaction the transaction to be undone
	 * @return true if the transaction is undone, false otherwise or if transation is not undoable
	 * @throws ServerException
	 * @throws RtmApiException
	 * @throws IOException
	 */
	public boolean transactionsUndo(String timeline,Transaction<?> transaction) throws ServerException, RtmApiException, IOException {
		if(transaction.isUndoable()) {
			AuthenticatedRequest request = this.requestFactory.createAuthenticatedRequest(Method.TASKS_SET_URL);
			request.put(RequestParameter.TRANSACTION_ID, transaction.getId());
			request.put(RequestParameter.TIMELINE, timeline);
			return new RestClient(request).execute().getStatus();	
		}
		else return false;
	}
	
	/**
	 * Gets the application key
	 * @return the application key
	 */
	public String getApiKey() {
		return this.apiKey;
	}
	
	/**
	 * Gets the application shared secret
	 * @return the application shared secret
	 */
	public String getSharedSecret() {
		return this.sharedSecret;
	}
	
	/**
	 * Gets the used token
	 * @return the used token
	 */
	public String getToken() {
		return this.token;
	}

}
