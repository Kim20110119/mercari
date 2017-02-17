package mercari.sample;

import mercari.excel.CreateFolder;

public class Sample_Main {
	public static void main(String[] args) {
		try {
			CreateFolder createFolder = new CreateFolder();
			createFolder.execute();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("処理が失敗しました");
		}
	}
}
