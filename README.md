# 공부(コンブ)
造語や流行語など、一般辞書にはない韓国語のスラングをユーザーが自ら登録するオープン辞書サイトです。<br/>
~~https://gongbu.jp/~~ (サービス終了)

## 공부(コンブ)を作ったきっかけ
仕事で携わってない技術を使ってみたかったので、공부(コンブ)を作ることになりました。<br>
今の仕事ではEOLを迎えたフレームワークで開発し、テストケースをエクセルに書いて人がアプリケーションを動きながらテストを行います。<br>
そんな中、CI/CDについて分かるようになり、すごく便利で、人を楽にさせるものだなという印象を受けました。<br>
そして「自らやってみたい！」と思いました。<br>
そのため、テストコードの作成、CI/CDによるサイトの運営に日々精進してまいります。💪

## 공부(コンブ)の特徴
- oauth2を使用したgoogle、またはLINEアカウントでソーシャルログインが可能
- ことばの登録・修正・削除(ログインユーザーのみ可)/検索/ソート
- マイページで登録したことばの管理、会員脱会(ログインユーザーのみ可)

## 開発環境
- Vagrant 2.2.18
- Linux(Kali 5.10.0)
- Docker 20.10.11
- Docker Compose 1.29.2

## バージョン情報
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
     + [関連するプルリクエスト](https://github.com/crane93/gongbu/pulls?q=is%3Apr+is%3Aclosed+label%3Aphase1)
   - アプリケーションのアーキテクチャ
   ![image](https://user-images.githubusercontent.com/44425582/194066133-4075faa3-1f0b-45bc-9e5d-ea89665ea562.png)
     
2. インフラ構築
   - ステータス：完了
   - 実施期間：2022/04中旬~2022/05中旬
   - 作業内容：github actionとaws codepipeline(code build, code deploy)でCI/CD環境を整える、aws ecsでアプリケーションが入っているDocker コンテナを実行する
     + [関連するプルリクエスト](https://github.com/crane93/gongbu/pulls?q=is%3Apr+is%3Aclosed+label%3A%E3%82%A4%E3%83%B3%E3%83%95%E3%83%A9)
   - インフラ構成図
   ![infra image](https://user-images.githubusercontent.com/44425582/169836756-9aa6c01e-a1d6-4028-8282-55d62b8ecbb9.png)
3. phase2 
   - ステータス：完了
   - 実施期間：2022/05中旬~2022/07/20
   - 作業内容：テストコード作成、不具合対応
     + [関連するプルリクエスト](https://github.com/crane93/gongbu/pulls?q=is%3Apr+is%3Aclosed+label%3Aphase2+label%3Amerged%21)
4. phase3
   - ステータス：着手中
   - 実施期間：2022/07/末~
   - 作業内容：ちょこちょこ機能追加
     + [関連するプルリクエスト](https://github.com/crane93/gongbu/pulls?q=is%3Apr+is%3Aclosed+label%3Aphase3)

## 反省点
- ブランチの主題と関係ないコミットをしてしまったこと
  + 作業中にブランチの内容と関係ない問題が発生したら別途ブランチを切って、そこで解決してまたプルしたら良かったかなと思う
- 一つのコミットに複数の作業内容が含まれていること
  + 1つの作業に1つのコミットをしていたら、もしものときエラーの原因になるコミットだけ打消しができるからかな
