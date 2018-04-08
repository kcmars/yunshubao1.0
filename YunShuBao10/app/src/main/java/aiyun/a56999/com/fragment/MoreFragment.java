package aiyun.a56999.com.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import aiyun.a56999.com.R;

/**
 * Created by zhangping on 2018/4/2.
 */

public class MoreFragment extends BaseFragment {

    private View mView;//根视图

    public MoreFragment() {
    }

    @SuppressWarnings("unused")
    public static MoreFragment newInstance() {
        MoreFragment fragment = new MoreFragment();
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
        mView = inflater.inflate(R.layout.fragment_more, container, false);
        return mView;
    }

}
