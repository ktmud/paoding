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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.Properties;

import net.paoding.analysis.Constants;
import net.paoding.analysis.exception.PaodingAnalysisException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * @author Zhiliang Wang [qieqie.wang@gmail.com]
 * 
 * @since 2.0.0
 */
public class PaodingMaker {

	private static Log log = LogFactory.getLog(PaodingMaker.class);

	/**
	 * ��ȡ��·���µ�paoding-analysis.properties�ļ�����֮����һ���µ�Paoding����
	 * <p>
	 * 
	 * @return
	 */
	public static Paoding make() {
		return make("classpath:paoding-analysis.properties");
	}

	/**
	 * ��ȡ��ָ��·���������ļ�(��������ļ���������·���£���Ӧ�ü�"classpath:"Ϊǰ׺)����֮����һ���µ�Paoding����
	 * <p>
	 * 
	 * @param properties
	 * @return
	 */
	public static Paoding make(String properties) {
		return implMake(properties(properties), properties);
	}

	/**
	 * ���ݸ��������Զ��󴴽�һ���µ�Paoding����
	 * <p>
	 * 
	 * һ��أ�������ʹ����Ӧ�ý�֮������һ��ȫ�ֹ���ı������ظ����á�
	 * 
	 * @param properties
	 * @return
	 */
	public static Paoding make(Properties p) {
		return implMake(p, p);
	}

	// --------------------------------------------------

	@SuppressWarnings("unchecked")
	private static Paoding implMake(Properties p, Object holderKey) {
		String singleton = p.getProperty(Constants.MAKE_PROTYPE, "singleton");
		Paoding paoding = PaodingHolder.get(holderKey);
		if (paoding != null) {
			return paoding;
		}
		paoding = new Paoding();
		try {
			// ��װ�����ֵ�-���Զ�Ѱ�ң����������ȡ��·���е�paoding-analysis.properties�ļ�
			// �������ڸ������ļ�����һ��ʹ��Ĭ�����ã����ֵ����ļ�ϵͳ��ǰ·����dic��(����·��dic��)
			Dictionaries dictionaries = new FileDictionaries(
					new FileWordsLoader(p));
			Enumeration names = p.propertyNames();
			while (names.hasMoreElements()) {
				String name = (String) names.nextElement();
				// ��paoding.knife.class��ͷ�ı���Ϊ��knife����
				if (name.startsWith("paoding.knife.class")) {
					String className = p.getProperty(name);
					Class clazz = Class.forName(className);
					Knife knife = (Knife) clazz.newInstance();
					if (knife instanceof DictionariesWare) {
						((DictionariesWare) knife)
								.setDictionaries(dictionaries);
					}
					// �ѵ������Ҷ�
					log.info("add knike: " + className);
					paoding.addKnife(knife);
				}
			}
			if ("singleton".equalsIgnoreCase(singleton)) {
				PaodingHolder.set(holderKey, paoding);
			}
			return paoding;
		} catch (Exception e) {
			throw new PaodingAnalysisException("Wrong paoding analysis config:"
					+ e.getMessage(), e);
		}
	}

	private static Properties properties(String path) {
		Properties p = new Properties();
		if (path == null) {
			return p;
		}
		InputStream in = null;
		try {
			if (path.startsWith("classpath:")) {
				path = path.substring("classpath:".length());
				URL url = PaodingMaker.class.getClassLoader().getResource(path);
				if (url == null) {
					throw new IllegalArgumentException("Not found " + path
							+ " in classpath.");
				}
				in = url.openStream();
			} else {
				File f = new File(path);
				if (!f.exists()) {
					throw new IllegalArgumentException("Not found " + path
							+ " in system.");
				}
				in = new FileInputStream(f);
			}
			p.load(in);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
				}
			}
		}
		return p;
	}
}
