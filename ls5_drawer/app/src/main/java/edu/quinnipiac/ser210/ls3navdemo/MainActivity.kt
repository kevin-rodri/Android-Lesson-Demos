package edu.quinnipiac.ser210.ls3navdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView;
import android.widget.ShareActionProvider
import android.widget.Toast
import android.widget.Toolbar
import androidx.core.view.MenuItemCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() , SearchView.OnQueryTextListener,
    android.widget.SearchView.OnQueryTextListener {
    private lateinit var navController: NavController
    private lateinit var  appBarConfiguration: AppBarConfiguration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // adding support to action bar
        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        // add controls to make it controllable with navigation controller
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
        val navigationView = findViewById<NavigationView>(R.id.navigationView)

        // allows to access the controller
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        // access appBarConfiguartion
        appBarConfiguration = AppBarConfiguration(setOf(R.id.mainFragment), drawerLayout)
        setupActionBarWithNavController(navController!!, appBarConfiguration)

        // logic for navigationview
        findViewById<NavigationView>(R.id.navigationView).setupWithNavController(navController!!)

    }
    // support for burger menu
    override fun onSupportNavigateUp(): Boolean {
        return navController?.navigateUp(appBarConfiguration)!!
    }

    // method that displays the menu options to the toolbar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val returnValue =  super.onCreateOptionsMenu(menu)
        // inflate the menu options to the menu
        menuInflater.inflate(R.menu.main, menu)
        // get search value
        val search = menu?.findItem(R.id.action_search)
        val searchView : SearchView? = search?.actionView as SearchView?
        searchView?.apply{
            isSubmitButtonEnabled = true
            setOnQueryTextListener(this@MainActivity)
        }

        return returnValue
    }



    // function that will check the items that are clicked on the menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        return when(id){
            R.id.action_settings -> {
                Toast.makeText(this, "Here are my setting", Toast.LENGTH_SHORT).show()
                true
            }
            // get the share action Provider
            R.id.share -> {

                val shareActionProvider: ShareActionProvider? = MenuItemCompat.getActionProvider(item) as ShareActionProvider?
                val intent = Intent(Intent.ACTION_SEND)
                // type of information being sent
                intent.type = "text/plain"
                intent.putExtra(Intent.EXTRA_TEXT, "This is a message")

                // share intent
                if (shareActionProvider != null){
                    shareActionProvider.setShareIntent(intent)
                }
                true
            }
            else -> NavigationUI.onNavDestinationSelected(item!!, navController) || super.onOptionsItemSelected(item)

        }
    }

    override fun onQueryTextSubmit(p0: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        Toast.makeText(this, "Searching", Toast.LENGTH_SHORT).show()
        return true
    }
}