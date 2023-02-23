package com.github.mydialog;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.arch.lifecycle.GenericLifecycleObserver;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatDialog;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@SuppressLint("RestrictedApi")
public class TheDialog extends AppCompatDialog implements GenericLifecycleObserver {


    private int width = WindowManager.LayoutParams.WRAP_CONTENT; //(int) (getScreenWidth() * 0.7);
    private int height = WindowManager.LayoutParams.WRAP_CONTENT; //(int) (getScreenWidth() * 0.6);


    //    private int titleViewHeight = -1;
//    private int titleViewColor = -1;
    private boolean translucentStatus = false;
    private boolean useActStatusBarFontColor = false;
    private int backgroundColor = Color.WHITE;
    private int backgroundDrawableResId = -1;
    private Drawable backgroundDrawable;
    private int paddingLeft = -1;
    private int paddingTop = -1;
    private int paddingRight = -1;
    private int paddingBottom = -1;
    private int gravity = Gravity.CENTER;
    private float dimAmount = 0.3f;
    private float alpha = 1;
    private int leftRadius;
    private int topRadius;
    private int rightRadius;
    private int bottomRadius;

    private int animationId = -1;
    private boolean canMoveDialog;

    private boolean hideNavigation;
    private Window window;

    public TheDialog(@NonNull Context context) {
        super(context);
        setCanceledOnTouchOutside(false);
        initWindow();
    }

