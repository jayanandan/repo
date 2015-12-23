package com.lab.ui.fields;

import java.io.Serializable;

public interface DataModel<T> extends Serializable{
	public T getUniqueId();
	public void setUniqueId(T id);
}
