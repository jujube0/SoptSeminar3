package com.example.soptseminar3


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.soptseminar3.webtoon.Monday
import com.example.soptseminar3.webtoon.Tuesday
import com.example.soptseminar3.webtoon.Wednesday
import kotlinx.android.synthetic.main.fragment_webtoon.*

/**
 * A simple [Fragment] subclass.
 */
class Webtoon : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_webtoon, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewpager_webt.adapter=PagerAdapter(childFragmentManager)
        tabs.setupWithViewPager(viewpager_webt)
    }
}

private class PagerAdapter(fm:FragmentManager):
        FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){
    override fun getItem(position: Int): Fragment {
        return when(position){
            0->Monday()
            1->Tuesday()
            else->Wednesday()
        }
    }
    override fun getCount(): Int {
        return 3
    }
    override fun getPageTitle(position: Int): CharSequence? {
        when(position){
            0->return "monday"
            1->return "tuesday"
            else-> return "wednesday"
        }
    }
}
