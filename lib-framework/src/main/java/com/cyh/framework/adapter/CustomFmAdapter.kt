package com.cyh.framework.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import java.util.ArrayList

/**
 * Description:
 * @Author: chenyihui
 * Date: 2022/3/11
 */
class CustomFmAdapter(
    fm: FragmentManager?,
    private val tabFragments: ArrayList<Fragment>,
    private val titles: Array<String>
) : androidx.fragment.app.FragmentPagerAdapter(fm!!, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getCount(): Int {
        return tabFragments.size
    }

    override fun getItem(position: Int): Fragment {
        return tabFragments[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titles[position]
    }
}