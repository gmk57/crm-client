package gmk57.crmclient.leads;

import java.util.List;

import gmk57.crmclient.data.Lead;

public interface LeadsContract {

    interface View {

        void showLeads(List<Lead> leads);

        void showError(Throwable error);
    }

    interface Presenter {

        void takeView(LeadsContract.View view);

        void dropView();
    }
}
