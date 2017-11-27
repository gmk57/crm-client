package gmk57.crmclient.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

class Wrappers {
    static class ResponseOuter {
        ResponseInner response;
    }

    static class ResponseInner {
        List<Lead> leads;
        Account account;
    }

    static class Account {
        @SerializedName("leads_statuses")
        List<LeadStatus> mLeadStatuses;
    }
}
