package ru.webanimal.test47_viewpager2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity(), OnboardingHostFragment.ExitClickListener {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		if (savedInstanceState == null) {
			supportFragmentManager.beginTransaction()
				.replace(R.id.container, OnboardingHostFragment.create())
				.commit()
		}
	}
	
	override fun onExit() {
		onBackPressed()
	}
}