Doma から SQL Database に接続するサンプル

SQL Database では必須なリトライ処理をどこに入れるか考え中。

SQL Database のリトライについてはここらへんが参考になる。  
http://blogs.msdn.com/b/jpsql/archive/2014/10/22/sql-database-windows-azure-sql-database.aspx
https://code.msdn.microsoft.com/windowsazure/SQL-Azure-Retry-Logic-2d0a8401

DataSource を wrap して DataSource#getConnection のタイミングでリトライするべきか、  
各 Dao のメソッド呼び出しでリトライするべきかどっちが良いのか不明。  

getConnection だと、プログラムはリトライの事を考えなくて良くなる。  
ただし、デッドロックのリトライは無理ー。  

Dao の呼び出し側で書くと好きな物に対してリトライ掛けれるが面倒。  

getConnection でリトライするパターンは  
sqldatabase_doma_retry.retryconnection パッケージ  

各Dao でリトライするパターンは  
sqldatabase_doma_retry.retrydao パッケージ  

/libs には、sqljdbc41.jar を配置してください。  
http://www.microsoft.com/ja-jp/download/details.aspx?id=11774
