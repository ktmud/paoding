package net.paoding.analysis.knife;

import net.paoding.analysis.dictionary.Dictionary;

/**
 * �����ֵ仺����ݵ�,Ϊ{@link CJKKnife}���á�<br>
 * �ӱ�������Ի�ȡ������Ҫ������ֵ䡣�����ʻ�����ϱ�������λ�����ԵĴʻ��ֵȡ�
 * <p>
 * 
 * @author Zhiliang Wang [qieqie.wang@gmail.com]
 * 
 * @see CJKKnife
 * 
 * @since 1.0
 */
public interface Dictionaries {
	/**
	 * �ʻ���ֵ�
	 * 
	 * @return
	 */
	public Dictionary getVocabulary();

	/**
	 * �����ֵ�
	 * 
	 * @return
	 */
	public Dictionary getConfucianFamilyNames();

	/**
	 * ���ԵĴ���
	 * 
	 * @return
	 */
	public Dictionary getNoiseCharactors();

	/**
	 * ���Եĵ���
	 * 
	 * @return
	 */
	public Dictionary getNoiseWords();

	/**
	 * ������λ
	 * 
	 * @return
	 */
	public Dictionary getUnits();
}
