package net.iubris.berkeley_persister.io;

public interface Committable {
	public void beginTransaction();
	public void commit();
}
