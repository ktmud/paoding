/*
 * ����������Ȩ���������� ���ڱ���Դ���벻���ƻ��Լ������������Ļ����� �κ��˿���������ʹ��
 */
package com.sohospace.paoding;

import com.sohospace.paoding.cjk.CJKKnife;

/**
 * Knife�涨��ηֽ��ַ����ɴ�������ֽ�ɵĴ����֪{@link Collector}�ӿڡ�
 * <p>
 * 
 * @author zhiliang.wang@yahoo.com.cn
 * 
 * @see Collector
 * @see Paoding
 * @see CJKKnife
 * @see CharKnife
 * @see NumberKnife
 * @see LetterKnife
 * 
 * @since 1.0
 * 
 */
public interface Knife {
	/**
	 * 
	 * @param beaf
	 * @param index
	 * @return
	 */
	public boolean assignable(CharSequence beaf, int index);

	/**
	 * �ֽ��������ֽ�ɵĴ��������Ϣ��֪{@link Collector}�ӿڡ�
	 * <p>
	 * �ֽ��beaf��offsetλ�ÿ�ʼ��ֱ�����ܵĽ�����λ�ã�����ʱ���ؾ����ض������һ����0���֡�<br>
	 * 
	 * @param collector
	 *            ���ֽ⵽����ʱ��collector����֪ͨ���ոô���
	 * @param beaf
	 *            ���ֽ���ַ���������ַ�����������Ҫ�ֽ��ȫ���ַ�����һ����(���������е�ĳһ����)����beaf�����һ���ַ�Ϊ'\0'ʱ����ʾ�˴ηֽ����������һ�Ρ�
	 * @param offset
	 *            �ַ����ֽ⿪ʼλ�ã������˷ֽ�ֻ���beaf.charAt(offset)��ʼ
	 * @return ��0������������������������<br>
	 *         ����ʱ����ʾ�˴ηֽ⵽�ý���λ��(�������ñ߽�)�����˴γɹ��ֽ��˴�offset����λ�õ��ı������ر�أ�����>=beaf.lenght()��ʾ�Ѿ���beaf���еĴ���ֽ����<br>
	 *         ����ʱ���ø����ľ���ֵ����>=offset���������ֵ��ʾ�˴γɹ��ֽ��˴�offset���þ���ֵ���ı�����ʣ�µ��ַ�����knife�Ѿ�������ȷ������(һ���ʱӦ�����´����µ�beaf�������)
	 *         <p>
	 *         ���磬������Ϊ"hello yang!"�����£��ȶ���8���ַ�"hello
	 *         ya"����ʱ�ֽ��Ӧ�÷���-5����ʾ��ȷ������5���λ�ã���"hello"������������µ��ַ�Ȼ���ټ���������
	 *         ��ʱbeaf�����߾Ͷ���ʣ�µ��ַ�"ng!"����ǰ��ʣ�µ�" ya"���ɴ�"
	 *         yang!"���������ܼ����������Ӷ�������"yang"!
	 * 
	 * 
	 */
	public int dissect(Collector collector, CharSequence beaf, int offset);
}
