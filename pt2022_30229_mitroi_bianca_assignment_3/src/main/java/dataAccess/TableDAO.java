package dataAccess;

import connection.ConnectionFactory;

import javax.swing.table.DefaultTableModel;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class TableDAO<T> {

    private Class<T> type;

    public TableDAO(){
        type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * Creeaza un tabel indiferent de ce fel de date primeste ca parametrii
     * @param list - ce a returnat o cautare pe tabel
     * @return - tabelul de afisat
     */

    public DefaultTableModel createTableModel(List<T> list) {
        Field[] fields = type.getDeclaredFields(); // list of fields of the element
        Object[][] data = new Object[1024][5]; // JTable data
        String[] header = new String[fields.length]; // JTable header
        try {
            for(int i = 0; i < fields.length; i++) { // for each field
                header[i] = fields[i].getName(); // we build the header
            }
            int counter = 0;
            for(int i = 0; i < list.size(); i++) { // for each element
                int j = 0;
                for(Field field : fields) { // for each field of the element
                    field.setAccessible(true); // we make them accessible
                    data[i][j] = field.get(list.get(counter)); // we build the data
                    j++;
                }
                counter++;
            }
            return new DefaultTableModel(data, header); // we return the finished JTable model
        } catch (IllegalAccessException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    /**
     * Creeaza interogarea ce returneaza rezultatul unei cautari pe tabel
     * @param field - criteriul de cautare
     * @return - interogarea de executat
     */

    public String createFindQuery(String field) {
        String query = "SELECT *\nFROM `" + type.getSimpleName().toLowerCase() + "`";
        if (!field.equals(""))
            query += "\nWHERE " + field + " = ?;";
        else
            query += ";";
        return query;
    }

    /**
     * Construieste rezultatele interogarii sub forma de lista
     * @param resultSet - setul de rezultate returnare de query
     * @return - lista de rezultate prelucrabila pentru afisarea tabelului
     */

    public List<T> buildResults(ResultSet resultSet) {
        List<T> results = new ArrayList<>();
        try {
            while (resultSet.next()) {
                T object = this.type.getDeclaredConstructor().newInstance();
                for (Field field : this.type.getDeclaredFields()) {
                    Object value = resultSet.getObject(field.getName());
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), this.type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(object, value);
                }
                results.add(object);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return results;
    }

    /**
     * Conexiunea cu baza de date
     * @return - lista de rezultate
     */

    public List<T> find() {
        Connection connection = null;
        PreparedStatement findStatement = null;
        ResultSet resultSet = null;
        String query = createFindQuery("");
        //System.out.println(query + "\n");
        try {
            connection = ConnectionFactory.getConnection();
            findStatement = connection.prepareStatement(query);
            resultSet = findStatement.executeQuery();

            return buildResults(resultSet);
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

}
