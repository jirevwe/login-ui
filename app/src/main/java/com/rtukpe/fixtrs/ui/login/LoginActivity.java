package com.rtukpe.fixtrs.ui.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.rtukpe.fixtrs.R;
import com.rtukpe.fixtrs.ui.base.BaseActivity;
import com.rtukpe.fixtrs.utils.others.AppUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class LoginActivity extends BaseActivity implements LoginMvpView {

    private final int RC_SIGN_IN = 9001;

    @Inject
    LoginMvpContract<LoginMvpView> mPresenter;
    @BindView(R.id.logout_view)
    CardView logoutView;
    @BindView(R.id.login_view)
    CardView loginView;
    @BindView(R.id.login_password)
    EditText mPasswordField;
    @BindView(R.id.login_email)
    EditText mEmailField;
    private GoogleSignInClient mGoogleSignInClient;
    private FirebaseAuth mAuth;

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

        FirebaseApp.initializeApp(this);

        mAuth = FirebaseAuth.getInstance();

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Timber.d("Google sign in failed", e);
                Timber.e(e);
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        AppUtils.checkPlayServices(this);

        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Timber.d("firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Timber.d("signInWithCredential:success");
                        FirebaseUser user = mAuth.getCurrentUser();
                        updateUI(user);
                        show("Authentication Successful.", false);
                    } else {
                        // If sign in fails, display a message to the user.
                        Timber.d("signInWithCredential:failure");
                        Timber.e(task.getException());
                        updateUI(null);
                        show("Authentication Failed.", false);
                    }
                });
    }

    private boolean validateForm() {
        boolean valid = true;

        String email = mEmailField.getText().toString();
        if (TextUtils.isEmpty(email) || mEmailField.getText().toString().contains("@")) {
            mEmailField.setError("Required.");
            valid = false;
        } else {
            mEmailField.setError(null);
        }

        String password = mPasswordField.getText().toString();
        if (TextUtils.isEmpty(password)) {
            mPasswordField.setError("Required.");
            valid = false;
        } else {
            mPasswordField.setError(null);
        }

        if (password.length() < 6) {
            mPasswordField.setError("Password should be at least 6 characters");
            valid = false;
        } else {
            mPasswordField.setError(null);
        }

        return valid;
    }

    private void createAccount(String email, String password) {
        Timber.d("createAccount:" + email + " " + validateForm());
        if (validateForm()) {
            showLoading();

            // [START create_user_with_email]
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, task -> {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Timber.d("createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                            show("Account Created", false);
                            } else {
                            // If sign in fails, display a message to the user.
                            Timber.d("createUserWithEmail:failure");
                            Timber.e(task.getException());
                            show("Sign Up Failed.", false);
                            updateUI(null);
                        }

                        // [START_EXCLUDE]
                        hideLoading();
                        // [END_EXCLUDE]
                    });
            // [END create_user_with_email]
        }
    }

    private void trySignIn(String email, String password) {
        Timber.d("signIn:" + email);
        if (validateForm()) {
            showLoading();

            // [START sign_in_with_email]
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, task -> {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Timber.d("signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Timber.d("signInWithEmail:failure");
                            if (task.getException() != null && task.getException().getMessage()
                                    .equalsIgnoreCase("There is no user record corresponding" +
                                            " to this identifier. The user may have been deleted.")) {
                                createAccount(email, password);
                                Timber.e(task.getException());
                            } else {
                                show("Authentication failed.", false);
                                updateUI(null);
                            }
                        }

                        // [START_EXCLUDE]
                        if (!task.isSuccessful()) {
                            show("Sign In failed.", false);
                        }
                        hideLoading();
                        // [END_EXCLUDE]
                    });
            // [END sign_in_with_email]
        }
    }

    private void updateUI(FirebaseUser user) {
        hideLoading();
        hideKeyboard();
        loginView.setVisibility(user != null ? View.GONE : View.VISIBLE);
        logoutView.setVisibility(user == null ? View.GONE : View.VISIBLE);
    }

    @OnClick(R.id.login_with_google)
    public void onLoginGoogleClicked(View v) {
        if (AppUtils.hasInternetConnection(this)) {
            Intent signInIntent = mGoogleSignInClient.getSignInIntent();
            startActivityForResult(signInIntent, RC_SIGN_IN);
            showLoading();
        } else
            show("Please connect to the internet", false);
    }

    @OnClick(R.id.login_with_email)
    public void onLoginEmailClicked(View v) {
        if (AppUtils.hasInternetConnection(this))
            trySignIn(mEmailField.getText().toString(), mPasswordField.getText().toString());
        else
            show("Please connect to the internet", false);
    }

    @OnClick(R.id.logout)
    public void onLogoutClicked(View v) {
        mAuth.signOut();
        showLoading();
        updateUI(null);
    }

    @Override
    protected void setUp() {
        mPresenter.onViewInitialized();
        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getResources().getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void showHideToolBar(boolean hide) {

    }

    @Override
    public void recyclerViewListClicked(View v, int position) {

    }
}
