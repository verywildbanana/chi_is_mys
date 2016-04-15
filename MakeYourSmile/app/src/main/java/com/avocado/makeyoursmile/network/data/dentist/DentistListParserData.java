package com.avocado.makeyoursmile.network.data.dentist;

import com.avocado.makeyoursmile.network.data.error.ErrorData;

import java.util.ArrayList;

/**
 * Created by HDlee on 4/15/16.
 */
public class DentistListParserData extends ErrorData {

    public int TOTAL_COUNT;

    public boolean NEXT;

    public ArrayList<DentistData> dentist = new ArrayList<DentistData>();

}
