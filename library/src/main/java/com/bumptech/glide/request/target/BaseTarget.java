package com.bumptech.glide.request.target;

import android.graphics.drawable.Drawable;

import com.bumptech.glide.request.Request;

/**
 * A base {@link Target} for loading {@link com.bumptech.glide.load.engine.Resource}s that provides
 * basic or empty implementations for most methods.
 *
 * <p> For maximum efficiency, clear this target when you have finished using or displaying the
 * {@link com.bumptech.glide.load.engine.Resource} loaded into it using
 * {@link com.bumptech.glide.Glide#clear(Target)}.</p>
 *
 * <p> For loading {@link com.bumptech.glide.load.engine.Resource}s into {@link android.view.View}s,
 * {@link com.bumptech.glide.request.target.ViewTarget} or
 * {@link com.bumptech.glide.request.target.ImageViewTarget} are preferable.</p>
 *
 * @param <Z> The type of resource that will be received by this target.
 */
public abstract class BaseTarget<Z> implements Target<Z> {

  private Request request;

  @Override
  public void setRequest(Request request) {
    this.request = request;
  }

  @Override
  public Request getRequest() {
    return request;
  }

  @Override
  public void onLoadCleared(Drawable placeholder) {
    // Do nothing.
  }

  @Override
  public void onLoadStarted(Drawable placeholder) {
    // Do nothing.
  }

  @Override
  public void onLoadFailed(Exception e, Drawable errorDrawable) {
    // Do nothing.
  }

  @Override
  public void onStart() {
    // Do nothing.
  }

  @Override
  public void onStop() {
    // Do nothing.
  }

  @Override
  public void onDestroy() {
    // Do nothing.
  }
}
