/*
 * ����������Ȩ���������� ���ڱ���Դ���벻���ƻ��Լ������������Ļ����� �κ��˿���������ʹ��
 */
package com.sohospace.paoding;

/**
 * Paoding��һ�����š����䡱(�ڲظ��֡�����)������������ţ�����ˣ������Ҷ�����
 * <p>
 * ����Ϊ��ӵ�и��ֲ�ͬ�ġ������������ܹ�ʶ��ʲô����(�ַ�)��Ӧ����ʲô�������ָ����������������ذ���ͷţ�и��Ϊ���ʵġ���Ƭ(����)���� <br>
 * ����ġ�������Knife���ݣ����֡������ɡ����䡱KnifeBox����(Paoding���������һ��KnifeBox)������KnifeBox����ʲôʱ���ʲô��������
 * 
 * @author zhiliang.wang@yahoo.com.cn
 * 
 * @see Knife
 * @see KnifeBox
 * @see KnifeBoxBean
 * 
 * @since 1.0
 */
public final class Paoding extends KnifeBox implements Knife {

	// -------------------------------------------------
	
	public int dissect(Collector collector, CharSequence beaf, int offset) {
		while (offset >=0 && offset < beaf.length()) {
			offset = super.dissect(collector, beaf, offset);
		}
		return offset;
	}

}
