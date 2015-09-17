package com.example.androidtest.supportlib;

import com.example.androidtest.R;

import android.support.design.widget.Snackbar;
import android.view.View;

public class SnackbarUtil {

    // android-support-design���ݰ�������ӵ�һ������Toast�Ŀؼ���
    // make()�еĵ�һ������������д��ǰ�����е�����һ��view����
    private static Snackbar mSnackbar;

    /**
     * 
     *  Function:
     *  @author liuzheng
     *  @created 2015��7��17�� ����10:59:27 
     *  @param view ��ʾ���� view
     *  @param msg  ��ʾ��Ϣ
     *  @param flag ��ʾʱ�� 0 �� 1 ��
     */
    public static void show(View view, String msg, int flag) {

        if (flag == 0) { // ��ʱ��ʾ
            mSnackbar = Snackbar.make(view, msg, Snackbar.LENGTH_SHORT);
        } else { // ��ʱ��ʾ
            mSnackbar = Snackbar.make(view, msg, Snackbar.LENGTH_LONG);
        }

        mSnackbar.show();
        // Snackbar����һ���ɵ�������֣��������õ���������Ĳ�����
        mSnackbar.setAction(R.string.close, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Snackbar�ڵ�����رա�����ʧ
                mSnackbar.dismiss();
            }
        });
    }
}
