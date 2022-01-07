package com.example.unittestsample.test;

import com.example.unittestsample.test.authtoken.AuthTokenCache;
import com.example.unittestsample.test.eventbus.EventBusPoster;
import com.example.unittestsample.test.eventbus.LoggedInEvent;
import com.example.unittestsample.test.networking.LoginHttpEndpointSync;
import com.example.unittestsample.test.networking.NetworkErrorException;

public class LoginUseCaseSync {

    public enum UseCaseResult{
        SUCCESS,
        FAILURE,
        NETWORK_ERROR
    }

    private final LoginHttpEndpointSync mLoginHttpEndpointSync;
    private final AuthTokenCache mAuthTokenCache;
    private final EventBusPoster mEventBusPoster;

    public LoginUseCaseSync(LoginHttpEndpointSync mLoginHttpEndpointSync,
                            AuthTokenCache mAuthTokenCache,
                            EventBusPoster mEventBusPoster) {
        this.mLoginHttpEndpointSync = mLoginHttpEndpointSync;
        this.mAuthTokenCache = mAuthTokenCache;
        this.mEventBusPoster = mEventBusPoster;
    }

    public UseCaseResult loginSync(String username, String password) {
        LoginHttpEndpointSync.EndpointResult endpointEndpointResult;
        try {
            endpointEndpointResult = mLoginHttpEndpointSync.loginSync(username, password);
        } catch (NetworkErrorException e) {
            return UseCaseResult.NETWORK_ERROR;
        }


        if (isSuccessfulEndpointResult(endpointEndpointResult)) {
            mAuthTokenCache.cacheAuthToken(endpointEndpointResult.getAuthToken());
            mEventBusPoster.postEvent(new LoggedInEvent());
            return UseCaseResult.SUCCESS;
        } else {
            return UseCaseResult.FAILURE;
        }
    }

    private boolean isSuccessfulEndpointResult(LoginHttpEndpointSync.EndpointResult endpointResult) {
        return endpointResult.getStatus() == LoginHttpEndpointSync.EndpointResultStatus.SUCCESS;
    }
}