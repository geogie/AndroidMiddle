
## app: dagger2 + MVP
- app:dagger2 + MVP
- base文件夹MVP：抽出 BaseActivity范型初始化injector，共有presenter。IBasePresenter + IBaseView
- IMain:对presenter和view的抽取。MainPresenter：presenter实现。
- injector文件夹：对dagger2的准备，component:执行injector。module：连接M和P的桥梁。
- DaggerMainComponent：利用injector文件夹（component、module）下的类生成的中间件。

## rxBus: rxJava2 实现rxBus 
- app:dagger2 + MVP
- base文件夹MVP：抽出 BaseActivity范型初始化injector，共有presenter。IBasePresenter + IBaseView
- IMain:对presenter和view的抽取。MainPresenter：presenter实现。
- injector文件夹：对dagger2的准备，component:执行injector。module：连接M和P的桥梁。
- DaggerMainComponent：利用injector文件夹（component、module）下的类生成的中间件。

## drawer: 抽屉实现，白天\夜晚 模式切换，tooBar使用，rxBus事件，SettingUtil保存数据
- 抽屉实现：SVG图标，选择事件处理，开关按钮使用
- 白天\夜晚：主题切换，开关着色，抽屉背景色、item背景色、字体色、SVG图标色，

## retrofit: 网络请求
- retrofit
- rxJava

## fullwebview: webView 播放html中的视频
- 点击全屏响应：全屏
- bug：HUAWEI H60-L01 Android 4.4.2 API 19 没有全屏回调事件。只是其中一个手机，等待解决