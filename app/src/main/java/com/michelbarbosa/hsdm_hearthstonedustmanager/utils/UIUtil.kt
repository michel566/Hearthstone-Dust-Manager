package com.michelbarbosa.hsdm_hearthstonedustmanager.utils

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat
import com.michelbarbosa.hsdm_hearthstonedustmanager.R
import com.michelbarbosa.hsdm_hearthstonedustmanager.enums.DialogType
import com.michelbarbosa.hsdm_hearthstonedustmanager.ui.components.CustomDialog
import java.util.*

object UIUtil {
    @JvmStatic
    fun setToCardLayout(context: Context, view: View, resourceDrawable: Int, supportResourceDrawable: Int) {
        if (Util.checkMinimalAPI(Build.VERSION_CODES.LOLLIPOP)) {
            view.background = context.resources.getDrawable(resourceDrawable)
            view.elevation = context.resources.getDimension(R.dimen.elevation_widget_default)
        } else {
            view.background = context.resources.getDrawable(supportResourceDrawable)
        }
    }

    @JvmStatic
    fun showDialogOption(customDialog: CustomDialog,
                         textEscapeButton: String?, escapeButtonOption: View.OnClickListener?,
                         textConfirmButton: String?, confirmButtonOption: View.OnClickListener?) {
        if (escapeButtonOption != null) {
            customDialog.onOptionEscapeClickListener(escapeButtonOption, textEscapeButton)
        }
        if (confirmButtonOption != null) {
            customDialog.onOptionConfirmClickListener(confirmButtonOption, textConfirmButton)
        }
    }

    @JvmStatic
    fun iconFillColor(context: Context, imageView: ImageView, resourceDrawable: Int, resourceTargetColor: Int) {
        if (Util.checkMinimalAPI(Build.VERSION_CODES.LOLLIPOP)) {
            val drawable: Drawable? = VectorDrawableCompat.create(context.resources,
                    resourceDrawable, context.theme)
            if (drawable != null) {
                val porterDuffColorFilter = PorterDuffColorFilter(
                        ContextCompat.getColor(context, resourceTargetColor), PorterDuff.Mode.SRC_ATOP)
                drawable.colorFilter = porterDuffColorFilter
                imageView.setImageDrawable(drawable)
            }
        }
    }

    @JvmStatic
    fun progressDialog(context: Context?, message: String?): AlertDialog {
        val llPadding = 30
        val ll = LinearLayout(context)
        ll.orientation = LinearLayout.HORIZONTAL
        ll.setPadding(llPadding, llPadding, llPadding, llPadding)
        ll.gravity = Gravity.CENTER
        var llParam = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT)
        llParam.gravity = Gravity.CENTER
        ll.layoutParams = llParam
        val progressBar = ProgressBar(context)
        progressBar.isIndeterminate = true
        progressBar.setPadding(0, 0, llPadding, 0)
        progressBar.layoutParams = llParam
        llParam = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)
        llParam.gravity = Gravity.CENTER
        val tvText = TextView(context)
        tvText.text = message
        tvText.setTextColor(Color.parseColor("#000000"))
        tvText.textSize = 16f
        tvText.layoutParams = llParam
        ll.addView(progressBar)
        ll.addView(tvText)
        val builder = AlertDialog.Builder(context)
        builder.setCancelable(false)
        builder.setView(ll)
        val dialog = builder.create()
        val window = dialog.window
        if (window != null) {
            val layoutParams = WindowManager.LayoutParams()
            layoutParams.copyFrom(dialog.window!!.attributes)
            layoutParams.width = LinearLayout.LayoutParams.WRAP_CONTENT
            layoutParams.height = LinearLayout.LayoutParams.WRAP_CONTENT
            dialog.window!!.attributes = layoutParams
        }
        return dialog
    }

    fun selectionOptionList(context: Context?, title: String?,
                            onClick: DialogInterface.OnClickListener?, vararg options: String?): AlertDialog.Builder {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(title)
        builder.setItems(options, onClick)
        return builder
    }

    fun showToastListChanged(context: Context?, listAdapter: List<String?>, flag: String?) {
        val text = StringBuilder()
        text.append(flag).append("\n")
        var i = 0
        val brokeLine = "\n - "
        for (element in listAdapter) {
            if (i != 0) {
                text.append(brokeLine).append(i).append(":").append(element)
            } else {
                text.append(" - ").append(i).append(":").append(element)
            }
            i++
        }
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

    @JvmStatic
    fun setTextViewWithValue(context: Context, textView: TextView,
                             resourceValue: Int, text: String?, value: Int) {
        if (text == null) {
            textView.text = context.resources.getString(resourceValue, value)
        } else {
            textView.text = String.format(Locale.getDefault(), text, value)
        }
    }

    @JvmStatic
    fun setTextViewToTooltipDialog(context: Context, textsToDialog: List<String?>, textViewers: List<TextView>) {
        var index = 0
        for (tv in textViewers) {
            val finalIndex = index
            tv.setOnLongClickListener {
                val tooltipDialog = CustomDialog(context,
                        textsToDialog[finalIndex], DialogType.INFO, true)
                tooltipDialog.show()
                tooltipDialog.onOptionConfirmClickListener({ tooltipDialog.dismiss() }, context.resources.getString(R.string.dialog_bt_ok))
                true
            }
            index++
        }
    }
}