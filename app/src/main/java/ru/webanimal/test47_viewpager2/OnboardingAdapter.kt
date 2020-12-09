package ru.webanimal.test47_viewpager2

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class OnboardingAdapter(
		hostFragment: Fragment,
		private val pages: List<Page>
) : FragmentStateAdapter(hostFragment) {
	
	override fun getItemCount(): Int = pages.size
	
	override fun createFragment(position: Int): Fragment = OnboardingPageFragment.create(
			avatarResId = pages[position].avatarResId,
			title = pages[position].title,
			subtitle = pages[position].subtitle
	)
}