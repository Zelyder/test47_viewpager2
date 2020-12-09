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
        
        pager = view.findViewById(R.id.pager)
        pager?.adapter = OnboardingAdapter(this, preparePages())
        pager?.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
        
                currentPage = position
                updateIndicators(currentPage)
                updateSkipButton(currentPage)
            }
        })
        
        skipButton = view.findViewById<Button>(R.id.btnOnboardingButton)?.apply { setOnClickListener {
            when {
                currentPage >= (pagesCount - 1) -> exitListener?.onExit()
                else -> pager?.setCurrentItem(pagesCount - 1, true)
            }
        }}
    
        indicatorsLayout = view.findViewById(R.id.layIndicators)
        initIndicators(indicatorsLayout)
    }
    
    private fun initIndicators(layout: LinearLayout?) {
        layout?.let {
            val context = it.context
            it.removeAllViews()
            val params = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(
                    context.dpToPx(4f).toInt(),
                    context.dpToPx(4f).toInt(),
                    context.dpToPx(4f).toInt(),
                    context.dpToPx(4f).toInt()
            )
            for (i in 0 until pagesCount) {
                val indicator = ImageView(context).apply {
                    layoutParams = params
                    setImageResource(R.drawable.shape_pager_indicator_default)
                }
                indicators.add(indicator)
                layout.addView(indicator, i)
            }
        }
    }
    
    private fun updateIndicators(currentPage: Int) {
        var drawableResId = 0
        for (i in indicators.indices) {
            drawableResId = if (i == currentPage) {
                R.drawable.shape_pager_indicator_selected
        
            } else {
                R.drawable.shape_pager_indicator_default
            }
            indicators[i].setImageResource(drawableResId)
        }
    }
    
    private fun updateSkipButton(currentPage: Int) {
        val textResId = if (currentPage >= (pagesCount - 1)) {
            R.string.onboarding_page_button_close
        
        } else {
            R.string.onboarding_page_button_skip
        }
        skipButton?.text = getString(textResId)
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