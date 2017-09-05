package com.georgeren.androidmiddle.scope;

import dagger.Component;

/**
 * Created by georgeRen on 2017/9/5.
 * Scope 共享：这个User的单例局部于 ScopeActivity 和 TestActivity1
 */
@UserScope
@Component(modules = UserModule.class)
public interface ActivityComponent {
    void inject(ScopeActivity activity);
    void inject(TestActivity1 activity);
}
