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
 * A class for the definition of a estimate duration for a Task. The fields are
 * <ul>
 * <li> <b>quantity</b> - A float value
 * <li> <b>unit</b> - The unit of duration associated to float value(minutes, hours or days)
 * @author Giovanni Pini
 *
 */
public class Estimate implements Serializable{
	private float quantity;
	private DurationUnit unit;
	public Estimate(float quantity, DurationUnit unit) {
		this.unit = unit;
		this.quantity = quantity;
	}
	
	public void setQuantity(float quantity) {
		this.quantity = quantity;
	}
	
	public float getQuantity() {
		return this.quantity;
	}
	
	public void setUnit(DurationUnit unit) {
		this.unit = unit;;
	}
	
	public DurationUnit getUnit() {
		return this.unit;
	}
		
	public enum DurationUnit {
		MINUTES,HOURS,DAYS,NONE;
	}
		
	public String toString() {
		return "" + quantity + " " + unit; 
	}
}