package com.michelbarbosa.hsdm_hearthstonedustmanager.ui.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.michelbarbosa.hsdm_hearthstonedustmanager.R;
import com.michelbarbosa.hsdm_hearthstonedustmanager.ui.interfaces.StereotypeRecyclerClickListener;
import com.michelbarbosa.hsdm_hearthstonedustmanager.ui.viewholders.StereotypeHolder;

import java.util.ArrayList;
import java.util.List;

public class StereotypeAdapter extends RecyclerView.Adapter<StereotypeHolder> {

    private final LayoutInflater layoutInflater;
    private final StereotypeRecyclerClickListener listener;
    private List<String> stereotypeList;

    public StereotypeAdapter(LayoutInflater layoutInflater,
                             StereotypeRecyclerClickListener listener, List<String> stereotypeList) {
        this.layoutInflater = layoutInflater;
        this.listener = listener;
        this.stereotypeList = stereotypeList;
    }

    @NonNull
    @Override
    public StereotypeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new StereotypeHolder(layoutInflater.inflate(R.layout.card_stereotype, null), listener);
    }

    @Override
    public void onBindViewHolder(StereotypeHolder holder, int position) {
        holder.bindItem(stereotypeList.get(position));
    }

    @Override
    public int getItemCount() {
        return stereotypeList != null ? stereotypeList.size() : 0;
    }

    public void addStereotype(String stereotype){
        stereotypeList.add(stereotype);
        notifyItemInserted(getItemCount());
    }

    public void removeStereotype(int position){
        stereotypeList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, stereotypeList.size());
    }

    public ArrayList<String> getList(){
        return (ArrayList<String>) stereotypeList;
    }

    public void removeAllStereotypes(){
        stereotypeList.clear();
        notifyDataSetChanged();
    }

}
