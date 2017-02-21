package mercari.pc.comment;

import static common.constant.MercariConstants.*;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import mercari.pc.Pc_Mercari;

/**
 * =====================================================================================================================
 * コメントある商品の抽出（PC版メルカリ）
 * =====================================================================================================================
 *
 * @author kimC
 *
 */
public class Pc_Comment extends Pc_Mercari {
	//==================================================================================================================
	// 定数
	//==================================================================================================================

	//==================================================================================================================
	// メルカリアカウント
	//==================================================================================================================
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
	public Pc_Comment(String id, String pass) {
		// ユーザーID
		this.userId = id;
		// ユーザーパスワード
		this.userPass = pass;
		super.login(this.userId, this.userPass);
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
			this.scroll(0, 100);
			driver.findElement(By.className("pager-next")).click();
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
