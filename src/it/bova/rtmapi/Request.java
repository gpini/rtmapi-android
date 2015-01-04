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

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.Normalizer;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;


class Request {
	
	TreeMap<String,String> parameters;
	String baseUrl;
		
	Request(Method method, String apiKey) {
		this.parameters = new TreeMap<String,String>();
		this.baseUrl = method.getBaseUrl();
		this.put(RequestParameter.FORMAT,"json");
		this.put(RequestParameter.API_KEY,apiKey);
		if (!(method == Method.NO_METHOD)) {
			this.put(RequestParameter.METHOD,method.toString());
		}
	}
	
	void put(RequestParameter parameter, String value) {
		this.parameters.put(parameter.toString(),value);
	}
	
	void put(String parameter, String value) {
		this.parameters.put(parameter.toString(),value);
	}
	
	void put(RequestParameter parameter, int value) {
		this.parameters.put(parameter.toString(), "" + value);
	}
	
	void put(RequestParameter parameter, long value) {
		this.parameters.put(parameter.toString(), "" + value);
	}
	
	void put(RequestParameter parameter, Method method) {
		this.parameters.put(parameter.toString(),method.toString());
	}
		
	String getUrl() throws RtmApiException { //devo metterla qui perchè il signed me la genera
		String url = this.baseUrl + "?";
		String query = "";
		//come ottenere un iteratore non ordinato? Mi serve davvero? NO!
		Iterator it = parameters.entrySet().iterator(); //in ordine di chiave, senza ordinare la mappa?
		while (it.hasNext()) {
			Map.Entry pairs = (Map.Entry) it.next();
			try {
				query = query + URLEncoder.encode(pairs.getKey().toString(),"UTF-8") + "=" 
					+ URLEncoder.encode(pairs.getValue().toString(), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				throw new RtmApiException(e.getMessage());
			}
			if (it.hasNext()) query += "&";
		}

		String completeUrl =  url+query;
		//System.out.println("url - " + completeUrl);
		return completeUrl;

	}
	
}
