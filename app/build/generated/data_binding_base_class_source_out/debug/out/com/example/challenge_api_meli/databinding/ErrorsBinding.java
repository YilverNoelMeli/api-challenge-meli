// Generated by view binder compiler. Do not edit!
package com.example.challenge_api_meli.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.challenge_api_meli.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ErrorsBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ConstraintLayout layoutE;

  @NonNull
  public final LinearLayout llErrorDetail;

  @NonNull
  public final LinearLayout llNoFound;

  @NonNull
  public final LinearLayout llWithoutConnection;

  @NonNull
  public final RelativeLayout rlNoFound;

  @NonNull
  public final RelativeLayout rlWithoutConnection;

  @NonNull
  public final TextView tvTryConnection;

  private ErrorsBinding(@NonNull ConstraintLayout rootView, @NonNull ConstraintLayout layoutE,
      @NonNull LinearLayout llErrorDetail, @NonNull LinearLayout llNoFound,
      @NonNull LinearLayout llWithoutConnection, @NonNull RelativeLayout rlNoFound,
      @NonNull RelativeLayout rlWithoutConnection, @NonNull TextView tvTryConnection) {
    this.rootView = rootView;
    this.layoutE = layoutE;
    this.llErrorDetail = llErrorDetail;
    this.llNoFound = llNoFound;
    this.llWithoutConnection = llWithoutConnection;
    this.rlNoFound = rlNoFound;
    this.rlWithoutConnection = rlWithoutConnection;
    this.tvTryConnection = tvTryConnection;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ErrorsBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ErrorsBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup parent,
      boolean attachToParent) {
    View root = inflater.inflate(R.layout.errors, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ErrorsBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      ConstraintLayout layoutE = (ConstraintLayout) rootView;

      id = R.id.llErrorDetail;
      LinearLayout llErrorDetail = ViewBindings.findChildViewById(rootView, id);
      if (llErrorDetail == null) {
        break missingId;
      }

      id = R.id.llNoFound;
      LinearLayout llNoFound = ViewBindings.findChildViewById(rootView, id);
      if (llNoFound == null) {
        break missingId;
      }

      id = R.id.llWithoutConnection;
      LinearLayout llWithoutConnection = ViewBindings.findChildViewById(rootView, id);
      if (llWithoutConnection == null) {
        break missingId;
      }

      id = R.id.rlNoFound;
      RelativeLayout rlNoFound = ViewBindings.findChildViewById(rootView, id);
      if (rlNoFound == null) {
        break missingId;
      }

      id = R.id.rlWithoutConnection;
      RelativeLayout rlWithoutConnection = ViewBindings.findChildViewById(rootView, id);
      if (rlWithoutConnection == null) {
        break missingId;
      }

      id = R.id.tvTryConnection;
      TextView tvTryConnection = ViewBindings.findChildViewById(rootView, id);
      if (tvTryConnection == null) {
        break missingId;
      }

      return new ErrorsBinding((ConstraintLayout) rootView, layoutE, llErrorDetail, llNoFound,
          llWithoutConnection, rlNoFound, rlWithoutConnection, tvTryConnection);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
