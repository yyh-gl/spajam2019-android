# フロントエンド開発用リポジトリ  
-----------------------------------
## ディレクトリ構成
-----------------------------------
## アーキテクチャ
-----------------------------------
## 使用ライブラリ
- retrofit2.4.0
- Gson2.8.2
- OkHttp3.10.0
- rxjava2.1.11
- Dagger2.13

## retrofit2
Square製のRESTクライアントライブラリ

参考URL: https://qiita.com/hymmr/items/cbb1013617cd43b8c7c4

## Gson
Googleが提供するJSONデータとJavaオブジェクトを相互に変換するためのライブラリ  
今回はHTTPレスポンスのJSONデータをJavaオブジェクトに変換する際に使用  
`Models`にPOJOスキーマを設定し，そのスキーマに従って格納  
`JSON形式→POJOの変換`には http://www.jsonschema2pojo.org/ を使用

参考URL: https://qiita.com/u-chida/items/cbdd040e4199a10936dc
## OkHttp
Square製のHTTP通信とSPDY通信をするためのクライアント用ライブラリ  
今回はOkHttp単体ではなくretrofit2と組み合わせ，主にlogging部分に使用

参考URL: https://qiita.com/joji/items/488b85c90b5147c99e3a

## rxjava2
androidでは外部通信に関してはサブスレッドで実装する必要がある．　  
これに関して今までのハッカソンでは`Thread`や`AsyncTask`等を用いて実装して来たがメインスレッドとの同期が困難であった．　  
そこで今回は非同期処理部分として`rxjava2`を使用．
今回は簡単のためにGETリクエスト時は`Single<T>`を，POSTリクエスト時には`Completable`を返却

## Dagger2
DIディレクトリの中に各ModuleおよびComponentを作成
### Module
ModuleではInjectしたいDependencyを提供する  
#### AppModule
Application全体にInjectしたいDependencyを提供
#### ApiModule
Api利用に関するDependencyを提供
- Okhttp
- Retrofit2
- 各Serviceインスタンス
#### ActivityModule
各Activityで利用するDependencyを提供
- 各ContractのView

### Component
ModuleとDependencyをInjectされる側をつなぐ役目を担う
#### AppComponent
#### ActivityComponent


-----------------------------------
## gitのbranch運用に関して
### 1. 運用は"git flow"に従う([ref](http://ism1000ch.hatenablog.com/entry/2014/03/31/152441))
#### master
ほとんどさわらない

#### develop
開発期間中はほとんどこのブランチにマージする

#### feature
個別機能の実装を行うブランチ  
命名規約 : feature/[fileNameUnderDevelopment]/[versionNumber]

### 2. branchを切る場合は実装予定の内容をissueに設定しておく
- issueはbitbucket上では「課題」と表示されている
- 「課題」に設定すべき内容を記述したので[こちらを参照](https://bitbucket.org/ISDL_EUC/looci/issues?status=new&status=open)
-----------------------------------

