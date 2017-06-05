package com.havrylyuk.thenounproject.presenter;

import android.support.annotation.CallSuper;

import com.havrylyuk.thenounproject.data.DataManager;
import com.havrylyuk.thenounproject.data.remote.helper.CompositeDisposableHelper;
import com.havrylyuk.thenounproject.data.remote.helper.error.ErrorHandlerHelper;
import com.havrylyuk.thenounproject.ui.base.BaseMvpView;
import com.havrylyuk.thenounproject.ui.base.BasePresenter;
import com.havrylyuk.thenounproject.utils.reactive.TestSchedulerProvider;

import org.junit.After;
import org.junit.Before;
import org.mockito.Mock;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.TestScheduler;

import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by Igor Havrylyuk on 30.05.2017.
 */

public abstract class BasePresenterTest<P extends BasePresenter<V>, V extends BaseMvpView> {

    @Mock
    ErrorHandlerHelper errorHandlerHelper;
    @Mock
    DataManager dataManager;

    public V view;
    public P presenter;
    public CompositeDisposableHelper compositeDisposableHelper;
    public TestScheduler testScheduler;

    @CallSuper
    @Before
    public void before() {
        initMocks(this);

        testScheduler = new TestScheduler();
        compositeDisposableHelper = new CompositeDisposableHelper(new CompositeDisposable(),
                new TestSchedulerProvider(testScheduler));

        view = createView();
        presenter = createPresenter();
        presenter.attachView(view);
    }

    @CallSuper
    @After
    public void tearDown() throws Exception {
        presenter.detachView();
    }

    abstract P createPresenter();
    abstract V createView();
}
