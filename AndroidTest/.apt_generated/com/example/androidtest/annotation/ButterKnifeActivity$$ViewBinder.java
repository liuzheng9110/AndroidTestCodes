// Generated code from Butter Knife. Do not modify!
package com.example.androidtest.annotation;

import android.content.res.Resources;
import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class ButterKnifeActivity$$ViewBinder<T extends com.example.androidtest.annotation.ButterKnifeActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558617, "field 'editText'");
    target.editText = finder.castView(view, 2131558617, "field 'editText'");
    view = finder.findRequiredView(source, 2131558618, "field 'button', method 'btnClick', and method 'onLongClick'");
    target.button = finder.castView(view, 2131558618, "field 'button'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.btnClick();
        }
      });
    view.setOnLongClickListener(
      new android.view.View.OnLongClickListener() {
        @Override public boolean onLongClick(
          android.view.View p0
        ) {
          return target.onLongClick();
        }
      });
    view = finder.findRequiredView(source, 2131558616, "field 'textView'");
    target.textView = finder.castView(view, 2131558616, "field 'textView'");
    view = finder.findRequiredView(source, 2131558619, "field 'listView', method 'onListItemClick', and method 'onListLongClick'");
    target.listView = finder.castView(view, 2131558619, "field 'listView'");
    ((android.widget.AdapterView<?>) view).setOnItemClickListener(
      new android.widget.AdapterView.OnItemClickListener() {
        @Override public void onItemClick(
          android.widget.AdapterView<?> p0,
          android.view.View p1,
          int p2,
          long p3
        ) {
          target.onListItemClick(p2);
        }
      });
    ((android.widget.AdapterView<?>) view).setOnItemLongClickListener(
      new android.widget.AdapterView.OnItemLongClickListener() {
        @Override public boolean onItemLongClick(
          android.widget.AdapterView<?> p0,
          android.view.View p1,
          int p2,
          long p3
        ) {
          return target.onListLongClick();
        }
      });
    Resources res = finder.getContext(source).getResources();
    target.textString = res.getString(2131165184);
  }

  @Override public void unbind(T target) {
    target.editText = null;
    target.button = null;
    target.textView = null;
    target.listView = null;
  }
}
