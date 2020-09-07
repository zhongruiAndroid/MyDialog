package com.github.mydialog;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatDialog;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;

public class TheDialog extends AppCompatDialog {


    private int width = WindowManager.LayoutParams.WRAP_CONTENT; //(int) (getScreenWidth() * 0.7);
    private int height = WindowManager.LayoutParams.WRAP_CONTENT; //(int) (getScreenWidth() * 0.6);


    //    private int titleViewHeight = -1;
//    private int titleViewColor = -1;
    private boolean translucentStatus = false;
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
        }
        return this;
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

    @Override
    public void show() {
        if (isDestroyed(getContext())) {
            return;
        }

        WindowManager.LayoutParams lp = window.getAttributes();
        if (this.width != WindowManager.LayoutParams.WRAP_CONTENT) {
            lp.width = width;
        }
        if (this.height != WindowManager.LayoutParams.WRAP_CONTENT) {
            lp.height = height;
        }
        if (width != WindowManager.LayoutParams.WRAP_CONTENT || height != WindowManager.LayoutParams.WRAP_CONTENT) {
            window.setAttributes(lp);
        }

        super.show();
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
}
