package ru.webanimal.test47_viewpager2

import android.content.Context
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2

class OnboardingHostFragment : Fragment() {
    
    private var exitListener: ExitClickListener? = null
    private var pager: ViewPager2? = null
    private var skipButton: Button? = null
    private var indicatorsLayout: LinearLayout? = null
    private var indicators: MutableList<ImageView> = mutableListOf()
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
        
        // TODO 01: Create a Page data class
        // TODO 02: Prepare pages list and save pagesCount
        val avatars = arrayOf(
                R.drawable.img_portrait_page_01,
                R.drawable.img_portrait_page_02,
                R.drawable.img_portrait_page_03,
        )
        val titles = resources.getStringArray(R.array.onboarding_page_titles)
        val subtitles = resources.getStringArray(R.array.onboarding_page_subtitles)
        
        // TODO 03: Create OnboardingAdapter as a FragmentStateAdapter(hostFragment),
        //  pass host fragment and list of pages
        // TODO 04: Replace TextView in the host fragment's layout with ViewPager2
        // TODO 05: Find viewpager here, save in a property
        // TODO 06: Set the adapter
        // TODO 07: Register empty OnPageChangeCallback here
        
        // OnPageChangeCallback
        // TODO 10: Save currentPage index inside callback in the property
        // TODO 11: Create function to update skip button text depending on current position
        // TODO 12: Create function to update indicators depending on current position
        
        skipButton = view.findViewById<Button>(R.id.btnOnboardingButton)?.apply {
            setOnClickListener {
                // TODO 13: Depending on a current page position, setCurrentItem() pager or onExit()
            }
        }
    
        indicatorsLayout = view.findViewById(R.id.layIndicators)
        // TODO 08: Create shape drawables for selected and deselected indicators
        // TODO 09: Create a function to create and add indicator ImageViews to the indicators layout:
        //  - Create LinearLayout.LayoutParams with WRAP_CONTENT dimensions
        //  - Add margins to the params with Context.dpToPx(dp: Float) extension
        //  - For pagesCount create indicator ImageView, apply params, set shapes with setImageResource
        //  - Add indicator to indicators
        //  - Add indicator to layout with index
    }
    
    override fun onDetach() {
        exitListener = null
        pager = null
        skipButton = null
        indicatorsLayout = null
        
        super.onDetach()
    }
    
    interface ExitClickListener {
        fun onExit()
    }

    companion object {
        fun create(): OnboardingHostFragment = OnboardingHostFragment()
    }
}

private fun Context.dpToPx(dp: Float) = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dp,
        this.resources.displayMetrics
)