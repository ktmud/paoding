/*
 * ����������Ȩ���������� ���ڱ���Դ���벻���ƻ��Լ������������Ļ����� �κ��˿���������ʹ��
 */
package com.sohospace.dictionary.support.filewords;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Map;

/**
 * 
 * @author zhiliang.wang@yahoo.com.cn
 * 
 * @since 1.0
 * 
 */
public class FileWordsReader {
	
	public static Map<String, LinkedList<String>> readWords(String fileOrDirectory) throws IOException {
		SimpleReadListener l = new SimpleReadListener();
		readWords(fileOrDirectory, l);
		return l.getResult();
	}

	public static void readWords(String fileOrDirectory, ReadListener l) throws IOException {
		File file = new File(fileOrDirectory);
		File[] files = new File[]{file};
		if (file.isDirectory()) {
			files = file.listFiles();
		}
		for (int i = 0; i < files.length; i++) {
			if (!l.onFileBegin(files[i].getName())) {
				continue;
			}
			BufferedReader in = new BufferedReader(new InputStreamReader(
					new FileInputStream(files[i])));
			String word;
			while ((word = in.readLine()) != null) {
				l.onWord(word);
			}
			l.onFileEnd(files[i].getName());
			in.close();
		}
	}
	
	public static void main(String[] args) throws IOException {
		Map<String, LinkedList<String>> map = readWords("dic/chinese/x/character.dic");
		String[] baseWords = map.get("character").toArray(new String[0]);
		System.out.println(Arrays.toString(baseWords));
		

		Map<String, LinkedList<String>> map2 = readWords("dic/chinese");
		String[] baseWords2 = map2.get("title").toArray(new String[0]);
		System.out.println(Arrays.toString(baseWords2));
		String[] baseWords3 = map2.get("xword").toArray(new String[0]);
		System.out.println(Arrays.toString(baseWords3));
	}
	
	
}
