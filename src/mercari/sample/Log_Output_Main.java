package mercari.sample;

import java.io.File;

public class Log_Output_Main {
	public static void main(String[] args) {
		//ファイル名の一覧を取得する
        File file = new File("images/sample/1");
        File files[] = file.listFiles();

        //取得した一覧を表示する
        for (int i=0; i<files.length; i++) {
            System.out.println("ファイル" + (i+1) + "→" + files[i].getPath());
        }

	}
}
