/*
 * ����������Ȩ���������� ���ڱ���Դ���벻���ƻ��Լ������������Ļ����� �κ��˿���������ʹ��
 */
package com.sohospace.paoding.cjk;

import java.util.LinkedList;
/**
 * 
 * @author zhiliang.wang@yahoo.com.cn
 *
 */
public interface WordsLoader {

	public LinkedList<String> loadCJKVocabulary();

	public LinkedList<String> loadCJKConfucianFamilyNames();

	public LinkedList<String> loadCJKXwords();

	public LinkedList<String> loadCJKXchars();

	public LinkedList<String> loadCJKUnit();
}
