package mercari.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import mercari.bean.OutputBean;

public class Output {

	/** ファイル入出力ストリーム */
	FileInputStream filein;
	/** EXCELワークブック */
	XSSFWorkbook workbook;
	/** EXCELシート */
	XSSFSheet sheet;
	/** メルカリアカウント */
	String userId;
	/** 【出品した商品 - 取引中】商品用Bean */
	List<OutputBean> list;
	/** 【出品した商品 - 取引中】商品ステータス */
	String status;

	/**
	 * コンストラクタ
	 */
	public Output() {
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
		File file = new File("excel/" + pUserId);
		if(!file.exists()){
			file.mkdir();
		}
		// 出力EXCELを作成する
		this.create();
	}

	/**
	 * =================================================================================================================
	 * 商品情報を抽出してEXCELファイルで出力する
	 * =================================================================================================================
	 *
	 * @author kimC
	 *
	 */
	public void create() throws IOException {
		// Excel2007以降の「.xlsx」形式のファイルの素を作成
		workbook = new XSSFWorkbook();
		// シートを「サンプル」という名前で作成
		Sheet sheet = workbook.createSheet("商品");
		// 商品項目セルを作成する
		// 行
		Row row = sheet.createRow(0);
		// 列
		Cell a1 = row.createCell(0);  // 「A1」
		a1.setCellValue("商品ID");
		Cell b1 = row.createCell(1);  // 「B1」
		b1.setCellValue("商品名");
		Cell c1 = row.createCell(2);  // 「C1」
		c1.setCellValue("販売価格");
		Cell d1 = row.createCell(3);  // 「D1」
		d1.setCellValue("販売手数料");
		Cell e1 = row.createCell(4);  // 「E1」
		e1.setCellValue("販売利益");
		Cell f1 = row.createCell(5);  // 「E1」
		f1.setCellValue("お届け先");
	    // セルのスタイル
	    CellStyle style =  workbook.createCellStyle();
	    // フォント
	    Font font = workbook.createFont();
	    font.setColor(IndexedColors.ROSE.getIndex());
	    // セルにセット！！
	    style.setFont(font);
	    a1.setCellStyle(style);
	    b1.setCellStyle(style);
	    c1.setCellStyle(style);
	    d1.setCellStyle(style);
	    e1.setCellStyle(style);
	    f1.setCellStyle(style);
	    int index = 1;
	    if(list != null){
	    	for(OutputBean bean : list){
		    	// 商品項目セルを作成する
				Row row_index = sheet.createRow(index);
				// 「A_Index」
				Cell a_index = row_index.createCell(0);
				a_index.setCellValue(bean.getId());         // 商品ID
				// 「B_Index」
				Cell b_index = row_index.createCell(1);
				b_index.setCellValue(bean.getName());       // 商品名
				// 「C_Index」
				Cell c_index = row_index.createCell(2);
				c_index.setCellValue(bean.getPrice());      // 販売価格
				// 「D_Index」
				Cell d_index = row_index.createCell(3);
				d_index.setCellValue(bean.getCommission()); // 販売手数料
				// 「E_Index」
				Cell e_index = row_index.createCell(4);
				e_index.setCellValue(bean.getProfit());     // 販売利益
				// 「F_Index」
				Cell f_index = row_index.createCell(5);
				f_index.setCellValue(bean.getDelivery());   // お届け先
		    }
	    }
	    // ファイル入出力ストリーム
	    FileOutputStream out = null;
	    try {
	    	// ステータスによりファイル名を確認する
	    	String fileName = "Sample";
	    	if(StringUtils.isNotEmpty(this.status)){
	    		fileName = this.status;
	    	}
			// 出力先のファイルを指定
			out = new FileOutputStream("excel/" + userId + "/" + fileName + ".xlsx");
			// 上記で作成したブックを出力先に書き込み
			workbook.write(out);
	    } catch (FileNotFoundException e) {
	    	System.out.println(e.getStackTrace());
	    } finally {
			// 最後はちゃんと閉じておきます
			out.close();
			workbook.close();
	    }
	}

}
