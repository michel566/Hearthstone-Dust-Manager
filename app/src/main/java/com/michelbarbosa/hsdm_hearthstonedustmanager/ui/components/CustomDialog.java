package com.michelbarbosa.hsdm_hearthstonedustmanager.ui.components;

import android.app.Dialog;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;

import com.michelbarbosa.hsdm_hearthstonedustmanager.R;
import com.michelbarbosa.hsdm_hearthstonedustmanager.enums.DialogType;
import com.michelbarbosa.hsdm_hearthstonedustmanager.utils.UIUtil;
import com.michelbarbosa.hsdm_hearthstonedustmanager.utils.Util;

public class CustomDialog extends Dialog implements View.OnClickListener {

    public Context context;
    private String message;
    private DialogType type;
    private TextView tvTitle;
    private ImageView iconDialogBox;

    public CustomDialog(Context context, String message, DialogType dialogType) {
        super(context);
        this.context = context;
        this.message = message;
        this.type = dialogType;
    }

    public CustomDialog(Context context, String message, DialogType dialogType, boolean isCanceledOnTouchOutside) {
        super(context);
        this.context = context;
        this.message = message;
        this.type = dialogType;
        this.setCanceledOnTouchOutside(isCanceledOnTouchOutside);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialogbox_alert);

        LinearLayout container = findViewById(R.id.container_dialogBoxAlert);
        iconDialogBox = findViewById(R.id.iconDialogBox);
        tvTitle = findViewById(R.id.tv_TitleDialogBox);
        TextView tvDialogText = findViewById(R.id.tv_dialogText);
        UIUtil.setToCardLayout(context, container, R.drawable.layout_card, R.drawable.layout_card_shadow);
        changeDialogType(type);
        tvDialogText.setText(message);
    }

    private void changeDialogType(DialogType type) {
        switch (type) {
            case INFO:
                tvTitle.setText("Information");
                tvTitle.setTextColor(context.getResources().getColor(R.color.colorInfoDialog));
                UIUtil.iconFillColor(context, iconDialogBox, R.drawable.ic_info, R.color.colorInfoDialog);
                this.dismiss();
                break;
            case ALERT:
                tvTitle.setText("Attention!");
                tvTitle.setTextColor(context.getResources().getColor(R.color.colorAlertDialog));
                UIUtil.iconFillColor(context, iconDialogBox, R.drawable.ic_error, R.color.colorAlertDialog);
                this.dismiss();
                break;
            case ERROR:
                tvTitle.setText("Error");
                tvTitle.setTextColor(context.getResources().getColor(R.color.colorErrorDialog));
                UIUtil.iconFillColor(context, iconDialogBox, R.drawable.ic_cancel, R.color.colorErrorDialog);
                this.dismiss();
                break;
            default:
                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_DialogEscape:
                dismiss();
                break;
            default:
                break;
        }
        dismiss();
    }


    public void onOptionEscapeClickListener(View.OnClickListener listener, String textButton) {
        Button escape = findViewById(R.id.bt_DialogEscape);
        escape.setVisibility(View.VISIBLE);
        escape.setText(textButton);
        escape.setOnClickListener(listener);
    }

    public void onOptionConfirmClickListener(View.OnClickListener listener, String textButton) {
        Button confirm = findViewById(R.id.bt_DialogConfirm);
        confirm.setVisibility(View.VISIBLE);
        confirm.setText(textButton);
        confirm.setOnClickListener(listener);
    }
}