package ro.sapientia.android_9eloadas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Set up bottom navigation
        val navController = findNavController(this, R.id.myNavHostFragment)
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        bottomNav?.setupWithNavController(navController)

        // When BottomNavigationView does not work from XML binding!!!
//        bottomNav.setOnNavigationItemSelectedListener {
//            when(it.itemId){
//                R.id.listFragment->{
//                    navController.navigate(R.id.listFragment)
//                    true
//                }
//                R.id.detailFragment->{
//                    navController.navigate(R.id.detailFragment)
//                    true
//                }
//                R.id.wordFragment->{
//                    navController.navigate(R.id.wordFragment)
//                    true
//                }
//            }
//            true
//        }


    }
}