/*
 * ����������Ȩ���������� ���ڱ���Դ���벻���ƻ��Լ������������Ļ����� �κ��˿���������ʹ��
 */
package com.sohospace.paoding;

/**
 * Collector����Knife�и��ı��õ��Ĵ��
 * <p>
 * 
 * @author zhiliang.wang@yahoo.com.cn
 * 
 * @see Knife
 * 
 * @since 1.0
 * 
 */
public interface Collector {

	/**
	 * ��Knife���ı����л�ȡһ������ʱ�������������á� <br>
	 * ���õ�˳����������ı����е�˳���Ƿ�һ���Ӳ�ͬʵ�ֿ����в�ͬ�Ĳ��ԡ�
	 * <p>
	 * 
	 * �統Knife�յ����й�������������ı����еġ���ᡱʱ������Ĳ����ֱ��ǣ�(����ᡱ, 4, 6)
	 * 
	 * @param word
	 *            ���յ��Ĵ���
	 * @param offset
	 *            �ô������ı����е�ƫ��λ��
	 * @param end
	 *            �ô������ı����еĽ���λ��(���ﲻ�����ı���endλ�õ��ַ�)��end-offset��Ϊword�ĳ���
	 * 
	 *         
	 */
	public void collect(String word, int offset, int end);
}
