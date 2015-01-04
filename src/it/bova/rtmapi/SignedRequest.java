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
import java.util.Iterator;
import java.util.Map;

class SignedRequest extends Request {
	
	private String sharedSecret = "";
	
	SignedRequest(Method method, String apiKey, String sharedSecret) {
		super(method, apiKey);
		this.sharedSecret = sharedSecret;
	}
	
	String getUrl() throws RtmApiException {
		//togliere Md5Exception md5="" se c'è errore?
		String url = super.getUrl() + "&api_sig=" + this.getSignature();
		//System.out.println("url - " + url);
		return url;
	}

	private String getSignature() throws RtmApiException {
		String signature = this.sharedSecret;
		Iterator it = parameters.entrySet().iterator(); //in ordine di chiave, senza ordinare la mappa?
		while (it.hasNext()) {
			Map.Entry pairs = (Map.Entry) it.next();
			String key = pairs.getKey().toString();
			String value = pairs.getValue().toString();
			signature = signature + key + value;
		}
		//System.out.println("signature - " + signature);
		String md5 =  Md5Helper.md5(signature);
		//System.out.println("md5 - " + md5);
		return md5;
	
	}
}
