/*
 * ����������Ȩ���������� ���ڱ���Դ���벻���ƻ��Լ������������Ļ����� �κ��˿���������ʹ��
 */
package com.sohospace.dictionary;

import java.util.Arrays;


/**
 * 
 * @author zhiliang.wang@yahoo.com.cn
 * 
 * @since 1.0
 * 
 */
public class Main0 {

	static String[] words = { "��ʼ", "����", "���ݲֿ�", "����", "������", "��Ʒ", "Ʒζ",
			"����", "����" };

	static String segment = "��������Ʒζ��Ҷʱ�����������ˡ�";


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Arrays.sort(words);
		System.out.println(Arrays.toString(words));
		Dictionary dic = new BinaryDictionary(words);
		//
		System.out.println(segment);
		//
		String input = segment;

		//
		int index = 1, count;
		int segmentLength = segment.length();
		for (int begin = 0; begin < segmentLength; begin++) {
			for (index = begin + 1, count = 1; index <= segmentLength; index++, count++) {
				Hit word = dic.search(input, begin, count);
				if (word.isUndefined()) {
					break;
				} else if (word.isUnclosed()) {
					continue;
				} else {
					System.out.println("--" + begin + "," + count + ":" + word.getWord());
				} 
			}

		}
	}

}
