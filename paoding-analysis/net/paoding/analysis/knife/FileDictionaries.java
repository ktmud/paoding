/**
 * Copyright 2007 The Apache Software Foundation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.paoding.analysis.knife;

import java.util.Arrays;
import java.util.Properties;

import net.paoding.analysis.dictionary.BinaryDictionary;
import net.paoding.analysis.dictionary.Dictionary;
import net.paoding.analysis.dictionary.HashBinaryDictionary;

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
public class FileDictionaries implements Dictionaries {

	// -------------------------------------------------

	/**
	 * ���ڴ��ļ�ϵͳ�л�ȡ����
	 */
	private FileWordsLoader wordsLoader;

	// -------------------------------------------------

	/**
	 * �ʻ���ֵ�
	 */
	private Dictionary vocabulary;

	/**
	 * �����ֵ�
	 * 
	 */
	private Dictionary confucianFamilyNames;

	/**
	 * ���Եĵ���
	 */
	private Dictionary noiseCharactors;

	/**
	 * ���ԵĴ���
	 * 
	 */
	private Dictionary noiseWords;

	/**
	 * ������λ
	 */
	private Dictionary units;

	// -------------------------------------------------

	public FileDictionaries() {
		wordsLoader = new FileWordsLoader();
	}
	
	public FileDictionaries(Properties p) {
		wordsLoader = new FileWordsLoader(p);
	}

	// -------------------------------------------------
	/**
	 * �ʻ���ֵ�
	 * 
	 * @return
	 */
	public Dictionary getVocabulary() {
		if (vocabulary == null) {
			synchronized (this) {
				if (vocabulary == null) {
					String[] words = wordsLoader.getVocabulary().toArray(
							new String[0]);
					Arrays.sort(words);
					// �����5639�����д����ȡ0x2fff=x^13>8000>8000*0.75=6000>5639
					vocabulary = new HashBinaryDictionary(words, 0x2fff, 0.75f);
				}
			}
		}
		return vocabulary;
	}

	/**
	 * �����ֵ�
	 * 
	 * @return
	 */
	public Dictionary getConfucianFamilyNames() {
		if (confucianFamilyNames == null) {
			synchronized (this) {
				if (confucianFamilyNames == null) {
					String[] words = wordsLoader.getConfucianFamilyNames()
							.toArray(new String[0]);
					Arrays.sort(words);
					confucianFamilyNames = new BinaryDictionary(words);
				}
			}
		}
		return confucianFamilyNames;
	}

	/**
	 * ���ԵĴ���
	 * 
	 * @return
	 */
	public Dictionary getNoiseCharactors() {
		if (noiseCharactors == null) {
			synchronized (this) {
				if (noiseCharactors == null) {
					String[] words = wordsLoader.getNoiseCharactors().toArray(
							new String[0]);
					Arrays.sort(words);
					noiseCharactors = new HashBinaryDictionary(words, 256,
							0.75f);
				}
			}
		}
		return noiseCharactors;
	}

	/**
	 * ���Եĵ���
	 * 
	 * @return
	 */
	public Dictionary getNoiseWords() {
		if (noiseWords == null) {
			synchronized (this) {
				if (noiseWords == null) {
					String[] words = wordsLoader.getNoiseWords().toArray(
							new String[0]);
					Arrays.sort(words);
					noiseWords = new BinaryDictionary(words);
				}
			}
		}
		return noiseWords;
	}

	/**
	 * ������λ
	 * 
	 * @return
	 */
	public Dictionary getUnits() {
		if (units == null) {
			synchronized (this) {
				if (units == null) {
					String[] words = wordsLoader.getUnits().toArray(
							new String[0]);
					Arrays.sort(words);
					units = new HashBinaryDictionary(words, 1024, 0.75f);
				}
			}
		}
		return units;
	}
}
