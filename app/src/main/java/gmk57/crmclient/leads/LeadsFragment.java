package gmk57.crmclient.leads;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;
import gmk57.crmclient.R;
import gmk57.crmclient.data.Lead;

public class LeadsFragment extends DaggerFragment implements LeadsContract.View {

    @Inject
    LeadsContract.Presenter mPresenter;
    private LeadAdapter mAdapter;

    @Inject
    public LeadsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_leads, container, false);

        RecyclerView leadsRecyclerView = view.findViewById(R.id.leads_recycler_view);
        leadsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mAdapter = new LeadAdapter();
        leadsRecyclerView.setAdapter(mAdapter);

        mPresenter.takeView(this);

        return view;
    }

    @Override
    public void onDestroyView() {
        mPresenter.dropView();
        super.onDestroyView();
    }

    @Override
    public void showLeads(List<Lead> leads) {
        mAdapter.setLeads(leads);
    }

    @Override
    public void showError(Throwable error) {
        String message = "Loading leads failed: " + error.getMessage();
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

    private class LeadHolder extends RecyclerView.ViewHolder {

        private final TextView mTitle;
        private final TextView mSubtitle;

        LeadHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(android.R.layout.simple_list_item_2, parent, false));
            mTitle = itemView.findViewById(android.R.id.text1);
            mSubtitle = itemView.findViewById(android.R.id.text2);
        }

        void bind(Lead lead) {
            mTitle.setText(lead.getName());
            mSubtitle.setText(lead.getDetails());
        }
    }

    private class LeadAdapter extends RecyclerView.Adapter<LeadHolder> {

        private List<Lead> mLeads;

        public void setLeads(List<Lead> leads) {
            mLeads = leads;
            notifyDataSetChanged();
        }

        @Override
        public LeadHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new LeadHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(LeadHolder holder, int position) {
            holder.bind(mLeads.get(position));
        }

        @Override
        public int getItemCount() {
            return (mLeads != null) ? mLeads.size() : 0;
        }
    }
}
