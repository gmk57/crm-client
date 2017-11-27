package gmk57.crmclient.data;

import com.google.gson.annotations.SerializedName;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Lead {

    @SerializedName("id")
    private String mId;
    @SerializedName("name")
    private String mName;
    @SerializedName("date_create")
    private Long mCreatedDate;
    @SerializedName("price")
    private String mPrice;
    @SerializedName("status_id")
    private String mStatusId;
    private String mStatusName;

    public String getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public String getStatusId() {
        return mStatusId;
    }

    public void setStatusName(String statusName) {
        mStatusName = statusName;
    }

    public String getDetails() {
        String formattedDate = new SimpleDateFormat("dd.MM.yyyy", Locale.US)
                .format(new Date(mCreatedDate * 1000));
        return mPrice + " руб. • " +
                formattedDate + " • " +
                mStatusName;
    }
}
