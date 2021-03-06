package com.test.mydialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2018/5/11.
 */

public class SheetDialogFragment extends BottomSheetDialogFragment {
    private BottomSheetBehavior mBehavior;
    @Override
     public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog2, null);
         return view;//super.onCreateView(inflater, container, savedInstanceState);
     }
    @Override
    public void show(FragmentManager manager, String tag) {
        super.show(manager, tag);
    }

     /*    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        return super.onCreateDialog(savedInstanceState);
//        BottomSheetDialog dialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
//        View view = View.inflate(getContext(), R.layout.dialog_bottom_sheet, null);
//        dialog.setContentView(view);
//        mBehavior = BottomSheetBehavior.from((View) view.getParent());
//        return dialog;

        BottomSheetDialog sheetDialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog2, null);
        sheetDialog.setContentView(view);
        mBehavior = BottomSheetBehavior.from((View) view.getParent());
        return sheetDialog;
    }*/
    public static SheetDialogFragment newInstance() {
        Bundle args = new Bundle();
        SheetDialogFragment fragment = new SheetDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onStart() {
        super.onStart();
        //默认全屏展开
//        mBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }

    public void doclick(View v) {
        //点击任意布局关闭
//        mBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
    }
}
