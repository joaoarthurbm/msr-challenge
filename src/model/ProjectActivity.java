package model;

import java.util.Collection;

public interface ProjectActivity {
	
	public Collection<? extends Comment> getComments();
	public String getID();

}
