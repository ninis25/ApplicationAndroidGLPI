// Generated by view binder compiler. Do not edit!
package com.example.glpiwebview.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.glpiwebview.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivitySplashBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ProgressBar loadingProgressBar;

  @NonNull
  public final ImageView logoImageView;

  private ActivitySplashBinding(@NonNull ConstraintLayout rootView,
      @NonNull ProgressBar loadingProgressBar, @NonNull ImageView logoImageView) {
    this.rootView = rootView;
    this.loadingProgressBar = loadingProgressBar;
    this.logoImageView = logoImageView;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivitySplashBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivitySplashBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_splash, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivitySplashBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.loadingProgressBar;
      ProgressBar loadingProgressBar = ViewBindings.findChildViewById(rootView, id);
      if (loadingProgressBar == null) {
        break missingId;
      }

      id = R.id.logoImageView;
      ImageView logoImageView = ViewBindings.findChildViewById(rootView, id);
      if (logoImageView == null) {
        break missingId;
      }

      return new ActivitySplashBinding((ConstraintLayout) rootView, loadingProgressBar,
          logoImageView);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
