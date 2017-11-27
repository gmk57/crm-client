package gmk57.crmclient.data;

import com.google.gson.annotations.SerializedName;

class LeadStatus {

    @SerializedName("id")
    private String mId;
    @SerializedName("name")
    private String mName;

    public String getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }
}
