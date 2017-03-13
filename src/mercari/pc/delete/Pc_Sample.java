package mercari.pc.delete;

import static common.constant.MercariConstants.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import mercari.bean.OutputBean;

/**
 * =====================================================================================================================
 * 【発送待ち】商品の抽出して「商品の発送をしたので、発送通知をする」クリックし、EXCELで出力する（PC版メルカリ）
 * =====================================================================================================================
 *
 * @author kimC
 *
 */
public class Pc_Sample{
	//==================================================================================================================
	// 定数
	//==================================================================================================================

	//==================================================================================================================
	// メルカリアカウント
	//==================================================================================================================
	/** WEBドライバー */
	WebDriver driver;
	/** メルカリユーザーID */
	String userId;
	/** メルカリユーザー名 */
	String userName;
	/** メルカリユーザーパスワード */
	String userPass;
	/** 出品一覧メッセージ */
	String message;
	/** 「発送待ち」商品リスト */
	List<OutputBean> list = new ArrayList<OutputBean>();

	//==================================================================================================================
	// JavaScript
	//==================================================================================================================
	/** JavaScript */
	JavascriptExecutor executor;
	/** WindowsID */
	String originalHandel;
	/** WindowsIDリスト */
	List<String> handelList = new ArrayList<String>();

	/**
	 * コンストラクタ
	 *
	 * @param AccountBean account メルカリカウント情報
	 *
	 * @author kimC
	 *
	 */
	public Pc_Sample() {
		// Chromeドライバーをプロパティへ設定
		System.setProperty("webdriver.chrome.driver", "lib/chromedriver.exe");
		// ユーザーエージェントを上書きして、起動する
		ChromeOptions options = new ChromeOptions();
		File paths = new File("lib/8.4.4_0.crx");
		options.addExtensions(paths);
		driver = new ChromeDriver(options);
		driver = new ChromeDriver();
		executor = (JavascriptExecutor)driver;
		this.sleep(10000);
		// オリジナルWindowsIDを取得
		originalHandel = driver.getWindowHandle();
		handelList.add(originalHandel);
	}

