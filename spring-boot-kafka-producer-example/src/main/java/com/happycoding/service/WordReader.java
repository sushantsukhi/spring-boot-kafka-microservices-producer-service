package com.happycoding.service;

import java.io.FileInputStream;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.stereotype.Service;

@Service
public class WordReader {

	public String processWordDoc(int i) {
		String text = null;
		try {

			FileInputStream fis = new FileInputStream("C:\\kafka-storage\\test" + i + ".docx");
			XWPFDocument xdoc = new XWPFDocument(OPCPackage.open(fis));
			XWPFWordExtractor extractor = new XWPFWordExtractor(xdoc);
			text = extractor.getText();
			System.out.println(text);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return text;
	}

	public static void main(String[] args) {
		//new WordReader().processWordDoc(1);
	}
}
