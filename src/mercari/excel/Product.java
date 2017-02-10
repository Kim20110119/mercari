package mercari.excel;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import mercari.bean.ProductBean;

public class Product {

	/** ファイル入出力ストリーム */
	FileInputStream filein;
	/** EXCELワークブック */
	XSSFWorkbook workbook;
	/** EXCELシート */
	XSSFSheet sheet;

	/**
	 * コンストラクタ
	 */
	public Product() {
	}

	/**
	 * =================================================================================================================
	 * EXCELから商品情報を抽出して商品Beanに設定する
	 * =================================================================================================================
	 *
	 * @return List<ProductBean> 商品情報リスト
	 *
	 * @author kimC
	 *
	 */
	public List<ProductBean> execute(String userId) {
		List<ProductBean> list = new ArrayList<ProductBean>();
		try {
			filein = new FileInputStream("excel/" + userId + "/商品.xlsx");
			workbook = new XSSFWorkbook(filein);
			// 「出品データ」シート
			sheet = workbook.getSheet("出品データ");
			Iterator<Row> rows = sheet.rowIterator();
			int index = 0;
			while(rows.hasNext()) {
				Row row = rows.next();
				if(index > 0){
					ProductBean bean = new ProductBean();
					// 出品画像フォルダ
					Cell cell_0 = row.getCell(0);
					bean.setImages_path(this.getCellValue(cell_0));
					// 商品名
					Cell cell_1 = row.getCell(1);
					bean.setName(this.getCellValue(cell_1));
					// 商品の説明
					Cell cell_2 = row.getCell(2);
					bean.setDescription(this.getCellValue(cell_2));
					// カテゴリー(大)
					Cell cell_3 = row.getCell(3);
					bean.setCategory_0(this.getCellValue(cell_3));
					// カテゴリー(中)
					Cell cell_4 = row.getCell(4);
					bean.setCategory_1(this.getCellValue(cell_4));
					// カテゴリー(小)
					Cell cell_5 = row.getCell(5);
					bean.setCategory_2(this.getCellValue(cell_5));
					// サイズ
					Cell cell_6 = row.getCell(6);
					bean.setSize(this.getCellValue(cell_6));
					// ブランド
					Cell cell_7 = row.getCell(7);
					bean.setBrand(this.getCellValue(cell_7));
					// 商品の状態
					Cell cell_8 = row.getCell(8);
					bean.setState(this.getCellValue(cell_8));
					// 配送料の負担
					Cell cell_9 = row.getCell(9);
					bean.setDelivery_f(this.getCellValue(cell_9));
					// 配送の方法
					Cell cell_10 = row.getCell(10);
					bean.setDelivery_m(this.getCellValue(cell_10));
					// 発送元の地域
					Cell cell_11 = row.getCell(11);
					bean.setRegion(this.getCellValue(cell_11));
					// 発送までの日数
					Cell cell_12 = row.getCell(12);
					bean.setDay(this.getCellValue(cell_12));
					// 価格
					Cell cell_13 = row.getCell(13);
					bean.setPrice(this.getCellValue(cell_13));
					list.add(bean);
				}
				index++;
			}
			workbook.close();
			filein.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("処理が失敗しました");
		}
		return list;
	}

	/**
	 * =================================================================================================================
	 * EXCELから商品情報を抽出して商品Beanに設定する
	 * =================================================================================================================
	 *
	 * @return List<ProductBean> 商品情報リスト
	 *
	 * @author kimC
	 *
	 */
	public String getCellValue(Cell cell) {
		if(cell != null){
			if(cell.getCellTypeEnum() == CellType.NUMERIC){
				int int_value = (int)cell.getNumericCellValue();
				return String.valueOf(int_value);
			}else{
				return cell.getStringCellValue();
			}
		}else{
			return StringUtils.EMPTY;
		}

	}

}
