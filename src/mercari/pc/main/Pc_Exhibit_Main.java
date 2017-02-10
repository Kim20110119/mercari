package mercari.pc.main;

import mercari.bean.ProductBean;
import mercari.excel.Product;
import mercari.pc.exhibit.Pc_Exhibit;

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
		Pc_Exhibit exhibit = new Pc_Exhibit(args[0],args[1]);
		Product product = new Product();
		for(ProductBean bean : product.execute(args[0])){
			exhibit.execute(bean);
		}
		exhibit.driverQuit();
		System.out.println("【" + args[0] + "】アカウントの出品完了！！！");
	}

}
