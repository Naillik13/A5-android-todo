package iimdemo.killiangalea.com.todolist.presentation.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import iimdemo.killiangalea.com.todolist.R
import iimdemo.killiangalea.com.todolist.domain.SharedPreferencesUtils

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        SharedPreferencesUtils.initSharedPreferences(this)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.mainActivityFragmentHost) as NavHostFragment

        navController = navHostFragment.navController

        navigateToTodoFragment()

    }

    fun navigateTo(actionId : Int) {
        navController.navigate(actionId)
    }

    fun navigateToTodoFragment() {
        if (!(SharedPreferencesUtils.getFirstname().isNullOrBlank() && SharedPreferencesUtils.getLastame().isNullOrBlank())) {
            navController.navigate(R.id.action_loginFragment_to_todoFragment)
        }
    }
}