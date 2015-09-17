package com.example.androidtest.supportlib;

import com.example.androidtest.R;

import android.support.design.widget.Snackbar;
import android.view.View;

public class SnackbarUtil {

    // android-support-design兼容包中新添加的一个类似Toast的控件。
    // make()中的第一个参数，可以写当前界面中的任意一个view对象。
    private static Snackbar mSnackbar;

    /**
     * 
     *  Function:
     *  @author liuzheng
     *  @created 2015年7月17日 上午10:59:27 
     *  @param view 显示对象 view
     *  @param msg  显示信息
     *  @param flag 显示时长 0 短 1 长
     */
    public static void show(View view, String msg, int flag) {

        if (flag == 0) { // 短时显示
            mSnackbar = Snackbar.make(view, msg, Snackbar.LENGTH_SHORT);
        } else { // 长时显示
            mSnackbar = Snackbar.make(view, msg, Snackbar.LENGTH_LONG);
        }

        mSnackbar.show();
        // Snackbar中有一个可点击的文字，这里设置点击所触发的操作。
        mSnackbar.setAction(R.string.close, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Snackbar在点击“关闭”后消失
                mSnackbar.dismiss();
            }
        });
    }
}
