package com.avocado.makeyoursmile.network.data.dentist;

import com.avocado.makeyoursmile.network.data.error.ErrorData;
import com.google.gson.annotations.SerializedName;

/**
 * Created by HDlee on 11/26/15.
 */
public class DetailDentistParserData extends ErrorData {

    @SerializedName("dentist")
    public DentistData mDentistData;

}
