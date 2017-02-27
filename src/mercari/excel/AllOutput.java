package mercari.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import mercari.bean.OutputBean;

public class AllOutput {

	/** ファイル入出力ストリーム */
	FileInputStream filein;
	/** EXCELワークブック */
	XSSFWorkbook workbook;
	/** EXCELシート */
	XSSFSheet sheet;
	/** EXCELファイル名 */
	String fileName;
	/** メルカリアカウント */
	String userId;
	/** 【出品した商品 - 取引中】商品用Bean */
	List<OutputBean> list;
	/** 【出品した商品 - 取引中】商品ステータス */
	String status;

	/**
	 * コンストラクタ
	 */
	public AllOutput() {
	}

	/**
	 * =================================================================================================================
	 * 商品情報を抽出してEXCELファイルで出力する
	 * =================================================================================================================
	 *
	 * @author kimC
	 *
	 */
	public void execute(String pUserId,List<OutputBean> pList, String pStatus) throws IOException {
		// メルカリアカウント
		this.userId = pUserId;
		// 商品リスト
		this.list = pList;
		// ステータス
		this.status = pStatus;
		// EXCEL出力フォルダを作成する
		File file = new File("output/" + this.status);
		if(!file.exists()){
			file.mkdir();
		}
		// EXCEL出力ファイル名を設定する
		this.setFileName();
		// EXCELファイルを取得する
		File excel = new File("output/" + this.status + "/" + this.fileName);
		if(!excel.exists()){
			// EXCELファイルを作成する
			this.createExcel();
			// ファイル入力ストリーム
			filein = new FileInputStream("output/" + this.status + "/" + this.fileName);
			// 「データ」シートを作成し商品情報を出力する
			this.createSheet();
		}else{
			// ファイル入力ストリーム
			filein = new FileInputStream("output/" + this.status + "/" + this.fileName);
			// 商品情報を出力する
			this.setData();
		}
	}

