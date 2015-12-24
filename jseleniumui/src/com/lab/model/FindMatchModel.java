package com.lab.model;

import com.lab.ui.fields.Component;
import com.lab.ui.fields.DataModel;
import com.lab.ui.fields.FieldType;
import com.lab.ui.fields.TextFieldValueHandler;
import com.lab.ui.fields.UIMapper;
import com.lab.ui.panels.SkipPanel;

@Component(id=SkipPanel.NAME)
public class FindMatchModel implements DataModel<Integer> {

	protected Integer uniqueId;
	
	@UIMapper(id="find.value",type=FieldType.TEXT_FIELD,handler={TextFieldValueHandler.class})
    protected String value;
	@UIMapper(id="find.by",type=FieldType.COMBO)
    protected String by;
	@UIMapper(id="find.id",type=FieldType.TEXT_FIELD)
    protected String id;
	@UIMapper(id="find.iframe",type=FieldType.CHECKBOX)
    protected Boolean iframe;
	@UIMapper(id="find.invisible",type=FieldType.CHECKBOX)
    protected Boolean invisible;
	@UIMapper(id="find.multiple",type=FieldType.CHECKBOX)
    protected Boolean multiple;
	@UIMapper(id="find.index",type=FieldType.TEXT_FIELD)
    protected Integer matchIndexes;
	@UIMapper(id="find.match",type=FieldType.TEXT_FIELD)	
	protected String test;
	
	public Integer getUniqueId() {
		return uniqueId;
	}
	public void setUniqueId(Integer uniqueId) {
		this.uniqueId = uniqueId;
	}	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getBy() {
		return by;
	}
	public void setBy(String by) {
		this.by = by;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Boolean getIframe() {
		return iframe;
	}
	public void setIframe(Boolean iframe) {
		this.iframe = iframe;
	}
	public Boolean getInvisible() {
		return invisible;
	}
	public void setInvisible(Boolean invisible) {
		this.invisible = invisible;
	}
	public Boolean getMultiple() {
		return multiple;
	}
	public void setMultiple(Boolean multiple) {
		this.multiple = multiple;
	}
	public Integer getMatchIndexes() {
		return matchIndexes;
	}
	public void setMatchIndexes(Integer matchIndexes) {
		this.matchIndexes = matchIndexes;
	}	
	public String getTest() {
		return test;
	}
	public void setTest(String test) {
		this.test = test;
	}
	@Override
	public String toString() {
		return "FindMatchModel [uniqueId=" + uniqueId + ", value=" + value
				+ ", by=" + by + ", id=" + id + ", iframe=" + iframe
				+ ", invisible=" + invisible + ", multiple=" + multiple
				+ ", matchIndexes=" + matchIndexes + ", test=" + test + "]";
	}
	
}
