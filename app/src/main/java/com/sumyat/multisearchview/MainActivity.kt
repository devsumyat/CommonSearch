package com.sumyat.multisearchview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.view.Menu
import androidx.appcompat.widget.SearchView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    val CONTACT_QUERY_LOADER = 0
    val QUERY_KEY = "query"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpPage()
        if (getIntent() != null) {
            handleIntent(getIntent())
        }
    }

    fun setUpPage() {
        setSupportActionBar(toolbar)

        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(DemoTab(), "Tab1")
        adapter.addFragment(DemoTab(), "Tab2")
        adapter.addFragment(DemoTab(), "Tab3")
        adapter.addFragment(DemoTab(), "Tab4")
        adapter.addFragment(DemoTab(), "Tab5")
        adapter.addFragment(DemoTab(), "Tab6")
        adapter.addFragment(DemoTab(), "Tab7")
        adapter.addFragment(DemoTab(), "Tab8")
        viewPager.adapter = adapter
        viewPager.setPageTransformer(true, ZoomOutPageTransformer())
        tabs.setupWithViewPager(viewPager)
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
