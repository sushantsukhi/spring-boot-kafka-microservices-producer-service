package com.happycoding.service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class WordReader {

	public List<String> processWordDoc(String filename) {
		List<String> msgList = new ArrayList<String>();
		try {

			FileInputStream fis = new FileInputStream(filename);
			XWPFDocument xdoc = new XWPFDocument(OPCPackage.open(fis));

			// XWPFWordExtractor extractor = new XWPFWordExtractor(xdoc);
			// text = extractor.getText();
			// System.out.println(text);

			List<XWPFParagraph> paragraphList = xdoc.getParagraphs();
			if (!paragraphList.isEmpty()) {
				int ii = 1;
				for (XWPFParagraph paragraph : paragraphList) {
					if (!StringUtils.isEmpty(paragraph.getText())) {
						msgList.add(paragraph.getText());
						System.out.println("Line:" + paragraph.getText());
//						XWPFDocument document = new XWPFDocument();
//						try (OutputStream fileOut = new FileOutputStream("C:\\kafka-storage\\testing" + ii + ".docx")) {
//							document.createParagraph().createRun().setText(paragraph.getText());
//							document.write(fileOut);
//							System.out.println("File created");
//							ii++;
//						} finally {
//							document.close();
//						}
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return msgList;
	}

	public static void main(String[] args) {
		//new WordReader().processWordDoc(1);
	}
}
