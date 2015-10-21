package com.wr.read;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import jxl.Cell;
import jxl.CellType;
import jxl.DateCell;
import jxl.LabelCell;
import jxl.Sheet;
import jxl.Workbook;

public class Read {

	public static List<String> list = new ArrayList<String>();

	/**
	 * 读取Excel
	 * 
	 * @param filePath
	 */
	public static void readExcel(String filePath) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			InputStream is = new FileInputStream(filePath);
			Workbook rwb = Workbook.getWorkbook(is);

			// Sheet st = rwb.getSheet("0");//这里有两种方法获取sheet表,1为名字，而为下标，从0开始
			Sheet st = rwb.getSheet("Sheet1");
			int rs = st.getColumns();
			int rows = st.getRows();
			System.out.println("列数===>" + rs + "行数：" + rows);

			for (int k = 0; k < rows; k++) {// 行
				for (int i = 0; i < rs; i++) {// 列

					Cell c00 = st.getCell(i, k);
					// 通用的获取cell值的方式,返回字符串
					String strc00 = c00.getContents();
					// 获得cell具体类型值的方式
					if (c00.getType() == CellType.LABEL) {
						LabelCell labelc00 = (LabelCell) c00;
						strc00 = labelc00.getString();
					}
					// excel 类型为时间类型处理;
					if (c00.getType() == CellType.DATE) {
						DateCell dc = (DateCell) c00;
						strc00 = sdf.format(dc.getDate());

					}
					// excel 类型为数值类型处理;
					/*
					 * if(c00.getType()==CellType.NUMBER||
					 * c00.getType()==CellType.NUMBER_FORMULA){ NumberCell
					 * nc=(NumberCell)c00; strc00=""+nc.getValue(); }
					 */

					// 输出
					System.out.println(">" + strc00);

					list.add(strc00);

				}
				System.out.println("======" + list.get(k) + "=========");
			}

			// 关闭
			rwb.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 测试
	public static void main(String[] args) {
		try {
			// 读Excel
			Read.readExcel("/Users/sunxiaodi/Desktop/excel.xls");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
