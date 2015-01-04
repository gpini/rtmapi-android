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

enum Method {
	AUTH_CHECK_TOKEN("rtm.auth.checkToken"),
	AUTH_GET_TOKEN("rtm.auth.getToken"),
	AUTH_GET_FROB("rtm.auth.getFrob"),
	CONTACTS_ADD("rtm.contacts.add"),
	CONTACTS_DELETE("rtm.contacts.delete"),
	CONTACTS_GET_LIST("rtm.contacts.getList"),
	LOCATIONS_GET_LIST("rtm.locations.getList"),
	GROUPS_ADD("rtm.groups.add"),
	GROUPS_ADD_CONTACT("rtm.groups.addContact"),
	GROUPS_DELETE("rtm.groups.delete"),
	GROUPS_GET_LIST("rtm.groups.getList"),
	GROUPS_REMOVE_CONTACT("rtm.groups.removeContact"),
	LISTS_ADD("rtm.lists.add"),
	LISTS_ARCHIVE("rtm.lists.archive"),
	LISTS_DELETE("rtm.lists.delete"),
	LISTS_GET_LIST("rtm.lists.getList"),
	LISTS_SET_DEFAULT("rtm.lists.setDefaultList"),
	LISTS_SET_NAME("rtm.lists.setName"),
	LISTS_UNARCHIVE("rtm.lists.unarchive"),
	NO_METHOD("") {
		public String getBaseUrl() {return "http://www.rememberthemilk.com/services/auth/";}
	},
	REFLECTION_GET_METHODS("rtm.reflection.getMethods"),
	REFLECTION_GET_METHOD_INFO("rtm.reflection.getMethodInfo"),
	SETTINGS_GET_LIST("rtm.settings.getList"),
	TASKS_GET_LIST("rtm.tasks.getList"),
	TASKS_ADD("rtm.tasks.add"),
	TASKS_ADD_TAGS("rtm.tasks.addTags"),
	TASKS_DELETE("rtm.tasks.delete"),
	TASKS_COMPLETE("rtm.tasks.complete"),
	TASKS_MOVE_PRIORITY("rtm.tasks.movePriority"),
	TASKS_MOVE_TO("rtm.tasks.moveTo"),
	TASKS_NOTES_ADD("rtm.tasks.notes.add"),
	TASKS_NOTES_DELETE("rtm.tasks.notes.delete"),
	TASKS_NOTES_EDIT("rtm.tasks.notes.edit"),
	TASKS_POSTPONE("rtm.tasks.postpone"),
	TASKS_REMOVE_TAGS("rtm.tasks.removeTags"),
	TASKS_SET_DUE_DATE("rtm.tasks.setDueDate"),
	TASKS_SET_ESTIMATE("rtm.tasks.setEstimate"),
	TASKS_SET_LOCATION("rtm.tasks.setLocation"),
	TASKS_SET_NAME("rtm.tasks.setName"),
	TASKS_SET_PRIORITY("rtm.tasks.setPriority"),
	TASKS_SET_RECURRENCE("rtm.tasks.setRecurrence"),
	TASKS_SET_TAGS("rtm.tasks.setTags"),
	TASKS_SET_URL("rtm.tasks.setURL"),
	TASKS_UNCOMPLETE("rtm.tasks.uncomplete"),
	TEST_ECHO("rtm.test.echo"),
	TEST_LOGIN("rtm.test.login"),
	TIME_CONVERT("rtm.time.convert"),
	TIME_PARSE("rtm.time.parse"),
	TIMELINES_CREATE("rtm.timelines.create"),
	TIMEZONES_GET_LIST("rtm.timezones.getList"),
	TRANSACTIONS_UNDO("rtm.transactions.undo");
	
	private String method;
	private Method (String method) {this.method = method; }
	public String toString() {return this.method; }	
	public String getBaseUrl() {return "http://api.rememberthemilk.com/services/rest/";}
}
