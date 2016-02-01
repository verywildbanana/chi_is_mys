package com.avocado.makeyoursmile.network.data;

import com.avocado.makeyoursmile.network.data.account.UploadImgData;
import com.avocado.makeyoursmile.network.data.error.ErrorData;
import com.google.gson.Gson;

import java.io.IOException;

public class ImageUploadParser implements BeanParser {
	public UploadImgData mJsonObj;

	@Override
	public int parse(String in) throws IllegalArgumentException, IOException {

		Gson gson = new Gson();
		mJsonObj = gson.fromJson(in, UploadImgData.class);

		return 0;
	}

	@Override
	public boolean isError() {

		return mJsonObj == null;
	}

	@Override
	public ErrorData getErrorData() {

		return null;
	}
}
