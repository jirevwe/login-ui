package com.rtukpe.fixtrs.ui.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.rtukpe.fixtrs.R;
import com.rtukpe.fixtrs.ui.base.BaseActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class LoginActivity extends BaseActivity implements LoginMvpView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Inject
    LoginMvpContract<LoginMvpView> mPresenter;

    @NonNull
    public static Intent getStartIntent(Context context) {
        return new Intent(context, LoginActivity.class);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            default:
                break;
        }
        return false;
    }

    @Override
    protected void setUp() {
        mPresenter.onViewInitialized();
        setSupportActionBar(toolbar);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void showHideToolBar(boolean hide) {
        toolbar.setVisibility(hide ? View.GONE : View.VISIBLE);
    }

    @Override
    public void recyclerViewListClicked(View v, int position) {

    }
}
