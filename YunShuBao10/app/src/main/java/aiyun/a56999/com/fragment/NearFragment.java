package aiyun.a56999.com.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import aiyun.a56999.com.R;

/**
 * Created by zhangping on 2018/4/2.
 */

public class NearFragment extends BaseFragment {

    private View mView;//根视图

    public NearFragment() {
    }

    @SuppressWarnings("unused")
    public static NearFragment newInstance() {
        NearFragment fragment = new NearFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_near, container, false);
        return mView;
    }
}
