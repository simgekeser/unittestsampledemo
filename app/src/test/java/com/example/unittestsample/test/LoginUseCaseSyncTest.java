package com.example.unittestsample.test;

import com.example.unittestsample.test.authtoken.AuthTokenCache;
import com.example.unittestsample.test.eventbus.EventBusPoster;
import com.example.unittestsample.test.networking.LoginHttpEndpointSync;
import com.example.unittestsample.test.networking.NetworkErrorException;

import org.junit.Before;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class LoginUseCaseSyncTest {

    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String AUTH_TOKEN = "authToken";
    public static final String NON_INITIALIZED_AUTH_TOKEN = "noAuthToken";

    LoginHttpEndpointSyncTd mLoginHttpEndpointSyncTd;
    AuthTokenCacheTd mAuthTokenCacheTd;
    EventBusPosterTd mEventBusPosterTd;

    LoginUseCaseSync SUT;

    @Before
    public void setUp() throws Exception {
        mLoginHttpEndpointSyncTd = new LoginHttpEndpointSyncTd();
        mAuthTokenCacheTd = new AuthTokenCacheTd();
        mEventBusPosterTd = new EventBusPosterTd();
        SUT = new LoginUseCaseSync(mLoginHttpEndpointSyncTd,mAuthTokenCacheTd,mEventBusPosterTd);
    }

    @Test
    public void loginSync_success_usernameAndPasswordPassedEndpoint() throws Exception{
        SUT.loginSync(USERNAME,PASSWORD);
        assertThat(mLoginHttpEndpointSyncTd.mUsername).isEqualTo(USERNAME);
        assertThat(mLoginHttpEndpointSyncTd.mPassword).isEqualTo(PASSWORD);
    }

    @Test
    public void loginSync_success_AuthTokenCached() throws Exception{
        SUT.loginSync(USERNAME,PASSWORD);
        assertThat(mAuthTokenCacheTd.getAuthToken()).isEqualTo(AUTH_TOKEN);
    }

    @Test
    public void loginSync_generalError_AuthTokenCached() throws Exception{
        SUT.loginSync(USERNAME,PASSWORD);
        assertThat(mAuthTokenCacheTd.getAuthToken()).isEqualTo(AUTH_TOKEN);
    }



    private static class LoginHttpEndpointSyncTd implements LoginHttpEndpointSync{
        private String mUsername;
        private String mPassword;

        @Override
        public EndpointResult loginSync(String username, String password) throws NetworkErrorException {
           mUsername=username;
            mPassword=password;

            return new EndpointResult(EndpointResultStatus.SUCCESS,AUTH_TOKEN);
        }
    }

    private static class AuthTokenCacheTd implements AuthTokenCache{
        String mAuthToken;
        @Override
        public void cacheAuthToken(String authToken) {
            mAuthToken=authToken;
        }

        @Override
        public String getAuthToken() {
            return mAuthToken;
        }
    }

    private static class EventBusPosterTd implements EventBusPoster{

        @Override
        public void postEvent(Object event) {

        }
    }
}