package com.avocado.makeyoursmile.network.data.error;

import com.avocado.makeyoursmile.util.SmartLog;
import com.google.gson.annotations.SerializedName;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class ErrorData {
	
	public final static String TAG = ErrorData.class.getSimpleName();

	@SerializedName("code")
	public String mCode;

	@SerializedName("message")
	private String dpMsg;


	@SerializedName("url")
	public String url;

	public UpdateData update_info;

	public String getDpMsg() {
		try {
			
			dpMsg = URLDecoder.decode(dpMsg, "utf-8");
			
		} 
		catch (UnsupportedEncodingException e) {
			
			// e.printStackTrace();
			SmartLog.getInstance().e(TAG, "UnsupportedEncodingException");
		}
		catch (Exception e) {
			
			SmartLog.getInstance().e(TAG, "Exception: " + e);
		}

		return dpMsg;
	}

//	@SerializedName("display_message")
	public void setDpMsg(String dpMsg) {
		
		this.dpMsg = dpMsg;
	}



//	{
//		"code": "400.1101",
//			"message": "최신 버전으로 업데이트가 필요합니다.",
//			"update_info": {
//		"required_version": "1.0.0",
//				"update_url": "https://play.google.com/store/apps/details?id=com.nsocialnetwork.wearru"
//	       }
//	}



}
