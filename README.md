
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
