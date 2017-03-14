package mercari.sample;

import mercari.bean.AccountBean;
import mercari.excel.Product;
import mercari.pc.delete.Pc_Sample;

public class Sample_Main {
	public static void main(String[] args) {
		try {
			Product product = new Product();
			// アカウント情報を取得する
			AccountBean account = product.getAccount(args[0]);
			Pc_Sample sample = new Pc_Sample(account);
			sample.login();;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("処理が失敗しました");
		}
	}
}