    public TheDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        setCanceledOnTouchOutside(false);
        initWindow();
    }

    public TheDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        setCanceledOnTouchOutside(false);
        initWindow();
    }

    private void initWindow() {
        window = getWindow();

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        setDrawable();

    }

    public TheDialog setWidth(int width) {
        this.width = width;
        return this;
    }

    public TheDialog setHeight(int height) {
        this.height = height;
        return this;
    }

    public TheDialog setFullWidth() {
        this.width = WindowManager.LayoutParams.MATCH_PARENT;
        return this;
    }

    public TheDialog setFullHeight() {
        this.height = WindowManager.LayoutParams.MATCH_PARENT;
        return this;
    }

    public TheDialog setRadius(int radius) {
        this.leftRadius = radius;
        this.topRadius = radius;
        this.rightRadius = radius;
        this.bottomRadius = radius;
        setDrawable();
        return this;
    }


    private void setDrawable() {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(backgroundColor);
        float[] fourRadius = new float[]{
                leftRadius, leftRadius,
                topRadius, topRadius,
                rightRadius, rightRadius,
                bottomRadius, bottomRadius};
        gradientDrawable.setCornerRadii(fourRadius);
        backgroundDrawable = gradientDrawable;
        window.setBackgroundDrawable(gradientDrawable);
    }

    public TheDialog setRadius(int leftRadius, int topRadius, int rightRadius, int bottomRadius) {
        this.leftRadius = leftRadius;
        this.topRadius = topRadius;
        this.rightRadius = rightRadius;
        this.bottomRadius = bottomRadius;
        setDrawable();
        return this;
    }

    public float getDimAmount() {
        return dimAmount;
    }

    public TheDialog setDimAmount(float dimAmount) {
        /*window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);全透明(包括状态栏)*/
        this.dimAmount = dimAmount;
        window.setDimAmount(this.dimAmount);
        return this;
    }

    public float getAlpha() {
        return this.alpha;
    }

    public TheDialog setAlpha(float alpha) {
        this.alpha = alpha;
        window.getAttributes().alpha = this.alpha;
        return this;
    }

    public Drawable getBackgroundDrawable() {
        return backgroundDrawable;
    }

    public TheDialog setBackgroundDrawable(Drawable backgroundDrawable) {
        this.backgroundDrawable = backgroundDrawable;
        window.setBackgroundDrawable(backgroundDrawable);
        return this;
    }

    public int getBackgroundColor() {
        return this.backgroundColor;
    }

    public TheDialog setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
        setDrawable();
        return this;
    }

    public int getBackgroundDrawableResource() {
        return this.backgroundDrawableResId;
    }

    public TheDialog setBackgroundDrawableResource(int resId) {
        this.backgroundDrawableResId = resId;
        window.setBackgroundDrawableResource(this.backgroundDrawableResId);
        return this;
    }

    public boolean getTranslucentStatus() {
        return this.translucentStatus;
    }

    public TheDialog setTranslucentStatus(boolean translucentStatus) {
        this.translucentStatus = translucentStatus;
        if (this.translucentStatus) {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        } else {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        return this;
    }

    public boolean isUseActStatusBarFontColor() {
        return useActStatusBarFontColor;
    }

    public void setUseActStatusBarFontColor(boolean useActStatusBarFontColor) {
        this.useActStatusBarFontColor = useActStatusBarFontColor;
        if (useActStatusBarFontColor) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        } else {
            window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        }
    }

    public TheDialog setPadding(int paddingLeft, int paddingTop, int paddingRight, int paddingBottom) {
        this.paddingLeft = paddingLeft;
        this.paddingTop = paddingTop;
        this.paddingRight = paddingRight;
        this.paddingBottom = paddingBottom;
        if (paddingLeft != -1 && paddingTop != -1 && paddingRight != -1 && paddingBottom != -1) {
            window.getDecorView().setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
        }
        return this;
    }


    public TheDialog setPadding(int padding) {
        return setPadding(padding, padding, padding, padding);
    }

    public int getGravity() {
        return gravity;
    }

    public TheDialog setGravity(int gravity) {
        this.gravity = gravity;
        window.setGravity(gravity);
        if (animationId == -1) {
            setAnimation(-1);
        }
        return this;
    }

    public int getAnimationId() {
        return animationId;
    }

    public void setAnimationId(int animationId) {
        if (this.animationId == animationId) {
            return;
        }
        this.animationId = animationId;
        setAnimation(animationId);
    }

    public boolean isCanMoveDialog() {
        return canMoveDialog;
    }

    public TheDialog setCanMoveDialog(boolean canMoveDialog) {
        this.canMoveDialog = canMoveDialog;
        return this;
    }

    public boolean isHideNavigation() {
        return hideNavigation;
    }

    public TheDialog setHideNavigation(boolean hideNavigation) {
        this.hideNavigation = hideNavigation;
        return this;
    }

    public TheDialog setAnimation(int resId) {
        if (resId == -1) {
            switch (gravity) {
                case Gravity.TOP:
                    window.setWindowAnimations(R.style.MyDialogAnimationTop);
                    break;
                case Gravity.BOTTOM:
                    window.setWindowAnimations(R.style.MyDialogAnimationBottom);
                    break;
            }
        } else {
            this.animationId = resId;
            window.setWindowAnimations(animationId);
        }
        return this;
    }

    /*dialog整体居中时，是否考虑状态栏高度*/
    public boolean onDialogCenterCalculateStatusBarHeight() {
        return false;
    }

    public void show() {
        WindowManager.LayoutParams lp = window.getAttributes();
        if (this.width != WindowManager.LayoutParams.WRAP_CONTENT) {
            lp.width = width;
        }
        if (this.height != WindowManager.LayoutParams.WRAP_CONTENT) {
            lp.height = height;
        }
        if (onDialogCenterCalculateStatusBarHeight()) {
            lp.y -= getStatusBarHeight(getContext()) / 2;
        }
        if (width != WindowManager.LayoutParams.WRAP_CONTENT || height != WindowManager.LayoutParams.WRAP_CONTENT) {
            window.setAttributes(lp);
        }

        superShow();
    }

    private int viewXOffset;
    private int viewYOffset;

    public void setXOffset(int xOffset) {
        this.viewXOffset = xOffset;
    }

    public void setYOffset(int yOffset) {
        this.viewYOffset = yOffset;
    }

    public void addXOffset(int xOffset) {
        this.viewXOffset += xOffset;
    }

    public void addYOffset(int yOffset) {
        this.viewYOffset += yOffset;
    }

    public static final int left_left = 1;
    public static final int left_right = 2;
    public static final int center = 3;
    public static final int right_left = 4;
    public static final int right_right = 5;


    public static final int top_top = 6;
    public static final int top_bottom = 7;
    public static final int bottom_top = 8;
    public static final int bottom_bottom = 9;

    @IntDef({left_left,
            left_right,
            center,
            right_left,
            right_right,
            top_top,
            top_bottom,
            bottom_top,
            bottom_bottom})
    @Retention(RetentionPolicy.SOURCE)
    public @interface showMode {
    }

    /*dialog相对某个view的位置显示时，是否需要考虑状态栏高度，true:dialogY轴偏移量整体上移*/
    public boolean onDialogShowCalculateStatusBarHeight() {
        return false;
    }

    private int statusBarHeight = 0;

    public int getStatusBarHeight(Context context) {
        if (this.statusBarHeight > 0) {
            return this.statusBarHeight;
        }
        statusBarHeight = 0;
        try {
            int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
            if (resourceId > 0) {
                statusBarHeight = context.getResources().getDimensionPixelOffset(resourceId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusBarHeight;
    }

    //    public static final int right_right=5;
    public void showAsLeft(View anchor, int xOffset, int yOffset, @showMode int showMode) {
        showAsLeftOrRight(anchor, xOffset, yOffset, true, showMode);
    }

    public void showAsRight(View anchor, int xOffset, int yOffset, @showMode int showMode) {
        showAsLeftOrRight(anchor, xOffset, yOffset, false, showMode);
    }

    private void showAsLeftOrRight(View anchor, int xOffset, int yOffset, boolean isLeft, @showMode int showMode) {
        if (anchor == null || width < 0) {
            show();
            return;
        }
        int[] location = new int[2];
        anchor.getLocationOnScreen(location);
        int x = location[0] + xOffset;
        int y = location[1] + yOffset;
        if (!isFullScreen(anchor) || onDialogShowCalculateStatusBarHeight()) {
            y -= getStatusBarHeight(getContext());
        }
        if (isLeft) {
            viewXOffset = x - this.width;
        } else {
            viewXOffset = x + anchor.getWidth();
        }
        int anchorHeight;
        switch (showMode) {
            case top_top:
                viewYOffset = y;
                break;
            case top_bottom:
                viewYOffset = y - (height < 0 ? 0 : height);
                break;
            case center:
                anchorHeight = anchor.getHeight();
                if (height > 0 && anchorHeight > 0) {
                    viewYOffset = y - (height - anchorHeight) / 2;
                } else {
                    show();
                    return;
                }
                break;
            case bottom_top:
                anchorHeight = anchor.getHeight();
                viewYOffset = y + anchorHeight;
                break;
            case bottom_bottom:
                anchorHeight = anchor.getHeight();
                if (height > 0 && anchorHeight > 0) {
                    viewYOffset = y - (height - anchorHeight);
                } else {
                    show();
                    return;
                }
                break;
        }
        if (setAttributeForMode()) {
            return;
        }
        superShow();
    }

    public void showAsTop(View anchor, int xOffset, int yOffset, @showMode int showMode) {
        showAsTopOrBottom(anchor, xOffset, yOffset, true, showMode);
    }

    public void showAsBottom(View anchor, int xOffset, int yOffset, @showMode int showMode) {
        showAsTopOrBottom(anchor, xOffset, yOffset, false, showMode);
    }

    private void showAsTopOrBottom(View anchor, int xOffset, int yOffset, boolean isAsTop, @showMode int showMode) {
        if (anchor == null) {
            show();
            return;
        }
        int[] location = new int[2];
        anchor.getLocationOnScreen(location);
        int x = location[0] + xOffset;
        int y = location[1] + yOffset;
        if (!isFullScreen(anchor) || onDialogShowCalculateStatusBarHeight()) {
            y -= getStatusBarHeight(getContext());
        }
        int anchorWidth;
        int anchorHeight = anchor.getHeight();
        switch (showMode) {
            case left_left:
                viewXOffset = x;
                if (isAsTop) {
                    viewYOffset = y - (height < 0 ? 0 : height);
                } else {
                    viewYOffset = y + anchorHeight;
                }
                break;
            case left_right:
                viewXOffset = x - (width < 0 ? 0 : width);
                if (isAsTop) {
                    viewYOffset = y - (height < 0 ? 0 : height);
                } else {
                    viewYOffset = y + anchorHeight;
                }
                break;
            case center:
                anchorWidth = anchor.getWidth();
                if (anchorWidth > 0 && this.width > 0) {
                    viewXOffset = x - (this.width - anchorWidth) / 2;
                    if (isAsTop) {
                        viewYOffset = y - (height < 0 ? 0 : height);
                    } else {
                        viewYOffset = y + anchorHeight;
                    }
                } else {
                    show();
                    return;
                }
                break;
            case right_left:
                anchorWidth = anchor.getWidth();
                viewXOffset = x + (anchorWidth < 0 ? 0 : anchorWidth);
                if (isAsTop) {
                    viewYOffset = y - (height < 0 ? 0 : height);
                } else {
                    viewYOffset = y + anchorHeight;
                }
                break;
            case right_right:
                anchorWidth = anchor.getWidth();
                if (anchorWidth > 0 && this.width > 0) {
                    viewXOffset = x - (this.width - anchorWidth);
                    if (isAsTop) {
                        viewYOffset = y - (height < 0 ? 0 : height);
                    } else {
                        viewYOffset = y + anchorHeight;
                    }
                } else {
                    show();
                    return;
                }
                break;
        }
        if (setAttributeForMode()) {
            return;
        }
        superShow();
    }

    private void superShow() {
        if (isHideNavigation()) {
            if (getWindow() != null) {
                this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
                View decorView = getWindow().getDecorView();
                decorView.setSystemUiVisibility(decorView.getSystemUiVisibility()
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_IMMERSIVE
                );
            }
        }
        if (isDestroyed(getContext())) {
            return;
        }
        try {
            lifecycleAddObserver();
            super.show();
        } catch (Exception e) {
        }
        if (isHideNavigation()) {
            if (getWindow() != null) {
                this.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
            }
        }
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onStateChanged(LifecycleOwner source, Lifecycle.Event event) {
        if (event == Lifecycle.Event.ON_DESTROY) {
            try {
                source.getLifecycle().removeObserver(TheDialog.this);
                super.dismiss();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void lifecycleAddObserver() {
        Activity activity = findActivity(getContext());
        if (activity instanceof FragmentActivity) {
            FragmentActivity fragmentActivity = (FragmentActivity) activity;
            fragmentActivity.getLifecycle().removeObserver(this);
            fragmentActivity.getLifecycle().addObserver(this);
        }
    }

    @Override
    public void dismiss() {
        if (isDestroyed(getContext())) {
            return;
        }
        try {
            super.dismiss();
        } catch (Exception e) {
        }
    }

    private boolean setAttributeForMode() {
        if (isDestroyed(getContext())) {
            return true;
        }
        setGravity(Gravity.LEFT | Gravity.TOP);
        WindowManager.LayoutParams lp = window.getAttributes();
        if (this.width != WindowManager.LayoutParams.WRAP_CONTENT) {
            lp.width = width;
        }
        if (this.height != WindowManager.LayoutParams.WRAP_CONTENT) {
            lp.height = height;
        }
        lp.x = viewXOffset;
        lp.y = viewYOffset;
        if (width != WindowManager.LayoutParams.WRAP_CONTENT || height != WindowManager.LayoutParams.WRAP_CONTENT) {
            window.setAttributes(lp);
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event) {
        if (!canMoveDialog) {
            return super.onTouchEvent(event);
        }
        moveDialog(event);
        return super.onTouchEvent(event);
    }

    private float downX;
    private float downY;

    private void moveDialog(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                float x = event.getRawX();
                float y = event.getRawY();
                float tempX = x - downX;
                float tempY = y - downY;
                if (Gravity.BOTTOM == getGravity()) {
                    tempY *= -1;
                }
                WindowManager.LayoutParams attributes = window.getAttributes();
                attributes.x += tempX;
                attributes.y += tempY;
                downX = x;
                downY = y;
                onWindowAttributesChanged(attributes);
                break;
            case MotionEvent.ACTION_DOWN:
                downX = event.getRawX();
                downY = event.getRawY();
                break;
        }
    }

    public static Activity findActivity(Context context) {
        if (context == null) {
            return null;
        } else {
            if (context instanceof Activity) {
                return (Activity) context;
            } else if (context instanceof ContextWrapper) {
                ContextWrapper wrapper = (ContextWrapper) context;
                return findActivity(wrapper.getBaseContext());
            } else {
                return null;
            }
        }
    }

    public static boolean isDestroyed(Context context) {
        Activity activity = findActivity(context);
        if (activity == null) {
            return true;
        } else {
            if (activity.isFinishing() || (Build.VERSION.SDK_INT >= 17 && activity.isDestroyed())) {
                return true;
            }
            if (activity instanceof FragmentActivity) {
                FragmentManager supportFragmentManager = ((FragmentActivity) activity).getSupportFragmentManager();
                if (supportFragmentManager != null && supportFragmentManager.isDestroyed()) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isFullScreen(View view) {
        if (view == null) {
            return false;
        }
        Activity activity = findActivity(view.getContext());
        if (activity == null || activity.isFinishing()) {
            return false;
        }
        Window window = activity.getWindow();
        if (window == null || !window.isActive()) {
            return false;
        }
        WindowManager.LayoutParams attributes = window.getAttributes();
        if (attributes == null) {
            return false;
        }
        int flags = attributes.flags;
        if ((flags & WindowManager.LayoutParams.FLAG_FULLSCREEN) == WindowManager.LayoutParams.FLAG_FULLSCREEN) {
            return true;
        }
        return false;
    }


    public void setFullScreen(boolean intoFull, boolean intoCutout) {
        if (window == null) {
            return;
        }
        setIntoCutout(intoCutout);
        setFullScreen(window.getDecorView(), intoFull);
    }

    public void setIntoCutout(boolean intoCutout) {
        if (window == null) {
            return;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            WindowManager.LayoutParams lp = window.getAttributes();
            if (intoCutout) {
                lp.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
            } else {
                lp.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_DEFAULT;
            }
            window.setAttributes(lp);
        }
    }

    public void setFullScreen(View view, boolean intoFull) {
        if (view == null) {
            return;
        }
        if (intoFull) { //全屏
            view.setSystemUiVisibility(
                    view.getSystemUiVisibility()
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            );
        } else { //非全屏
            int systemUiVisibility = view.getSystemUiVisibility();
            systemUiVisibility &= ~View.SYSTEM_UI_FLAG_FULLSCREEN;
            systemUiVisibility &= ~View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            systemUiVisibility &= ~View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
            systemUiVisibility &= ~View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
            systemUiVisibility &= ~View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
            systemUiVisibility &= ~View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
            view.setSystemUiVisibility(systemUiVisibility);
        }
    }

    public void setFullScreenCompat(boolean intoFull, boolean intoCutout) {
        if (window == null) {
            return;
        }
        setIntoCutout(intoCutout);
        if (intoFull) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
            window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        } else {
            window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            window.addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        }
    }

    public void setIntoStatusBarV1(boolean isInto) {
        setIntoCutout(isInto);
        setTranslucentStatus(isInto);
    }

    public void setIntoStatusBar(boolean isInto) {
        setIntoCutout(isInto);
        if (window == null) {
            return;
        }
        int systemUiVisibility = window.getDecorView().getSystemUiVisibility();
        if (isInto) {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(systemUiVisibility | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.setStatusBarColor(Color.TRANSPARENT);
            }
        } else {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            systemUiVisibility &= ~View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
            window.clearFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.getDecorView().setSystemUiVisibility(systemUiVisibility);

        }
    }
}
