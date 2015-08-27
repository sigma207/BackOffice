package com.jelly.jt8.bo.util;

import com.jelly.jt8.bo.model.BaseModel;
import org.apache.commons.beanutils.BeanUtils;

import javax.persistence.Column;
import javax.persistence.Id;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by user on 2015/8/11.
 */
public class RsMapper {
    @SuppressWarnings("unchecked")
    public static void map(ResultSet rs, List list, Class outputClass) {
        try {
            if (rs != null) {
                ResultSetMetaData rsmd = rs.getMetaData();
                Field[] fields = outputClass.getDeclaredFields();
                BeanInfo info = Introspector.getBeanInfo(outputClass);
                PropertyDescriptor[] props = info.getPropertyDescriptors(); //Gets all the properties for the class.
                Map<String,Field> fieldMap = new HashMap<String, Field>();
                for (Field field : fields) {
                    fieldMap.put(field.getName().toUpperCase(),field);
                }
                for(Method method:outputClass.getDeclaredMethods()){
                    Column column = method.getAnnotation(javax.persistence.Column.class);
                    if(column!=null){
                        for (PropertyDescriptor pd : props){
                            if(method.equals(pd.getWriteMethod()) || method.equals(pd.getReadMethod())){
                                fieldMap.put(column.name().toUpperCase(), fieldMap.get(pd.getDisplayName().toUpperCase()));
                            }
                        }
                    }
                }
                Object bean = outputClass.newInstance();
                boolean copyId = (bean instanceof BaseModel);
                while (rs.next()) {
                    bean = outputClass.newInstance();
                    for (int _iterator = 0; _iterator < rsmd.getColumnCount(); _iterator++) {
                        String columnName = rsmd.getColumnName(_iterator + 1);
                        Object columnValue = rs.getObject(_iterator + 1);
                        if(columnValue != null){
                            Field field = fieldMap.get(columnName.toUpperCase());
                            if(field!=null){
                                BeanUtils.setProperty(bean, field.getName(), columnValue);
                                if(copyId){
                                    Id id = field.getAnnotation(javax.persistence.Id.class);
                                    if(id!=null){
                                        BeanUtils.setProperty(bean, "id", columnValue);
                                    }
                                }
                            }
                        }
                    }
                    list.add(bean);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
