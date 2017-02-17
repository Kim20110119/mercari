package mercari.wifi;

import static common.Common.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * =====================================================================================================================
 * Wifi再起動
 * =====================================================================================================================
 *
 * @author kimC
 *
 */
public class Restart {

	/**
	 * コンストラクタ
	 */
	public Restart() {
	}

	/**
	 * =================================================================================================================
	 * Wifiを再起動する(WiMax)
	 * =================================================================================================================
	 *
	 * @author kimC
	 *
	 */
	public void execute(String password) {
		try{
			// Chromeドライバーをプロパティへ設定
			System.setProperty("webdriver.chrome.driver", "lib/chromedriver.exe");
			/** 「WEBドライバー」 */
			WebDriver driver = new ChromeDriver();
			driver.get("http://admin:" + password + "@192.168.179.1/index.cgi/reboot_main");
			driver.findElement(By.id("UPDATE_BUTTON")).click();
			driver.switchTo().alert().accept();
			sleep(100000);
			driver.switchTo().alert().accept();
			System.out.println("Wifi再起動成功！");
		}catch (Exception e) {
			System.out.println("【エラー】：Wifi再起動失敗！");
		}
	}
}
