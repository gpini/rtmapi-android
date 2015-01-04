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

/**
 * A generic container for transactions, including transaction information and the subject of the transaction itself.
 * @param <T> The object subjected to the transaction
 * @author Giovanni Pini
 */
public class Transaction<T> implements Serializable{
	
	private String id = "";
	private boolean undoable = false;
	private T object;

	/**
	 * The Transaction constructor
	 * @param id The string ID of the transaction, for undoing purpose.
	 * @param undoable A boolean value that is true if the transaction is undoable.
	 * @param object The object subjected to the transaction
	 */
	public Transaction(String id, boolean undoable, T object) {
		this.id = id;
		this.undoable = undoable;
		this.object = object;
	}
	
	Transaction(T object) {
		this.object = object;
	}
	
	/**
	 * Sets the string ID of the transaction
	 * @param id string ID of the transaction
	 */
	void setId(String id) { this.id = id;}
	
	/**
	 * Sets if the transaction is undoable or not with a boolean flag
	 * @param undoable A boolean value that is true if the transaction is undoable
	 */
	void setUndoable(boolean undoable) { this.undoable = undoable;}
	
	/**
	 * Insert the object that is subjected to the trasaction
	 * @param object The object subjected to the transaction
	 */
	void setObject(T object) { this.object = object;}
	
	/**
	 * Gets the string ID of the transaction
	 * @return the string ID of the transaction
	 */
	public String getId() { return this.id;}
	
	/**
	 * Gets the status of transaction; in particular if it is undoable or not
	 * @return a boolean value that is true if the transaction is undoable
	 */
	public boolean isUndoable() {return this.undoable;}
	
	/**
	 * Extract the object that is subjected to the trasaction
	 * @return the object that is subjected to the trasaction
	 */
	public T getObject() { return this.object;}

}
