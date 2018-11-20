package com.hl.core.lib.viewmodel;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.lifecycle.ViewModelStores;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import java.lang.reflect.InvocationTargetException;

/**
 * @Describe: VM管理
 * @Package: com.hl.core.lib.viewmodel
 * @Author: liyu
 * @Date: 2018/3/15 10:33
 * @Copyright: hl
 */
public class CoreViewModelProviders {

    @SuppressLint("StaticFieldLeak")
    private static CoreViewModelProviders.DefaultFactory sDefaultFactory;

    private static void initializeFactoryIfNeeded(Application application) {
        if (sDefaultFactory == null) {
            sDefaultFactory = new CoreViewModelProviders.DefaultFactory(application);
        }
    }

    private static Application checkApplication(Activity activity) {
        Application application = activity.getApplication();
        if (application == null) {
            throw new IllegalStateException("Your activity/fragment is not yet attached to "
                    + "Application. You can't request ViewModel before onCreate call.");
        }
        return application;
    }

    private static Activity checkActivity(Fragment fragment) {
        Activity activity = fragment.getActivity();
        if (activity == null) {
            throw new IllegalStateException("Can't create ViewModelProvider for detached fragment");
        }
        return activity;
    }

    /**
     * Creates a {@link ViewModelProvider}, which retains ViewModels while a scope of given
     * {@code fragment} is alive. More detailed explanation is in {@link ViewModel}.
     * <p>
     * It uses {@link ViewModelProviders.DefaultFactory} to instantiate new ViewModels.
     *
     * @param fragment a fragment, in whose scope ViewModels should be retained
     * @return a ViewModelProvider instance
     */
    @MainThread
    public static CoreViewModelProvider of(@NonNull Fragment fragment) {
        initializeFactoryIfNeeded(checkApplication(checkActivity(fragment)));
        return new CoreViewModelProvider(ViewModelStores.of(fragment), sDefaultFactory);
    }

    /**
     * Creates a {@link ViewModelProvider}, which retains ViewModels while a scope of given Activity
     * is alive. More detailed explanation is in {@link ViewModel}.
     * <p>
     * It uses {@link ViewModelProviders.DefaultFactory} to instantiate new ViewModels.
     *
     * @param activity an activity, in whose scope ViewModels should be retained
     * @return a ViewModelProvider instance
     */
    @MainThread
    public static CoreViewModelProvider of(@NonNull FragmentActivity activity) {
        initializeFactoryIfNeeded(checkApplication(activity));
        return new CoreViewModelProvider(ViewModelStores.of(activity), sDefaultFactory);
    }

    /**
     * Creates a {@link ViewModelProvider}, which retains ViewModels while a scope of given
     * {@code fragment} is alive. More detailed explanation is in {@link ViewModel}.
     * <p>
     * It uses the given {@link ViewModelProvider.Factory} to instantiate new ViewModels.
     *
     * @param fragment a fragment, in whose scope ViewModels should be retained
     * @param factory  a {@code Factory} to instantiate new ViewModels
     * @return a ViewModelProvider instance
     */
    @MainThread
    public static CoreViewModelProvider of(@NonNull Fragment fragment, @NonNull ViewModelProvider.Factory factory) {
        checkApplication(checkActivity(fragment));
        return new CoreViewModelProvider(ViewModelStores.of(fragment), factory);
    }

    /**
     * Creates a {@link ViewModelProvider}, which retains ViewModels while a scope of given Activity
     * is alive. More detailed explanation is in {@link ViewModel}.
     * <p>
     * It uses the given {@link ViewModelProvider.Factory} to instantiate new ViewModels.
     *
     * @param activity an activity, in whose scope ViewModels should be retained
     * @param factory  a {@code Factory} to instantiate new ViewModels
     * @return a ViewModelProvider instance
     */
    @MainThread
    public static CoreViewModelProvider of(@NonNull FragmentActivity activity,
                                       @NonNull ViewModelProvider.Factory factory) {
        checkApplication(activity);
        return new CoreViewModelProvider(ViewModelStores.of(activity), factory);
    }

    /**
     * {@link ViewModelProvider.Factory} which may create {@link AndroidViewModel} and
     * {@link ViewModel}, which have an empty constructor.
     */
    @SuppressWarnings("WeakerAccess")
    public static class DefaultFactory extends CoreViewModelProvider.NewInstanceFactory {

        private Application mApplication;

        /**
         * Creates a {@code DefaultFactory}
         *
         * @param application an application to pass in {@link AndroidViewModel}
         */
        public DefaultFactory(@NonNull Application application) {
            mApplication = application;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if (AndroidViewModel.class.isAssignableFrom(modelClass)) {
                //noinspection TryWithIdenticalCatches
                try {
                    return modelClass.getConstructor(Application.class).newInstance(mApplication);
                } catch (NoSuchMethodException e) {
                    throw new RuntimeException("Cannot create an instance of " + modelClass, e);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Cannot create an instance of " + modelClass, e);
                } catch (InstantiationException e) {
                    throw new RuntimeException("Cannot create an instance of " + modelClass, e);
                } catch (InvocationTargetException e) {
                    throw new RuntimeException("Cannot create an instance of " + modelClass, e);
                }
            }
            return super.create(modelClass);
        }
    }



}
