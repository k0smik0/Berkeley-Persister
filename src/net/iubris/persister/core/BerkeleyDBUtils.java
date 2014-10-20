package net.iubris.persister.core;

import java.io.File;

import net.iubris.persister.config.StorageConfig;

public class BerkeleyDBUtils {

	public static File getSiloDir(String corpusName, String siloName) {
		String dirsPath = StorageConfig.SilosStorage + File.separatorChar + corpusName + File.separatorChar + siloName;
		File d = new File(dirsPath);
		if (!d.exists())
			d.mkdirs();
		return d;
	}
}
