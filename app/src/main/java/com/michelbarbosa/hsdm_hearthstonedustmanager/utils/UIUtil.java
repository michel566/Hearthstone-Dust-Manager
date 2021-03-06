package com.michelbarbosa.hsdm_hearthstonedustmanager.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;

import com.michelbarbosa.hsdm_hearthstonedustmanager.R;
import com.michelbarbosa.hsdm_hearthstonedustmanager.enums.DialogType;
import com.michelbarbosa.hsdm_hearthstonedustmanager.ui.components.CustomDialog;

import java.util.List;
import java.util.Locale;

public class UIUtil {

    public static void setToCardLayout(Context context, View view, int resourceDrawable, int supportResourceDrawable) {
        if (Util.checkMinimalAPI(Build.VERSION_CODES.LOLLIPOP)) {
            view.setBackground(context.getResources().getDrawable(resourceDrawable));
            view.setElevation(context.getResources().getDimension(R.dimen.elevation_widget_default));
        } else {
            view.setBackground(context.getResources().getDrawable(supportResourceDrawable));
        }
    }

    public static void showDialogOption(CustomDialog customDialog,
                                        String textEscapeButton, View.OnClickListener escapeButtonOption,
                                        String textConfirmButton, View.OnClickListener confirmButtonOption) {
        if (escapeButtonOption != null) {
            customDialog.onOptionEscapeClickListener(escapeButtonOption, textEscapeButton);
        }
        if (confirmButtonOption != null) {
            customDialog.onOptionConfirmClickListener(confirmButtonOption, textConfirmButton);
        }
    }


    public static void iconFillColor(Context context, ImageView imageView, int resourceDrawable, int resourceTargetColor) {
        if (Util.checkMinimalAPI(Build.VERSION_CODES.LOLLIPOP)) {
            Drawable drawable = VectorDrawableCompat.create(context.getResources(),
                    resourceDrawable, context.getTheme());
            if (drawable != null) {
                PorterDuffColorFilter porterDuffColorFilter = new PorterDuffColorFilter(
                        ContextCompat.getColor(context, resourceTargetColor), PorterDuff.Mode.SRC_ATOP);

                drawable.setColorFilter(porterDuffColorFilter);
                imageView.setImageDrawable(drawable);
            }
        }
    }

    public static AlertDialog progressDialog(Context context, String message) {
        int llPadding = 30;
        LinearLayout ll = new LinearLayout(context);
        ll.setOrientation(LinearLayout.HORIZONTAL);
        ll.setPadding(llPadding, llPadding, llPadding, llPadding);
        ll.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams llParam = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        llParam.gravity = Gravity.CENTER;
        ll.setLayoutParams(llParam);

        ProgressBar progressBar = new ProgressBar(context);
        progressBar.setIndeterminate(true);
        progressBar.setPadding(0, 0, llPadding, 0);
        progressBar.setLayoutParams(llParam);

        llParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        llParam.gravity = Gravity.CENTER;
        TextView tvText = new TextView(context);
        tvText.setText(message);
        tvText.setTextColor(Color.parseColor("#000000"));
        tvText.setTextSize(16);
        tvText.setLayoutParams(llParam);

        ll.addView(progressBar);
        ll.addView(tvText);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false);
        builder.setView(ll);

        AlertDialog dialog = builder.create();
        Window window = dialog.getWindow();
        if (window != null) {
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
            layoutParams.copyFrom(dialog.getWindow().getAttributes());
            layoutParams.width = LinearLayout.LayoutParams.WRAP_CONTENT;
            layoutParams.height = LinearLayout.LayoutParams.WRAP_CONTENT;
            dialog.getWindow().setAttributes(layoutParams);
        }
        return dialog;
    }

    public static AlertDialog.Builder selectionOptionList(final Context context, String title,
                                                          DialogInterface.OnClickListener onClick, final String... options) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setItems(options, onClick);
        return builder;
    }

    public static void showToastListChanged(Context context, List<String> listAdapter, String flag) {
        StringBuilder text = new StringBuilder();
        text.append(flag).append("\n");
        int i = 0;
        String brokeLine = "\n - ";
        for (String element : listAdapter) {
            if (i != 0) {
                text.append(brokeLine).append(i).append(":").append(element);
            } else {
                text.append(" - ").append(i).append(":").append(element);
            }
            i++;
        }

        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

    public static void setTextViewWithValue(Context context, TextView textView,
                                            int resourceValue, String text, int value) {
        if (text == null) {
            textView.setText(context.getResources().getString(resourceValue, value));
        } else {
            textView.setText(String.format(Locale.getDefault(), text, value));
        }
    }

    public static void setTextViewToTooltipDialog(final Context context, final List<String> textsToDialog, List<TextView> textViewers) {
        int index = 0;
        for (final TextView tv : textViewers) {
            final int finalIndex = index;
            tv.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    final CustomDialog tooltipDialog = new CustomDialog(context,
                            textsToDialog.get(finalIndex), DialogType.INFO, true);
                    tooltipDialog.show();
                    tooltipDialog.onOptionConfirmClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            tooltipDialog.dismiss();
                        }
                    }, context.getResources().getString(R.string.dialog_bt_ok));
                    return true;
                }
            });
            index++;
        }

    }

}
