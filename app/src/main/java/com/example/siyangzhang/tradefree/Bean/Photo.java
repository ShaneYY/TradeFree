package com.example.siyangzhang.tradefree.Bean;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by siyangzhang on 2/17/17.
 */

public class Photo implements Serializable {
	private static final String JSON_FILENAME = "filename";
	
	private String mFilename;
	
	// Create a Photo representing an existing file on disk
	public Photo(String filename){
		mFilename = filename;
	}
	
	public Photo(JSONObject json) throws JSONException {
		mFilename = json.getString(JSON_FILENAME);
	}
	
	public JSONObject toJSON() throws JSONException {
		JSONObject json = new JSONObject();
		json.put(JSON_FILENAME, mFilename);
		return json;
	}
	
	public String getFilename() {
		return mFilename;
	}
	
}
