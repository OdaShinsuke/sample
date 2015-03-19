arquillian を Gradle で使う用のサンプル

Gradle Importer (Gradle で作成した war) を使うパターンでは無い。
```/build/output/lib``` に 依存している jar をコピーし、それを ShrinkWrap に指定する

また、manage と remote (arquillian.xml のコンテナ)の切替も

manage でテスト
```gradle clean test```
remote でテスト
```gradle clean wildflyRemoteTest```
