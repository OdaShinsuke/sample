Swagger で JAX-RS の JSON を Gradle で出力するサンプル

Swagger のサンプルで多いのは、WebAPIサーバーに直接載せてしまう方法だが、それはやりたくないので build 時に api-docs.json を出力するサンプル。

Gradle で 自身の output を classpath に渡す方法が良くわからなかったので、マルチプロジェクト で実装。
flat (not use master フォルダ) な マルチプロジェクト を用いることで、JAX-RS アプリケーション の app は シングルプロジェクト としても ビルド 可能。

```
cd app
gradle war
```

Swagger の JSON を吐く swagger_json は root より ビルドする。
swagger_json プロジェクトは、app に依存しているので、先に :app:jar を実行しておく必要がある。
```
cd root
gradle jar
gradle generateSwaggerJson
```

汎用的にするなら、Reader に渡す クラスは app.jar から Resource クラスだけかき集めるようにコードを書く必要がある。
(※JarURLConnection とかを使う)
