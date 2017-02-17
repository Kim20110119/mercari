package mercari.pc;

import static common.Common.*;
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
		try{
			// ログインメールアドレス
			driver.findElement(By.name("email")).sendKeys(id);
			// ログインパスワード
			driver.findElement(By.name("password")).sendKeys(pass);
			// ロボットチェック
			driver.findElement(By.className("g-recaptcha")).click();
			Boolean loginFlag = Boolean.TRUE;
			// 1時間後に手動でログインしてない場合、自動でログインするようにする(※　認証してないとログインエラー表示)
			for(int i = 0; i < 3600; i++){
				// 現在のURLを取得する
				String url = driver.getCurrentUrl();
				// ログイン後URLかを判断する
				if(url.equals("https://www.mercari.com/jp/")){
					loginFlag = Boolean.FALSE;
					break;
				}
				// 10秒待ち
				sleep(1000);
			}
			if(loginFlag){
				// ログインボタン
				driver.findElement(By.className("login-submit")).click();
			}
		}catch (Exception e){
			System.out.println("【エラー】：ログイン失敗しました。");
			driver.quit();
		}
	}

}
