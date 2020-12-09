package ru.webanimal.test47_viewpager2

import android.content.Context
import android.os.Bundle
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
    
        skipButton = view.findViewById<Button>(R.id.btnOnboardingButton)?.apply {
            setOnClickListener {
            
            }
        }

        val avatars = arrayOf(
            R.drawable.img_portrait_page_01,
            R.drawable.img_portrait_page_02,
            R.drawable.img_portrait_page_03,
        )
        val titles = resources.getStringArray(R.array.onboarding_page_titles)
        val subtitles = resources.getStringArray(R.array.onboarding_page_subtitles)
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