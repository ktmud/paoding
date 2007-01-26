/*
 * ����������Ȩ���������� ���ڱ���Դ���벻���ƻ��Լ������������Ļ����� �κ��˿���������ʹ��
 */
package com.sohospace.paoding;

/**
 * 
 * @author zhiliang.wang@yahoo.com.cn
 *
 */
public class CollectorStdoutImpl implements Collector {

	private static ThreadLocal<Integer> tl = new ThreadLocal<Integer>(){
		@Override
		protected Integer initialValue() {
			return 0;
		}
	};
	
	public void collect(String word, int begin, int end) {
		int c = tl.get() + 1;
		tl.set(c);
		System.out.println(c + ":\t[" + begin + ", " + end + ")=" + word);
	}

}
