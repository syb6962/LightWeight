package com.example.lightweight

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.lightweight.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.nav_host_container
        ) as NavHostFragment
        navController = navHostFragment.navController

//        val navController = findNavController(R.id.nav_host_container)

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
//        val appBarConfiguration = AppBarConfiguration(
//            setOf(
//                R.id.calendarFragment, R.id.dailyWorkoutLogFragment, R.id.notificationsFragment
//            )
//        )

        //TODO: sealed 클래스로 뭔가를 할수있지않을지? 안드 QnA 질문한것 참고
        navController.addOnDestinationChangedListener { _, destination, _ -> // 바텀메뉴와 앱바를 컨트롤하기 위함
            when(destination.id) {
                R.id.workoutListTabFragment -> {
//                    bottomNav.visibility = View.GONE
//                    supportActionBar?.hide() // 앱바 숨김
                }
                else ->
                    navView.visibility = View.VISIBLE
            }
        }

//        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}