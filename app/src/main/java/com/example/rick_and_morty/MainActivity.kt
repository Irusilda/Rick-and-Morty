package com.example.rick_and_morty

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.rick_and_morty.databinding.ActivityMainBinding
import com.example.rick_and_morty.fragments.CharacterFragment
import com.example.rick_and_morty.fragments.DetailsCharacterFragment
import com.example.rick_and_morty.fragments.EpisodesFragment
import com.example.rick_and_morty.fragments.LocationsFragment

class MainActivity : AppCompatActivity(), Navigator {

    lateinit var binding: ActivityMainBinding
    lateinit var toolbar: Toolbar

    private val currentFragment: Fragment
        get() = supportFragmentManager.findFragmentById(R.id.place_holder)!!

    private val fragmentListener = object : FragmentManager.FragmentLifecycleCallbacks() {
        override fun onFragmentViewCreated(
            fm: FragmentManager,
            f: Fragment,
            v: View,
            savedInstanceState: Bundle?
        ) {
            super.onFragmentViewCreated(fm, f, v, savedInstanceState)
            updateUi()
        }
    }

    private var keep = true
    private val delay = 2000L
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {

        val splashScreen = installSplashScreen()
        Log.d("MyLog", "$splashScreen")
        splashScreen.setKeepOnScreenCondition { keep }
        handler.postDelayed({
            keep = false
            showCharacterFragment()
            setSelectedColor()
        }, delay)

        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        Log.d("MyLog", "onCreate")

        toolbar = binding.root.findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        binding.bottomNavigation.setOnItemSelectedListener { it ->
            it.isChecked = true
            setSelectedColor()
            when (it.itemId) {
                R.id.characters -> showCharacterFragment()
                R.id.locations -> showLocationsFragment()
                R.id.episodes -> showEpisodesFragment()
            }
            true
        }
        supportFragmentManager.registerFragmentLifecycleCallbacks(fragmentListener, false)
    }

    private fun updateUi() {
        val fragment = currentFragment
        if (fragment is CustomTitle) {
            toolbar.title = getString(fragment.getTitleRes())
        } else toolbar.title = getString(R.string.app_name)
    }

    private fun launchFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.place_holder, fragment)
            .commit()
    }

    override fun showCharacterFragment() {
        launchFragment(CharacterFragment.newInstance())
    }

    override fun showLocationsFragment() {
        launchFragment(LocationsFragment.newInstance())
    }

    override fun showEpisodesFragment() {
        launchFragment(EpisodesFragment.newInstance())
    }

    override fun backPressed() {
        val callBack = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (currentFragment is CharacterFragment)
                    finish()
                else {
                    launchFragment(CharacterFragment.newInstance())
                }
            }
        }
        onBackPressedDispatcher.addCallback(this, callBack)
    }

    override fun showDetailCharacterFragment() {
        launchFragment(DetailsCharacterFragment.newInstance())
    }

    override fun onDestroy() {
        super.onDestroy()
        supportFragmentManager.unregisterFragmentLifecycleCallbacks(fragmentListener)
    }

    private fun setSelectedColor() {
        with(binding.bottomNavigation) {
            val selectedColor = ContextCompat.getColorStateList(
                this@MainActivity,
                R.color.bottom_nav_selected_color
            )
            itemTextColor = selectedColor
            itemIconTintList = selectedColor
        }
    }

}