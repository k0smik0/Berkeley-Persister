/*******************************************************************************
 * Copyleft (c) 2015, "Massimiliano Leone - <maximilianus@gmail.com> - https://plus.google.com/+MassimilianoLeone"
 * This file (BerkeleyDBUtils.java) is part of BerkeleyPersister.
 * 
 *     BerkeleyDBUtils.java is free software: you can redistribute it and/or modify
 *     it under the terms of the Lesser GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 * 
 *     BerkeleyDBUtils.java is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See   the Lesser GNU General Public License for more details.
 * 
 *     You should have received a copy of the Lesser GNU General Public License along with .  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
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
