package com.example.soptseminar3

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Base64
import android.util.Log
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.soptseminar3.data.App
import com.example.soptseminar3.kakaoBook.Mypage
import com.example.soptseminar3.login.Login
import kotlinx.android.synthetic.main.activity_main.*
import java.security.MessageDigest


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //getHashKey(applicationContext)

        viewPager_main.adapter=MainPagerAdapter(supportFragmentManager)
        //viewPager_main.offscreenPageLimit=2
        viewPager_main.addOnPageChangeListener(object:ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {
            }

            @SuppressLint("MissingSuperCall")
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                bt_navigation.menu.getItem(position).isChecked=true
            }
        })

        bt_navigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.menu_home -> viewPager_main.currentItem=0
                R.id.menu_book->viewPager_main.currentItem=1
                R.id.menu_mypage->viewPager_main.currentItem=2
            }
            true
        }

    }

    override fun onStart() {
        super.onStart()
        //login data가 없으면 login페이지로
        if(App.prefs.id.isNullOrBlank()|| App.prefs.pw.isNullOrBlank()){
            val intent= Intent(this, Login::class.java)
            startActivity(intent)
        }
    }

}
//adapter
class MainPagerAdapter(fm: FragmentManager):
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){
    override fun getItem(position: Int): Fragment {
        return when(position){
            0->Home()
            1->Webtoon()
            else-> Mypage()
        }
    }

    override fun getCount(): Int {
        return 3
    }
}

@Nullable
fun getHashKey(context: Context): String? {
    val TAG = "KeyHash"
    var keyHash: String? = null
    try {
        val info: PackageInfo = context.getPackageManager()
            .getPackageInfo(context.getPackageName(), PackageManager.GET_SIGNATURES)
        for (signature in info.signatures) {
            var md: MessageDigest
            md = MessageDigest.getInstance("SHA")
            md.update(signature.toByteArray())
            keyHash = String(Base64.encode(md.digest(), 0))
            Log.d(TAG, keyHash)
        }
    } catch (e: Exception) {
        Log.e("name not found", e.toString())
    }
    return keyHash
}

