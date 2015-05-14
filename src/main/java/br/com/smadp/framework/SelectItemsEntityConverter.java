package br.com.smadp.framework;

import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UISelectItems;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author kurt
 */
@FacesConverter(value = "SelectItemsEntityConverter")
public class SelectItemsEntityConverter implements Converter {
	
	private static final String ENTITY_REPOSITY_ITEMS = "entityRepositoryItems";

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if(value == null) {
			return null;
		}
		
		Long id = Long.valueOf(value);
		List<PersistentEntity> entities = (List<PersistentEntity>) component.getAttributes().get(ENTITY_REPOSITY_ITEMS);
		for(PersistentEntity entity : entities) {
			if(entity.getId().equals(id)) {
				return entity;
			}
		}
		
		throw new ConverterException(new FacesMessage("Não foi possível converter a entidade."));
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value == null) {
			return "";
		}
		
		HtmlSelectOneMenu selectOneMenu = (HtmlSelectOneMenu) component;
		for(UIComponent kid : selectOneMenu.getChildren()) {
			if(kid instanceof UISelectItems) {
				UISelectItems items = (UISelectItems) kid;
				selectOneMenu.getAttributes().put(ENTITY_REPOSITY_ITEMS, items.getValue());
			}
		}
		
		if(value instanceof PersistentEntity) {
			PersistentEntity entity = (PersistentEntity) value;
			return entity.getId().toString();
		}
		
		return null;
	}
	
}
