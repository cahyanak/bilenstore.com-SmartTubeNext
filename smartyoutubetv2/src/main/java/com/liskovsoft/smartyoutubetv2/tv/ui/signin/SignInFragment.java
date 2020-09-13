package com.liskovsoft.smartyoutubetv2.tv.ui.signin;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.leanback.app.ErrorSupportFragment;
import com.liskovsoft.smartyoutubetv2.common.app.presenters.SignInPresenter;
import com.liskovsoft.smartyoutubetv2.common.app.views.SignInView;
import com.liskovsoft.smartyoutubetv2.tv.ui.common.LeanbackActivity;
import com.liskovsoft.smartyoutubetv2.tv.ui.common.UriBackgroundManager;

public class SignInFragment extends ErrorSupportFragment implements SignInView {
    private static final String TAG = SignInFragment.class.getSimpleName();

    private final Handler mHandler = new Handler();
    private SignInPresenter mSignInPresenter;
    private UriBackgroundManager mBackgroundManager;
    private static final boolean TRANSLUCENT = true;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        mSignInPresenter = SignInPresenter.instance(context);
        mSignInPresenter.register(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mBackgroundManager = ((LeanbackActivity) getActivity()).getBackgroundManager();

        setupUi();

        mSignInPresenter.onInitDone();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onPause() {
        mHandler.removeCallbacksAndMessages(null);

        // fix Service not registered: android.speech.SpeechRecognizer$Connection
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mSignInPresenter.unregister(this);
    }

    @Override
    public void showCode(String userCode) {
        // TODO: not implemented
    }

    private void setupUi() {
        //setImageDrawable(getResources().getDrawable(R.drawable.lb_ic_sad_cloud, null));

        setMessage("User code");
        setDefaultBackground(TRANSLUCENT);

        setButtonText("DONE");
        setButtonClickListener(arg0 -> {
            getFragmentManager().beginTransaction().remove(SignInFragment.this).commit();
            getFragmentManager().popBackStack();
        });
    }
}
