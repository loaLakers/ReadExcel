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
	 * ��ȡExcel
	 * 
	 * @param filePath
	 */
	public static void readExcel(String filePath) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			InputStream is = new FileInputStream(filePath);
			Workbook rwb = Workbook.getWorkbook(is);

			// Sheet st = rwb.getSheet("0");//���������ַ�����ȡsheet��,1Ϊ���֣���Ϊ�±꣬��0��ʼ
			Sheet st = rwb.getSheet("Sheet1");
			int rs = st.getColumns();
			int rows = st.getRows();
			System.out.println("����===>" + rs + "������" + rows);

			for (int k = 0; k < rows; k++) {// ��
				for (int i = 0; i < rs; i++) {// ��

					Cell c00 = st.getCell(i, k);
					// ͨ�õĻ�ȡcellֵ�ķ�ʽ,�����ַ���
					String strc00 = c00.getContents();
					// ���cell��������ֵ�ķ�ʽ
					if (c00.getType() == CellType.LABEL) {
						LabelCell labelc00 = (LabelCell) c00;
						strc00 = labelc00.getString();
					}
					// excel ����Ϊʱ�����ʹ���;
					if (c00.getType() == CellType.DATE) {
						DateCell dc = (DateCell) c00;
						strc00 = sdf.format(dc.getDate());

					}
					// excel ����Ϊ��ֵ���ʹ���;
					/*
					 * if(c00.getType()==CellType.NUMBER||
					 * c00.getType()==CellType.NUMBER_FORMULA){ NumberCell
					 * nc=(NumberCell)c00; strc00=""+nc.getValue(); }
					 */

					// ���
					System.out.println(">" + strc00);

					list.add(strc00);

				}
				System.out.println("======" + list.get(k) + "=========");
			}

			// �ر�
			rwb.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ����
	public static void main(String[] args) {
		try {
			// ��Excel
			Read.readExcel("/Users/sunxiaodi/Desktop/excel.xls");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
