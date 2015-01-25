package net.iubris.berkeley_persister.core.helper.base.utils;

import java.io.File;

import net.iubris.berkeley_persister.config.StorageConfig;

public class BerkeleyDBUtils {

	public static File getSiloDir(String corpusName, String siloName) {
		String dirsTreePath = StorageConfig.SilosStorage + File.separatorChar + corpusName + File.separatorChar + siloName;
		File dtp = new File(dirsTreePath);
		if (!dtp.exists())
			dtp.mkdirs();
		return dtp;
	}
}
