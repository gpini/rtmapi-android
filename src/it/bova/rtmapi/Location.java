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

import org.json.JSONObject;

/**
 * An object for a Remember the Milk location. The fields are:
 * <ul>
 * <li> <b>id</b> - The string unique ID
 * <li> <b>address</b> - A string for the address of the location
 * <li> <b>latitude</b> - The floating point latitude of the location
 * <li> <b>longitude</b> - The floating point longitude of the location
 * <li> <b>name</b> - The name label of the location
 * <li> <b>viewable</b> - A boolean value that is true if the location is viewable on the map (I'm not sure)
 * <li> <b>zoom</b> - The level of zoom within the map, when dthe location is displayed
 * </ul>
 * @author Giovanni Pini
 *
 */
public class Location implements RtmObject,Serializable {
	
	private String address;
	private String id;
	private double latitude, longitude;
	private String name;
	private boolean viewable;
	private int zoom;
	
	
	
	public Location(String address, String id, double latitude,
			double longitude, String name, boolean viewable, int zoom) {
		this.address = address;
		this.id = id;
		this.latitude = latitude;
		this.longitude = longitude;
		this.name = name;
		this.viewable = viewable;
		this.zoom = zoom;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isViewable() {
		return viewable;
	}
	public void setViewable(boolean viewable) {
		this.viewable = viewable;
	}
	public int getZoom() {
		return zoom;
	}
	public void setZoom(int zoom) {
		this.zoom = zoom;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Location) return this.getId().equals(((Location)o).getId());
		else return false;
	}
	
	@Override
	public String toString() {
		return "RtmLocation [address=" + address + ", id=" + id + ", latitude="
				+ latitude + ", longitude=" + longitude + ", name=" + name
				+ ", viewable=" + viewable + ", zoom=" + zoom + "]";
	}

}
