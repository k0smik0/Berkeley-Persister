/*******************************************************************************
 * Copyleft (c) 2015, "Massimiliano Leone - <maximilianus@gmail.com> - https://plus.google.com/+MassimilianoLeone"
 * This file (MismatchKeysException.java) is part of BerkeleyPersister.
 * 
 *     MismatchKeysException.java is free software: you can redistribute it and/or modify
 *     it under the terms of the Lesser GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 * 
 *     MismatchKeysException.java is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See   the Lesser GNU General Public License for more details.
 * 
 *     You should have received a copy of the Lesser GNU General Public License along with .  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package net.iubris.berkeley_persister.dao.base.exception;

public class MismatchKeysException extends UpdateException {

	private static final long serialVersionUID = 2469362429923667357L;

	public MismatchKeysException() {
	}

	public MismatchKeysException(String message) {
		super(message);
	}

	public MismatchKeysException(Throwable cause) {
		super(cause);
	}

	public MismatchKeysException(String message, Throwable cause) {
		super(message, cause);
	}

	public MismatchKeysException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
