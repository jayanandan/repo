package com.lab.ui.fields;

import java.awt.Component;

public interface ComponentValueHandler<P extends Component, Q> {
	public void setValue(P p,Q q);
	public Q getValue(P p);
}
