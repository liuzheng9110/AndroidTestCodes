// Generated code from Butter Knife. Do not modify!
package com.example.androidtest.annotation;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class SimpleAdapter$ViewHolder$$ViewBinder<T extends com.example.androidtest.annotation.SimpleAdapter.ViewHolder> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558875, "field 'sexTv'");
    target.sexTv = finder.castView(view, 2131558875, "field 'sexTv'");
    view = finder.findRequiredView(source, 2131558876, "field 'ageTv'");
    target.ageTv = finder.castView(view, 2131558876, "field 'ageTv'");
    view = finder.findRequiredView(source, 2131558874, "field 'nameTv'");
    target.nameTv = finder.castView(view, 2131558874, "field 'nameTv'");
    view = finder.findRequiredView(source, 2131558873, "field 'idTv'");
    target.idTv = finder.castView(view, 2131558873, "field 'idTv'");
  }

  @Override public void unbind(T target) {
    target.sexTv = null;
    target.ageTv = null;
    target.nameTv = null;
    target.idTv = null;
  }
}
