/*
 * ����������Ȩ���������� ���ڱ���Դ���벻���ƻ��Լ������������Ļ����� �κ��˿���������ʹ��
 */
package com.sohospace.dictionary;

/**
 * Dictionary��һ��ֻ���ֵ䣬���ڲ����Ƿ����ĳ������Լ������Ϣ��
 * <p>
 * 
 * @author zhiliang.wang@yahoo.com.cn
 * 
 * @see BinaryDictionary
 * @see HashBinaryDictionary
 * 
 * @since 1.0
 * 
 */
public interface Dictionary {

	/**
	 * �����ֵ��д�����>=0
	 * 
	 * @return
	 */
	public int size();

	/**
	 * ���ظ���λ�õĴ���
	 * 
	 * @param index
	 *            0,1,2,...,size-1
	 * @return
	 */
	public String get(int index);

	/**
	 * �����ʵ��Ƿ��ռ�input[offset]��input[offset+count-1]֮���ַ���(�����߽�)�Ĵʡ�<br>
	 * ��������Էǿ�Hit���������
	 * <p>
	 * @param input Ҫ�������ַ���������������һ����
	 * @param offset Ҫ�������ַ�����ʼλ�����input��ƫ��
	 * @param count Ҫ�������ַ����ַ�����
	 * @return ���ص�Hit����ǿգ�����ͨ��word�����ṩ�ķ����ж��������
	 * 
	 * @see Hit
	 */
	public Hit search(CharSequence input, int offset, int count);
}
