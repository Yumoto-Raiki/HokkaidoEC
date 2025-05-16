package com.example.demo.Model.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConectDao {
	/** ロガー（ログ出力用） */
	private static final Logger LOGGER = Logger.getLogger(DBConectDao.class.getName());

	/** DB接続オブジェクト（Connection） */
	protected Connection con = null;

	/** DB接続情報：接続先のURI（ホスト、ポート番号、DB名などを指定） */
	private static final String DB_URI = "jdbc:mysql://192.168.100.51:3306/hba2025_3?characterEncoding=utf8&useSSL=false"
			+ "&serverTimezone=GMT%2B9&rewriteBatchedStatements=true";

	/** DB接続情報：ユーザー名 */
	private static final String DB_USER = "newuser";

	/** DB接続情報：パスワード */
	private static final String DB_PASS = "hba2025";

	/**
	 * connectメソッド：データベースへの接続を開始する。
	 * 
	 * ・JDBCドライバのクラスをロードする ・DriverManagerを使ってDBに接続する
	 * ・接続に成功した場合、Connectionオブジェクトを生成し、conに保持する
	 * 
	 * 注意： JavaSE 8 以降ではClass.forNameが不要な場合もあるが、Tomcatなどサーブレット環境では 明示的にロードするのが安全である。
	 */
	protected void connect() {
		try {
			// MySQLドライバクラスをロード
			Class.forName("com.mysql.cj.jdbc.Driver");

			// DriverManagerを通じてDB接続を確立し、接続成功時はConnectionインスタンスを取得
			con = DriverManager.getConnection(DB_URI, DB_USER, DB_PASS);

			// 接続成功のログを出力
			LOGGER.log(Level.INFO, "データベースへの接続に成功しました。");

		} catch (SQLException | ClassNotFoundException e) {
			// SQL例外 または クラス（ドライバ）不在の場合
			LOGGER.log(Level.SEVERE, "データベースへの接続に失敗しました。", e);
		}
	}

	/**
	 * closeメソッド：データベースとの接続を切断する。
	 * 
	 * ・Connectionオブジェクトが非nullの場合はクローズを試みる ・クローズ後、conをnullにしておくことで再利用を防止
	 * 
	 * 注意： このクラスを継承する各DAOクラスでは、クエリ実行後に必ずclose()を呼び出して リソースを解放するようにすることが望ましい。
	 */
	protected void close() {
		try {
			if (con != null) {
				con.close(); // DB接続をクローズ
				con = null; // 再利用を防ぐためにnullを代入
			}
			// クローズに成功した場合は標準出力（コンソール）にメッセージを表示
			System.out.println("データベースからの切断に成功しました。");

		} catch (SQLException e) {
			// DB切断時のエラー
			String errMsg = "データベースからの切断時に問題が発生しました。";
			System.err.println(errMsg);
			System.err.println(e);
		}
	}

	/**
	 * メインメソッド（テスト用）
	 * 
	 * ・このクラスが単独で動作することをテストするためのエントリーポイント。
	 * ・SuperDaoインスタンスを生成し、connect→closeメソッドを呼び出して 正常に接続／切断が行えるかを確認できる。
	 * 
	 * @param args コマンドライン引数（未使用）
	 */
	public static void main(String[] args) {
		DBConectDao superDao = new DBConectDao();
		superDao.connect();
		superDao.close();
	}

}
