package mercari.pc;

import static common.constant.MercariConstants.*;

import org.openqa.selenium.By;

import common.Selenium;

/**
 * メルカリ：共通処理
 *
 * @author kimC
 *
 */
public class Pc_Mercari extends Selenium {

	public Pc_Mercari() {
		// ログイン画面
		driver.get(PC_LOGIN_URL);
		// ログインメールアドレス
		driver.findElement(By.name("email")).sendKeys("zcorsuxua7@yahoo.co.jp");
		// ログインパスワード
		driver.findElement(By.name("password")).sendKeys("afjkl253");
		// ロボットチェック
		driver.findElement(By.className("g-recaptcha")).click();
		// 10秒待ち
		sleep(10000);
		// ログインボタン
		driver.findElement(By.className("login-submit")).click();
	}

}
