package com.michelbarbosa.hdm_hearthstone_dust_manager.ui.activities

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.michelbarbosa.hdm_hearthstone_dust_manager.R
import kotlinx.android.synthetic.main.activity_base.*

open class MainActivity : BaseActivity(){
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setToolbar()
        setBottomNavgationView()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_settings ->
                gotoSettingsActivity()
            R.id.action_testes ->
                gotoShowCardsActivity()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setBottomNavgationView() {
        navigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.action_yourDecks ->
                    gotoYourDecksActivity()
                R.id.action_tierStandardDecks ->
                    gotoTierStandardDecksActivity()
                R.id.action_tierWildDecks ->
                    gotoTierWildDecksActivity()
            }
            true
        }
    }

}