	/**
	 * =================================================================================================================
	 * 出力ファイル名を設定する
	 * =================================================================================================================
	 *
	 * @author kimC
	 *
	 */
	public void setFileName(){
		String fileName = StringUtils.EMPTY;
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(this.status + "yyyyMMdd'.xlsx'");
			fileName = simpleDateFormat.format(new Date(System.currentTimeMillis()));
		} catch (Exception e) {
			System.out.println(this.status + "のファイル名作成失敗！！！" );
		}
		this.fileName = fileName;
	}

	/**
	 * =================================================================================================================
	 * EXCELファイルを作成する
	 * =================================================================================================================
	 *
	 * @author kimC
	 *
	 */
	public void createExcel() throws IOException {
		// Excel2007以降の「.xlsx」形式のファイルの素を作成
		workbook = new XSSFWorkbook();
		// ファイル入出力ストリーム
		FileOutputStream out = null;
		try {
			// 出力先のファイルを指定
			out = new FileOutputStream("output/" + this.status + "/" + this.fileName);
			// 上記で作成したブックを出力先に書き込み
			workbook.write(out);
		} catch (Exception e) {
			System.out.println(this.status + "の「" + this.fileName +"」ファイル作成失敗！！！" );
		} finally {
			// ファイル出力ストリームを閉じる
			out.close();
			workbook.close();
		}
	}

	/**
	 * =================================================================================================================
	 * 商品情報を抽出して該当するEXCELファイルのシートで出力する
	 * =================================================================================================================
	 *
	 * @author kimC
	 *
	 */
	public void createSheet() throws IOException {
		workbook = new XSSFWorkbook(filein);
		// 「出品データ」シートを作成する
		sheet = workbook.createSheet("データ");
		// 商品項目セルを作成する
		// 行
		Row row = sheet.createRow(0);
		// 列
		Cell a1 = row.createCell(0);  // 「A1」
		a1.setCellValue("アカウント");
		Cell b1 = row.createCell(1);  // 「B1」
		b1.setCellValue("商品ID");
		Cell c1 = row.createCell(2);  // 「C1」
		c1.setCellValue("商品名");
		Cell d1 = row.createCell(3);  // 「D1」
		d1.setCellValue("販売価格");
		Cell e1 = row.createCell(4);  // 「E1」
		e1.setCellValue("販売手数料");
		Cell f1 = row.createCell(5);  // 「F1」
		f1.setCellValue("販売利益");
		Cell g1 = row.createCell(6);  // 「G1」
		g1.setCellValue("お届け先");
		// セルのスタイル
		CellStyle style =  workbook.createCellStyle();
		// フォント
		Font font = workbook.createFont();
		font.setColor(IndexedColors.ROSE.getIndex());
		// セルにスタイルセット
		style.setFont(font);
		a1.setCellStyle(style);
		b1.setCellStyle(style);
		c1.setCellStyle(style);
		d1.setCellStyle(style);
		e1.setCellStyle(style);
		f1.setCellStyle(style);
		g1.setCellStyle(style);
		int index = 1;
		if(list != null){
			for(OutputBean bean : list){
				// 商品項目セルを作成する
				Row row_index = sheet.createRow(index);
				// 「A_Index」
				Cell a_index = row_index.createCell(0);
				a_index.setCellValue(bean.getAccount());    // アカウント
				// 「B_Index」
				Cell b_index = row_index.createCell(1);
				b_index.setCellValue(bean.getId());         // 商品ID
				// 「C_Index」
				Cell c_index = row_index.createCell(2);
				c_index.setCellValue(bean.getName());       // 商品名
				// 「D_Index」
				Cell d_index = row_index.createCell(3);
				d_index.setCellValue(bean.getPrice());      // 販売価格
				// 「E_Index」
				Cell e_index = row_index.createCell(4);
				e_index.setCellValue(bean.getCommission()); // 販売手数料
				// 「F_Index」
				Cell f_index = row_index.createCell(5);
				f_index.setCellValue(bean.getProfit());     // 販売利益
				// 「G_Index」
				Cell g_index = row_index.createCell(6);
				g_index.setCellValue(bean.getDelivery());   // お届け先
				index++;
			}
		}
		// ファイル入出力ストリーム
		FileOutputStream out = null;
		try {
			// 出力先のファイルを指定
			out = new FileOutputStream("output/" + this.status + "/" + this.fileName);
			// 上記で作成したブックを出力先に書き込み
			workbook.write(out);
		} catch (FileNotFoundException e) {
			System.out.println(this.userId + "の" + this.status + "新規出力失敗！！！" );
		} finally {
			// 最後はちゃんと閉じておきます
			out.close();
			workbook.close();
		}
	}

	/**
	 * =================================================================================================================
	 * EXCELからアカウント情報を抽出してアカウントBeanに設定する
	 * =================================================================================================================
	 *
	 * @return List<AccountBean> アカウント情報リスト
	 *
	 * @author kimC
	 *
	 */
	public void setData() {
		try {
			workbook = new XSSFWorkbook(filein);
			// 「データ」シート
			sheet = workbook.getSheet("データ");
			Iterator<Row> rows = sheet.rowIterator();
			int index = 0;
			while(rows.hasNext()) {
				index++;
				Row row = rows.next();
				Cell cell_0 = row.getCell(0);
				if(StringUtils.isEmpty(this.getCellValue(cell_0))){
					break;
				}
			}
			if(list != null){
				for(OutputBean bean : list){
					// 商品項目セルを作成する
					Row row_index = sheet.createRow(index);
					// 「A_Index」
					Cell a_index = row_index.createCell(0);
					a_index.setCellValue(bean.getAccount());    // アカウント
					// 「B_Index」
					Cell b_index = row_index.createCell(1);
					b_index.setCellValue(bean.getId());         // 商品ID
					// 「C_Index」
					Cell c_index = row_index.createCell(2);
					c_index.setCellValue(bean.getName());       // 商品名
					// 「D_Index」
					Cell d_index = row_index.createCell(3);
					d_index.setCellValue(bean.getPrice());      // 販売価格
					// 「E_Index」
					Cell e_index = row_index.createCell(4);
					e_index.setCellValue(bean.getCommission()); // 販売手数料
					// 「F_Index」
					Cell f_index = row_index.createCell(5);
					f_index.setCellValue(bean.getProfit());     // 販売利益
					// 「G_Index」
					Cell g_index = row_index.createCell(6);
					g_index.setCellValue(bean.getDelivery());   // お届け先
					index++;
				}
			}
			// ファイル入出力ストリーム
			FileOutputStream out = null;
			try {
				// 出力先のファイルを指定
				out = new FileOutputStream("output/" + this.status + "/" + this.fileName);
				// 上記で作成したブックを出力先に書き込み
				workbook.write(out);
			} catch (FileNotFoundException e) {
				System.out.println(this.userId + "の" + this.status + "追加出力失敗！！！" );
			} finally {
				// 最後はちゃんと閉じておきます
				out.close();
				workbook.close();
			}
		} catch (Exception e) {
			System.out.println(this.userId + "の" + this.status + "追加出力失敗！！！" );
		}
	}

	/**
	 * =================================================================================================================
	 * EXCELのシートから値を取得する
	 * =================================================================================================================
	 *
	 * @return String value 値
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
