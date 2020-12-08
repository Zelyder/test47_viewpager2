package ru.webanimal.test47_viewpager2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class OnboardingPageFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.item_onboarding_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            view.findViewById<TextView>(R.id.tvOnboardingPageTitle).text = it.getString(KEY_ONBOARDING_TITLE)
                ?: getString(R.string.onboarding_page_title_sample)

            view.findViewById<TextView>(R.id.tvOnboardingPageSubtitle).text = it.getString(KEY_ONBOARDING_SUBTITLE)
                ?: getString(R.string.onboarding_page_subtitle_sample)
        }
    }

    companion object {
        fun create(title: String, subtitle: String): OnboardingPageFragment {
            return OnboardingPageFragment().apply {
                arguments = Bundle().apply {
                    putString(KEY_ONBOARDING_TITLE, title)
                    putString(KEY_ONBOARDING_SUBTITLE, subtitle)
                }
            }
        }
    }
}

private const val KEY_ONBOARDING_TITLE = "KEY_ONBOARDING_TITLE"
private const val KEY_ONBOARDING_SUBTITLE = "KEY_ONBOARDING_SUBTITLE"