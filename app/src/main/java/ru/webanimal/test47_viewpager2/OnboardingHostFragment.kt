package ru.webanimal.test47_viewpager2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2

class OnboardingHostFragment : Fragment() {
    
    private var pager: ViewPager2? = null
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.viewpager_host_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        pager = view.findViewById(R.id.pager)
        pager?.adapter = OnboardingAdapter(this, preparePages())
    }
    
    private fun preparePages(): List<Page> {
        val avatars = arrayOf(
                R.drawable.img_portrait_page_01,
                R.drawable.img_portrait_page_02,
                R.drawable.img_portrait_page_03,
        )
        val titles = resources.getStringArray(R.array.onboarding_page_titles)
        val subtitles = resources.getStringArray(R.array.onboarding_page_subtitles)
        
        var pages: MutableList<Page> = mutableListOf()
        for (i in avatars.indices) {
            pages.add(Page(avatars[i], titles[i], subtitles[i]))
        }
        return pages
    }
    
    companion object {
        fun create(): OnboardingHostFragment = OnboardingHostFragment()
    }
}