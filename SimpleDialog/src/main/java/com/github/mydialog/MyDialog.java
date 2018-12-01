package com.github.mydialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MyDialog extends Dialog {
	private TextView titleView,messageView,negativeView,positiveView;

	public MyDialog(Context context) {
		super(context);
	}
	public MyDialog(Context context, int theme) {
		super(context, theme);
	}

	public TextView getTitleView() {
		return titleView;
	}

	public void setTitleView(TextView titleView) {
		this.titleView = titleView;
	}

	public TextView getMessageView() {
		return messageView;
	}

	public void setMessageView(TextView messageView) {
		this.messageView = messageView;
	}

	public TextView getNegativeView() {
		return negativeView;
	}

	public void setNegativeView(TextView negativeView) {
		this.negativeView = negativeView;
	}

	public TextView getPositiveView() {
		return positiveView;
	}

	public void setPositiveView(TextView positiveView) {
		this.positiveView = positiveView;
	}

	public static class Builder {
		private Context context;
		private String title;
		private String message;
		private String negativeText;
		private String positiveText;
		private View contentView;
		private boolean touchOutside=false;
		private OnClickListener positiveButtonClickListener;
		private OnClickListener negativeButtonClickListener;
		private OnDismissListener onDismissListener;
		private OnCancelListener onCancelListener;
		private OnKeyListener onKeyListener;
		private OnShowListener onShowListener;

		public Builder(Context context) {
			this.context = context;
		}

		public boolean isTouchOutside() {
			return touchOutside;
		}

		public void setTouchOutside(boolean touchOutside) {
			this.touchOutside = touchOutside;
		}

		public Builder setMessage(String message) {
			this.message = message;
			return this;
		}
		public Builder setMessage(int message) {
			this.message = (String) context.getText(message);
			return this;
		}
		public Builder setTitle(int title) {
			this.title = (String) context.getText(title);
			return this;
		}

		public Builder setTitle(String title) {
			this.title = title;
			return this;
		}

		public Builder setContentView(View v) {
			this.contentView = v;
			return this;
		}

		public Builder setPositiveButton(int positiveButtonText,
				OnClickListener listener) {
			this.positiveText = (String) context
					.getText(positiveButtonText);
			this.positiveButtonClickListener = listener;
			return this;
		}

		public Builder setPositiveButton(String positiveButtonText,
				OnClickListener listener) {
			this.positiveText = positiveButtonText;
			this.positiveButtonClickListener = listener;
			return this;
		}
		public Builder setPositiveButton(OnClickListener listener) {
			return setPositiveButton("确认",listener);
		}
		public Builder setNegativeButton(int negativeButtonText,
										 OnClickListener listener) {
			this.negativeText = (String) context
					.getText(negativeButtonText);
			this.negativeButtonClickListener = listener;
			return this;
		}
		public Builder setNegativeButton(String negativeButtonText,
				OnClickListener listener) {
			this.negativeText = negativeButtonText;
			this.negativeButtonClickListener = listener;
			return this;
		}
		public Builder setNegativeButton(OnClickListener listener) {
			return setNegativeButton("取消",listener);
		}

		public void setOnDismissListener(OnDismissListener onDismissListener) {
			this.onDismissListener = onDismissListener;
		}

		public Builder setOnCancelListener(OnCancelListener onCancelListener) {
			this.onCancelListener = onCancelListener;
			return this;
		}

		public Builder setOnKeyListener(OnKeyListener onKeyListener) {
			this.onKeyListener = onKeyListener;
			return this;
		}

		public Builder setOnShowListener(OnShowListener onShowListener) {
			this.onShowListener = onShowListener;
			return this;
		}

		public MyDialog create() {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			// instantiate the dialog with the custom Theme
			final MyDialog dialog=new MyDialog(context, R.style.MyDialog);
			dialog.setCanceledOnTouchOutside(touchOutside);

			View dialogView = inflater.inflate(R.layout.my_dialog, null);
			//增加Dialog是否设置标题
			/*if(isNotitle){
				dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
				(layout.findViewById(R.id.title)).setVisibility(View.GONE);
			}else{*/
				// set the dialog title
			TextView tv_title = (TextView) dialogView.findViewById(R.id.tv_title);
			tv_title.setText(title==null?"提示":title);
			if("".equals(title)){
				tv_title.setVisibility(View.GONE);
			}
			dialog.setTitleView(tv_title);
//			}

			WindowManager wm = (WindowManager) context
					.getSystemService(Context.WINDOW_SERVICE);
			Window dialogWindow =dialog.getWindow();
			int width = wm.getDefaultDisplay().getWidth();
//			int height = wm.getDefaultDisplay().getHeight();
			// set the confirm button
			if(onDismissListener!=null){
				dialog.setOnDismissListener(onDismissListener);
			}
			if(onShowListener!=null){
				dialog.setOnShowListener(onShowListener);
			}
			if(onKeyListener!=null){
				dialog.setOnKeyListener(onKeyListener);
			}
			if(onCancelListener!=null){
				dialog.setOnCancelListener(onCancelListener);
			}

			TextView tv_message=(TextView)dialogView.findViewById(R.id.tv_message);
			TextView tv_negative=(TextView)dialogView.findViewById(R.id.tv_negative);
			TextView tv_positive=(TextView)dialogView.findViewById(R.id.tv_positive);

			dialog.setMessageView(tv_message);
			dialog.setNegativeView(tv_negative);
			dialog.setPositiveView(tv_positive);
			if (positiveText != null) {
				tv_positive.setText(positiveText);

				if (positiveButtonClickListener != null) {
					tv_positive.setOnClickListener(new View.OnClickListener() {
								public void onClick(View v) {
									positiveButtonClickListener.onClick(dialog,
											DialogInterface.BUTTON_POSITIVE);
								}
							});
				}
			} else {
				// if no confirm button just set the visibility to GONE
				tv_positive.setVisibility(View.GONE);
				dialogView.findViewById(R.id.v_line).setVisibility(View.GONE);
				tv_negative.setBackgroundResource(R.drawable.single_selector);
			}
			// set the cancel button
			if (negativeText != null) {
				tv_negative.setText(negativeText);
				if (negativeButtonClickListener != null) {
					tv_negative.setOnClickListener(new View.OnClickListener() {
								public void onClick(View v) {
									negativeButtonClickListener.onClick(dialog,
											DialogInterface.BUTTON_NEGATIVE);
								}
							});
				}
			} else {
				// if no confirm button just set the visibility to GONE
				tv_negative.setVisibility(View.GONE);
				dialogView.findViewById(R.id.v_line).setVisibility(View.GONE);
				tv_positive.setBackgroundResource(R.drawable.single_selector);
			}
			// set the content message
			if (contentView != null) {
				// if no message set
				// add the contentView to the dialog body
				((LinearLayout) dialogView.findViewById(R.id.ll_dialog)).removeViews(0,2);
				((LinearLayout) dialogView.findViewById(R.id.ll_dialog)).addView(
						contentView, 0, new LayoutParams(
								LayoutParams.MATCH_PARENT,
								LayoutParams.WRAP_CONTENT));
			} else if (message != null) {
				tv_message.setText(message);

			}
			dialog.setContentView(dialogView);
			WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
//			p.height = (int) (width* 0.7); // 高度设置为屏幕的0.6
			p.width = (int) (width * 0.7); // 宽度设置为屏幕的0.65
			dialogWindow.setAttributes(p);
			return dialog;
		}

	}
	
}
