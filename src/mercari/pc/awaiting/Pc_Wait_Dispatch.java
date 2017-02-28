package mercari.pc.awaiting;

import static common.constant.MercariConstants.*;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import mercari.bean.OutputBean;
import mercari.excel.AllOutput;
import mercari.pc.Pc_Mercari;

/**
 * =====================================================================================================================
 * 【発送待ち】商品の抽出して「商品の発送をしたので、発送通知をする」クリックし、EXCELで出力する（PC版メルカリ）
 * =====================================================================================================================
 *
 * @author kimC
 *
 */
public class Pc_Wait_Dispatch extends Pc_Mercari {
	//==================================================================================================================
	// 定数
	//==================================================================================================================

	//==================================================================================================================
	// メルカリアカウント
	//==================================================================================================================
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
	JavascriptExecutor executor = (JavascriptExecutor)driver;
	/** WindowsID */
	String originalHandel;

	/**
	 * コンストラクタ
	 *
	 * @param String id メルカリカウントID
	 * @param String pass メルカリカウントパスワード
	 *
	 * @author kimC
	 *
	 */
	public Pc_Wait_Dispatch(String id, String pass) {
		// ユーザーID
		this.userId = id;
		// ユーザーパスワード
		this.userPass = pass;
		super.login(this.userId, this.userPass);
		originalHandel = driver.getWindowHandle();
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
			// アカウント名を設定する
			this.setName();
			// 【出品した商品 - 取引中】画面
			driver.get(PC_IN_PROGRESS_URL);
			for(int i = 0;i < 10000;i++){
				// 【出品した商品 - 取引中】画面メッセージ
				message = driver.findElement(By.id("mypage-tab-transaction-old")).getText();
				if(message.matches(STR_IN_PROGRESS)){
					break;
				}else{
					// 商品一覧数を取得する
					int p_count = driver.findElement(By.id("mypage-tab-transaction-old")).findElements(By.tagName("li")).size();
					// 【発送待ち】商品を検索し、新しいタブで商品詳細画面を開く
					for(int j = 0; j < p_count; j++){
						if(this.getStatus(j).equals(STR_WAIT_1)){
							// 「発送待ち」
							list.add(this.getBean(j));
							// 「商品の発送をしたので、発送通知をする」クリックする
							this.click_button();
							// メインタブに移動
							driver.switchTo().window(originalHandel);
						}
					}
					// 「次のページ」
					this.pagerNext();
				}
			}
			// 商品リストをEXCELで出力する
			AllOutput output = new AllOutput();
			// 発送待ち
			if(list.size() > 0){
				output.execute(userId, list, STR_WAIT_1);
			}
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
		String name = driver.findElements(By.xpath("//ul[@class='transact-info-table-cell']")).get(INT_0).getText();
		if(StringUtils.isNotEmpty(name)){
			// 商品名
			bean.setName(name.split("\n")[0]);
			// 販売価格
			bean.setPrice(name.split("\n")[1]);
		}
		// 販売手数料
		String commission = driver.findElements(By.xpath("//ul[@class='transact-info-table-cell']")).get(INT_2).getText();
		if(StringUtils.isNotEmpty(commission)){
			bean.setCommission(commission);
		}
		// 販売利益
		String profit = driver.findElements(By.xpath("//ul[@class='transact-info-table-cell']")).get(INT_3).getText();
		if(StringUtils.isNotEmpty(profit)){
			bean.setProfit(profit);
		}
		// 商品ID
		String id = driver.findElements(By.xpath("//ul[@class='transact-info-table-cell']")).get(INT_5).getText();
		if(StringUtils.isNotEmpty(id)){
			bean.setId(id);
		}
		// お届け先
		String delivery = driver.findElements(By.xpath("//ul[@class='transact-info-table-cell']")).get(INT_6).getText();
		delivery = delivery.replaceAll("\n", "　");
		if(StringUtils.isNotEmpty(delivery)){
			bean.setDelivery(delivery);
		}
		return bean;
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
