package com.bumptech.glide;

import static com.bumptech.glide.tests.BackgroundUtil.testInBackground;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import android.widget.ImageView;

import com.bumptech.glide.manager.Lifecycle;
import com.bumptech.glide.manager.RequestTracker;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.tests.BackgroundUtil;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

@SuppressWarnings("unchecked")
@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE, emulateSdk = 18)
public class RequestBuilderTest {
  @Mock
  GlideContext glideContext;
  @Mock
  RequestTracker requestTracker;
  @Mock
  Lifecycle lifecycle;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test(expected = NullPointerException.class)
  public void testThrowsIfContextIsNull() {
    new RequestBuilder(null, Object.class, requestTracker, lifecycle);
  }

  @Test(expected = NullPointerException.class)
  public void testThrowsWhenGlideAnimationFactoryIsNull() {
    getNullModelRequest().transition(null);
  }

  @Test
  public void testDoesNotThrowWhenModelAndLoaderNull() {
    // TODO: fixme.
//        new RequestBuilder(Robolectric.application, null, null, Object.class, mock(Glide.class)
// , requestTracker,
//                mock(Lifecycle.class));
  }
  // TODO: fixme.
//
//    @Test
//    public void testDoesNotThrowWithNullModelWhenDecoderSet() {
//        getNullModelRequest().decoder(mock(ResourceDecoder.class));
//    }
//
//    @Test
//    public void testDoesNotThrowWithNullModelWhenCacheDecoderSet() {
//        getNullModelRequest().cacheDecoder(mock(ResourceDecoder.class));
//    }
//
//    @Test
//    public void testDoesNotThrowWithNullModelWhenEncoderSet() {
//        getNullModelRequest().encoder(mock(ResourceEncoder.class));
//    }
//
//    @Test
//    public void testDoesNotThrowWithNullModelWhenDiskCacheStrategySet() {
//        getNullModelRequest().diskCacheStrategy(DiskCacheStrategy.ALL);
//    }

  @Test
  public void testDoesNotThrowWithNullModelWhenRequestIsBuilt() {
    getNullModelRequest().into(mock(Target.class));
  }

  @Test
  public void testAddsNewRequestToRequestTracker() {
    getNullModelRequest().into(mock(Target.class));
    verify(requestTracker).runRequest(any(Request.class));
  }

  @Test
  public void testRemovesPreviousRequestFromRequestTracker() {
    Request previous = mock(Request.class);
    Target target = mock(Target.class);
    when(target.getRequest()).thenReturn(previous);

    getNullModelRequest().into(target);

    verify(requestTracker).removeRequest(eq(previous));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testThrowsIfGivenNullTarget() {
    getNullModelRequest().into((Target) null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testThrowsIfGivenNullView() {
    getNullModelRequest().into((ImageView) null);
  }

  @Test(expected = RuntimeException.class)
  public void testThrowsIfIntoViewCalledOnBackgroundThread() throws InterruptedException {
    final ImageView imageView = new ImageView(RuntimeEnvironment.application);
    testInBackground(new BackgroundUtil.BackgroundTester() {
      @Override
      public void runTest() throws Exception {
        getNullModelRequest().into(imageView);

      }
    });
  }

  @Test(expected = RuntimeException.class)
  public void testThrowsIfIntoTargetCalledOnBackgroundThread() throws InterruptedException {
    final Target target = mock(Target.class);
    testInBackground(new BackgroundUtil.BackgroundTester() {
      @Override
      public void runTest() throws Exception {
        getNullModelRequest().into(target);
      }
    });
  }

  private RequestBuilder getNullModelRequest() {
    when(glideContext.buildImageViewTarget(any(ImageView.class), any(Class.class)))
        .thenReturn(mock(Target.class));
    when(glideContext.getOptions()).thenReturn(new RequestOptions());
    return new RequestBuilder(glideContext, Object.class, requestTracker, lifecycle)
        .load((Object) null);
  }
}
