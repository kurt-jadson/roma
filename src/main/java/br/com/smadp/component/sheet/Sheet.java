package br.com.smadp.component.sheet;

import com.lassitercg.faces.components.sheet.BadUpdate;
import com.lassitercg.faces.components.sheet.Column;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import javax.el.ValueExpression;
import javax.faces.context.FacesContext;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.model.BeanPropertyComparator;

/**
 *
 * @author kurt
 */
public class Sheet extends com.lassitercg.faces.components.sheet.Sheet {

	private List<Object> sortedList;

	@Override
	protected void renderRowUpdateScript(FacesContext context, Set<Integer> dirtyRows) {
		String jsVar = "PF('" + this.resolveWidgetVar() + "')";
		StringBuilder eval = new StringBuilder();

		for (Integer row : dirtyRows) {
			setRowIndex(context, row);
			// data is array of array of data
			com.lassitercg.faces.components.util.VarBuilder vbRow = new com.lassitercg.faces.components.util.VarBuilder(null, false);
			for (int col = 0; col < getColumns().size(); col++) {
				final Column column = getColumns().get(col);
				if (!column.isRendered()) {
					continue;
				}

				// render data value
				String value = getRenderValueForCell(context, getRowIndex(), col);
				vbRow.appendArrayValue(value, true);
			}
			eval.append(jsVar);
			eval.append(".cfg.data[");
			eval.append(Integer.toString(row));
			eval.append("]=");
			eval.append(vbRow.closeVar().toString());
			eval.append(";");
		}
		eval.append(jsVar);
		eval.append(".ht.render();");
		RequestContext.getCurrentInstance().getScriptsToExecute().add(eval.toString());
	}

	@Override
	protected void renderBadUpdateScript(FacesContext context) {
		String widgetVar = "PF('" + this.resolveWidgetVar() + "')";
		String badDataVar = this.getBadDataValue();
		StringBuffer sb = new StringBuffer(widgetVar);
		sb.append(".cfg.errors=");
		sb.append(badDataVar);
		sb.append(";");
		sb.append(widgetVar);
		sb.append(".ht.render();");
		RequestContext.getCurrentInstance().getScriptsToExecute().add(sb.toString());

		sb = new StringBuffer();
		sb.append(widgetVar);
		sb.append(".sheetDiv.removeClass('ui-state-error')");
		if (!getBadUpdates().isEmpty()) {
			sb.append(".addClass('ui-state-error')");
		}
		RequestContext.getCurrentInstance().getScriptsToExecute().add(sb.toString());
	}

	@Override
	public String getBadDataValue() {
		VarBuilder vb = new VarBuilder(null, true);
		for (BadUpdate badUpdate : getBadUpdates()) {
			final int row = badUpdate.getBadRowIndex();
			final int col = getRenderIndexFromRealIdx(badUpdate.getBadColIndex());
			vb.appendRowColProperty(row, col, badUpdate.getBadMessage().replace("'", "&apos;"), true);
		}
		return vb.closeVar().toString();
	}

	@Override
	public List<Object> getSortedValues() {
		if (sortedList == null) {
			sortAndFilter();
		}
		return sortedList;
	}

	@Override
	public void sortAndFilter() {
		sortedList = new ArrayList<>();
		Collection<?> values = (Collection<?>) getValue();
		if (values == null || values.isEmpty()) {
			return;
		}

		boolean filters = false;
		for (Column col : getColumns()) {
			if (StringUtils.isNotEmpty(col.getFilterValue())) {
				filters = true;
				break;
			}
		}

		if (filters) {
			// iterate and add those matching the filters
			FacesContext context = FacesContext.getCurrentInstance();
			Map<String, Object> requestMap = context.getExternalContext().getRequestMap();
			String var = getVar();
			for (Object obj : values) {
				requestMap.put(var, obj);
				try {
					if (matchesFilter(obj)) {
						sortedList.add(obj);
					}
				} finally {
					requestMap.remove(var);
				}
			}
		} else {
			sortedList.addAll(values);
		}

		ValueExpression veSortBy = this.getValueExpression("sortBy");
		if (veSortBy == null) {
			return;
		}

		Collections.sort(sortedList,
				new BeanPropertyComparator(veSortBy, getVar(),
						convertSortOrder(), null, false, Locale.US, 0));
	}

}
