/*
 * ����������Ȩ���������� ���ڱ���Դ���벻���ƻ��Լ������������Ļ����� �κ��˿���������ʹ��
 */
package com.sohospace.lucene.analysis.xanalyzer;

import java.util.Iterator;

import org.apache.lucene.analysis.Token;

import com.sohospace.paoding.Collector;
/**
 * 
 * @author zhiliang.wang@yahoo.com.cn
 *
 * @since 1.1
 */
public interface TokenCollector extends Collector {

	/**
	 * 
	 * @return
	 */
	public Iterator<Token> iterator();
}
