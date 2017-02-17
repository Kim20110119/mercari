package mercari.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import mercari.bean.AccountBean;

/**
 * =================================================================================================================
 * アカウント情報リストからアカウントごとに必要なフォルダ・ファイル・バッチファイルを作成する
 * =================================================================================================================
 *
 * @author kimC
 *
 */
public class CreateFolder {

	/** ファイル入出力ストリーム */
	FileInputStream filein;
	/** EXCELワークブック */
	XSSFWorkbook workbook;
	/** EXCELシート */
	XSSFSheet sheet;
	/** アカウントリスト */
	List<AccountBean> list;


	/**
	 * コンストラクタ
	 */
	public CreateFolder() {
	}

	/**
	 * =================================================================================================================
	 * 商品情報を抽出してEXCELファイルで出力する
	 * =================================================================================================================
	 *
	 * @author kimC
	 *
	 */
	public void execute() throws IOException {
		try {
			filein = new FileInputStream("アカウントリスト.xlsx");
			workbook = new XSSFWorkbook(filein);
			// 「アカウントリスト」シート
			sheet = workbook.getSheet("アカウントリスト");
			Iterator<Row> rows = sheet.rowIterator();
			int index = 0;
			while(rows.hasNext()) {
				Row row = rows.next();
				if(index > 0){
					// メールアドレス
					Cell cell_0 = row.getCell(0);
					// アカウントごとにフォルダを作成する
					this.createFolde(this.getCellValue(cell_0));
					// サンプルファイルをコピーする
					this.copyFile(this.getCellValue(cell_0));
				}
				index++;
			}
			workbook.close();
			filein.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("処理が失敗しました");
		}
	}

	/**
	 * =================================================================================================================
	 * EXCELファイルを作成する
	 * =================================================================================================================
	 *
	 * @author kimC
	 *
	 */
	public void createFolde(String account){
		try{
			// EXCELフォルダを作成する
			File excel_file = new File("excel/" + account);
			if(!excel_file.exists()){
				excel_file.mkdir();
			}
			// IMAGES出力フォルダを作成する
			File image_file = new File("images/" + account);
			if(!image_file.exists()){
				image_file.mkdir();
			}
		}catch (Exception e) {
			System.out.println("【エラー】：フォルダ作成失敗！");
		}

	}

	/**
	 * =================================================================================================================
	 * サンプルから商品EXCELとアカウント情報をコピーする
	 * =================================================================================================================
	 *
	 * @author kimC
	 *
	 */
	public void copyFile(String account) {
		try{
			File account_file = new File("excel/" + account + "/アカウント.xlsx");
			if(!account_file.exists()){
				// アカウントEXCELコピーする
				Path account_input = FileSystems.getDefault().getPath("excel/sample/アカウント.xlsx");
				Path account_output = FileSystems.getDefault().getPath("excel/" + account + "/アカウント.xlsx");
				Files.copy(account_input, account_output);
			}
			File product_file = new File("excel/" + account + "/商品.xlsx");
			if(!product_file.exists()){
				// アカウントEXCELコピーする
				Path product_input = FileSystems.getDefault().getPath("excel/sample/商品.xlsx");
				Path product_output = FileSystems.getDefault().getPath("excel/" + account + "/商品.xlsx");
				Files.copy(product_input, product_output);
			}

		}catch (Exception e){
			System.out.println("【エラー】：ファイルコピー失敗！");
		}

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
