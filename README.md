# 공부(コンブ)
造語や流行語など、一般辞書にはない韓国語のスラングをユーザーが自ら登録するオープン辞書サイトです。
https://gongbu.jp/

## 공부(コンブ)の特徴
- oauth2を使用したgoogle、またはLINEアカウントでソーシャルログインが可能
- ことばの登録・修正・削除(ログインユーザーのみ可)/検索/ソート
- マイページで登録したことばの管理、会員脱会(ログインユーザーのみ可)

## 開発環境
- Vagrant 2.2.18
- Linux(Kali 5.10.0)
- Docker 20.10.11
- Docker Compose 1.29.2
- Spring Boot 2.6.7
- Gradle 7.2.0
- Java 16.0.1
- postgres 11.14
- jpa 2.5.4
- thymeleaf 2.6.2
- terraform 1.1.3

## 進捗状況
1. phase1
   - ステータス：完了
   - 実施期間：2022/01~2022/04中旬
   - 作業内容：サイトの根幹になる言葉のCRUD、ログイン機能を実装
2. インフラ構築
   - ステータス：完了
   - 実施期間：2022/04中旬~2022/05中旬
   - 作業内容：github actionとaws codepipeline(code build, code deploy)でCI_CD環境を整える、aws ecsでアプリケーションが入っているDocker コンテナを実行する
   - インフラ構成図
   ![infra image](https://user-images.githubusercontent.com/44425582/169836756-9aa6c01e-a1d6-4028-8282-55d62b8ecbb9.png)
3. phase2 
   - ステータス：着手中
   - 実施期間：2022/05中旬~現在
   - 作業内容：テストコード作成、不具合対応