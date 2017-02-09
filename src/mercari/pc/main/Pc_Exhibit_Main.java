package mercari.pc.main;

import mercari.bean.ProductBean;
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
		ProductBean bean = new ProductBean();
		exhibit.execute(bean);
		System.out.println("成功！！！");
	}

}
