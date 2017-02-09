package mercari.pc.exhibit;

import static common.constant.MercariConstants.*;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import mercari.bean.ProductBean;
import mercari.pc.Pc_Mercari;

/**
 * =====================================================================================================================
 * PC版メルカリ出品処理
 * =====================================================================================================================
 *
 * @author kimC
 *
 */
public class Pc_Exhibit extends Pc_Mercari {
	//==================================================================================================================
	// 定数
	//==================================================================================================================
	/** サイズデフォルト */
	public final static String DEFAULT_SIZE = "FREE SIZE";
	/** 配送の方法デフォルト */
	public static final String DEFAULT_DELIVERY = "未定";

	//==================================================================================================================
	// 商品項目
	//==================================================================================================================
	/** 商品画像パス */
	String images_path;
	/** 商品名 */
	String name;
	/** 商品の説明 */
	String description;
	/** カテゴリー（大） */
	String category_0;
	/** カテゴリー（中） */
	String category_1;
	/** カテゴリー（小） */
	String category_2;
	/** ブランド */
	String brand;
	/** サイズ */
	String size;
	/** 商品状態 */
	String state;
	/** 配送料の負担 */
	String delivery_f;
	/** 配送の方法 */
	String delivery_m;
	/** 配送元の地域 */
	String region;
	/** 発送までの日数 */
	String day;
	/** 価格 */
	String price;

	/**
	 * コンストラクタ
	 */
	public Pc_Exhibit(String id, String pass) {
		super.login(id, pass);
		// PC版出品画面
		driver.get(PC_EXHIBIT_URL);
	}

	/**
	 * =================================================================================================================
	 * 出品処理
	 * =================================================================================================================
	 *
	 * @return Boolean 出品結果
	 *
	 * @author kimC
	 *
	 */
	public Boolean execute(ProductBean bean) {
		try {
			this.setData(bean);
			// 商品名
			this.sendKeysByInput(INT_0,this.name);
			// 商品の説明
			this.sendKeysByTextArea(this.description);
			// カテゴリー（大分類）
			this.selectByText(INT_0,this.category_0);
			// カテゴリー（中分類）
			this.selectByText(INT_1,this.category_1);
			// カテゴリー（小分類）
			this.selectByText(INT_2,this.category_2);
			// 商品のブランド項目をチェックする
			if(StringUtils.isNotEmpty(this.brand)){
				this.sendKeysByInput(INT_1,this.brand);
			}
			// 出品商品のサイズ項目をチェックする
			if(this.size() == 7){
				// 出品商品のサイズ項目がない場合
				// 商品の状態
				this.selectByText(INT_3,this.state);
				// 配送料の負担
				this.selectByText(INT_4,this.delivery_f);

				if(this.size() == 7){
					// 発送元の地域
					this.selectByText(INT_5,this.region);
					// 発送までの日数
					this.selectByText(INT_6,this.day);
				}else{
					// 配送の方法
					if(StringUtils.isNotEmpty(this.delivery_f)){
						this.selectByText(INT_5,this.delivery_f);
					}else{
						this.selectByText(INT_5,DEFAULT_DELIVERY);
					}
					// 発送元の地域
					this.selectByText(INT_6,this.region);
					// 発送までの日数
					this.selectByText(INT_7,this.day);
				}
			}else if(size() > 7){
				// 出品商品のサイズ項目がある場合
				// サイズ
				if(StringUtils.isNotEmpty(this.size)){
					this.selectByText(INT_3,this.size);
				}else{
					this.selectByText(INT_3,DEFAULT_SIZE);
				}
				// 商品状態
				this.selectByText(INT_4,this.state);
				// 配送料の負担
				this.selectByText(INT_5,this.delivery_f);
				if(this.size() == 8){
					// 発送元の地域
					this.selectByText(INT_6,this.region);
					// 発送までの日数
					this.selectByText(INT_7,this.day);
				}else{
					// 配送の方法
					this.selectByText(INT_6,DEFAULT_DELIVERY);
					// 発送元の地域
					this.selectByText(INT_7,this.region);
					// 発送までの日数
					this.selectByText(INT_8,this.day);
				}
			}else{
				System.out.println("【エラー】：出品が失敗しました。");
				driver.quit();
				return Boolean.FALSE;
			}
			// 価格
			this.sendKeysByInput(INT_2,this.price);
			//「出品する」ボタンをクリックする
			this.click();
			// ブラウザドライバーを終了する
			driver.quit();
			return Boolean.TRUE;
		} catch (Exception e) {
			driver.quit();
			System.out.println("【エラー】：出品が失敗しました。");
			return Boolean.FALSE;
		}
	}

	/**
	 * =================================================================================================================
	 * 商品情報項目を設定する
	 * =================================================================================================================
	 *
	 * @author kimC
	 *
	 */
	public void setData(ProductBean bean) {
		// 商品画像
		this.images_path = bean.getImages_path();
		// 商品名
		this.name = bean.getName();
		// 商品の説明
		this.description = bean.getDescription();
		// カテゴリー（大）
		this.category_0 = bean.getCategory_0();
		// カテゴリー（中）
		this.category_1 = bean.getCategory_1();
		// カテゴリー（小）
		this.category_2 = bean.getCategory_2();
		// ブランド
		this.brand = bean.getBrand();
		// サイズ
		this.size = bean.getSize();
		// 商品状態
		this.state = bean.getState();
		// 配送料の負担
		this.delivery_f = bean.getDelivery_f();
		// 配送の方法
		this.delivery_m = bean.getDelivery_m();
		// 配送元の地域
		this.region = bean.getRegion();
		// 発送までの日数
		this.day = bean.getDay();
		// 価格
		this.price = bean.getPrice();
	}

	/**
	 * =================================================================================================================
	 * 入力テキスト
	 * =================================================================================================================
	 *
	 * @author kimC
	 *
	 */
	public void sendKeysByInput(Integer index, String value) {
		driver.findElements(By.className("input-default")).get(index).sendKeys(value);
	}

	/**
	 * =================================================================================================================
	 * 入力テキストエリア
	 * =================================================================================================================
	 *
	 * @author kimC
	 *
	 */
	public void sendKeysByTextArea(String value) {
		driver.findElement(By.className("textarea-default")).sendKeys(value);
	}

	/**
	 * =================================================================================================================
	 * セレクトブルダウン
	 * =================================================================================================================
	 *
	 * @author kimC
	 *
	 */
	public void selectByText(Integer index, String text) {
		Select select = new Select(driver.findElements(By.xpath("//select[@class='select-default']")).get(index));
		select.selectByVisibleText(text);
	}

	/**
	 * =================================================================================================================
	 * 「出品」ボタンをクリックする
	 * =================================================================================================================
	 *
	 * @author kimC
	 *
	 */
	public void click() {
		driver.findElement(By.className("btn-default")).click();
	}

	/**
	 * =================================================================================================================
	 * エレメント個数を取得する
	 * =================================================================================================================
	 *
	 * @return size
	 *
	 * @author kimC
	 *
	 */
	public Integer size() {
		Integer size = 0;
		try{
			size = driver.findElements(By.xpath("//select[@class='select-default']")).size();
			return size;
		}catch (Exception e){
			return size;
		}

	}

	/**
	 * sleep処理
	 *
	 * @param long
	 *            millis
	 */
	public void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
