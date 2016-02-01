package com.avocado.makeyoursmile.network.data;


import com.avocado.makeyoursmile.network.data.error.ErrorData;

import java.io.IOException;

public interface BeanParser {
	
	int parse(String in) throws IllegalArgumentException, IOException;

	boolean isError();
	
	ErrorData getErrorData();

//	public int parse(InputStream in) throws JsonParseException,
//			JsonMappingException, IOException;
}
