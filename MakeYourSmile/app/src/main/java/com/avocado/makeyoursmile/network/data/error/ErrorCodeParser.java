package com.avocado.makeyoursmile.network.data.error;

import com.avocado.makeyoursmile.network.data.BeanParser;
import com.google.gson.Gson;

import java.io.IOException;

public class ErrorCodeParser implements BeanParser {
	public ErrorCodeParserData mJsonObj;

	@Override
	public int parse(String in) throws IllegalArgumentException, IOException {

		Gson gson = new Gson();
		mJsonObj = gson.fromJson(in, ErrorCodeParserData.class);

		return 0;
	}

	@Override
	public boolean isError() {

		return mJsonObj != null && mJsonObj.mErrorData != null;
	}

	@Override
	public ErrorData getErrorData() {

		if(mJsonObj != null) {
			return mJsonObj.mErrorData;
		}
		
		return null;
	}
}
