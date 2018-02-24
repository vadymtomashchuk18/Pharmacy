package com.pharmacy.view.elements;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.table.AbstractTableModel;

import com.pharmacy.annotations.TableColumn;
import com.pharmacy.annotations.Tableable;

public final class CustomTableModel<EType> extends AbstractTableModel {
    private List<String> columnNames;
    private Map<Integer, Method> method;
    private List<EType> data;
    private TableSelectionEventHandler<EType> handler;
    private Class<EType> elemType;
    
    public CustomTableModel(List<EType> entities, Class<EType> clazz) {
        this(entities, clazz, null);
    }
    
    @SuppressWarnings("unchecked")
    public CustomTableModel(List<EType> entities, Class<EType> clazz, TableSelectionEventHandler<EType> handler) {
        elemType = clazz;
        columnNames = new LinkedList<>();
        method = new HashMap<>();
        data = entities;
        this.handler = handler;
        
        if (elemType.isAnnotationPresent(Tableable.class)) {
            Method[] methods = elemType.getMethods();
            for (Method m : methods) {
                TableColumn c = m.getAnnotation(TableColumn.class);
                
                if (c != null) {
                    columnNames.add(c.name());
                    method.put(columnNames.size() - 1, m);
                }
            }
        }
    }
    
    @Override
    public int getColumnCount() {
        return columnNames.size();
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public String getColumnName(int col) {
        return columnNames.get(col);
    }

    @Override
    public Object getValueAt(int row, int col) {
        try {
            return method.get(col).invoke(data.get(row));
        } catch (Exception ignored) { }
        return "Error";
    }
    
    @Override
    public Class getColumnClass(int c) {
        Object o = getValueAt(0, c);
        if (o == null) {
            return String.class;
        }
        return o.getClass();
    }
    
    public EType getAtRow(int row) {
        if (row < 0 || row > data.size()) {
            throw new IndexOutOfBoundsException();
        }
        
        return data.get(row);
    }
   
    public interface TableSelectionEventHandler<EType> {
        public void handle(EType object, int row);
    }
}
