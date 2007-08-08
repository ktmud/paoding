package net.paoding.analysis.knife;

import java.util.Enumeration;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import net.paoding.analysis.Config;

public class PaodingMaker {
	
	private static Log log = LogFactory.getLog(PaodingMaker.class);
	
	public static Paoding make() {
		return make(Config.properties());
	}

	@SuppressWarnings("unchecked")
	public static Paoding make(Properties p) {
		try {
			Paoding paoding = new Paoding();
			// ��װ�����ֵ�-���Զ�Ѱ�ң����������ȡ��·���е�paoding-analysis.properties�ļ�
			// �������ڸ������ļ�����һ��ʹ��Ĭ�����ã����ֵ����ļ�ϵͳ��ǰ·����dic��(����·��dic��)
			Dictionaries dictionaries = new FileDictionaries(p);
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
			return paoding;
		} catch (Exception e) {
			throw new IllegalStateException("Wrong paoding properties config.",
					e);
		}
	}
}
