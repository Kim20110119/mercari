package mercari.pc.exhibit;

import static common.constant.MercariConstants.*;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

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
	/** 「再スタートフラグ」 */
	Boolean restart_flag = Boolean.FALSE;

	/**
	 * コンストラクタ
	 */
	public Pc_Exhibit() {
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
	public Boolean execute() {
		try {
			// 商品名
			driver.findElements(By.className("input-default")).get(INT_0).sendKeys("流行♪ オフショルダー 肩見せ オールインワン ブラック M");
			// 商品の説明
			driver.findElement(By.className("textarea-default")).sendKeys("即ご購入可能。数量限定品。");

			// カテゴリー（大分類）
			Select category_0 = new Select(driver.findElements(By.xpath("//select[@class='select-default']")).get(INT_0));
			category_0.selectByVisibleText("レディース");
			// カテゴリー（中分類）
			Select category_1 = new Select(driver.findElements(By.xpath("//select[@class='select-default']")).get(INT_1));
			category_1.selectByVisibleText("ワンピース");
			// カテゴリー（小分類）
			Select category_2 = new Select(driver.findElements(By.xpath("//select[@class='select-default']")).get(INT_2));
			category_2.selectByVisibleText("ミニワンピース");
			// 商品のブランド項目をチェックする
//			if(Boolean.TRUE){
//				driver.findElements(By.className("input-default")).get(INT_1).sendKeys("");
//			}
			// ブランド
			driver.findElements(By.className("input-default")).get(INT_1).sendKeys("");
			// 出品商品のサイズ項目をチェックする
			if(driver.findElements(By.xpath("//select[@class='select-default']")).size() == 7){
				// 出品商品のサイズ項目がない場合
				// 商品の状態
				Select status = new Select(driver.findElements(By.xpath("//select[@class='select-default']")).get(INT_3));
				status.selectByVisibleText("新品、未使用");
				// 配送料の負担
				Select fee = new Select(driver.findElements(By.xpath("//select[@class='select-default']")).get(INT_3));
				fee.selectByVisibleText("送料込み(出品者負担)");
				if(driver.findElements(By.xpath("//select[@class='select-default']")).size() == 7){
					// 発送元の地域
					Select region = new Select(driver.findElements(By.xpath("//select[@class='select-default']")).get(INT_5));
					region.selectByVisibleText("大阪府");
					// 発送までの日数
					Select day = new Select(driver.findElements(By.xpath("//select[@class='select-default']")).get(INT_6));
					day.selectByVisibleText("1~2日で発送");
				}else{
					String str_delivery = "";
					// 配送の方法
					Select delivery_m = new Select(driver.findElements(By.xpath("//select[@class='select-default']")).get(INT_5));
					if(StringUtils.isNotEmpty(str_delivery)){
						delivery_m.selectByVisibleText("ゆうメール");
					}else{
						delivery_m.selectByVisibleText("未定");
					}
					// 発送元の地域
					Select region_6 = new Select(driver.findElements(By.xpath("//select[@class='select-default']")).get(INT_6));
					region_6.selectByVisibleText("大阪府");
					// 発送までの日数
					Select day_7 = new Select(driver.findElements(By.xpath("//select[@class='select-default']")).get(INT_7));
					day_7.selectByVisibleText("1~2日で発送");
				}
			}else{
				// 出品商品のサイズ項目がある場合
				// 商品のブランド項目をチェックする
//				String str_size = "";
//				if(!empty(str_size)){
//
//	            If Worksheets(sheet_name).Cells(line, size_col).Value <> "" Then
//	                selenium.FindElementsByXPath("//select[@class='select-default']").Item(4).AsSelect.SelectByText (Worksheets(sheet_name).Cells(line, size_col).Value)           ' サイズ
//	            Else
//	                selenium.FindElementsByXPath("//select[@class='select-default']").Item(4).AsSelect.SelectByText (size_default)                                                 ' サイズ
//	            End If
//	            selenium.FindElementsByXPath("//select[@class='select-default']").Item(5).AsSelect.SelectByText (Worksheets(sheet_name).Cells(line, state_col).Value)              ' 商品の状態
//	            selenium.FindElementsByXPath("//select[@class='select-default']").Item(6).AsSelect.SelectByText (Worksheets(sheet_name).Cells(line, delivery_col).Value)           ' 配送料の負担
//	            If selenium.FindElementsByXPath("//select[@class='select-default']").Count = 8 Then
//	                selenium.FindElementsByXPath("//select[@class='select-default']").Item(7).AsSelect.SelectByText (Worksheets(sheet_name).Cells(line, region_col).Value)         ' 発送元の地域
//	                selenium.FindElementsByXPath("//select[@class='select-default']").Item(8).AsSelect.SelectByText (Worksheets(sheet_name).Cells(line, day_col).Value)            ' 発送までの日数
//	            Else
//	                selenium.FindElementsByXPath("//select[@class='select-default']").Item(7).AsSelect.SelectByText (delivery_default)                                             ' 配送の方法
//	                selenium.FindElementsByXPath("//select[@class='select-default']").Item(8).AsSelect.SelectByText (Worksheets(sheet_name).Cells(line, region_col).Value)         ' 発送元の地域
//	                selenium.FindElementsByXPath("//select[@class='select-default']").Item(9).AsSelect.SelectByText (Worksheets(sheet_name).Cells(line, day_col).Value)            ' 発送までの日数
//	            End If
			}
			// 価格
			driver.findElements(By.className("input-default")).get(INT_2).sendKeys("1380");
			//「出品する」ボタンをクリックする
			driver.findElement(By.className("btn-default")).click();
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
	 * 出品開始
	 * =================================================================================================================
	 *
	 * @author kimC
	 *
	 */
	public void start() {
		try {
			// TODO 出品処理
		} catch (Exception e) {
			System.out.println("【エラー】：○○商品の出品が失敗！");
			restart();
		}
	}

	/**
	 * =================================================================================================================
	 * 再出品
	 * =================================================================================================================
	 *
	 * @author kimC
	 *
	 */
	public void restart() {
		try {
			// TODO 再出品
		} catch (Exception e) {
			System.out.println("【エラー】：○○商品の再出品が失敗！");
		}
	}

}
