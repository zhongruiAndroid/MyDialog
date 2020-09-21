package com.github.mydialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public abstract class BaseTheDialogFragment extends AppCompatDialogFragment implements View.OnClickListener {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new TheDialog(this.getContext(), this.getTheme());
    }
    @Override
    public TheDialog getDialog() {
        return (TheDialog) super.getDialog();
    }
    @Override
    public void show(FragmentManager manager, String tag) {
        super.showNow(manager, tag);
    }
    /***************************************************/
    public void show(FragmentManager manager) {
        super.showNow(manager,"BaseDialogFragment");
    }
    public void show(FragmentActivity activity) {
        if(activity==null||activity.isFinishing()){
            return;
        }
        super.showNow(activity.getSupportFragmentManager(),"BaseDialogFragment");
    }
    public void show(Fragment fragment) {
        if(fragment==null){
            return;
        }
        FragmentActivity activity = fragment.getActivity();
        if(activity.isFinishing()){
            return;
        }
        FragmentManager childFragmentManager = fragment.getChildFragmentManager();
        if(childFragmentManager==null){
            return;
        }
        super.showNow(childFragmentManager,"BaseDialogFragment");
    }


    @Override
    public void dismiss() {
        super.dismissAllowingStateLoss();
    }

    private Long clickTimeFlag;
    protected View mView;
    private FragmentActivity mActivity;
    public abstract @LayoutRes int getContentView();
    public View getContentLayout() {
        TextView textView = new TextView(mActivity);
        textView.setText(this.getClass().getSimpleName()+":getContentLayout()");
        return textView;
    }
    public abstract void initView();

    public long getNoDoubleClickInterval() {
        return 900;
    }

    public abstract void onNoDoubleClick(View v);

    public void onViewClick(View v) {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        clickTimeFlag = new Long(hashCode());
        mActivity = getActivity();
        super.onCreate(savedInstanceState);
    }
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int contentViewId = getContentView();
        if (contentViewId > 0) {
            mView = inflater.inflate(contentViewId, container, false);
        } else {
            mView = getContentLayout();
        }
        return mView;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }
    @Override
    public final void onClick(View v) {
        onViewClick(v);
        boolean fastClick = ClickTools.get().isFastClick(clickTimeFlag, v, getNoDoubleClickInterval());
        if (!fastClick) {
            onNoDoubleClick(v);
        }
    }
    @Override
    public void onPause() {
        super.onPause();
        ClickTools.get().clearLastClickTime(clickTimeFlag);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ClickTools.get().removeLastClickTime(clickTimeFlag);
    }
}
