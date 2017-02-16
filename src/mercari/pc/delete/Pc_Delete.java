package mercari.pc.delete;

import static common.constant.MercariConstants.*;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import mercari.pc.Pc_Mercari;

/**
 * =====================================================================================================================
 * コメント・お気に入りない商品の削除（PC版メルカリ）
 * =====================================================================================================================
 *
 * @author kimC
 *
 */
public class Pc_Delete extends Pc_Mercari {
	//==================================================================================================================
	// 定数
	//==================================================================================================================
	/** サイズデフォルト */
	public final static String DEFAULT_SIZE = "FREE SIZE";
	/** 配送の方法デフォルト */
	public static final String DEFAULT_DELIVERY = "未定";

	//==================================================================================================================
	// メルカリアカウント
	//==================================================================================================================
	/** メルカリユーザーID */
	String userId;
	/** メルカリユーザーパスワード */
	String userPass;
	/** 出品一覧メッセージ */
	String message;
	/** WindowsID */
	String originalHandel;

	//==================================================================================================================
	// JavaScript
	//==================================================================================================================
	/** JavaScript */
	JavascriptExecutor executor = (JavascriptExecutor)driver;

	/**
	 * コンストラクタ
	 *
	 * @param String id メルカリカウントID
	 * @param String pass メルカリカウントパスワード
	 *
	 * @author kimC
	 *
	 */
	public Pc_Delete(String id, String pass) {
		// ユーザーID
		this.userId = id;
		// ユーザーパスワード
		this.userPass = pass;
		super.login(this.userId, this.userPass);
		// WindowsIDを取得する
		originalHandel = driver.getWindowHandle();
	}

	/**
	 * =================================================================================================================
	 * コメント・お気に入りない商品の削除処理
	 * =================================================================================================================
	 *
	 * @return Boolean コメント・お気に入りない商品の削除結果
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
						if(this.getCommentCount(j) == 0 && this.getLikeCount(j) == 0){
							// この商品を削除する
							this.delete(j);
						}
					}
					// 「次のページ」
					this.pagerNext();
				}
			}
			return Boolean.TRUE;
		} catch (Exception e) {
			System.out.println("【エラー】：コメント・お気に入りない商品の削除が失敗しました。");
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
	 * 「お気に入り数」を取得する
	 * =================================================================================================================
	 *
	 * @param int i インデクス
	 *
	 * @author kimC
	 *
	 */
	public Integer getLikeCount(int i) {
		try{
			String str_c_count = StringUtils.EMPTY;
			str_c_count = driver.findElement(By.id("mypage-tab-transaction-now")).findElements(By.tagName("li")).get(i).findElements(By.className("listing-item-count")).get(INT_0).getText();
			if(StringUtils.isNotEmpty(str_c_count)){
				return Integer.parseInt(str_c_count);
			}
		}catch(Exception e){
		}
		return 0;

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
				if (!handle.equals(originalHandel)) {
					driver.switchTo().window(handle);
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
	 * タブを閉じる
	 * =================================================================================================================
	 *
	 * @author kimC
	 *
	 */
	public void tab_close() {
		try {
			for (String handle : driver.getWindowHandles()) {
				if (!handle.equals(originalHandel)) {
					driver.switchTo().window(handle);
					driver.close();
				}
			}
			driver.switchTo().window(originalHandel);
		} catch (Exception e) {
		}

	}

	/**
	 * =================================================================================================================
	 * 商品を削除する処理
	 * =================================================================================================================
	 *
	 * @author kimC
	 *
	 */
	public void delete(int i) {
		try {
			// 取引中商品詳細画面を開く
			this.openTab(this.getDetailUrl(i));
			// 商品詳細画面タブへ遷移する
			driver.get(this.getUrlByTab());
			// 「この商品を削除する」
			driver.findElement(By.xpath("//button[@data-modal='delete-item']")).click();
			// 「削除する」
			driver.findElements(By.className("modal-btn-submit")).get(INT_1).click();
			// タブを閉じる
			this.tab_close();
		} catch (Exception e) {
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
		driver.findElement(By.className("pager-next")).click();
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
