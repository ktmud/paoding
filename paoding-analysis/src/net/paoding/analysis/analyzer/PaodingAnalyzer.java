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
package net.paoding.analysis.analyzer;

import java.io.Reader;

import net.paoding.analysis.knife.CJKKnife;
import net.paoding.analysis.knife.Knife;
import net.paoding.analysis.knife.Paoding;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;

/**
 * PaodingAnalyzer�ǻ��ڡ��Ҷ���ţ����ܵ�Lucene������������ǡ��Ҷ���ţ����ܶ�Lucene����������
 * <p>
 * 
 * PaodingAnalyzer���̰߳�ȫ�ģ����������ʹ��ͬһ��PaodingAnalyzerʵ���ǿ��еġ�<br>
 * PaodingAnalyzer�ǿɸ��õģ��Ƽ����ͬһ��PaodingAnalyzerʵ����
 * <p>
 * 
 * ������Ҫ�ر������Ӧͨ�����캯����knife������(setter)�����Զ��Ƶ�Knifeʵ����
 * <p>
 * 
 * @author Zhiliang Wang [qieqie.wang@gmail.com]
 * 
 * @see PaodingTokenizer
 * @see Knife
 * @see Paoding
 * @see CJKKnife
 * @see TokenCollector
 * 
 * @since 1.0
 * 
 */
public class PaodingAnalyzer extends Analyzer {

	// -------------------------------------------------

	/**
	 * ��ģʽ�ڽ�������ʱʹ�ã��ܹ�ʹ��������ÿ�����ܵĴ��ｨ������
	 */
	public static final int WRITER_MODE = 1;

	/**
	 * ��ģʽ���û�����ʱʹ�ã�ʹ�û������Ľ��ƥ������
	 */
	public static final int QUERY_MODE = 2;

	// -------------------------------------------------
	/**
	 * ������PaodingTokenizer�ṩ���ֽ��ı��ַ�
	 * 
	 * @see PaodingTokenizer#next()
	 * 
	 */
	private Knife knife;

	/**
	 * @see #WRITER_MODE
	 * @see #QUERY_MODE
	 */
	private int mode = WRITER_MODE;

	// -------------------------------------------------

	public PaodingAnalyzer() {
	}

	/**
	 * @see #setKnife(Knife)
	 * @param knife
	 */
	public PaodingAnalyzer(Knife knife) {
		this.knife = knife;
	}

	/**
	 * @see #setKnife(Knife)
	 * @see #setMode(int)
	 * @param knife
	 * @param mode
	 */
	public PaodingAnalyzer(Knife knife, int mode) {
		this.knife = knife;
		this.mode = mode;
	}
	


	/**
	 * @see #setKnife(Knife)
	 * @see #setMode(int)
	 * @param knife
	 * @param mode
	 */
	public PaodingAnalyzer(Knife knife, String mode) {
		this.knife = knife;
		this.setMode(mode);
	}

	public static PaodingAnalyzer writerMode(Knife knife) {
		return new PaodingAnalyzer(knife, WRITER_MODE);
	}
	
	
	public static PaodingAnalyzer queryMode(Knife knife) {
		return new PaodingAnalyzer(knife, QUERY_MODE);
	}

	// -------------------------------------------------

	public Knife getKnife() {
		return knife;
	}

	public void setKnife(Knife knife) {
		this.knife = knife;
	}

	public int getMode() {
		return mode;
	}

	/**
	 * ���÷�����ģʽ��дģʽ(WRITER_MODE)�����ģʽ(QUERY_MODE)����һ�֡�Ĭ��Ϊдģʽ��
	 * <p>
	 * WRITER_MODE�ڽ�������ʱʹ�ã��ܹ�ʹ��������ÿ�����ܵĴ��ｨ������<br>
	 * QUERY_MODE���û�����ʱʹ�ã�ʹ�û������Ľ��ƥ������
	 * 
	 * @param mode
	 */
	public void setMode(int mode) {
		this.mode = mode;
	}
	
	public void setMode(String mode) {
		if ("writer".equalsIgnoreCase(mode)){
			this.mode = WRITER_MODE;
		}
		else if ("query".equalsIgnoreCase(mode)){
			this.mode = QUERY_MODE;
		}
	}

	// -------------------------------------------------

	@Override
	public TokenStream tokenStream(String fieldName, Reader reader) {
		if (knife == null) {
			throw new NullPointerException("knife should be set before token");
		}
		// PaodingTokenizer��TokenStreamʵ�֣�ʹ��knife����reader������ı�
		return new PaodingTokenizer(reader, knife, createTokenCollector());
	}

	protected TokenCollector createTokenCollector() {
		switch (mode) {
		case WRITER_MODE:
			return new WriterTokenCollector();
		case QUERY_MODE:
			return new QueryTokenCollector();
		default:
			throw new IllegalArgumentException("wrong mode");
		}
	}

}
