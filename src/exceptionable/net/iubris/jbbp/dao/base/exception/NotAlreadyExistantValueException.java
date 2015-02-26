/*******************************************************************************
 * Copyleft (c) 2015, "Massimiliano Leone - <maximilianus@gmail.com> - https://plus.google.com/+MassimilianoLeone"
 * This file (NotAlreadyExistantValueException.java) is part of BerkeleyPersister.
 * 
 *     NotAlreadyExistantValueException.java is free software: you can redistribute it and/or modify
 *     it under the terms of the Lesser GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 * 
 *     NotAlreadyExistantValueException.java is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See   the Lesser GNU General Public License for more details.
 * 
 *     You should have received a copy of the Lesser GNU General Public License along with .  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package net.iubris.jbbp.dao.base.exception;

public class NotAlreadyExistantValueException extends UpdateException {

	private static final long serialVersionUID = -7172016836315207653L;

	public NotAlreadyExistantValueException() {
		super();
	}

	public NotAlreadyExistantValueException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NotAlreadyExistantValueException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotAlreadyExistantValueException(String message) {
		super(message);
	}

	public NotAlreadyExistantValueException(Throwable cause) {
		super(cause);
	}
}
