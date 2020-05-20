package com.sofascorezadatak.sofascorezadatak

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.sofascorezadatak.sofascorezadatak.contributors.ContributorsFragment
import com.sofascorezadatak.sofascorezadatak.issues.IssuesFragment
import com.sofascorezadatak.sofascorezadatak.model.Repository
import com.sofascorezadatak.sofascorezadatak.pulls.PullsFragment

class ViewPagerAdapter(private val mContext: Context, fm: FragmentManager?, private var repository: Repository) : FragmentPagerAdapter(fm!!) {
    private val tabsCount: Int = 3

    override fun getItem(position: Int): Fragment {
        if (position == 0) {
            return ContributorsFragment.newInstance(mContext, repository)
        }
        else if (position == 1)  {
            return IssuesFragment.newInstance(mContext, repository)
        }
        else {
            return PullsFragment.newInstance(mContext, repository)
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return TAB_TITLES[position]
    }

    override fun getCount(): Int {
        return tabsCount
    }

    companion object {
        private lateinit var TAB_TITLES: Array<String?>
    }

    init {
        TAB_TITLES = arrayOfNulls(tabsCount)
        TAB_TITLES[0] = "Contributors"
        TAB_TITLES[1] = "Issues"
        TAB_TITLES[2] = "Pulls"
    }
}