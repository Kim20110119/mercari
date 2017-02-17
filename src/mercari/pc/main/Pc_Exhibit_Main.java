package mercari.pc.main;

import mercari.bean.AccountBean;
import mercari.bean.ProductBean;
import mercari.excel.Product;
import mercari.pc.exhibit.Pc_Exhibit;
import mercari.wifi.Restart;

/**
 * =====================================================================================================================
 * 出品の自動化処理
 * =====================================================================================================================
 *
 * @author kimC
 *
 */
public class Pc_Exhibit_Main {
	public static void main(String[] args) {
		Product product = new Product();
		// アカウント情報を取得する
		AccountBean account = product.getAccount(args[0]);
		// ログイン処理
		Pc_Exhibit exhibit = new Pc_Exhibit(account);
		// Wifi最起動
		if(account.getWifi()){
			Restart restart = new Restart();
			restart.execute(account.getWifiPassword());
		}
		// 商品出品処理
		for(ProductBean bean : product.execute(args[0])){
			exhibit.execute(bean);
		}
		exhibit.driverQuit();
		System.out.println("【" + args[0] + "】アカウントの出品完了！！！");
	}

}
