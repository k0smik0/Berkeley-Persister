package net.iubris.persister.io;

public interface Committable {
	public void beginTransaction();
	public void commit();
}
