package mercari.pc.main;

import mercari.bean.AccountBean;
import mercari.excel.Product;
import mercari.pc.awaiting.Pc_Wait_Payment;

/**
 * =====================================================================================================================
 * 【支払い待ち】商品詳細画面表示の自動化処理
 * =====================================================================================================================
 *
 * @author kimC
 *
 */
public class Pc_Wait_Payment_Main {
	public static void main(String[] args) {
		Product product = new Product();
		// アカウント情報を取得する
		AccountBean account = product.getAccount(args[0]);
		// 【支払い待ち】商品詳細表示処理
		Pc_Wait_Payment wait_payment = new Pc_Wait_Payment(account);
		wait_payment.execute();
		System.out.println("【" + account.getMail() + "】アカウントの【支払い待ち】商品詳細画面表示処理完了！！！");
	}

}
