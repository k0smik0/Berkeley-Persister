/*******************************************************************************
 * Copyleft (c) 2015, "Massimiliano Leone - <maximilianus@gmail.com> - https://plus.google.com/+MassimilianoLeone"
 * This file (ExistantValueException.java) is part of BerkeleyPersister.
 * 
 *     ExistantValueException.java is free software: you can redistribute it and/or modify
 *     it under the terms of the Lesser GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 * 
 *     ExistantValueException.java is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See   the Lesser GNU General Public License for more details.
 * 
 *     You should have received a copy of the Lesser GNU General Public License along with .  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package net.iubris.jbbp.dao.base.exception;

public class ExistantValueException extends Exception {

	private static final long serialVersionUID = -7969921610422818406L;

	public ExistantValueException() {
		super();
	}

	public ExistantValueException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ExistantValueException(String message, Throwable cause) {
		super(message, cause);
	}

	public ExistantValueException(String message) {
		super(message);
	}

	public ExistantValueException(Throwable cause) {
		super(cause);
	}

	
}
