package mercari.pc.main;

import mercari.pc.delete.Pc_Delete;

/**
 * =====================================================================================================================
 * コメント・お気に入りない商品の削除処理
 * =====================================================================================================================
 *
 * @author kimC
 *
 */
public class Pc_Delete_Main {
	public static void main(String[] args) {
		Pc_Delete delete = new Pc_Delete(args[0],args[1]);
		delete.execute();
		delete.driverQuit();
		System.out.println("【" + args[0] + "】アカウントのコメント・お気に入りない商品の削除処理完了！！！");
	}

}
