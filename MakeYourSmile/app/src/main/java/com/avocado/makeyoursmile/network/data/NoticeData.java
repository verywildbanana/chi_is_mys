package com.avocado.makeyoursmile.network.data;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class NoticeData {

	public static final String TYPE_IMG = "image";
	public static final String TYPE_TXT = "text";
	public static final String TYPE_URL = "url";
	
	@SerializedName("notice_id")
	public long mId = 0;

	@SerializedName("title")
	public String mTitle;
	
	@SerializedName("contents")
	public String mContents;
	
	@SerializedName("created_at")
	public Date mCreateAt;

}
