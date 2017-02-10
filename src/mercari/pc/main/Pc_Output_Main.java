package mercari.pc.main;

import mercari.pc.output.Pc_Output;

/**
 * =====================================================================================================================
 * 評価待ち商品詳細画面表示の自動化処理
 * =====================================================================================================================
 *
 * @author kimC
 *
 */
public class Pc_Output_Main {
	public static void main(String[] args) {
		Pc_Output output = new Pc_Output(args[0],args[1]);
		output.execute();
		output.driverQuit();
		System.out.println("【" + args[0] + "】アカウントの評価待ち商品の抽出処理完了！！！");
	}

}
