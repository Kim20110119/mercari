package mercari.pc.main;

import mercari.bean.AccountBean;
import mercari.excel.Product;
import mercari.pc.awaiting.Pc_Wait_Dispatch;

/**
 * =====================================================================================================================
 * 【発送待ち】商品出力の自動化処理
 * =====================================================================================================================
 *
 * @author kimC
 *
 */
public class Pc_Wait_Dispatch_Main {
	public static void main(String[] args) {
		Product product = new Product();
		// アカウント情報を取得する
		AccountBean account = product.getAccount(args[0]);
		// 【発送待ち】商品の抽出処理
		Pc_Wait_Dispatch output = new Pc_Wait_Dispatch(account.getMail(),account.getPassword());
		// 【発送待ち】商品出力
		output.execute();
		System.out.println("【" + account.getMail() + "】アカウントの【発送待ち】商品の抽出処理完了！！！");
	}

}
