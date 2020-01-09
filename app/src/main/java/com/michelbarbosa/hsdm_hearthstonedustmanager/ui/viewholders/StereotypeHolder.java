package com.michelbarbosa.hsdm_hearthstonedustmanager.ui.viewholders;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.michelbarbosa.hsdm_hearthstonedustmanager.R;
import com.michelbarbosa.hsdm_hearthstonedustmanager.ui.interfaces.StereotypeRecyclerClickListener;

public class StereotypeHolder extends RecyclerView.ViewHolder {

    private final StereotypeRecyclerClickListener viewHolderListener;
    private TextView tvStereotype;
    private ImageView imRemoveStereotype;

    public StereotypeHolder(@NonNull View itemView, final StereotypeRecyclerClickListener viewHolderListener) {
        super(itemView);
        this.viewHolderListener = viewHolderListener;
        this.tvStereotype = itemView.findViewById(R.id.tv_cardStereotype_name);
        this.imRemoveStereotype = itemView.findViewById(R.id.im_cardStereotype_remove);

        imRemoveStereotype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewHolderListener.onClick(imRemoveStereotype, getAdapterPosition());
            }
        });
    }

    public void bindItem(String stereotypeName){
        if(!TextUtils.isEmpty(stereotypeName)){
            tvStereotype.setText(stereotypeName);
        }
    }

}
