package mercari.pc.main;

import mercari.bean.AccountBean;
import mercari.excel.Product;
import mercari.pc.awaiting.Pc_Wait_Evaluation;

/**
 * =====================================================================================================================
 * 【評価待ち】商品出力の自動化処理
 * =====================================================================================================================
 *
 * @author kimC
 *
 */
public class Pc_Wait_Evaluation_Main {
	public static void main(String[] args) {
		Product product = new Product();
		// アカウント情報を取得する
		AccountBean account = product.getAccount(args[0]);
		// 【評価待ち】商品の抽出処理
		Pc_Wait_Evaluation output = new Pc_Wait_Evaluation(account.getMail(),account.getPassword());
		// 【評価待ち】商品出力
		output.execute();
		// ブラウザドライバーを終了する
		output.driverQuit();
		System.out.println("【" + args[0] + "】アカウントの【評価待ち】商品の抽出処理完了！！！");
	}
}
