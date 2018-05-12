# ミドルウェア開発用リポジトリ  
-----------------------------------
## 開発規約  
### 1. 命名規約
- 英語のみ  
- 二単語以上の変数名をつける場合はキャメルケース  
- 定数はすべて大文字  
#### 以下は例
~~~~
#include <stdio.h>
#define CONSTANT 20

int main(void){

    int count;                // 1 character(e.g. i, j in for-loop) forbidden!
    const int MAX = CONSTANT; // the modifier 'const' means constant variable

    for(count = 0; count < MAX; count++) printf("counter : %d\n", count);
    
    return 0;
}
~~~~

### 2. コメントの書き方
- 個別に実装する場合はコメントを書くこと


### 3. 実装時におけるその他注意事項

-----------------------------------
## 開発環境に関して
### シェルの設定  
自身のシェル(bash or zsh)に以下の記述を追加
~~~~
export $LOOCI=/path/to/looci_directory
~~~~

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

-----------------------------------
## 開発環境に関して
### 1. シェルの設定
- 自身のシェル(bash or zsh)に以下の記述を追加
~~~~
export $LOOCI=/path/to/looci_directory
~~~~

### 2. branchを切る場合は実装予定の内容をissueに設定しておく
- issueはbitbucket上では「課題」と表示されている
- 「課題」に設定すべき内容を記述したので[こちらを参照](https://bitbucket.org/ISDL_EUC/looci/issues?status=new&status=open)

