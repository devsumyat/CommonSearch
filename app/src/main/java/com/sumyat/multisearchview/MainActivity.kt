package com.sumyat.multisearchview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.view.Menu
import androidx.appcompat.widget.SearchView


class MainActivity : AppCompatActivity() {

    val CONTACT_QUERY_LOADER = 0
    val QUERY_KEY = "query"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (getIntent() != null) {
            handleIntent(getIntent())
        }
    }

    override fun onNewIntent(intent: Intent) {
        handleIntent(intent)
    }

    private fun handleIntent(intent: Intent) {
        if (Intent.ACTION_SEARCH == intent.action) {
            val query = intent.getStringExtra(SearchManager.QUERY)

            val bundle = Bundle()
            bundle.putString(QUERY_KEY, query)

//            val loaderCallbacks = ContactablesLoaderCallbacks(this)
//
//            // Start the loader with the new query, and an object that will handle all callbacks.
//            loaderManager.restartLoader(CONTACT_QUERY_LOADER, bundle, loaderCallbacks)
        }
    }

    override
    fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        // Inflate the menu; this adds items to the action bar if it is present.
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        (menu.findItem(R.id.search).actionView as SearchView).apply {
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
        }

        return true
    }

}