	/**
	 * =================================================================================================================
	 * メルカリログイン処理
	 * =================================================================================================================
	 *
	 * @author kimC
	 *
	 */
	public void login(){
		try{
			// ログイン画面
			driver.get(PC_LOGIN_URL);
			// ログインメールアドレス
			driver.findElement(By.name("email")).sendKeys(this.userId);
			// ログインパスワード
			driver.findElement(By.name("password")).sendKeys(this.userPass);
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

	/**
	 * =================================================================================================================
	 * 【発送待ち】商品の抽出処理
	 * =================================================================================================================
	 *
	 * @return Boolean 【発送待ち】商品の抽出結果
	 *
	 * @author kimC
	 *
	 */
	public Boolean execute() {
		try {
			// 【出品した商品 - 取引中】画面
			driver.get("www.yahoo.co.jp");
			return Boolean.TRUE;
		} catch (Exception e) {
			System.out.println("【エラー】：【発送待ち】商品の抽出処理が失敗しました。");
			System.out.println(e.getMessage());
			return Boolean.FALSE;
		}
	}

	/**
	 * =================================================================================================================
	 * 「アカウント名」を取得する
	 * =================================================================================================================
	 *
	 * @author kimC
	 *
	 */
	public void setName() {
		String name = StringUtils.EMPTY;
		try{
			// マイページ
			driver.get(PC_MYPAGE_URL);
			name = driver.findElement(By.xpath("//h2[@class='bold']")).getText();
		}catch(Exception e){
			System.out.println("【エラー】：アカウント名を取得失敗。");
		}
		this.userName = name;
	}

	/**
	 * =================================================================================================================
	 * 「商品詳細画面URL」を取得する
	 * =================================================================================================================
	 *
	 * @param int i インデクス
	 *
	 * @author kimC
	 *
	 */
	public String getDetailUrl(int i) {
		String url = StringUtils.EMPTY;
		try{
			url = driver.findElement(By.id("mypage-tab-transaction-old")).findElements(By.tagName("li")).get(i).findElement(By.tagName("a")).getAttribute("href");
		}catch(Exception e){
			System.out.println("【エラー】：商品詳細画面URLを取得失敗。");
		}
		return url;
	}

	/**
	 * =================================================================================================================
	 * 取引中商品のスタータスを取得する
	 * =================================================================================================================
	 *
	 * @param int i インデクス
	 * @return String status 取引中商品のスタータス
	 *
	 * @author kimC
	 *
	 */
	public String getStatus(int i) {
		String status = StringUtils.EMPTY;
		try{
			status = driver.findElement(By.id("mypage-tab-transaction-old")).findElements(By.tagName("li")).get(i).findElement(By.className("mypage-item-status")).getText();
		}catch(Exception e){
			System.out.println("【エラー】：取引中商品のスタータスを取得失敗。");
		}
		return status;
	}

	/**
	 * =================================================================================================================
	 * 商品詳細画面から商品情報を取得する
	 * =================================================================================================================
	 *
	 * @param int i インデクス
	 * @return OutputBean bean 商品情報Bean
	 *
	 * @author kimC
	 *
	 */
	public OutputBean getBean(int i) {
		// 取引中商品詳細画面を開く
		this.openTab(this.getDetailUrl(i));
		// 商品詳細画面タブへ遷移する
		driver.get(this.getUrlByTab());
		// 出力商品Bean
		OutputBean bean = new OutputBean();
		// アカウント名
		bean.setAccount(this.userName);
		// 商品名
		String name = this.getValue(INT_0);
		if(StringUtils.isNotEmpty(name)){
			// 商品名
			bean.setName(name.split("\n")[0]);
			// 販売価格
			bean.setPrice(name.split("\n")[1]);
		}
		// 販売手数料
		String commission = this.getValue(INT_2);
		if(StringUtils.isNotEmpty(commission)){
			bean.setCommission(commission);
		}
		// 販売利益
		String profit = this.getValue(INT_3);
		if(StringUtils.isNotEmpty(profit)){
			bean.setProfit(profit);
		}
		// 商品ID
		String id = this.getValue(INT_5);
		if(StringUtils.isNotEmpty(id)){
			bean.setId(id);
		}
		// お届け先
		String delivery = this.getValue(INT_6);
		delivery = delivery.replaceAll("\n", "　");
		if(StringUtils.isNotEmpty(delivery)){
			bean.setDelivery(delivery);
		}
		return bean;
	}

	/**
	 * =================================================================================================================
	 * 商品詳細画面から商品情報を取得する
	 * =================================================================================================================
	 *
	 * @param int i インデクス
	 * @return String value 商品情報
	 *
	 * @author kimC
	 *
	 */
	public String getValue(int i) {
		String value = StringUtils.EMPTY;
		try{
			value = driver.findElements(By.xpath("//ul[@class='transact-info-table-cell']")).get(i).getText();
		}catch (Exception e) {
		}
		return value;
	}


	/**
	 * =================================================================================================================
	 * 「新しいタブ」を開く
	 * =================================================================================================================
	 *
	 * @param String url 商品詳細URL
	 *
	 * @author kimC
	 *
	 */
	public void openTab(String url) {
        executor.executeScript("window.open('"+ url +"','_blank')");
	}

	/**
	 * =================================================================================================================
	 * タブを取得する
	 * =================================================================================================================
	 *
	 * @author kimC
	 *
	 */
	public String getUrlByTab() {
		String tab_url = StringUtils.EMPTY;
		try {
			for (String handle : driver.getWindowHandles()) {
				if (!handelList.contains(handle)) {
					driver.switchTo().window(handle);
					handelList.add(handle);
					tab_url = driver.getCurrentUrl();
					return tab_url;
				}
			}
			return tab_url;
		} catch (Exception e) {
			return tab_url;
		}

	}

	/**
	 * =================================================================================================================
	 * 評価をクリックする
	 * =================================================================================================================
	 *
	 * @author kimC
	 *
	 */
	public void click_face() {
		try {
			driver.findElement(By.xpath("//label[@for='face1']")).click();
		} catch (Exception e) {
			System.out.println("【エラー】：評価をクリック失敗。");
		}

	}

	/**
	 * =================================================================================================================
	 * 「商品の発送をしたので、発送通知をする」をクリックする
	 * =================================================================================================================
	 *
	 * @author kimC
	 *
	 */
	public void click_button() {
		try {
			driver.findElements(By.xpath("//button[@class='btn-default btn-red']")).get(INT_0).click();
		} catch (Exception e) {
			System.out.println("【エラー】：「商品の発送をしたので、発送通知をする」をクリック処理が失敗しました。");
		}

	}

	/**
	 * =================================================================================================================
	 * タブを閉じる
	 * =================================================================================================================
	 *
	 * @author kimC
	 *
	 */
	public  void tab_close() {
		try {
			for (String handle : driver.getWindowHandles()) {
				if (!handle.equals(originalHandel)) {
					driver.switchTo().window(handle);
					driver.close();
				}
			}
			driver.switchTo().window(originalHandel);
		} catch (Exception e) {
			System.out.println("【エラー】：タブを閉じる処理が失敗しました。");
		}

	}

	/**
	 * =================================================================================================================
	 * スクロール処理
	 * =================================================================================================================
	 *
	 * @param 座標 x
	 * @param 座標 y
	 *
	 * @author kimC
	 *
	 */
	public void scroll(int x, int y) {
		try {
			executor.executeScript("scroll(" + x + ", " + y + ");");
		} catch (Exception e) {
			System.out.println("【エラー】：スクロール処理が失敗しました。");
		}
	}

	/**
	 * =================================================================================================================
	 * 「前のページ」へ遷移する
	 * =================================================================================================================
	 *
	 * @author kimC
	 *
	 */
	public void pagerPrev() {
		driver.findElement(By.className("pager-prev")).click();
	}

	/**
	 * =================================================================================================================
	 * 「後のページ」へ遷移する
	 * =================================================================================================================
	 *
	 * @author kimC
	 *
	 */
	public void pagerNext() {
		try{
			driver.findElement(By.className("pager-next")).click();
		}catch (Exception e){
			try{
				for(int i = 0; i < 10; i++){
					this.scroll(0, 100 * (i + 1));
					try{
						driver.findElement(By.className("pager-next")).click();
						break;
					}catch(Exception ex){
					}
				}
			}catch(Exception end_e){
				System.out.println("【エラー】：次ページ遷移する処理が失敗しました。");
			}
		}
	}

	/**
	 * =================================================================================================================
	 * ブラウザドライバーを終了する
	 * =================================================================================================================
	 *
	 * @author kimC
	 *
	 */
	public void driverQuit() {
		driver.quit();
	}

	/**
	 * sleep処理
	 *
	 * @param long millis 秒数
	 */
	public void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
