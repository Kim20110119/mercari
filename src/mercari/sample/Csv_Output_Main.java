package mercari.sample;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

import mercari.bean.ProductBean;
import mercari.excel.Product;

public class Csv_Output_Main {

	  public static void main(String[] args) {
		  try{
			  String csvFile = "csv/test.csv";
			  Product product = new Product();
			  //CellBeanのリストに代入
			  List<ProductBean> beanList = product.execute("ttxzyo7i0qu47@yahoo.co.jp");

			  //CSVファイルに書き込む
			  BufferedWriter output = new BufferedWriter (
			  new OutputStreamWriter(new FileOutputStream(csvFile), "UTF8"));

			  output.write("ID,名前,アドレス");
			  output.newLine();

			  for(int i = 0; i <= beanList.size() - 1; i++){
				  output.write(
				  (i+1)+","
				  +beanList.get(i).getName()+","
				  +beanList.get(i).getPrice());
				  output.newLine();
			  }
			  output.close();
		  }catch (Exception e){
			  System.out.println(e.getMessage());
		  }

	  }
}
