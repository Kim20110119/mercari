package mercari.pc.comment;

import static common.constant.MercariConstants.*;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import mercari.bean.AccountBean;

/**
 * =====================================================================================================================
 * コメントある商品の抽出（PC版メルカリ）
 * =====================================================================================================================
 *
 * @author kimC
 *
 */
public class Pc_Comment{
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
	/** メルカリユーザーパスワード */
	String userPass;
	/** 出品一覧メッセージ */
	String message;

	//==================================================================================================================
	// JavaScript
	//==================================================================================================================
	/** JavaScript */
	JavascriptExecutor executor;

	/**
	 * コンストラクタ
	 *
	 * @param AccountBean account メルカリカウント情報
	 *
	 * @author kimC
	 *
	 */
	public Pc_Comment(AccountBean account) {
		// ユーザーID
		this.userId = account.getMail();
		// ユーザーパスワード
		this.userPass = account.getPassword();
		// Chromeドライバーをプロパティへ設定
		System.setProperty("webdriver.chrome.driver", "lib/chromedriver.exe");
		if(StringUtils.isNotEmpty(account.getUserAgent())){
			// ユーザーエージェントを上書きして、起動する
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--user-agent=" + account.getUserAgent() );
			driver = new ChromeDriver(options);
		}else{
			driver = new ChromeDriver();
		}
		executor = (JavascriptExecutor)driver;
		// ログイン処理
		this.login();
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
	 * コメントある商品の抽出処理
	 * =================================================================================================================
	 *
	 * @return Boolean コメントある商品の抽出結果
	 *
	 * @author kimC
	 *
	 */
	public Boolean execute() {
		try {
			// 【出品した商品 - 出品中】画面
			driver.get(PC_LISTING_URL);

			for(int i = 0;i < 10000;i++){
				// 【出品した商品 - 出品中】画面メッセージ
				message = driver.findElement(By.id("mypage-tab-transaction-now")).getText();
				if(message.matches(STR_LISTING)){
					break;
				}else{
					// 商品一覧数を取得する
					int p_count = driver.findElement(By.id("mypage-tab-transaction-now")).findElements(By.tagName("li")).size();
					// コメントある商品を検索し、新しいタブで商品詳細画面を開く
					for(int j = 0; j < p_count; j++){
						if(this.getCommentCount(j) > 0){
							this.openTab(this.getDetailUrl(j));
						}
					}
					// 「次のページ」
					this.pagerNext();
				}
			}
			return Boolean.TRUE;
		} catch (Exception e) {
			System.out.println("【エラー】：コメントある商品の抽出処理が失敗しました。");
			System.out.println(e.getMessage());
			return Boolean.FALSE;
		}
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
			url = driver.findElement(By.id("mypage-tab-transaction-now")).findElements(By.tagName("li")).get(i).findElement(By.tagName("a")).getAttribute("href");
		}catch(Exception e){
		}
		return url;
	}

	/**
	 * =================================================================================================================
	 * 「コメントカウント数」を取得する
	 * =================================================================================================================
	 *
	 * @param int i インデクス
	 *
	 * @author kimC
	 *
	 */
	public Integer getCommentCount(int i) {
		try{
			String str_c_count = StringUtils.EMPTY;
			str_c_count = driver.findElement(By.id("mypage-tab-transaction-now")).findElements(By.tagName("li")).get(i).findElements(By.className("listing-item-count")).get(INT_1).getText();
			if(StringUtils.isNotEmpty(str_c_count)){
				return Integer.parseInt(str_c_count);
			}
		}catch(Exception e){
		}
		return 0;

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
		executor.executeScript("scroll(" + x + ", " + y + ");");
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
