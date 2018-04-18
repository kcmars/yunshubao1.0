package aiyun.a56999.com.activity;

import android.databinding.DataBindingUtil;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import aiyun.a56999.com.R;
import aiyun.a56999.com.databinding.ActivityHomeBinding;
import aiyun.a56999.com.fragment.BaseFragment;
import aiyun.a56999.com.fragment.HomeFragment;
import aiyun.a56999.com.fragment.MeFragment;
import aiyun.a56999.com.fragment.MoreFragment;
import aiyun.a56999.com.fragment.NearFragment;
import aiyun.a56999.com.utils.VoiceUtil;

public class HomeActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {

    private ActivityHomeBinding mBinding;
    private TabLayout mFooterTab;
    //首先需要先实例好n个全局Fragment
    private BaseFragment currentFragment = new BaseFragment();
    private HomeFragment mHomeFrag;
    private NearFragment mNearFrag;
    private MoreFragment mMoreFrag;
    private MeFragment mMeFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            mHomeFrag = (HomeFragment) getSupportFragmentManager().findFragmentByTag("mHomeFrag");
            mNearFrag = (NearFragment) getSupportFragmentManager().findFragmentByTag("mNearFrag");
            mMoreFrag = (MoreFragment) getSupportFragmentManager().findFragmentByTag("mMoreFrag");
            mMeFrag = (MeFragment) getSupportFragmentManager().findFragmentByTag("mMeFrag");
        }
        if (mHomeFrag == null) {
            mHomeFrag = HomeFragment.newInstance();
        }
        if (mNearFrag == null) {
            mNearFrag = NearFragment.newInstance();
        }
        if (mMoreFrag == null) {
            mMoreFrag = MoreFragment.newInstance();
        }
        if (mMeFrag == null) {
            mMeFrag = MeFragment.newInstance();
        }
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        setSupportActionBar(mBinding.toolbar);

        mFooterTab = (TabLayout) mBinding.footerTab;

        TabLayout.Tab tab1 = mFooterTab.newTab().setCustomView(R.layout.layout_tab_item1);
        TabLayout.Tab tab2 = mFooterTab.newTab().setCustomView(R.layout.layout_tab_item2);
        TabLayout.Tab tab3 = mFooterTab.newTab().setCustomView(R.layout.layout_tab_item3);
        TabLayout.Tab tab4 = mFooterTab.newTab().setCustomView(R.layout.layout_tab_item4);
        mFooterTab.addTab(tab1);
        mFooterTab.addTab(tab2);
        mFooterTab.addTab(tab3);
        mFooterTab.addTab(tab4);
        tab1.select();
        mFooterTab.addOnTabSelectedListener(this);

        switchFragment(mHomeFrag, "mHomeFrag").commit();

        //语音播报
//        VoiceUtil.getInstance(getApplicationContext()).startSpeakingNow();

    }

    private FragmentTransaction switchFragment(BaseFragment targetFragment, String tag) {

        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction();
        if (!targetFragment.isAdded()) {
            //第一次使用switchFragment()时currentFragment为null，所以要判断一下
            if (currentFragment != null) {
                transaction.hide(currentFragment);
            }
            transaction.add(R.id.layout_content, targetFragment, tag);

        } else {
            transaction.hide(currentFragment).show(targetFragment);

        }
        currentFragment = targetFragment;
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        return transaction;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        switch (tab.getPosition()) {
            case 0:
                switchFragment(mHomeFrag, "mHomeFrag").commit();
                break;
            case 1:
                switchFragment(mNearFrag, "mNearFrag").commit();
                break;
            case 2:
                switchFragment(mMoreFrag, "mMoreFrag").commit();
                break;
            case 3:
                switchFragment(mMeFrag, "mMeFrag").commit();
                break;
            default:break;
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
