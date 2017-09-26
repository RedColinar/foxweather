package com.harry.domain.common.usecase;


import com.harry.domain.common.executor.PostExecutionThread;
import com.harry.domain.common.executor.ThreadExecutor;

import rx.Observable;
import rx.Subscription;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

/*
 * Abstract class that represents a basic use case.
 */
public abstract class UseCase<T> {

    /**
     * User logged in token.
     */
    protected String token;

    ThreadExecutor threadExecutor;

    PostExecutionThread postExecutionThread;

    private Subscription disposable = Subscriptions.empty();

    private boolean switchThreads = false;

    public UseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        this.threadExecutor = threadExecutor;
        this.postExecutionThread = postExecutionThread;
    }

    protected abstract Observable<T> buildObservableUseCase();

    public Observable<T> execute() {
        if (switchThreads) {
            return this.buildObservableUseCase()
                    .subscribeOn(Schedulers.from((threadExecutor)))
                    .observeOn(postExecutionThread.getScheduler());
        }

        return this.buildObservableUseCase();
    }

    public void unsubscribe() {
        if (!disposable.isUnsubscribed()) {
            disposable.unsubscribe();
        }
    }

    public UseCase<T> setToken(String token) {
        this.token = token;
        return this;
    }

    public boolean isSwitchThreads() {
        return switchThreads;
    }

    public UseCase<T> setSwitchThreads(boolean switchThreads) {
        this.switchThreads = switchThreads;
        return this;
    }
}
