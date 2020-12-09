package ru.webanimal.test47_viewpager2

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2

class OnboardingHostFragment : Fragment() {
    
    private var exitListener: ExitClickListener? = null
    private var pager: ViewPager2? = null
    private var skipButton: Button? = null
    private var pagesCount = 0
    private var currentPage = 0
    
    override fun onAttach(context: Context) {
        super.onAttach(context)
    
        if (context is ExitClickListener) {
            exitListener = context
        }
    }
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.onboarding_host_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        pager = view.findViewById(R.id.pager)
        pager?.adapter = OnboardingAdapter(this, preparePages())
        pager?.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
        
                currentPage = position
                val textResId = if (currentPage >= (pagesCount - 1)) {
                    R.string.onboarding_page_button_close
            
                } else {
                    R.string.onboarding_page_button_skip
                }
                skipButton?.text = getString(textResId)
                Log.d("TAG", "TEST::onPageSelected pos:$currentPage")
            }
        })
        
        skipButton = view.findViewById<Button>(R.id.btnOnboardingButton)?.apply { setOnClickListener {
            when {
                currentPage >= (pagesCount - 1) -> exitListener?.onExit()
                else -> pager?.setCurrentItem(pagesCount - 1, true)
            }
        }}
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
        
        pagesCount = pages.size
        
        return pages
    }
    
    override fun onDetach() {
        exitListener = null
        pager = null
        skipButton = null
        
        super.onDetach()
    }
    
    interface ExitClickListener {
        fun onExit()
    }
    
    companion object {
        fun create(): OnboardingHostFragment = OnboardingHostFragment()
    }
}