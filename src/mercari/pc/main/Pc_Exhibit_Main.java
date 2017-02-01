package mercari.pc.main;

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
		Pc_Exhibit exhibit = new Pc_Exhibit();
		exhibit.execute();
		System.out.println("成功！！！");
	}

}
