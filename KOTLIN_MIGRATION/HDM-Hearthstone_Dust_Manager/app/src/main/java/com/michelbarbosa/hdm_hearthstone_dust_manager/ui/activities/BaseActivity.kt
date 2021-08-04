package com.michelbarbosa.hdm_hearthstone_dust_manager.ui.activities

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.util.TypedValue
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.michelbarbosa.hdm_hearthstone_dust_manager.BuildConfig
import com.michelbarbosa.hdm_hearthstone_dust_manager.R
import com.michelbarbosa.hdm_hearthstone_dust_manager.R.drawable

/**
 * Created by Michel Barbosa on 29/07/2021.
 */
open class BaseActivity : AppCompatActivity() {

    protected lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
    }

    open fun setToolbar() {
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        setOverflowIcon()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            toolbar.elevation = 4f
        }
        enableDisplayShow(false)
    }

    protected fun setToolbarTitle(idResourceTitle: Int) {
        if (idResourceTitle != 0) {
            val titleToolbar: TextView = findViewById(R.id.titleToolbar)
            val title: String = resources.getString(idResourceTitle)
            titleToolbar.text = title

            if (title.length > 23) {
                titleToolbar.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12F)
            } else if (title.length > 19) {
                titleToolbar.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16F)
            }
        }
    }

    fun managerFragmentTransaction(idContainer: Int, fragment: Fragment?) {
        val manager: FragmentManager = supportFragmentManager
        manager.findFragmentById(idContainer)
        val transaction = manager.beginTransaction()
        if (transaction.isEmpty) {
            transaction.add(idContainer, fragment!!)
        } else {
            transaction.replace(idContainer, fragment!!)
            transaction.addToBackStack(null)
        }
        transaction.commit()
    }

    open fun setArrowBackPressed() {
        enableDisplayShow(true)
        val arrow: Drawable = resources.getDrawable(drawable.ic_arrow_back)
        toolbar.navigationIcon = arrow
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    private fun enableDisplayShow(isShow: Boolean) {
        supportActionBar?.setDisplayShowHomeEnabled(isShow)
        supportActionBar?.setDisplayShowTitleEnabled(isShow)
    }

    fun setOverflowIcon() {
        val icon = ContextCompat.getDrawable(applicationContext, drawable.ic_more_vert)
        toolbar.overflowIcon = icon
    }

    fun setLayoutContent(resourceContentView: Int) {
        val dynamicContent = findViewById<RelativeLayout>(R.id.content)
        val view = layoutInflater.inflate(resourceContentView, dynamicContent, false)
        dynamicContent.addView(view)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_testes) {
            item.isVisible = BuildConfig.DEBUG
        }
        return super.onOptionsItemSelected(item)
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (currentFocus != null) run {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
            imm?.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }

        return super.dispatchTouchEvent(ev)
    }

    protected fun clearData() {}

    protected fun destroyApplication() {
        finishAffinity()
    }

    //Navegability methods
    protected fun gotoTierWildDecksActivity() {
        val toast =
            Toast.makeText(applicationContext, "gotoTierWildDecksActivity", Toast.LENGTH_SHORT)
        toast.show()
    }

    protected fun gotoTierStandardDecksActivity() {
        val toast =
            Toast.makeText(applicationContext, "gotoTierStandardDecksActivity", Toast.LENGTH_SHORT)
        toast.show()
    }

    protected fun gotoYourDecksActivity() {
        val toast = Toast.makeText(applicationContext, "gotoYourDecksActivity", Toast.LENGTH_SHORT)
        toast.show()
    }

    protected fun gotoShowCardsActivity() {
        val toast = Toast.makeText(applicationContext, "gotoShowCardsActivity", Toast.LENGTH_SHORT)
        toast.show()
    }

    protected fun gotoSettingsActivity() {
        val toast = Toast.makeText(applicationContext, "gotoSettingsActivity", Toast.LENGTH_SHORT)
        toast.show()
    }

    protected fun gotoCreateDeckActivity() {
        val toast = Toast.makeText(applicationContext, "gotoCreateDeckActivity", Toast.LENGTH_SHORT)
        toast.show()
    }

}