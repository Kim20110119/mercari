package mercari.pc.output;

import static common.constant.MercariConstants.*;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import mercari.bean.OutputBean;
import mercari.excel.Output;
import mercari.pc.Pc_Mercari;

/**
 * =====================================================================================================================
 * 評価待ち商品の抽出（PC版メルカリ）
 * =====================================================================================================================
 *
 * @author kimC
 *
 */
public class Pc_Output extends Pc_Mercari {
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
	/** WindowsID */
	String originalHandel;
	/** 「発送待ち」商品リスト */
	List<OutputBean> list_0 = new ArrayList<OutputBean>();
	/** 「受取評価待ち」商品リスト */
	List<OutputBean> list_1 = new ArrayList<OutputBean>();;
	/** 「評価待ち」商品リスト */
	List<OutputBean> list_2 = new ArrayList<OutputBean>();;


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
	public Pc_Output(String id, String pass) {
		// ユーザーID
		this.userId = id;
		// ユーザーパスワード
		this.userPass = pass;
		super.login(this.userId, this.userPass);
		originalHandel = driver.getWindowHandle();
	}

	/**
	 * =================================================================================================================
	 * 評価待ち商品の抽出処理
	 * =================================================================================================================
	 *
	 * @return Boolean 評価待ち商品の抽出結果
	 *
	 * @author kimC
	 *
	 */
	public Boolean execute() {
		try {
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
					// コメントある商品を検索し、新しいタブで商品詳細画面を開く
					for(int j = 0; j < p_count; j++){
						String status = this.getStatus(j);
						if(status.equals(STR_WAIT_0)){
							// 「発送待ち」
							list_0.add(this.getBean(j));
							this.tab_close();
						}else if(status.equals(STR_WAIT_1)){
							// 「受取評価待ち」
							list_1.add(this.getBean(j));
							this.tab_close();
						}else if(status.equals(STR_WAIT_2)){
							// 「評価待ち」
							list_2.add(this.getBean(j));
							this.tab_close();
						}else{
						}
					}
					// 「次のページ」
					this.pagerNext();
				}
			}
			// 商品リストをEXCELで出力する
			Output output = new Output();
			if(list_0.size() > 0){
				output.execute(userId, list_0, STR_WAIT_0);
			}
			if(list_1.size() > 0){
				output.execute(userId, list_1, STR_WAIT_1);
			}
			if(list_2.size() > 0){
				output.execute(userId, list_2, STR_WAIT_2);
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
			url = driver.findElement(By.id("mypage-tab-transaction-old")).findElements(By.tagName("li")).get(i).findElement(By.tagName("a")).getAttribute("href");
		}catch(Exception e){
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
		}
		return status;
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
	public OutputBean getBean(int i) {
		// 取引中商品詳細画面を開く
		this.openTab(this.getDetailUrl(i));
		// 商品詳細画面タブへ遷移する
		driver.get(this.getUrlByTab());
		// 出力商品Bean
		OutputBean bean = new OutputBean();
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
