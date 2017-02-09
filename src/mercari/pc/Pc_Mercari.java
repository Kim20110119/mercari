package mercari.pc;

import static common.constant.MercariConstants.*;

import org.openqa.selenium.By;

import mercari.Mercari;

/**
 * メルカリ：共通処理
 *
 * @author kimC
 *
 */
public class Pc_Mercari extends Mercari {

	/**
	 * コンストラクタ
	 */
	public Pc_Mercari() {
		// ログイン画面
		driver.get(PC_LOGIN_URL);
	}

	/**
	 * =================================================================================================================
	 * メルカリログイン処理
	 * =================================================================================================================
	 *
	 * @param String id ユーザーID
	 * @param String pass ユーザーパスワード
	 *
	 * @author kimC
	 *
	 */
	public void login(String id, String pass){
		// ログインメールアドレス
		driver.findElement(By.name("email")).sendKeys(id);
		// ログインパスワード
		driver.findElement(By.name("password")).sendKeys(pass);
		// ロボットチェック
		driver.findElement(By.className("g-recaptcha")).click();
		// 10秒待ち
		sleep(10000);
		// ログインボタン
		driver.findElement(By.className("login-submit")).click();
	}

}
